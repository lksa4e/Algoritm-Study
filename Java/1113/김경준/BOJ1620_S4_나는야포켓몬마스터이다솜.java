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
 * 메모리 : 62452KB, 시간 604ms
 * 맵을 사용한 즐거운 문제
 */

public class BOJ1620_S4_나는야포켓몬마스터이다솜 {
	static int N,M;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		// 조건 체크를 붙이기 귀찮아서 그냥 String String으로 사용함
		Map<String, String> pokemon = new HashMap<String, String>();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			String str = br.readLine();
			pokemon.put(str, Integer.toString(i));
			pokemon.put(Integer.toString(i), str);
		}
		
		for(int i = 0; i < M; i++) 
			sb.append(pokemon.get(br.readLine()) + "\n");
		
		System.out.print(sb);
	}
}
