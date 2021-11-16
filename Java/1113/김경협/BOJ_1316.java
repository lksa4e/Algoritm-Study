import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 1316번 그룹 단어 체커
 * 
 * 단어가 주어지면, 알파벳들의 연속성을 체크하는 문제
 * 각 단어당 overlap을 체크하는 배열로 알파벳의 중복을 체크했고
 * preAlphabet과 alphabet 변수를 사용하여 알파벳이 연속하고 있는지 아닌지를 체크했다.
 */

public class BOJ_1316 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int count = 0;
		OUT: for (int i = 0; i < N; i++) {
			int[] overlap = new int[26];	// 알파벳 중복 체크할 배열
			char[] str = br.readLine().toCharArray();
			int size = str.length;
			
			int preAlphabet = str[0] - 'a';	// 연속된 알파벳 체크하기 위한 pre 변수
			overlap[preAlphabet]++;
			for (int j = 1; j < size; j++) {
				int alphabet = str[j] - 'a';
				
				if(preAlphabet == alphabet) continue;	// 연속된 알파벳일 경우
				if(overlap[alphabet] != 0) continue OUT;	// 연속되지 않은 알파벳일 경우,
				else {	// 이 전에 한 번도 안나왔던 알파벳 경우
					preAlphabet = alphabet;
					overlap[alphabet]++;
				}
			}
			count++;
		}
		System.out.println(count);
	}

}
