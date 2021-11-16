import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 21610번 마법사 상어와 비바라기
 * 
 * 시뮬레이션,
 * 
 * 주어진 순서에 따라서 구현하는 문제
 * Queue를 사용하여 구름의 위치를 저장하고
 * Boolean 이중 배열을 사용하여 구름이 비를 내린 위치를 저장했다.
 */

public class BOJ_21610 {

	static final int dr[] = {0,-1,-1,-1,0,1,1,1};
	static final int dc[] = {-1,-1,0,1,1,1,0,-1};
	static final int diagR[] = {-1,-1,1,1};
	static final int diagC[] = {-1,1,1,-1};
	
	static int N, map[][];
	static boolean isRemoved[][];
	static Queue<int[]> cloud;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloud = new ArrayDeque<int[]>();	// 구름 위치를 저장할 Queue
		init();	// 초기 구름 위치 지정
		for (int i = 0; i < M; i++) {
			isRemoved = new boolean[N][N];	// 매 순환마다 구름이 사라지는 위치를 저장할 배열
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			solve(d, s);
		}
		System.out.println(countWater());
	}
	
	static void init() {
		cloud.offer(new int[] {N-1, 0});
		cloud.offer(new int[] {N-1, 1});
		cloud.offer(new int[] {N-2, 0});
		cloud.offer(new int[] {N-2, 1});
	}
	
	static void solve(int d, int s) {
		/*
		 * 1. 모든 구름이 di 방향으로 si칸 이동한다.
		 * 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
		 * 3. 구름이 모두 사라진다.
		 * 4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
		 * 		이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
		 * 		예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
		 * 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
		*/
		moveCloudAndRain(d,s);	// 1, 2, 3단계
		copyWater();		// 4단계
		makeCloud();		// 5단계
	}

	private static void moveCloudAndRain(int d, int s) {
		int moveRow = dr[d] * s % N;
		moveRow = moveRow < 0 ? N + moveRow: moveRow;	// 음수의 modular 처리
		int moveCol = dc[d] * s % N;
		moveCol = moveCol < 0 ? N + moveCol: moveCol;
		while(!cloud.isEmpty()) {
			int[] coord = cloud.poll();					// Cloud 빼서 이동시킨 다음에 비 내리기
			int row = (coord[0] + moveRow) % N;
			int col = (coord[1] + moveCol) % N;
			map[row][col]++;
			isRemoved[row][col] = true;					// 구름 사라진 위치 저장
		}
	}

	private static void copyWater() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(isRemoved[i][j]) {
					int cnt = searchDiagonal(i, j);		// 대각선에 물이 있는지 탐색
					map[i][j] += cnt;					// 물이 있는 칸만큼 복사
				}
			}
		}
	}
	
	private static void makeCloud() {					// 구름 만들기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(isRemoved[i][j]) continue;		// 구름 사라졌던 위치는 X
				if(map[i][j] >= 2) {				// 물이 2 이상일 경우만 구름으로
					cloud.offer(new int[] {i,j});
					map[i][j] -= 2;
				}
			}
		}
	}
	
	static int countWater() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cnt += map[i][j];
			}
		}
		return cnt;
	}
	
	static int searchDiagonal(int r, int c) {		// 대각선 조사 메소드
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + diagR[i];
			int nc = c + diagC[i];
			if(isOutOfMap(nr, nc)) continue;
			if(map[nr][nc] > 0) cnt ++;
		}
		return cnt;
	}
	
	static boolean isOutOfMap(int r, int c) {
		return r < 0 || c < 0 || r >= N || c >= N;
	}

}
