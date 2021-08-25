package BaekOJ.study.date0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 전형적인 분할 정복 문제이다.
 * 9분할할 때, 재귀에 넘겨주는 시작점 인덱스정도만 좀 신경써서 넘겨주면 되는 간단한 문제
 * 
 * 메모리 	시간
 * 310240	1232
 */

public class BaekOJ1780_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, map[][], minus_one, zero, one;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		cut(0, 0, N);
		System.out.println(minus_one+"\n"+zero+"\n"+one);
	}
	
	public static void cut(int start_i, int start_j, int size) {
		int temp_1, temp0, temp1;
		temp_1 = temp0 = temp1 = 0;
		
		// 순차탐색해서 각 숫자 카운트
		for(int i = start_i; i < start_i+size; i++) {
			for(int j = start_j; j < start_j+size; j++) {
				switch (map[i][j]) {
				case -1: temp_1 += 1; break;
				case 0: temp0 += 1; break;
				case 1: temp1 += 1; break;
				}
			}
		}
		
		// 만약에 개수와 넓이가 동일하다면 1장
		if(temp_1 == size*size) { minus_one++; return; }
		else if(temp0 == size*size) { zero++; return; }
		else if(temp1 == size*size) { one++; return; }
				
		// 9분할정복
		for(int i = start_i; i <= start_i+(size/3)*2; i += size/3) {
			for(int j = start_j; j <= start_j+(size/3)*2; j += size/3) {
				cut(i, j, size/3);
			}
		}
	}
	
}