package BaekOJ.study.date0921;

/*
 * 수열이 주어졌을 때, 인접한 두 수를 합칠 수 있고 전체를 하나의 수로 합하는 최솟값을 구하는 문제이다.
 * 학교에서 DP에 대해 배울때 대표 DP문제라고 배웠던 덕분에 풀 수 있었던 문제...
 * 그 때 교수님께서 이 알고리즘을 O(N^3)에서 O(N^2)로 줄일 수 있는 크누스최적화라는 아주 놀라운 놈이 있다고 하셨음
 * 그때 설명을 해주셔서 당시에는 이해를 좀 했던 것 같은데 이번에 이 문제를 풀면서 다시 보니까 당최 뭔지 모르겠더라..
 * 
 * 메모리 	시간
 * 31036	620
 */

import java.io.*;
import java.util.*;

public class BaekOJ11066_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, K, file[], sum[], dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			K = Integer.parseInt(br.readLine());
			file = new int[K];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < K; i++) file[i] = Integer.parseInt(st.nextToken());
			
			// 1부터 K까지 각 구간의 총합, i부터 j까지 단순한 합을 빠르게 구하기 위해서 
			// 이 배열이 DP점화식을 세울 수 있게 만드는 핵심 
			sum = new int[K+1];
			for(int i = 0; i < K; i++) sum[i+1] = sum[i]+file[i];
			
			dp = new int[K][K];
			
			// 1부터 K까지 1씩 범위를 늘려가며 최종적으로 K까지 도달하면 결과값을 찾을 수 있음. 
			// 40 30 30 50의 최솟값을 찾으려면 모든 구간의 최솟값을 찾아야 하며 
			// x는 구간의 크기이다.
			
			// x = 1 : 40 / 30 / 30 / 40
			// x = 2 : 40 30 / 30 30 / 30 50 
			// x = 3 : 40 30 30 / 30 30 50
			// x = 4 : 40 30 30 50
			// 여기서 x = 3일 때를 보면, 40 30 30 의 최소합은 
			// 40 30 / 30 이 최소인지, 40 / 30 30 이 최소인지를 알아야 하므로
			// 미리 x = 1 일 때 구해 놓은 40, 30 
			// 미리 x = 2 일 때 구해 놓은 40 30의 합과 30 30의 합을 이용하여 값을 구한다.
			// 같은 매커니즘으로 x = 100이 되더라도 앞서서 
			// 미리 구해놓은 x보다 작은 구간들 x = 1 ~ 99까지의 구간 최소합을 이용해서 (1+99 , 2+98, 3+97,,) 결과를 찾을 수 있다.
			//
			// i는 최소합을 찾을 시작점이고 j는 i+x, 즉 최소합을 찾을 마지막점이다.
			// 그리고 i부터 j사이에 div 변수를 둬서 
			// 위에서 보았듯이 40 30 30의 최소합이 40 / 30 30 인지, 40 30 / 30인지 
			// x를 x보다 작은 구간으로 분할하여, 미리 구해놓은 해당 구간의 값을 이용해 결과값을 찾는다.
			
			for(int x = 1; x <= K; x++) { // x는 파일을 합치는 범위 
				for(int i = 0; i < K-x; i++) { // i는 파일을 합치는 시작점 
					int j = i+x; // j는 파일을 합치는 마지막점 
					dp[i][j] = Integer.MAX_VALUE; //항상 최대값으로 초기화 한 뒤 최소합을 찾
					for(int div = i; div < j; div++) { // 미리 구해놓은 구간들로 범위를 분할  
						// 1 / 2 3 4 ,   1 2 / 3 4,   1 2 3 / 4 로 탐색 
						dp[i][j] = Math.min(dp[i][j], (dp[i][div]+dp[div+1][j]) + (sum[j+1]-sum[i]));
					}
				}
			}
			// 0 ~ K-1까지의 최소 구간합 
			sb.append(dp[0][K-1]).append("\n");
		}
		System.out.println(sb);
	}
}