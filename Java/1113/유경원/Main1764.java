import java.io.*;
import java.util.*;

public class Main1764 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Boolean> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			map.put(br.readLine(), true);
		}
		
		String input = "";
		for(int i=0; i<M; i++) {
			input = br.readLine();
			if(map.get(input)!=null) list.add(input);
		}
		
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append("\n");
		for(String s : list) {
			sb.append(s).append("\n");
		}
		System.out.println(sb);
		
	}
}