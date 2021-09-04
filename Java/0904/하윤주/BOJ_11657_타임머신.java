import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [0904] 백준 11657 타임머신
 * 그래프, 최단경로, 벨만 포드
 * 
 * sol)
 * 음수 가중치가 가능한 벨만 포드 알고리즘
 * 모든 정점이 자신으로부터 출발해서 도착할 수 있는 정점을 확인하며 최단 경로를 갱신
 * 단, 음수 가중치가 가능하므로 음수 사이클이 존재하는 경우를 걸러주기 위해 위의 반복이 끝나고 나서도 값이 계속 작아지는 경로는 제외
 * 
 * 시행착오)
 * 처음에 인접행렬로 구현했는데 간선 정보 핸들링이 어려워서 간선리스트로 변경하여 풀었는데 벨만포드 알고리즘에는 간선리스트를 이용하는게 더 어울리는 것 같다
 * 
 * tc)
 * 1) 최단 경로 갱신
 * 	  O(N*M) = 500 * 6,000 = 3,000,000
 * 2) 음수 사이클 체크
 * 	  O(M) = 6,000
 *	
 */

public class BOJ_11657_타임머신 {
	static int V, E;                      // 정점, 간선 개수
	static long INF = Long.MAX_VALUE;     // 최솟값 갱신을 위한 최댓값(가중치를 더하고 빼는 과정에서 언더, 오버플로우 막기위해 long형)
	static List<Edge> edgeList;           // 간선 리스트
	static long[] distance;               // 최단 경로
	
	// 간선 리스트를 위한 간선 정보 타입
	static class Edge {
		int from, to;       // 진입, 진출 정점 인덱스
		long weight;        // 가중치
		
		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeList = new ArrayList<>();
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			edgeList.add(new Edge(A, B, C));    // 간선 정보 저장
		}
		
		// 벨만포드 알고리즘
		bellmanFord();
		
		// 출력
		StringBuilder sb = new StringBuilder();
		
		if (distance[0] == -1) sb.append(-1);     // 음수 사이클 존재하면 예외처리
		else {
			for (int i=2; i<=V; i++) {
				long result = distance[i] == INF ? -1 : distance[i];     // 최단 경로 갱신 실패(도달할 수 없는 경로)하면 예외처리
				sb.append(result).append('\n');
			}
		}
		
		System.out.print(sb);
		
	}

	// 벨만포드 알고리즘
	private static void bellmanFord() {
		distance = new long[V+1];
		Arrays.fill(distance, INF);       // 최단 경로 초기화
		distance[1] = 0;                  // 출발 정점 초기화
		
		// 1) 최단 경로 갱신
		for (int i=1; i<V; i++) {              // 모든 정점에서 출발하여 
			for (Edge edge : edgeList) {
				if (distance[edge.from] == INF) continue;       // 출발하는 정점의 최단 경로가 존재하지 않는다면 그 정점에서 출발하는 경우는 무조건 최단 경로가 될 수 없으므로 pass
				distance[edge.to] = distance[edge.from]+edge.weight < distance[edge.to] ? distance[edge.from]+edge.weight : distance[edge.to];   // 최단 경로 갱신
			}
		}
		
		// 2) 음수 사이클 체크
		for (Edge edge : edgeList) {
			if (distance[edge.from] == INF) continue;
			if (distance[edge.from]+edge.weight < distance[edge.to]) {       // 최단 경로를 다 확인했는데도 또 갱신할 수 있다는 것은 음수 사이클이 존재하는 상황을 의미
				distance[0] = -1;        // 예외처리 출력을 위해 distance 배열에서 사용하지 않는 인덱스에 flag 처리
				break;
			}
		}
	}

}
