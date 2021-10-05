package BaekOJ.study.date1002;

import java.io.*;
import java.util.*;

/* 
 * 처음에 상하좌우 4방향으로 dfs를 했더니 i,j 인덱스 관리하는게 너무 머리아파서 
 * 2차원 배열을 사용하지 않고, 1차원 배열씩 한 방향으로만 합쳐서 저장하고  
 * 배열을 회전시키는 방식으로 접근하니 훨씬 편해졌음.
 * 
 * 시행착오 :
 * merge 메소드에서 숫자를 합치기 전에도 push를 해줘야 하고, 숫자를 합치고 난 다음에도 다시 한번 앞으로 push 해줘야 함
 * 둘 중 한번만 해주니 4% 컷
 * 
 * 메모리 	시간
 * 24724	204
 */

public class BaekOJ12100_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int MAX = 5;
	static int N, map[][], result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dfs(map, 0);
		System.out.println(result);
	}
	
	public static void dfs(int map[][], int move) {
		if(move == MAX) { // 기저조건 : 5번 움직였으면 최댓값 구하기
			result = Math.max(result,getResult(map));
			return;
		}
		
		int mergedMap[][] = new int[N][N]; // map을 한 방향으로 합친 결과를 저장할 배열
		for(int d = 0; d < 4; d++) { // 상하좌우 dfs
			for(int i = 0; i < N; i++) mergedMap[i] = merge(map[i]); // 한 방향으로 한 라인씩 합치고 저장 
			dfs(mergedMap, move+1);
			map = rotate(map); // 배열 회전
		}
	}
	
	public static int[] merge(int line[]) {
		int pushedLine[] = push(line); // 앞으로 모으기
		// 숫자 합치기
		// 뒤에 숫자와 같으면, 앞 숫자에 두 숫자를 합치고 뒷 숫자는 0으로 초기화
		for(int i = 1; i < N; i++) {
			if(pushedLine[i-1] == pushedLine[i]) {
				pushedLine[i-1] += pushedLine[i];
				pushedLine[i] = 0;
			}
		}
		return push(pushedLine); // 다시 앞으로 모아 리턴
	}

	// 0으로 임시 배열을 채우고, 앞으로 모아서 리턴
	public static int[] push(int line[]) {
		int idx = 0; // 앞에서 부터 채울 인덱스
		int tempLine[] = new int[N];
		Arrays.fill(tempLine, 0); // 0으로 채우기
		for(int i = 0; i < N; i++) {
			if(line[i] != 0) tempLine[idx++] = line[i]; // 0이 아니면, 앞에서부터 숫자 채우기
		}
		return tempLine;
	}
	
	// 시계 방향으로 배열 회전
	public static int[][] rotate(int map[][]){
		int[][] copy = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) copy[j][N-i-1] = map[i][j];
		}
		return copy;
	}
	
	// 최대값 구하기
	public static int getResult(int map[][]) {
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(max < map[i][j]) max = map[i][j];
			}
		}
		return max;
	}
}