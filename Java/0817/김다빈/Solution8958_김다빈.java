package P0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** 
 * 백준 8958번 OX퀴즈 
 * 풀이 : O이면 연속된 만큼 결과에 더하고 X면 점수 0으로 초기화
 * 
 * 15788KB	164ms
 */
public class Solution8958_김다빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int test_case=0;test_case<N;test_case++) {
			char[] input = br.readLine().toCharArray();
			
			int result = 0;
			int score = 0;
			for(int i=0;i<input.length;i++) {
				if(input[i] == 'O') {
					result += ++score;
				} else {
					score = 0;
				}
			}
			
			sb.append(result+"\n");
		}
		
		System.out.println(sb);
	}

}
