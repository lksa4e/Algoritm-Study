import java.io.*;
import java.util.*;

/**
 * SW Expert 5356번 의석이의 세로로 말해요
 *
 * 풀이 : 문자열 처리?
 * 1. 문자열을 모두 배열에 저장하면서 다 출력했는지 확인하기 위해 크기도 배열에 따로 저장 
 * 2. 주어진 문자열의 크기가 최대 15이므로 최대 15번을 반복하면서 문자열 출력
 * 3. 남아있는 문자가 없을 때 반복 종료 
 * 
 * 18,952 kb	102 ms
 */
public class Solution5356_김다빈 {
	
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input_5356.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			char[][] input = new char[5][];
			int[] flag = new int[5];	// 입력받은 문자열들의 크기 저장 
			
			for(int i=0;i<5;i++) {
				input[i] = br.readLine().toCharArray();
				flag[i] = input[i].length;
			}
			
			sb.append("#"+test_case+" ");
			for(int i=0;i<15;i++) {	// 주어진 최대 문자열의 크기가 15이므로 최대 15번 반복  
				int remain = 0;	// 남아있는 문자 개수 저장 
				
				for(int j=0;j<5;j++) {	// 5개의 문자열을 돌아가면서 출력 
					if(flag[j] > 0) {
						sb.append(input[j][i]);
						flag[j]--;
					}
					remain += flag[j];
				}
				
				if(remain == 0) break;	// 출력할 문자가 없다면 break 
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
