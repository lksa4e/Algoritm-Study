import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 14503번 로봇 청소기
 * 
 * 시뮬레이션 문제,
 * 문제 설명대로 구현했다.
 * 
 * 14,416KB	132ms
 */

public class BOJ_14503 {
	// 북동 남서 0123
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };
	static int N, M, r, c, dir, map[][], cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		while(move());
		System.out.println(cnt);
	}
	
	static boolean move() {
		map[r][c] = 2;	// 현재 위치 청소
		cnt++;	// 청소 카운트 ++
		
		while(true) {
			for (int i = 0; i < 4; i++) {
				dir = (dir + 3) % 4;	// 현재 기준 왼쪽 방향으로 회전
				int nextR = r + dr[dir];
				int nextC = c + dc[dir];
				if(map[nextR][nextC] == 0) {
					r = nextR;
					c = nextC;	// 그 방향으로 한 칸 전진
					return true;	// 1번부터 진행	
				}
			}
		
			// 네 방향 모두 청소, 벽인 경우 한 칸 후진, 그런데 후진할 칸이 벽인 경우 장치를 정지
			int nextR = r - dr[dir];
			int nextC = c - dc[dir];
			if(map[nextR][nextC] == 1)
				return false;
			else {
				r = nextR;
				c = nextC;
				continue;
			}
		}
	}
}
