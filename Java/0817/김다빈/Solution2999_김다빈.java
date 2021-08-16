package P0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 2999번 비밀 이메일 
 * 풀이 : 가장 큰 R은 N%R==0이면서 루트 N에 가장 가까운 수 
 * 첫번째 열부터 마지막 열까지 순차적으로 R*C 행렬 채우고
 * 첫번재 행부터 마지막 행까지 순차적으로 출력 
 * 
 * 14112KB	120ms
 */
public class Solution2999_김다빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] mes = br.readLine().toCharArray();
		int N = mes.length;
		
		// 가장 큰 R 구하기
		int R = (int) Math.sqrt(N);
		while(N%R != 0) {
			R--;
		}
		int C = N/R;
		
		// 첫번째 열부터 마지막 열까지 순차적으로 입력 
		char[][] result = new char[R][C];
		int cnt = 0;
		for(int c=0;c<C;c++) {
			for(int r=0;r<R;r++) {
				result[r][c] = mes[cnt++];
			}
		}
		
		// 첫번째 행부터 마지막 행까지 순차적으로 출력 
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				sb.append(result[r][c]);
			}
		}
		
		System.out.println(sb);
	}

}
