import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 11657번 타임머신
 * 
 * 음수 가중치가 있는 경로가 있고
 * 시작점에서 각 정점까지의 최단경로를 구하는 문제이다.
 * 
 * 음수 가중치가 있기에 벨만 포드 알고리즘을 사용했다.
 * 
 * 시간 복잡도:
 * O(V*E) // 정점 수(V)만큼 Edge-relaxation(E)를 실행
 * 
 * 
 * 
 * 벨만 포드 알고리즘에서 왜 edge-relaxation이 v-1번 시행되어야 하는가?
 * 
 * s에서 u로 가는 최단 경로가 u를 제외한 모든 노드(v-1개)일 수 있다.
 * edge-relaxation이 1회 수행시 시작점에서 거리 1만큼 업데이트 된다.
 * 그런데 s에서 u로 가는 최단 경로가 u를 제외한 모든 노드이고,
 * 그 거리가 V-1일 때에(최단 경로에서 u로 가는 가장 긴 길)
 * edge-relaxation이 V-1회 실행되어야만 모든 노드의 최단 경로가 업데이트 되는 것이다.
 * 
 * 
 * 벨만 포드는 간선 정보가 정렬되거나 링크되어 있을 이유가 없음
 * 그냥 1 edge-relaxation은 모든 간선에 대해서 정점을 업데이트 하는 것
 */

public class BOJ_11657 {
	
	static class Edge{
		int start, end, w;
		Edge(int from, int to, int w) {
			this.start = from;
			this.end = to;
			this.w = w;
		}
	}
	
	static int N;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Edge[] edgeList = new Edge[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edgeList[i] = new Edge(
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		bellman_ford(edgeList);
	}
	
	// 무한히 과거로 가면 -1
	// 그렇지 않다면 1~N번 도시의 최단경로 한 줄씩 출력
	// 해당 도시로 가는 경로가 없다면 최단경로 -1로
	static void bellman_ford(Edge[] edgeList) {
		StringBuilder sb = new StringBuilder();
		
		long[] dist = new long[N+1];
		Arrays.fill(dist, INF);
		dist[1] = 0; // 시작점은 1번도시
		
		// 불필요한 edge-relaxation을 찾아내는 flag
		boolean updateFlag = false;
		for (int i = 1; i <= N; i++) {
			updateFlag = false;
			for(Edge edge : edgeList) {
				// 시작점이 계속 INF라는 것은, 그 정점으로 가는 경로가 없다는 의미이다. 이거 체크 안하면 틀림
				if(dist[edge.start] != INF && dist[edge.end] > dist[edge.start] + edge.w) {
					updateFlag = true;
					dist[edge.end] = dist[edge.start] + edge.w;
					if(i == N) {	// edge-relaxation이 N-1번 돌고 난 후에도 업데이트가 계속 되면 음수 사이클이 존재한다는 의미.
						System.out.println(-1);
						return;
					}
				}
			}
			if(!updateFlag) break;	// 이제 더이상 바뀌질 않아서 edge-relaxation을 할 필요가 없음
		}
		
		for (int i = 2; i <= N; i++)
			sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
		
		System.out.println(sb);
	}

}
