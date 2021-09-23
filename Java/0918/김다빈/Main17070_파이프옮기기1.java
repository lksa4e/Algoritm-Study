import java.io.*;
import java.util.*;

/**
 * 백준 17070번 파이프 옮기기1 
 * 
 * 풀이 : DFS 
 * 
 * (1,2)부터 시작해서 파이프를 옮길 수 있는 모든 방향을 시도해보고
 * (N,N)에 도착할 수 있는 가능한 방법의 수를 계산!
 * 
 * 파이프의 방향을 따져주는 것이 약간 번거로웠던 문제였다.
 * 
 * 16524KB	332ms
 */
public class Main17070_파이프옮기기1 {

	static int N, count = 0;
	static int[][] map;
	static int[] dr = {0,1,1};	// 가로, 세로, 대각선 
	static int[] dc = {1,0,1};
	static int[][] dir = {	{0,2},		// 가로 -> 가로, 대각선 
							{1,2},		// 세로 -> 세로, 대각선
							{0,1,2}};	// 대각선 -> 가로, 세로, 대각선 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];
		for (int i = 0; i < N+2; i++) {	// map 벽으로 초기화 
			Arrays.fill(map[i], 1);
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 시작점은 (1,2)의 가로 방향으로 설정 
		dfs(1,2,0);
		System.out.println(count);
	}

	// 파이프의 끝 인덱스 = (r,c), 파이프의 방향 = d (0 : 가로, 1 : 세로, 2 : 대각선)
	private static void dfs(int r, int c, int d) {
		if(r == N && c == N) {	// 파이프 한쪽 끝이 (N,N)이면 개수 + 1
			count++;
			return;
		}
		
		int nr, nc;
		for(int menu : dir[d]) {	// 현재 방향에서 놓을 수 있는 파이프의 방향 시도 
			if(isPossible(r, c, menu)) {
				nr = r + dr[menu];
				nc = c + dc[menu];
				dfs(nr, nc, menu);
			}
		}
	}

	private static boolean isPossible(int r, int c, int menu) {
		if(menu != 2) {	// 대각선이 아니면 다음 좌표만 검사 
			int nr = r + dr[menu];
			int nc = c + dc[menu];
			if(map[nr][nc] == 1) return false;
		} else {		// 대각선이면 가로, 세로, 대각선 모두 검사 
			for (int i = 0; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(map[nr][nc] == 1) return false;
			}
		}
		
		return true;
	}

}
