import java.util.*;
import java.io.*;

/**
 * 백준 11780번 플로이드 2
 * 
 * 풀이 : 플로이드 와샬 알고리즘 
 * 
 * 1389번과 다른 점은 처음 weight 값이 구간마다 다르고 같은 경로에 여러 간선이 존재할 수 있으므로
 * 처음 설정해줄 때 가장 작은 가중치를 넣어주어야 한다.
 * 
 * 그리고 경로 출력을 위해서 2차원의 path 배열로 관리
 * 처음 간선을 입력받을 때 start -> end 구간에 대해 start점으로 path 설정, 만약 갈 수 없다면 -1
 * 
 * 최단 거리를 업데이트하면서 만약 경유지를 거치는게 더 빠르다면, 바로 start 직전 정점으로 갱신 
 * 
 * 출력은 도착지점부터 출발지점까지 역순으로 백트래킹하면서 Stack에 저장 -> Stack의 크기(경로의 길이) -> Stack 순서대로 출력 
 * 
 * 이번엔 Integer.MAX_VALUE로 설정해주는 대신 long[][]으로 weight를 선언해주니 편하게 계산할 수 있었다!
 * 
 * 
 * 53516KB	716ms
 */
public class Solution11780_김다빈 {

	static int N, M;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		long[][] city = new long[N+1][N+1];
		int[][] path = new int[N+1][N+1];
		
		for (int i = 0; i <= N; i++) {
			Arrays.fill(city[i], INF);
			Arrays.fill(path[i], -1);	// 갈 수 없는 경우 -1로 초기값 INF 설정 
			city[i][i] = 0;				// 자기자신인 경우 0
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 만약 같은 경로에 여러 가중치값이 들어온다면 더 작은 가중치로 설정해주어야 함!!!!
			city[start][end] = Math.min(city[start][end], weight);
			path[start][end] = start;	// 시작점으로 설정 
		}
		
		floydWarshall(city, path);
	}

	private static void floydWarshall(long[][] city, int[][] path) {
		for (int cur = 1; cur <= N; cur++) {	// 현재 지나는 정점 cur 
			for (int start = 1; start <= N; start++) {
				for (int end = 1; end <= N; end++) {
					if(city[start][end] > city[start][cur] + city[cur][end]) {	// cur를 경유하는 것이 더 가까우면 갱신 
						city[start][end] = city[start][cur] + city[cur][end];
						path[start][end] = path[cur][end];	// 직전 정점 갱신 
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 최소비용 출력
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(city[i][j] == INF) {
					sb.append("0 ");
				} else {
					sb.append(city[i][j]+" ");
				}
			}
			sb.append("\n");
		}
		
		// 경로 출력 
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(path[i][j] == -1) sb.append(0);
				else {
					int cur = j;
					Stack<Integer> stack = new Stack<Integer>();
					stack.add(cur);	// 마지막 정점 push
					
					// 역순으로 백트래킹해서 경로 탐색 
					while(cur != i) {
						cur = path[i][cur];
						stack.add(cur);
					}
					
					sb.append(stack.size()+" ");
					while(!stack.isEmpty()) {
						sb.append(stack.pop()+" ");
					}
				}
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}

}
