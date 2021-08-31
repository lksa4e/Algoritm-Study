import java.io.*;
import java.util.*;
/**
 * BOJ 2252 줄세우기: 
 * 메모리 : 45852kb  시간 : 608ms 
 * 
 * 처리해야 할 일의 순서가 있는 위상정렬 문제
 * 주의할 점 : 가능하면 쉬운 문제부터 풀어야 한다 -> 처리 후보 중 index 번호가 가장 낮은것을 선택해야함 -> pq 사용
 * 
 */

public class BOJ1766_G2_문제집{
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, degree[];
	static List<Integer> list[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		degree = new int[N+1];
		list = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) list[i] = new ArrayList<Integer>();  // Arraylist 초기화
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fst = Integer.parseInt(st.nextToken());
			int snd = Integer.parseInt(st.nextToken());
			list[fst].add(snd);    // fst를 처리하는 경우 제한을 해제하는 후행 작업들을 리스트에 추가
			degree[snd]++;         // snd를 처리하기 위해서 선행되어야 하는 작업 개수
		}
		solve();
		System.out.print(sb);
	}
	
	static void solve() {
		PriorityQueue<Integer> pq = new PriorityQueue();  // index 작은 순서대로 처리해야하므로
		
		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) pq.offer(i);   // 선행조건 없이 처음부터 바로 문제를 풀 수 있는경우
		}
		
		int count = 0;
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			sb.append(cur + " ");
			
			if(++count == N) return;     // N개의 작업을 모두 처리하면 바로 return
			
			for(int i = 0; i < list[cur].size(); i++) {  // 나를 선행 작업으로 가지고 있는 후행 작업들의 잠금해제
				int next = list[cur].get(i);
				if(--degree[next] == 0) pq.offer(next);  // 만약 나까지 처리됨으로써 모든 선행 작업이 끝났다 -> 후행 작업의 처리 가능 -> pq 삽입
			}
		}
	}
}
