package P0831;
import java.io.*;
import java.util.*;

/**
 * 백준 2056번 작업 
 * 
 * 풀이 : 위상 정렬
 * 
 * 이전에 선행되어야하는 작업을 모두 마친 후 작업을 수행해야 하므로
 * 해당 작업의 '최장 시간'을 구해야 한다. 
 * 
 * 결과값으로 모든 작업이 완료된 후의 시간을 출력해야 하므로 
 * 가장 큰 값을 출력해주어야 함 -> 정렬 후 마지막 작업시간 출력 
 *
 * 76404KB	712ms
 */

public class Solution2056_김다빈 {

	static class Node {
		int data;
		Node link = null;
		
		public Node(int data, Node link) {
			super();
			this.data = data;
			this.link = link;
		}
	}
	
	static int N;
	static Node[] linkedList;
	static int[] indegree, weight, result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		linkedList = new Node[N+1];
		indegree = new int[N+1];
		weight = new int[N+1];
		result = new int[N+1];
		
		// 작업 시간과 인접리스트 저장 
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < num; j++) {
				int to = Integer.parseInt(st.nextToken());
				linkedList[i] = new Node(to, linkedList[i]);
				indegree[to]++; 
			}
		}
		
		topologicalSort();
		
		Arrays.sort(result);
		System.out.println(result[N]);	// 모든 작업이 끝난 시간을 출력해야 하므로 가장 큰 작업시간 출력 
	}

	private static void topologicalSort() {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
				result[i] = weight[i];
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(Node temp = linkedList[cur]; temp!=null; temp=temp.link) {
				int index = temp.data;
				
				// 최장 시간을 구하기 위한 작업 (cur 작업을 수행한 후 index 작업을 수행해야 하므로) 
				if(result[index] < result[cur] + weight[index]) {
					result[index] = result[cur] + weight[index];
				}
				
				indegree[index]--;
				if(indegree[index] == 0) queue.offer(index);
			}
		}
	}

}
