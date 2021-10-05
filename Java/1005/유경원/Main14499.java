import java.io.*;
import java.util.StringTokenizer;

public class Main14499 {
	/*
	 * 문제 해석이 힘들었는데 해석만 하면 간단한 문제
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	static int N, M, map[][], dice[], x, y, K;
	static int[] dx = {0,0,0,-1,1}; // 동 서 북 남
	static int[] dy = {0,1,-1,0,0};
	
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	x = Integer.parseInt(st.nextToken());
    	y = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	map = new int[N][M];
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<M; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	dice = new int[7];
    	int nx = 0, ny = 0, d = 0;
    	st = new StringTokenizer(br.readLine());
    	
    	for(int j=0; j<K; j++) {
    		d = Integer.parseInt(st.nextToken());
    		nx = x + dx[d];
    		ny = y + dy[d];
    		if(nx<=-1 || nx>=N || ny<=-1 || ny>=M) continue;
    		
    		x = nx;
    		y = ny;
    		sb.append(rollDice(nx, ny, d)).append("\n");
		}
    	
    	System.out.println(sb);
    }
    
    private static int rollDice(int x, int y, int d) {
    	int temp = 0;
    	switch(d) { // 방향에 따라 굴리기
    		case 1: // 동
    			temp = dice[1];
    			dice[1] = dice[4];
    			dice[4] = dice[6];
    			dice[6] = dice[3];
    			dice[3] = temp;
    			break;
    		case 2: // 서
    			temp = dice[1];
    			dice[1] = dice[3];
    			dice[3] = dice[6];
    			dice[6] = dice[4];
    			dice[4] = temp;
    			break;
    		case 3: // 북
    			temp = dice[1];
    			dice[1] = dice[5];
    			dice[5] = dice[6];
    			dice[6] = dice[2];
    			dice[2] = temp;
    			break;
    		case 4: // 남
    			temp = dice[1];
    			dice[1] = dice[2];
    			dice[2] = dice[6];
    			dice[6] = dice[5];
    			dice[5] = temp;
    			break;
    	}
    	
    	if(map[x][y]==0) { // 칸이 0이면 주사위 밑면이 칸으로 복사
    		map[x][y] = dice[6]; 
    	}else { // 칸이 0이 아니면 칸의 수가 주사위 밑면으로, 칸은 0으로
    		dice[6] = map[x][y];
    		map[x][y] = 0;
    	}
    	
    	return dice[1]; // 윗면 출력
    }
}
