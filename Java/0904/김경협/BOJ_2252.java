import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  BOJ 2252번 줄 세우기
 *  
 *  위상 정렬을 사용해 학생들의 키 순서를 구하는 문제이다.
 *  
 *  위상 정렬 문제를 처음 풀어봤는데, 학부생 때 배웠던 개념이라 구현하는데
 *  크게 어렵지는 않았다. 하지만 처음에 위상 정렬 문제인지 몰라서
 *  많이 헤맸음
 *  
 *  시간 복잡도:
 *  N = 32,000
 *  M = 100,000
 *  (N + M) = 132,000
 *  
 *  47,020 KB
 *  588 ms
 */

public class BOJ_2252 {
	static int N,M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 상위 노드가 없는 노드를 구하기 위한 배열
		int[] cntParents = new int[N + 1]; 
		// 인접 리스트로 graph 저장 , TODO: arrayList 배열로도 해보기
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to	 = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			cntParents[to]++;
		}
		
		topoSort(graph, cntParents);
		
	}
	
	static void topoSort(ArrayList<ArrayList<Integer>> graph, int[] cntParents) {
		StringBuilder sb = new StringBuilder();
		// 큐로 상위 노드가 없는 노드부터 차례대로 출력
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		// 큐에 상위 노드가 없는 노드들 저장
		for (int i = 1; i <= N; i++)
			if(cntParents[i] == 0)
				q.offer(i);
		
		// 노드의 개수만큼 반복
		for (int i = 0; i < N; i++) {
			int curr = q.poll();
			sb.append(curr).append(" ");
			
			// 현재 노드의 하위 노드 cntPrev를 하나씩 빼고, cntPrev가 0이 됐다면 큐에 추가
			for(int next : graph.get(curr))
				if(--cntParents[next] == 0)
					q.offer(next);
		}
		System.out.println(sb);
	}
}
