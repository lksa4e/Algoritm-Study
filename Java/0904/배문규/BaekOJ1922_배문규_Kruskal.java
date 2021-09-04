package BaekOJ.study.date0904;

import java.io.*;
import java.util.*;

/*
 * V = 1,000, E = 100,000
 * 크루스칼 : O(ElogE) = 10만 * 16 = 160만
 * 프림 : ElogV ~ V^2 = 둘 다 100만
 * 이 문제는 프림이 유리함
 * 
 * 1197과 동일하게 풀었으나
 * 크루스칼 연습용으로 그냥 한번 더 짜봄
 * 
 * 메모리 	시간
 * 	49936	680
 */

class Edge implements Comparable<Edge>{
	int from;
	int to;
	int weight;
	
	Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
	}
}

public class BaekOJ1922_배문규_Kruskal {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int V, E, parents[];
	static Edge[] edges;

	public static void main(String[] args) throws NumberFormatException, IOException {
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		edges = new Edge[E];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from =  Integer.parseInt(st.nextToken());
			int to =  Integer.parseInt(st.nextToken());
			int weight =  Integer.parseInt(st.nextToken());
			edges[i] = new Edge(from, to, weight);
		}

		parents = new int[V+1];
		for(int i = 0; i < V; i++) parents[i] = i;
		
		Arrays.sort(edges); 
		
		System.out.println(getResult());	
	}
	
	public static boolean unionSet(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		if(aRoot == bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}

	public static int findRoot(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findRoot(parents[a]);
	}
	
	public static int getResult() {
		int cnt = 0;
		int result = 0;
		for (Edge edge : edges) {
			if(unionSet(edge.from, edge.to)){
				result += edge.weight;
				if(++cnt == V-1) break;
			}
		}
		return result;
	}
}
