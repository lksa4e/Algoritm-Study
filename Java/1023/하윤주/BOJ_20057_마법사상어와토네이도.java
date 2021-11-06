import java.io.*;
import java.util.*;

/**
 * [1019] BOJ 20057 마법사 상어와 토네이도
 * 구현, 또 문제 해석이 젤 어려운 문제..
 * 
 * sol)
 * 큰 흐름 : (N/2, N/2)부터 배열 돌리기를 하며 토네이도 한 칸을 이동한다.
 *          이때 토네이도가 방향을 돌리지 않고 전진하는 칸 수는 1~(N-1)개이고, 이 칸수는 (좌, 하), (우, 상) 각각 2번씩 유지된다.
 * 내부 흐름 : 토네이도가 전진하는 방향에 따라 모래가 흩어지는 비율이 정해진 칸으로 모래를 흘뿌리고, 마지막으로 알파칸에 남은 모래를 모두 흩뿌린다.
 *           이때 비율이 정해진 칸이나 알파 칸이 경계 내부가 아니면 흩뿌려질 모래 양 만큼을 최종 정답에 더한다.
 *
 */

public class BOJ_20057_마법사상어와토네이도 {

	static int N, x, y, out;
	static int[][] map;
	static final int ALPHA = 9;
	static final int[] PERCENTS = {2, 10, 7, 1, 5, 10, 7, 1, 2};
	static final int[][] LEFT = {{-2, -1}, {-1, -2}, {-1, -1}, {-1, 0}, {0, -3}, {1, -2}, {1, -1}, {1, 0}, {2, -1}, {0, -2}};
	static final int[][] DOWN = {{1, -2}, {2, -1}, {1, -1}, {0, -1}, {3, 0}, {2, 1}, {1, 1}, {0, 1}, {1, 2}, {2, 0}};
	static final int[][] RIGHT = {{-2, 1}, {-1, 2}, {-1, 1}, {-1, 0}, {0, 3}, {1, 2}, {1, 1}, {1, 0}, {2, 1}, {0, 2}};
	static final int[][] UP = {{-1, -2}, {-2, -1}, {-1, -1}, {0, -1}, {-3, 0}, {-2, 1}, {-1, 1}, {0, 1}, {-1, 2}, {-2, 0}};
	static final int[][][] MOVE = {LEFT, DOWN, RIGHT, UP};
	static final int[][] D = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 중심부터 토네이도 시작
		x = y = N/2;
		tornado();
		
		System.out.println(out);
	}
	
	// 토네이도 회전
	public static void tornado() {
		int d = 0;
		for (int i=1; i<N; i++) {             // N-1개의 칸만큼 전진하는데
			for (int j=1; j<=2; j++) {        // 그것을 좌하, 우상 2번씩 반복
				for (int k=1; k<=i; k++) {
					spreadSend(d%4, x, y);    // 모래 흩뿌림
					x += D[d%4][0];
					y += D[d%4][1];
				}
				d++;
			}
		}
		
		for (int i=1; i<N; i++) {             // 0번째 행인 마지막 토네이도 이동
			spreadSend(d%4, x, y);
			x += D[d%4][0];
			y += D[d%4][1];
		}
	}

	// 모래 흩뿌리기
	private static void spreadSend(int d, int x, int y) {
		int send = map[x+D[d%4][0]][y+D[d%4][1]];
		map[x+D[d%4][0]][y+D[d%4][1]] = 0;                     // 모든 모래 뿌림
		int leftSend = send;
		int nx, ny;
		
		for (int i=0; i<9; i++) {                              // 비율이 정해진 9개의 좌표에 대해
			nx = x + MOVE[d][i][0];
			ny = y + MOVE[d][i][1];
			
			int portion = (int) (send * PERCENTS[i] * 0.01);
			leftSend -= portion;                                // 비율만큼 뿌림
			
			if (!isInside(nx, ny)) out += portion;              // 뿌릴 곳이 경계 밖이면 정답에 추가하고
			else map[nx][ny] += portion;                        // 뿌릴 곳이 경계 안이면 뿌림
			
		}
		nx = x + MOVE[d][ALPHA][0];                             // 알파 위치에도 뿌림
		ny = y + MOVE[d][ALPHA][1];
		
		if (!isInside(nx, ny)) out += leftSend;
		else map[nx][ny] += leftSend;
		
	}
	
	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}

}
