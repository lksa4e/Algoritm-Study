import java.io.*;
import java.util.*;
/**
 * BOJ 3052 나머지 : https://www.acmicpc.net/problem/3052
 * 메모리 : 14048KB, 시간 : 140ms
 * 
 * %42 값을 Map에 넣음 -> Map의 사이즈 == 서로 다른 나머지 개수
 * 
 */
public class BOJ_3052_김경준 {
	static int N;
	static Map<Integer,Integer> m = new HashMap<Integer, Integer>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for(int i = 0; i < 10; i++) {
			m.put(Integer.parseInt(br.readLine()) % 42, 1); 
		}
		System.out.println(m.size());
	}
}
