package BaekOJ.study.date0921;

/*
 * 굉장히 쉽다고 생각하고 dfs로 풀었는데 
 * 처음에는 2중 for문으로 dfs하니까 생각처럼 플로우가 흘러가지 않아서 
 * 2중 for문과 같은 플로우의 재귀를 구현하니 원래 생각했던 대로 플로우로 재귀가 진행되었다.
 * 
 * 시행 착오 1. 
 * 2중 for문을 사용하니 dfs가 리턴되고 돌아올 때 엄청 꼬이더라
 * 재귀로 2중 for문을 구현하면 훨씬 더 구현이 직관적임 
 * 
 * 시행 착오 2.
 * 뭐로 덮었는지 마킹할 때 0으로 마킹 안하고 종이숫자에 - 부호를 붙혀서 마킹을 했더니 놀랍게도 시간초과가 남 
 * 
 * 메모리 	시간
 * 24480	240
 */

import java.io.*;
import java.util.*;

public class BaekOJ17136_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int map[][], cnt, result = Integer.MAX_VALUE, remain[] = {0,5,5,5,5,5};

	public static void main(String[] args) throws NumberFormatException, IOException {
		map = new int[10][10];
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	public static void dfs(int startI, int startJ) {
		// 행 끝 도달 
		if(startJ == 10) {
			dfs(startI+1, 0);
			return;
		}
		// 열 끝 도달 == 종료 
		// 최솟값 찾기 
		if(startI == 10) {
			result = result < cnt ? result : cnt;
			return;
		}

		// 0이면 다음으로 넘어가기 
		if(map[startI][startJ] == 0) {
			dfs(startI, startJ+1);
			return;
		}
		
		// 1이면 큰 사이즈부터 마킹
		for(int size = 5; size > 0; size--) {
			//종이가 남아있지 않거나, 종이를 덮을 수 없다면 컨티뉴 
			if(remain[size] == 0 || check(startI, startJ, size)) continue;
			
			// 덮을 수 있다면 0으로 마킹하고 사용 종이 감소 -> 추가 
			mark(startI, startJ, size, 0);
			remain[size]--;
			cnt++;
			
			// 다음으로 넘어가기 
			dfs(startI, startJ+size);
			
			// 원상 복구 
			mark(startI, startJ, size, 1);
			remain[size]++;
			cnt--;
		}	
	}
	
	// 범위 밖이거나, 중간에 0이 나타나면 true 리턴, 아니면 false
	public static boolean check(int si, int sj, int size) {
		if(isOOB(si+size, sj+size)) return true;
		for(int i = si; i < si+size; i++) {
			for(int j = sj; j < sj+size; j++) {
				if(map[i][j] == 0) return true;
			}
		}
		return false;
	}
	
	public static boolean isOOB(int i, int j) {
		if(i > 10 || j > 10) return true;
		else return false;
	}
	
	// map 마킹 
	public static void mark(int si, int sj, int size, int num) {
		for(int i = si; i < si+size; i++) {
			for(int j = sj; j < sj+size; j++) {
				map[i][j] = num;
			}
		}
	}

}
