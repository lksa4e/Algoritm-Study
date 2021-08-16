import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0817] BOJ 1592 영식이와 친구들
 *
 * sol) 회전문제...?
 * tc) O(N)
 */

public class BOJ1592 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int player = 1;
		int pass = 0;
		
		// 공을 최초로 2번 받을때까지 몇 번 던지게 되는지 카운트(즉, 1턴이 돌아오기까지 던지는 횟수)
		while(true) {
			player = (player + L) % N;    // 다음 순서에게 던짐
			pass++;
			
			if (player == 1) break;       // 1턴 종료
		}
		
		pass = M == 1 ? 0 : pass;     // 1번 받을때까지라면 0번 던지면 됨
		
		System.out.println((M-1) * pass);
	}

}
