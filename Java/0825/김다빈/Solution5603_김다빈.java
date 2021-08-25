import java.io.*;
import java.util.*;

/**
 * SW Expert 5603번 건초더미
 *
 * 풀이 : 구현?
 * 1. 통일해야하는 건초더미의 크기는 모든 건초 더미 크기의 평균 값
 * 2. 최소 이동 횟수는 모든 (건초더미 - 평균값)의 합을
 *    한 개의 건초를 이동할 때 2개의 건초더미에 영향을 주므로 절반으로 나눈 값! 
 * 
 * 23,272 kb	134 ms
 */
public class Solution5603_김다빈 {
	
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input_5603.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] input = new int[N];
			int avg = 0;	// 같게 만들어야하는 건초더미의 크기 
			int result = 0;	// 최소 이동 횟수 
			
			for(int i=0;i<N;i++) {
				input[i] = Integer.parseInt(br.readLine());
				avg += input[i];
			}
			avg /= N;
			
			for(int i=0;i<N;i++) {	// 모든 건초더미 크기에서 평균값을 빼준 합 구하기 
				result += Math.abs(input[i]-avg);
			}
			
			sb.append("#"+test_case+" "+(result/2)+"\n");	// 이동횟수/2 출력 
		}
		
		System.out.println(sb);
	}

}
