import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 1197번 최소 스패닝 트리
 * 
 * MST 문제
 * 간선 위주로 값이 주어지니
 * kruskal 알고리즘을 사용한다.
 * 네트워크 연결 코드 복붙함
 * 
 */

public class BOJ_1197 {
	static class Edge implements Comparable<Edge>{
		int start, end, w;
		public Edge(int start, int end, int w) {
			this.start = start;
			this.end = end;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	static int E, parent[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		Edge[] edgeList = new Edge[E];
		parent = new int[V + 1];
		for (int i = 1; i <= V; i++) parent[i] = i;
		
		for (int i = 0; i < edgeList.length; i++) {
			st = new StringTokenizer(br.readLine());
			edgeList[i] = new Edge(
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(edgeList);
		System.out.println(kruskal(edgeList));
	}
	
	static int kruskal(Edge[] edgeList) {
		int sum = 0;
		for (int i = 0; i < E; i++) {
			Edge edge = edgeList[i];
			if(!isSame(edge.start, edge.end)) {
				sum += edge.w;
				union(edge.start, edge.end);
			}
		}
		return sum;
	}
	
	static boolean isSame(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return true;
		else return false;
	}
	
	static int find(int a) {
		if(a==parent[a]) return a;
		parent[a] = find(parent[a]);
		return parent[a];
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot != bRoot)
			parent[aRoot] = b;
		else
			return false;
		return true;
	}

}
