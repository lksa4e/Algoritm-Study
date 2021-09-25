package BaekOJ.study.date0921;

import java.io.*;
import java.util.*;

/*
 * 넥퍼를 이용해서 타자들 순열을 쉽게 구현해서
 * 전체적인 틀은 쉽게 만들었는데 디테일한 구현이 까다로운 느낌
 * 현재 타자(변수 p) 매 조합마다 초기화해야 되는거 빼먹어서 
 * 그거 찾는데 30분...
 * 
 * 시행착오
 * 1. 주자들 출루해있는 상태를 List<Boolean>으로 한칸씩 밀어내듯이 offer, poll 하니까 시간초과
 * 
 * 메모리 	시간
 * 20508	620
 */
public class BaekOJ17281_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, player[][], play, p, score, result, order[] = {1, 2, 3, 4, 5, 6, 7, 8}; // 0번 빼고 나머지
	static boolean base[] = new boolean[8]; // 베이스 4개 + 히트 종류 4개
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		player = new int[N][9];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) player[i][j] = Integer.parseInt(st.nextToken());
		}
		
		do {
			score = 0; // 점수 초기화
			p = 0; // 현재 타자 초기화
			
			// 이닝 별
			for(int i = 0; i < N; i++) {
				int out = 0; // 아웃카운트 초기화
				Arrays.fill(base, false);// 1,2,3루 초기화
				
				// 아웃이 3개 될 떄 까지 반복
				while(true) {
					if(p < 3) play = player[i][order[p]]; // 1~3번 타자
					else if(p == 3) play = player[i][0]; // 4번타자 고정
					else if(p > 3) play = player[i][order[p-1]]; // 5~9번 타자
					
					if(++p == 9) p = 0; // 다음타자, 9번타자 다음 1번타자
					
					if(play == 0 && ++out == 3) break; // 아웃인데, 아웃 카운트가 3이 되면 다음 이닝 
					else if(play != 0) hit(play); // 아니면 히트
				}
			}
			result = Math.max(result, score); // 최대값 갱신
		}while(np(order));
		
		System.out.println(result);
	}
	
	public static void hit(int play) {
		base[3] = true; // 베이스 주자 
		for(int i = 0; i < 4; i++) {
			if(i < play && base[i]) score++; // 홈 인
			base[i] = base[play+i]; // 한칸씩 땡기기
		}
	}
	
	public static boolean np(int[] arr) {
		// 8개로 순열 생성
		int i = 7; 
		while (i > 0 && arr[i - 1] >= arr[i]) i--;

		if (i == 0) return false;

		int j = 7;
		while (arr[i - 1] >= arr[j]) j--;
		swap(arr, i - 1, j);

		int k = 7;
		while (i < k) swap(arr, i++, k--);

		return true;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
