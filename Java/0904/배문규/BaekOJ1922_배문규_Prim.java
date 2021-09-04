package BaekOJ.study.date0904;

import java.io.*;
import java.util.*;

/*
 * O(V^2)의 인접행렬 프림 알고리즘
 * V 범위가 작아서 ElogV ~ V^2 = 둘 다 100만으로 동일하므로 인접행렬로 풀었음
 * 
 * 인접 행렬을 만들어서 V^2 반복으로 
 * 모든 vertex들을 순환하며 그래프에 포함되지 않은 그래프와 가장 가까운 vertex를 찾아
 * 그래프에 추가하고, 그래프에 포함되지 않은 나머지 vertex들과의 최소 웨이트를 갱신함
 * 
 * 메모리 	시간
 * 46176	412
 */
public class BaekOJ1922_배문규_Prim {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int V, E, graph[][], minWeight[];
	static boolean check[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		setGraph();
		check = new boolean[V];		
		minWeight = new int[V];
		Arrays.fill(minWeight, Integer.MAX_VALUE);
		
		System.out.println(prim());
	}
	
	// 무향 그래프이니까 from -> to, to -> from 둘다 추가
	public static void setGraph() throws IOException {
		graph = new int[V][V]; 
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from =  Integer.parseInt(st.nextToken())-1;
			int to =  Integer.parseInt(st.nextToken())-1;
			int weight =  Integer.parseInt(st.nextToken());
			graph[from][to] = weight;
			graph[to][from] = weight;
		}
	}
	
	public static int prim(){
		// 그래프에 시작점 추가
		int _min = 0;
		minWeight[0] = 0;
		
		// 모든 vertex 순환 
		for(int i = 0; i < V; i++) {
			
			int temp = Integer.MAX_VALUE;
			int minVertex = -1;
			// 현재 그래프에 포함되지 않은 가장 가까운 vertex 탐색
			for(int j = 0; j < V; j++) {
				if(!check[j] && temp > minWeight[j]) {
					temp = minWeight[j];
					minVertex = j;
				}
			}
			 
			// 그래프에 포함시키고 토탈 웨이트 추가
			check[minVertex] = true;
			_min += temp;
			
			// 현재 그래프에 포함되지 않은 다른 vertex들과의 웨이트 갱신
			for(int j = 0; j < V; j++) 
				if(!check[j] && graph[minVertex][j] != 0 && minWeight[j] > graph[minVertex][j]) 
					minWeight[j] = graph[minVertex][j];
		}
		
		return _min;
	}
}
