import java.io.*;
import java.util.*;

/**
 * G2 BOJ 16236 아기 상어
 * 메모리 : 14744kb 시간 : 164ms
 * 
 * 시키는 것을 수행하는 구현 문제
 */
public class BOJ16236_G4_아기상어 {
	static int N, map[][];
	static int answer = 0;
	static int shark_size = 2, shark_eat = 0, shark_x, shark_y;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];
		for(int i = 0; i < N+2; i++) Arrays.fill(map[i], -1);
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark_x = i;
					shark_y = j;
				}
			}
		}
		System.out.println(solve());
	}
	
	static int solve() {
		int answer = 0;
		
		while(true) {
			
			// 가장 가까운 물고기 먹기
			int result = eatClosestFish();
			
			// 만약 return받은 번호가 < 0 인 경우 먹을 수 있는 물고기가 없고 break
			if(result > 0) answer += result;
			else break;
		}
		return answer;
	}
	
	// 가장 가까운 크기의 물고기를 먹고 먹은 물고기의 번호 return
	static int eatClosestFish() {
		Queue<int[]> q = new ArrayDeque<int[]>();   // BFS용 x,y 좌표 큐
		boolean[][] visit = new boolean[N+2][N+2];  // BFS용 visit[][] 배열 
		List<int[]> fish = new ArrayList<int[]>();  // 먹을 수 있는 가장 가까운 물고기들 list 
		int cnt = 0;
		
		q.offer(new int[] {shark_x,shark_y});
		visit[shark_x][shark_y] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			while(size--> 0) {
				int[] shark = q.poll();
				int cur_x = shark[0];
				int cur_y = shark[1];
				
				for(int i = 0; i < 4; i++) {
					int nx = cur_x + "2110".charAt(i)-'1';
					int ny = cur_y + "1201".charAt(i)-'1';
					
					// 지도 밖으로 나가거나, 상어보다 큰 물고기가 있는 경우 전진 불가
					if(map[nx][ny] == -1 || map[nx][ny] > shark_size) continue;
					
					// 기존에 방문했던 좌표는 방문 불가
					if(visit[nx][ny]) continue;
					
					// 빈칸이거나 나와 같은 사이즈의 물고기인 경우는 그냥 지나감
					if(map[nx][ny] == 0 || map[nx][ny] == shark_size) {
						visit[nx][ny] = true;
						q.offer(new int[] {nx,ny});
					}
					// 나보다 작은 사이즈의 물고기인 경우
					else {
						// 먹을 수 있는 물고기 list에 추가
						fish.add(new int[] {nx,ny});
					}
				}
			}
			// 먹을 수 있는 물고기가 존재한다면
			if(fish.size() > 0) {
				// 가장 위쪽 -> 가장 왼쪽의 물고기를 고르기 위해
				// row 오름차 -> col 오름차 기준 정렬
				Collections.sort(fish, new Comparator<int[]>() {
					public int compare(int[] o1, int[] o2) {
						if(o1[0] == o2[0]) return o1[1] - o2[1];
						else return o1[0] - o2[0];
					}
				});
				
				int nx = fish.get(0)[0];
				int ny = fish.get(0)[1];
				
				// 상어 이동
				map[shark_x][shark_y] = 0;
				map[nx][ny] = 9;
				
				// 상어 좌표 변경
				shark_x = nx;
				shark_y = ny;
				
				// 상어 먹은횟수 & 사이즈 변경
				if(++shark_eat == shark_size) {
					shark_eat = 0;
					shark_size++;
				}
				return cnt;
			}
		}
		return -1;
	}
}
