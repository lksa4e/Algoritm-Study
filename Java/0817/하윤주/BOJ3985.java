import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0817] BOJ 3985 롤 케이크
 *
 * sol) 배열 및 구현
 * tc) 1000 * 1000
 */

public class BOJ3985 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int expected = 0;            // 최대로 받을 것이라 기대되는 사람
		int result = 0;              // 실제 최대로 받은 사람
		int requireMaximum = 0;      // 최대로 요구한 개수
		int getMaximum = 0;          // 최대로 받은 개수
		
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int[] cake = new int[L+1];   // 케이크 조각 할당 배열
		
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());   // 요구 케이크 시작점
			int to = Integer.parseInt(st.nextToken());     // 요구 케이크 종료점
			
			// 최대로 요구한 사람 체크
			if (to-from > requireMaximum) {
				requireMaximum = to - from;
				expected = i;
			}
			
			// 케이크에 지분 설정
			int cnt = 0;
			for (int j=from; j<=to; j++) {
				if (cake[j] == 0) {    // 최초로 요구한 조각이라면
					cake[j] = i; 
					cnt++;             // 실제로 할당받은 조각 카운트
				}
			}
			
			// 실제로 최대로 받은 사람 체크
			if (cnt > getMaximum) {
				getMaximum = cnt;
				result = i;
			}
		}
		
		System.out.println(expected + "\n" + result);
	}

}
