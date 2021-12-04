import java.io.*;
import java.util.*;

/**
 * 백준 1620번 나는야 포켓몬 마스터 이다솜
 * 
 * 키(숫자)를 기준으로 값(문자열)을 저장하는 keyMap
 * 값(문자열)을 기준으로 키(숫자)를 저장하는 valueMap
 * 
 * 각각 두개를 저장하고 답을 출력할 때
 * 제일 앞 문자가 숫자면 keyMap에서, 숫자가 아닌 문자면 valueMap에서 찾기 
 * 
 * 58456KB	632ms
 */
public class Main1620_나는야포켓몬마스터이다솜 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<Integer, String> keyMap = new HashMap<Integer, String>();	// Integer -> String 
		Map<String, Integer> valueMap = new HashMap<String, Integer>();	// String -> Integer
		
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			keyMap.put(i, str);
			valueMap.put(str, i);
		}
		
		for (int i = 0; i < M; i++) {
			String input = br.readLine();
			
			if(input.charAt(0) >= '1' && input.charAt(0) <= '9') {	// 숫자 : 키인 경우 
				sb.append(keyMap.get(Integer.parseInt(input)));
			} else {	// 문자열 : 값인 경우 
				sb.append(valueMap.get(input));
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
}
