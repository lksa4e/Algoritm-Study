package swea;

import java.io.*;
import java.util.*;

class Solution6485 {
	/*
	 * 5001크기의 배열을 선언해놓고 입력받은 버스 노선 범위만큼++
	 * 각 버스정류장 번호를 배열 인덱스로 하여 출력
	 */
	static int T, N, P;
	static int[] bus;
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("C:\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			bus = new int[5001]; // 5000개의 버스 정류장
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// a~b 버스정류장 ++
				for(int j=a; j<=b; j++) bus[j]++;
			}
			
			sb.append("#").append(testCase);
			P = Integer.parseInt(br.readLine());
			for(int i=0; i<P; i++) {
				int n = Integer.parseInt(br.readLine()); // 버스정류장 번호
				
				sb.append(" ").append(bus[n]); // 버스정류장 지나는 버스 노선 개수
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
}
