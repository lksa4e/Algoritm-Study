import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * [0817] BOJ 2810 컵홀더
 *
 * sol) if-else 연습
 * tc) O(N)
 */

public class BOJ2810 {
	static int N, cnt;
	static boolean[] holder;
	static String seats;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		seats = br.readLine();
		
		compressSeats();        // 커플석 문자열을 하나의 문자로 합침
		holder = new boolean[sb.length()+1];    // 컵홀더 사용 여부 배열 생성(컵홀더 수 = 좌석 수 + 1)
		
		placeCups();            // 좌석 별로 컵홀더를 사용하고 이를 기록
		countPlacedCups();      // 컵홀더를 사용한 사람 수 카운트
		
		System.out.println(cnt);
		
	}

	// 커플석 문자열 압축(LL -> L)
	private static void compressSeats() {
		int i = 0;
		while(i<N) {
			sb.append(seats.charAt(i));
			if (seats.charAt(i) == 'L') i +=2;
			else i++;
		}
	}
	
	// 좌석 별 컵홀더 사용 기록
	private static void placeCups() {
		int size = sb.length();
		
		for (int i=0; i<size; i++) {
			if (sb.charAt(i) == 'S') {     // 일반좌석은 양쪽 중 한 곳에만 두고
				if (!holder[i]) holder[i] = true;
				else if (!holder[i+1]) holder[i+1] = true;
			} else {                       // 커플석은 양쪽 모두에 컵을 배치
				holder[i] = true;
				holder[i+1] = true;
			}
		}
	}
	
	// 전체 사용된 컵홀더 수(컵홀더를 사용한 사람 수) 카운트
	private static int countPlacedCups() {
		for (boolean h : holder) {
			if (h) cnt++;
		}
		return cnt;
	}

}
