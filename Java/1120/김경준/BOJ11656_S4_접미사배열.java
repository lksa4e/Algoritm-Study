import java.io.*;
import java.util.*;
/**
 * 백준 11656 실버 4 접미사 배열
 * 메모리 : 18344kb, 시간 : 176ms
 * 
 * 모든 접미사를 만들어두고 sort하는 문제
 * 1. 접미사만들기 -> substring
 * 2. sort -> 우선순위 큐 pop
 *
 */
public class BOJ11656_S4_접미사배열 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<String> pq = new PriorityQueue<String>();
		
		String str = br.readLine();
		for(int i = str.length()-1 ; i >= 0; i--)  // 뒤에서부터 1개 2개 3개 ,,, substring 끊어서 접미사 만들기
			pq.offer(str.substring(i, str.length()));
		
		while(!pq.isEmpty()) {
			sb.append(pq.poll() + "\n");
		}
		
		System.out.print(sb);
	}
}
