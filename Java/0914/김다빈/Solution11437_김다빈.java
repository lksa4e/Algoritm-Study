import java.io.*;
import java.util.*;

/**
 * 백준 11437번 LCA
 * 
 * 풀이 : LCA
 * 
 * 두 노드의 가장 가까운 공통 조상 노드를 찾기 위해
 * 1. 각 노드마다 부모 노드와 깊이 저장 -> DFS로 구현
 * 2. 두 노드의 깊이를 같게 설정
 * 3. 두 노드의 부모 노드가 일치할 때까지 부모 노드로 거슬러 올라감
 *    => 일치하면 그 부모 노드가 가장 가까운 공통 조상 노드!
 * 
 * 46772ms	1344KB
 */
public class Solution11437_김다빈 {
	
	static int[] parent, depth;
	static ArrayList<Integer> linkedList[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		depth = new int[N+1];
		
		linkedList = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			linkedList[i] = new ArrayList<Integer>();
		}
		
		// 인접리스트 연결 -> (N-1)개 입력받음
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			linkedList[from].add(to);
			linkedList[to].add(from);
		}
		
		// dfs로 깊이, 부모 노드 저장
		// root node 1의 깊이 = 0, 부모 노드 = -1로 시작
		dfs(1, 0, -1);
		
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(LCA(a,b)+"\n");
		}
		
		System.out.println(sb);
	}

	private static int LCA(int a, int b) {
		int depthA = depth[a];
		int depthB = depth[b];
		
		// a와 b의 깊이를 동일하게 설정 
		while(depthA > depthB) {
			a = parent[a];
			depthA--;
		}
		while(depthA < depthB) {
			b = parent[b];
			depthB--;
		}
		
		// a와 b의 깊이가 같아졌다면, 두 정점의 부모가 같아질 때까지 비교
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}

	private static void dfs(int curNode, int d, int p) {
		depth[curNode] = d;
		parent[curNode] = p;
		
		for(int next : linkedList[curNode]) {
			if(next != p) {
				dfs(next, d+1, curNode);
			}
		}
	}

}
