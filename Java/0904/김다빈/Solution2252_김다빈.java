package P0831;
import java.util.*;
import java.io.*;

/**
 * 백준 2252번 줄 세우기 
 * 
 * 풀이 : 위상 정렬 
 * 
 * 인접 리스트로 A -> B 를 연결하고 B를 가리키는 노드 수 증가
 * 위상 정렬 알고리즘 시작 
 * 
 * 자신을 가리키는 간선의 수가 0인 노드들을 큐에 추가 
 * 1. 큐에서 값을 꺼내며 해당 노드가 가리키는 노드의 indegree를 1 감소
 * 2. 만약 indegree가 0이 된다면 result 큐에 넣기
 * 3. 큐가 빌 때까지 반복
 * 
 * 모든 정렬이 끝나면 result 큐 순차적으로 출력 
 * 
 * 44604KB	476ms
 */

public class Solution2252_김다빈 {

	static class Node {
		int data;
		Node link = null;
		
		public Node(int data, Node link) {
			super();
			this.data = data;
			this.link = link;
		}
	}
	
	static int N, M;
	static Queue<Integer> result = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Node[] linkedList = new Node[N+1];	// 1~N번이므로 1부터 시작 
		int[] indegree = new int[N+1];
		
		// 인접 리스트 연결 + 노드를 가리키는 간선 추가 
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			linkedList[from] = new Node(to, linkedList[from]);
			indegree[to]++;
		}
		
		// 위상 정렬 알고리즘 시작 
		topologicalSort(linkedList, indegree);
		
		// 결과 출력 
		while(!result.isEmpty()) {
			sb.append(result.poll()+" ");
		}
		
		System.out.println(sb);
	}

	// 위상 정렬 알고리즘 구현 함수 
	private static void topologicalSort(Node[] linkedList, int[] indegree) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		// 자신을 가리키는 간선이 0인 노드 추가 
		for (int i = 1; i <= N; i++) {
			if(indegree[i] == 0) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			result.offer(cur);
			
			// cur 노드와 연결되어있는 간선 제거 
			for(Node temp = linkedList[cur]; temp!=null; temp = temp.link) {
				int index = temp.data;
				indegree[index]--;
				
				// 자신을 가리키는 간선이 없으면 추가 
				if(indegree[index] == 0) queue.offer(index);
			}
		}
	}
	
}
