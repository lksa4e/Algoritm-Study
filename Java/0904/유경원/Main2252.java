import java.io.*;
import java.util.*;

public class Main2252 {
	
	/*
	 * 순서가 정해져 있는  작업을 차례로 수행해야 할 때 => 위상정렬
	 */
	
	static int N, M;
	static int[] inDegree, result; // 진입차수, 결과 저장할 배열
	static List<Integer>[] graph;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inDegree = new int[N+1];
		result = new int[N+1];
		graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) graph[i] = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			inDegree[b]++;
		}
		
		topologySort();
		System.out.println(sb);
	}
	
	static void topologySort() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		for(int i=1; i<=N; i++) if(inDegree[i] == 0) q.offer(i); // 진입차수가 0인 노드 삽입
		
		for(int i=1; i<=N; i++) {
			int x = q.poll();
			result[i] = x;
			for(int j: graph[x]) if(--inDegree[j] == 0) q.offer(j); // 진입차수가 0이 되면 삽입
		}
		
		for(int i=1; i<=N; i++) sb.append(result[i]).append(" ");
		
	}
	
}