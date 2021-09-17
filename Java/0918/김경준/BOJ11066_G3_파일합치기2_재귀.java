import java.io.*;
import java.util.*;

/**
 * G3 BOJ 11066 파일 합치기 -> 재귀방식 :
 * 메모리 : 22272KB 시간 : 708ms  
 * 
 * 너무너무너무 어려웠던 문제...
 * 파일을 합칠 수 있는 모든 경우에 대해 최솟값을 구해야 한다.
 * dp[i][j] => i ~ j 장을 합치는데 드는 최소 비용으로 두고 dp 방식으로 문제를 해결한다.
 * 
 * 조금 더 직관적이라고 생각하는 재귀 방식의 구현
 * 
 * 간격을 1 ~ 2, 2 ~ 3,,,,
 *      1 ~ 3 , 2 ~ 4,,, 
 * 방식의 바텀업이 아닌
 * 
 * 1 ~ 6 -> 점점 쪼개면서 아래로 내려가는 탑다운 방식의 접근법
 * 물론 아래서부터 위로 타고 올라오면서 갱신이 되는 형태..
 */

public class BOJ11066_G3_파일합치기2_재귀 {
	static int T, K, cost[], sum[], dp[][];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; ++tc) {
			K = Integer.parseInt(br.readLine());
			cost = new int[K+1];
			sum = new int[K+1];
			dp = new int[K+1][K+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= K; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + cost[i];
			}
			
			for(int[] dps : dp) Arrays.fill(dps, Integer.MAX_VALUE);
			
			System.out.println(dfs(1,K));
		}
	}
	static int dfs(int start, int end) {
		// 만약 갱신이 되었다면, 이미 해당 구간의 최소값을 구한것 => 그대로 dp값 활용
		if(dp[start][end] != Integer.MAX_VALUE)
			return dp[start][end];
		
		// 합치는 파일 개수가 1 => 자기 자신은 0
		if(start == end)
			return dp[start][end] = 0;
		
		// 인접한 파일인 경우 => 단순히 cost의 덧셈만으로 계산 가능
		if(start + 1 == end)
			return dp[start][end] = cost[start] + cost[end];
		
		for(int mid = start; mid < end; mid++) {
			int left = dfs(start, mid);  // k 기준에서 왼쪽 구간의 최소값
			int right = dfs(mid + 1, end); // k 기준에서 오른쪽 구간의 최소값
			dp[start][end] = Math.min(dp[start][end], left + right); // 전체 구간의 최소값 갱신
		}
		
		return dp[start][end] += sum[end]-sum[start - 1]; // 파일합칠때는 합친 크기만큼 한번 더 추가해주어야함
	}
}
