import java.io.*;

/**
 * [0101] BOJ 4354 문자열 제곱
 * 문자열, KMP
 * 
 * sol)
 * KMP 알고리즘을 이용해 접미사, 접두사를 바탕으로 문자열 내 되돌아갈 중복 지점 배열을 만든다.
 * 만약 제곱근이 존재하는 문자열이라면 KMP 배열에서 값이 최초로 할당되는 지점 이전까지가 제곱근 문자열이되고,
 * 이후는 제곱근이 반복되는 형태가 된다. 따라서 KMP 배열의 마지막에는 제곱근 문자열 자기자신(1회)를 차감한 제곱 승이 저장된다.
 * 이 마지막에 저장된 값을 제곱근 문자열 길이로 나눠주고 자기자신 횟수인 1을 더하면 n승을 구할 수 있음
 * 
 * 단, 접미사의 i번째 문자와 접두사의 j번째 문자가 일치하지 않는 지점이 이미 일치하는 지점 이후에 또 등장했다면 제곱근 찾기 실패
 * -> KMP 배열 마지막에 저장된 값을 제곱근 문자열 길이로 나눌 수 없으면 실패 판별
 * -> 또한 제곱근 문자열 길이가 한번도 갱신되지 않아도 실패
 *
 */

public class BOJ_4354_문자열제곱 {
	static char[] str;
	static int strLen, subLen;    // 원본 문자열 길이, 제곱근 문자열 길이
	static int[] returnPointer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			str = br.readLine().toCharArray();
			if (str[0]=='.') break;
			
			strLen = str.length;
			subLen = 0;
			// 제곱근 문자열 찾기
			System.out.println(findN());
		}
	}

	// 제곱근 문자열 찾기
	private static int findN() {
		returnPointer = new int[strLen];
		
		for (int i=1, j=0; i<strLen; i++) {     // 문자열에서 중복되는 부분 찾아 되돌아올 포인터 찾기
			// 만약 접두사를 포함한 문자열 길이가 2 이상이고, 문자열이 j 자리에서 일치하지 않으면
			while (j>0 && str[i]!=str[j]) {
				j = returnPointer[j-1];         // j-1까지 일치했으므로 해당 포인터자리로 돌아감
			}
			// 만약 접두사와 접미사가 각각 현재 가리키는 문자가 같으면
			if (str[i]==str[j]) {
				returnPointer[i] = ++j;         // j를 하나 증가시키고 되돌아갈 포인터도 일치하는 j지점과 같게 설정
				// 만약 접두사가 새롭게 설정되면 제곱근 문자열 길이를 새롭게 설정
				if (j==1) subLen = i;
			}
		}
		// 일치하는 접미사, 접두사가 없어서 제곱근 문자열 길이가 갱신되지 않았거나,
		// 배열 마지막에 저장된 제곱 승이 제곱근 문자열 길이의 배수가 아니면 제곱근 문자열이 완전히 제곱되는 경우가 아님
		if (subLen==0 || returnPointer[strLen-1]%subLen!=0) return 1;
		// 제곱근 문자열을 찾은 경우에는 배열 마지막에 저장된 제곱 승을 제곱근 문자열 길이로 나누고 자기 자신인 1을 더한 값 반환
		else return returnPointer[strLen-1]/subLen + 1;
	}

}
