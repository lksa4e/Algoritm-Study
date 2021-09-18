import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [0918] BOJ 16637 괄호 추가하기
 *  완전탐색, 재귀
 * 
 * sol)
 * 재귀를 이용해 가능한 괄호를 모두 씌워보고(완전탐색) 연산 결과의 최댓값을 출력
 * 이때 괄호는 중첩해서 사용할 수 없으므로 현재 부분 먼저 사용하고 다음부분을 사용하거나 다음부분 먼저 사용하고 현재 부분 사용
 * 
 * -- 유도파트
 * 1. 첫번째 연산자 묶음부터 괄호를 씌워보고 다음 연산자 묶음으로 탐색
 * 2. 위의 경우를 모두 탐색했다면 이번에는 자신의 다음 연산자 묶음을 괄호로 씌워 먼저 계산하고 이 결과에 자신의 연산자 묶음을 계산
 * -- 기저조건
 * 위의 과정을 모든 연산자에 대해 수행했다면 연산 결과 최댓값을 갱신 시도
 * 
 */

public class BOJ_16637_괄호추가하기 {
	static int N, halfN, max = Integer.MIN_VALUE;
	static int[] nums;
	static char[] oper;
	
	// 수식 계산
	private static int calculation(int n1, char o, int n2) {
		int answer = 0;
		if (o=='+') answer = n1+n2;
		if (o=='-') answer = n1-n2;
		if (o=='*') answer = n1*n2;
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		halfN = N/2;                    // 연산자와 피연산자 배열 길이를 위함
		String line = br.readLine();    // 전체 수식
		
		nums = new int[halfN+1];        // 피연산자 배열
		oper = new char[halfN];         // 연산자 배열
		
		int n = 0, o = 0;
		for (int i=0; i<N; i++) {
			if (i%2==0) nums[n++] = line.charAt(i)-'0';    // 피연산자 저장
			else oper[o++] = line.charAt(i);               // 연산자 저장
		}
		
		// dfs를 통해 괄호 완전탐색
		findBracket(0, nums[0]);
		System.out.println(max);
	}

	// 재귀적으로 괄호가 가능한 경우를 완전탐색
	private static void findBracket(int idx, int result) {
		// 기저조건 : 모든 연산자 사용했으면 계산 결과 최댓값을 갱신 시도
		if (idx==halfN) {
			max = Math.max(max, result);
			return;
		}
		
		// 유도파트 : 괄호는 중첩해서 사용할 수 없으므로 현재 연산자 묶음 괄호에 포함시키는 경우와 다음 연산자 묶음을 괄호에 포함시키는 경우로 나눠서 탐색
		// 1) 현재 연산자 묶음을 괄호로 감싸 계산
		findBracket(idx+1, calculation(result, oper[idx], nums[idx+1]));      // 피연산자는 연산자보다 1개 더 많으므로 i번째 피연산자와 i+1번째 피연산자 사이에 존재하는 i번째 연산자로 연산 수행
		
		// 2) 다음 연산자 묶음을 괄호로 감싸 계산
		if (idx+1<halfN) {     // 다음 연산자 인덱스인 idx+1까지가 연산자 배열 길이를 넘어가면 안됨
			int nextResult = calculation(nums[idx+1], oper[idx+1], nums[idx+2]);     // 다음 연산자 묶음 먼저 계산하고
			findBracket(idx+2, calculation(result, oper[idx], nextResult));          // 현재 연산자 묶음에 위 결과를 반영하여 다시 계산
		}
	}
	
}
