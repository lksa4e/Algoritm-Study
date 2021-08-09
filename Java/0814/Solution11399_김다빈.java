package P0810;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 11399번 ATM 
 * @author 김다빈 
 *
 */

public class Solution11399_김다빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// 각 사람이 돈을 인출하는데 걸리는 시간 입력 받기 
		int[] line = new int[N];
		for(int i=0;i<N;i++) {
			line[i] = sc.nextInt();
		}
		
		// N 최대 1000
		// O(N^2) = 1,000,000
		Arrays.sort(line);
		
		int sum = 0;
		// 기다리는 사람 수 * 시간
		for(int i=0;i<N;i++) {
			sum += (N-i) * line[i];
		}
		
		System.out.println(sum);
	}

}
