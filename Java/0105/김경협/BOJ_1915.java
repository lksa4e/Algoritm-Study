import java.io.*;
import java.util.StringTokenizer;

/*
 * BOJ 1915번 가장 큰 정사각형
 * 
 * DP를 이용해 정사각형인지 체크하면서(1로 채워져있는지) 이동
 * 1. 정사각형 체크 메소드
 * 2. 왼쪽 위, 왼쪽, 위쪽 dp에서 가장 작은 값 + 1로 자신의 dp 채우기
 * 를 통해서 가장 큰 정사각형을 구했다.
 * 
 * 정사각형이 없어서 max가 0이었다면, 아예 1인 칸이 없었는지, 적어도 하나는 1인 칸이 있었는지를 체크했다.
 * 
 * 28908	356
 */
public class BOJ_1915 {
	static int N, M, map[][], dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				map[i][j] = arr[j] - '0';
			}
		}
		
		// 1,1부터 끝까지 체크
		int max = 0;
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < M; j++) {
				if(map[i][j] == 1) {	// 1인 칸에 도착
					if(isSquare(i,j)) {	// 자신 기준 왼쪽 위부터 1로 채워져있는지 체크
						dp[i][j] = getLeastDP(i,j);	// 저장한 사각형dp로 부터 최솟값 + 1 을 가져옴
						max = Math.max(dp[i][j], max);
					}
				}
			}
		}
		
		if(max == 0) {	// 맵을 훑었을 때 2x2 크기의 정사각형이 없는 경우,
			// 최대 크기가 1인 정사각형이 있는지 없는지 체크
			System.out.println(isZeroMap() ? 0 : 1);
		}else	// 2x2 이상의 정사각형 존재
			System.out.println((max+1)*(max+1));

	}
	
	static boolean isSquare(int row, int col) {	// 사각형 체크
		if(map[row-1][col-1] == 0) return false;
		if(map[row][col-1] == 0) return false;
		if(map[row-1][col] == 0) return false;
		return true;
	}
	
	static int getLeastDP(int row, int col) {	// 저장한 사각형들 dp에서 가장 최소의 값 +1 반환
		int min = Integer.MAX_VALUE;
		min = Math.min(dp[row-1][col-1], dp[row-1][col]);
		min = Math.min(min, dp[row][col-1]);
		
		return min + 1;
	}
	
	static boolean isZeroMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1) return false;
			}
		}
		return true;
	}

}
