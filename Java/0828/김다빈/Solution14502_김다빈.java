package P0821;

import java.io.*;
import java.util.*;

/**
 * 
 * 백준 14502 연구소
 * 풀이 : 조합 + BFS
 * 1. 3중 for문으로 모든 가능한 벽 3개의 조합 구하기 
 * 2. 선택한 벽 조합을 복사한 맵에 표시한 후, 모든 바이러스 좌표에 대해 BFS로 이동할 수 있는 위치 구하고 맵에 바이러스 표시  
 * 3. 기존 안전영역 개수와 퍼진 바이러스 개수 차이로 최댓값 갱신
 * 
 * 121808KB	460ms
 */
public class Solution14502_김다빈 {
	
	static ArrayList<int[]> virus = new ArrayList<int[]>();
	static int N,M;
	static int safeNum = 0, max = Integer.MIN_VALUE;	// 초기 안전영역 개수, 최대 안전영역 개수 
	static int[] dr = {-1,1,0,0};	// 상하좌우 
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+2][M+2];
		for(int i=0;i<N+2;i++) Arrays.fill(map[i], 1);	// 맵 벽으로 초기화 
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.add(new int[] {i,j});	// 바이러스 좌표값 저장 
				else if(map[i][j] == 0) safeNum++;
			}
		}
		
		// 이후에 벽 세개 선택할거라 -3
		safeNum -= 3;
		
		// 모든 벽 3개 조합 구하기 
		for(int i=0;i<N*M;i++) {	// 첫번째 벽 선택 (i/M+1, i%M+1)
			if(map[i/M+1][i%M+1] == 0) {	// 빈칸이면 다음 벽 체크 
				for(int j=i+1;j<N*M;j++) {	// 두번째 벽 선택 (j/M+1, j%M+1)
					if(map[j/M+1][j%M+1] == 0) {
						for(int k=j+1;k<N*M;k++) {	// 세번째 벽 선택 (k/M+1, k%M+1)
							if(map[k/M+1][k%M+1] == 0) {
								int[][] copyMap = new int[N+2][M+2];	// 맵 복사한 후 선택한 벽 세개 저장 
								for(int t=0;t<N+2;t++) copyMap[t] = map[t].clone();
								copyMap[i/M+1][i%M+1] = 1;
								copyMap[j/M+1][j%M+1] = 1;
								copyMap[k/M+1][k%M+1] = 1;
								
								bfs(copyMap);
							}
						}
					}
				}				
			}
		}
		
		System.out.println(max);
	}

	private static void bfs(int[][] map) {		
		int safe = safeNum;
		
		// 바이러스 개수만큼 BFS 탐색 
		for(int num=0;num<virus.size();num++) {
			Queue<int[]> queue = new ArrayDeque<int[]>();
			boolean[][] visited = new boolean[N+2][M+2];
			queue.offer(virus.get(num));
			visited[virus.get(num)[0]][virus.get(num)[1]] = true;
			
			while(!queue.isEmpty()) {
				int[] pos = queue.poll();
				
				if(max > safe) return;	// 갱신할 수 없으면 종료 
				
				// 바이러스 퍼지는 상하좌우 탐색 
				for(int i=0;i<4;i++) {
					int nr = pos[0]+dr[i];
					int nc = pos[1]+dc[i];
					
					if(!visited[nr][nc] && map[nr][nc] == 0) {	// 바이러스가 퍼짐 
						queue.offer(new int[] {nr, nc});
						map[nr][nc] = 2;
						visited[nr][nc] = true;
						safe--;	// 안전영역 -1 
					}
				}
			}
		}
		
		// 모든 바이러스가 퍼지고 난 후 안전 영역의 크기 계산 
		max = Math.max(max, safe);
	}
	
}
