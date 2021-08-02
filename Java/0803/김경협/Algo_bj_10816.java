import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algo_bj_10816 {
	static int[] cards = new int[20000001];

	public static void main(String[] args) throws NumberFormatException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) 
			cards[Integer.parseInt(st.nextToken()) + 10000000]++;
		
		int k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < k; i++)
			sb.append(cards[Integer.parseInt(st.nextToken()) + 10000000] + " ");
		
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

}
