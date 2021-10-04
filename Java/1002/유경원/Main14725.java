import java.io.*;
import java.util.*;

public class Main14725 {

	/*
	 * 트라이 알고리즘 찾다가 신박한 풀이 발견
	 * TreeMap으로 트리만든 후 출력
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		
		// 트리 만들기
		Map<String, TreeMap> map = new TreeMap<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			Map tmap = map;
			for(int j=0; j<K; j++) {
				String s = st.nextToken();
				
				if(tmap.get(s)==null)
					tmap.put(s, new TreeMap<>()); 
				tmap = (TreeMap) tmap.get(s);
			}
		} 
		
		print(map, 0);
		
		System.out.println(sb);
	}
	
	private static void print(Map map, int n) {
		for(Object s : map.keySet()) {
			for(int i=0; i<n; i++)
				sb.append("--");
			sb.append(s).append("\n");
			print((TreeMap)map.get(s),n+1);
		}
	}
	
}
