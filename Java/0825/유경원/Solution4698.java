package swea;

import java.io.*;
import java.util.*;

class Solution4698 {
	/*
	 * 에라토스테네스의 체로 1000000까지의 소수를 구하고 조건에 맞는 특별한 소수 개수를 구한다.
	 */
	static int T, D, A, B;
	static final int N = 1000000;
	static final boolean[] sosu = new boolean[N+1];
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("C:\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for(int i=2; i<=Math.sqrt(N); i++) { // 에라토스테네스의 체, 소수를 false로
			if(sosu[i]) continue;
			int j=i;
			while(i*j<=N) {
				sosu[i*j] = true;
				j++;
			}
		}
		
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			if(A<2) A=2; // 만약 A가 2미만이라면 소수인 2부터 시작하도록 설정
			int ans = 0;
			for(int i=A; i<=B; i++) {
				if(!sosu[i]) { // false가 소수
					int num = i;
					while(num>0) {
						if(num%10 == D) { // 특별한 소수라면 답 증가시키고 break
							ans++; break;
						}
						num /= 10;
					}
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
