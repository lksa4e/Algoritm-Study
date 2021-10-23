import java.io.*;
import java.util.*;

public class Main17142_연구소3 {
	
	/*
	 * 조합으로 활성화할 바이러스 선택 후 bfs
	 */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, map[][], cmap[][], len,
			minTime = Integer.MAX_VALUE, liveVirus[], blankCnt, visitCnt;
	static List<int[]> virusList;
	static Queue<int[]> q;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		cmap = new int[N][N];
		
		virusList = new ArrayList<>();
		blankCnt = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					virusList.add(new int[] {i,j});
					map[i][j] = -1;
				}else if(map[i][j] == 0) blankCnt++;
			}
		}
		liveVirus = new int[M];
		len = virusList.size();
		q = new ArrayDeque<>();
		
		comb(0,0);
		System.out.println((minTime==10000)?-1:minTime);
	}
	
	private static void comb(int cnt, int start) {
		if(cnt == M) {
			copyMap();
			visited = new boolean[N][N];
			
			for(int i : liveVirus) {
				int[] v = virusList.get(i);
				cmap[v[0]][v[1]] = 0; // 바이러스 활성화된 곳 0으로
				visited[v[0]][v[1]] = true; // 방문체크
				q.offer(v); // q에 추가
			}
			
			visitCnt = 0; // 빈칸 방문 수 초기화
			int time = bfs();
			if(minTime>time) minTime = time;
			return;
		}
		
		for(int i=start; i<len; i++) {
			liveVirus[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
	
	private static void copyMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				cmap[i][j] = map[i][j];
			}
		}
	}
	
	private static int bfs() {
		int temp[] = null, x = 0, y = 0, nx = 0, ny = 0, size = 0, level = 0;
		while(!q.isEmpty()) {
			size = q.size();
			while(size-- >0) {
				temp = q.poll();
				x = temp[0];
				y = temp[1];
				if(visitCnt == blankCnt) break; // 빈칸 방문 수 == 전체 빈칸 수 종료
				
				for(int i=0; i<4; i++) {
					nx = x + dx[i];
					ny = y + dy[i];
					
					if(nx<=-1 || nx>=N || ny<=-1 || ny>=N || visited[nx][ny]) continue;
					
					if(cmap[nx][ny] == 0) { // 빈칸일때
						cmap[nx][ny] = cmap[x][y] + 1;
						visitCnt++; // 빈칸 방문 수 증가
						q.offer(new int[] {nx, ny});
					}else if(cmap[nx][ny] == -1) { // 비활성화된 바이러스
						cmap[nx][ny] = level+1;
						q.offer(new int[] {nx, ny});
					}
				}
			}
			level++;
		}
		if(visitCnt < blankCnt) return 10000; // 빈칸을 다 못 채우면 10000 리턴
		
		return cmap[x][y];
	}
	
}
