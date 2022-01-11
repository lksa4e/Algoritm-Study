import java.io.*;
import java.util.*;

/*
 * BOJ 12865�� ����� �賶
 * 
 * 0-1 knapsack ����.
 * �������� Ǯ� ������ �򰥸��� ������� ���ͳ����� �����ϰ� Ǯ����.
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
		
		dp = new int[N+1][K+1]; // 1��° �� -> �ƹ��͵� �� �־��� ��
		
		for(int i = 1; i <= N; i++) {	// i�� 1~i��° ���Ǳ��� �ִ� ��츦 �ǹ��Ѵ�.
			
			for(int currW = 1; currW <= K; currW++) {	// currW�� ���� �� �ִ� ���Ը� �ǹ��Ѵ�.
				if(weight[i] <= currW) {	// i ��° ������ ���԰� ���� �� �� �ִ� ���Ժ��� �۰ų� ���� ���,
					// �� ��� ���� ���濡 �ִ� �͵��� value��, �� ���� i��° ����+���¹��Կ� �ش��ϴ� value�� ���� ���ؼ� �� ������ �ִ´�.
					dp[i][currW] = Math.max(dp[i-1][currW], value[i] + dp[i-1][currW-weight[i]]);
				} else
					dp[i][currW] = dp[i-1][currW];
			}
		}
		System.out.println(dp[N][K]);
	}

}
