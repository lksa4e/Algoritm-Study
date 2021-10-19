import java.io.*;
import java.util.*;

/**
 * 
 * [1009] BOJ 19236 청소년 상어
 * 	시뮬레이션, dfs, 재귀, 백트래킹, 8방탐색
 * 
 * 	sol)
 * 1. 상어가 (1,1)에 진입하고 물고기 번호는 먹고 방향은 유지한다.
 * 2. 물고기 이동과 상어 이동을 반복한다. 여기서부터 재귀함수로 구현하여 반복한다.
 * 		1) 물고기 번호가 작은 것부터 한 칸씩 이동한다.
 * 			- 현재 방향에서 방향 델타를 1씩 더하며 8방탐색으로 갈 수 있는 좌표 찾음
 * 			- 찾았으면 자신과 swap
 * 		2) 상어가 방향대로 한 칸 이동한다.
 * 			- 자신의 방향에서 갈 수 있는 모든 좌표를 인접한 좌표라고 생각하고 dfs 탐색한다.
 *			- 이때 각 인접한 좌표 방문 전에 지도를 복사
 *			- 인접한 좌표가 경계 벗어나면 최댓값 갱신
 *			- 인접한 좌표가 빈칸이면 다음 인접한 빈칸으로 pass
 *			- 상어가 있던 원래 좌표는 빈칸 처리하고 이동할 인접 좌표는 상어(-1)로 표기한다.
 *			- 이동할 좌표에서 다음 인접한 좌표를 탐색해야하므로 2. 로 돌아가 재귀를 타고 다음 반복을 진행한다. 
 * 
 *  시행착오)
 * - 지도 복사를 하지 않은 채 매개변수로 달고 다니면 괜찮을 줄 알았는데 인접한 좌표를 한번에 타고 들어가는게 아니라
 * 	 각각을 타고 들어가게되므로 모든 경우에서 지도를 복사해서 다음 재귀로 넘기고 돌아와서는 원본을 유지할 수 있도록 해야함.
 * - 최초 물고기인 (1,1) 더하는 것 빼먹어서 3시간동안 뻘짓...했습니다...후...
 */

public class BOJ_19236_청소년상어 {
	static int firstFish, max;
	static int[] initFish;                            // 1~16번 물고기의 좌표
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};    // 상, 좌상, 좌, 좌하, 하, 우하, 우, 우상
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 지도 입력
		initFish = new int[17];
		int[][][] map = new int[4][4][2];
		for (int i=0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<4; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				map[i][j][1] = Integer.parseInt(st.nextToken())-1;
			}
		}
		
		// 물고기와 상어 이동 시작
		firstFish = map[0][0][0];             // 최초의 물고기 번호
		map[0][0][0] = -1;                    // 최초의 물고기 자리에 상어 진입
		sharkTour(map, 0, 0, 0);              // dfs 탐색
		System.out.println(max+firstFish);
		
	}
	
	// 상어 이동 dfs
	private static void sharkTour(int[][][] map, int x, int y, int sum) {
		// 물고기 이동
		map = fishTour(map);
		
		int nx = x;
		int ny = y;
		int dir = map[x][y][1];
		
		// 상어 이동
		while(true) {                                     // 상어 방향에서 경계 닿을때까지 탐색
			int[][][] copiedMap = copyMap(map);	          // 지도 복사 필수!!!
			nx += dx[dir];
			ny += dy[dir];
			
			if (!isPossibleMove(copiedMap, nx, ny)) {     // 경계 벗어나면 최댓값 갱신하고 반복 탈출
				max = Math.max(max, sum);
				return;
			}
			int alive = copiedMap[nx][ny][0];
			if (alive==0) continue;                       // 빈칸이면 다음 인접 탐색으로 넘어감
			
			copiedMap[x][y][0] = 0;                       // 상어가 원래 있던 자리는 빈칸 처리
			copiedMap[nx][ny][0] = -1;                    // 상어가 이동한 자리는 상어 처리
			sharkTour(copiedMap, nx, ny, sum+alive);      // 재귀타고 다음 인접 물고기 탐색
		}
	}
	
	// 물고기 이동을 위한 메서드
	private static int[][][] fishTour(int[][][] map) {
		findFishLocation(map);                 // 1~16번 물고기 좌표를 먼저 파악
		
		for (int i=1; i<=16; i++) {
			int xy = initFish[i];
			if (xy==-1) continue;              // 빈칸이면 pass
			
			int x = xy/4;
			int y = xy%4;
			int dir = map[x][y][1];
			
			// 8방 탐색으로 가능한 좌표 찾기
			for (int d=0; d<8; d++) {
				int dIdx = (dir+d)%8;
				int nx = x + dx[dIdx];
				int ny = y + dy[dIdx];
				
				if (isPossibleMove(map, nx, ny)) {         // 경계 내부이고 상어가 있는 곳이 아니면
					initFish[map[nx][ny][0]] = x*4+y;      // 이동할 좌표의 물고기의 위치를 재조정해주고
					map[x][y][1] = dIdx;                   // 이동하는 자신의 방향 재조정
					
					int[] temp = map[x][y];                // 자신과 이동할 좌표 물고기 swap
					map[x][y] = map[nx][ny];
					map[nx][ny] = temp;
					break;
				}
			}
		}
		return map;
	}
	
	// 물고기 이동 전에 각 물고기의 좌표를 파악하는 메서드
	private static void findFishLocation(int[][][] map) {
		Arrays.fill(initFish, -1);
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				int fishIdx = map[i][j][0];
				if (fishIdx<=0) continue;          // 빈칸이면 pass
				initFish[fishIdx] = i*4+j;
			}
		}
	}
	
	// 지도 복사
	private static int[][][] copyMap(int[][][] map) {
		int[][][] copiedMap = new int[4][4][2];
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<2; k++) copiedMap[i][j][k] = map[i][j][k];
			}
		}
		return copiedMap;
	}

	// 경계 체크 및 상어 체크
	private static boolean isPossibleMove(int[][][] map, int x, int y) {
		return (x>=0 && x<4 && y>=0 && y<4 && map[x][y][0]!=-1);
	}

}
