import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [0831] 백준 1766 문제집
 * 그래프, 위상정렬
 * 
 * sol)
 * 위상정렬 알고리즘
 * 집입차수가 0인 노드들을 먼저 순서로 정렬하고 이후 이 노드들과 연결된 간선을 제거
 * 간선이 제거된 뒤 다시 진입차수가 0인 노드들을 찾아 정렬하며 위의 과정을 반복
 * 
 * 위상정렬 응용 문제
 * - 문제 조건 2번은 위상정렬을 적용해야 하고, 조건 3번은 우선순위 큐를 이용해야 한다.
 * - 우선순위 큐에 노드를 삽입할 경우 쉬운 문제(인덱스가 작은 노드) 먼저 poll 할 수 있다!
 * 
 * 시행착오)
 * 입력받을 때부터 각 노드의 진입차수 인덱스가 낮은 노드먼저 연결하고 풀어봤는데 완벽하게 정렬하지 못함...
 * 
 * tc)
 * O(V+E)
 *	
 */

public class BOJ_1766_문제집 {
	static int N, M;
	static Node[] problems;          // 각 정점 별 진출 노드가 연결된 배열
	static int[] prerequisites;      // 각 노드 별 진입차수 개수
	static StringBuilder sb;
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		problems = new Node[N+1];                 // 1~N까지이므로 인덱스+1
		prerequisites = new int[N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());       // 진출 시작점
			int p2 = Integer.parseInt(st.nextToken());       // 진입 도착점
			
			// 진출 노드와 진입 노드를 저장
			problems[p1] = new Node(p2, problems[p1]);       // 진출하는 노드에 해당 노드로부터 출발해 도착한 노드를 연결
			
			// 각 노드의 진입 차수 개수 카운트
			if (prerequisites[p2] == 0) prerequisites[p2] = 50000;      // 최초부터 진입차수가 0인 노드들과 위상정렬 과정에서 간선을 제거하여 진입차수가 0이된 노드들을 구분하기 위해 인덱스 기준 변경
			prerequisites[p2]++;                                        // 각 노드별 진입차수 카운트
		}
		
		sb = new StringBuilder();
		
		lineProblems();     // 위상정렬
		
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}

	// 위상 정렬
	private static void lineProblems() {
		PriorityQueue<Integer> q = new PriorityQueue<>();      // 쉬운 문제(인덱스가 낮은 노드)부터 정렬하기 위한 우선순위 큐
		
		for (int i=1; i<=N; i++) {
			if (prerequisites[i] == 0) q.offer(i);             // 진입차수가 0인 노드는 방문
			
			while(!q.isEmpty()) {
				int curProb = q.poll();
				sb.append(curProb).append(" ");
				
				for (Node nextProb=problems[curProb]; nextProb!=null; nextProb=nextProb.link) {     // 진입차수가 0인 노드에 연결된 노드들에 대해
					int nextIdx = nextProb.data;
					if (--prerequisites[nextIdx] == 50000) q.offer(nextIdx);                        // 간선을 제거하여 진입차수가 0이된다면 큐에 추가하여 정렬시도
				}
			}
		}
	}

}
