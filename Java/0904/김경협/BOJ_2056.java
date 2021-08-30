import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  BOJ 2056번 작업
 *  
 *  작업을 진행하기 위한 선행작업이 있고,
 *  선행 작업끼리 얽히지 않은 일들은 병행처리할 수 있다.
 *  
 *  위상 정렬로 진행하면서 다음 작업에게 현재 작업까지 걸리는 시간을 누적해서 던져줬다.
 *  배열로 누적합을 구하는 작업을 추가하다보니 큐에 뺄 때와 다음 작업들을 탐색할 때 추가적인 작업이 이루어졌다.
 *  밑에 코드에 주석으로 달아놨음,
 *  
 *  시간 복잡도:
 *  N = 10000
 *  adj(N) = 100
 *  10000 * 100 = 1,000,000
 *  
 *  71,840 KB
 *  724 ms
 * 
 */

public class BOJ_2056 {
	static int N,M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		// 위상 정렬에서 상위노드의 개수를 구하는 배열과, 현재 작업이 걸리는 시간을 저장하는 배열
		int[] cntParents = new int[N + 1], taskHour = new int[N + 1];
		
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			taskHour[i] = Integer.parseInt(st.nextToken());
			// 이 작업을 하기위한 선행 작업의 개수
			int parentWork = Integer.parseInt(st.nextToken());
			for (int j = 0; j < parentWork; j++) {
				// 그래프에 선행작업들을 추가하고, 상위노드의 개수를 증가시킴
				int from = Integer.parseInt(st.nextToken());
				graph.get(from).add(i);
				cntParents[i]++;
			}
		}
		
		topological(graph, cntParents, taskHour);
	}
	
	static void topological(ArrayList<ArrayList<Integer>> graph, int[] cntParents, int[] taskHour) {
		// 큐를 이용해 먼저 들어온 작업이 먼저 나가도록
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		// 선행 관계가 없는 작업들 큐에 넣기
		for (int i = 1; i <= N; i++)
			if(cntParents[i] == 0)
				q.offer(i);
		
		// 현재까지의 누적합을 구하는 배열
		int[] sumTaskHour = new int[N + 1];
		for (int i = 0; i < N; i++) {
			int curr = q.poll();
			// 큐에서 꺼낼 때 이전 누적 시간에 자신의 시간 더해주기
			sumTaskHour[curr] += taskHour[curr];
			
			for(int next : graph.get(curr)) {
				// 다른 선행 작업까지의 누적 시간이랑 현재 선행 작업까지의 누적 시간이랑 비교해서 더 큰 값으로
				sumTaskHour[next] = Math.max(sumTaskHour[curr], sumTaskHour[next]);
				if(--cntParents[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		// 이렇게 구한 누적 합 중에서 가장 큰 값이 모든 작업을 완료하기 위한 최소 시간이다.
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++)
			max = Math.max(max, sumTaskHour[i]);
		
		System.out.println(max);
	}
}
