import java.io.*;
import java.util.*;
/**
 * 백준 11656 실버 4 팰린드롬 만들기
 * 메모리 : 14188kb, 시간 : 124ms
 * 
 * 구현
 * 1. 가능/불가능 판단 -> 홀수번 등장하는 알파벳이 2개 이상인 경우 불가능
 * 	   1.1 ) 길이가 짝수인 경우 -> 홀수번 등장하는 알파벳은 무조건 짝수개 -> 0일때는 상관 없으니 2 이상으로 모든 경우의 수 체크 가능
 *     1.2 ) 길이가 홀수인 경우 -> 홀수번 등장하는 알파벳은 1개 이상 -> 1일때는 상관없으니 2 이상으로 모든 경우의 수 체크 가능
 * 
 * 2. 팰린드롬 만들기 
 *  -> A~Z까지 하나씩 보면서 남은 알파벳 개수가 2개 이상이면 앞 뒤에 하나씩 넣고 남은알파벳 개수 2 감소
 *     -> if 길이가 홀수개인 경우?
 *        -> 전체중에 하나 남은 나머지 알파벳을 중간 위치에 넣음
 *
 */
public class BOJ1213_S4_팰린드롬만들기 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		int charCount[] = new int[26];
		int str_length = str.length();
		char answer[] = new char[str_length];
		
		// 알파벳 등장횟수 count
		for(int i = 0; i < str_length; i++) 
			charCount[str.charAt(i)-'A']++;
		
		// 홀수번 등장 알파벳 count
		int cnt = 0;
		for(int i = 0; i < 26; i++)
			if(charCount[i] % 2 == 1) cnt++;
		
		// 팰린드롬 완성 가능/불가 체크
		if(cnt > 1) {
			System.out.print("I'm Sorry Hansoo");
			return;
		}

		// 팰린드롬 만들기
		int alpha_pointer = 0;  // 현재 탐색중인 알파벳 포인터, A부터 시작하므로 초기값 0
		for(int i = 0; i < str_length/2; i++) { // 앞뒤 동시에 채우므로 length/2 까지만 탐색
			while(charCount[alpha_pointer] == 0) // 사용할 수 없는 알파벳 건너뛰기
				alpha_pointer++;
			
			if(charCount[alpha_pointer] == 1) {  // 홀수여도 건너뛰기
				alpha_pointer++;
				i--;
				continue;
			}else {  // 2개 이상 알파벳 있는경우 앞뒤로 채워넣기
				answer[i] = (char)(alpha_pointer + 'A');
				answer[str_length - 1 - i] = (char)(alpha_pointer + 'A');
				charCount[alpha_pointer] -= 2;
			}
		}
		
		if(str_length % 2 == 1) {       // 홀수 길이인 경우 가운데 글자 채워주기
			for(int i = 0; i < 26; i++) {
				if(charCount[i] == 1) {
					answer[str_length/2] = (char)(i + 'A');
					break;
				}
			}
		}
		
		System.out.print(answer.clone());
	}
}
