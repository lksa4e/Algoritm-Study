import java.io.*;
import java.util.*;

public class Main11049 {
	
	/*
	 * a[i][0] = i번째 행렬의 행
	 * a[i][1] = i번째 행렬의 열
	 * dp[i][j] = i번째 ~ j번째 행렬 곱셈연산 최소
	 * dp[i][j] = dp[i][k] + dp[k+1][j] + (i~k까지 곱한 행렬) x (k+1 ~ j까지 곱한행렬)의 곱셈연산 횟수
	 * dp[i][j] = Min(dp[i][j], dp[i][k] + dp[k+1][j] + (a[i][0]*a[k][1]*a[j][1]))
	 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		int[][] a = new int[N][2];
		int[][] dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a[i][0] = Integer.parseInt(st.nextToken());
			a[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N - i; j++) {
				dp[j][j + i] = Integer.MAX_VALUE;
				for (int k = 0; k < i; k++) {
					int cost = dp[j][j+k] + dp[j+k+1][j+i] + a[j][0] * a[j+k][1] * a[j+i][1];
					dp[j][j + i] = Math.min(dp[j][j+i], cost);
				}
			}
		}
		
		System.out.println(dp[0][N - 1]);

	}

}
