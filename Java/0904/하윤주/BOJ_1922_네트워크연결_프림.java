import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [0904] 백준 1922 네트워크 연결
 * 트리, MST, 프림
 * 
 * sol)
 * 인접행렬을 이용하여 정점 간 관계를 구현
 * 
 * tc)
 * O((V+E) log V)
 *	
 */

public class BOJ_1922_네트워크연결_프림 {
	static int V, E, weights;
	static int[][] adjMatrix;       // 인접 행렬
	static int[] minWeight;
	static boolean[] visited;
	static PriorityQueue<Vertex> vertexQueue;     // 최단 경유지를 찾기 위한 우선순위 큐
	
	// 정점 타입
	static class Vertex implements Comparable<Vertex>{
		int idx, weight;

		public Vertex(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		adjMatrix = new int[V+1][V+1];
		
		for (int i=0; i<E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjMatrix[from][to] = w;      // 인접 행렬에 데이터 저장
			adjMatrix[to][from] = w;      // 무향 그래프이므로 양방향 저장
		}
		
		prim();
		System.out.println(weights);
	}

	// 프림 알고리즘
	private static void prim() {
		minWeight = new int[V+1];
		visited = new boolean[V+1];
		vertexQueue = new PriorityQueue<>();
		
		Arrays.fill(minWeight, Integer.MAX_VALUE);
		minWeight[1] = 0;                        // 임의의 출발 정점 초기화
		vertexQueue.offer(new Vertex(1, 0));     // 도착 정점과 해당 정점까지의 가중치
		
		while(!vertexQueue.isEmpty()) {
			Vertex v = vertexQueue.poll();       // 경유지
			int i = v.idx;
			
			if (visited[i]) continue;            // 방문했으면 경유지 채택 불가능
			visited[i] = true;
			
			weights += v.weight;                 // 현재가 최단이므로 가중치 합에 합
			
			for (int j=0; j<=V; j++) {
				int w = adjMatrix[i][j];
				if (!visited[j] && w!=0 && w<minWeight[j]) vertexQueue.offer(new Vertex(j, w));     // 인접하고 방문 안했고 최단 경로 갱신 가능하먄 큐에 삽입
			}
		}
		
	}

}
