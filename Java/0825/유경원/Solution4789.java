package swea;

import java.io.*;
import java.util.*;

class Solution4789 {
	/*
	 * 사람 수를 더해가며 '사람 수 < 박수 치는데 필요한 사람'일 경우 그 차이만큼 더해준다
	 */
	static int T;
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("C:\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			String s = br.readLine();
			
			int person = 0, sum = 0, ans = 0;
			for(int i=0; i<s.length(); i++) {
				person = s.charAt(i) - '0';
				if(person != 0 && sum<i) { // 여태까지 사람 수 < 박수치는데 필요한 사람 수
					ans += i-sum; // 차이 만큼 더해준다
					sum += i-sum;
				}
				sum += person;
			}
			
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
