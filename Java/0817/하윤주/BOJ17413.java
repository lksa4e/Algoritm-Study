import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [0817] BOJ 17413 문자열 파싱
 *
 * sol) 문자열을 잘 어르고 달래는 문제
 * tc) O(N)인데 입출력에서 시간이 많이 걸릴 것 같습니다
 */

public class BOJ17413 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] S = br.readLine().toCharArray();     // char형 배열로 문자열 저장
		boolean flag = false;        // 태그 여부
		StringBuilder tmpSb = new StringBuilder();   // 각 단어 혹은 태그 문자열을 저장할 sb
		
		for (char c : S) {
			if (c == '<') flag = true;     // 태그가 시작되면 flag 들어서 태그 문자열임을 기억
			if (c == '>') flag = false;    // 태그가 종료되면 flag 내려서 일반 단어임을 기억
			
			if (c == ' ' || c == '<' || c == '>') {    // 띄어쓰기, 태그 문자 등장 시
				sb.append(tmpSb).append(c);            // 지금까지의 단어를 최종 문자열에 붙임
				tmpSb.setLength(0);                    // 각 단어 sb는 초기화(new StringBuilder()나 delete() 보다 setLength(0)으로 초기화하는 것이 더 빠르다고 합니다)
			} else {
				if (flag) tmpSb.append(c);      // 태그 문자열은 순서대로 저장
				else tmpSb.insert(0, c);        // 일반 단어 문자열은 거꾸로 저장
			}
		}
		
		sb.append(tmpSb);         // 마지막 단어까지 최종 문자열에 붙여서 종료
		System.out.println(sb);
	}

}
