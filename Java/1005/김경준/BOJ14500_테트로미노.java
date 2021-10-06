import java.io.*;
import java.util.*;

/**
 * G5 BOJ 14500 테트로미노
 * 메모리 : 30664kb 시간 : 644ms
 * 
 * ㅜ 모양을 제외한 나머지 모양들은 단순하게 DFS를 수행하면 모두 만들 수 있는 모양이다.
 * 따라서 일반 모양인 경우에는 그냥 4방향 DFS를 수행하고 
 * ㅜ 모양인 경우에는 별도의 dx[], dy[] 배열을 통해 따로 DFS 처리한다.
 * 
 */
public class BOJ14500_테트로미노{
	static int N,M, map[][], answer = 0;
	static int dx[][] = { {0,0,0,1} , {0,0,-1,1}, {0,1,2,1} , {0,0,0,-1} };
	static int dy[][] = { {0,1,2,1}, {0,1,1,1}, {0,0,0,1}, {0,1,2,1} };
	static boolean visit[][];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		check();
		System.out.println(answer);
	}
	static void check() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				// 일반 DFS 돌리는 경우 
				visit[i][j] = true;
				normal_dfs(i,j, map[i][j], 1);
				visit[i][j] = false;
				// ㅜ 모양의 특별한 DFS를 돌리는 경우
				special_dfs(i,j);
			}
		}
	}
	// 단순하게 4방향 모두 갈 수 있는 DFS 함수
	static void normal_dfs(int x, int y, int sum, int cnt) {
		if(cnt == 4) {
			answer = Math.max(answer, sum);
			return;
		}
		for(int i = 0; i < 4; i++) {
			int nx = x + "2110".charAt(i)-'1';
			int ny = y + "1201".charAt(i)-'1';
			
			if(oob(nx,ny)) continue;
			if(visit[nx][ny]) continue;
			
			visit[nx][ny] = true;
			normal_dfs(nx, ny, sum + map[nx][ny], cnt + 1);
			visit[nx][ny] = false;
		}
	}
	// ㅜ 모양 처리 함수. 실제로는 DFS가 아니긴 함
	static void special_dfs(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int sum = 0;
			for(int j = 0; j < 4; j++) {
				int nx = x + dx[i][j];
				int ny = y + dy[i][j];
				
				if(oob(nx,ny)) break;
				
				sum += map[nx][ny];
				// j == 3인 상태에서 아래 조건을 통과했다는 것은 ㅜ 모양을 처리할 수 있다는 뜻
				if(j == 3) answer = Math.max(answer, sum);
			}
		}
	}
	static boolean oob(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
