package P0904;

import java.io.*;
import java.util.*;

/**
 * 백준 11657번 타임머신  
 *
 * 풀이 : 벨만 포드 알고리즘 
 * 
 * 음수 가중치가 존재하는 최단 경로를 구할 때 => 벨만 포드 알고리즘 사용
 * 
 * 각 정점들을 차례로 돌아 가면서 해당 정점의 간선들을 탐색 (단 맨 처음은 시작점부터 탐색)
 * 그 간선의 가중치와 시작점에서 정점까지의 거리의 합이 해당 간선이 도달하고자 하는 정점의 기존 거리보다 작으면 업데이트
 * 
 * 음수 사이클이 있는지 판단하는 방법 => 최단 경로의 크기가 |N|-1
 * 
 * 최악의 경우,
 * N(500) * M(6000) * C(-10,000) = -300억
 * 거리를 -300억까지 저장할 수 있어야 하므로 int형이 아닌 long형으로 저장해야함!
 * 
 * 19248KB	308ms
 */

public class Solution11657_김다빈 {
	
	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	static Edge[] edgeList;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList[i] = new Edge(from, to, weight);
		}
		
		bellmanFord();
		System.out.println(sb);
	}

	private static void bellmanFord() {
		long[] distance = new long[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		// start 지점 1번 도시로 설정 
		int start = 1;
		distance[start] = 0L;

		// 음수 사이클이 존재하는지 체크하는 변수 
		boolean isCycle = false;
		
		// 최단 경로가 나올 수 있는 최대 간선의 개수가 N-1개
		// 모든 간선의 경우를 다 따져봐야함 
		for(int i = 0; i < N; i++) {
			for(Edge edge : edgeList) {
				if(distance[edge.from] != Integer.MAX_VALUE && distance[edge.to] > distance[edge.from] + edge.weight) {
					distance[edge.to] = distance[edge.from] + edge.weight;
					
					if(i == N-1) isCycle = true;
				}
			}
		}
		
		if(isCycle) {	// 음수 사이클이 존재하는 경우 
			sb.append(-1);
		} else {
			for (int i = 2; i <= N; i++) {
				if(distance[i] == Integer.MAX_VALUE) {
					sb.append(-1+"\n");
				} else {
					sb.append(distance[i]+"\n");
				}
			}			
		}
	}

}
