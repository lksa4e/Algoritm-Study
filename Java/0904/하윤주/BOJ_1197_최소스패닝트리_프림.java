import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [0904] 백준 1197 최소 스패닝 트리
 * 트리, MST, 프림
 * 
 * sol)
 * 정점을 중심으로 각 정점을 경유해보며 최소 가중치 합을 갱신해나가는 프림 알고리즘
 * 인접 리스트를 이용하여 각 정점을 기준으로 타 정점과의 가중치 정보를 저장
 * 우선순위 큐를 이용하여 경유지를 선택하는 과정을 최적화
 * 
 * 시행착오)
 * 이 문제가 간선 개수 크기가 꽤 큰편이라 인접행렬로 구현했다가 메모리 초과가 떴습니다.. 
 * 뭔가 그럴 줄 알았지만 오기로 마저 풀었는데 처음부터 인접 리스트로 풀걸 그랬습니다
 * 
 * 그리고 각 간선의 가중치 크기 범위가 int형 범위에 꽉차기때문에 가중치합을 구하다보면 오버플로우가 발생할 수 있을 것 같아 long형으로 구현했습니다
 * 
 * tc)
 * - 정점만큼 반복하며 최단 경로 경유지 탐색 : O(V log V)
 * - 경유지로부터 인접한 정점의 최단 경로 갱신 : O(E log V)
 * O((V+E) log V)
 *	
 */

public class BOJ_1197_최소스패닝트리_프림 {
	static int V, E;                // 정점, 간선 개수
	static long weights;            // 최종 가중치 합
	static Vertex[] adjList;        // 인접 리스트
	static long[] minWeight;        // 각 정점까지의 최단 경로 합
	static boolean[] visited;       // 정점 방문 여부
	static PriorityQueue<Vertex> vertexQueue;      // 최소 가중치 경유지를 구하기 위한 우선순위 큐
	
	// 인접리스트를 위한 정점 타입
	static class Vertex implements Comparable<Vertex>{
		int from, to;           // 진출, 진입 정점 인덱스
		long weight;            // 가중치
		Vertex link = null;     // 연결된 정점(자기 자신에서 출발해서 도착하는 정점, to 인덱스의 정점)
		
		// 프림 알고리즘에서 사용할 우선순위 큐를 위한 생성자(정점의 인덱스 정보와 가중치만 필요)
		public Vertex(int from, long weight) {
			super();
			this.from = from;
			this.weight = weight;
		}
		
		// 인접 리스트 생성을 위한 생성자(인접리스트 자체 인덱스가 from 인덱스를 의미하므로 도착 정점 인덱스, 가중치, 도착 정점 정보 필요)
		public Vertex(int to, long weight, Vertex link) {
			this.to = to;
			this.weight = weight;
			this.link = link;
		}
		
		// 정렬을 위한 비교
		@Override
		public int compareTo(Vertex o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjList = new Vertex[V+1];
		
		// 인접 리스트 생성
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long w = Long.parseLong(st.nextToken());
			
			adjList[from] = new Vertex(to, w, adjList[from]);     // 무향 그래프이므로 인접 리스트에 진출, 진입 정보를 각각 저장해야함
			adjList[to] = new Vertex(from, w, adjList[to]);
		}
		
		// 프림 알고리즘
		prim();
		
		System.out.println(weights);

	}

	// 프림 알고리즘
	private static void prim() {
		visited = new boolean[V+1];               // 방문 여부 초기화
		minWeight = new long[V+1];                // 최단 경로 초기화
		vertexQueue = new PriorityQueue<>();      // 최단 거리의 경유지 찾기위한 큐 초기화
		
		Arrays.fill(minWeight, Integer.MAX_VALUE);
		minWeight[1] = 0;                         // 최초에 출발하는 임의의 정점
		vertexQueue.offer(new Vertex(1, 0));      // 큐에 도착하는 정점의 인덱스와 해당 정점으로가는 가중치 저장
		
		while(!vertexQueue.isEmpty()) {
			Vertex vertex = vertexQueue.poll();
			int from = vertex.from;
			
			if (visited[from]) continue;          // 최단 거리의 정점에 이미 방문한 적 있으면 경유지로 채택 안하고 pass
			visited[from] = true;                 // 방문 처리
			
			weights += vertex.weight;             // 경유지로 채택됐다면 현재 경로가 최단임을 의미하므로 최종 가중치에 합함
			
			// 경유지로부터 인접한 정점을 확인하며 가능하다면 인접 정점까지의 가중치 갱신
			for (Vertex curV=adjList[vertex.from]; curV!=null; curV=curV.link) {
				int nextV = curV.to;
				long nextW = curV.weight;
				if (!visited[nextV] && nextW<minWeight[nextV]) {       // 인접 정점이 아직 방문 안한 상태이며 가중치 갱신 가능하면 갱신
					vertexQueue.offer(new Vertex(nextV, nextW));
				}
			}
		}
	}

}
