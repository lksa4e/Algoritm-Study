import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [0913] 백준 11437 LCA
 * 트리, LCA(Least Common Ancestor)
 * 
 * sol)
 * 각 노드 간 관계를 트리 구조로 구성한 뒤, 각 노드의 깊이를 계산해둔다.
 * 공통 조상을 구하려는 두 노드 중 깊이가 더 깊은 노드는 다른 노드와 깊이를 맞춰주고(얕은 노드의 깊이까지 타고 올라가 조상을 미리 구해둠)
 * 이후부터는 두 노드의 깊이를 동일하게 하나씩 줄여가며 공통된 조상을 만날때까지 반복
 * 
 * tc)
 * 트리 생성 : 2N
 * 깊이 계산 : N
 * 두 노드의 공통 조상 찾기 : 최대 N
 *	
 */

public class BOJ_11437_LCA {
	static int N, a, b;
	static int size;
	static List<Integer>[] tree;     // 트리
	static int[] depth, parent;      // 각 노드 별 깊이, 각 노드의 부모 노드를 저장한 배열
	static boolean[] visited;        // 깊이 계산을 위한 방문 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		tree = new ArrayList[N+1];
		depth = new int[N+1];
		parent = new int[N+1];
		visited = new boolean[N+1];
		
		// 트리 생성
		for (int i=1; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 서로 연결된 두 노드 입력
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			// 각 노드에 연결된 노드들을 저장할 리스트 생성
			if (tree[p] == null) tree[p] = new ArrayList();
			if (tree[q] == null) tree[q] = new ArrayList();
			
			// 인접 노드 리스트에 서로 연결된 노드를 저장(무향 그래프이므로 서로 저장)
			tree[p].add(q);
			tree[q].add(p);
		}
		
		// 각 노드 별 깊이 계산
		makeDepthList(1);
		
		// 두 노드를 입력받으며 최소 공통 조상 찾기
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		while(M-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 두 노드 입력
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 더 깊은 노드와 얕은 노드를 찾음
			int deep = depth[a] >= depth[b] ? a : b;
			int shallow = depth[a] < depth[b] ? a : b;
			
			// 깊은 노드를 얕은 노드의 깊이에 맞춰주기 위해 노드갭만큼 미리 조상을 찾아둠
			deep = findParent(depth[deep]-depth[shallow], deep);
			
			// 깊이가 같아진 두 노드로 최소 공통 조상 찾음
			int parentA = deep;
			int parentB = shallow;
			
			for (int i=0; i<N; i++) {
				// 공통 조상 찾으면 break
				if (parentA == parentB) {
					sb.append(parentA).append("\n");
					break;
				}
				// 공통 조상 못찾으면 부모의 부모를 다시 탐색
				parentA = parent[parentA];
				parentB = parent[parentB];
			}
		}
		sb.setLength(sb.length()-1);
		System.out.print(sb);
	}

	// 각 노드의 깊이를 저장
	private static void makeDepthList(int p) {
		visited[p] = true;
		
		for (int c : tree[p]) {     // 서로 연결된 노드 중 자식노드 찾기
			if (!visited[c]) {      // 아직 방문 안했으면 자식노드임
				depth[c] = depth[p] + 1;     // 자식 노드 깊이 ++
				parent[c] = p;               // 부모 노드도 함께 저장
				makeDepthList(c);            // 이 과정을 재귀적으로 반복
			}
		}
	}
	
	// 더 깊은 노드가 얕은 깊이 노드와 깊이갭만큼 미리 조상 찾아둠
	private static int findParent(int move, int cur) {
		int next = cur;
		while(move-->0) {           // 깊이 갭만큼
			next = parent[next];    // 조상 찾기
		}
		return next;
	}
	

}