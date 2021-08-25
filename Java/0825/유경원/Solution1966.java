package swea;

import java.io.*;
import java.util.*;

class Solution1966 {
	/*
	 * 정렬하기
	 */
	static int T, N;
	static int[] arr;
	public static void main(String args[]) throws IOException {
//		System.setIn(new FileInputStream("C:\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arr);
			
			sb.append("#").append(testCase);
			for(int a: arr) sb.append(" ").append(a);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
