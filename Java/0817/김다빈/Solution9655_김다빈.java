package P0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** 
 * 백준 9655번 돌게임  
 * 풀이 : 계산했을 때 N이 홀수면 상근이가, 짝수면 창영이가 이긴다.
 * 
 * 14304KB	132ms
 */
public class Solution9655_김다빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if(N%2==1) System.out.println("SK");
		else System.out.println("CY");
	}

}
