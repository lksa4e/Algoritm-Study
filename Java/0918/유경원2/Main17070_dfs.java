import java.io.*;
import java.util.*;

public class Main17070_dfs {
	
	/* 
	 * 이전방향을 기억하여 다음 방향 가능한 경우의 수 dfs
	 * 초기값 (0,1) 가로방향 셋팅하고 출발 
	 * 이전방향이 가로면 가로or대각선으로 다음 dfs실행
	 * r 가로 c 세로 d 대각선
	 * 
	 * 방식      메모리  / 시간
	 * dfs : 17188 / 216
	 * dp : 14252 / 128
	 */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N , ans = 0;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,1,'r'); // (0,1) 위치에 가로방향 r 셋팅
		System.out.println(ans);
	}
	
	private static void dfs(int x, int y, char beforeDirection) {
		if(x>=N || y >=N || map[x][y]==1) return; // 범위 벗어나거나 벽이면 종료
		if(beforeDirection=='d' && (map[x-1][y]==1 || map[x][y-1]==1)) return; // 대각선으로 왔고, 왼쪽or위 벽이면 종료
		
		if(x == N-1 && y == N-1) { // 마지막 칸 도달시 ans증가, 종료
			ans++;
			return;
		}
		// 가로 r
		if(beforeDirection == 'r') {
			dfs(x, y+1, 'r');
			dfs(x+1, y+1, 'd');
		}
		
		// 세로 c
		if(beforeDirection == 'c') {
			dfs(x+1, y, 'c');
			dfs(x+1, y+1, 'd');
		}
			
		// 대각선 d
		if(beforeDirection == 'd') {
			dfs(x, y+1, 'r');
			dfs(x+1, y, 'c');
			dfs(x+1, y+1, 'd');
		}
	}
}