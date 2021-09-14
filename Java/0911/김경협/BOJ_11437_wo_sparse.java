import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * LCA 문제
 * 
 * sparse table을 적용하지 않고 한칸씩 부모따라서 올라가도록 풀었음
 * 
 * 51124KB	2060ms
 */

public class BOJ_11437_wo_sparse {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		// 각 정점들의 자식 값을 저장할 리스트 배열 생성
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<Integer>();
		// 각 정점들의 부모를 저장하는 배열
		int[] parent = new int[N + 1];
		// 각 정점들의 현재 깊이를 저장할 배열 생성
		int[] depth = new int[N + 1];
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int start 	= Integer.parseInt(st.nextToken());
			int end 	= Integer.parseInt(st.nextToken());
			adj[start].add(end);
			adj[end].add(start);
		}
		
		depth[0] = -1; // root 노드(1)의 depth에 0이 들어갈 수 있도록 0의 depth를 -1로 설정
		// 모든 정점들의 depth와 anc 배열 생성
		buildTree(1, 0, depth, adj, parent);
				
		// 쿼리 받기
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 깊이 맞춰주기
			if(depth[a] != depth[b]) {
				// depth[a] > depth[b]가 되도록
				if(depth[a] < depth[b]) {
					int tmp = a;
					a = b;
					b = tmp;
				}
				int climbCnt = depth[a] - depth[b];
				while(climbCnt-- > 0)
					a = parent[a];
			}
			
			// a와 b가 같아질때까지 부모타고 올라가기
			while(a != b) {
				a = parent[a];
				b = parent[b];
			}
			sb.append(a).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void buildTree(int curr, int parent, int[] depth, ArrayList<Integer>[] adj, int[] parents) {
		// 현재 노드의 depth 저장
		depth[curr] = depth[parent] + 1;
		
		// 현재 노드의 부모 저장
		parents[curr] = parent;
		
		for(int adjNode : adj[curr])
			if(adjNode != parent)
				buildTree(adjNode, curr, depth, adj, parents);
	}
}
