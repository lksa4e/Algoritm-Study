import java.io.*;
import java.util.*;

/**
 * [1005] BOJ 14499 주사위 굴리기
 *	구현, 주사위, 배열
 *
 * sol)
 *	주사위를 동, 서, 남, 북 회전하면 각 면이 바라보는 방향이 바뀐다. 
 *	예를 들면 동으로 회전할 경우
 *		뚜껑 -> 우 -> 바닥 -> 좌
 *	각 면이 바라보는 방향은 위와 같이 화살표 방향으로 한 칸씩 이동한다. 
 *	동, 서, 남, 북 방향으로 회전할 때도 방향은 다르지만 위와 같은 방식으로 4개의 면이 바뀐다.
 *	각 방향 별 바뀌는 면의 패턴을 파악하여 회전 명령이 주어질 때마다 4개의 면을 바꿔주며(swap) 게임을 진행한다.
 *
 *	또한 뚜껑-바닥, 상-하, 우-좌 에 위치한 면은 서로 쌍을 이루어 두 면의 인덱스의 합은 7이 된다. 이 원리를 이용한다.
 *
 */

public class BOJ_14499_주사위굴리기 {
	static int N, M, x, y;
	static int bottom=6, right=3, up=2;		// 초기 바닥, 오른쪽, 위쪽 면의 인덱스
	static int[][] map;
	static int[] dice = new int[7];			// 주사위에 적힐 수를 저장하는 배열, 1~6번 인덱스만 사용
	static int[] dx = {0, 0, 0, -1, 1};		// 1: 동, 2: 서, 3: 북, 4: 남
	static int[] dy = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 지도 입력
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 각 회전 명령에 따라 게임 진행
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) play(Integer.parseInt(st.nextToken()));
	}
	
	// 회전 명령에 따라 주사위 굴리기
	private static void play(int d) {
		// 지도 이동
		int nx = x + dx[d];
		int ny = y + dy[d];
		
		if (!isInside(nx, ny)) return;		// 경계 체크
		
		x = nx;		// 경계 안이면 좌표 조정
		y = ny;
		
		// 주사위 이동
		moveDice(d);
		
		int nextMapNum = map[x][y];
		if (nextMapNum == 0) map[x][y] = dice[bottom];    // 지도의 수가 0이면 주사위 수를 복사하고
		else {
			dice[bottom] = nextMapNum;                    // 지도의 수가 0이 아니면 지도의 수를 복사  
			map[x][y] = 0;
		}
		
		// 주사위 뚜껑(7-바닥)에 적힌 수 출력
		System.out.println(dice[7-bottom]);
	}
	
	// 주사위 회전
	private static void moveDice(int d) {
		switch (d) {
		case 1: swapDice(bottom, right); break;     // 동
		case 2: swapDice(right, bottom); break;     // 서
		case 3: swapDice(bottom, up); break;        // 북
		case 4: swapDice(up, bottom); break; }      // 남
	}
	
	// 각 방향으로 주사위 굴리면 바뀌는 면에따라 주사위에 적힌 수도 swap
	private static void swapDice(int d1, int d2) {
		int d3 = 7-d1;            // 쌍을 이루는 면의 인덱스 계산
		int d4 = 7-d2;
		
		// 바뀌게 되는 4개의 면 swap
		int temp = dice[d1];
		dice[d1] = dice[d2];
		dice[d2] = dice[d3];
		dice[d3] = dice[d4];
		dice[d4] = temp;
	}

	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}

}

