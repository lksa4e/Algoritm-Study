import java.io.*;
import java.util.*;

/**
 * G2 BOJ 17472 다리 만들기2 :
 * 메모리 : 14252kb, 시간 : 128ms
 * 
 * 1. 1로 입력받은 모든 섬을 각각 넘버링
 * 2. N*M 탐색하며 각 점에서 다리를 만들어보고, 다른 섬으로의 다리를 만들 수 있으면 edge list에 추가
 * 3. edge list를 사용해 kruskal 알고리즘 수행 (N개 섬에서 N-1개 간선이 사용 안되었으면 false)
 * 
 */
public class BOJ17472_G2_다리만들기2 {
	static int N, M, map[][], parent[], island_num, answer = Integer.MAX_VALUE;
	static PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
	static int dx[] = { 1, 0, 0, -1 };
	static int dy[] = { 0, 1, -1, 0 };
	static boolean visit[][];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		map = new int[N+2][M+2];
		visit = new boolean[N+2][M+2];
		for(int i = 0; i < N+2; i++) Arrays.fill(map[i], -1);
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 각 섬에 넘버링 하기
		island_num = 1;
		for(int i = 1; i<= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(map[i][j] == 1 && !visit[i][j])
					mark(i,j, island_num++);
			}
		}
		 
		// 모든 좌표에서 다리 만들어보기
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(map[i][j] != 0) set_bridge(i,j);
			}
		}
		
		// union-find 용도 parent 배열
		parent = new int[island_num + 1];
		for(int i = 1; i <= island_num; i++) parent[i] = i;
		
		// kruskal 알고리즘
		int cnt = 0, sum = 0;
		while(!edges.isEmpty()) {
			Edge cur = edges.poll();
			if(union(cur.x, cur.y)) { // 합칠 수 있으면 합치기
				cnt++;
				sum += cur.dist;
			}
		}
		
		// 만약 N개 섬 사이에서 N-1개 간선이 선택 안되었다면 -> 연결 불가
		if(cnt < island_num - 2) System.out.println(-1);
		else System.out.println(sum);
	}
	
	// x, y 좌표에서 4방으로 다리 놔보기
	static void set_bridge(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int cnt = 0;
			// 다리 놓을 수 있는만큼 계속 놔보기
			while (map[nx][ny] == 0) {
				nx += dx[i];
				ny += dy[i];
				cnt++;
			}
			
			if(map[nx][ny] == -1) continue; // 다리 도착지점이 영역 바깥
			if(map[nx][ny] == map[x][y]) continue; // 다리 도착지점이 같은 섬
			if(cnt < 2) continue; // 다리 길이가 2 미만
			
			edges.offer(new Edge(map[x][y], map[nx][ny], cnt)); // 다리 만들어서 추가
		}
	}

	// dfs를 통해 인접한 섬 영역 같은 넘버링
	static void mark(int x, int y, int num) {
		visit[x][y] = true;
		map[x][y] = num;
		for (int i = 0; i < 4; i++) {
			int nx = x + "2110".charAt(i) - '1';
			int ny = y + "1201".charAt(i) - '1';
			if (!visit[nx][ny] && map[nx][ny] == 1) {
				visit[nx][ny] = true;
				mark(nx, ny, num);
			}
		}
	}
	
	// union-find
	static boolean union(int a,int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return false;
		
		if(pa < pb) parent[pb] = pa;
		else parent[pa] = pb;
		
		return true;
	}
	static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}
	// Edge 정보 저장 class
	static class Edge implements Comparable<Edge> {
		int x, y, dist;

		public Edge(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
}
