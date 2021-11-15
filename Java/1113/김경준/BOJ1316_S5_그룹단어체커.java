import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1316_S5_그룹단어체커 {
	static int N,M;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		Map<String, String> pokemon = new HashMap<String, String>();
		N = Integer.parseInt(br.readLine());
		int count = 0;
		
		for(int i = 1; i <= N; i++) {
			String str = br.readLine();
			
			boolean visit[] = new boolean[26]; // 등장한 알파벳인지를 체크하기 위한 boolean 배열
			char before_char = str.charAt(0); // 연속한 문자 체크를 위한 변수
			boolean flag = true; // 그룹단어 여부
			
			for(int j = 0; j < str.length(); j++) {
				char current_char = str.charAt(j);
				// 단어의 변화가 있을 때 (단어의 변화가 없다 -> 연속단어)
				if(before_char != current_char) {
					// visit 방문처리를 해줌
					visit[before_char-'a'] = true;
					
					// 만약 기존에 사용된 적이 있는 단어라면 그룹 단어가 아님
					if(visit[current_char-'a'] == true) {
						flag = false;
						break;
					}
					before_char = current_char;
				}
			}
			if(flag) count++;
		}
		
		System.out.print(count);
	}
}
