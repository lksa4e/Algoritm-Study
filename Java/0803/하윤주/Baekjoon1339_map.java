import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * [0807] 백준 1339 단어수학
 * Map을 이용한 구현
 */

public class Baekjoon1339_map {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Character, Integer> alphabets = new HashMap<>();   // key : 알파벳, value : 자리수의 최소 수
		
		int N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			char[] word = br.readLine().toCharArray();
			int len = word.length;
			for (int j=0; j<len; j++) {
				
				int place = len - j - 1;    // 자리수
				
				// 맵에 알파벳과 자리수의 최소 수 저장
				Integer before = alphabets.get(word[j]);
				if (before == null) {    // 키가 존재하지 않을 경우
					alphabets.put(word[j], (int)Math.pow(10, place));
				} else {                 // 키가 존재할 경우
					alphabets.put(word[j], before+((int)Math.pow(10, place)));
				}
			}
		}
		
		// List<Integer> places = new ArrayList<Integer>(alphabets.values());   // 맵의 value를 리스트로
		// Collections.sort(places, Collections.reverseOrder());

		// 왜인지 모르겠는데 배열이 더 빠르네요...?
		Object[] places = alphabets.values().toArray();     // 맵의 value를 배열로
		Arrays.sort(places, Collections.reverseOrder());    // 내림차순 정렬
		
		int answer = 0;
		int num = 9;
		for (Object p : places) {
			
			answer += (int)p * num--;
		}
		
		System.out.println(answer);
	}

}
