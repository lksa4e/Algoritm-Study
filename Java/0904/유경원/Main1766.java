import java.io.*;
import java.util.*;

public class Main1766 {

	/*
	 * 순서가 정해져 있는 작업을 차례로 수행한다. => 위상정렬
	 * 2252문제 와 다른 점은 가능한 쉬운 문제부터 풀어야하기 때문에 우선순위 큐 활용 
	 */
	static int N, M;
	static List<Integer>[] graph;
	static int[] inDegree;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inDegree = new int[N+1];
		graph = new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) graph[i] = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			inDegree[b]++;
		}
		
		Queue<Integer> q = new PriorityQueue<Integer>(); // 가능한 쉬운 문제부터 푼다는 조건때문에 우선순위 큐 사용
		for(int i=1; i<=N; i++) if(inDegree[i] == 0) q.offer(i);
		
		for(int i=1; i<=N; i++) {
			int x = q.poll();
			sb.append(x).append(" ");
			for(int j: graph[x]) if(--inDegree[j] == 0) q.offer(j);
		}
		System.out.println(sb);
	}
}