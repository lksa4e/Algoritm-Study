package BaekOJ.study.date1012;

import java.io.*;
import java.util.*;

/*
 * 1. 상어가 물고기 먹기
 * 2. 모든 물고기 이동
 * 3. 상어가 갈 수 있는 곳에서 다 DFS
 * 
 * 시행착오 : 
 * 1. clone 메소드가 전체 딥카피인줄 알았으나, 1차원만 되서 문제가 생겼었다..
 * 2. 인덱스 사이즈를 16짜리 1차원 배열을 선언하고 {-1,0}은 -4, {-1,-1}는 -5처럼 표현했는데 잘 안됐음
 * 근데 clone때문에 그런 것 같지만 1차원으로 바꿔서 다시 풀어보고 싶지는 않음...
 * 
 * 메모리 	시간
 * 14340	156
 */

public class BaekOJ19236_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int FISH = 0, DIR = 1, SIZE = 4;
	static int result;
	static int delta[][] = {{-1,0}, {-1,-1}, {0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}, {-1,1}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][][] map = new int[SIZE][SIZE][2];
		for(int i = 0; i < SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < SIZE; j++) {
				map[i][j][FISH] = Integer.parseInt(st.nextToken());
				map[i][j][DIR] = Integer.parseInt(st.nextToken())-1;
			}
		}
		
		dfs(map, 0, 0, 0);
		System.out.println(result);
	}
	
	public static void dfs(int[][][] map, int i, int j, int eat) {
		// 해당 위치(i, j)의 물고기를 먹고 값 추가하고 0으로 갱신
		eat += map[i][j][FISH];
		map[i][j][FISH] = 0;
		
		// 1번 물고기부터 인덱스를 찾음
		for(int num = 1; num <= 16; num++) {
			int fishIdx = findIdx(map, num);
			// 이미 먹혀서 인덱스가 없다면 컨티뉴
			if(fishIdx < 0) continue;
			
			// 해당 물고기의 인덱스와 방향 추출
			int fishI = fishIdx/SIZE;
			int fishJ = fishIdx%SIZE;
			int fishDir = map[fishI][fishJ][DIR];
			
			// 해당 물고기에서 갈 수 있는 위치 찾기
			for(int d = 0; d < 8; d++) {
				int dIdx = (fishDir + d)%8;
				int nextI = fishI + delta[dIdx][0];
				int nextJ = fishJ + delta[dIdx][1];
				// 맵 범위 밖이 아니면서, 상어 위치가 아니라면 방향 갱신 후 위치 교환
				if(!isOOB(nextI, nextJ) && !(nextI == i && nextJ == j)) {
					map[fishI][fishJ][DIR] = dIdx;
					
					int[] temp = map[fishI][fishJ];
					map[fishI][fishJ] = map[nextI][nextJ];
					map[nextI][nextJ] = temp;
					break;
				}
			}
		}
		
		// 최대 값 갱신
		result = Math.max(result, eat);
		
		// 상어 위치 추출후 갈 수 있는 곳으로 이동해서 dfs 수행
		int sharkDir = map[i][j][DIR]; 
		for(int d = 1; d < SIZE; d++) {
			int nextI = i + (delta[sharkDir][0]*d);
			int nextJ = j + (delta[sharkDir][1]*d);
			if(!isOOB(nextI, nextJ) && map[nextI][nextJ][FISH] > 0) dfs(deepCopy(map), nextI, nextJ, eat);
		}
	}
	
	// 인덱스 찾기
	public static int findIdx(int[][][] map, int num) {
		for(int idx = 0; idx < 16; idx++) {
			if(map[idx/4][idx%4][FISH] == num) return idx;
		}
		return -1;
	}
	
	// 맵 범위 체크
	public static boolean isOOB(int i, int j) {
		return i > 3 || i < 0 || j > 3 || j < 0;
	}
	
	// 배열 딥카피
	public static int[][][] deepCopy(int[][][] map){
		int[][][] temp = new int[SIZE][SIZE][];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) temp[i][j] = map[i][j].clone();
		}
		return temp;
	}
}
