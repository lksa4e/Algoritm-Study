import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1654 {
	static int N, M, left = 1, right = 0, answer = 0;
	static int[] arr;
	static void solve() {
		while(left <= right) {
			int mid = (int)(((long)left + right) / 2);
			if (mid == 0) return;
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				cnt += arr[i] / mid;
			}
			if(cnt >= M) {
				left = mid + 1;
				answer = Math.max(answer, mid);
			}else {
				right = mid - 1;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			right = Integer.max(right, arr[i]);
		}
		solve();
		System.out.print(answer);
	}
}
