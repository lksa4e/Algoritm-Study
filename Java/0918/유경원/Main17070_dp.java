import java.io.*;
import java.util.*;

public class Main17070_dp {
	/* 
	 * dp[i][j][0] 파이프 끝이 (i,j)좌표이고 가로방향일 경우
	 * = dp[i][j-1][0] + dp[i][j-1][2]; 
	 *   파이프 끝이 (i,j-1)좌표이고 가로 + 대각선
	 * 
	 * 방식      메모리  / 시간
	 * dfs : 17188 / 216
	 * dp : 14252 / 128
	 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1];
		int[][][] dp = new int[N+1][N+1][3]; // 0:가로 1:세로 2: 대각선
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][2][0] = 1;
		for(int i=1; i<=N; i++) {
			for(int j=3; j<=N; j++) {
				if(map[i][j]==1) continue;
				
				// 가로
				dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];
				// 세로
				dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];
				// 대각
				if(map[i-1][j]==1 || map[i][j-1]==1) continue;
				dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
					
			}
		}
		System.out.println(dp[N][N][0]+dp[N][N][1]+dp[N][N][2]);
	}
	
}