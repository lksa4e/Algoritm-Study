import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [0928] BOJ 17472 다리 만들기 2
 * 2차원 배열, 완전탐색, BFS, MST 알고리즘
 * 
 * sol)
 * 완전 탐색으로 각 섬 사이의 모든 간선을 구한 뒤, 간선의 가중치를 기준으로 MST를 구함(크루스칼 알고리즘 이용)
 * 
 * 1. 지도 위의 각각의 섬을 넘버링해서 구분한 상태로 시작
 * 2. 모든 섬 좌표에 대해 BFS로 4방 탐색을 하며 다른 섬으로 도달할 수 있는 모든 간선을 구하고, 이를 간선 리스트로 저장
 * 3. 간선 리스트를 오름차순 정렬한 뒤, 크루스칼 알고리즘을 이용해 MST를 구하여 최소 비용 계산
 * 		단, 모든 섬이 포함되었는지 최종적으로 확인해줘야 함
 *
 * 시행착오)
 * - 아무리 최소 비용 간선이더라도 모든 정점이 포함되지 않은 경우가 있었는데 이를 체크해주지 않아 누락된 섬이 존재함.
 * 	  다른 MST문제는 무조건 모든 정점이 연결될 수 있다는 전제가 있는데 이 문제에서는 모든 정점이 연결됨이 보장되지 않으므로 마지막에 모든 섬이 포함됐는지 체크해야 했음
 * 
 */

public class BOJ_17472_다리만들기2 {
	static int N, M, paint, total;
	static int[] parents;
	static int[][] map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static ArrayList<Integer> initIsland = new ArrayList<Integer>();
	static ArrayList<Edge> edgeList = new ArrayList<>();
	
	// MST 알고리즘을 위한 간선 타입
	private static class Edge {
		int from;		// 출발 섬
		int to;			// 도착 섬
		int weight;		// 비용
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	// union-find의 서로소 집합 생성 함수
	private static void make() {
		parents = new int[paint+1];
		for (int i=0; i<paint+1; i++) parents[i] = i;
	}
	
	// union-find의 조상을 찾는 함수
	private static int find(int a) {
		if (parents[a]==a) return a;
		return parents[a] = find(parents[a]);
	}
	
	// union-find의 서로소 집합 간 결합을 시도하는 함수
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA == rootB) return false;
		
		parents[rootB] = rootA;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		// 초기 지도 입력
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==1) initIsland.add(i*M+j);		// 섬이 위치한 좌표는 기억
			}
		}
		
		// 1. 각 섬을 넘버링하여 구분
		numberingIsland();
		// 2. 각 섬의 위치에서 다른 섬으로 도달하는 모든 간선 구함
		makeEdge();
		// 3. 간선을 기준으로 두 섬을 연결해보며 모든 섬이 연결되는 최단 비용 계산
		kruskal();
		System.out.println(total);		
	}
	
	// 각 섬을 넘버링하는 함수
	private static void numberingIsland() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N*M];
		
		for (int init : initIsland) {		// 섬이 위치한 좌표를 모두 넘버링
			if (visited[init]) continue;	// BFS 탐색하며 이미 넘버링된 위치는 pass
			paint++;						// 넘버링을 위한 인덱스
			q.offer(init);
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				int x = cur/M;
				int y = cur%M;
				
				visited[cur] = true;
				map[x][y] = paint;			// 넘버링
				
				// 인접한 섬에 대해 같은 번호로 넘버링
				for (int i=0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					int idx = nx*M+ny;
					
					// 경계 내부에 있고, 아직 방문 안했으며, 섬이면 같은 번호로 넘버링
					if (isInside(nx, ny) && !visited[idx] && map[nx][ny]==1) q.offer(idx);
				}
			}
		}
	}
	
	// 각 섬에서 다른 섬으로 도달하는 간선 구하기
	private static void makeEdge() {
		for (int init : initIsland) {		// 섬이 위치한 모든 좌표에 대해 완전 탐색
			int x = init/M;
			int y = init%M;
			
			for (int i=0; i<4; i++) {
				int nx = x;
				int ny = y;
				int cnt = 0;
				
				// 다른 섬을 만나거나 경계를 벗어날 때까지 이동하며 비용 계산
				while(true) {
					nx += dx[i];
					ny += dy[i];
				
					// 경계 벗어나거나 같은 번호로 넘버링 된 같은 섬이면 pass
					if (!isInside(nx, ny) || map[nx][ny]==map[x][y]) break;
					// 다른 섬에 도달했고, 간선 비용이 2 이상일때만 간선 리스트에 저장
					if (map[nx][ny]>=1) {
						if (cnt>1) edgeList.add(new Edge(map[x][y], map[nx][ny], cnt));
						break;
					}
					cnt++;		// 간선 비용
				}
			}
		}
	}
	
	// 최소 비용 간선 기준으로 두 섬을 연결하여 MST 구하는 함수
	private static void kruskal() {
		edgeList.sort((e1, e2) -> e1.weight - e2.weight);		// 간선 리스트 오름차순 정렬
		int included = 0;										// 총 연결된 섬의 개수
		
		// 서로소 집합 생성
		make();
		
		// 오름차순 정렬된 간선에 대해(최소 비용 간선 정점 먼저 연결)
		for (Edge e : edgeList) {
			if (union(e.from, e.to)) {							// 서로소 집합이면 두 섬 연결
				total += e.weight;
				if (++included>=paint-1) break;
			}
		}
		// 아직 연결되지 않은 섬이 존재하면 예외처리
		if (included<paint-1) total = -1;
	}
	
	// 경계 체크 함수
	private static boolean isInside(int x, int y) {
		if (x>=0 && x<N && y>=0 && y<M) return true;
		return false;
	}

}
