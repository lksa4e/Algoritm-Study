import java.io.*;

/**
 * [1211] BOJ 2023 신기한 소수
 * 소수, 백트래킹, 재귀, 순열
 * 
 * sol)
 * 유도파트 : 0~9 사이 숫자 중 하나를 선택하여 이전에 선택한 수 뒤에 붙여가며 소수인지 판별한다.
 *          이때, (이전에 선택한 수 * 10 + 현재 선택한 수)를 통해 적절한 자리수의 수를 만들 수 있다.
 * 기저조건 : 총 수의 길이가 N자리가 되면 출력에 덧붙인다.
 * 
 * 시행착오)
 * 소수인지 판별하는 부분에서 미리 에라토스테네스의 체 알고리즘으로 N자리까지의 소수판별 배열을 만들어뒀는데 메모리 초과가 떴다...
 * 그냥 매 수를 선택할 때마다 해당 수의 제곱근 범위까지 for문 돌며 작은 수의 배수가 되는지를 판단하는 방식으로 소수 판별했더니 풀림. 
 */

public class BOJ_2023_신기한소수 {
	static int N;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		getSurprisingPrimeNums(0, 0);
		System.out.println(answer);
	}

	// 신기한 소수 구하기
	private static void getSurprisingPrimeNums(int len, int num) {
		// 기저조건 : N자리 수가 만들어지면 출력에 붙임
		if (len==N) {
			answer.append(num).append("\n");
			return;
		}
		
		// 유도파트 : (이전 수 * 10 + 현재 수)가 소수인지 판별
		for (int n=0; n<=9; n++) {
			int targetNum = num * 10 + n;
			if (!isPrimeNum(targetNum)) continue;        // 소수가 아니라면 다른 숫자로 바로 판별
			
			getSurprisingPrimeNums(len+1, targetNum);    // 소수라면 다음 자리수 판별을 위해 재귀 탐
		}
	}

	// 소수 판별하기
	private static boolean isPrimeNum(int num) {
		// 0, 1은 소수가 아님
		if (num==0 || num==1) return false;
		
		// 2 이상부터는 제곱근 범위까지 나눠보며 해당 수의 배수가 되는지 확인
		for (int range=(int) Math.sqrt(num), i=2; i<=range; i++) {
			if (num % i == 0) return false;
		}
		return true;
	}

}
