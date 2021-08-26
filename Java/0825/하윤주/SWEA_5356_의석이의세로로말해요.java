import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [0825] SWEA 5356 의석이의 세로로 말해요
 * 문자열 | 10min
 * 
 * sol)
 * 5개의 입력 문자열을 저장한 1차원 배열을 생성함
 * 최대 입력 문자열 길이를 구한 뒤, 해당 길이만큼의 새로운 단어를 생성함
 * 따라서 (0 ~ 최대 입력 문자열 길이-1)만큼의 범위로 반복을 돌며 5개 문자열의 문자를 하나씩 추출
 * 이때 입력 문자열의 길이가 현재 접근하려는 인덱스보다 작은 문자열이 있으면 pass
 * 
 * time_complex)
 * 최대 문자열 길이 : 15
 * 문자열 개수 : 5
 * O(15*5)
 * 
 */

public class SWEA_5356_의석이의세로로말해요 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("5356input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int max = 0;       // 문자열 최대 길이를 저장
			String[] words = new String[5];
			
			// 5개의 문자열을 입력받아 통째로 배열에 저장
			for (int i=0; i<5; i++) {
				words[i] = br.readLine();
				max = Math.max(max, words[i].length());    // 문자열의 최대 길이 갱신
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			
			// 문자열의 문자 인덱스별로 접근하여 새로운 단어를 생성
			for (int i=0; i<max; i++) {        // 문자열 최대 길이만큼의 새로운 단어 생성
				for (int j=0; j<5; j++) {      // 5개의 문자열의 문자를 모두 살핌
					if (words[j].length() > i) sb.append(words[j].charAt(i));    // 현재 문자열의 길이를 넘어가지 않는 선에서 인덱스로 접근하여 문자를 추출한 뒤 새로운 단어 뒤에 붙임
				}
			}
			
			System.out.println(sb);
		}
	}

}
