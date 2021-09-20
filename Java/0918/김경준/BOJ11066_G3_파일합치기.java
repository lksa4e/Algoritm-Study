import java.io.*;
import java.util.*;

/**
 * G3 BOJ 11066 파일 합치기 :
 * 메모리 : 29628KB 시간 : 564ms  
 * 
 * 너무너무너무 어려웠던 문제...
 * 파일을 합칠 수 있는 모든 경우에 대해 최솟값을 구해야 한다.
 * dp[i][j] => i ~ j 장을 합치는데 드는 최소 비용으로 두고 dp 방식으로 문제를 해결한다.
 * 
 * dp[i][j] = min<i<=k<=j> (dp[i][k] + dp[k+1][j]) + cost[i][j]의 점화식을 만족한다.
 * 예를 들어 보는것이 이해가 잘됨
 * 1 ~ 4 장까지 합치는데 드는 최소비용은 아래와 같다.
 * dp[1][4] = 아래 중 최소값
 *            dp[1][1] + dp[2][4] + cost[1][4];
 *            dp[1][2] + dp[3][4] + cost[1][4];
 *            dp[1][3] + dp[4][4] + cost[1][4];
 */

public class BOJ11066_G3_파일합치기 {
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
			
			
			// 1st for문 -> dp[i][j]를 구하기 위해서는 i ~ j 사이의 모든 dp값이 갱신이 되어있어야함
			//             if 1 ~ 6 의 최소비용을 구하고 싶으면 1 ~ 5, 2 ~ 6 등 중간의 최소비용을 알아야함
			//             1 ~ K 까지 모든 간격에 대해 수행하도록 하는 for문
			
			// 2nd for문 -> 1st for문에서 구한 간격을 이용하여 i, j 를 정하는 for문 
			//             if 간격이 2인 경우 1 ~ 2, 2 ~ 3, 3 ~ 4 간격의 dp갱신을 수행하도록 함
			
			// 3rd for문 -> i,j에 대한 dp값 갱신을 수행한다.
		
			for(int gap = 1; gap < K; gap++) {
				for(int start = 1; start + gap <= K; start++) {
					int end = start + gap;
					dp[start][end] = 99999999;
					
					for(int mid = start; mid < end; mid++) {
						dp[start][end] = Math.min(dp[start][end], 
								dp[start][mid] + dp[mid + 1][end] + sum[end]-sum[start-1]);
					}
				}
			}
			System.out.println(dp[1][K]);
		}
	}
}
