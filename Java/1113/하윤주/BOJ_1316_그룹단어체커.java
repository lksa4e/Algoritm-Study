import java.io.*;
import java.util.*;

/**
 * [1113] BOJ 1316 그룹 단어 체커
 *  문자열, map
 * 
 * sol)
 *  단어 문자를 하나씩 확인하며 직전 문자와 다를 경우 맵에 추가한다. 이때 이미 맵에 해당 문자가 저장되어있으면 실패
 */

public class BOJ_1316_그룹단어체커 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;          // 최종 정답
		
		Map<Character, Boolean> groupWordChecker = new HashMap<>();      // 문자 등장 여부 체크 맵
		for (int i=0; i<N; i++) {
			groupWordChecker.clear();           // 단어 별 문자 등장 체크
			boolean flag = true;                // 현재 문자가 이전에 등장한 적 있는지 확인하는 플래그
			char prev = '0';                    // 직전 문자
			String word = br.readLine();
			
			for (int size=word.length(), j=0; j<size; j++) {
				char cur = word.charAt(j);
				// 만약 직전 문자와 다른 문자인데 이전에 등장한 적 있으면 실패
				if (cur!=prev && groupWordChecker.containsKey(cur)) {
					flag = false;
					break;
				}
				groupWordChecker.put(cur, true);
				prev = cur;
			}
			// 실패가 아닌 경우만 카운트
			if (flag) cnt++;
		}
		
		System.out.println(cnt);
	}

}
