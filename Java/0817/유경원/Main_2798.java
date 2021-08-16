import java.io.*;
import java.util.*;

public class Main_2798 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] card = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(card);

		int ans = 0;
		LOOP: for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				if (card[i] + card[j] > M) continue;

				for (int k = j + 1, sumCard = 0; k < N; k++) {
					sumCard = card[i] + card[j] + card[k];
					if (sumCard <= M && ans < sumCard) {
						ans = sumCard;
						if (ans == M) break LOOP;
					}
				}
			}
		}

		System.out.println(ans);

	}
}