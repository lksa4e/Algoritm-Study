import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 16236번 아기 상어
 * 
 * BFS, 시뮬레이션
 * 
 * 상어의 위치에서 BFS를 돌려서 자기보다 작은 물고기를 먹는다.
 * 이 때 같은 거리를 가진 먹이 물고기 중에서 pq를 통해 가장 왼쪽 위에 있는 물고기를 선택했다.
 * 
 */

public class BOJ_16236 {
	static class Coord implements Comparable<Coord>{
		int row;
		int col;
		public Coord(int row, int col) {
			this.row = row;
			this.col = col;
		}
		@Override
		public int compareTo(Coord o) {
			return this.row == o.row ? this.col - o.col : this.row - o.row;
		}
	}

	static final int dr[] = {-1,1,0,0};
	static final int dc[] = {0,0,-1,1};
	static int N, row, col, map[][], sharkSize = 2, sharkGrow;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
				if(val == 9) {	// 상어의 현재 위치 저장
					row = i;
					col = j;
					map[i][j] = 0;
				}
			}
		}
		System.out.println(babyShark());
	}
	
	static int babyShark() {
		int cnt = 0;
		while(true) {
			int sharkMove = BFS();	// 상어가 현재 위치에서 최소 거리에 있는 물고기를 먹음
			cnt += sharkMove;
			if(sharkMove == 0) break;	// sharkMove가 0 이면 더 이상 먹을 고기 없기 때문에  break
		}
		return cnt; // 현재까지 상어가 움직인 거리를 반환
	}
	
	static int BFS() {
		Queue<Coord> q = new ArrayDeque<>();
		PriorityQueue<Coord> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		visited[row][col] = true;
		q.offer(new Coord(row,col)); // 상어 현재 위치 push
		
		int sharkMove = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Coord curr = q.poll();
				
				for (int j = 0; j < 4; j++) {
					int nextR = curr.row + dr[j];
					int nextC = curr.col + dc[j];
					
					if(isOutOfMap(nextR, nextC)) continue;	// 맵 밖으로는 못 나감
					if(visited[nextR][nextC]) continue;		// 이미 방문한 곳으로는 못 감
					int fishSize = map[nextR][nextC];		// 가려는 곳의 물고기 사이즈, 혹은 빈 칸
					
					if(sharkSize < fishSize) continue;		// 물고기 사이즈가 더 클 경우 못 지나감
					else if(sharkSize == fishSize || fishSize == 0) {	// 물고기의 사이즈가 상어와 같은 경우, 혹은 빈 칸인 경우 통과
						visited[nextR][nextC] = true;
						q.offer(new Coord(nextR,nextC));	// 큐에 저장
					} else {								// 상어보다 작아서 먹이가 되는 경우
						visited[nextR][nextC] = true;
						pq.offer(new Coord(nextR,nextC));	// pq에 저장
					}
				}
			}
			sharkMove++;	// 자신 주위로 현재 sharkMove만큼 탐색했음.
			
			if(!pq.isEmpty()) { 	// 상어보다 작은 먹이가 존재
				Coord fish = pq.poll();	// 먹이 중에서 가장 윗 칸, 왼쪽 칸에 있는 먹이
				map[fish.row][fish.col] = 0;
				sharkGrow++;
				if(sharkSize == sharkGrow) {	// 성장치가 사이즈만해졌을 경우, 상어 성장
					sharkSize++;
					sharkGrow = 0;
				}
				row = fish.row; col = fish.col;	// 먹은 위치를 상어 현재 위치로 바꿔주고 BFS 종료
				return sharkMove;
			}
		}
		// 먹을 수 있는 먹이가 없어서 엄마상어 콜
		return 0;
	}
	
	static boolean isOutOfMap(int r, int c) {
		return r < 0 || c < 0 || r >= N || c >= N;
	}
	
}
