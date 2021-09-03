import java.io.*;
import java.util.*;

public class Main11657 {

	/*
	 * 벨만포드 알고리즘
	 * 1. 출발노드 설정
	 * 2. 최단거리 테이블 초기화
	 * 3. 다음 과정을 N-1 번 반복
	 *  1) 전체 간선을 확인
	 *  2) 각 간선을 거쳐 다른 노드로 이동하는 비용을 비교하여 최단거리 테이블 갱신
	 * 4. 음수 간선 순환을 확인하려면 3번 과정을 한 번더 수행 
	 * => 이때 최단거리 테이블이 갱신된다면 음수 간선 순환 존재
	 * 
	 * 시행착오
	 * 최단거리 배열을 int로 했다가 출력초과
	 */
	static class Edge implements Comparable<Edge> {

		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int N, M;
	static Edge[] edgeList;
	static final int MAX = Integer.MAX_VALUE;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		long[] distance = new long[N+1];
		edgeList = new Edge[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(start, end, weight);
		}
				
		Arrays.fill(distance, MAX);
		distance[1] = 0; // 시작점 1의 간선비용을 0으로 세팅
		
		boolean flag = false; // 순환 여부
		// 전체 N-1번  반복
		LOOP:for (int i = 0; i < N; i++) {
	        // 매 반복마다 모든 간선을 확인하며
	    	for (Edge edge : edgeList) {
	            int cur = edge.start;
	            int next = edge.end;
	            int weight = edge.weight;
	            // 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
	            if (distance[cur] != MAX && distance[next] > distance[cur] + weight) {
	            	distance[next] = distance[cur] + weight;
	                // n번째 라운드에서도 값이 갱신된다면 음수 순환이 존재
	                if (i == N - 1) {
	                	flag = true;
	                	break LOOP;
	                }
	            }
	        }
	    }
		
		if(flag) System.out.println(-1); // 음수 순환 존재한다면 -1
		else{
			for(int i=2; i<=N; i++) sb.append((distance[i]==MAX)?-1:distance[i]).append("\n");
			System.out.println(sb);
		}
	}
}