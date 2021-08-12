import java.io.*;
import java.util.*;
/**
 * 문제 링크 : https://www.acmicpc.net/problem/2805
 * 가능한 높이의 최대값을 구해야 하지만, 높이의 범위는 1 <= h <= 1,000,000,000 이다.
 * 따라서 모든 높이를 경우의 수로 두는 완전탐색은 사용하지 못하고, 이진탐색을 통해 높이를 log(N)에 찾아 사용해야한다.
 */


public class P2805 {
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, M;
	static long left = 1, right = 1, answer = 0;
	static int tree[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			if(tree[i] <= 1000000000 && right < tree[i]) right = tree[i];
		}
		solve();
		System.out.print(answer);
	}
	static void solve() {
		while(left <= right) {
			long result = 0;
			long mid = (left + right) / 2;
			for(int i = 0; i < N; i++) {
				result += tree[i] - mid > 0 ? tree[i]-mid : 0;
			}
			if(result >= M) {
				left = mid + 1;
				answer = Math.max(mid, answer);
			}else right = mid - 1;
		}
	}
}
