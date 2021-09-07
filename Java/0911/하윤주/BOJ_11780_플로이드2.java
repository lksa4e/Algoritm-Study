import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [0907] 백준 11780 플로이드2
 * 그래프, 플로이드 워샬
 * 
 * sol)
 * 1389랑 똑같이 플로이드 워샬 문제인데 양방향 그래프가 아닌 점, 거쳐온 정점들을 추적해야하는 것, 그리고 출력이 까다로웠음
 * 
 * 이 문제도 구간합 구하기에 이어 엄청 틀렸네요..
 * 
 * tc)
 * 1) 값 업데이트, 구간합 구하기
 * 		log(1,000,000) * 10,000 = 약 200,000
 *	
 */

public class BOJ_11780_플로이드2 {
	static int N, M, INF=10000001, adjMatrix[][], course[][];       // 최댓값을 MAX_VALUE로 설정하지 않도록 주의(오버플로우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjMatrix = new int[N+1][N+1];
		course = new int[N+1][N+1];
		
		// 가중치, 거쳐온 정점 초기화
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if (i!=j) adjMatrix[i][j] = INF;
				course[i][j] = INF;
			}
		}
		
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjMatrix[s][e] = w < adjMatrix[s][e] ? w : adjMatrix[s][e];    // 기존에 저장된 가중치보다 작을때만 초기화(이부분도 초반에 에러 많이 난 부분)
			course[s][e] = s;                                               // 최초에 출발한 정점 저장해둠
		}
		
		floydWarshall();
		printMap();
		printCost();
		
	}
	
	// 최단 경로 구하기
	private static void floydWarshall() {
		for (int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {
				if (k == i) continue;        // 자기 자신이면 pass
				for (int j=1; j<=N; j++) {
					if (k == j || i == j) continue;      // 자기 자신이면 pass

					int via = adjMatrix[i][k] + adjMatrix[k][j];       // k를 경유한 비용
					int straight = adjMatrix[i][j];                    // 직선 비용
					
					if (via < straight) {                              // 경유한 것이 최단이면
						adjMatrix[i][j] = via;
						course[i][j] = course[k][j];                   // 어디를 거쳐왔는지 저장
					} 
				}
			}
		}
	}

	// 최종 최소 비용 출력
	private static void printMap() {
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				int cur = adjMatrix[i][j];
				if (cur >= INF) System.out.print(0 + " ");
				else System.out.print(cur + " ");
			}
			System.out.println();
		}
	}
	
	// 각 정점 별 최소 비용과 이를 구하기 위해 거쳐온 정점 출력
	private static void printCost() {
		Stack<Integer> stack = new Stack<>();
		
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if (course[i][j] == INF) System.out.println(0);
				else {
					stack.push(j);
					int from = j;
					while(course[i][from] != i) {
						from = course[i][from];
						stack.push(from);
					}
					stack.push(i);
					stack.push(stack.size());
					while(!stack.isEmpty()) System.out.print(stack.pop() + " ");
					System.out.println();
				}
			}
		}
	}
	
}
