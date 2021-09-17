import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 17435번 합성함수와 쿼리
 * 
 * 문제 해결 방법: Sparse Table
 * 
 * 2의 제곱수에 해당하는 쿼리들을 저장한다. 
 * 이 때, 2^(i-1)번째 쿼리를 이용하여 2^i에 해당하는 쿼리를 구할 수 있기 때문에
 * 2의 제곱수에 해당하는 쿼리들을 적은 비용으로 구할 수 있다.
 * 이 수들을 이용하여 원하는 쿼리를 2의 제곱수들로 분해하여 계산한다.
 * 
 */

public class BOJ_17435 {
	static final int max_d = 18; // log500,000보다 작으면서 가장 큰 정수

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		int next[][] = new int[m + 1][max_d + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++)
			next[i][0] = Integer.parseInt(st.nextToken());	// 각 x를 2^0번 f에 넣었을 때의 값
		
		// j번에 2^1부터 2^max_d까지 2의 제곱수번 f에 넣었을 때의 값을 구하기
		for (int i = 1; i <= max_d; i++) {
			// j번째 값을 f()에 2^i번 넣었을 때의 값
			for (int j = 1; j <= m; j++) {
				// j번째 값을 f에 2^i번 넣었을 때의 값은
				// j번째 값을 f에 2^(i-1)*2번 넣었을 때의 값과 같다.
				// 즉 j를 f에 2^(i-1)번 넣어서 나온 값을 다시 f에 2^(i-1)번 넣은 값과 같다.
				next[j][i] = next[next[j][i-1]][i-1];
			}
		}
		
		int query = Integer.parseInt(br.readLine());	// 쿼리의 개수
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < query; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			// x를 f에 n번 만큼 넣어서 나온 값을 구하기
			// ex) n = 7이라면 x를 f에 4번 돌려서 나온 값을 2번 돌리고 또 1번 더돌리면 됨
			for (int j = max_d; j >= 0; j--) {
				if(n < (1 << j)) continue;	// n보다 작으면서 가장 큰 2^j를 찾을 때 까지 continue
				x = next[x][j];				// x를 f에 2^j번 넣어서 나온 값을 다시 x에 넣어줌
				n -= 1 << j;
			}
			sb.append(x).append("\n");
		}
		System.out.println(sb);
	}
}
