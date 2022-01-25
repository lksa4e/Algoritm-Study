import java.io.*;

/*
 * BOJ 2133�� Ÿ�� ä���
 * 
 * memoization
 * 
 * N�� ¦���� ��쿡�� �����Ѵ�.
 * N = 2�� ���, 3���� ���̽��� �ְ�
 * N�� 4, 6, 8���ʹ� 2���� ���̽��� �ִ�.
 * 
 * �̸� ��������� N�� ���ؼ� 
 * 2 �� (N-2)�� ������ ���� ���̽�
 * 4 �� (N-4)�� ������ ���� ���̽�
 * ...
 * �� ����Ѵ�.
 * �� N-2, N-4 ... ���� �ٽ� ��������� ���� ���� ��Ȳ�� �ݺ��Ѵ�.
 * �׸��� memoization�� ����� �߰��߰� ���� �����Ͽ� ���� �Ѵ�. 
 * 
 * 	14320KB	128ms 
 */

public class BOJ_2133 {
	static int dp[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		dp = new int[N + 1];
		System.out.println(divideTile(N));
		
	}
	
	static int divideTile(int N) {
		if(N == 2) {	// ���� ����, N�� 2�� ���� 0�� ��,
			return 3;
		} else if(N == 0) { // 0�� ���� ����� ���� ���� ������ 1�� �����ؼ� ������ ���� �����Ѵ�.
			return 1;
		}
		
		if(dp[N] != 0) {	// ����� ���� ������ ��������.
			return dp[N];
		}
		
		int cases = 0;
		
		for(int i = 2; i <= N; i+=2) {
			if(i == 2)	// N = 2�� ���, 3���� ���̽��� ����
				cases += 3 * divideTile(N - i);
			else	// N != 2�� ��� 2���� ���̽��� ����
				cases += 2 * divideTile(N - i); 
		}
		
		dp[N] = cases;	// ���� ������ ���� �� ����
		return cases;
	}

}
