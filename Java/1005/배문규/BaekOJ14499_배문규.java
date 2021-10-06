package BaekOJ.study.date1005;

import java.io.*;
import java.util.*;

/*
 * 문제를 이해하는게 제일 힘들었음
 * 일차원 배열로 주사위를 컨트롤 해보려다가 쓸데없는 곤조는 버리고 그냥 내가 가장 이해하기 쉽게 
 * 문제 나오는 모양처럼 4*3 배열로 주사위를 만들어서 커맨드마다 배열을 시프트 시키면서 주사위를 컨트롤해줌
 * 
 * 메모리 	시간
 * 16480	172
 */
public class BaekOJ14499_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4;
	static int N, M, x, y, K, map[][], dice[][];
	static int delta[][] = {{}, {0,1}, {0,-1}, {-1,0}, {1,0}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dice = new int[4][3];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			// 맵 밖으로 넘어가지 않으면 주사위 굴리고 윗면 출력
			if(!isOOB(x+delta[cmd][0], y+delta[cmd][1])) {
				x += delta[cmd][0];
				y += delta[cmd][1];
				rollDice(cmd);
				System.out.println(dice[1][1]);
			}
		}
	}
	
	/*
	 *  주사위(3X4) 굴리기
	 *        남쪽 
	 *  서쪽  위쪽  동쪽
	 *        북쪽
	 *        밑쪽
	 */
	public static void rollDice(int cmd) {
		int temp;
		switch (cmd) {
		case EAST:
			temp = dice[1][0];
			dice[1][0] = dice[3][1];
			dice[3][1] = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = temp;
			break;
			
		case WEST:
			temp = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = dice[3][1];
			dice[3][1] = temp;
			break;
					
		case NORTH:
			temp = dice[3][1];
			dice[3][1] = dice[2][1];
			dice[2][1] = dice[1][1];
			dice[1][1] = dice[0][1];
			dice[0][1] = temp;
			break;
			
		case SOUTH:
			temp = dice[0][1];
			dice[0][1] = dice[1][1];
			dice[1][1] = dice[2][1];
			dice[2][1] = dice[3][1];
			dice[3][1] = temp;
			break;

		default:
			break;
		}
		
		// map이 0이면 주사위 밑면 복사
		if(map[x][y] == 0) { 
			map[x][y] = dice[3][1];
		}
		// map이 0이아니면 주사위 밑면에 map 복사하고 map을 0으로 세팅
		else{
			dice[3][1] = map[x][y];
			map[x][y] = 0;
		}
	}
	
	public static boolean isOOB(int i, int j) {
		return i>N-1 || i<0 || j>M-1 || j<0;
	}
}
