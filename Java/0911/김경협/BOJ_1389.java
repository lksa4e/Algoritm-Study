import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 1389번 케빈 베이컨의 6단계 법칙
 * 
 * 주어지는 친구 사이가 비용이 1인 그래프를 만들고,
 * 이 그래프를 통해서 서로에서 서로 간의 최단 거리를 구하는 문제.
 * N:N으로 최단 거리를 구하기 대문에 플로이드 와샬 알고리즘을 사용했다.
 * 
 * 시간 복잡도:
 * O(V*3) = 1,000,000
 */

public class BOJ_1389 {
	static final int INF = 10000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] dist = new int[N+1][N+1];
		
		// distance 이중 배열을 무한대로 채워주고, 자기자신은 0으로
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			dist[start][end] = 1;
			dist[end][start] = 1;
		}
		
		floyd(N,dist);
		printResult(N, dist);
	}
	
	static void floyd(int N,int[][] dist) {
		// 거쳐가는 노드 k
		for (int k = 1; k <= N; k++) {
			// 출발지 i, 도착지 j
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j],
							dist[i][k] + dist[k][j]);
				}
			}
		}
	}
	
	static void printResult(int N, int[][] dist) {
		int min = Integer.MAX_VALUE, minPerson = 0;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			
			for (int j = 1; j <= N; j++)
				sum += dist[i][j];
			
			// 각자의 케빈 베이컨 수 구하기
			if(sum < min) {
				min = sum;
				minPerson = i;
			}
		}
		System.out.println(minPerson);
	}

}
