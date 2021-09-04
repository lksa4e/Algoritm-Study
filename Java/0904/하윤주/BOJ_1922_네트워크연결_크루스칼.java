import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [0904] 백준 1922 네트워크 연결
 * 트리, MST, 크루스칼
 * 
 * sol)
 * 최소 스패닝 트리 문제와 입력 한줄 빼고 다 같음
 * 
 * tc)
 * O(E log E)
 *	
 */

public class BOJ_1922_네트워크연결_크루스칼 {
	static int V, E, weights;
	static PriorityQueue<Edge> edgeQueue;     // 간선을 우선순위 큐로 관리
	
	// 간선 타입
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	// union-find
	static int[] parents;
	
	static void make() {
		parents = new int[V+1];
		for (int i=0; i<=V; i++) parents[i] = -1;      // 너무 똑같아서 집합 원소 개수 구하는 버전으로 구현
	}
	
	static int find(int p) {
		if (parents[p] < 0) return p;             // 대표자 배열 값이 음수라면 특정 집합의 대표자임을 의미
		return parents[p] = find(parents[p]);
	}
	
	static boolean union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		
		if (rootP == rootQ) return false;
		
		if (parents[rootP] < parents[rootQ]) {    // 음수 값이 클수로 절댓값이 큼 -> 원소 개수가 더 많음
			parents[rootP] += parents[rootQ];     // 원소가 더 많은 집합으로 결합
			parents[rootQ] = rootP;               // 합쳐진 집합의 대표자는 상대 집합의 대표자로 설정
		}
		else {
			parents[rootQ] += parents[rootP];
			parents[rootP] = rootQ;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		edgeQueue = new PriorityQueue<>();
		
		for (int i=0; i<E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edgeQueue.offer(new Edge(from, to, w));
		}
		
		kruskal();
		System.out.println(weights);
	}

	// 크루스칼
	private static void kruskal() {
		make();                                   // 각 정점 서로소 집합 생성
		
		int edgeCnt = 0;
		while(!edgeQueue.isEmpty()) {
			Edge edge = edgeQueue.poll();         // 최단 가중치 간선
			
			if (union(edge.from, edge.to)) {      // 간선을 연결할 수 있으면
				weights += edge.weight;           // 해당 간선을 최단 경로로 채택
				if (++edgeCnt == V-1) break;      // 모든 정점 연결되면 탈출
			}
		}
		
	}

}
