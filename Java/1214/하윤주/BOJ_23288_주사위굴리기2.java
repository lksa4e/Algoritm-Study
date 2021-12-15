import java.io.*;
import java.util.*;

/**
 * [1214] BOJ 23288 주사위 굴리기2
 *	시뮬레이션, 구현, bfs, dfs, 주사위
 *
 * sol)
 * - 주사위의 마주보는 면 숫자의 합은 7이므로 위, 상, 우 면의 숫자를 저장해두면 아래, 하, 좌면의 숫자를 구할 수 있음.
 * - 각 좌표 별 점수를 미리 계산한 뒤 이를 활용하면 시간을 조금 단축할 수 있음.
 * - 같은 숫자를 가진 인접한 좌표는 bfs, dfs 모두 탐색 가능
 * 
 * 시행착오)
 * 이동 방향을 시계, 반시계방향으로 90도 회전하는 부분에서 반시계방향은 (4+(dir-1))%4 방식으로 구현했는데
 * 4를 N으로 잘못써서 계속 틀린걸 못잡아냈다.. 하필 테케에서 N이 4였던지라 틀린 부분 못찾아낼 뻔 했다...
 * 
 */

public class BOJ_23288_주사위굴리기2 {
	static final int EAST = 0;
	static final int NORTH = 1;
	static final int WEST = 2;
	static final int SOUTH = 3;
	
	static int N, M, K, top, up, right, score, dir, curX, curY;
	static int[][] map;
	static int[][] scoreMap;           // 각 좌표 별 점수를 미리 계산하여 저장한 배열
	static int[] dx = {0, -1, 0, 1};   // 동, 북, 서, 남
	static int[] dy = {1, 0, -1, 0};
	static Queue<Integer> sameScore = new ArrayDeque<Integer>();    // bfs 탐색 시 같은 점수인 좌표들을 저장할 큐
	static Queue<Integer> q = new ArrayDeque<Integer>();            // bfs를 위한 큐
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 지도 입력
		map = new int[N][M];
		scoreMap = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 초기 상태
		top = 1;      // 주사위 위쪽면
		up = 2;       // 주사위 북쪽면
		right = 3;    // 주사위 동쪽면
		
		dir = EAST;   // 이동 방향 동쪽으로 시작
		
		curX = 0;     // 시작 좌표 (0, 0)
		curY = 0;
		
		// 좌표별 점수 초기화
		initScoreMap();
		
		// K번만큼 반복
		for (int i=0; i<K; i++) {
			moveDice();            // 주사위 이동하고
			getScore();            // 점수 취득한 뒤
			decideDirection();     // 다음 이동 방향 결정
		}
		
		// 점수 출력
		System.out.println(score);
	}

	// 주사위 이동
	private static void moveDice() {
		arrivePoint();     // 좌표 이동
		rotateDice();      // 주사위 상태 변경
	}
	
	// 좌표 이동
	private static void arrivePoint() {
		int nextX = curX + dx[dir];       // 현재 이동 방향대로 이동한 뒤
		int nextY = curY + dy[dir];
		
		if (!isInside(nextX, nextY)) {    // 해당 좌표가 경계 밖이면
			dir = (dir+2) % 4;            // 이동 방향을 반대로
			nextX = curX + dx[dir];
			nextY = curY + dy[dir];
		}
		
		curX = nextX;       // 최종 이동할 좌표 결정
		curY = nextY;
	}
	
	// 주사위 상태 변경
	private static void rotateDice() {
		int temp;
		switch (dir) {
		case EAST:           // 이동 방향이 동
			temp = top;
			top = 7-right;
			right = temp;
			break;
		case WEST:           // 이동 방향이 서
			temp = top;
			top = right;
			right = 7-temp;
			break;
		case SOUTH:          // 이동 방향이 남
			temp = top;
			top = up;
			up = 7-temp;
			break;
		case NORTH:          // 이동 방향이 북
			temp = top;
			top = 7-up;
			up = temp;
			break;
		}
	}

	// 점수 획득
	private static void getScore() {
		score += scoreMap[curX][curY];      // 초기 저장해둔 좌표별 점수를 취득
	}

	// 다음 이동 방향 결정
	private static void decideDirection() {
		int A = 7-top;                     // 주사위 바닥면과
		int B = map[curX][curY];           // 좌표 숫자 비교
		if (A > B) dir = (4+(dir-1))%4;
		else if (A < B) dir = (dir+1)%4;
	}
	
	// 좌표별 점수 저장
	private static void initScoreMap() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (scoreMap[i][j]==0) {       // 아직 방문 안했으면
					int b = map[i][j];
					int c = bfs(i, j, b);      // bfs 탐색
					setScoreMap(b*c);          // 같은 점수인 좌표들 점수 설정
				}
			}
		}
	}
	
	// 같은 숫자인 인접 좌표 bfs 탐색
	private static int bfs(int x, int y, int b) {
		sameScore.clear();         // 같은 숫자인 좌표들을 저장할 큐
		q.clear();                 // bfs 탐색을 위한 큐
		
		int cnt = 1;
		scoreMap[x][y] = cnt;
		
		q.offer(x);
		q.offer(y);
		sameScore.offer(x);
		sameScore.offer(y);
		
		while(!q.isEmpty()) {
			int cx = q.poll();
			int cy = q.poll();
			
			for (int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				// 경계 안이고, 아직 방문 안했고, 숫자가 같으면 점수도 같음
				if (isInside(nx, ny) && scoreMap[nx][ny]==0 && map[nx][ny]==b) {
					scoreMap[nx][ny] = cnt++;
					q.offer(nx);
					q.offer(ny);
					sameScore.offer(nx);
					sameScore.offer(ny);
				}
			}
		}
		return cnt;     // 같은 점수인 좌표 개수 반환
	}
	
	// 같은 점수인 좌표들 점수 설정
	private static void setScoreMap(int score) {
		while(!sameScore.isEmpty()) {
			int x = sameScore.poll();
			int y = sameScore.poll();
			scoreMap[x][y] = score;
		}
	}
	
	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}

}
