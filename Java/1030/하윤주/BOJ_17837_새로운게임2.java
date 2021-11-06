import java.io.*;
import java.util.*;

/**
 * [1026] BOJ 17837 새로운 게임2
 * 시뮬레이션, 구현
 * 
 * sol)
 * 색상을 표현하는 지도와 말의 위치를 표기하는 지도, 2가지 지도를 저장한다.
 * 또한 한 좌표에 여러 말이 존재할 수 있으므로 말의 위치를 표기하는 지도는 List 형으로 관리한다.
 * 각 말의 경우 좌표와 방향정보를 기억해야하므로 Horse 클래스를 생성하여 관리한다.
 * (이미 2차원 지도에 각 말들의 위치를 저장해뒀으므로 더이상 배열의 차원을 늘리는 것보다 객체로 관리하는 것이 편할 것 같았다)
 *
 */

public class BOJ_17837_새로운게임2 {
	static final int WHITE = 0;       // 지도 색상
	static final int RED = 1;
	static final int BLUE = 2;
	
	static final int RIGHT = 0;       // 말의 움직임
	static final int LEFT = 1;
	static final int UP = 2;
	static final int DOWN = 3;
	
	static int N, K;
	static boolean flag = false;      // 말이 4개가 쌓였는지 여부
	static int[][] map;               // 색상을 표기한 지도
	static ArrayList[][] horseMap;    // 말의 위치를 표기한 지도
	static Horse[] horses;            // Horse 타입의 배열 : 전체 말들의 위치와 방향을 저장하고 있음
	static int[] dx = {0, 0, -1, 1};  // 4방 이동 델타
	static int[] dy = {1, -1, 0, 0};
	
	// 각 말의 좌표 정보와 방향 정보를 표현하는 클래스
	private static class Horse {
		int x;
		int y;
		int dir;
		
		public Horse(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 색상 정보를 저장한 지도, 이때 테두리 패딩은 파란색으로 채움
		map = new int[N+2][N+2];
		for (int i=0; i<N+2; i++) Arrays.fill(map[i], BLUE);
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 말의 위치를 저장한 지도, 한 좌표에 여러 말을 표현하기 위해 ArrayList 타입으로 생성
		horses = new Horse[K];
		horseMap = new ArrayList[N+2][N+2];
		for (int i=0; i<N+2; i++) {
			for (int j=0; j<N+2; j++) horseMap[i][j] = new ArrayList<Integer>();
		}
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			horseMap[x][y].add(i);
			// 각 말의 위치, 방향을 horse라는 1차원 배열에 따로 또 저장
			horses[i] = new Horse(x, y, Integer.parseInt(st.nextToken())-1);
		}
		
		System.out.println(playGame());

	}

	// 게임시작
	private static int playGame() {
		for (int turn=1; turn<=1000; turn++) {         // 1000번의 트라이 넘어가면 실패
			for (int i=0; i<K; i++) {
				int dir = horses[i].dir;
				int nx = horses[i].x + dx[dir];
				int ny = horses[i].y + dy[dir];
				
				switchMove(i, nx, ny, dir);            // 지도 색상에 따라 이동
				
				if (flag) return turn;                 // 만약 이동 이후 4개의 말이 쌓이게 된다면 실패
			}
		}
		return -1;
	}
	
	// 지도 색상에 따른 이동
	private static void switchMove(int horse, int nx, int ny, int dir) {
		switch (map[nx][ny]) {
		case WHITE: moveWhite(horse, nx, ny, dir); break;
		case RED: moveRed(horse, nx, ny, dir); break;
		case BLUE: moveBlue(horse, dir); break;
		}
	}

	// 흰색칸으로 이동하기
	private static void moveWhite(int horse, int nx, int ny, int dir) {
		int cx = horses[horse].x;
		int cy = horses[horse].y;
		int size = horseMap[cx][cy].size();
		int idx = horseMap[cx][cy].indexOf(horse);
		
		// 4개의 말이 쌓였는지 확인
		if((size-idx)+horseMap[nx][ny].size()>=4) {     // 현재 말부터 제일 꼭대기까지 말의 개수를 세고, 이를 이동하려는 칸에 이미 존재하는 말의 개수에 더함
			flag = true;
			return;
		}
		
		for (int i=idx; i<size; i++) {                  // 현재 말부터 제일 꼭대기 말까지 순서대로 다음 좌표에 쌓음
			int h = (int) horseMap[cx][cy].get(idx);
			horses[h].x += dx[dir];
			horses[h].y += dy[dir];
			
			horseMap[nx][ny].add(h);
			horseMap[cx][cy].remove(idx);
		}
	}

	// 빨강칸으로 이동하기
	private static void moveRed(int horse, int nx, int ny, int dir) {
		int cx = horses[horse].x;
		int cy = horses[horse].y;
		int size = horseMap[cx][cy].size();
		int idx = horseMap[cx][cy].indexOf(horse);
		
		if((size-idx)+horseMap[nx][ny].size()>=4) {     // 현재 말부터 제일 꼭대기까지 말의 개수를 세고, 이를 이동하려는 칸에 이미 존재하는 말의 개수에 더함
			flag = true;
			return;
		}
		
		for (int i=size-1; i>=idx; i--) {               // 제일 꼭대기 말부터 현재 말까지 역순으로 다음 좌표에 쌓음
			int h = (int) horseMap[cx][cy].get(i);
			horses[h].x += dx[dir];
			horses[h].y += dy[dir];
			
			horseMap[nx][ny].add(h);
			horseMap[cx][cy].remove(i);
		}
	}

	// 파랑칸으로 이동하기
	private static void moveBlue(int horse, int dir) {
		switch (dir) {
		case RIGHT: case UP: ++dir; break;        // 우 <-> 좌, 상 <-> 하 방향 바꿔줌
		case LEFT: case DOWN: --dir; break;
		}
		
		horses[horse].dir = dir;
		int nx = horses[horse].x + dx[dir];
		int ny = horses[horse].y + dy[dir];
		
		if (map[nx][ny]!=BLUE) switchMove(horse, nx, ny, dir);    // 반대 방향으로 이동할 칸이 흰색, 빨강색이면 해당 색상 기준에 맞게 이동, 파랑이면 유지
	}
	
}
