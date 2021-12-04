import java.io.*;
import java.util.*;

/**
 * [1204] BOJ 20437 문자열 게임2
 * 문자열, 맵
 * 
 * sol)
 * 입력받은 문자열의 각 문자 별 등장 인덱스를 저장한 리스트를 만든다.
 * 이 리스트의 길이가 K개 이상이 되면, 마지막 인덱스부터 K개 만큼의 인덱스를 포함하는 구간을 찾아 해당 인덱스로 서브스트링을 만든다.
 * 이 길이로 최댓값, 최솟값을 갱신해준다.
 * 
 * 시행착오)
 * 테케 별 맵, 최대, 최소 길이를 초기화하지 않아 틀렸습니다 발생
 *
 */

public class BOJ_20437_문자열게임2 {
	static String W;
	static int K;
	static Map<Character, ArrayList<Integer>> charCheck = new HashMap<Character, ArrayList<Integer>>();    // key: 등장하는 문자, value: 문자의 등장 인덱스
	static int len3, len4;      // 3번, 4번 답(최솟값, 최댓값)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) {
			W = br.readLine();                         // 테케 별 문자열 입력받고
			K = Integer.parseInt(br.readLine());

			stringGame();                              // 게임 시작
			
			if (len4 == 0) System.out.println(-1);     // 만약 K개를 포함하는 서브스트링이 없으면 예외처리
			else {
				StringBuilder sb = new StringBuilder();
				sb.append(len3).append(" ").append(len4);     // K개를 포함하는 서브스트링의 최소, 최대 길이 출력
				System.out.println(sb);	
			}
		}
	}

	// 문자열 게임 시작
	private static void stringGame() {
		len3 = Integer.MAX_VALUE;         // 각 테케 별 게임 시작 시 최소, 최대, 맵 초기화함을 잊지 말아야한다.
		len4 = 0;
		charCheck.clear();
		
		for (int size=W.length(), i=0; i<size; i++) {
			char c = W.charAt(i);                          // 문자열의 모든 문자에 대해
			charCheck.computeIfAbsent(c, v -> new ArrayList<Integer>()).add(i);     // 등장 인덱스를 저장한다.
			
			ArrayList<Integer> idxs = charCheck.get(c);
			int idxsSize = idxs.size();
			
			if (idxsSize >= K) {                           // 특정 문자가 K번 이상 등장했으면
				int startIdx = idxs.get(idxsSize-K);
				int endIdx = idxs.get(idxsSize-1) + 1;
				int subStrLen = endIdx - startIdx;
				
				if (subStrLen < len3) len3 = subStrLen;    // 최솟값 갱신 시도
				if (subStrLen > len4) len4 = subStrLen;    // 최댓값 갱신 시도
			}
		}
		
	}

}
