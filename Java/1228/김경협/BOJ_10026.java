import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * BOJ 10026번 적록색양
 * 
 * DFS
 * 
 * 골드 5답지 않게 단순했던 문제
 * 인자로 적록색약인지만 체크하고 DFS로 탐색했다.
 * visited를 두 종류를 써서 적록색약용 구역과 아닌 사람용 구역을 구분했다.
 * 
 * 19680KB	172ms
 */

public class BOJ_10026 {
	static int N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static char[][] map;
	static boolean[][] visited, visitedForRG;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][];
		
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();
		
		visited = new boolean[N][N];		// 적록색약 아님
		visitedForRG = new boolean[N][N];	// 적록색약
		
		int cnt = 0, cntRG = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					dfs(i,j,false);
					cnt++;
				}
				if(!visitedForRG[i][j]) {
					dfs(i,j,true);
					cntRG++;
				}
			}
		}
		
		System.out.println(cnt + " " + cntRG);
	}
	
	static void dfs(int row, int col, boolean isRGWeakness) {
		
		char color = map[row][col];
		for (int i = 0; i < 4; i++) {
			int nextRow = row + dr[i];
			int nextCol = col + dc[i];
			
			if(isOutOfMap(nextRow, nextCol)) continue;
			
			char nextColor = map[nextRow][nextCol];
			
			if(isRGWeakness && visitedForRG[nextRow][nextCol]) continue;
			if(!isRGWeakness && visited[nextRow][nextCol]) continue;
			
			if(isRGWeakness) {	// 적록색약 일 때,
				if(color == 'R' || color == 'G') {	// 적록색약은 R과 G를 동일하게 취급하였음
					if(nextColor == 'R' || nextColor == 'G') {
						visitedForRG[nextRow][nextCol] = true;
						dfs(nextRow, nextCol, isRGWeakness);
					}
				}
				if(color == 'B' && nextColor == 'B') {
					visitedForRG[nextRow][nextCol] = true;
					dfs(nextRow, nextCol, isRGWeakness);
				}
			} else {	// 적록색약이 아닐 때
				if(color == nextColor) {
					visited[nextRow][nextCol] = true;
					dfs(nextRow, nextCol, isRGWeakness);
				}
			}
		}
	}
	
	static boolean isOutOfMap(int row, int col) {
		return row < 0 || col < 0 || row >= N || col >= N;
	}

}
