import java.io.*;
import java.util.*;

public class Main21620 {

	static int N, M, A[][];
	static int[] dx = {0,0,-1,-1,-1,0,1,1,1};
	static int[] dy = {0,-1,-1,0,1,1,1,0,-1};
	static Queue<int[]> cloudQ;
	static List<int[]> movedCloudList;
	static boolean[][] wasCloud;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloudQ = new ArrayDeque<int[]>();
		cloudQ.offer(new int[] {N,1});
		cloudQ.offer(new int[] {N,2});
		cloudQ.offer(new int[] {N-1,1});
		cloudQ.offer(new int[] {N-1,2});
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
//			모든 구름이 d 방향으로 s칸 이동한다.
//			각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
//			구름이 모두 사라진다.
			moveAndrain(d, s);
//			2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
//			이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
//			예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
			waterCopy();
			// 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
			makeCloud();
		}
		
		int ans = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				ans += A[i][j];
			}
		}
		System.out.println(ans);
	}
	
	private static void makeCloud() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(A[i][j]>=2 && !wasCloud[i][j]) { // 물의 양 2이상이고 구름이었던 곳 아니면
					cloudQ.offer(new int[] {i,j});
					A[i][j] -= 2;
				}
			}
		}
		
	}

	private static void waterCopy() {
		wasCloud = new boolean[N+1][N+1];
		int x = 0, y = 0, nx = 0, ny = 0, cnt = 0;
		
		for(int[] cloud : movedCloudList) {
			x = cloud[0];
			y = cloud[1];
			
			cnt = 0;
			for(int i=2; i<=8; i+=2) {
				nx = x + dx[i];
				ny = y + dy[i];
				
				if(nx<=0 || nx>N || ny<=0 || ny>N || A[nx][ny]==0) continue;
				cnt++;
			}
			A[x][y] += cnt;
			wasCloud[x][y] = true;
		}
	}

	private static void moveAndrain(int d, int s) {
		int temp[] = null, x = 0, y = 0, nx = 0, ny = 0;
		movedCloudList = new ArrayList<int[]>();
		
		while(!cloudQ.isEmpty()) {
			temp = cloudQ.poll();
			x = temp[0];
			y = temp[1];
			
			nx = (x + dx[d]*s) %N;
			ny = (y + dy[d]*s) %N;
			if(nx<=0) nx += N;
			if(ny<=0) ny += N;
			
			// 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
			A[nx][ny]++;
			movedCloudList.add(new int[] {nx, ny});
		}
	}
}