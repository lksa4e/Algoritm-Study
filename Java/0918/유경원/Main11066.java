import java.io.*;
import java.util.*;

public class Main11066 {

	/* 못풀어서 아래 링크 참고했음
	 * https://guy-who-writes-sourcecode.tistory.com/43
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int T, K, files[], sum[], dp[][], MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			K = Integer.parseInt(br.readLine());
			files = new int[K+1];
			sum = new int[K+1];
			dp = new int[K+1][K+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				files[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + files[i];
            }
			
			for (int n = 1; n <= K; n++) {
                for (int from = 1; from + n <= K; from++) {
                    int to = from + n;
                    dp[from][to] = MAX;
                    for (int divide = from; divide < to; divide++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }

            System.out.println(dp[1][K]);
		}
		
	}
}