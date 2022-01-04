import java.io.*;

/*
 * BOJ 16916번 부분 문자열
 * 
 * KMP
 * 
 * 부분 문자열인지 아닌지 구하는 문제,
 * contains로 풀면 시간 초과가 뜬다. 자바의 contains는 indexOf를 사용하기 때문에
 * 효율적이지 못하다.
 * 
 * 30816KB	340ms
 */

public class BOJ_16916 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();

		System.out.println(isExist(text, pattern) ? 1 : 0);

	}

	private static boolean isExist(char[] text, char[] pattern) {
		int tLen = text.length, pLen = pattern.length;

		int pi[] = new int[pLen];	// 실패함수 만들기

		for (int i = 1, j = 0; i < pLen; i++) {
			while (j > 0 && pattern[i] != pattern[j])	// 패턴이 일치하지 않을 경우
				j = pi[j - 1];		// j는 실패함수의 j - 1 위치의 값을 갖는다.
			
			if (pattern[i] == pattern[j])	// 패턴이 일치할 경우
				pi[i] = ++j;				// 실패 함수에서 i번째 위치에 1증가된 j를 넣는다. (패턴이 일치하면 i,j 둘 다 증가)
		}
		
		for(int i = 0, j = 0; i < tLen; ++i) { // 실패 함수 만들 때와 거의 비슷함
			// text에서 i 인덱스를, pattern에서 j 인덱스를 사용해서 둘 사이를 비교한다.
			while(j > 0 && text[i] != pattern[j]) // i와 j 위치의 문자가 다를 경우, 패턴의 처음부터 비교하는 것이 아니라 실패함수를 통해 j의 위치를 정한다.
				j = pi[j - 1];
			
			if(text[i] == pattern[j]) {	// 패턴이 일치할 경우
				if(j == pLen - 1)	// 패턴을 가르키는 j가 끝에 도달했을 때, 부분 문자열이 존재한다는 뜻이 된다.
					return true;
				else
					j++;	// 패턴이 일치할 경우 j 증가
			}
		}
		return false;
	}
}
