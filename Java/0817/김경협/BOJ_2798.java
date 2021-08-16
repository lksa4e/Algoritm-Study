import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2798 {
	static int max, N, M;
	static int[] inputs, numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());

		inputs = new int[N];
		numbers = new int [3];
		tk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(tk.nextToken());
		}

		permutation(0,0);
		System.out.println(max);

	}

	static void permutation(int cnt, int start) {
		if (cnt == 3) {
			int sum = 0;
			
			for(int i : numbers)
				sum += inputs[i];
			
			if (sum <= M)
				max = Math.max(max, sum);
			return;
		}
		
		for(int i = start; i < inputs.length; i++) {
	
			numbers[cnt] = i;
			permutation(cnt+1, i+ 1);
		}
	}

}
