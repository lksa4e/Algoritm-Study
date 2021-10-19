import java.util.*;
import java.io.*;

/**
 * 백준 20057번 마법사 상어와 토네이도 
 * 
 * 풀이 : 구현 
 * 
 * 중간부터 회전하면서 (0,-1)이 될 때까지 토네이도 이동
 * 토네이도 시작을 기준으로 각 방향에 따라 어디에 얼마나 모래가 흩어지는지 미리 좌표 계산!
 * 만약 경계를 벗어나면 result에, 아니면 해당 좌표에 모래 더해주기 
 * 
 * 35696KB	556ms
 */
public class Main20057_마법사상어와토네이도 {

	static int N, result;
	static int[][] map;
	static int[] dr = {0,1,0,-1};	// 좌하우상 
	static int[] dc = {-1,0,1,0};
	
	static int[][][] direction = {
			{
				{-1,1}, {1,1},	// 1% 
				{-2,0}, {2,0},	// 2%
				{-1,0}, {1,0},	// 7%
				{-1,-1}, {1,-1},	// 10%
				{0,-2},	// 5%
				{0,-1}	// 55%
			},	// 왼쪽으로 이동할 때 모래가 흩어지는 방향 
			{
				{-1,-1}, {-1,1},	// 1% 
				{0,-2}, {0,2},	// 2%
				{0,-1}, {0,1},	// 7%
				{1,-1}, {1,1},	// 10%
				{2,0},	// 5%
				{1,0}	// 55%
			},	// 아래쪽으로 이동할 때 모래가 흩어지는 방향 
			{
				{-1,-1}, {1,-1},	// 1% 
				{-2,0}, {2,0},	// 2%
				{-1,0}, {1,0},	// 7%
				{-1,1}, {1,1},	// 10%
				{0,2},	// 5%
				{0,1}	// 55%
			},	// 오른쪽으로 이동할 때 모래가 흩어지는 방향 
			{
				{1,-1}, {1,1},	// 1% 
				{0,-2}, {0,2},	// 2%
				{0,-1}, {0,1},	// 7%
				{-1,-1}, {-1,1},	// 10%
				{-2,0},	// 5%
				{-1,0}	// 55%
			}	// 위쪽으로 이동할 때 모래가 흩어지는 방향 
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int r = (N-1)/2, c = (N-1)/2;	// 초기 위치 중간값으로 설정 
		int length = 0;	// 이동할 길이
		
Loop:	while(true) {
			for(int dir = 0; dir < 4; dir++) {	// 사방으로 이동
				if(dir == 0 || dir == 2) length++;
				
				for (int i = 1; i <= length; i++) {
					r += dr[dir];
					c += dc[dir];
					if(r == 0 && c == -1) break Loop;
					moveTornado(r, c, dir);
				}
			}
		}
		
		System.out.println(result);
	}

	private static void moveTornado(int r, int c, int dir) {
		int sand = map[r][c];
		int spreadSand = 0;
		if(sand == 0) return;	// 이동시킬 모래가 없으면 이동 X 
		
		int[][] spread = direction[dir];
		
		int nr, nc;
		
		// 1% 이동 (0,1)
		for(int i=0;i<2;i++) {
			nr = r + spread[i][0];
			nc = c + spread[i][1];
			
			if(isOOB(nr, nc)) {
				result += sand * 0.01;
			} else {
				map[nr][nc] += sand * 0.01;
			}
			spreadSand += sand * 0.01;
		}
		
		// 2% 이동 (2,3)
		for(int i=2;i<4;i++) {
			nr = r + spread[i][0];
			nc = c + spread[i][1];
			
			if(isOOB(nr, nc)) {
				result += sand * 0.02;
			} else {
				map[nr][nc] += sand * 0.02;
			}
			spreadSand += sand * 0.02;
		}
		
		// 7% 이동 (4,5)
		for(int i=4;i<6;i++) {
			nr = r + spread[i][0];
			nc = c + spread[i][1];
			
			if(isOOB(nr, nc)) {
				result += sand * 0.07;
			} else {
				map[nr][nc] += sand * 0.07;
			}
			spreadSand += sand * 0.07;
		}
		
		// 10% 이동 (6,7)
		for(int i=6;i<8;i++) {
			nr = r + spread[i][0];
			nc = c + spread[i][1];
			
			if(isOOB(nr, nc)) {
				result += sand * 0.1;
			} else {
				map[nr][nc] += sand * 0.1;
			}
			spreadSand += sand * 0.1;
		}

		// 5% 이동 (8)
		nr = r + spread[8][0];
		nc = c + spread[8][1];
		
		if(isOOB(nr, nc)) {
			result += sand * 0.05;
		} else {
			map[nr][nc] += sand * 0.05;
		}
		spreadSand += sand * 0.05;
		
		// 55% 이동 (9)
		nr = r + spread[9][0];
		nc = c + spread[9][1];
		
		if(isOOB(nr, nc)) {
			result += (sand - spreadSand);
		} else {
			map[nr][nc] += (sand - spreadSand);
		}
	}
	
	private static boolean isOOB(int r, int c) {
		return (r<0||r>=N||c<0||c>=N);
	}

}
