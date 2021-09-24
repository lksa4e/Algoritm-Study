import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * BOJ 16637번 괄호 추가하기
 * 
 * 1. 수식이 N일 때의 괄호 개수 operCnt를 구하고, 괄호 개수 0개부터 operCnt까지의 조합을 넥퍼로 구한다. 
 * 2. 연산자와 피연산자를 따로따로 배열에 저장하는데, 이 때 괄호의 위치에 따라서 미리 계산하여 저장한다.
 * 3. 배열에서 하나씩 빼면서 계산한다.
 * 
 * 배열 인덱스 계산하고 버그잡느라 엄청 오래 걸린 문제.
 * 다풀고 나서 생각난건데 그냥 리스트 쓸걸 그랬다.
 * 
 * 14208KB	124ms
 */

public class BOJ_16637 {
	static int N,operCnt;
	static char[] exp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		operCnt = N/2;
		exp = br.readLine().toCharArray();
		
		int bracket = (operCnt + 1) / 2;
		int[] operFlag = new int[operCnt];
		long max = Integer.MIN_VALUE;
		
		if(N == 1) {
			System.out.println(exp[0]);
			return;
		}
		
		for (int i = 0; i <= bracket; i++) {
			Arrays.fill(operFlag, 0);
			int fillEnd = i;
			while(fillEnd > 0) operFlag[operCnt - fillEnd--] = 1;
			
			do {
				if(isCascade(operFlag, 1)) continue;
				
				// 수식 계산
				max = Math.max(max, getResult(operFlag, i));
				
			} while(np(operFlag));
		}
		
		System.out.println(max);
	}
	
	static long getResult(int[] operFlag, int brkCnt) {
		// 괄호 처리된 값을 피연산자 배열과 연산자 배열에 넣어줌(괄호가 몇개냐에 따라 배열의 길이가 달라짐)
		Long[] operand = new Long[operCnt + 1 - brkCnt]; 
		char[] operator = new char[operCnt - brkCnt]; 
		
		// operIdx => 연산자들을 따라 움직이는 연산자 인덱스
		for (int i = 0, operIdx = 0; i < operCnt; i++, operIdx++) {
			// expIdx => 오리지널 수식을 따라 움직이는 수식 인덱스
			int expIdx = i * 2 + 1;
			
			if(operFlag[i] == 0) {
				operand[operIdx] = (long) (exp[expIdx-1] - '0');
				operator[operIdx] = exp[expIdx];
				if(i == operCnt - 1)	// 맨 끝값 넣어주기
					operand[operIdx + 1] = (long) (exp[N-1] - '0');
			} else {
				// 괄호와 괄호 안의 수식 통째로 넣기
				// 괄호 연산자 다음에는 중복해서 괄호가 올 수 없으므로 idx+2(연산자)까지 넣어주고
				// 연산자 index는 하나 더 증가시켜주기
				operand[operIdx] = calc(exp[expIdx-1] - '0', exp[expIdx], exp[expIdx+1] - '0');
				if(i != operCnt - 1) {	// 맨 마지막 연산자일 경우 범위 벗어나기 때문에 빼주기
					operator[operIdx] = exp[expIdx+2];
					// 맨 마지막 바로 앞 연산자일 경우 맨 끝까지 처리해주기
					if(i == operCnt - 2) {
						operand[operIdx + 1] = (long) (exp[expIdx+3] - '0');
					}
				}
				i++;
			}
		}
				
		// 배열에 저장된 값들 계산하기
		long result = operand[0];
		for (int j = 0; j < operCnt - brkCnt; j++) {
			result = calc(result, operator[j], operand[j+1]);
		}
		return result;
	}
	
	static long calc(long op1, char operator, long op2) {
		switch(operator) {
			case '+':
				op1 += op2;
				break;
			case '-':
				op1 -= op2;
				break;
			case '*':
				op1 *= op2;
				break;
		}
		return op1;
	}
	
	static boolean isCascade(int[] arr, int n) {
		int cascCnt = 0;
		for (int i = 0; i < operCnt; i++) {
			if(arr[i] == 1) cascCnt++;
			else cascCnt = 0;
			
			if(cascCnt == 2) return true;
		}
		return false;
	}

	static boolean np(int[] arr) {
		int i = operCnt - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) i--;

		if (i == 0) return false;

		int j = operCnt - 1;
		while (arr[i - 1] >= arr[j]) j--;
		swap(i - 1, j, arr);

		int k = operCnt - 1;
		while (i < k) swap(i++, k--, arr);
		return true;
	}

	static void swap(int i, int j, int[] arr) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
