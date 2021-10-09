package BaekOJ.study.date1012;

import java.io.*;
import java.util.*;

/*
 * 진짜 작은놈이고 적당한놈이고 큰놈이고간에 상어들 다 극혐
 * 
 * 1. 상어가 자신의 위치에 냄새를 뿌린다.
 * 2. 상어가 이동하고 K초 후 냄새가 사라짐
 * 3. 상어는 우선순위에 따라 아무 냄새가 없는 칸으로 이동함
 * 4. 아무 냄새가 없는 칸이 없다면 우선순위에 따라 자신의 냄새가 있는 칸으로 이동한다.
 * 5. 같은 곳에서 상어가 만나면 숫자가 작은 상어가 다른 상어를 아예 맵에서 쫓아냄
 * 6. 상어가 1번 상어만 남으면 소요시간 출력하고 중단
 * 7. 소요시간이 1000이상이 되면 -1 출력하고 중단
 * 
 * 시행착오 : 큐로 BFS하다가 time체크를 쉽게하려고 while문으로 다시 구현
 * 
 * 메모리 	시간
 * 19596	208
 */

public class BaekOJ19237_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, M, K, time, map[][], dir[], shark[][][], smell[][][], temp[][];
	static int delta[][] = {{-1,0}, {1,0}, {0,-1}, {0,1}}; 

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		// 각 상어들의 방향
		dir = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) dir[i] = Integer.parseInt(st.nextToken());
		
		// 각 상어들의 우선 순위 입력
		shark = new int[M][4][4];
		for(int i = 0; i < M; i++) {
			for(int d = 0; d < 4; d++) {
				st = new StringTokenizer(br.readLine());
				for(int p = 0; p < 4; p++) shark[i][d][p] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 맵에서 상어번호와 지속시간을 각각 담을 배열
		smell = new int[N][N][2];
		
		while(true) {
			// 자신의 위치에 냄새를 뿌림, 냄새가 이미 뿌려져 있으면 지속시간 -1
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(smell[i][j][1] > 0) smell[i][j][1] -= 1;
					if(map[i][j] != 0) {
						smell[i][j][0] = map[i][j];
						smell[i][j][1] = K;
					}
				}
			}
			
			// 임시배열 초기화
			for(int i = 0; i < N; i++) Arrays.fill(temp[i], 0);
			
			// 
			for(int i = 0; i < N; i++) {
			loop:for(int j = 0; j < N; j++) {
					if(map[i][j] != 0) {
						int di, dj, now = dir[map[i][j]-1];
						for(int d = 0; d < 4; d++) {
							di = i + delta[shark[map[i][j]-1][now-1][d]-1][0];
							dj = j + delta[shark[map[i][j]-1][now-1][d]-1][1];
							// 아무 냄새가 없는 곳을 찾아서 방향을 설정
							if(!isOOB(di, dj) && smell[di][dj][1] == 0) {
								dir[map[i][j]-1] = shark[map[i][j]-1][now-1][d];
								// 아무도 없으면 일단 이동하고,
								// 다른 상어가 있으면 작은 상어가 이김
								if(temp[di][dj] == 0) temp[di][dj] = map[i][j];
								else temp[di][dj] = Math.min(temp[di][dj], map[i][j]);
								continue loop;
							}
						}
						for(int d = 0; d < 4; d++) {
							di = i + delta[shark[map[i][j]-1][now-1][d]-1][0];
							dj = j + delta[shark[map[i][j]-1][now-1][d]-1][1];
							// 아무 냄새가 없는 곳이 없으면, 자신의 냄새를 찾아서 우선 순위대로 방향을 설정
							if(!isOOB(di, dj) && smell[di][dj][0] == map[i][j]) {
								dir[map[i][j]-1] = shark[map[i][j]-1][now-1][d];
								temp[di][dj] = map[i][j];
								break;
							}
						}
					}
				}
			}
			
			time++;
			boolean check = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = temp[i][j];
					// 아직도 1번 상어 말고도 다른 상어가 있는지 체크
					if(map[i][j] > 1) check = true;
				}
			}

			if(!check) {
				System.out.println(time);
				break;
			}
			
			// (중요!) 시간이 1000이상이 되면 -1 출력
			if(time >= 1000) {
				System.out.println(-1);
				break;
			}
		}
	}
	
	public static boolean isOOB(int i, int j) {
		return i > N - 1 || i < 0 || j > N - 1 || j < 0;
	}
}
