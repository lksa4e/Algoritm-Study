import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * BOJ 11780번 플로이드 2
 * 
 * 플로이드 와샬로 각 정점 to 정점의 최단 거리를 구하면서
 * 동시에 최단거리의 경로를 구하는 문제.
 * 
 * 직전 경로를 저장해 놓는 기법을 통해 알고리즘을 돌면서
 * 최단 거리의 경로를 저장할 수 있었다.
 * 
 * 계속 틀려서 30분 동안 로직 뜯어보고 있었는데
 * 원인은 디버그 한다고 맵 찍어 놓은 코드를 주석 처리 안한 탓이었다.. 너무 억울하네요...
 * 
 */

public class BOJ_11780 {
	static final int INF = 10000000;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] dist = new int[N + 1][N + 1];
		int[][] path = new int[N + 1][N + 1];

		// distance 이중 배열을 무한대로 채워주고, 자기자신은 0으로
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if (dist[start][end] > weight) {
				dist[start][end] = weight;
				path[start][end] = start;
			}
		}

		floyd(dist, path);
		printResult(dist, path);
	}

	static void floyd(int[][] dist, int[][] path) {

		for (int k = 1; k <= N; k++) {

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						path[i][j] = path[k][j];
					}
				}
			}
		}
	}

	static void printResult(int[][] dist, int[][] path) {
		StringBuilder sb = new StringBuilder();
		
		// 맵 출력
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (dist[i][j] == INF)
					sb.append("0 ");
				else
					sb.append(dist[i][j]).append(" ");
			}
			sb.append("\n");
		}

		// 경로 알아내기
		Stack<Integer> stk = new Stack<Integer>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (dist[i][j] == 0 || dist[i][j] == INF)
					sb.append("0");
				else {
					stk.push(j);
					int before = j;
					
					do {
						before = path[i][before];
						stk.push(before);
					} while (before != i);

					sb.append(stk.size()).append(" ");
					while (!stk.isEmpty())
						sb.append(stk.pop()).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);

	}
}
