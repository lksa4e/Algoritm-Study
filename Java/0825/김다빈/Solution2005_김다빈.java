import java.io.*;
import java.util.*;

/**
 * SW Expert 2005번 파스칼의 삼각형 
 *
 * 풀이 : 2차원 배열 이용
 * 1. int[N][N] 2차원 배열을 생성 
 * 2. 먼저 첫번째 열을 1로 초기화 한 후, 2번째 열부터 행의 크기만큼 (왼쪽 위, 바로 위)의 합 저장 
 * 3. 저장한 행을 바로 출력 
 * 
 * 16,080 kb	113 ms
 */
public class Solution2005_김다빈 {
	
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input_2005.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			sb.append("#"+test_case+"\n");
			
			int N = Integer.parseInt(br.readLine());
			int[][] pascal = new int[N][N];
			
			for(int i=0;i<N;i++) {	// 첫번째 열의 값들을 1로 초기화 
				pascal[i][0] = 1;
			}
			
			for(int r=0;r<N;r++) {
				for(int c=1;c<=r;c++) {	// r과 값이 같을 때까지 왼쪽 위와 위쪽의 합으로 값 갱신 
					pascal[r][c] = pascal[r-1][c-1] + pascal[r-1][c];
				}
				
				for(int c=0;c<=r;c++) {	// 값 출력 
					sb.append(pascal[r][c]+" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
