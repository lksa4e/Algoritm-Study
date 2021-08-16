import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [0817] BOJ 2851 슈퍼 마리오
 *
 * sol) for문 연습
 * tc) O(N)
 */

public class BOJ2851 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int score = 0;
		int curAbs = Integer.MAX_VALUE;    // 현재까지의 절댓값
		
		for (int i=0; i<10; i++) {
			int mushroom = Integer.parseInt(br.readLine());
			
			score += mushroom;    // 우선 점수를 더하고
			
			if (Math.abs(100-score) <= curAbs) curAbs = Math.abs(100-score);   // 절댓값 구한 뒤 최솟값이면 갱신
			else {                // 아니면 원상복구 후 반복 탈출
				score -= mushroom;
				break;
			}
			
		}
		
		System.out.println(score);

	}

}
