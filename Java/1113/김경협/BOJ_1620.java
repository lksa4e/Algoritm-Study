import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * BOJ 1620 나는야 포켓몬 마스터 이다솜
 * 
 * 주어진 포켓몬 이름들을 이름을 key로 map1에 담고 번호를 key로 map2에 담은 뒤,
 * 문제로 주어진 번호 혹은 이름을 map1과 map2에 줘서 null이 아닌 값을 출력했다.
 */

public class BOJ_1620 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> map1 = new HashMap<>();
		HashMap<String, String> map2 = new HashMap<>();
		
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			map1.put(str, i);	// 문자열 key, 숫자 V
			map2.put(Integer.toString(i), str);	// 숫자를 Key, 문자열 V
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			if(map1.get(str) != null) {		// 포켓몬 이름일 경우
				sb.append(map1.get(str)).append("\n");
			} else if(map2.get(str) != null) {	// 포켓몬 숫자일 경우
				sb.append(map2.get(str)).append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
