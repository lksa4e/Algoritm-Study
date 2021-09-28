import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [0928] BOJ 13460 구슬 탈출 2
 * 2차원 배열, BFS, 빡구현
 * 
 * sol)
 * B가 구멍에 빠지지 않는 방향을 기준으로 B와 R을 동시에 BFS 탐색한다. 
 * 
 * 1. 최초의 R과 B, 기울인 횟수 변수(0으로 초기화)를 bfs를 위한 큐에 삽입한다.
 * 2. 큐의 B를 기준으로 4방 탐색을 진행한다.
 * 		- 만약 B가 구멍으로 빠지는 방향이면 해당 방향은 더이상 탐색하지 않고 다른 방향을 탐색한다.
 * 		- B가 구멍에 빠지지 않으면 해당 방향으로 R을 탐색한다. R이 구멍에 빠지면 오직 R만 빠진 경우이므로 지금까지 기울인 횟수를 바로 반환한다.
 * 		- R과 B모두 구멍에 빠지지 않고 벽까지 도달했다면 두 구슬이 겹치지 않게 위치 조정을 해준다.
 * 		- 조정한 좌표를 큐에 삽입한다.
 * 3. 큐에 삽입된 다음 좌표에 대해 위의 과정을 반복한다.
 *
 * 시행착오)
 * - 당연히 R을 구멍에 넣어야하니까 R의 방문과 R이 구멍에 들어가느냐를 중점으로 생각했는데, 
 * 	  R이 구멍에 들어가더라도 B가 구멍에 들어가버리면 무조건 불가능한 경우이므로 B부터 고려해야 했던 것이 너무 어려웠다...
 * - 큐에 기울인 횟수(cnt) 변수도 함께 넣어줘야지 해당 방향에서의 기울인 횟수가 저장되는 것
 * 	  처음에는 전역변수로 처리했다가 불가능한 방향으로 기울여 본 횟수까지 카운트해버림
 * - R과 B의 방문 배열을 각각 만들었는데 동시에 방문했던 곳을 다시 방문하는게 동일하게 기울인 경우이고,
 * 	  아무리 R(B)은 방문했던 곳이더라도 B(R)는 방문하지 않은 곳에 동시에 도달했다면 다른 경우이기때문에 하나의 배열 위에 방문체크를 해줘야 한다.  (ex. 테케 3번)
 * 
 */

public class BOJ_13460_구슬탈출2 {
	static int N, M, curR, curB, nextR, nextB;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 0, -1, 1};	// 좌, 우, 상, 하
	static int[] dy = {-1, 1, 0, 0};
	static Queue<Integer> q = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 초기 지도 입력
		map = new char[N][M];
		for (int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j=0; j<M; j++) {
				if (map[i][j] == 'R') curR = i*M+j;			// 초기 R 좌표 기억
				else if (map[i][j] == 'B') curB = i*M+j;	// 초기 B 좌표 기억
			}
		}
		
		// R과 B의 방문 좌표를 동시에 기록
		visited = new boolean[N*M][N*M];
		// BFS 탐색하여 기울인 횟수 출력
		System.out.println(bfs());
	}

	// BFS를 통해 구슬 이동
	private static int bfs() {
		int cnt = 0;					// 기울인 횟수
		q.offer(cnt);
		q.offer(curR);
		q.offer(curB);
		visited[curR][curB] = true;
		
		while(!q.isEmpty()) {
			cnt = q.poll();				// 직전까지 기울인 횟수
			if (cnt>=10) return -1;		// 직전까지 기울인 횟수가 10 이상이면 더이상 탐색 불가능
			
			curR = q.poll();			// 현재 R 위치
			int Rx = curR/M;
			int Ry = curR%M;
			
			curB = q.poll();			// 현재 B 위치
			int Bx = curB/M;
			int By = curB%M;
			
			// 4방 탐색
			for (int i=0; i<4; i++) {
				// B가 구멍에 빠지는지를 먼저 탐색
				if (isFallInHole(Bx, By, i, 'B')) continue;
				// R이 구멍에 빠진다면 직전까지 기울인 횟수에 1 증가하여 반환
				if (isFallInHole(Rx, Ry, i, 'R')) return cnt+1;
				// B와 R 모두 구멍에 안빠지고 벽에 붙어있다면 두 구슬이 겹치지 않게 위치 조정
				if (nextR == nextB) rearrange(i);
				// 멈춘 위치가 이전에 방문한 적 있는 위치면 다음 방향 탐색
				if (visited[nextR][nextB]) continue;
				
				// B와 R 모두 구멍에 빠지지 않았고, 현재 멈춘 위치에서 다른 방향으로 이동한다면 구멍에 도달할 가능성이 있는 경우
				q.offer(cnt+1);					// 큐에 현재 정보 삽입
				q.offer(nextR);
				q.offer(nextB);
				visited[nextR][nextB] = true;	// 방문 처리
			}
		}
		return -1;		// 기울인 횟수가 10을 넘지는 않지만 R도 구멍에 빠지지 않은 경우
	}
	
	// 지도를 기울여 구슬 이동시키는 함수
	private static boolean isFallInHole(int x, int y, int i, char ball) {
		while(true) {
			x += dx[i];
			y += dy[i];
			
			if (map[x][y]=='O') return true;		// 구멍에 빠지면 true
			if (map[x][y]=='#') {					// 벽에 닿아 멈추면 멈춘 위치 기억
				x -= dx[i];
				y -= dy[i];
				
				if (ball=='R') nextR = x*M+y;
				else nextB = x*M+y;
				
				break;
			}
		}
		return false;
	}
	
	// 두 구슬이 벽에 붙어있는 경우 겹치지 않게 위치 조정해주는 함수
	private static void rearrange(int i) {
		switch (i) {
		case 0:			// 좌
			if (curR < curB) nextB++;
			else nextR++;
			break;
		case 1:			// 우
			if (curR < curB) nextR--;
			else nextB--;
			break;
		case 2:			// 상
			if (curR < curB) nextB+=M;
			else nextR+=M;
			break;
		case 3:			// 하
			if (curR < curB) nextR-=M;
			else nextB-=M;
			break;
		}
	}

}
