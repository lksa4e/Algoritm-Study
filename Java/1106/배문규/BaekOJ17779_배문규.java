package BaekOJ.study.date1030;

import java.io.*;
import java.util.*;

/*
 * 백준 17779 게리맨더링2
 * 
 * 1. 구역을 5개로 나눠야 하고 모든 구역은 5개의 선거구 중 하나에 포함되어야 함.
 * 2. 한 선거구에 포함된 구역은 모두 연결되어 있어야 함.
 * 
 * 선거구를 나누는 방법이 도대체 뭔소린지 이해하기가 힘들었음
 * 아래는 선거구를 나누는 방법이다.
 * 
 * 1) 기준점(x, y)와 경계의 길이 d1, d2
 * - 1 <= d1, d2
 * - 1 <= x < (x + d1 + d2) <= N
 * - 1 <= (y - d1) < y < (y + d2) <= N
 * 
 * 2) 경계선
 * - (x, y), (x+1, y-1), ..., (x+d1, y-d1)
 * - (x, y), (x+1, y+1), ..., (x+d2, y+d2)
 * - (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
 * - (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
 * 
 * 3) 경계선과 경계선 안에 포함되어있는 곳은 5번 선거구이다.
 * 
 * 4) 5번 선거구에 포함되지 않은 구역 (r, c)의 선거구 번호는 다음 기준을 따른다.
 * - 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
 * - 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
 * - 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d
 * - 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
 * 
 * 조건에 따라 완탐을 하면서 최적의 x, y, d1, d2을 찾아야 함
 * 
 * 메모리 	시간
 * 17732	224
 */

public class BaekOJ17779_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, map[][], total, result = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		
		for(int y = 0; y < N-2; y++) {
			for(int x = 1; x < N-1; x++) {
				for(int d1 = 1; d1 < N-1; d1++) {
					for(int d2 = 1; d2 < N-1; d2++) makeSector(y, x, d1, d2);
				}
			}
		}
		
		System.out.println(result);
	}
	
	// 조건에 따라 만들어진 선거구에서 결과값 구하기
	public static void getResult(int y, int x, int d1, int d2) {
		// 선거구 배열
		int sector[] = new int[5];
		
		// 1번 선거구
		int x1 = x+1;
		for(int i = 0; i < y+d1; i++) {
			if(i >= y) x1 -= 1;
			sector[0] += sum(i, 0, x1);
		}
		
		// 2번 선거구
		int x2 = x+1;
		for(int i = 0; i < y+d2+1; i++) {
			if(i > y) x2 += 1;
			sector[1] += sum(i, x2, N);
		}
		
		// 3번 선거구
		int x3 = x-d1;
		for(int i = y+d1; i < N; i++) {
			sector[2] += sum(i, 0, x3);
			if(i < y+d1+d2) x3 += 1;
		}
		
		// 4번 선거구
		int x4 = x+d2;
		for(int i = y+d2+1; i < N; i++) {
			sector[3] += sum(i, x4, N);
			if(i <= y+d1+d2) x4 -= 1;
		}
		
		// 5번 선거구
		sector[4] = total - (sector[0] + sector[1] + sector[2] + sector[3]);
		
		// 정렬하고 최대값 - 최소값하여 결과값 구함
		Arrays.sort(sector);
		result = Math.min(result, sector[4] - sector[0]);
	}
	
	// 조건에 따라 선거구 만들기
	public static void makeSector(int y, int x, int d1, int d2) {
		if((0 <= y+d1-1 && y+d1-1 < N) && 
		   (0 <= y+d2-1 && y+d2-1 < N) && 
		   (0 <= x-d1+d2-1 && x-d1+d2-1 < N) &&
		   (0 <= x-d1 && x+d2 < N && y+d1+d2 < N)) {
			getResult(y, x, d1, d2);
		}
	}
	
	// 배열 구간합 구하기
	public static int sum(int y, int fromX, int toX) {
		int ret = 0;
		for(int i = fromX; i < toX; i++) ret += map[y][i];
		return ret;
	}
}
