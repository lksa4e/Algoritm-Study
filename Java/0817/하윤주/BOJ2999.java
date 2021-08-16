import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [0817] BOJ 2999 비밀 이메일
 *
 * sol) 문자열, 2차원 배열 연습
 * tc) O(N)
 */

public class BOJ2999 {
	static String message;
	static int N, R, C;
	static char[][] matrix;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 메시지 입력
		message = br.readLine();
		N = message.length();
		
		findDivisor();     // 약수 구하기(R, C 초기화)
		decode1();        // 메시지 복호화 1단계
		decode2();        // 메시지 복호화 2단계
		
		System.out.println(sb);
	}

	private static void findDivisor() {
		for (int i=1; i<=N/2; i++) {
			int quotient = N / i;      // 몫
			int remainder = N % i;     // 나머지
			
			if (remainder == 0) {      // 약수면 R과 C 설정
				int r = Math.min(quotient, i);
				int c = Math.max(quotient, i);
				
				if (r > R) {    // R을 최대로
					R = r;
					C = c;
				}
			}
		}
		
	}

	// 메시지 복호화 1단계(열기준으로 행 접근)
	private static void decode1() {
		matrix = new char[R][C];
		int idx = 0;
		
		for (int c=0; c<C; c++) {
			for (int r=0; r<R; r++) matrix[r][c] = message.charAt(idx++);
		}
	}
	
	// 메시지 복호화 2단계(행기준으로 열 접근)
	private static void decode2() {
		sb = new StringBuilder();
		
		for (int r=0; r<R; r++) {
			for (int c=0; c<C; c++) sb.append(matrix[r][c]);
		}
		
	}
}
