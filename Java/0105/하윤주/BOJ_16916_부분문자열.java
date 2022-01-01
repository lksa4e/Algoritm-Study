import java.io.*;

/**
 * [0101] BOJ_16916_부분 문자열
 * 문자열, KMP
 * 
 * sol)
 * kmp 알고리즘
 * 기준 문자열과 찾아야하는 부분 문자열이 주어짐.
 * 부분 문자열 안에서 중복되는 문자열을 찾아 중복되지 않을 때 되돌아가야하는 지점을 기억한 테이블을 만든다.
 * 부분 문자열을 기준 문자열과 비교할 때는 이 테이블을 기반으로 일치가 시작한 지점부터만 확인하여 비교 연산을 줄인다.
 *
 */

public class BOJ_16916_부분문자열 {
	static char[] P;                // 기준 문자열
	static char[] S;                // 부분 문자열
	static int sizeP, sizeS;
	static int[] returnPointer;     // 되돌아가야하는 지점을 기억한 테이블
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		P = br.readLine().toCharArray();
		S = br.readLine().toCharArray();
		sizeP = P.length;
		sizeS = S.length;
		
		// 되돌아 가는 지점을 기억해두고
		makeReturnPointerTable();
		// 기준 문자열과 부분 문자열을 비교
		System.out.println(isSubString());
	}
	
	// 되돌아가는 지점 기억할 테이블 생성
	private static void makeReturnPointerTable() {
		returnPointer = new int[sizeS];
		
		// 부분 문자열 문자만큼 반복하면서 부분 접미사와 부분 접두사를 비교하여 중복여부를 판단한다
		for (int i=1, j=0; i<sizeS; i++) {
			// 만약 접두사의 길이가 2 이상인데 j까지 비교하다가 더이상 일치하지 않으면
			while(j>0 && S[i]!=S[j]) j = returnPointer[j-1];     // j직전까지는 일치했으므로 j직전이 가리키는 포인터로 되돌아감
			// (j+1)까지 일치했다면(최초 접두사는 0번째문자를 접미사로 설정하고 바로 그 다음 문자부터 시작했으므로 +1해줌) 되돌아갈 포인터도 이에 맞게 하나씩 증가
			if (S[i]==S[j]) returnPointer[i] = ++j;
		}
	}

	// 기준 문자열과 부분 문자열 비교
	private static int isSubString() {
		// 기준 문자열 문자만큼 반복
		for (int i=0, j=0; i<sizeP; i++) {
			// 만약 접두사 길이가 2 이상이고 j부터 일치하지 않았다면 j직전이 가리키는 포인터로 돌아감
			while(j>0 && P[i]!=S[j]) j = returnPointer[j-1];
			
			// 만약 기준 문자와 부분 문자가 일치하면
			if (P[i]==S[j]) {
				if (j==(sizeS-1)) return 1;     // 부분 문자열 길이만큼 다 일치했으면 종료
				else ++j;                       // 아직 부분 문자열 길이에 못미치게 비교중이라면 부분 문자열 인덱스만 증가
			} 
		}
		
		return 0;
	}

}
