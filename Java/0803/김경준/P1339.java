import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * 알파벳 등장 횟수, 자리수에 따른 비중 구하기
 * 비중에 따라 9 8 7 6,,, 숫자 지정
 * 등장 숫자에 따라 정답 계산
 */
public class P1339 {
	static int N, answer = 0;
	static String[] s;
	static Map<Character, Integer> m = new HashMap<>();
	static List<Pair> list = new ArrayList<Pair>();
	static void mapsetting() {  // 문자열에서 알파벳의 비중 구하기 + 알파벳 숫자 매칭
		for(int i = 0; i < N; i++) {
			int cnt = 0;
			for(int j = s[i].length() - 1; j >= 0; j--) {
				if(m.get(s[i].charAt(j)) == null) {
					m.put(s[i].charAt(j), (int)Math.pow(10, cnt));
				}else {
					m.put(s[i].charAt(j), m.get(s[i].charAt(j)) + (int)Math.pow(10, cnt));
				}
				cnt++;
			}
		}
		/*
		 * Map을 Value 기준으로 정렬하는 방법
		List<Map.Entry<Character, Integer>> entryList = new LinkedList<>(m.entrySet());
		entryList.sort(Map.Entry.<Character,Integer>comparingByValue().reversed());
		for(Map.Entry<Character, Integer> elem : entryList) {
			m.put(elem.getKey(), num--);
		}
		*/
		for(Map.Entry<Character, Integer> elem : m.entrySet()) {
			list.add(new Pair(elem.getKey(),elem.getValue()));
		}
		Collections.sort(list);
		
		int num = 9;
		for(Pair p : list) {
			m.put(p.x, num--);
		}
	}
	
	static void solve() {
		for(int i = 0; i < N; i++) {			
			int cnt = 0;
			for(int j = s[i].length() - 1; j >= 0; j--) {
				answer += m.get(s[i].charAt(j)) * (int)Math.pow(10, cnt);
				cnt++;
			}			
		}
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		s = new String[N];
		for(int i = 0; i < N; i++) {
			s[i] = br.readLine();
		}
		mapsetting();
		solve();
		System.out.println(answer);
		
	}
}
class Pair implements Comparable<Pair>{
	public char x;
	public int y;
	public Pair(char x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Pair p2) {
		return Integer.compare(p2.y, y);
	}
}

