import java.util.*;
import java.io.*;

/**
 * [1109] BOJ 1181 단어 정렬
 *	문자열, 정렬
 *
 * sol)
 *  사용자 정의 타입이 아니므로 Comparator 인터페이스의 compare 메서드를 오버라이딩하여 정렬한다.
 * 
 */

public class BOJ_1181_단어정렬 {
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> words = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 단어 입력
		int N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			words.add(br.readLine());
		}
		
		// 입력 단어 정렬
		Collections.sort(words, (o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length());
		
		// 출력
		StringBuilder sb = new StringBuilder();
		String prevWord = words.get(0);
		sb.append(prevWord).append("\n");
		
		for (int i=1; i<N; i++) {
			String curWord = words.get(i);
			if (!curWord.equals(prevWord)) sb.append(curWord).append("\n");
			prevWord = curWord;
		}
		
		System.out.println(sb);
	}

}
