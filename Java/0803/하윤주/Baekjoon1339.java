import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [0807] 백준 1339 단어수학
 * 배열을 이용한 구현
 * 알파벳의 자리수가 1순위, 등장 횟수가 2순위
 */

public class Baekjoon1339 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 대문자 알파벳 순서대로 자리수 저장할 배열 생성
		int[] alphabets = new int[26];
		int N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			char[] word = br.readLine().toCharArray();
			int len = word.length;
			
			// 입력받은 단어를 거꾸로 돌며 자리수 저장(일의 자리부터 계산)
			for (int j=len-1; j>=0; j--) {
				int place = len - j - 1;    // 입력받은 단어의 길이를 통해 자리수 계산
				alphabets[(int)word[j]-65] += (int)(Math.pow(10, place));   // 알파벳 유니코드 값을 이용한 배열 인덱싱, 해당 자리수 수들 중 최소인 값을 배열에 저장
			}
		}
		
		// 배열에 저장된 자리수의 크기를 기준으로 정렬한 뒤 숫자 할당
		int answer = 0;
		int num = 9;
		Arrays.sort(alphabets);  // 오름차순
		// Arrays.sort(alphabets, Collections.reverseOrder());   내림차순 시 Wrapper를 이용해 Integer형 배열을 만들어야 함. Integer형 배열은 초기값이 null임을 주의.
		for (int a=25; a>=0; a--) {
			if (alphabets[a] != 0) answer += alphabets[a] * num--;   // 자리수가 큰 순서대로 큰 숫자 할당
		}
		
		System.out.println(answer);
	}

}
