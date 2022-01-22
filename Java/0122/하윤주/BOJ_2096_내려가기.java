import java.io.*;
import java.util.*;

/**
 * [0119] BOJ 2096 내려가기
 * DP(Top-Down)
 * 
 * sol)
 * Top-Down 방식으로 현재 행의 각 열(0, 1, 2)에 도달할 수 있는 최댓값과 최솟값을 저장한 DP 테이블을 만든다
 * 점화식은 다음과 같다(최댓값은 min() -> max())
 *      DP[i][0] = map[i][0] + min(DP[i-1][0], DP[i-1][1])
 *      DP[i][1] = map[i][1] + min(DP[i-1][0], DP[i-1][1], DP[i-1][2])
 *      DP[i][2] = map[i][2] + min(DP[i-1][2], DP[i-1][1])
 *
 * 단, 메모리 초과를 주의해야한다. 현재 행에서의 최댓값(혹은 최솟값)만 찾으면 되므로 N개짜리 행의 배열을 만들 필요가 없다!!
 */

public class BOJ_2096_내려가기 {
	static int N, minResult=Integer.MAX_VALUE, maxResult;
	static int[][] map;
	static int[] minDP, maxDP;    // 현재 행의 각 열까지 최소(혹은 최대)로 도착한 경우를 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 초기 숫자판 입력
		map = new int[N][3];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 최소로 도착하는 경우와 최대로 도착하는 경우를 저장
		minDP = new int[3];
		maxDP = new int[3];
		for (int i=0; i<3; i++) minDP[i] = maxDP[i] = map[0][i];
		
		// DP 테이블 채운 뒤
		makeDPTable();
		
		// 마지막 행의 각 열(3개)에 최소, 최대로 도착한 경우 중 최솟값과 최댓값을 구하여 출력
		for (int i=0; i<3; i++) {
			minResult = Math.min(minResult, minDP[i]);
			maxResult = Math.max(maxResult, maxDP[i]);
		}
		System.out.println(maxResult + " " + minResult);
	}

	// DP 테이블 채우기
	private static void makeDPTable() {
		for (int i=1; i<N; i++) {
			// 0번째 열 기준 이전까지의 최솟값
			int leftMin = Math.min(minDP[0], minDP[1]);
			// 2번째 열 기준 이전까지의 최솟값
			int rightMin = Math.min(minDP[2], minDP[1]);
			// 0번째 열 기준 이전까지의 최댓값
			int leftMax = Math.max(maxDP[0], maxDP[1]);
			// 2번째 열 기준 이전까지의 최댓값
			int rightMax = Math.max(maxDP[2], maxDP[1]);
			
			// 0, 2, 1번째 열 기준 최솟값을 현재 DP 테이블에 저장
			minDP[0] = map[i][0] + leftMin;
			minDP[2] = map[i][2] + rightMin;
			minDP[1] = map[i][1] + Math.min(leftMin, rightMin);    // 1번째 열(가운데) 최솟값은 0번째와 2번째 열에서 구한 최솟값 중 가장 작은 값
			
			// 0, 2, 1번째 열 기준 최댓값을 현재 DP 테이블에 저장
			maxDP[0] = map[i][0] + leftMax;
			maxDP[2] = map[i][2] + rightMax;
			maxDP[1] = map[i][1] + Math.max(leftMax, rightMax);    // 1번째 열(가운데) 최댓값은 0번째와 2번째 열에서 구한 최댓값 중 가장 큰 값
		}
	}

}
