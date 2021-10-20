package BaekOJ.study.date1023;

import java.io.*;
import java.util.*;

class Shark{
	int i, j, speed, dir, size;
	boolean isAlive = true;
	
	public Shark(int i, int j, int speed, int dir, int size) {
		super();
		this.i = i;
		this.j = j;
		this.speed = speed;
		this.dir = dir;
		this.size = size;
	}
}

/*
 * 백준 17413 낚시왕
 * 
 * 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
 * 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
 * 3. 상어가 이동한다.
 * 
 * 그냥 상어를 한칸씩 이동시키면 훨씬 쉽긴한데 시간의 압박때문에 모듈러 연산으로 이동시키려니 머리가 아팠음
 *
 * 메모리 	시간
 * 23864	368
 */

public class BaekOJ17143_배문규 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int R, C, M, map[][], result;
	static Shark shark[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		shark = new Shark[M+1];
		for(int idx = 1; idx <= M; idx++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			
			map[i][j] = idx;
			shark[idx] = new Shark(i, j, speed, dir, size);
		}
		
		// 모듈러 연산을 통해 상어를 이동시킬 배열
		// if C : 6 ==> cols : 1 2 3 4 5 6 5 4 3 2 / 1 2 3 4 5 6 5 4 3 2 식으로 반복
		// if R : 4 ==> rows : 1 2 3 4 3 2 
		int cols[] = new int[(C-1)*2];
		for(int c = 0; c < (C-1)*2; c++) {
			if(c < C) cols[c] = c+1;
			else cols[c] = 2*C-c-1;
		}
			
		int rows[] = new int[(R-1)*2];
		for(int r = 0; r < (R-1)*2; r++) {
			if(r < R) rows[r] = r+1;
			else rows[r] = 2*R-r-1;
		}
		
		// 낚시왕이 장소를 옮길때마다
		for(int j = 1; j <= C; j++) {
			// 1. 낚시왕이 해당 열에서 땅과 제일 가까운 상어잡기
			for(int i = 1; i <= R; i++) {
				if(map[i][j] != 0) {
					// 안걸거치게 맵 밖으로 보내버리기
					shark[map[i][j]].isAlive = false;
					result += shark[map[i][j]].size;
					break;
				}
			}
			
			// 2. 상어 이동
			for(int idx = 1; idx <= M; idx++) {
				if(shark[idx].isAlive) {
					if(shark[idx].dir == 1) {
						int movedI = (2*R - shark[idx].i + shark[idx].speed - 1) % ((R-1)*2);
						if(movedI < R) shark[idx].dir = 2;
						shark[idx].i = rows[movedI];
					}
					else if(shark[idx].dir == 2) {
						int movedI = (shark[idx].i + shark[idx].speed - 1) % ((R-1)*2);
						if(movedI > R-1) shark[idx].dir = 1;
						shark[idx].i = rows[movedI];
					}
					else if(shark[idx].dir == 3) {
						int movedJ = (shark[idx].j + shark[idx].speed - 1) % ((C-1)*2);
						if(movedJ > C-1) shark[idx].dir = 4;
						shark[idx].j = cols[movedJ];
					}
					else if(shark[idx].dir == 4) {
						int movedJ = (2*C - shark[idx].j + shark[idx].speed - 1) % ((C-1)*2);
						if(movedJ < C) shark[idx].dir = 3;
						shark[idx].j = cols[movedJ];
					}
				}
			}
			
			// 맵 갱신을 위해 초기화
			for(int i = 0; i < R+1; i++) Arrays.fill(map[i], 0);
			
			for(int idx = 1; idx <= M; idx++) {
				if(shark[idx].isAlive) {
					// 같은 위치에 상어가 여러마리면
					if(map[shark[idx].i][shark[idx].j] != 0) {
						// 큰놈만 살려둠
						if(shark[map[shark[idx].i][shark[idx].j]].size > shark[idx].size) {
							shark[idx].isAlive = false;
						}
						else {
							shark[map[shark[idx].i][shark[idx].j]].isAlive = false;
							map[shark[idx].i][shark[idx].j] = idx;
						}
					}
					else map[shark[idx].i][shark[idx].j] = idx;
				}
			}
		}
		
		System.out.println(result);
	}
}

