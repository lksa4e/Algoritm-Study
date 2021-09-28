import java.io.*;
import java.util.*;

public class Main13460 {

	/*
	 * 4차원 배열로 빨간구슬, 파란구슬 방문체크
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, M;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static char[][] map;
	static boolean[][][][] visited;
	static Queue<int[]> q;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		char[] temp;
		int rx = 0, ry = 0, bx = 0, by = 0;
		for(int i=0; i<N; i++) {
			temp = br.readLine().toCharArray();
			for(int j=0; j<M; j++) { // R, B 좌표 저장
				if(temp[j]=='R') {
					rx = i;
					ry = j;
				}else if(temp[j]=='B') {
					bx = i;
					by = j;
				}
				map[i][j] = temp[j];
			}
		}
		visited = new boolean[N][M][N][M];
		q = new ArrayDeque<int[]>();
		q.offer(new int[] {rx,ry,bx,by,1});
		visited[rx][ry][bx][by] = true;
		bfs();
	}
    
	private static void bfs() {
		while(!q.isEmpty()) {
			int[] ball = q.poll();
			int rx = ball[0], ry = ball[1], bx = ball[2], by = ball[3], depth = ball[4];
			
			if(depth>10) break;
			
			for(int i=0; i<4; i++) {
				int[] temp = move(rx,ry,dx[i],dy[i]);
				int nrx = temp[0], nry = temp[1], rcnt = temp[2];
				
				temp = move(bx,by,dx[i],dy[i]);
				int nbx = temp[0], nby = temp[1], bcnt = temp[2];
				
				if(map[nbx][nby]!='O') { // blue 목적지 아닌데
					if(map[nrx][nry]=='O') { // red 목적지면 성공
						System.out.println(depth);
						return;
					}
					if(nrx==nbx && nry==nby) { // 겹쳤다면
						if(rcnt > bcnt) { // 더 많이 움직인 구슬 한칸 뒤로
							nrx -= dx[i];
							nry -= dy[i];
						}else {
							nbx -= dx[i];
							nby -= dy[i];
						}
					}
					if(!visited[nrx][nry][nbx][nby]) {
						visited[nrx][nry][nbx][nby] = true;
						q.offer(new int[] {nrx,nry,nbx,nby,depth+1});
					}
				}
			}
		}
		System.out.println(-1);
	}
	
	private static int[] move(int x, int y, int dx, int dy) {
		int cnt = 0; // 이동 횟수
		while(map[x+dx][y+dy]!='#' && map[x][y] != 'O') { // 다음칸이 벽이거나 현재칸이 목적지일때까지 계속
			x += dx;
			y += dy;
			cnt++;
		}
		return new int[] {x, y, cnt};
	}
	
}
