import java.io.*;
import java.util.*;

/**
 * 
 * [1009] BOJ 19237 어른상어
 *  시뮬레이션, bfs, 노가다의 끝판왕, 미세먼지 안녕이랑 비슷
 * 
 *  sol)
 * 1. 상어의 1칸 이동을 위해 M개의 상어가 모두 동시에 방향탐색
 * 		1) 각 상어가 자신이 이동할 좌표 탐색(bfs)
 * 			- 자신의 현재 방향을 확인
 * 			- 현재 방향에 따른 우선순위 방향 확인
 * 			- 우선순위에서 아무 냄새 없는 칸이 등장하면 해당 방향 결정
 * 			- 우선순위에서 자신의 냄새가 있는 칸이 등장하면 델타값 기억
 * 			- 만약 아무 냄새 없는 칸을 찾지 못했으면 위의 델타값을 이동 방향으로 결정
 *          - 상어의 현재 방향을 변경
 * 		2) 상어가 이동할 좌표 중 일치하는 것이 존재하면 상어 번호가 작은 것이 유지됨
 * 2. 결정된 칸으로 이동
 * 	    - 총 소요 시간 1 증가. 이때 1000초가 넘었고 다른 상어도 남아있으면 -1 반환
 * 	    - 1000초 이하이면서 1번 상어만 남았으면 총 소요시간 반환
 * 	    - 이전 칸의 잔향시간 1만큼 감소
 *      - 이동한 좌표를 bfs 큐에 삽입
 * 
 */

public class BOJ_19237_어른상어 {
	static int N, M, K, time, leftShark;         // 소요 시간과 남은 상어 수
	static int[][][] map;                        // 입력 지도(x, y좌표, 상어 종류, 잔향 시간)
	static int[] sharkDir;                       // 각 상어가 바라보는 방향
	static int[][][] priorityDir;                // 상어 별 우선순위 방향
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	static Queue<Integer> q = new ArrayDeque<Integer>();
	static Map<Integer, Integer> curSharks = new HashMap<>();      // 각 상어가 이동하게 될 좌표
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 지도 입력
		map = new int[N][N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				if (map[i][j][0]!=0) {
					map[i][j][1] = K;
					q.offer(i);          // 상어가 있던 곳은 큐에 좌표 삽입
					q.offer(j);
				}
			}
		}
		
		// 상어의 현재 방향 입력
		sharkDir = new int[M+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=M; i++) sharkDir[i] = Integer.parseInt(st.nextToken());
		
		// 상어 별 우선순위 방향 입력
		priorityDir = new int[M+1][5][4];
		for (int m=1; m<=M; m++) {
			for (int i=1; i<=4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<4; j++) priorityDir[m][i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 남은 상어를 M마리로 초기화한 뒤 1000초 동안 상어 이동
		leftShark = M;
		if (bfs()) System.out.println(time);
		else System.out.println(-1);
	}

	// 1번 상어만 남을 때까지 상어 이동
	private static boolean bfs() {
		while(true) {
			if (time>1000) return false;             // 1000초 초과 시 종료
			if (leftShark==1) return true;           // 1번 상어만 남으면 true 반환하고 종료
			
			// 모든 상어 동시에 이동할 방향 탐색
			curSharks.clear();                       // 상어 별 이동하게 될 좌표
			while (!q.isEmpty()) {
				int x = q.poll();
				int y = q.poll();
				int shark = map[x][y][0];            // 상어 번호
				int d = detectDir(shark, x, y);      // 이동할 방향 결정
				sharkDir[shark] = d;                 // 상어 별 현재 방향 변경하고
				detectSharks(shark, x, y, d);        // 이동할 방향에 다른 상어도 이동하려고 한다면 번호가 작은 상어 유지
			}
			// 모든 상어 동시에 이동
			decrSmell();                             // 지도의 잔향 감소
			move();                                  // 지도에 상어 이동 표시
			time++;                                  // 소요 시간 증가
		}
	}

	// 이동할 방향 결정 메서드
	private static int detectDir(int shark, int x, int y) {
		int my = -1;
		int curDir = sharkDir[shark];
		int[] priority = priorityDir[shark][curDir];
		
		// 현재 방향에서 우선순위대로 이동 시도
		for (int d : priority) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (!isInside(nx, ny)) continue;         // 경계 체크
			
			int nSmell = map[nx][ny][0];
			if (my==-1 && nSmell==shark) my = d;     // 자신의 냄새가 있는 칸이면 기억(이동할 후보)
			if (nSmell==0) return d;                 // 최우선 순위인 빈칸이면 바로 이동
		}
		return my;                                   // 결국 빈칸을 못찾았으면 자신의 냄새 중 최우선순위로 이동
	}
	
	// 이동할 방향에 다른 상어가 존재한다면 번호가 더 작은 상어만 유지하는 메서드
	private static void detectSharks(int shark, int x, int y, int d) {
		int nx = x + dx[d];
		int ny = y + dy[d];
		int nxny = nx*N+ny;
		Integer other = curSharks.get(nxny);       // 이미 맵에 다른 상어 존재한다면 null이 아니라 상어 번호가 저장됨
		
		if (other!=null) leftShark--;              // 다른 상어가 이미 존재하기때문에 둘 중 하나는 탈락해야하므로 남은 상어 수 감소
		if (other==null || other>shark) curSharks.put(nxny, shark);      // 작은 번호 상어로 대치
	}

	// 잔향 감소 메서드
	private static void decrSmell() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j][0]!=0 && --map[i][j][1]==0) map[i][j][0] = 0;
			}
		}
	}

	// 결정된 좌표로 이동하는 메서드
	private static void move() {
		Set<Integer> keys = curSharks.keySet();
		for (int xy : keys) {
			int x = xy/N;
			int y = xy%N;
			
			map[x][y][0] = curSharks.get(xy);       // 상어 번호
			map[x][y][1] = K;                       // 잔향
			
			q.offer(x);     // bfs 큐에 다음 좌표 삽입
			q.offer(y);
		}
	}
	
	// 경계 체크 메서드
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}

}
