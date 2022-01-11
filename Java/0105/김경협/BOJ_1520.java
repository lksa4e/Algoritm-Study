package BOJ_passed;
import java.io.*;
import java.util.*;

/*
 * BOJ 1520�� ������ ��
 * 
 * �׳� DFS�� �ϸ� �ð��ʰ� �� �� ���� ����,
 * DP�� � �� �������� �� �� �ִ� ����� ���� DFS Ž���� �����ϸ� ������ �ش�.
 * 
 * 36292KB	380ms
 */

public class BOJ_1520 {
	static final int dr[] = {0,0,-1,1}, dc[] = {-1,1,0,0};
	static int M,N, map[][], dp[][];
	static boolean visited[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		dp = new int[M][N];
		visited = new boolean[M][N];
		
		for(int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(0,0));
		
	}
	
	static int dfs(int row, int col) {
		if(row == M - 1 && col == N - 1) return 1;
		if(visited[row][col]) return dp[row][col];
		
		for(int i = 0; i < 4; i++) {
			int nextRow = row + dr[i];
			int nextCol = col + dc[i];
			if(isOutOfMap(nextRow, nextCol)) continue;
			if(map[row][col] <= map[nextRow][nextCol]) continue;
			
			dp[row][col] += dfs(nextRow, nextCol);
			visited[row][col] = true;
		}
		
		return dp[row][col];
	}
	
	static boolean isOutOfMap(int row, int col) {
		return row < 0 || col < 0 || row >= M || col >= N;
	}

}
