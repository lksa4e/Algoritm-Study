import java.io.*;
import java.util.*;

/**
 * SW Expert 1234번 비밀번호 
 *
 * 풀이 : Stack 이용
 * 1. input으로 들어오는 값을 모두 Stack에 담는다.
 * 2. input과 output의 top이 같으면 output pop
 *    다르면 output push
 * 3. 비교를 위해 output에 -1을 추가해주고 마지막 출력 시 제외 
 *
 * 18,644 kb	111 ms
 */
public class Solution1234_김다빈 {

	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input_1234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			char[] ch = st.nextToken().toCharArray();
			
			Stack<Integer> input = new Stack<Integer>();
			for(char c : ch) {
				input.add(c - '0');
			}
			
			Stack<Integer> output = new Stack<Integer>();
			output.add(-1);	// 초기 비교 값 -1 추가 
			while(!input.isEmpty()) {
				int num = input.pop();
				
				if(num != output.peek()) {	// 다르면 add 
					output.add(num);
				} else {	// 같으면 pop 
					output.pop();
				}
			}
			
			sb.append("#"+test_case+" ");
			while(!output.isEmpty()) {
				sb.append(output.pop());
			}
			sb.setLength(sb.length()-2);	// 초기 -1 제외하고 출력 
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
