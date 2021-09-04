package BaekOJ.study.date0904;

import java.io.*;
import java.util.*;

/*
 * 인접 행렬로 MST구성은 V^2이 걸리는데, 
 * 이 문제는 V가 1,000인 1922번 문제와 다르게 V가 10,000이라서 V^2 = 100,000,000 -> 메모리 초과가 난다. 
 * 그래서 이 문제를 Prim으로 해결하려면O(V^2)의 인접행렬이 아닌 O(ElogV)인 minHeap을 이용하여야 한다.
 * minHeap을 이용해 그래프에 포함시킬 Edge를 찾아서 문제를 풀어야 함.
 * 
 * 1. 임의의 vertex를 그래프에 포함시키고 시작점으로 정함
 * 2. 시작점과 연결된  edge들을 pq에 추가.
 * 3. pq에서 edge들을 하나씩 꺼내며 그래프에 포함되지 않은  vertex라면 그래프에 포함시키고 반복 queue에 추가
 * 4. 큐에서 vertex를 꺼내며 위 과정을 반복
 * 5. pq가 그래프와 그래프에 포함되지 않은 vertex와의 웨이트를 갱신하는 역할을 함
 * 6. 단 pq가 그래프에 포함되지 않은 edge를 리턴하는것이 아니므로 그래프에 포함된 vertex인지 체크가 필요함
 * 
 * 메모리		시간
 * 54340	660
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

public class BaekOJ1197_배문규_Prim {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int V, E;
	static boolean check[];
	static PriorityQueue<Edge> pq;
	static List<Edge>[] graph;
	static Queue<List<Edge>> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		setGraph();
		check = new boolean[V]; // 그래프에 포함됐는지 체크
		queue = new ArrayDeque<List<Edge>>(); // 현재 그래프에 포함된 vertex큐
		pq = new PriorityQueue<Edge>(); // 가장 가까운 edge를 가져오는 minHeap (무조건 그래프 밖의 edge를 가져오진 않으니 체크 필수)
		
		System.out.println(prim());
	}
	
	// 그래프 입력
	// 무향 그래프이기 때문에, from-to, to-from 각각 추가
	public static void setGraph() throws IOException {
		graph = new List[V];
		for(int i = 0; i < V; i++) graph[i] = new ArrayList<Edge>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new Edge(from, to, weight));
			graph[to].add(new Edge(to, from, weight));
		}
	}
	
	// ElogV Prim 알고리즘
	public static int prim() {
		int result = 0;
		
		// 임의의 vertex를 시작 그래프로 지정
		check[0] = true;
		queue.offer(graph[0]); 
		while(!queue.isEmpty()) {
			// 그래프에 포함된 임의의 vertex와 연결된 egde들을 minHeap에 추가
			// minHeap에서 알아서 그래프와의 가장 가까운 vertex를 가져다줘서 최솟값 갱신역할을 해줌
			List<Edge> next = queue.poll();
			for(Edge edge : next) if(!check[edge.to]) pq.offer(edge);
			
			while(!pq.isEmpty()) {
				Edge minWeight = pq.poll();	//해당 vertex와 연결된 가장 작은 weight의 vertex 
				// 그래프에 포함되지 않았다면
				// 토탈 웨이트에 추가하고, 그래프에 포함시킴
				if(!check[minWeight.to]) { 
					result += minWeight.weight;
					check[minWeight.to] = true;
					queue.offer(graph[minWeight.to]);
					break;
				}
			}
		}
		
		return result;
	}
}
