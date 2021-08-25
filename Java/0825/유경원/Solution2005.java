package swea;

import java.io.*;

class Solution2005 {
	/*
	 * 규칙에 맞게 출력
	 */
	static int T, N;
	static int[][] arr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N+1];
		
			sb.append("#").append(testCase).append("\n");
			arr[0][1] = 1;
			sb.append(1).append("\n");
			for(int i=1; i<N; i++) {
				for(int j=1; j<=i+1; j++) {
					arr[i][j] = arr[i-1][j-1] + arr[i-1][j]; // 왼쪽위 와 위 숫자의 합
					sb.append(arr[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}