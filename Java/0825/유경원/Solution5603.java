package swea;

import java.io.*;
import java.util.*;

class Solution5603 {
	/*
	 * 건초더미 크기를 다 더해서 건초더미들 개수로 나누면 초기 건초더미 크기
	 * 초기 크기보다 작은 건초더미들 얼마나 부족한지(차이가 곧 옮기는 횟수) 더해준다
	 */
	static int T, N;
	static int[] arr;
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("C:\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			
			int sum = 0;
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(br.readLine());
				sum += arr[i];
			}
			int origin = sum/N; // 초기 건초더미 크기
			
			int ans = 0;
			for(int a: arr) // 초기 건초더미 크기보다 작다면 그 차이를 더해준다
				if(a < origin) ans += origin - a; 
			
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
