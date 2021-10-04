import java.io.*;
import java.util.*;

/**
 * G2 BOJ 14499 주사위 굴리기
 * 메모리 : 16512kb 시간 : 180ms
 * 
 * 주사위를 어떤 방식으로 관리할지가 핵심이었던 문제
 * 문제에서 주어진 아래 그림을 기반으로 2개의 1차원 배열을 생성하고 주사위 눈금을 관리함
 *    2
 *	4 1 3
 *    5
 *    6
 * 
 * 4,1,3,6 을 dice_row[] 배열에 담고
 * 2,1,5,6 을 dice_col[] 배열에 담음
 * 
 * 실제 주사위의 면은 6개이고, 배열을 통해서 관리하는 면은 8개이다.
 * 이 때, 주사위를 굴리는 경우 윗면과 아랫면의 경우 상하방향, 좌우방향 회전 시에 공유하는 공통된 면이다.
 * row[1] = col[1] = 주사위 윗면
 * row[3] = col[3] = 주사위 바닥면
 * 따라서 방향 전환시 배열 돌리기 작업 이후에도 row[1] == col[1] , row[3] == col[3] 이 되도록 유지시켜 줘야 한다.
 */
public class BOJ14449_주사위굴리기{
	static int N,M,X,Y,K, map[][];
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	static int dice_row[] = new int[4];
	static int dice_col[] = new int[4];
	static StringBuilder sb = new StringBuilder(); 
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int k = 0; k < K; k++) {
			int dir = Integer.parseInt(st.nextToken());
			move(X,Y,dir);
		}
		System.out.println(sb);
	}
	// 커맨드마다 주사위를 이동시키는 함수
	static void move(int x, int y, int dir) {
		int nx = x + dx[dir - 1];
		int ny = y + dy[dir - 1];
		
		// 만약 주사위 이동 시 영역 밖으로 이동하게 되면 추가작업 수행 X
		if(oob(nx,ny)) return;
		
		// 주사위를 회전
		roll(dir);
		
		// 주사위 면과 좌표를 비교하여 채워주는 작업 수행
		mark(nx,ny);
		
		// 현재 위치 변경
		X = nx;
		Y = ny;
	}
	
	static void roll(int dir) {
		int temp = 0;
		switch(dir) {
		// 각 방향으로 주사위를 굴리는 경우에는 row, col 배열을 각각 방향에 맞게 배열 돌리기 수행
		// 배열 돌리기 수행 후 row, col 배열의 주사위 공통면 일치 작업을 수행함
		case 1:
			// 배열 돌리기
			temp = dice_row[3];
			for(int i = 3; i > 0; i--)
				dice_row[i] = dice_row[i-1];
			dice_row[0] = temp;
			
			// row, col의 공통면 일치 작업
			dice_col[1] = dice_row[1];
			dice_col[3] = dice_row[3];
			break;
		case 2:
			temp = dice_row[0];
			for(int i = 0; i < 3; i++)
				dice_row[i] = dice_row[i+1];
			dice_row[3] = temp;
			dice_col[1] = dice_row[1];
			dice_col[3] = dice_row[3];
			break;
		case 3:
			temp = dice_col[0];
			for(int i = 0; i <3; i++)
				dice_col[i] = dice_col[i+1];
			dice_col[3] = temp;
			dice_row[1] = dice_col[1];
			dice_row[3] = dice_col[3];
			break;
		case 4:
			temp = dice_col[3];
			for(int i = 3; i > 0; i--)
				dice_col[i] = dice_col[i-1];
			dice_col[0] = temp;
			dice_row[1] = dice_col[1];
			dice_row[3] = dice_col[3];
			break;
		}
	}
	
	// 주사위 or 좌표 색칠하는 함수
	static void mark(int x, int y) {
		// 좌표값이 0이면 주사위 바닥면을 좌표에 채움
		if(map[x][y] == 0) {
			map[x][y] = dice_row[3];
		}
		// 좌표값이 0이 아니면 좌표값을 주사위 바닥면에 채움
		else {
			dice_col[3] = dice_row[3] = map[x][y];
			map[x][y] = 0;
		}
		sb.append(dice_col[1] + "\n");
	}
	static boolean oob(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
