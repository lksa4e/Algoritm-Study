import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [0817] BOJ 8958 OX퀴즈
 *
 * sol) 누적 합
 * tc) O(N)
 */

public class BOJ8958 {
	static int score;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) {
			score = 0;     // 테케 별 총점
			calculateScore(br.readLine().toCharArray());     // 누적 점수 계산
			System.out.println(score);
		}
		
	}
	
	// 누적 점수 계산 메서드
	private static void calculateScore(char[] quiz) {
		int accumulation = 0;
		
		for (char q : quiz) {
			if (q=='O') score += ++accumulation;    // 맞은 문제 : 현재까지 누적해서 맞은 개수를 총점에 더함
			else accumulation = 0;                  // 틀린 문제 : 누적 점수 초기화
		}
	}

}
