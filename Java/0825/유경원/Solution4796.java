package swea;

import java.io.*;
import java.util.*;

class Solution4796 {
	/*
	 *  BufferedReader는 9000바이트 정도의 입력 제한이 있는데 테케에 그 범위를 넘어가는 입력이 있어서 Scanner 씀
	 *  우뚝선 산 구간 개수 = 산 올라간 횟수 * 내려간 횟수 
	 */
	static int T, N;
	static int[] arr;
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("C:\\input.txt"));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		T = sc.nextInt();
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = sc.nextInt();
			arr = new int[N+1];
			arr[N] = Integer.MAX_VALUE; // 패딩
			for(int i=0; i<N; i++) arr[i] = sc.nextInt();
			
			boolean up = false, down = false; // up 산 올라감 down 내려감
			int left=0, right=0, ans=0; // left 산 올라간 횟수 right 산 내려간 횟수
			for(int i=1; i<N+1; i++) {
				if(arr[i-1]<arr[i]) {
					if(down) { // 산 올라갔다가 내려갔을때
						ans += left*right; // 우뚝선 산 구간 개수 = 산 올라간 횟수 * 내려간 횟수 
						down = false;
						left=right=0;
					}
					left++; // 올라간 횟수++
					up = true;
				}else if(up){ // arr[i-1] > arr[i] 산 올라갔다가 내려갈때
					right++; // 내려간 횟수++
					down = true; 
				}
			}
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
