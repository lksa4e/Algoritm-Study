import java.io.*;
import java.util.*;

public class Main11437 {
	 
	/*
	 * LCA (최소 공통 조상)
	 * 1. 모든 노드에 대한 깊이
	 * 2. LCA를 찾을 두 노드 확인
	 * 2-1. 두 노드 깊이가 동일할때까지 거슬러 올라감
	 * 2-2. 부모 같아질때까지 두 노드의 부모방향으로 거슬러 올라감
	 * 3. 모든 LCA(a,b) 연산에 대해 2번과정 반복
	 * 
	 * 2번과정에서 거슬러 올라갈때 2의 제곱형태로 올라가도록 하기
	 * 총 15칸일 경우 8 -> 4 -> 2 -> 1
	 * 각 노드의 2^i번째 부모 정보 기록
	 * 
	 * 매 쿼리마다 부모 거슬러 올라감 O(logN)
	 * 따라서 쿼리 개수 M일때 O(MlogN)
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	static int N, M;
	static int[][] parent; // 부모 노드 정보
	static int[] depthOf; // 각 노드 깊이
	static boolean[] visited; // 각 노드 방문정보
	static ArrayList[] graph;
	static final int MAX_LEVEL = 19;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1][MAX_LEVEL];
		depthOf = new int[N+1];
		visited = new boolean[N+1];
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; i++) graph[i] = new ArrayList<Integer>();
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		setParent();
		
		M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a,b)).append("\n");
		}
		
		System.out.println(sb);
	}

	// 각 노드 깊이 구하기
	private static void dfs(int node, int depth) {
		visited[node] = true;
		depthOf[node] = depth;
		
		for(int i=0; i<graph[node].size(); i++) {
			int nextNode = (int) graph[node].get(i);
			if(visited[nextNode]) continue;
			
			parent[nextNode][0] = node;
			dfs(nextNode, depth+1);
		}
	}
	
	// 부모 관계 설정
	private static void setParent() {
		dfs(1,0); // 루트 노드 1번, 깊이 0
		for(int i=1; i<MAX_LEVEL; i++) {
			for(int j=1; j<=N; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1]; // 2의 제곱형태로 건너뛰었을때 부모값 기록
			}
		}
	}
	
	// A, B LCA 찾기
	private static int lca(int a, int b) {
		if(depthOf[a] > depthOf[b]) { // b가 더 깊도록 설정
			int t = a;
			a = b;
			b = t;
		}
		
		for(int i=MAX_LEVEL-1; i>=0; i--) { // 깊이 동일할때까지 2의 제곱형태로 거슬러 올라감
			if(depthOf[b] - depthOf[a] >= (1<<i)) {
				b = parent[b][i];
			}
		}
		
		if(a==b) return a; // 같다면 종료
		
		for(int i=MAX_LEVEL-1; i>=0; i--) { // 부모 같아질때까지 거슬러올라감
			if(parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		return parent[a][0];
	}
}