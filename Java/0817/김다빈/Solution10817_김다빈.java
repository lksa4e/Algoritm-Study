package P0816;

import java.util.Arrays;
import java.util.Scanner;

/** 
 * 백준 10817번 세 수 
 * 풀이 : sort해서 중간 값 출력 
 * 
 * 17760KB	224ms
 */
public class Solution10817_김다빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] input = new int[3];
		for(int i=0;i<3;i++) {
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input);
		System.out.println(input[1]);
	}

}
