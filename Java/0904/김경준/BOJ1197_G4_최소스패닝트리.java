import java.io.*;
import java.util.*;
/**
 * G4 BOJ 1197 최소 스패닝 트리: 
 * 메모리 : 47908kb  시간 : 652ms 
 * 
 * MST를 구하는 문제
 * kruscal을 사용함
 */

public class BOJ1197_G4_최소스패닝트리{
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M, parent[];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Tuple> pq = new PriorityQueue();
		init(pq);
		System.out.print(kruscal(pq));
	}
	
	// 크루스칼
	static int kruscal(PriorityQueue<Tuple> pq){
		int cnt = 0;
		int answer = 0;
		while(!pq.isEmpty()) {
			Tuple cur = pq.poll();
			// 만약 이미 같은 집합에 속하는 간선이라면 -> 포함하면 사이클 발생 -> continue
			if(find(cur.x) == find(cur.y)) continue;
			
			// 다른 집합이면 간선 새롭게 포함
			union(cur.x, cur.y);
			answer += cur.dist;
			
			// N-1 개의 간선을 모두 선택했다면 break
			if(cnt == N-1) break;
		}
		return answer;
	}
	
	// 입력받아주기
	static void init(PriorityQueue<Tuple> pq) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) parent[i] = -1;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fst = Integer.parseInt(st.nextToken());
			int snd = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			pq.offer(new Tuple(fst, snd, dist));
		}
	}
	
	// Tuple Class 
	static class Tuple implements Comparable<Tuple>{
		int x,y;
		long dist;
		public Tuple(int x, int y, long dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		@Override
		public int compareTo(Tuple o) {
			return Long.compare(this.dist, o.dist);
		}
	}
	
	// Union-find
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return;
		
		if(pa < pb) {
			parent[pa] += parent[pb];
			parent[pb] = a;
		}else {
			parent[pb] += parent[pa];
			parent[pa] = b;
		}
	}
	static int find(int a) {
		if(parent[a] < 0) return a;
		else return parent[a] = find(parent[a]);
	}
}
