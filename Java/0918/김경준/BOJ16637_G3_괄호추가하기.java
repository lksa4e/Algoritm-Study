import java.io.*;
import java.util.*;

/**
 * G3 BOJ 16637 괄호 추가하기 :
 * 메모리 : 14224kb 시간 : 128ms 
 * 
 * 앞 or 뒤에서부터 시작하는 그리디한 방법을 고려했을때 반대쪽에서 우선 실행되는 () 연산을 알 방법이 없다
 * 태희쌤이 말씀해주신 것처럼 가능한 모든 경우의 수를 고려하고 그 중 최대값을 구해야 하는 문제
 * 
 * 문자열의 길이가 19까지이므로 숫자의 개수는 최대 10개
 * 
 * ==> 괄호 숫자가 증가할때마다 1개의 숫자가 없어지는 효과
 * ==> 숫자의 개수가 N개일 때 괄호의 최대 개수는 N/2개
 * 괄호개수 0개일 때 ==> 1
 * 괄호개수 1개일 때 ==> (n-1)C1
 * 괄호개수 2개일 때 ==> (n-2)C2
 * 괄호개수 3개일 때 ==> (n-3)C3
 * 
 * 모든 경우의 수를 계산해도 문제 없음
 */

public class BOJ16637_G3_괄호추가하기 {
	static int N, answer = Integer.MIN_VALUE;
	static String str;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		dfs(str.charAt(0)-'0',0);
		System.out.println(answer);
	}
	// sum - 지금까지의 누적합, idx - 계산해야하는 위치
	static void dfs(int sum, int idx) {
		if(idx == N-1) {
			answer = Math.max(answer, sum);
			return;
		}
		// 그냥 단순계산 -> a x b x c 일때 a x b만 계산하고 다음 dfs로 넘김
		if(idx < N-2) {
			int calc_num = calc(sum, str.charAt(idx+1), str.charAt(idx+2)-'0');
			dfs(calc_num, idx+2);
		}
		// 뒤쪽괄호 -> a x b x c 일때 a x (b x c)를 계산하고 다음 dfs로 넘김
		if(idx < N-4) {
			int right_sub = calc(str.charAt(idx+2)-'0', str.charAt(idx+3), str.charAt(idx+4)-'0');
			int calc_num = calc(sum, str.charAt(idx+1), right_sub);
			dfs(calc_num, idx + 4);
		}
	}
	static int calc(int num1, char sign, int num2) {
		switch(sign){
		case '+': return num1 + num2;
		case '-': return num1 - num2;
		case '*': return num1 * num2;
		}
		return 0;
	}
}
