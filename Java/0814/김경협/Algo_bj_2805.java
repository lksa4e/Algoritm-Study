import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algo_bj_2805 {
	static int N, M, longest;
	static int tree[];

	static boolean cutable(int thread) {
		// 나무 길이의 합은 20억 이상일 수도 있으니 long으로 선언
		long sum = 0;
		for (int i = 0; i < N; i++)
			if (tree[i] > thread)
				sum += tree[i] - thread;

		return sum >= M ? true : false;
	}

	static int bSearch() {
		// 자르는 스레드를 0과 가장 높은 나무를 기준으로 이분 탐색
		// 가장 M에 가까운 길이로 자를 수 있는 thread길이를 탐색
		int low = 0, high = longest, mid = 0, result = 0;

		while (low <= high) {
			mid = (low + high) / 2;
			// mid로 나온 thread가 M을 만족할 시 thread를 올려줌
			if (cutable(mid)) {
				low = mid + 1;
				if (result < mid)
					result = mid;
			} else
				high = mid - 1;
		}
		
		return result;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer tk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());

		tree = new int[N];
		tk = new StringTokenizer(br.readLine());
		
		// 입력 중에 가장 높은 나무 찾기
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(tk.nextToken());
			longest = Math.max(longest, tree[i]);
		}

		System.out.println(bSearch());

	}

}
