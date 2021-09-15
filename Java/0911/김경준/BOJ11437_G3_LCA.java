import java.io.*;
import java.util.*;

/**
 * G3 BOJ 11437 LCA :
 * 메모리 : 46512, 시간 : 500ms
 * 
 * LCA 알고리즘 중 부모를 1칸씩 올라가며 찾는 알고리즘이 아닌,
 * 1, 2, 4, 8 방식으로 log2(N)에 찾아 올라가는 최적화 알고리즘 적용
 * 
 * 시간복잡도 : LCA를 한 번 찾는데 필요한 연산 수 : log(N)
 */

public class BOJ11437_G3_LCA {
	static int N,M;
	static List<Integer>[] list;
	static int parent[][], depth[], tree_h;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		init();
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);  // 양방향 그래프 생성
			list[to].add(from);
		}
		
		make_tree(1);
		set();
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			sb.append(LCA(u, v) + "\n");
		}
		System.out.print(sb);
	}
	
	// 1단계 세팅 -> 모든 정점에 대해 바로 밑에 딸린 자식들에 대한 depth, parent값 갱신작업
	static void make_tree(int cur) {
		// 정점 cur에 연결된 정점 next에 대해
		for(int next : list[cur]) {
			if(depth[next] == -1) {  //깊이가 갱신되지 않은 노드라면(방문 X)
				parent[next][0] = cur; // next의 1번째 부모는 cur로 세팅
				depth[next] = depth[cur] + 1; // next는 cur보다 깊이가 1만큼 깊음
				make_tree(next);  // 재귀 호출
			}
		}
	}
	
	// 2단계 세팅 -> 모든 정점에 대해 1번째, 2번째, 4번째,,, 2^K번째 부모값 세팅
	static void set() {
		for(int i = 1; i < tree_h; i++) // 깊이 1 ~ tree_height까지
			for(int cur = 1; cur <= N; cur++)  // 모든 정점에 대하여 수행
				parent[cur][i] = parent[parent[cur][i-1]][i-1];
				// 나의 2번째 부모는 나의 1번째 부모의 1번째 부모이다. (like 할아버지는 아빠의 아빠)
				// 나의 4번째 부모는 나의 2번째 부모의 2번째 부모이다. 
	}
	
	// u, v의 최소 공통 조상(LCA) 찾기
	static int LCA(int u, int v) {
		
		// 계산의 편의를 위해 u를 깊이가 더 깊은 노드로 세팅하고 시작한다.
		if (depth[u] < depth[v]) {
			int temp = v;
			v = u;
			u = temp;
		}
		
		// diff = 두 노드의 깊이 차
		int diff = depth[u] - depth[v];
		
		// 정점 u와 v의 높이가 같아질때까지 점프 점프
		// 점프를 1 단위가 아닌 2의 n승 단위로 뛰기로 하였으니 2진수로 계산한다.
		// 만약 u와 v의 높이차가 11인 경우 2진수의 표현식은 1011(2) 가 된다.
		// 이는 1단계 점프 1번, 2단계 점프 1번, 8단계 점프 1번의 조합으로 u와 v의 높이를 같게 만들 수 있다는 것을 의미한다.
		for(int i = 0; diff != 0; i++) {
			
			if(diff % 2 == 1)       // 2진수로 나타내었을때 1이라면 점프
				u = parent[u][i];
			
			diff >>= 1;  // 점프의 높이를 변경
		}
		
		// 깊이가 같아졌으니 공통 조상을 찾기 위해 u와 v를 동시에 점프시킨다.
		// 단, 목표는 LCA가 아닌 LCA의 바로 밑 자식이다.
		// 단순하게 두 노드가 같은 부모를 가지는 경우를 생각해서 break한다면 그것은 LCA가 아닌 단순 공통 조상이 될 수 있다.
		if(u != v) {
			// 뛸 수 있는 최대한 먼 거리부터 뜀. 작은 거리부터 뛰면 원하는 부모 바로 밑을 구할 수 없음 -> 설명필요시 comment
			for(int i = tree_h - 1; i >= 0; i--) {
				// 부모가 -1인 경우 트리의 바깥으로 나갔다는 의미
				// u와 v의 부모가 다른 경우에만 수행, 같다는 것은 너무 멀리 나가서 공통 조상이 되어버림
				if(parent[u][i] != -1 && parent[u][i] != parent[v][i]) {
					u = parent[u][i];
					v = parent[v][i];
				}
			}
			// LCA바로 밑을 구했으니 그 위인 LCA로 값을 바꿔줌
			u = parent[u][0];
		}
		return u;
	}
	
	static void init() {
		tree_h = (int)Math.ceil(Math.log(N) / Math.log(2));
		parent = new int[N + 1][tree_h];
		list = new ArrayList[N + 1];
		depth = new int[N + 1];
		Arrays.fill(depth, -1);
		depth[1] = 0; // root Node;
		for(int i = 0; i < N+1; i++) 
			list[i] = new ArrayList<Integer>();
	}
	
}
