package swea;

import java.io.*;
import java.util.*;

class Solution7964 {
	/*
	 * 입력받으면서 0이 연속으로 D번 나오면 관문 개수++
	 */
	static int T, N, D;
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("C:\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			// 입력받은 수 n, 0의 개수 cnt, 차원관문 수 ans
			int n=0, cnt=0, ans=0;
			for(int i=0; i<N; i++) {
				n = Integer.parseInt(st.nextToken());
				if(n==0) { 
					cnt++;
					if(cnt == D) { // 0이 연속 D번 나왔으면
						ans++; // 관문 수++;
						cnt = 0; // 0 개수 초기화
					}
				}else { // 1나오면 0 개수 초기화
					cnt = 0; 
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
}
