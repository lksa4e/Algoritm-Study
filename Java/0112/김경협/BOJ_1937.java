import java.io.*;
import java.util.*;

/*
 * BOJ 1937번 욕심쟁이 판다
 * 
 * DP와 DFS
 * 탐색은 현재 위치보다 값이 높은 곳으로만 움직일 수 있으며
 * 최대로 이동할 수 있는 칸의 개수를 출력한다.
 * 
 * DP로 그 칸에서 이동할 수 있는 최대 칸수를 저장했다.
 * DFS로 돌면서 각 칸의 최대 칸수를 DP로 저장하면서 움직였다.
 * 
 * 37272KB	472ms
 */

public class BOJ_1937 {

	static int N, map[][], dp[][];
	static final int dr[] = {-1,1,0,0}, dc[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dp = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxRoute = 0;
		// map 순환하면서 dp 채워넣기
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(dp[r][c] == 0) {
					maxRoute = Math.max(dfs(r,c), maxRoute);
				}
			}
		}
		
		System.out.println(maxRoute);
		
	}
	
	static int dfs(int row, int col) {
		
		int currVal = map[row][col], maxRoute = 1;
		for(int i = 0; i < 4; i++) {
			int nextRow = row + dr[i];
			int nextCol = col + dc[i];
			
			if(isOutOfMap(nextRow, nextCol)) continue;
			if(currVal >= map[nextRow][nextCol]) continue;
			int nextRoute = 0;
			if(dp[nextRow][nextCol] == 0) {
				nextRoute = dfs(nextRow, nextCol) + 1;
			} else {
				nextRoute = dp[nextRow][nextCol] + 1;
			}
			
			maxRoute = Math.max(nextRoute, maxRoute);
		}
		
		dp[row][col] = maxRoute;
		return maxRoute;
	}
	
	static boolean isOutOfMap(int row, int col) {
		return row < 0 || col < 0 || row >= N || col >= N;
	}

}
