import java.io.*;
import java.util.*;

/**
 * SW Expert 4698번 테네스의 특별한 소수
 *
 * 풀이 : 에라토스테네스의 체 알고리즘
 * 1. 에라토스테네스의 체 알고리즘을 적용해 최대 입력값인 10^6의 제곱근까지 소수인지 아닌지 미리 계산
 * 2. A와 B 사이의 값들 중 소수이고 D를 포함하는 특별한 소수를 찾기 위해 num을 String으로 변환하여 contains 함수 사용
 * 
 * 96,628 kb	474 ms
 */
public class Solution4698_김다빈 {
	
	static int max = 1000000;	// 최대 10^6
	// 소수면 0, 소수가 아니면 1 
	static int[] arr = new int[max+1];
	
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input_4698.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		// 최대 10^6까지의 수들을 소수인지 아닌지 미리 계산 
		int limit = (int) Math.sqrt(max);
		arr[1] = 1;	// 1은 소수가 아니다.. 
		for(int i=2;i<=limit;i++) {
			if(arr[i] == 1) continue;
			
			for(int j=i+i;j<=max;j+=i) {	// i의 제곱수 제외
				arr[j] = 1;
			}
		}
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			String D = st.nextToken();
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int result = 0;
			
			for(int i=A;i<=B;i++) {
				if(arr[i]==0 && String.valueOf(i).contains(D)) {	// D를 포함하는 특별한 소수인지 확인 
					result++;
				}
			}
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		
		System.out.println(sb);
	}

}
