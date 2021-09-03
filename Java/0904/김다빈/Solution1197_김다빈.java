package P0904;

import java.io.*;
import java.util.*;

/**
 * 백준 1197번 최소 스패닝 트리 
 *
 * 풀이 : 최소 스패닝 트리 + 크루스칼 알고리즘 
 * 
 * 이름 그대로 MST를 구하는 문제!
 * 
 * 크루스칼 알고리즘을 이용하여 간선을 거리가 짧은 순서대로 그래프에 포함
 * union-find 알고리즘을 이용해 사이클 제거 
 * 
 * 49396KB	604ms
 */

public class Solution1197_김다빈 {

	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static Edge[] edgeList;
	static int[] parents;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[M];
		parents = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList);
		
		// 모든 정점을 각각으로 집합으로 만들고 출발
		for(int i=0;i<=N;i++) {
			parents[i] = i;
		}
		
		int cnt = 0,result = 0;
		for (Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				if(++cnt == N) break;
			}
		}
		System.out.println(result);
	}

	private static int find(int num) {
		if(num == parents[num]) return num;
		return parents[num] = find(parents[num]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
}
