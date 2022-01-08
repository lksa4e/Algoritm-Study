import java.io.*;
import java.util.*;

/*
 * BOJ 1937�� ������� �Ǵ�
 * 
 * DP�� DFS
 * Ž���� ���� ��ġ���� ���� ���� �����θ� ������ �� ������
 * �ִ�� �̵��� �� �ִ� ĭ�� ������ ����Ѵ�.
 * 
 * DP�� �� ĭ���� �̵��� �� �ִ� �ִ� ĭ���� �����ߴ�.
 * DFS�� ���鼭 �� ĭ�� �ִ� ĭ���� DP�� �����ϸ鼭 ��������.
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
		// map ��ȯ�ϸ鼭 dp ä���ֱ�
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
