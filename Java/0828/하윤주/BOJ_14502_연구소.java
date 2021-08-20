import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [0821] 백준 14502 연구소
 * 완전탐색, 조합, 2차원 배열 | 90min
 * 
 * sol)
 * 1. 빈 공간 좌표들 중 3개를 뽑는 조합 생성, 이때 최초 빈 공간 개수 카운트 후 기억
 * 2. 각 조합에 벽을 배치(조합 별 별도의 복사된 지도 사용해야함)
 * 3. 벽이 아닌 빈 공간에 가능한 만큼 바이러스를 퍼뜨림(bfs를 통한 4방탐색), 이때 새롭게 퍼뜨린 바이러스 수 카운트 후 기억
 * 4. 1에서 기억한 최초 안전 구역과 3에서 새롭게 퍼뜨린 바이러스 구역의 개수 차이가 최소가 되는 조합 찾기
 * 
 * 좌표 표현에 대한 고민이 많은데 (0,0)에서 (N,M)을 0 ~ (N*M-1)로 일차원 배열 인덱스처럼 표현해봤습니다
 * 저한테는 이런 방법도 나름..? 괜찮은 것 같습니다
 * 
 * time_complex)
 * 조합 N^2C3 = 대략 40,000
 * 맵 복사 * (N^2)
 * bfs 탐색 대략 * (N^2)
 * 
 * 와우,, 저렇게 계산하면 대략 1.6억인데 제한시간 2초라서 가능한걸까요,,
 */

public class BOJ_14502_연구소 {
	static int N, M, initSafeArea, maxSafeArea;    // 행렬, 초기 0의 개수, 최종 0의 개수
	static int[][] map, copiedMap;                 // 원본 지도와 조합별 비교를 위한 복사된 지도
	static int[] combi;                            // 각 조합 경우
	static int[] dx = {0, 0, -1, 1};               // bfs 탐색을 위한 델타값
	static int[] dy = {-1, 1, 0, 0};
	static List<Integer> blankPoint = new ArrayList<Integer>();    // 초기 빈칸 좌표
	static List<Integer> virusPoint = new ArrayList<Integer>();   // 초기 바이러스 좌표
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 행렬 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 지도 생성
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 0) blankPoint.add(i*M + j);         // 초기 빈칸 좌표 기억
				else if (map[i][j] == 2) virusPoint.add(i*M + j);   // 초기 바이러스 좌표 기억
			}
		}
		
		// 초기 빈칸(0)의 개수
		initSafeArea = blankPoint.size();
		
		// 벽을 새롭게 세울 수 있는 경우의 수 탐색
		combi = new int[3];
		wallCombi(0, 0);
		
		// 출력
		System.out.println(maxSafeArea);
	}
	
	// 재귀를 통해 벽을 세우는 조합구하기. 조합이 생성되면 각 조합별 안전구역 개수 세서 최댓값 갱신
	private static void wallCombi(int depth, int start) {
		// 기저조건 - 조합이 완성되면(벽이 3개 세워지면) 조합 별 안전 구역 개수 카운트
		if (depth == 3) {
			
			// 지도 복사
			copyMap();
			
			for (int i=0; i<3; i++) {
				int x = combi[i] / M;
				int y = combi[i] % M;
				copiedMap[x][y] = 1;    // 벽 세우기
			}
			
			// 최초의 안전구역(0) 개수와 바이러스(2)가 새롭게 퍼진 구역의 개수 차이가 최대가 되는 경우 찾기
			maxSafeArea = Math.max(maxSafeArea, initSafeArea - (spreadVirus() + 3));
			
			return;
		}
		
		// 유도파트 - 벽(1) 세울 수 있는 좌표 조합짜기
		for (int i=start, size=blankPoint.size(); i<size; i++) {
			combi[depth] = blankPoint.get(i);
			wallCombi(depth+1, i+1);
		}
	}

	// 각 조합별 테스트해보기 위해 원본 맵 복사
	private static void copyMap() {
		copiedMap = new int[N][M];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) copiedMap[i][j] = map[i][j];
		}
	}

	// 가능한 만큼 바이러스(2) 퍼뜨릴 경우 몇개나 퍼뜨릴 수 있는지 개수 카운트
	private static int spreadVirus() {
		Queue<Integer> q;
		boolean[][] visited = new boolean[N][M];    // 방문 여부
		int newlySpreaded = 0;                      // 이번 조합에서 바이러스 새롭게 퍼진 개수
		
		for (int i=0; i<virusPoint.size(); i++) {   // 지도상의 모든 바이러스 좌표에 대해 전염 시도
			q = new ArrayDeque<Integer>();
			int curXY = virusPoint.get(i);
			
			q.offer(curXY);                         // 바이러스 좌표를 기준으로
			visited[curXY/M][curXY%M] = true;
			
			while (!q.isEmpty()) {
				int curQ = q.poll();
				
				for (int j=0; j<4; j++) {           // 4방 탐색
					int nx = (curQ / M) + dx[j];
					int ny = (curQ % M) + dy[j];
					
					if (isInside(nx, ny) && !visited[nx][ny]) {     // 경계 내부이고 아직 방문 안했으면
						visited[nx][ny] = true;
						
						if (copiedMap[nx][ny] != 0) continue;       // 오직 안전구역에만 전염시도
						
						newlySpreaded++;            // 새롭게 확산된 개수
						copiedMap[nx][ny] = 2;      // 확산됨을 표시
						q.offer(nx*M + ny);
					}
				}
			}
		}
		
		return newlySpreaded;
		
	}
	
	// 2차원 배열 경계 체크
	private static boolean isInside(int nx, int ny) {
		if (nx >=0 && nx < N && ny >= 0 && ny < M) return true;
		return false;
	}

}
