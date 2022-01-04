import java.io.*;
import java.util.*;

/**
 * [0105] BOJ 1915 가장 큰 정사각형
 * DP, Bottom-Up
 * 
 * sol)
 * 2차원 배열의 첫번째 행과 열을 확인하며 넓이 DP 테이블을 초기화한다. 값이 1인 좌표만 넓이 DP 테이블에 1을 저장한다.
 * (1, 1)좌표부터 (n-1, m-1)좌표까지 넓이 테이블을 Bottom-Up 방식으로 채워간다.
 * 
 * DP 점화식을 생각해보면,
 * 현재 좌표에서 만들 수 있는 최대 넓이는 (i-1, j-1), (i-1, j), (i, j-1) 좌표에 저장된 넓이 중 최솟값을 구하고, 여기에 1을 더한 값이 된다.
 *     DP[i][j] = min(DP[i-1[j-1], DP[i-1][j], DP[i][j-1]) + 1
 * 현재 좌표에서 정사각형을 그리기 위해서는 인접한 3 좌표가 그리는 것보다 크게 그릴 수 없다.
 * 따라서 가장 작은 값에 자기 자신인 1을 더한 값이 최종 넓이가 되는 것!
 *
 */

public class BOJ_1915_가장큰정사각형 {
	static int n, m, maxSquare;
	static char[][] map;         // 원본 배열
	static int[][] dp;           // 넓이를 저장한 DP 테이블

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 원본 배열 초기화
		map = new char[n][m];
		for (int i=0; i<n; i++) map[i] = br.readLine().toCharArray();
		
		// DP 테이블 채우면서 최대 넓이 구하기
		findSquare();
		System.out.println(maxSquare);
	}

	// DP 테이블 채우고 최대 넓이 구하기
	private static void findSquare() {
		dp = new int[n][m];
		
		// 첫번째 열 DP 테이블 초기화
		for (int i=0; i<n; i++) {
			dp[i][0] = map[i][0] - '0';
			if (dp[i][0]==1) maxSquare = 1;     // 1인 좌표가 있다면 넓이 최댓값도 1로 초기화
		}
		// 첫번째 행 DP 테이블 초기화
		for (int j=0; j<m; j++) {
			dp[0][j] = map[0][j] - '0';
			if (dp[0][j]==1) maxSquare = 1;
		}
		
		// (1, 1) 부터 DP 테이블 채우기
		for (int i=1; i<n; i++) {
			for (int j=1; j<m; j++) {
				// 만약 1이 아니면 Pass
				if (map[i][j]=='0') continue;
				// 만약 인접한 3 좌표 중 빈칸이 존재한다면 무조건 최대 넓이는 (2*2)인 4를 뛰어 넘을 수 없으므로 1로 채움
				if (map[i-1][j]=='0' || map[i][j-1]=='0' || map[i-1][j-1]=='0') dp[i][j] = 1;
				else {
					// 인접한 3 좌표에 빈칸이 없이 다 채워져있다면 최솟값을 구해 자기 자신을 더함
					dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]);
					dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
					dp[i][j] += 1;
				}
				// 최대 넓이를 갱신
				maxSquare = Math.max(maxSquare, (int) Math.pow(dp[i][j], 2));
			}
		}
	}

}
