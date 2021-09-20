import java.io.*;
import java.util.*;

/**
 * 백준 11066번 파일 합치기
 * 
 * 풀이 : DP
 * 
 * 시행착오 1.
 * 챕터를 순서대로 합쳐야한다는 언급이 없어서 정렬해서 풀려다가 테케에서 막히고 그게 아니라는걸 알게됨.
 * 
 * 시행착오 2.
 * 일단 점화식인 DP[i][j] = DP[i][k] + DP[k+1][j] 자체를 생각해내는건 어렵지 않았는데
 * 여기에 그 파일을 합칠 때의 비용을 더하는걸 떠올리기가 쉽지 않았고 구현도..ㅜㅜ 
 * 
 * 31776KB	632ms
 */
public class Main11066_파일합치기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] memo = new int[N+1][N+1];
			int[] input = new int[N+1];
			int[] sum = new int[N+1];	// sum[i]는 0 ~ i까지 책을 합친 비용 
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + input[i];
			}
			
			for (int num = 1; num <= N; num++) {				// 몇 개의 챕터를 하나의 파일로 만들지 결정  
				for (int start = 1; num+start <= N; start++) {	// 어디서부터 합칠지 시작 파일 결정 
					int end = start + num;	// 마지막으로 합칠 파일 
					
					memo[start][end] = Integer.MAX_VALUE;
					for (int index = start; index < end; index++) {
						// index를 기준으로 (start ~ index), (index+1 ~ end) 두 그룹으로 나누어 비용 합산 
						int value = memo[start][index] + memo[index+1][end];
						
						// start ~ end 그룹을 합칠 때 비용도 더해줘야 한다!
						value += sum[end] - sum[start-1];
						memo[start][end] = Math.min(memo[start][end], value);
					}
				}
			}
			
			// 1 ~ N의 챕터를 모두 합친 최소 비용 출력 
			sb.append(memo[1][N]+"\n");
		}
		System.out.println(sb);
	}

}
