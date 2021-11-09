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
 * 메모리 : 23708KB, 시간 560ms
 * 맵을 사용한 즐거운 문제
 */

public class BOJ1181_S5_단어정렬 {
	static int N;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Map<String, Integer> visit = new HashMap<String, Integer>();
		List<String> list = new ArrayList<String>();
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			if(visit.get(str) == null) {
				visit.put(str, 1);
				list.add(str);
			}
		}
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) return o1.compareTo(o2);
				return Integer.compare(o1.length(), o2.length());
			}
		});
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
