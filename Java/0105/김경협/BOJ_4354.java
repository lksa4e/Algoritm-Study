import java.io.*;

/*
 * BOJ 4354번 문자열 제곱
 * 
 * KMP
 * 
 * KMP의 실패 함수를 사용해서 풀었다.
 * 실패 함수를 통해 가장 큰 공통 접두,접미사를 찾고
 * 이를 통해 가장 작은 반복 문자열을 찾았다.
 * 
 * 	97168KB	452ms
 */

public class BOJ_4354 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str;
		while(!(str = br.readLine()).equals(".")) {
			sb.append(solve(str)).append("\n");
		}
		System.out.println(sb);
	}

	private static int solve(String str) {
		int strLen = str.length();
		int[] pi = new int[strLen];	// KMP 실패함수
		
		for(int i = 1,j=0; i < strLen; i++) { // KMP 실패함수로 가장 큰 반복되는 접두,접미사 찾기
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = pi[j - 1];
			}
			
			if(str.charAt(i) == str.charAt(j)) {
				pi[i] = ++j;
			}
		}
		// pi[strLen - 1]이 가장 큰 겹치는 접두, 접미사이기 때문에 이걸 len에서 빼주면 가장 작은 반복되는 문자열 길이이다.
		int minRepeat = strLen - pi[strLen - 1];
		
		// 문자열이 홀수 일 때, 문자열 제곱이 될 수 없으므로 1을 반환
		// 짝수일 때, 전체 길이를 반복 문자열 길이로 나눈 값을 반환한다.
		return strLen % minRepeat == 0 ? strLen / minRepeat : 1;
	}

}
