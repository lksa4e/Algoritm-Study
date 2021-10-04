import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 3190번 뱀
 * 
 * 맵에 뱀을 그려가면서 진행,
 * 뱀의 머리와 꼬리 좌표를 저장 후, 한 칸 이동하면서 머리를 위치시키고 사과 체크하고 사과에 따라 꼬리 없에거나 놔둔다.
 * 이 때, 맵에서 뱀이 남기는 표식이 2부터 시작해서 하나씩 커진다.
 * 이동할 때 뱀의 꼬리를 지우는 방법 --> 현재 꼬리의 좌표에 남아있는 표식 i, 사방 탐색으로 i+1의 좌표를 찾고 꼬리 좌표 갱신
 * 
 * 14,180KB	128ms
 */

public class BOJ_3190 {
	static int N, map[][], SNAKE = 2, time, dir;
	static int[] snakeCoord = {1,1,1,1};	// head row, head col, tail row, tail col
	static final int APPLE = 1;
	static final int[] dr = { 0, 1, 0, -1 };	// 우하좌상
	static final int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		int numOfApple = Integer.parseInt(br.readLine());

		map = new int[N+1][N+1];

		for (int i = 0; i < numOfApple; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = APPLE;
		}

		map[1][1] = SNAKE;	// 뱀 출발점(뱀의 몸은 2부터 계속 1씩 증가하면서 맵에 저장)

		int commandCnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < commandCnt + 1; i++) {
			if(i == commandCnt) {	// command가 모두 완료되고, 마지막 command대로 끝날 때 까지 뱀 움직이기
				moveSnake(10001, 'L');
				break;
			}
			st = new StringTokenizer(br.readLine());
			int commandStartTime = Integer.parseInt(st.nextToken());
			char command = st.nextToken().charAt(0);

			if(!moveSnake(commandStartTime, command)) break;	// 중간에 벽이나 자기 몸 만나면 종료
		}
		System.out.println(time);
	}
		
	static boolean moveSnake(int commandStartTime, char command) {
		while(time++ < commandStartTime) {
			int nextR = snakeCoord[0] + dr[dir];
			int nextC = snakeCoord[1] + dc[dir];
			if (isOutOfMap(nextR, nextC) || map[nextR][nextC] > APPLE)
				return false;	// 1보다 큰 수는 모두 뱀
			
			boolean isApple = map[nextR][nextC] == APPLE;	// 현재 뱀이 간 위치에 사과가 있는지 확인
			map[nextR][nextC] = ++SNAKE;	// 뱀의 흔적을 계속 1씩 늘려서 맵에 기록하기
			if(!isApple) {
				// 꼬리 갱신, 4 방향을 탐색해서 꼬리보다 1 큰 수가 다음 꼬리가 됨
				catchNextTail();
			}
			
			// 현재 머리 위치에 뱀 남긴 후에 머리 좌표 갱신
			snakeCoord[0] = nextR; snakeCoord[1] = nextC;
		}

		if (command == 'L') { // 왼쪽으로 90도 회전
			dir--;
			if (dir < 0) dir = 3;
		} else { // 오른쪽으로 90도 회전
			dir = (dir + 1) % 4;
		}
		time--;	// 정상적으로 회전까지 했다면 while문에서 time이 한 번 더 돌아갔으므로 하나 줄임
		return true;
	}
	
	static void catchNextTail() {
		int nextTail = map[snakeCoord[2]][snakeCoord[3]] + 1;	// 다음 꼬리는 현재 꼬리보다 1 큰 수
		map[snakeCoord[2]][snakeCoord[3]] = 0;	// 현재 꼬리는 잘라주기
		
		// 사방 탐색으로 다음 꼬리의 찾아서 꼬리 좌표 갱신
		for (int i = 0; i < 4; i++) {
			int nr = snakeCoord[2] + dr[i];
			int nc = snakeCoord[3] + dc[i];
			if(isOutOfMap(nr, nc)) continue;
			if(map[nr][nc] == nextTail) {
				snakeCoord[2] = nr;
				snakeCoord[3] = nc;
				break;
			}
		}
	}
	
	static boolean isOutOfMap(int row, int col) {
		return row < 1 || row >= N+1 || col < 1 || col >= N+1;
	}
}
