package BaekOJ.study.date1023;

import java.io.*;
import java.util.*;

/*
 * 백준 20057 마법사 상어와 토네이도
 * 
 * 패턴을 찾아 회오리를 이동시켜 모래를 날림
 * 밖에 날라간 모래를 찾음
 * 따로 밖에 나간 모래를 구하기보다, 전체 - 맵에 남아있는 모래로 밖으로 나간 모래를 구함 
 * 
 * 시행착오 : α가 당연히 55%로 두고 했는데 답이 영 이상하게 나와서
 * 문제를 계속 다시 읽어보니 소수점 아래를 버리다 보니 총량을 유지시키기 위해서 왜 55%가 아니라 α로 뒀는지 깨닫게됨
 * 
 * 메모리 	시간
 * 39252	548
 */

public class BaekOJ20057_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, map[][], total;
	static int delta[][] = {{0,-1}, {1,0}, {0,1}, {-1,0}};
	// 모래가 날라가는 비율
	static int sand[] = {2, 10, 7, 1, 5, 10, 7, 1, 2};
	// 해당 비율과 매칭되는 인덱스, 마지막 0,-1은 α로 남은 모래를 다 여기다 모음
	static int L[][] = {{-2,0}, {-1,-1}, {-1,0}, {-1,1}, {0,-2}, {1,-1}, {1,0}, {1,1}, {2,0}, {0,-1}};
	static int[][] D,R,U, dir[];
	static int si, sj, tI, tJ;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		// 초기 토네이도 인덱스
		tI = N/2;
		tJ = N/2;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j]; // 전체 모래 총량을 미리 구함
			}
		}
		
		// 토네이도가 왼쪽으로 갈 때, 날리는 모래비율 배열L 을 반시계 방향 회전으로 D,R,U 배열 구함 
		D = rotate(L);
		R = rotate(D);
		U = rotate(R);
		dir = new int[][][] {L, D, R, U}; // 하나로 묶음
		
		// 1122/3344/5566 이런식으로 LDRU 반복
		for(int i = 1; i < N; i += 2) {
			for(int j = 0; j < 4; j++) {
				if(j < 2) move(j, i);
				else move(j, i+1);
			}
		}
		// 마지막으로 L방향 이동
		move(0, N-1);
		
		// 전체 모래량에서 남아있는 모래량으로 날라간 모래량을 구함 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) 
				total -= map[i][j];
		}
		
		System.out.println(total);
	}

	public static void move(int idx, int num) {
		for(int m = 1; m <= num; m++) {
			tI += delta[idx][0];
			tJ += delta[idx][1];
			
			int sandTotal = 0;
			// s가 0에서 8까진 %가 주어진 모래를 날리고, 마지막 s=9때는 남은 모래를 모두 α에 넣고 break
			for(int s = 0; s < 10; s++) {
				si = tI+dir[idx][s][0];
				sj = tJ+dir[idx][s][1];
				// 맨 마지막에 α에 나머지 모래를 다 넣음
				if(s == 9) {
					if(!isOOB(si, sj)) map[si][sj] += map[tI][tJ] - sandTotal;
					break;
				}
				// 각 위치에 모래를 날리고, 맵안으로 날라가면 날라간 만큼 더함
				if(!isOOB(si, sj))map[si][sj] += (map[tI][tJ] * sand[s])/100;
				// 날라간 모래량을 구함
				sandTotal += (map[tI][tJ] * sand[s])/100;
			}
			// 토네이도가 지나간 직후의 자리에는 모래가 다 날라가고 없음
			map[tI][tJ] = 0;
		}
	}
	
	public static int[][] rotate(int[][] arr) {
		int[][] temp = new int[10][2];
		for(int i = 0; i < 10; i++) {
			temp[i][0] = -arr[i][1];
			temp[i][1] = arr[i][0];
		}
		return temp; 
	}
	
	public static boolean isOOB(int i, int j) {
		return i>N-1 || i<0 || j>N-1 || j<0;
	}
}
