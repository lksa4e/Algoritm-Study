import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *  BOJ 1766번 문제집
 *  
 *  먼저 푸는 것이 좋은 문제는 항상 그 순서대로 푸는 위상 정렬 문제,
 *  
 *  priority queue 를 사용하여 뽑을 때 항상 난이도가 가장 쉬운 순서대로
 *  뽑히도록 했다.
 *  
 *  시간 복잡도:
 *  N = 32,000
 *  M = 100,000
 *  pq를 쓰므로 M * pq의 시간 복잡도
 *  (N+MlogM) = 32,000 + 100,000 * 16.? = 1,632,000
 *  
 *  47,325 KB
 *  582 ms
 * 
 */

public class BOJ_1766 {
	static int N,M;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 위상 정렬에서 현재 노드의 상위 노드가 몇 개인지 저장하는 배열
		int[] cntParents = new int[N + 1];
		// 인접 리스트
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++)
			graph[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to	 = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			cntParents[to]++;	// 부모 노드가 있으므로 cnt 올려주기
		}
		
		topological(graph, cntParents);
	}
	
	static void topological(ArrayList<Integer>[] graph, int[] cntParents) {
		StringBuilder sb = new StringBuilder();
		// PQ를 사용해서 항상 난이도가 낮은게 먼저 나오도록 했음
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		// pq에 상위 노드가 없는 노드들 넣기
		for (int i = 1; i <= N; i++)
			if(cntParents[i] == 0)
				pq.offer(i);
		
		// N만큼 돌면서 상위 노드가 없는 순서대로 꺼내기
		for (int i = 0; i < N; i++) {
			int curr = pq.poll();
			sb.append(curr).append(" ");
			
			// 꺼낸 노드의 하위 노드들 cntParents를 1씩 줄이고, 상위 노드가 없는 노드들은 pq에 넣기
			for(int next : graph[curr]) {
				if(--cntParents[next] == 0)
					pq.offer(next);
			}
		}
		
		System.out.println(sb);
	}
}
