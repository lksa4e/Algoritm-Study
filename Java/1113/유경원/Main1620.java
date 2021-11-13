import java.io.*;
import java.util.*;

public class Main1620 {

	static int N, M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map<String, String> dogam = new HashMap<String, String>();
		String input, num;
		for(int i=1; i<=N; i++) {
			input = br.readLine();
			num = i+"";
			dogam.put(input, num);
			dogam.put(num, input);
		}
		for(int i=1; i<=M; i++) {
			input = br.readLine();
			sb.append(dogam.get(input)).append("\n");
		}
		System.out.println(sb);
		
	}

}