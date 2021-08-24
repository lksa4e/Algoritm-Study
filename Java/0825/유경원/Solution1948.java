package swea;

import java.io.*;
import java.util.*;

class Solution1948 {
	static int T, firstM, firstD, secondM, secondD, ans;
	static final int[] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("C:\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			firstM = Integer.parseInt(st.nextToken());
			firstD = Integer.parseInt(st.nextToken());
			secondM = Integer.parseInt(st.nextToken());
			secondD = Integer.parseInt(st.nextToken());
			
			ans = secondD - firstD + 1;
			
			for(int i=firstM; i<secondM; i++) {
				ans += month[i];
			}
			
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
