package SWEA.study0825;

import java.io.*;
import java.util.*;

/*
 * 문제를 보니 행렬에서 첫번째 열에 패딩으로 빈공간을 주면 쉽게 풀릴거라 생각함
 * 실제로 패딩만 주고 별다른 조건없이 triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j]해도 결과가 잘나옴 
 * 
 * 메모리 	시간
 * 17,144 	121 
 */
public class Solution2005_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, N, triangle[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			// N * N+1 행렬, +1은 첫번째 열을 패딩으로 사용
			triangle = new int[N][N+1];
			triangle[0][1] = 1; // 초기값 꼭짓점 1
			sb.append("#").append(tc).append("\n").append(triangle[0][1]).append("\n");
			for(int i = 1; i < N; i++) {
				for(int j = 1; j <= i+1; j++) {
					// 현재 위치값 = 바로 위 하나 전 인덱스의 값+ 바로 위 동일 인덱스의 값
					triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
					sb.append(triangle[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
