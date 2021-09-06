import java.io.*;
import java.util.*;

public class Main1389 {

	/*
	 * 모든 정점에서 모든 정점으로의 최단거리 합의 최솟값
	 * 정점의 수가 최대 100 이므로
	 * O(N^3)인 플로이드-와샬을 써도 10^6으로 충분
	 * 
	 */
	static int N, M;
	static final int INF = (int)1e9;
	static int[][] graph;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				graph[i][j] = INF; // 초기값 셋팅
			}
		}
		
		for(int i=1; i<=N; i++) graph[i][i] = 0; // 자기자신은 0
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = graph[b][a] = 1; // 양방향 가중치 1로 셋팅
		}
		
		// 플로이드 와샬
		for(int k=1; k<=N; k++) { // 거쳐가는 정점
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					// i에서 j로 가는경우와 k를 거쳐가는 경우 비교
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		int min = INF, sum, ans = 0;
		boolean flag;
		
		for(int i=1; i<=N; i++) { // 케빈베이컨 최솟값인 사람 찾기
			
			sum = 0; flag = true;
			
			for(int j=1; j<=N; j++) {
				if(graph[i][j] == INF) {
					flag = false;
					break;
				}
				sum += graph[i][j];
			}
			
			if(flag && min>sum) {
				min = sum;
				ans = i;
			}
		}
		System.out.println(ans);
	}
}