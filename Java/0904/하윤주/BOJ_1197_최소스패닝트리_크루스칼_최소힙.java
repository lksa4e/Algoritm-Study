import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [0904] 백준 1197 최소 스패닝 트리
 * 트리, MST, 크루스칼
 * 
 * sol)
 * 우선순위 큐로 푼 코드도 함께 제출합니다~
 * 
 * tc)
 * O(E log E)
 *	
 */

public class BOJ_1197_최소스패닝트리_크루스칼_최소힙 {
	static int V, E;
	static long weights;
	static PriorityQueue<Edge> edgeQueue;     // 간선을 우선순위 큐로 관리
	
	// 간선 타입
	static class Edge implements Comparable<Edge>{
		int from, to;
		long weight;
		
		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	// Union-find
	static int[] parents;
	
	static void make() {
		parents = new int[V+1];
		for (int i=0; i<V+1; i++) parents[i] = i;
	}
	
	static int find(int p) {
		if (p == parents[p]) return p;
		return parents[p] = find(parents[p]);
	}
	
	static boolean union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		
		if (rootP == rootQ) return false;
		
		parents[rootQ] = rootP;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeQueue = new PriorityQueue<>();
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long w = Long.parseLong(st.nextToken());
			
			edgeQueue.offer(new Edge(from, to, w));        // 우선 순위 큐에 간선 정보 삽입, 정렬 필요 없음
		}
		
		kruskal();
		
		System.out.println(weights);
	}

	private static void kruskal() {
		make();
		
		int edgeCnt = 0;
		
		while(!edgeQueue.isEmpty()) {
			Edge edge = edgeQueue.poll();           // 최소 가중치 간선부터 poll
			
			if (union(edge.from, edge.to)) {
				weights += edge.weight;
				if (++edgeCnt == V-1) break;
			}
		}
	}

}
