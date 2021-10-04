import java.io.*;
import java.util.*;

/**
 * [1005] BOJ 14503 로봇청소기
 * 2차원 배열, 시뮬레이션, 구현
 * 
 * sol)
 *  문제에서 주어진 로직 그자체로 구현하되, 방향 회전 관리에 신경써야함. 배열 돌리기처럼 2차원 배열 이동 연습에 좋은 문제인 것 같다.
 * 	
 */

public class BOJ_14503_로봇청소기 {
	static int N, M, cnt;					// 최종 정답인 이동 횟수
	static boolean stopFlag = false;		// 더 이상 탐색할 방 없을 때 종료하기 위한 플래그
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};		// 북, 동, 남, 서
	static int[] dc = {0, 1, 0, -1};
	static int[] leftDir = {3, 0, 1, 2};	// 북->서, 동->북, 남->동, 서->남
	static int[] backDir = {2, 3, 0, 1};	// 북->남, 동->서, 남->북, 서->동
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());		// 0: 북, 1: 동, 2: 남, 3: 서
		
		// 초기 지도 입력
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());		// 0: 빈칸, 1: 벽, 2: 청소완료
		}
		
		// 출발지부터 시뮬레이션 시작
		cleanRooms(r, c, d);
		
		System.out.println(cnt);
	}

	// 1번
	private static void cleanRooms(int r, int c, int d) {
		// 1. 현재 위치를 청소한다
		map[r][c] = 2;
		cnt++;
		
		// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 인접한 칸을 탐색한다.
		detectDirtyRooms(r, c, d, 0);
		
		if (stopFlag) return;		// 만약 인근에 더 이상 청소할 수 있는 방이 없으면 종료
	}

	// 2번
	private static void detectDirtyRooms(int r, int c, int d, int turnCnt) {
		int lr = r + dr[leftDir[d]];        // 왼쪽 방
		int lc = c + dc[leftDir[d]];
		int leftState = map[lr][lc];        // 왼쪽 방 상태
		
		// 2-a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
		if (leftState == 0) {
			cleanRooms(lr, lc, leftDir[d]);
			return;
		}
		
		//2-b. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
		if (turnCnt<4) detectDirtyRooms(r, c, leftDir[d], turnCnt+1);
		
		//2-c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는
		else {
			int br = r + dr[backDir[d]];    // 뒤쪽 방
			int bc = c + dc[backDir[d]];
			int backState = map[br][bc];    // 뒤쪽 방 상태
			
			// 2-c. (뒤쪽 방향이 벽이 아니면) 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
			if (backState != 1) detectDirtyRooms(br, bc, d, 0);
			
			// 2-d. 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
			else {
				stopFlag = true;            // 더 이상 탐색할 방 없음을 재귀로 돌아가는 1번 함수에게 알림
				return;
			}
		}
	}

}