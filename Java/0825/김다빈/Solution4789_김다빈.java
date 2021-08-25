import java.io.*;
import java.util.*;

/**
 * SW Expert 4789번 성공적인 공연 기획 
 *
 * 풀이 : 구현?
 * 1. 현재 누적 사람 수가 다음 기립박수 조건과 같거나 많으면, 사람 수 누적합
 * 2. 작다면, 부족한 만큼 고용한 후 누적합 
 * 
 * 18,328 kb	115 ms
 */
public class Solution4789_김다빈 {
	
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input_4789.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			char[] clap = br.readLine().toCharArray();
			
			int result = 0;	// 고용해야할 사람의 수 
			int sum = 0;	// 누적 사람 수 
			
			for(int i=0;i<clap.length;i++) {
				if(sum >= i) {	// i명보다 많거나 같다면 기립박수  
					sum += clap[i]-'0';
				} else {	// 부족한 수만큼 고용한 후 기립박수 
					result += i-sum;
					sum = i + (clap[i]-'0');
				}
			}
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		
		System.out.println(sb);
	}

}
