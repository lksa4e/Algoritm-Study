import java.io.*;
import java.util.*;

/**
 * 백준 14503번 로봇 청소기
 * 
 * 풀이 : BFS + 구현
 * 
 * 문제에서 제시한 로봇 청소기의 작동 과정을 그대로 코드로 옮겼다.
 * 
 * 시행착오) 
 * 처음 후진할 때 그냥 -1씩만 해줘서 안 풀려서 당황했다가
 * 바라보는 방향에 맞춰 다시 후진해줬다.. 
 * 
 * 14716KB	136ms
 */
public class Main14503_로봇청소기 {

	static int[] dr = {-1, 0, 1, 0};	// 상우하좌
	static int[] dc = {0, 1, 0, -1};
	
	// 로봇 청소기의 위치, 방향을 나타내는 클래스 
	static class RobotCleaner {
		int r, c, dir;

		public RobotCleaner(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 맵의 크기 입력받기 
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		
		// 로봇 청소기 시작 위치, 방향 입력받기 
		st = new StringTokenizer(br.readLine());
		int initR = Integer.parseInt(st.nextToken());
		int initC = Integer.parseInt(st.nextToken());
		int initDir = Integer.parseInt(st.nextToken());
		
		// 맵 정보 입력받기 
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 청소한 횟수 출력 
		System.out.println(bfs(R, C, initR, initC, initDir, map));
	}

	private static int bfs(int R, int C, int initR, int initC, int initDir, int[][] map) {
		int cnt = 0;	// 청소한 횟수 
		
		Queue<RobotCleaner> queue = new ArrayDeque<RobotCleaner>();
		boolean[][] visited = new boolean[R][C];
		
		// 시작점 설정
		queue.offer(new RobotCleaner(initR, initC, initDir));
		
		RobotCleaner cur = null;
		int r, c, dir, nr, nc;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			r = cur.r;
			c = cur.c;
			dir = cur.dir;
			
			// 1. 현재 위치 청소하기 (이전에 청소하지 않았으면)
			if(!visited[r][c]) {
				visited[r][c] = true;
				cnt++;				
			}
			
			// 2. 현재 방향 기준 왼쪽 방향부터 차례대로 인접한 칸 탐색
			boolean movable = false;	// 이동할 수 있는 칸이 존재하는지 체크 
			for (int i = 0; i < 4; i++) {
				int nd = (dir + 3 - i) % 4;
				
				nr = r + dr[nd];
				nc = c + dc[nd];
				
				// 청소한 적이 없고 벽이 아니면 청소하기 
				if(!visited[nr][nc] && map[nr][nc] != 1) {
					queue.offer(new RobotCleaner(nr, nc, nd));
					movable = true;
					break;
				}
			}
			
			// 3. 청소할 곳이 없다면 후진 시도 
			if(!movable) {
				nr = r + dr[(dir + 2) % 4];
				nc = c + dc[(dir + 2) % 4];
				
				if(map[nr][nc] != 1) {	// 후진하는 곳이 벽이 아니면 이동 (바라보는 방향은 유지)
					queue.offer(new RobotCleaner(nr, nc, dir));
				} else {	// 후진도 불가능하면 탐색 종료 
					break;
				}
			}
		}
		
		return cnt;
	}

}
