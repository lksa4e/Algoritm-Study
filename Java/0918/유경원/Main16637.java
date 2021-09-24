import java.io.*;
import java.util.*;

public class Main16637 {
	/*
	 * 괄호로 묶을때 안묶을때 나눠서 dfs 진행
	 * 괄호계산할때는 연산자 1개만 포함되므로 수식배열에서 3개단위로 묶어서 계산
	 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, ans;
	static char[] expression;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		expression = br.readLine().toCharArray();
		ans = Integer.MIN_VALUE;
		cal(0,0);
		System.out.println(ans);

	}

	private static int op(int a, int b, char oper) {
		int result = a;
		switch (oper) {
			case '+': result += b; break;
			case '*': result *= b; break;
			case '-': result -= b; break;
		}
		return result;
	}

	private static void cal(int idx, int value) {
		if (idx >= N) {
			if (ans < value) ans = value;
			return;
		}
		// 첫번째면 더하기
		char oper = (idx == 0) ? '+' : expression[idx - 1];

		// 괄호 계산, 수식배열에서 괄호계산에 3개, 연산에 1개 소모했으니 idx+4
		if (idx + 2 < N) {
			int temp = op(expression[idx] - '0', expression[idx + 2] - '0', expression[idx + 1]);
			cal(idx + 4, op(value, temp, oper));
		}
		// 괄호 안 묶었으면 연산에 1개, 다음 값에 1개 이므로 idx+2
		cal(idx + 2, op(value, expression[idx] - '0', oper));
	}

}