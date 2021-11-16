import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * BOJ 1181번 단어 정렬
 * 
 * 1. 길이가 짧은 순으로
 * 2. 길이가 같으면 사전 순으로
 * 
 * 중복 제거,
 */

public class BOJ_1181 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		HashMap<String, Boolean> map = new HashMap<>();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if(map.get(str) == null) {
				list.add(str);
				map.put(str, true);
			}
		}
		Collections.sort(list, (a,b)->
			a.length() != b.length() ? a.length()-b.length() : a.compareTo(b)
		);
		
		StringBuilder sb = new StringBuilder();
		for (String str : list) {
			sb.append(str).append("\n");
		}
		System.out.println(sb);
	}

}
