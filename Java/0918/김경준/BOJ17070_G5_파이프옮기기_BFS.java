import java.io.*;
import java.util.*;

/**
 * G5 BOJ 17070 괄호 추가하기 :
 * 
 * DFS - 메모리 : 15092kb 시간 : 292ms
 * BFS - 메모리 : 378952kb 시간 : 764ms
 * 
 * 해설은 DFS에
 */

public class BOJ17070_G5_파이프옮기기_BFS {
	static int N, map[][], answer = 0;
	static int dx[][] = {{0,-1,1}, {-1,1,1}, {0,1,1}};
	static int dy[][] = {{1,-1,1}, {-1,0,1}, {1,0,1}};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];
		for(int i = 0; i < N+2; i++) Arrays.fill(map[i], 1);
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		System.out.println(answer);
	}
	//bfs 풀이
	static void bfs() {
		Queue<Tuple> q = new ArrayDeque<Tuple>();
		q.offer(new Tuple(1,2,0));
		while(!q.isEmpty()) {
			Tuple cur = q.poll();
			int x = cur.x; int y = cur.y; int dir = cur.dir;
			if(x == N && y == N) {
				answer++;
				continue;
			}
			for(int i = 0; i < 3; i++) {
				if(dx[dir][i] == -1) continue;
				
				int nx = x + dx[dir][i];
				int ny = y + dy[dir][i];
				
				if(map[nx][ny] == 1) continue;
				if(i == 2) {
					if(map[nx-1][ny] == 1 || map[nx][ny-1] == 1) continue;
				}
				q.offer(new Tuple(nx, ny, i));
			}			
		}
	}
	static class Tuple{
		int x,y,dir;
		public Tuple(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
