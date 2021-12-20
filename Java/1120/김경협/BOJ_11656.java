import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * BOJ 11656번 접미사 배열
 * 
 * SubString으로 자르고
 * Priority Queue를 이용해 사전순 출력
 */

public class BOJ_11656 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int size = str.length();
		
		PriorityQueue<String> pq = new PriorityQueue<String>();
		for (int i = 0; i < size; i++) {
			pq.offer(str.substring(i));	// substring으로자르고
		}
		
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");	// pq로 출력
		}
		System.out.println(sb);
	}

}
