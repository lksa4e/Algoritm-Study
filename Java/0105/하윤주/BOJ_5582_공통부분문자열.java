import java.io.*;

/**
 * [0101] BOJ 5582 공통 부분 문자열
 * 문자열, DP
 * 
 * sol)
 * 문자열1과 문자열2를 각각 행과 열로 하는 동적 테이블을 만든다. 테이블의 각 요소에는 현재 문자열까지의 공통 부분 문자열의 최대 길이가 저장된다.
 * 공통 부분 문자열 최대 길이가 갱신되는 때는 문자열1의 i번째 문자와 j번째 문자(교차하는 좌표에서 각각 문자)가 같을 때임.
 * 비교를 당하는 문자열의 i번째까지와 비교를 하는 문자열의 j번째까지 비교한 결과값은 저장되어있는 상태에서 새롭게 일치하는 문자가 등장했으므로 +1만큼 갱신해주면 됨.
 * 이때 값은 동적테이블[i-1][j-1]의 좌표를 기준으로 +1 해준다.
 * 
 * 시행착오)
 * 완탐 방식으로 부분 문자열을 다 구해서 딕셔너리에 키로 저장한 다음 조회했더니 메모리 초과 에러 발생...
 * 아마 메모리가 안터져도 시간이 터졌을 것 같다.
 *
 */

public class BOJ_5582_공통부분문자열 {
	static String str1, str2;
	static int size1, size2, maxLen;
	static int[][] commonSubCnt;         // 동적 테이블
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine();
		str2 = br.readLine();
		size1 = str1.length();
		size2 = str2.length();
		
		commonSubCnt = new int[size1+1][size2+1];     // 인덱싱 편하게 하기 위해 길이 1씩 증가하여 배열 생성
		findSubString();
		System.out.println(maxLen);
	}
	
	// 공통 부분 문자열 길이를 저장한 동적 테이블 만들기
	private static void findSubString() {
		for (int i=1; i<=size1; i++) {
			char char1 = str1.charAt(i-1);       // 문자열1의 i번째 문자(문자열2의 모든 문자와 비교할 때까지 고정)
			for (int j=1; j<=size2; j++) {
				char char2 = str2.charAt(j-1);   // 문자열2의 j번째 문자
				if (char1 == char2) {
					commonSubCnt[i][j] = commonSubCnt[i-1][j-1]+1;     // (i-1, j-1)번째보다 1개 더 일치함
					maxLen = Math.max(maxLen, commonSubCnt[i][j]);
				}
			}
		}
	}

}
