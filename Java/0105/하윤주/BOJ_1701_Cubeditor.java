import java.io.*;

/**
 * [0105] BOJ 1701 Cubeditor
 * 문자열, KMP
 * 
 * sol)
 * 문자열의 모든 가능한 서브 문자열에 대해 KMP 시도하여 일치하는 문자열 길이의 최댓값을 구한다.
 * KMP에서는 문자열의 0번째 인덱스부터 접두사로 설정하여 문자열 일치 여부를 검사하므로,
 * 서브 문자열은 원본 문자열의 맨 앞에서부터 문자 하나씩 지워가며 구성해야 한다.
 * 
 * 시행착오)
 * 이미 등장한 적 있는 문자가 또 등장했다면 앞서 등장한 지점부터 현재까지의 문자열이, 반복되는 서브 문자열이 될 가능성이 있다고 생각.
 * 따라서 이러한 문자열들만 리스트에 담아 KMP를 시도했는데 두가지 문제가 있었음
 * 1. 일치하는 부분이 자기 자신이 떼어져 나온 바로 그 위치라면 중복되는 것이 아님
 * 2. 일치하는 부분이 통째로 일치하지 않고 일부 일치하는 경우 체크가 어려움
 * 따라서 실패...
 * 
 */

public class BOJ_1701_Cubeditor {
	static String str;           // 원본 문자열
	static char[] subChars;      // 서브 문자열은 char 배열로 표현
	static int strLen, subLen, maxLen;
	static int[] returnPointer;  // 되돌아갈 인덱스 저장한 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		strLen = str.length();
		
		// 가능한 모든 서브 문자열 KMP 시도
		findSubStrs();
		System.out.println(maxLen);
	}

	// 원본 문자열의 모든 서브 문자열 KMP 시도
	private static void findSubStrs() {
		for (int i=0; i<strLen-1; i++) {      // 접두사, 접미사 비교를 위해 적어도 길이가 2 이상인 서브 문자열 사용
			subChars = str.substring(i, strLen).toCharArray();
			subLen = subChars.length;
			returnPointer = new int[subLen];
			
			makeReturnPointerArr(i);         // 모든 서브 문자열 KMP return table 구성
		}
	}

	// return table 만들기
	private static void makeReturnPointerArr(int s) {
		for (int i=1, j=0; i<subLen; i++) {
			// 접두사 길이가 2 이상이고 j번째에서 어긋났다면 j-1번째 문자가 가리키는 일치 인덱스로 돌아감
			while (j>0 && subChars[i]!=subChars[j]) j = returnPointer[j-1];
			// 접미사와 접두사의 현재 좌표들이 각각 일치하면
			if (subChars[i]==subChars[j]) {
				returnPointer[i] = ++j;          // 접미사가 가리키는 return table에는 현재 접두사 좌표를 저장하고
				maxLen = Math.max(maxLen, j);    // 일치하는 길이를 의미하는 접두사의 길이로 일치 서브 문자열 길이 최댓값 갱신
			}
		}
	}

}
