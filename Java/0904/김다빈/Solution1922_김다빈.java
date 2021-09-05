package P0904;

import java.io.*;
import java.util.*;

/**
 * 백준 1992번 네트워크 연결 
 *
 * 풀이 : 최소 스패닝 트리 + 크루스칼 알고리즘 
 * 
 * A -> B, B -> C = A -> C  :  union-find 알고리즘!
 * 
 * 1197번과 동일하게 MST 문제를 크루스칼 알고리즘으로 해결 
 * 
 * 48444KB	604ms
 */

public class Solution1922_김다빈 {

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
	
	static Edge[] computer;
	static int[] parents;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		computer = new Edge[M];
		parents = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			computer[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(computer);
		
		// 모든 정점을 각각으로 집합으로 만들고 출발
		for(int i=0;i<=N;i++) {
			parents[i] = i;
		}
		
		int cnt = 0,result = 0;
		for (Edge edge : computer) {
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
