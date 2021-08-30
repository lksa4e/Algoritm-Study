package P0831;
import java.util.*;
import java.io.*;

/**
 * 백준 1766번 문제집 
 * 
 * 풀이 : 위상 정렬 + 우선순위 큐 
 * 
 * 2252 줄 세우기 문제를 더 먼저 풀었는데.. 이 문제와 다른 점은 
 * '가능하면 쉬운 문제부터 풀어야 한다'는 조건 때문에 우선순위 큐로 위상 정렬 알고리즘을 구현한 점!
 * 
 * 실제로 인접 리스트를 아래와 같이 클래스로 정의하지 않고 List<List<Integer>>로 구현해본 결과
 * 48412KB	724ms의 메모리와 시간이 나왔다.
 * 
 * 크게 차이는 나지 않아서 편한 방식으로 풀어도 될 듯 합니다:)
 * 
 * 45628KB	568ms
 */

public class Solution1766_김다빈 {

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
		// 줄 세우기 문제와 유일하게 다른 점은 오직 우선순위 큐를 사용한 것..
		Queue<Integer> queue = new PriorityQueue<Integer>();
		
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
