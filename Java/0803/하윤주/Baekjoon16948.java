import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [0807] 백준 16948 데스 나이트
 * bfs를 이용한 최소 이동 횟수 구하기
 */

public class Baekjoon16948 {
	static int N;
	static boolean[][] visited;
	static Queue<Pair> q = new LinkedList<>();
	static int[] dr = {-2, -2, 0, 0, 2, 2};
	static int[] dc = {-1, 1, -2, 2, -1, 1};
	static Pair p1, p2;
	
	// 좌표 정보와 해당 좌표까지 이동 횟수 저장하기 위한 Pair 클래스
	static class Pair {
		Integer x, y, c;
		
		public Pair(Integer x, Integer y, Integer c) {
			this.x = x;
			this.y = y;
			this.c = c;  // 이동 횟수
		}
		
		public Integer xPoint() {
			return x;
		}
		
		public Integer yPoint() {
			return y;
		}
		
		public Integer count() {
			return c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		p1 = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		p2 = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		
		System.out.println(bfs());
	}

	// bfs로 탐색하며 이동 횟수 하나씩 늘려보기
	private static int bfs() {
		q.offer(p1);
		
		while(!q.isEmpty()) {
			Pair curP = q.poll();
			
			for (int i=0; i<6; i++) {
				int nX = curP.xPoint() + dr[i];   // 델타로 이동
				int nY = curP.yPoint() + dc[i];
				
				if (nX>=0 && nX<N && nY>=0 && nY<N && !visited[nX][nY]) {
					if (nX == p2.xPoint() && nY == p2.yPoint()) {   // 목표로 하는 좌표에 도달하면 이동 횟수 반환
						return curP.count()+1;
					}
					
					visited[nX][nY] = true;   // 최소 이동 횟수 구해야하므로 방문 여부 체크
					Pair nextP = new Pair(nX, nY, curP.count()+1);
					q.offer(nextP);
				}
			}
		}
		return -1;
	}

}
