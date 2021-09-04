package BaekOJ.study.date0904;

import java.io.*;
import java.util.*;

/*
 * V = 10,000, E = 100,000
 * 크루스칼 : O(ElogE) = 10만 * 16 = 160만
 * 프림 : ElogV ~ V^2 = 130만 ~ 1억
 * 이 문제는 ElogV로 풀지 않는 이상 크루스칼이 유리함
 * 
 * edge들을 정렬하고...
 * 웨이트가 작은것들부터 하나씩 체크하면서
 * 사이클이 형성이 안되면 추가하고, 형성되면 추가하지 않음
 * V개의 vertex가 MST를 구성하려면 V-1개의 edge가 필요하므로
 * V-1개의 egde가 연결되면 MST완성
 * 
 * 메모리 	시간
 * 49360	704
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

public class BaekOJ1197_배문규_Kruskal {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int V, E, parents[];
	static Edge edges[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// edge 입력
		edges = new Edge[E];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(from, to, weight); 
		}
		
		// 각 그래프의 루트 vertex 배열
		parents = new int[V+1];
		for(int i = 0; i < V; i++) parents[i] = i;

		Arrays.sort(edges);
		
		System.out.println(getResult());
		
	}

	// 그래프 붙이; 사이클이 존재하는지 확인
	public static boolean unionSet(int a, int b) {
		int rootA = findRoot(a);
		int rootB = findRoot(b);
		if(rootA == rootB) return false;
		
		parents[rootA] = rootB;
		return true;
	}
	
	// 각 그래프의 루트 찾기
	public static int findRoot(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findRoot(parents[a]);
	}
	
	// MST 가중치 구하기
	public static int getResult() {
		int cnt = 0;
		int result = 0;
		for(Edge edge : edges) { // 정렬된 모든 edge들을 순환하면서
			if(unionSet(edge.from, edge.to)){ // 사이클이 존재하지 않으면 엣지 추가
				result += edge.weight;
				if(++cnt == V-1) break; // V-1개의 edge로 모든 vertex가 연결되면 MST완성
			}
		}
		
		return result;
	}
}

