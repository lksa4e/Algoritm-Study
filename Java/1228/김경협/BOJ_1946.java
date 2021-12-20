import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 1946번 신입 사원
 * 
 * 그리디
 * 
 * 배열을 신입 사원 수 만큼 생성해서 index를 첫 번째 성적,
 * 해당 index의 값을 두 번째 성적으로 두었다.
 * 
 * 첫 번째 인덱스부터 출발해서 현재 min보다 value가 작다면 min을 갱신한다. 
 * min을 갱신한다는 건, 현재 성적이 최소한의 기준을 넘겼다는 것이기 때문에
 * cnt를 올려준다.
 * 
 * 301256KB	864ms
 */

public class BOJ_1946 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] scoreList = new int[N+1];	// index를 1번부터 출발하기 위해서 N+1 배열 생성
			
			int min = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int score1 = Integer.parseInt(st.nextToken());
				int score2 = Integer.parseInt(st.nextToken());
				scoreList[score1] = score2;	// 첫 번째 성적을 index로, 두 번째 성적을 value로 둔다.
			}
			
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if(scoreList[i] < min) {	// min을 두고, 두 번째 성적이 min보다 작다면 갱신,
					cnt++;
					min = scoreList[i];
				}
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
		
	}

}
