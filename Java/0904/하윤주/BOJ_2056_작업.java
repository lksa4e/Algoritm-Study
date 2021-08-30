import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [0831] 백준 2056 작업
 * 그래프, 위상정렬
 * 
 * sol)
 * 위상 정렬 응용 문제
 * - 특정 노드에 도착하기까지 출발하는 모든 노드의 종료 작업 시간을 비교해 가장 큰 경우를 채택
 * - 노드 별 작업 시간과 종료 시간을 저장할 배열을 각각 생성
 * 
 * 시행착오)
 * 모든 정점이 진입 차수가 0인 경우는 고려하지 않고, 간선을 끊으며 종료 시간을 갱신하는 과정에서만 max 종료시간을 구해서 틀림
 * 줄 세우기 문제에서도 진출 노드가 여러개인 경우를 고려하지 않아 다시 풀었는데, 문제 조건을 조금 더 깊게 생각해볼 필요가 있음..
 * 
 * tc)
 * O(V+E)
 *	
 */

public class BOJ_2056_작업 {
	static int N, max;        // 최종 종료 시간(모든 작업 소요 시간)
	static Node[] jobs;       // 각 노드 별 진출과 진입을 기록한 배열
	static int[] time, endTime, isEntered;       // 노드 별 작업 소요 시간, 종료 시간, 진입차수 개수 배열
	
	// 각 정점의 진입과 진출을 기록하기 위해 노드 타입을 생성하고 link로 다음 노드 연결
	static class Node {
		int data;
		Node link = null;
		
		public Node(int data, Node link) {
			super();
			this.data = data;
			this.link = link;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		jobs = new Node[N+1];
		time = new int[N+1];
		isEntered = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			int preJobs = Integer.parseInt(st.nextToken());
			
			// 각 노드의 진입 차수 개수 카운트
			isEntered[i] = preJobs==0 ? preJobs : 101+preJobs;      // 진입 차수 개수 기록(최초 진입 차수 0인 노드와 정렬 과정에서 0이 되는 노드들을 구분)
			
			// 진출 노드와 진입 노드를 저장
			for (int j=0; j<preJobs; j++) {
				int preJobIdx = Integer.parseInt(st.nextToken());
				jobs[preJobIdx] = new Node(i, jobs[preJobIdx]);     // 출발 노드에 도착하는 노드를 연결
			}
		}
		
		lineJobs();       // 위상 정렬
		
		System.out.println(max);
		
	}

	// 위상 정렬
	private static void lineJobs() {
		Queue<Integer> q = new ArrayDeque<>();       // 다음으로 방문할 노드를 저장한 큐
		endTime = new int[N+1];                      // 작업 별 종료 시간을 저장한 배열
		
		for (int i=1; i<=N; i++) {
	
			if(isEntered[i] == 0) {                  // 진입 차수가 0인 노드 정렬
				q.offer(i);
				endTime[i] = time[i];                // 진입 차수가 0이므로 종료 시간은 자신의 작업 소요 시간
				
				while(!q.isEmpty()) {
					int curJob = q.poll();
					
				    // 진입차수가 0인 노드에 연결된 노드들에 대해 정렬 시도
					for (Node nextJob=jobs[curJob]; nextJob!=null; nextJob=nextJob.link) { 
						int nextJobIdx = nextJob.data;
						
						// 출발한 노드의 종료 시간을 고려해 연결된 노드들의 종료 시간을 기록, 선행 관계 없는 작업은 동시에 진행하므로 실제 종료 시간은 출발 노드의 종료 시간이 가장 큰 경우에다 자신의 소요 시간을 더한 것이 됨
						endTime[nextJobIdx] = Math.max(endTime[nextJobIdx], endTime[curJob]+time[nextJobIdx]);
						
						if (--isEntered[nextJobIdx] == 101) q.offer(nextJobIdx);      // 간선을 제거하여 진입차수가 0이된다면 큐에 추가하여 정렬시도
					}
				}
			}
			max = Math.max(max, endTime[i]);      // endTime에는 각 노드의 종료 시간이 저장되어 있으므로 정답은 모든 노드를 방문하고난 다음인 종료 시간 최댓값이 됨
		}
	}

}
