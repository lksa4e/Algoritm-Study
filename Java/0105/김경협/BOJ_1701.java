package BOJ_passed;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * BOJ 1701번 Cubeditor
 * 
 * KMP
 * 
 * KMP의 실패함수를 응용했다. BOJ 문자열 제곱에서 사용했던 방식대로
 * 실패함수 구하는 걸 통해서 2번 이상 반복되는 가장 긴 접두 접미사를 구했다.
 * 그리고 원본 문자열, 원본 문자열에서 앞자리만 뺀 문자열, 원본 문자열에서 앞의 두자리만
 * 뺀 문자열 ... 이런식으로 해서 전체에 대한 가장 긴 접두 접미사를 구했다.
 * 
 * 113072KB	328ms
 */

public class BOJ_1701 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] text = br.readLine().toCharArray();
		int max = 0;
		
		for (int startIdx = 0, len = text.length; startIdx < len; startIdx++) {
			
			int[] pi = new int[len];
			
			for(int i = 1, j = 0; i < len - startIdx; i++) {
				while(j > 0 && text[i + startIdx] != text[j + startIdx]) j = pi[j - 1];
				
				if(text[i + startIdx] == text[j + startIdx]) 
				{
					pi[i] = ++j;
					max = Math.max(pi[i], max);
				}
			}
			
			
		}
		
		System.out.println(max);
		
	}

}
