import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3985 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		int[] cake = new int[L+1];

		int expectMax = 0;
		int realMax = 0;

		int expected = 0;
		int real = 0;

		for (int i = 1; i <= N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(tk.nextToken());
			int end = Integer.parseInt(tk.nextToken());

			if (end - start + 1 > expectMax) {
				expectMax = end - start + 1;
				expected = i;
			}

			int count = 0;
			for (int j = start; j <= end; j++) {
				if (cake[j] == 0) {
					cake[j] = i;
					count++;
				}
			}

			if (count > realMax) {
				realMax = count;
				real = i;
			}
		}
		
		System.out.println(expected + "\n" + real);

	}
}
