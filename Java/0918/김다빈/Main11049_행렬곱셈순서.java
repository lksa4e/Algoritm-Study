import java.io.*;
import java.util.*;

/**
 * 백준 11049번 행렬곱셈순서
 * 
 * 풀이 : DP
 * 
 * 미리 바로 옆 행렬들의 곱셈을 저장하고 작은 단위의 곱셈부터 큰 단위로 올라가는 Bottom-Up 방식으로 구현!
 * DP[i][j] = min(DP[i][j], DP[i][k] + DP[k+1][j] + i*k*j(->행렬 곱)) 점화식을 통해 최솟값 업데이트 
 * 
 * 17320KB	284ms
 */
public class Main11049_행렬곱셈순서 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[][] input = new int[N][2];
		int[][] memo = new int[N][N];
		
		for (int i = 0; i <	N; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());			
		}
		
		for (int i = 0; i < N-1; i++) {	// 바로 옆 행렬들의 값 미리 계산 
			memo[i][i+1] = input[i][0] * input[i][1] * input[i+1][1];
		}
		
		for (int diff = 1; diff < N; diff++) {	// start와 end의 차이 
			for (int start = 0; start < N - diff; start++) {
				int end = start + diff;
				memo[start][end] = Integer.MAX_VALUE;	// 정답이 2^31-1 보다 작거나 같은 자연수이므로 초기값을 int 최댓값으로 설정 가능 
				
				for (int mid = start; mid < end; mid++) { // (start~mid) + (mid+1~end) + (start * mid * end)
					int update = memo[start][mid] + memo[mid+1][end] + input[start][0] * input[mid][1] * input[end][1];
					memo[start][end] = Math.min(memo[start][end], update);
				}
			}
		}
		
		System.out.println(memo[0][N-1]);
	}

}
