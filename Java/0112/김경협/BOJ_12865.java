import java.io.*;
import java.util.*;

/*
 * BOJ 12865번 평범한 배낭
 * 
 * 0-1 knapsack 문제.
 * 오랜만에 풀어서 개념이 헷갈리고 어려워서 인터넷으로 공부하고 풀었다.
 * 
 * 53776KB	192ms
 */

public class BOJ_12865 {

	static int N, K, dp[][], weight[], value[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		weight = new int[N+1];
		value = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1][K+1]; // 1번째 행 -> 아무것도 안 넣었을 때
		
		for(int i = 1; i <= N; i++) {	// i는 1~i번째 물건까지 넣는 경우를 의미한다.
			
			for(int currW = 1; currW <= K; currW++) {	// currW는 넣을 수 있는 무게를 의미한다.
				if(weight[i] <= currW) {	// i 번째 물건의 무게가 현재 들 수 있는 무게보다 작거나 같은 경우,
					// 이 경우 원래 가방에 있던 것들의 value와, 다 빼고 i번째 물건+남는무게에 해당하는 value의 값을 비교해서 더 좋은걸 넣는다.
					dp[i][currW] = Math.max(dp[i-1][currW], value[i] + dp[i-1][currW-weight[i]]);
				} else
					dp[i][currW] = dp[i-1][currW];
			}
		}
		System.out.println(dp[N][K]);
	}

}
