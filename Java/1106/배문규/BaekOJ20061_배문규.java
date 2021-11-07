package BaekOJ.study.date1030;

import java.io.*;
import java.util.*;

/*
 * 백준 20061 미노미노도미노
 * 
 * 문제가 길어도 너무 길다 그리고 이름은 도미노인데 사실 테트리스였음
 * 
 * 룰
 * 1. 열이나 행이 4칸으로 가득차면 해당 칸을 제거하고 한칸씩 당김
 * 2. 진한색의 4*4블록을 벗어나면 5번행 또는 열에 있는 블록을 제거하고 한칸씩 당김
 * 3. 1,2번 조건이 동시에 발생하면 1번 먼저 수행 후, 2번을 수행함
 * 
 * 블록은 1X1, 2X1, 1X2 3개 있음
 * t = 1: 크기가 1×1인 블록을 (x, y)에 놓은 경우 
 * t = 2: 크기가 1×2인 블록을 (x, y), (x, y+1)에 놓은 경우
 * t = 3: 크기가 2×1인 블록을 (x, y), (x+1, y)에 놓은 경우
 * 
 * 파란색과 초록색은 결국 메커니즘은 같기 때문에 코드중복을 줄이기 위해
 * 초록색을 기준으로 파란색을 초록색으로 조절해주었음
 * 
 * 메모리 	시간
 * 20856	264
 */

public class BaekOJ20061_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, result;
	static int blueMap[][] = new int[6][4];
	static int greenMap[][] = new int[6][4];
	static int delta[][] = {{0,1}, {1,0}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		for(int idx = 0; idx < N; idx++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			// 2->3, 3->2 
			// i, j 시계 방향 회전
			move(blueMap, t == 1 ? 1 : (t == 2 ? 3 : 2), j, 3-i, true);
			move(greenMap, t, i, j, false);
			clearLine(blueMap);
			clearLine(greenMap);
			clearOOB(blueMap);
			clearOOB(greenMap);
		}
		System.out.println(result + "\n" + getBlock());
	}

	public static void move(int[][] map, int t, int y, int x, boolean isBlue) {
		// 파란색인데, t가 2이면 기준좌표 1칸 왼쪽으로 이동
		if(isBlue && t == 2) x -= 1; 
		
		// t : 1번 (1X1)
		// ㅁ
		if(t == 1) {
			int col = 0;
			for (int i = 0; i < 6; i++) {
				if(map[i][x] == 0) {
					col = i;
					continue;
				}
				else break;
			}
			map[col][x] = 1;
		}
		// t : 2번 (1X2)
		// ㅁ ㅁ
		else if(t == 2) {
			int col = 0;
			for (int j = 0; j < 6; j++) {
				if(map[j][x] == 0 && map[j][x+1] == 0) {
					col = j;
					continue;
				}
				else break;
			}
			map[col][x] = 1;
			map[col][x+1] = 1;
		}
		// t : 3번 (2X1)
		// ㅁ
		// ㅁ
		else {
			int col = 0;
			for (int j = 1; j < 6; j++) {
				if(map[j][x] == 0 && map[j-1][x] == 0) {
					col = j;
					continue;
				}else {
					break;
				}
			}
			map[col][x]=1;
			map[col-1][x]=1;
		}
	}
	
	private static void clearLine(int[][] map) {
		while(true) {
			boolean flag = true;
			// 꽉찬 행이 있는지 체크
			for(int j = 5; j > 1; j--) {
				int cnt=0;
				for(int i = 0; i < 4; i++) {
					if(map[j][i] == 1) cnt += 1;
				}
				// 있다면 없애고 댕기기
				if(cnt == 4) {
					result += 1;
					flag = false;
					// 비우고
					for(int i = 0; i < 4; i++) map[j][i] = 0;
					
					// 댕기기
					for(int k = j-1; k >= 0; k--) {
						for(int i = 0; i < 4; i++) {
							map[k+1][i] = map[k][i];
						}
					}
					// 다시 비우기
					for(int i = 0; i < 4; i++) map[0][i] = 0;
					
					break;
				}
			}
			
			if(flag) break;
		}
	}
	public static void clearOOB(int[][] map) {
		int cnt = 0;
		// 안튀어나왔는지, 1줄이 튀어나왔는지, 2줄이 튀어나왔는지 체크
		for(int j = 0; j < 2; j++) {
			for(int i = 0; i < 4; i++) {
				if(map[j][i] != 0) {
					cnt += 1;
					break;
				}
			}
		}
		// 튀어나왔다면
		if(cnt != 0) {
			for(int k = 5; k > 1; k--) {
				for(int i = 0; i < 4; i++) map[k][i] = map[k-cnt][i];
			}
			for(int j = 0; j < 2; j++) {
				for(int i = 0; i < 4; i++) map[j][i] = 0;
			}
		}
	}
	
	private static int getBlock() {
		int cnt = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if(greenMap[i][j] == 1) cnt += 1;
				if(blueMap[i][j] == 1) cnt += 1;
			}
		}
		return cnt;
	}
}
