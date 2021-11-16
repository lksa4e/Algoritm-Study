import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 1213번 팰린드롬 만들기
 * 
 * 앞뒤가 같은 단어를 만드는 문제.
 * 알파벳의 개수를 센 뒤, 홀수 개가 2개 이상 있으면 sorry를
 * 1개 이하면 팰린드롬으로 만들었다.
 */

public class BOJ_1213 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(solve(br.readLine().toCharArray()));

	}

	static String solve(char[] line) {
		StringBuilder sb = new StringBuilder();
		int[] alpha = new int[26];

		int size = line.length;
		for (int i = 0; i < size; i++) {	// 알파벳 몇 개인지
			alpha[line[i] - 'A']++;
		}

		// 좌우 2개짜리 하나로 해서 stringbuilder에 넣기
		for (int i = 0; i < 26; i++) {
			while (alpha[i] > 1) {
				alpha[i] -= 2;
				sb.append((char)('A' + i));
			}
		}

		int idx = -1;	// 홀수 개 계산
		for (int i = 0; i < 26; i++) {
			if (alpha[i] == 1) {
				if (idx == -1) {
					idx = i;
				} else if (idx != -1) {	// 홀수 개가 2개 이상
					return "I'm Sorry Hansoo";
				}
			}
		}
		
		String tmp = sb.toString();
		if(idx != -1) {
			sb.append((char)('A' + idx));
		}
		sb.reverse();	// 뒤집고
		return tmp + sb.toString();	// 붙이기
	}

}
