import java.io.*;
import java.util.*;

public class Main17472 {
	/*
	 * 1. 각 섬들 그룹별로 번호 마킹
	 * 2. 섬에서 다리놓기. 섬 간에 최소길이 다리를 나타내는 2차원 배열 만듦
	 * 3. 다리 배열 프림 알고리즘으로 모든 섬 연결하는 다리길이 최솟값 구함
	 * 
	 * !!
	 * 다리 놓을때 출발지와 같은 섬 만날경우 고려해야 함
	 * if(map[nx][ny] == startIsland) break;
8 8
0 0 0 0 0 1 0 1
0 1 0 1 0 1 1 0
1 1 1 1 1 1 0 0
0 0 1 0 0 1 0 0
1 1 0 0 0 1 1 0
0 0 0 1 1 1 1 1
0 1 1 0 0 1 1 0
0 0 1 0 1 0 0 1
output: 11
correct answer: -1

	 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M, map[][], bridge[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean visited[][], visitedNum[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬에 번호 표시
		visited = new boolean[N][M];
		int islandNum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					makeIsland(i,j,++islandNum);
				}
			}
		}
		
//		print(map);
//		System.out.println();
		
		// 다리놓기
		visited = new boolean[N][M];
		visitedNum = new boolean[islandNum+1];
		bridge = new int[islandNum+1][islandNum+1]; // 섬들 사이 최소거리 저장할 2차원 배열
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0 && !visitedNum[map[i][j]]) { // 방문하지않은 번호의 섬이면
					getMinDist(i,j,map[i][j]);
				}
			}
		}
		
//		print(bridge);
//		System.out.println();
		
		try {
			System.out.println(prim(bridge.length));
		}catch(Exception e) {
			System.out.println(-1);
		}
		
	}
	
	private static int prim(int n) {
		boolean[] visited = new boolean[n];
		int[] minEdge = new int[n];
		
		for (int i = 1; i < n; i++) {
			minEdge[i] = Integer.MAX_VALUE;
		}
		
		int result=0; // 최소신장트리 비용
		minEdge[1] = 0; // 임의의 시작점 0의 간선비용을 0으로 세팅
		
		for (int i = 1; i < n; i++) {
			// 1. 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 찾기
			int min = Integer.MAX_VALUE;
			int minVertex = -1; // 최소간선비용의 정점번호
			for (int j = 1; j < n; j++) {
				if(!visited[j] && min>minEdge[j]) {
					min = minEdge[j];
					minVertex = j;
				}
			}
			
			visited[minVertex] = true; // 신장트리에 포함시킴
			result += min;  // 간선비용 누적
			
			// 2. 선택된 정점 기준으로 신장트리에 연결되지 않은 타 정점과의 간선 비용 최소로 업데이트
			for (int j = 1; j < n; j++) {
				if(!visited[j] &&  bridge[minVertex][j] != 0 && minEdge[j]> bridge[minVertex][j]) {
					minEdge[j] = bridge[minVertex][j];
				}
			}
			//System.out.println(Arrays.toString(minEdge));
		}
		return result;
	}
	
	private static void getMinDist(int x, int y, int startIsland) {
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<=-1 || nx >=N || ny<=-1 || ny>=M || visited[nx][ny]) continue;
			
			if(map[nx][ny]==0) { // 다음 위치가 물이면 다리놓기
				int dist = 1;
				boolean makeBridge = false;
				while(true) {
					nx += dx[i];
					ny += dy[i];
					if(nx<=-1 || nx >=N || ny<=-1 || ny>=M) break;
					if(map[nx][ny] == startIsland) break; // 시작한 섬과 같다면 중지
					
					if(map[nx][ny] != 0 && map[nx][ny] != startIsland) { // 섬인데 시작한 섬이 아니라면
						makeBridge = true;
						break;
					}
					dist++;
				}
				if(makeBridge && dist >=2) { // 다리 길이 2이상이면 최솟값 갱신
					int pastDist = bridge[startIsland][map[nx][ny]];
					if(pastDist==0) {
						bridge[startIsland][map[nx][ny]] = dist;
					}else {
						bridge[startIsland][map[nx][ny]] = Math.min(pastDist, dist);
					}
				}
			}else if(map[nx][ny]==startIsland) {
				getMinDist(nx, ny, startIsland);
			}
		}
	}
	
	private static void makeIsland(int x, int y, int k) { // 섬 그룹별로 번호 부여
		visited[x][y] = true;
		map[x][y] = k;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<=-1 || nx >=N || ny<=-1 || ny>=M || visited[nx][ny] || map[nx][ny]==0) continue;
			makeIsland(nx,ny,k);
		}
	}
	
//	private static void print(int[][] arr) {
//		for(int i=0; i<arr.length; i++) {
//			for(int j=0; j<arr[0].length; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
	
}
