package SWEA.study0825;

import java.io.*;
import java.util.*;

/*
 * 입력 받고 정렬해서 출력하면 되는 문제
 * 이게 의미가 있는건가 싶음
 * 
 * 메모리		시간
 * 20,184 	111 
 */
public class Solution1966_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			int[] num = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(num);
			
			sb.append("#").append(tc).append(" ");
			for(int n : num) sb.append(n).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
