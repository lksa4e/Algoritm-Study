import java.io.*;
import java.util.*;

/**
 * 백준 17472번 다리 만들기 2 
 * 
 * 풀이 : BFS, DFS, MST(크루스칼 알고리즘)
 * 
 * 1. 섬 번호 붙이기 (BFS)
 * 2. 연결 가능한 모든 다리 구하기 (DFS) -> 다리 길이 기준으로 오름차순 정렬 
 * 3. 크루스칼 알고리즘을 이용한 MST 수행 -> 연결된 섬들을 연결리스트에 저장
 * 4. 모든 섬이 연결됐는지 BFS로 연결 리스트를 탐색하면서 연결된 섬의 개수 계산 -> 총 섬의 개수와 일치하는지 판단  
 * 
 * 조건도 다 따져주면서 모두 다 구현해야해서 너무 힘들었다.
 * 특히 모든 섬이 연결되었는지 판단해주는 부분을 생각하기가 어려웠던ㅜㅜ 
 * 
 * 14228KB	128ms
 */
public class Main17472_다리만들기2 {

	static int R, C;
	static int[][] map;
	
	// 연결 가능한 모든 다리 리스트 
	static ArrayList<Bridge> bridgeList = new ArrayList<Bridge>();
	// 각 섬의 대표자를 저장한 배열  
	static int[] parents;
	
	// 상하좌우 델타 배열 
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	// 섬 사이의 연결을 저장하는 리스트 
	static List<List<Integer>> linkedList = new ArrayList<>();
	
	static class Bridge implements Comparable<Bridge> {
		int start, end;	// 시작과 끝 섬의 번호 
		int length;		// 다리 길이
		
		public Bridge(int start, int end, int length) {
			super();
			this.start = start;
			this.end = end;
			this.length = length;
		}

		@Override
		public int compareTo(Bridge o) {	// 다리 길이를 기준으로 오름차순 정렬을 위한 비교 메소드 
			return this.length - o.length;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); 
		C = Integer.parseInt(st.nextToken()); 
		
		// 맵 정보 입력받기 
		map = new int[R+2][C+2];
		for (int i = 0; i < R+2; i++) Arrays.fill(map[i], -1);	// 경계값 체크를 위해 -1로 벽 두르기 
		
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 번호 붙이기 (BFS)
		boolean[][] visited = new boolean[R+2][C+2];	// 섬에 방문했는지 여부를 저장하는 배열 
		int num = 0;	// 섬 번호 
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if(!visited[i][j] && map[i][j] > 0) {	// 방문한 적 없는 육지라면 섬 번호 붙이기 
					setNumber(i, j, ++num, visited);
				}
			}
		}
		
		// 섬마다 연결할 수 있는 모든 다리 구하기 (DFS)
		getAllBridge(1, 1);
		
		// 다리 길이 기준 오름차순으로 정렬 
		Collections.sort(bridgeList);
		
		// 크루스칼 알고리즘으로 MST 
		parents = new int[num+1];
		for (int i = 1; i <= num; i++) parents[i] = i;
		
		for (int i = 0; i <= num; i++) linkedList.add(new ArrayList<Integer>());
		
		int totalLength = 0;
		for (int i = 0; i < bridgeList.size(); i++) {
			Bridge bridge = bridgeList.get(i);
			if(union(bridge.start, bridge.end)) {
				totalLength += bridge.length;
			}
		}
		
		// 섬이 모두 연결되어 있는지 확인 (BFS)
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] island_visit = new boolean[num+1];
		
		// 섬 1번을 시작점으로 설정 
		int cnt = 1;
		queue.offer(1);
		island_visit[1] = true;

		while (!queue.isEmpty())
		{
			int cur = queue.poll();

			for (int next : linkedList.get(cur))
			{
				if (!island_visit[next])
				{
					queue.offer(next);
					island_visit[next] = true;
					cnt++;
				}
			}
		}
		
		// 연결된 섬의 개수가 일치하는지 확인 
		if(cnt == num) System.out.println(totalLength);
		else System.out.println(-1);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		
		// a <-> b 연결 
		linkedList.get(a).add(b);
		linkedList.get(b).add(a);		
		
		return true;
	}

	private static int find(int index) {
		if(index == parents[index]) return index;
		return parents[index] = find(parents[index]);
	}

	// 섬에 번호를 붙이는 함수 (BFS)
	// (r, c) 좌표부터 사방탐색하며 num 번호 붙이기 
	private static void setNumber(int r, int c, int num, boolean[][] visited) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		// 시작점 (r,c) 설정 
		queue.offer(new int[] {r,c});
		visited[r][c] = true;
		map[r][c] = num;
		
		int[] cur;
		int curR, curC, nr, nc;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			curR = cur[0];
			curC = cur[1];
			
			// 사방탐색 
			for (int i = 0; i < 4; i++) {
				nr = curR + dr[i];
				nc = curC + dc[i];
				
				if(!visited[nr][nc] && map[nr][nc] > 0) {	// 방문한 적 없고, 바다가 아니면 번호 붙이기 
					queue.offer(new int[] {nr,nc});
					visited[nr][nc] = true;
					map[nr][nc] = num;
				}
			}
		}
	}

	// 모든 육지에서 연결할 수 있는 다리를 모두 구하는 함수 (DFS)
	private static void getAllBridge(int r, int c) {
		if(r == R && c == C+1) {	// 모든 좌표 탐색 완료 
			return;
		}
		if(c == C+1) {			// 다음 행의 첫번째 열로 이동 
			getAllBridge(r+1, 1);
			return;
		}
		if(map[r][c] == 0) {	// 다리를 연결할 수 없는 바다면 다음 좌표 탐색 
			getAllBridge(r, c+1);
			return;
		}
		
		// 사방탐색
		for (int i = 0; i < 4; i++) {
			int length = calcLength(r, c, i);
			
			if(length >= 2) {	// 길이가 2 이상인 다리만 추가
				int endR = r + dr[i] * (length+1);
				int endC = c + dc[i] * (length+1);
				bridgeList.add(new Bridge(map[r][c], map[endR][endC], length));
			}
		}
		// 다음 좌표로 이동 
		getAllBridge(r, c+1);
	}

	// 다른 땅을 만나거나, 경계를 벗어날 때까지 이동하여 다리 길이를 계산하는 함수 
	private static int calcLength(int r, int c, int dir) {
		int nr = r, nc = c, length = 0;
		
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			
			if(map[nr][nc] >= 1) break;			// 다른 땅을 만난 경우 
			else if(map[nr][nc] == -1) return 0;	// 경계를 벗어난 경우 
			
			length++;
		}
		
		return length;
	}

}
