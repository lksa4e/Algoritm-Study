import java.io.*;

/**
 * 
 * [1229] BOJ 1013 Contact
 * 문자열
 * 
 * sol)
 * 100으로 시작하는 패턴과 01로 시작하는 패턴 두가지 경우로 나눠 생각한다.
 * 1. 100으로 시작할 경우 
 *    - 뒤에 나오는 0은 모두 pass함.
 *    - 1이 등장하면 1이 1개 나오는지 2개 이상 나오는지 판단
 *       - 1개 나온다면 뒤는 무조건 01로 시작하는 패턴 체크로 넘겨버림
 *    - 2개 이상 나온다면 1이 끝난 지점 이후에 00 이 오는지 판단.
 *       - 00이 나온다면 100으로 시작하는 패턴으로 다시 돌아감
 *       - 아니라면 01로 시작하는 패턴 체크로 넘겨버림
 * 2. 01로 시작하는 경우
 *    - 뒷부분은 100과 01 모두 올 수 있으므로 100으로 시작하는 경우부터 다시 판단
 *
 */
public class BOJ_1013_Contact {
	static final String PATTERN1 = "100";     // 패턴 1
	static final String PATTERN2 = "01";      // 패턴 2
	
	static final char SUFFIX = '0';           // 패턴 1의 접미사
	static final char PREFIX = '1';           // 패턴 1의 접미사 뒤에오는 접두사
	
	static final int LENGTH1 = 3;             // 패턴 1 길이
	static final int LENGTH2 = 2;             // 패턴 2 길이
	
	static String wave;                       // 문자열
	static int idx, size; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) {
			wave = br.readLine();
			size = wave.length();
			if (findPattern()) System.out.println("YES");    // 패턴으로 채워진 문자열이면 YES
			else System.out.println("NO");                   // 패턴으로 채워진 문자열 아니면 NO
		}
	}

	// 패턴 찾는 메서드
	private static boolean findPattern() {
		idx = 0;
		
		while(idx<size) {      // 현재 확인을 시작할 인덱스가 문자열 길이 이내라면
			// 패턴 1로 시작하는지 확인
			if (idx+LENGTH1<=size && PATTERN1.equals(wave.substring(idx, idx+LENGTH1))) {
				// 패턴 1 중 "100"까지 찾음
				idx += LENGTH1;                 // 패턴 1의 길이만큼 시작 인덱스를 증가 
				if (idx==size) return false;    // 아직 패턴 1 안끝났는데 문자열이 끝났다면 패턴 찾기 실패
				
				// 패턴 1 중 반복되는 "0" 찾기
				while (SUFFIX == wave.charAt(idx)) {
					if (++idx==size) return false;     // 아직 패턴 1 안끝났는데 문자열 끝났으면 패턴 찾기 실패
				}
				
				// 패턴 1 중 반복되는 "1" 찾기
				if (++idx==size) return true;          // 1이 무조건 한번은 등장해야지 패턴 1이 완성됨. 1이 한번 등장하면 패턴 1은 끝난 상태. 이때 문자열 끝났으면 패턴 찾기 성공
				if (PREFIX == wave.charAt(idx)) {
					while (PREFIX == wave.charAt(idx)) {
						if (++idx==size) return true;
					}
					// 만약 1이 2개 이상 등장했다면 뒤에 "00"이 오는지 확인하여 온다면 인덱스 한칸 뒤로 보내 "100" 찾도록 함
					if (idx+1<size && SUFFIX==wave.charAt(idx+1)) idx--;
				}
			// 패턴 2로 시작하는지 확인
			} else if (idx+LENGTH2<=size && PATTERN2.equals(wave.substring(idx, idx+LENGTH2))) {
				idx += LENGTH2;
			// 패턴 1과 2 모두 아니라면 패턴 찾기 실패
			} else return false;
		}
		return true;
	}
}
