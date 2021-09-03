import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 1922번 네트워크 연결
 * 
 * MST 문제
 * 간선 위주로 값이 주어지니
 * kruskal 알고리즘을 사용한다.
 * 크루스컬 알고리즘에서 예전에 배웠던 union set을 사용하기 때문에
 * 구현하는 재미가 있었다.
 * 
 */

public class BOJ_1922 {
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
	static int M, parent[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		Edge[] edgeList = new Edge[M];
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) parent[i] = i;
		
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
		for (int i = 0; i < M; i++) {
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
