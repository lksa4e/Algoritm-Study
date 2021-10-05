package BaekOJ.study.date1005;

import java.io.*;
import java.util.*;

/*
 * 지금 풀이방법은 문제를 보자마자 생각이 났는데 정말 최후의 보루로 남겨두고
 * 구간합 그리디, pq, dfs 등의 방법으로 접근해보았으나... 접근해보았던 대부분의 풀이가 시간계산 해보니 비효율적이어서 포기함
 * 
 * 메모리 	시간
 * 31416	552
 */
public class BaekOJ14500_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, M, map[][], result;
	
	static int tetromino[][][] = {
	// ㅁ ㅁ ㅁ ㅁ
		{{0,0}, {0,1}, {1,0}, {1,1}},
	// ㅁ ㅁ
	// ㅁ ㅁ
		{{0,0}, {0,1}, {0,2}, {0,3}},
		{{0,0}, {1,0}, {2,0}, {3,0}},
	// ㅁ ㅁ ㅁ
	//   ㅁ
		{{0,0}, {0,1}, {0,2}, {1,1}},
		{{0,0}, {1,0}, {2,0}, {1,1}},
		{{0,1}, {1,0}, {1,1}, {2,1}},
		{{0,1}, {1,0}, {1,1}, {1,2}},
	// ㅁ ㅁ
	//   ㅁ ㅁ
		{{0,0}, {0,1}, {1,1}, {1,2}},
		{{1,0}, {0,1}, {1,1}, {0,2}},
		{{1,0}, {2,0}, {0,1}, {1,1}},
		{{0,0}, {1,0}, {1,1}, {2,1}},
	// ㅁ
	// ㅁ ㅁ ㅁ
		{{0,0}, {1,0}, {1,1}, {1,2}},
		{{0,2}, {1,0}, {1,1}, {1,2}},
		{{0,0}, {0,1}, {0,2}, {1,2}},
		{{0,0}, {0,1}, {0,2}, {1,0}},
		{{0,0}, {0,1}, {1,0}, {2,0}},
		{{0,0}, {0,1}, {1,1}, {2,1}},
		{{2,0}, {2,1}, {1,1}, {0,1}},
		{{0,0}, {1,0}, {2,0}, {2,1}}
	};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) 
			for(int j = 0; j < M; j++) getMax(i, j);
		
		System.out.println(result);
	}
	
	public static void getMax(int i, int j) {
		int cnt, sum;
		for(int[][] tet : tetromino) {
			cnt = sum = 0; // 각 테트로미노마다 초기화
			for(int[] t : tet) { 
				if(!isOOB(i+t[0], j+t[1])) { // 맵 범위만 벗어나지 않으면, 테트로미노 완성시킬 수 있음 (cnt 변수로 완성체크)
					sum += map[i+t[0]][j+t[1]];
					cnt++;
				}
			}
			if(cnt == 4) result = Math.max(result, sum); // 테트로미노가 완성이 되면 최대합 구하기
		}
	}
	
	public static boolean isOOB(int i, int j) {
		return i>N-1 || i<0 || j>M-1 || j<0;
	}
}
