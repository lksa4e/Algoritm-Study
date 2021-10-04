import java.io.*;
import java.util.*;

/**
 * 백준 14499번 주사위 굴리기
 * 
 * 풀이 : 구현
 * 
 * 막상 구현은 어렵지 않았지만 방향에 따라 주사위를 굴리는 부분이 너무 헷갈렸다..
 * 
 * 16064KB	164ms
 */
public class Main14499_주사위굴리기 {
	
	static int[] dice = new int[6];	// 위, 북, 동, 서, 남, 아래 (문제 주사위 전개도대로)
	static int[] dr = {0,0,-1,1};	// 동, 서, 북, 남 
	static int[] dc = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int initR = Integer.parseInt(st.nextToken()) + 1;
		int initC = Integer.parseInt(st.nextToken()) + 1;
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R+2][C+2];
		for (int i = 0; i < R+2; i++) Arrays.fill(map[i], -1);	// 경계 체크를 위해 -1로 초기화  
		
		// 맵 정보 입력받기 
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// K번 주사위 굴리기 
		st = new StringTokenizer(br.readLine());
		int r = initR, c = initC;
		for (int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(map[nr][nc] == -1) continue;	// 벽이면 이동 X 
			
			// 주사위를 dir 방향으로 굴리기 
			moveDice(dir);
			
			if(map[nr][nc] == 0) {	// 칸에 아래 복사 
				map[nr][nc] = dice[5];
			} else {	// 아래에 칸 복사, 칸 0으로 변경 
				dice[5] = map[nr][nc];
				map[nr][nc] = 0;
			}
			
			r = nr;
			c = nc;
			sb.append(dice[0]+"\n");	// 주사위 윗면 출력 
		}
		
		System.out.println(sb);
	}

	private static void moveDice(int dir) {
		int[] temp = dice.clone();	// 기존 주사위 정보를 이용해 굴리기 때문에 임시 주사위 생성 
		
		switch(dir) {
		case 0:	// 동 
			dice[0] = temp[3];
			dice[2] = temp[0];
			dice[3] = temp[5];
			dice[5] = temp[2];
			break;
		case 1:	// 서 
			dice[0] = temp[2];
			dice[2] = temp[5];
			dice[3] = temp[0];
			dice[5] = temp[3];
			break;
		case 2:	// 북 
			dice[0] = temp[4];
			dice[1] = temp[0];
			dice[4] = temp[5];
			dice[5] = temp[1];
			break;
		case 3:	// 남 
			dice[0] = temp[1];
			dice[1] = temp[5];
			dice[4] = temp[0];
			dice[5] = temp[4];
			break;
		}
	}

}
