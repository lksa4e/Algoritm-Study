import java.io.*;
import java.util.*;

/**
 * 백준 16637번 괄호 추가하기
 * 
 * 풀이 : DFS
 * 
 * 괄호를 묶은 연산과 안 묶은 연산 모두 수행하여 최댓값 찾기!
 * 
 * 이전 연산자로 연산해야하기 때문에 가장 첫번째 인덱스의 연산자를 +로 설정해주어야 한다.
 * 
 * 14256KB	124ms
 */
public class Main16637_괄호추가하기 {
	
	static int N, max = Integer.MIN_VALUE;
	static char[] input;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		input = br.readLine().toCharArray();
		
		dfs(0, 0);
		
		System.out.println(max);
	}

	private static void dfs(int index, int curResult) {
		if(index >= N) {
			max = Math.max(max, curResult);
			return;
		}
		
		// 가장 첫 인덱스의 바로 직전 연산자 +로 설정 
		char operator = '+';
		if(index != 0) operator = input[index-1];
		
		// 괄호로 묶기
		if(index + 2 < N) {
			int next = calc(input[index]-'0', input[index+1], input[index+2]-'0');
			dfs(index + 4, calc(curResult, operator, next));
		}
		
		// 괄호로 안 묶기 (이전 결과에 연산)
		dfs(index + 2, calc(curResult, operator, input[index] - '0'));
	}

	private static int calc(int a, char operator, int b) {
		int result = 0;
		switch(operator) {
		case '+':
			result = a + b; break;
		case '-':
			result = a - b; break;
		case '*':
			result = a * b; break;
		}
		return result;
	}

}
