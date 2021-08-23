import java.io.*;
import java.util.*;

/**
 * SW Expert 1948번 날짜 계산기  
 *
 * 풀이 : if-else 조건문 다루기?
 * 1. start월과 end월이 같으면 일의 차 계산
 * 2. 다르다면 (start월 날짜) + (start+1 ~ end-1월 날짜) + (end월 날짜)의 합 계산 
 * 
 * 18,340 kb	100 ms
 */
public class Solution1948_김다빈 {
	
	// 월의 마지막 일수를 저장한 배열 (월로 접근하기 위해 0월은 0으로 초기화)
	static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input_1948.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			// 월, 일 순으로 저장 
			int[] start = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int[] end = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int result = 0;
			
			// 월이 같으면 일 빼주기
			if(start[0] == end[0]) {
				result = end[1] - start[1] + 1;
			} else {
				// start 월 날짜 계산 
				result += days[start[0]] - start[1] + 1;
				
				// start+1 월부터 end-1 월까지의 날짜 계산 
				for(int i=start[0]+1;i<end[0];i++) {
					result += days[i];
				}
				
				// end 월 날짜 계산
				result += end[1];
			}
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		
		System.out.println(sb);
	}

}
