import java.io.*;
import java.util.*;

/**
 * [1023] BOJ 17142 연구소3
 * 완전탐색, 조합, 넥퍼
 * 
 * sol)
 * 1. 조합을 통해 바이러스 중 M개의 활성 바이러스 선택
 * 2. 선택한 바이러스를 동시에 큐에 삽입하여 bfs 탐색.
 *    - bfs로 벽이 아닌 좌표는 모두 바이러스 퍼뜨림. 
 *    - bfs 탐색하다가 빈칸 모두 채웠으면 바로 반복 탈출.
 * 3. 만약 bfs 탐색 끝났는데도 모든 빈칸 못채웠으면 예외처리
 * 
 * 시행착오)
 * 1. bfs 큐에 동시에 삽입하지 않아서 값이 더 크게 나옴.
 * 2. 큐를 전역 변수로 설정하다보니 미처 비우지 않은 채 다음 조합 경우의 수에서 bfs를 타 문제가 생김..
 *    테케를 반복적으로 돌리는 문제나 전역변수 쓸 때 주의하기.!!
 * 
 */

public class BOJ_17142_연구소3 {
	static int N, M, vacant, left, V, minTime=Integer.MAX_VALUE;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[] active;
	static int[][] visited;
	static List<Integer> virus = new ArrayList<>();         // 바이러스 좌표 위치
	static Queue<Integer> q = new ArrayDeque<Integer>();    // bfs를 위한 큐
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+2][N+2];
		for (int i=0; i<N+2; i++) Arrays.fill(map[i], 1);
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==2) virus.add(i*(N+2)+j);          // 바이러스 위치 기억
				if (map[i][j]==0) vacant++;                      // 빈칸 개수 기억
			}
		}
		
		// 넥퍼를 통한 활성 바이러스 조합 구하기
		V = virus.size();
		active = new int[V];
		for (int i=V-M; i<V; i++) active[i] = 1;
		
		do {
			q.clear();                      // 큐 초기화 주의!
			visited = new int[N+2][N+2];
			left = vacant;
			
			for (int i=0; i<V; i++) {
				if (active[i]==1) {         // 활성 바이러스는 큐에 삽입하여 bfs 탐색
					int vi = virus.get(i);
					int vx = vi/(N+2);
					int vy = vi%(N+2);
					q.offer(vx);
					q.offer(vy);
					visited[vx][vy] = -1;
				}
			}
			
			int spentTime = spread();       // bfs를 통해 현재 경우의 수에서 바이러스 퍼뜨리는 시간 계산
			if (left>0) continue;           // 만약 모든 빈칸 감염시키지 못했으면 pass
			
			minTime = Math.min(minTime, spentTime);      // 바이러스 퍼뜨리는 속도를 최솟값으로 갱신
		} while (nextPerm());
		
		if (minTime==Integer.MAX_VALUE) System.out.println(-1);     // 바이러스 못퍼뜨린 경우 예외처리
		else System.out.println(minTime);
	}

	// bfs로 바이러스 퍼뜨리기
	private static int spread() {
		int time = 0;
		while(!q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();
			
			if (left<=0) return time;      // 모든 빈칸에 바이러스 퍼뜨렸으면 종료
			
			for (int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (map[nx][ny]==1 || visited[nx][ny]!=0) continue;    // 벽이거나 이미 퍼뜨렸으면 pass
				
				if (visited[x][y]==-1) visited[nx][ny] = 1;            // 최초로 퍼뜨리는 곳은 1로 채우고
				else visited[nx][ny] = visited[x][y]+1;                // 나머지는 (이전 시간+1) 로 채우기
				time = Math.max(time, visited[nx][ny]);
				
				if (map[nx][ny]==0) left--;                            // 만약 채운 곳이 바이러스가 있던 곳이 아니고 빈칸이었으면 빈칸 감소
				
				q.offer(nx);
				q.offer(ny);
			}
		}
		return time;
	}

	// 조합을 위한 넥퍼
	private static boolean nextPerm() {
		int top = V-1;
		while(top>0 && active[top-1]>=active[top]) top--;
		if (top==0) return false;
		
		int target = V-1;
		while(active[top-1]>=active[target]) target--;
		
		swap(top-1, target);
		
		int bottom = V-1;
		while(bottom > top) swap(top++, bottom--);
		
		return true;
	}

	// 넥퍼를 위한 스왑
	private static void swap(int i, int j) {
		int tmp = active[i];
		active[i] = active[j];
		active[j] = tmp;
	}

}
