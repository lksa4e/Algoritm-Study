import java.io.*;
import java.util.*;

/**
 * [1012] BOJ 16236 아기상어
 * bfs, 구현
 * 
 * sol)
 * bfs 탐색을 통해 자신에게 인접한 물고기 중 먹을 수 있는 물고기 탐색.
 * 이때 바로 먹으면 안되고 먹을 수 있는 물고기라면 그 중에서도 인덱스가 가장 작은 물고기(좌상단에 위치한 물고기)를 먹어야하므 우선순위 큐에 삽입하여 정렬한다.
 * 
 * 시행착오)
 * 상, 좌, 우, 하 방향으로 4방탐색하면 우선순위 조건에 맞게 물고기를 탐색할 수 있을 것 같았는데
 * 같은 거리에 있는 다른 좌표의 4방탐색 결과와도 비교해야하므로 모든 가능성 있는 물고기를 찾은 뒤, 그 중에서 가장 인덱스가 작은 물고기를 먹어야 했다.
 *
 */

public class BOJ_16236_아기상어 {
	static int N, sx, sy, sharkSize, eatCnt, time;
	static int[][] map;
	static boolean[][] eaten;
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	static Queue<Integer> q = new ArrayDeque<>();
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 지도 입력 및 상어 위치 기억
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==9) {
					sx = i; sy = j;
					map[i][j] = 0;
				}
			}
		}
		
		// 상어가 물고기를 먹으러 여정을 떠남
		sharkSize = 2;
		sharkTour();
		System.out.println(time);
	}
	
	// 물고기 먹기 시도
	private static void sharkTour() {
		eaten = new boolean[N][N];
		while (true) {
			pq.clear();
			time += bfs(sx, sy);
			
			if (pq.isEmpty()) return;      // 더이상 먹을 수 있는 물고기 없으면 탈출
			
			int next = pq.poll();          // 가장 인덱스 작은 최우선순위 물고기를 먹고
			int nx = next/N;
			int ny = next%N;
			if (++eatCnt>=sharkSize) {     // 먹고 나서 몸 사이즈 증가 체크
				sharkSize++;
				eatCnt = 0;
			}
			eaten[nx][ny] = true;
			sx = nx; sy = ny;
		}
	}

	// 같은 너비(거리)에 위치한 물고기 중 먹을 수 있는 후보 물고기 찾기
	private static int bfs(int x, int y) {
		q.clear();
		boolean[][] visited = new boolean[N][N];
		int depth = 0;
		q.offer(x); q.offer(y);
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int qSize = q.size()/2;
			
			// 너비가 같은 범위 내에 먹을 수 있는 물고기가 있는지 확인하고 없으면 더 넒은 너비로 넘어감
			while(qSize-->0) {
				int cx = q.poll();
				int cy = q.poll();
				
				for (int i=0; i<4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					
					// 경계 체크
					if (!isInside(nx, ny)) continue;
					
					// 이미 방문했거나 자신보다 크면 pass
					int fishSize = map[nx][ny];
					if (visited[nx][ny] || fishSize>sharkSize) continue;
					
					// 같거나 작으면 우선 방문할 수 있으므로 방문
					q.offer(nx); q.offer(ny);
					visited[nx][ny] = true;
					
					// 자신보다 작고 아직 안먹었으면 우선순위 큐에 저장하여 먹기 위한 후보에 올림
					if (!eaten[nx][ny] && fishSize>0 && fishSize<sharkSize) pq.offer(nx*N+ny);
				}
			}
			depth++;                            // 같은 너비 탐색 끝나면 너비 증가하고
			if (!pq.isEmpty()) return depth;    // 이번 너비에서 먹을 수 있는 물고기 있었으면 탈출
		}
		return 0;
	}
	
	// 경계체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}

}
