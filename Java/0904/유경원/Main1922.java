import java.io.*;
import java.util.*;

public class Main1922 {
	/*
	 * 전형적인 최소신장트리 문제
	 * 
	 * 크루스칼 
	 * 49200 644
	 * 프림
	 * 50196 496
	 * 
	 */

	static class Node implements Comparable<Node> {

		int vertex, weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int N, M;
	static ArrayList<Node>[] graph;
	static final int MAX = Integer.MAX_VALUE;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N+1];
		int[] minEdge = new int[N+1];
		
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
			minEdge[i] = MAX;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b,w));
			graph[b].add(new Node(a,w));
		}
				
		int result = 0; // 최소신장트리 비용
		minEdge[1] = 0; // 임의의 시작점 1의 간선비용을 0으로 세팅
		
		PriorityQueue<Node> pq =  new PriorityQueue<>();
		pq.offer(new Node(1,0));
		while(!pq.isEmpty()) {
			// 1. 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 찾기
			Node cur = pq.poll();
			if(!visited[cur.vertex]) {
				visited[cur.vertex] = true; // 신장트리에 포함시킴
				result += cur.weight; // 간선비용 누적
				
				// 2. 선택된 정점 기준으로 신장트리에 연결되지 않은 타 정점과의 간선 비용 최소로 업데이트
				for(Node next : graph[cur.vertex]) {
					if(!visited[next.vertex] && minEdge[next.vertex] > next.weight) {
						minEdge[next.vertex] = next.weight;
						pq.offer(next);
					}
				}
			}
		}
		
		System.out.println(result);
	}
}