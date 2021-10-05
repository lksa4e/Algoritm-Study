import java.io.*;
import java.util.StringTokenizer;

public class Main14503 {
	/*
	 * 조건에 따라 dfs
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	static int N, M, map[][];
	static int[] dx = {-1,0,1,0}; // 북 동 남 서
	static int[] dy = {0,1,0,-1};
	
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	int rx = Integer.parseInt(st.nextToken());
    	int ry = Integer.parseInt(st.nextToken());
    	int d = Integer.parseInt(st.nextToken());
    	
    	map = new int[N][M];
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<M; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	map[rx][ry] = 2; // 청소하면 2
    	dfs(rx,ry,d,0,1);
    }
    
    private static void dfs(int x, int y, int curD, int cnt, int area) {
    	
    	if(cnt==4) { // 네 방향 모두 청소가 되어있거나 벽인경우 후진
    		int nx = x - dx[curD];
    		int ny = y - dy[curD];
    		if(map[nx][ny] == 1) { // 벽이라 후진 못할경우 작동 중지
    			System.out.println(area);
    			System.exit(0); 
    		}
    		dfs(nx, ny,curD,0, area);
    	}
    	
    	int nd = (curD+3)%4; // 현재방향 왼쪽
    	
    	int nx = x + dx[nd];
		int ny = y + dy[nd];
		
		if(map[nx][ny] == 0) { // 청소하지 않은 공간이라면
			map[nx][ny] = 2;
			dfs(nx, ny, nd, 0, area+1); // 그 방향으로 회전, 이동
		}else { // 청소할 공간 없다면
			dfs(x, y, nd, cnt+1, area); // 회전
		}
    }
}
