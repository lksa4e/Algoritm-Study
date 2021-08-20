import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algo_bj_14888 {
	static int[] num, oper;
	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

	// 각각 plus, minus, mul, div
	static void dfs(int p, int mi, int mu, int d, int cnt, int sum) {
		if (cnt == N - 1) { // 부호의 수만큼 카운팅 했을 때,
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		// 써야할 operator가 남아 있다면, 다음 친구한테 전달
		if (p > 0) dfs(p - 1, mi, mu, d, cnt + 1, sum + num[cnt + 1]);
		if (mi > 0) dfs(p, mi - 1, mu, d, cnt + 1, sum - num[cnt + 1]);
		if (mu > 0) dfs(p, mi, mu - 1, d, cnt + 1, sum * num[cnt + 1]);
		if (d > 0) dfs(p, mi, mu, d - 1, cnt + 1, sum / num[cnt + 1]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		num = new int[N];
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(tk.nextToken());

		tk = new StringTokenizer(br.readLine(), " ");
		dfs(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()),
				Integer.parseInt(tk.nextToken()), 0, num[0]);
		System.out.print(max + "\n" + min);
	}

}
