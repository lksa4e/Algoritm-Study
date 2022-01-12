import java.io.*;
import java.util.*;

/**
 * [0112] BOJ 12865 평범한 배낭
 * DP
 * 
 * sol)
 * 전형적인 DP 문제, 태희쌤께서 풀어주신 문제!
 * 최대 무게가 1부터 K일때까지 물건 하나씩을 추가할 때마다 최대 가치를 갱신하여 저장한 2차원 DP 배열을 만든다.
 * DP 점화식은 다음과 같다
 * 	  DP[i][j] = max(DP[i-1][j], DP[i-1][현재 최대 무게 - 현재 물건 무게])
 * 
 * 시행착오)
 * DP 테이블 작성 시 단순히 최대 무게가 현재 무게인 경우부터만 채워나가서 틀림
 * 이전 무게의 가치 중 (현재 최대 무게 - 현재 무게) 값을 사용하는 것을 잊지 말아야 했다.
 *
 */

public class BOJ_12865_평범한배낭 {
	static int N, K, answer;
	static int[][] stuff, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 물건들의 무게와 가치를 모두 입력받은 뒤 무게를 오름차순으로 정렬
		stuff = new int[N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			stuff[i][0] = Integer.parseInt(st.nextToken());
			stuff[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(stuff, (s1, s2) -> s1[0]-s2[0]);
		
		// DP 테이블 채운 뒤 최대 가치 출력
		dp = new int[N][K+1];
		makeDPTable();
		System.out.println(answer);
	}
	
	// DP 테이블 채우기
	private static void makeDPTable() {
		// DP 점화식 초기 값 설정(최소 무게의 물건 DP 테이블 채우기)
		int minValue = stuff[0][1];
		for (int i=stuff[0][0]; i<=K; i++) {
			dp[0][i] = minValue;    // 최소 무게 물건의 해당 무게부터 가방이 들 수 있는 최대 무게까지 최소 무게 물건의 가치로 초기화
			answer = minValue;      // 물건이 1개일 경우 최소 무게 물건 가치가 정답이 됨(단, 최대 들 수 있는 무게 이내에서만)
		}
		
		// 물건이 2개 이상일 경우
		for (int i=1; i<N; i++) {
			int curWeight = stuff[i][0];
			int curValue = stuff[i][1];
			
			for (int j=1; j<=K; j++) {
				// 최대 들 수 있는 무게가 현재 물건 무게보다 작으면 무조건 이전 물건의 최대 가치로 저장
				if (j<curWeight) dp[i][j] = dp[i-1][j];
				// 현재 물건 무게를 들 수 있어진다면 이전 물건의 최대 가치와 현재 무게 물건을 이전 무게 물건과 함께 들었을때 가치 합을 비교하여 저장
				else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-curWeight]+curValue);
				answer = Math.max(answer, dp[i][j]);
			}
		}
	}

}
