import java.io.*;
import java.util.*;

/**
 * SW Expert 1966번 숫자를 정렬하자 
 *
 * 풀이 : Arrays.sort 이용
 * 숫자를 입력받고.. 정렬하고.. 출력한다..
 * 
 * 19,312 kb	105 ms
 */
public class Solution1966_김다빈 {
	
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input_1966.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] number = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(number);	// 오름차순 정렬 
			
			sb.append("#"+test_case+" ");
			for(int num : number) sb.append(num+" ");
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
