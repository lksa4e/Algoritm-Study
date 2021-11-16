import java.io.*;
import java.util.*;

/**
 * [1116] BOJ 1213 팰린드롬 만들기
 * 문자열, 구현, StringBuilder
 * 
 * sol)
 * 1. 문자열 내 각 알파벳의 개수를 카운트
 *      이때, 홀수가 2개 이상이면 사랑고백 실패
 * 2. 홀수가 1개 이하라면 알파벳을 A 부터 Z까지 순서대로 개수/2 만큼씩 이어붙여 부분 문자열을 만든다.
 * 3. 부분문자열은 평상태와 역상태 2가지 버전으로 만들어둔 뒤,
 * 4. 홀수인 알파벳이 존재할 경우 평상태 문자열 뒤에 해당 알파벳을 붙인다.
 * 5. 평상태 문자열 뒤에 역상태 문자열을 붙인다.
 * 
 */

public class BOJ_1213_팰린드롬만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] name = br.readLine().toCharArray();
		
		// 문자열 내 알파벳 개수 카운트
		int[] alphabets = new int[26];
		for (char a : name) {
			alphabets[a-'A']++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		Character middle = null;
		boolean odd = false;          // 홀수 1개 이상인지
		boolean flag = false;         // 사랑고백 실패인지
		
		for (int i=0; i<26; i++) {
			int cnt = alphabets[i];
			if (cnt==0) continue;     // 존재하는 알파벳만 처리(A~Z 알파벳 배열을 모두 만들어두었으므로)
			
			char alphabet = (char)(i+'A');
			if (cnt%2!=0) {                   // 홀수 개수인데
				if (odd) {                    // 이미 홀수 개수 알파벳이 있었다면 실패
					sb.replace(0, sb.length(), "I'm Sorry Hansoo");
					flag = true;
					break;
				}
				else {                        // 홀수 개수 알파벳이 처음이라면 기억
					odd = true;
					middle = alphabet;
				}
			} 
			// 현재 알파벳을 개수만큼 평상태 문자열에 붙임
			for (int size=cnt/2, j=0; j<size; j++) sb.append(alphabet);
		}
		
		if (!flag) {                                  // 실패한 경우가 아니라면 평상태 + 홀수 알파벳 + 역상태 붙여 완성
			String prefix = sb.toString();
			if (middle != null) sb.append(middle);    // 홀수인 알파벳
			sb.reverse().insert(0, prefix);           // 역상태 앞에 평상태 + 홀수인 알파벳 붙임
		}
		System.out.println(sb);
	}

}
