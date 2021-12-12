import java.io.*;

/**
 * [1204] BOJ 14405 피카츄
 * 문자열
 * 
 * sol)
 * 문자열 앞에서부터 문자 하나씩 확인
 * 1. 알파벳 2개짜리 발음 가능 여부 확인
 *    - 가능하다면 2개 뒤의 문자로 바로 이동
 *    - 불가능하다면 chu 발음 가능 여부 확인
 * 2. chu 발음 가능 여부 확인
 *    - 가능하다면 3개 뒤의 문자로 이동
 *    - 불가능하다면 실패
 *
 */

public class BOJ_14405_피카츄 {
	static String S;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();         // 문자열 입력
		
		if (stringCheck()) System.out.println("YES");      // 발음 가능
		else System.out.println("NO");                     // 발음 불가능
	}
	
	// 발음 가능한지 체크
	private static boolean stringCheck() {
		for (int len=S.length(), i=0; i<len; i++) {
			
			// 먼저 알파벳 길이 2개인 경우(pi, ka) 발음 가능한지 체크
			if (i+1>=len) return false;                      // 알파벳 길이 2개 체크를 위해 최소 2개 이상의 문자가 남아있어야 함
			if ("pi/ka".contains(S.substring(i, i+2))) {     // 현재 인덱스에서 2개짜리 서브스트링이 pi, ka이면 
				i++;                                         // chu 발음 가능 여부 체크할 필요 없이 인덱스는 2 증가
				continue;
			}
			
			// pi, ka 발음 실패 시 알파벳 길이 3개인 경우(chu) 발음 가능한지 체크
			if (i+2>=len) return false;                      // 알파벳 길이 3개 체크를 위해 최소 3개 이상의 문자가 남아있어야 함
			if ("chu".contains(S.substring(i, i+3))) {       // 현재 인덱스에서 3개짜리 서브스트링이 chu이면 
				i += 2;                                      // 인덱스는 3 증가
				continue;
			}
			
			// pi, ka 발음 실패한 뒤, chu 마저 실패했다면 발음할 수 없는 문자열이므로 false 반환
			return false;
		}
		// 문자열을 모두 확인해서 여기까지 도달했다면 발음할 수 있는 문자열이므로 true 반환
		return true;
	}
	
}
