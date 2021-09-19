import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 11066번 파일 합치기
 * 
 * 그리디로 풀어보려다 실패함.
 * 다른 패턴이 있을 것 같은데 못 찾겠습니다.
 * 
 * 참고한 블로그: https://m.blog.naver.com/tjdwns0920/221135677693
 */

public class BOJ_11066 {
	static int K, element[];
	
	// 길이 내에서 2개씩 묶어 위치 이동 시키면서 최솟값 찾아내기
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			K = Integer.parseInt(br.readLine());
			element = new int[K];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++)
				element[i] = Integer.parseInt(st.nextToken());
			
			sb.append(dp()).append("\n");
		}
		System.out.println(sb);
	}
	
	static int dp() {
		int[][] dp  = new int[K][K];
		int[]	sum = new int[K];
		
		sum[0] = element[0];
		for (int i = 0; i < K - 1; i++)
			sum[i+1] += sum[i] + element[i+1];
		
		// i,i+1 합치는 경우 저장
		for(int i = 0; i < K-1; i++) dp[i][i+1] = element[i] + element[i+1];
		
		// 1. DP[i][i] = 0
		// 2. DP[i][i+1] = V[i] + V[i+1]
		// 3. DP[i][j] = MIN(DP[i][k]+DP[k+1][j]+SUM(V[i~j]), (단, i < k < j)
		for(int gap = 2; gap < K; gap++){ 
			for(int start = 0; start + gap < K; start++){	
				int end = start + gap;
				dp[start][end] = Integer.MAX_VALUE;	

				for(int mid = start; mid < end; mid++)
					dp[start][end] = Math.min(dp[start][mid] + dp[mid+1][end] + sum(sum, start, end), dp[start][end]);
			}
		}
		// 처음부터 끝까지 구해진 최솟값들의 누적합
		return dp[0][K-1];
	}
	
	public static int sum(int[] sum, int start, int end){
		if(start == 0) return sum[end];
		else return sum[end] - sum[start-1];
	}
}
