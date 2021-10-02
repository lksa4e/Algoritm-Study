import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 12100번 2048(Easy)
 * 
 * 2048게임 시뮬레이션 문제
 * 
 * 순열로 최대 다섯 번 움직이는 경우의 수를 모두 구해서
 * 움직이는 방향마다 시뮬레이션을 구해줬다.
 * 
 * 5번의 어떻게 움직일지 선택하고 나서 시뮬레이션을 돌리기 때문에
 * 부분 중복이 있어서 고치려고 했는데 실패했다.
 * 
 * 15420KB	352ms
 */

public class BOJ_12100 {
	static int N, map[][], cpMap[][], order[], maxBlock;
	static boolean[][] stacked;
	static final int[] dr = {-1,0,1,0}; // 상 좌 하 우
	static final int[] dc = {0,-1,0,1};
	static final int MOVE = 5;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];	// 원본맵
		cpMap = new int[N][N];
		stacked = new boolean[N][N];	// 한 번의 움직임에서 중복으로 블록이 합쳐지지 않도록 관리
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		order = new int[MOVE];	// 다섯 번의 움직임을 저장한는 배열
		orderPerm(0);
		System.out.println(maxBlock);
	}
	
	static void orderPerm(int cnt) {
		if(cnt == MOVE) {
			initCpMap();	// cpMap을 맨 처음 상태로
			moveMapWithCommands();	// 현재 결정된 순서로 cpMap 이동시키기
			updateMaximum();	// cpmap에서 가장 큰 수 고르기
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			order[cnt] = i;
			orderPerm(cnt+1);
		}
	}
	
	// 결정된 순서대로 맵 이동
	static void moveMapWithCommands() {
		for (int i = 0; i < MOVE; i++) {
			initStacked();		// 매 번 움직일 때마다 합쳐질 수 있기 때문에 초기화
			if(order[i] < 2)
				upLeft(order[i]);	// 좌, 상방향으로 움직일 때
			else
				downRight(order[i]);	// 우, 하방향으로 움직일 때
		}
	}
	
	// dir이 0,1 일 때 --> 왼쪽 위 부터 탐색
	static void upLeft(int dir) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				moveBlock(r, c, dir);
			}
		}
	}
	
	// dir이 2,3 일 때 --> 오른쪽 아래부터 탐색
	static void downRight(int dir) {
		for (int r = N - 1; r >= 0; r--) {
			for (int c = N - 1; c >= 0; c--) {
				moveBlock(r, c, dir);
			}
		}
	}
	
	// row, col에 있는 블록을 dir 방향으로 쭉 이동시키기
	static void moveBlock(int row, int col, int dir) {
		int nr = row;
		int nc = col;
		// 현재 블록의 stacked 상태, 숫자
		boolean currFlag = stacked[row][col], isStackable = false;	// 끝까지 움직였을 때 그곳에 같은 숫자가 있는지 체크하는 변수
		int currNum = cpMap[row][col];
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			
			if(isOutOfMap(nr, nc)) break;	// 밖으로 나가면 멈춤
			if(cpMap[nr][nc] == 0) continue;	// 빈칸이면 계속 이동시킴
			
			// 빈칸이 아닌 숫자가 들어옴
			if(cpMap[nr][nc] != currNum) break;	// 다른 숫자면 합칠 수 없음
			if(!stacked[nr][nc] && !stacked[row][col]) // 숫자가 같으면 둘 다 합칠 수 있는 상태인지 확인
				isStackable = true;
			break;	// 어쨌거나 0이 아닌 수였으므로 루프 탈출
		}
		if(!isStackable) {	// 합치는 상황이 아니면 한 칸 전으로 돌아가야 함
			nr -= dr[dir];
			nc -= dc[dir];
		}
		cpMap[row][col] = 0;
		stacked[row][col] = false;
		
		cpMap[nr][nc] = isStackable ? currNum*2 : currNum;	// 합치는 상황이면 원래 수 *2, 아니면 그냥 원래 수
		stacked[nr][nc] = isStackable ? true : currFlag;
	}
	
	static void updateMaximum() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) 
				if(cpMap[i][j] > maxBlock) 
					maxBlock = cpMap[i][j];
	}
	
	static boolean isOutOfMap(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}
	
	static void initStacked() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(stacked[i], false);
		}
	}
	
	static void initCpMap() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				cpMap[i][j] = map[i][j];
	}
}