package BaekOJ.study.date1002;

import java.io.*;
import java.util.*;

/*
 * 문제를 접근한 방식은 아래와 같음
 * 1. BFS로 섬을 2부터 ++해서 라벨링해서 서로 구분해 줌
 * 2. 각 섬끼리의 최단 거리를 구함 
 * 3. 섬이 V 섬과 섬을 서로 연결지을 수 있으면 E, 그리고 거리를 W로 두고 크루스칼로 서로 하나로 연결지음 
 * 4. 서로 하나로 연결되지 않는다면 -1, 연결되면 거리의 최소합을 출력
 * 
 * 그래프 + 빡구현 종합선물세트 같은느낌
 * 시험에서 나오면 정말 시간이 부족할 것 같다.
 * DP, 그래프 뭐하나 만만한 게 없다는 생각이 들었음
 * 
 * 메모리 	시간
 * 14320	140
 */

public class BaekOJ17472_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, M, di, dj, dist, result, map[][], parent[], distance[][];
	static int cnt = 2, INF = Integer.MAX_VALUE;
	static int delta[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static Queue<int[]> queue = new ArrayDeque<int[]>();
	static PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)->a[2]-b[2]);
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 섬들의 수를 BFS로 카운트하고 라벨링하여 구분해줌
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1) labeling(i, j);
			}
		}
		
		// 섬들 간의 거리 배열 생성 후 초기화
		distance = new int[cnt][cnt];
		for(int i = 2; i < cnt; i++) {
			for(int j = 2; j < cnt; j++) {
				if(i != j) distance[i][j] = INF;
			}
		}
		
		// 섬들 간 최단 거리 구함
		getMinDistance();
		
		// 서로 연결되어있다면 distance가 짧은게 우선 순위의 pq로 Enqueue
		for(int i = 2; i < cnt-1; i++) {
			for(int j = i+1; j < cnt; j++) {
				if(distance[i][j] != INF) pq.offer(new int[] {i, j, distance[i][j]});
			}
		}
				
		// Union-find를 위한 부모 배열
		parent = new int[cnt];
		for(int i = 1; i < cnt; i++) parent[i] = i;
		
		// 섬들을 서로 크루스칼로 연결하기
		buildBridge();
		
		// 만약 섬들이 서로 모두 연결이 되어있지 않다면 -1
		for(int i = 3; i < cnt; i++) {
			if(find(i) != find(2)) {
				result = -1;
				break;
			}
		}
		
		System.out.println(result);
		
	}

	// 1이 섬이니까, 섬들을 2부터 BFS로 라벨링해서 구분짓기
	public static void labeling(int si, int sj) {
		int i, j, di, dj, idx[];
		queue.offer(new int[] {si, sj});
		while(!queue.isEmpty()) {
			idx = queue.poll();
			i = idx[0];
			j = idx[1];
			map[i][j] = cnt;
			for(int[] d : delta) {
				di = i + d[0];
				dj = j + d[1];
				if(!isOOB(di, dj) && map[di][dj] == 1) {
					map[di][dj] = cnt;
					queue.offer(new int[] {di, dj});
				}
			}
		}
		cnt++;
	}
	
	// 섬들끼리 최단거리 찾기
	public static void getMinDistance() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue; //바다는 컨티뉴
				for(int[] d : delta) {
					di = i + d[0];
					dj = j + d[1];
					// 섬들끼리 직선으로 연결했을 때 서로 만나는 최단거리 찾기
					if(!isOOB(di, dj) && map[di][dj] == 0) {
						dist = 0;
						// 일직선 연결
						while(!isOOB(di, dj) && map[di][dj] == 0) {
							di += d[0];
							dj += d[1];
							dist++;
						}
						if(!isOOB(di, dj) && dist >= 2) { // 다리의 길이는 최소 2여야 하므로 거리도 2이상만 갱신
							distance[map[i][j]][map[di][dj]] = 
								distance[map[di][dj]][map[i][j]] = 
									Math.min(dist, distance[map[i][j]][map[di][dj]]);
						}
					}
				}
			}
		}
	}
	
	// 섬들을 서로 크루스칼로 연결하기
	public static void buildBridge() {
		int v1, v2, w;
		while(!pq.isEmpty()) {
			int[] edge = pq.poll();
			v1 = edge[0];
			v2 = edge[1];
			w = edge[2];
			if(find(v1) != find(v2)) {
				union(v1, v2);
				result += w;
			}
		}
	}
	
	public static boolean isOOB(int i, int j) {
		if(i>N-1 || i<0 || j>M-1 || j<0) return true;
		else return false;
	}
	
	// 크루스칼용 유니온파인드
	public static int find(int a) {
		if(a==parent[a]) return a; 
		return parent[a] = find(parent[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false; 
		
		parent[bRoot] = aRoot;
		return true;
	}
	
}
