import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * BOJ 11437번 LCA
 * 
 * 트리에서 두 노드의 가장 가까운 공통 조상을 찾는 문제.
 * 
 * sparse table을 적용한 것과 적용하지 않은 것에서 거의 4배의 시간 차이가 났다.
 * 만약 sparse table로 풀려면 17435 합성함수 문제부터 풀고 이 문제를 푸는게
 * 더 좋아보인다.
 * 
 * 51496KB	508ms
 */

public class BOJ_11437 {
	static int max_anc;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		// 가장 높이 올라갈 수 있는 2의 제곱수 조상은, log2(N+1)보다 작고 가장 큰 정수이다.
		max_anc = (int) Math.floor(Math.log10(N+1) / Math.log10(2));
		
		// 각 정점들의 인접 값을 저장할 리스트 배열 생성
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<Integer>();
		
		// 각 정점들의 2제곱수 조상들을 저장할 배열 생성
		int[][] anc = new int[N + 1][max_anc + 1];
		// 각 정점들의 현재 깊이를 저장할 배열 생성
		int[] depth = new int[N + 1];
		depth[0] = -1; // root 노드에 0이 들어갈 수 있도록 제일 초기값을 -1로 설정
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int start 	= Integer.parseInt(st.nextToken());
			int end 	= Integer.parseInt(st.nextToken());
			adj[start].add(end);
            adj[end].add(start);
		}
		
		// 모든 정점들의 depth와 anc 배열 생성
		buildTree(1, 0, depth, adj, anc);
				
		// 쿼리 받기
		int query = Integer.parseInt(br.readLine());
		for (int i = 0; i < query; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			 /*
			 * 1. 노드들의 depth가 같은지 비교해야 함.
			 * 		- 다르다면 depth가 더 큰 쪽을 anc를 이용해 조상을 타고 올라오면서 depth를 줄여준다.
			 * 2. depth가 같다면 혹은 같아졌다면 노드들이 현재 같은지 비교한다.
			 * 		- 다르다면 anc를 이용해 서로 같아질 때까지(같은 부모를 가지게 될 때까지) 같은 depth만큼 올라간다. 
			 */
			if(depth[a] != depth[b]) {
				// 항상 aNode가 더 depth가 크다고 가정, 아니라면 바꿔서 aNode가 더 크게 만들어주기
				if(depth[a] < depth[b]) {
					int tmp = b;
					b = a;
					a = tmp;
				}
				// 가장 큰 2의 제곱수 조상부터 탐색
				for (int j = max_anc; j >= 0; j--)
					// 타고 올라가고 있는 aNode의 2^j번째 조상이 bNode의 depth와 같아질 때까지 타고 올라가기
					if(depth[anc[a][j]] >= depth[b])
						a = anc[a][j];
			}
			int lca = a;
			if(a != b) {
				// 가장 큰 2의 제곱수 조상부터 탐색
				for (int j = max_anc; j >= 0; j--) {
					if(anc[a][j] != anc[b][j]) {
						a = anc[a][j];
						b = anc[b][j];
					}
					lca = anc[a][j];
				}
			}
			sb.append(lca).append("\n");
		}
		System.out.println(sb);
	}
	
	static void buildTree(int curr, int parent, int[] depth, ArrayList<Integer>[] adj, int[][] anc) {
		// 현재 노드의 depth 저장
		depth[curr] = depth[parent] + 1;
		
		// 현재 노드의 2^0번째 조상은 부모노드가 된다.
		anc[curr][0] = parent;
		
		for (int i = 1; i <= max_anc; i++) {
			// curr 노드의 2^i번째 조상의 값은
			// curr 노드의 2*2^(i-1)번째 조상과 같다.
			// 따라서 curr 노드의 2^(i-1)번째 조상의 2^(i-1)번째 조상이 curr 노드의 2^i 번째 조상이 된다.
			anc[curr][i] = anc[anc[curr][i-1]][i-1]; 
			
			if(anc[curr][i] == 0) break;	// 0이 저장 = 2^i번째 조상은 루트 범위 벗어남
		}
		
		// dfs로 인접 노드 찾으면서 정점들의 depth와 anc 배열 채워주기
		for(int adjNode : adj[curr]) {
			if(adjNode != parent)
				buildTree(adjNode, curr, depth, adj, anc);
		}
	}
}
