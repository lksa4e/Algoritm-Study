import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 메모리 : 26560KB, 시간 320ms
 * 맵을 사용한 즐거운 문제
 */

public class BOJ1764_S4_듣보잡 {
	static int N,M;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		Map<String, Integer> visit = new HashMap<String, Integer>();
		List<String> list = new ArrayList<String>();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			visit.put(str, 1);
		}
		for(int j = 0; j < M; j++) {
			String str = br.readLine();
			if(visit.get(str) != null)
				list.add(str);
		}
		Collections.sort(list);
		
		sb.append(list.size() + "\n");
		for(String data : list) {
			sb.append(data + "\n");
		}
		System.out.print(sb);
	}
}
