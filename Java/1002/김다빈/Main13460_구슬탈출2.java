import java.io.*;
import java.util.*;

/**
 * 백준 13460번 구슬 탈출 2
 * 
 * 풀이 : BFS
 * 
 * 시행착오 1 : 빨강, 파랑 구슬의 방문 처리를 어떻게 해야하는지에서 막혀서 헤맸다..
 * 두 구슬의 상태를 한 번에 저장해야해서 난생 처음으로 boolean[][][][] 4차원 배열을 사용해봤다.
 *  
 * 시행착오 2 : 두 구슬이 같은 선상에 있을 때의 처리가 헷갈렸다.
 * 결국 두 구슬 모두 굴린 후, 같은 위치에 있으면 방향에 따라 다시 하나의 구슬의 위치를 업데이트해주는 방식으로 구현했다.
 * 
 * 14332KB	128ms
 */
public class Main13460_구슬탈출2 {

	static int R, C, result = 0;
	static int holeR, holeC;	// 홀의 (r,c) 좌표 
	static char[][] map;		// 맵 정보 저장 
	
	static Queue<int[]> redBall = new ArrayDeque<int[]>();	// 빨간 구슬을 담을 큐 
	static Queue<int[]> blueBall = new ArrayDeque<int[]>();	// 파란 구슬을 담을 큐 
	static boolean[][][][] visited;	// 1,2차원에 빨간 구슬의 방문 여부, 3,4차원에 파란 구슬의 방문 여부를 저장 
	
	static int[] dr = {-1,1,0,0};	// 상하좌우 
	static int[] dc = {0,0,-1,1};	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R+2][C+2];
		for (int i = 0; i < R+2; i++) {	// 경계값을 모두 벽으로 초기화 
			Arrays.fill(map[i], '#');
		}
		
		visited = new boolean[R+2][C+2][R+2][C+2];
		
		int rR = 0, cR = 0, rB = 0, cB = 0;
		for (int i = 1; i <= R; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 1; j <= C; j++) {
				map[i][j] = input[j-1];
				
				switch(map[i][j]) {
				case 'R':	// 빨간 구슬인 경우 
					redBall.offer(new int[] {i,j});	
					rR = i;
					cR = j;
					break;
				case 'B':	// 파란 구슬인 경우 
					blueBall.offer(new int[] {i,j});
					rB = i;
					cB = j;
					break;
				case 'O':	// 홀인 경우 
					holeR = i;
					holeC = j;
					break;
				}
			}
		}
		
		// 초기값 (rR, cR), (rB, cB) 설정 
		visited[rR][cR][rB][cB] = true;
		
		bfs();
	}

	private static void bfs() {
		int[] red, blue;
		int redR, redC, blueR, blueC;
		
		// 빨간 구슬이 홀에 들어갔는지를 체크해주는 flag 
		boolean flag = false;
		
		while(!redBall.isEmpty()) {
			// 횟수 한 번에 갈 수 있는 모든 경우 수행 
			int size = redBall.size();
			
			while(size-- > 0) {
				red = redBall.poll();
				blue = blueBall.poll();
				redR = red[0];
				redC = red[1];
				blueR = blue[0];
				blueC = blue[1];
				
				// 굴리는 횟수가 10회를 초과하면 break 
				if(result > 10)break;

				// 빨강 구슬이 홀에 들어갔으면 break
				if(redR == holeR && redC == holeC) {
					flag = true;
					break;
				}
				
				// 사방 탐색 
				for (int dir = 0; dir < 4; dir++) {
					int nrR = redR + dr[dir];
					int ncR = redC + dc[dir];
					int nrB = blueR + dr[dir];
					int ncB = blueC + dc[dir];
					
					// 빨간 구슬 이동 
					while(true) {
						if(map[nrR][ncR] == '#') {	// 벽을 만나면 바로 직전 위치로 돌리고 break
							nrR -= dr[dir];
							ncR -= dc[dir];
							break;
						}
						if(map[nrR][ncR] == 'O') {
							break;
						}
						nrR += dr[dir];
						ncR += dc[dir];
					}
					
					// 파란 구슬 이동 
					while(true) {
						if(map[nrB][ncB] == '#') {	// 벽을 만나면 바로 직전 위치로 돌리고 break
							nrB -= dr[dir];
							ncB -= dc[dir];
							break;
						}
						if(map[nrB][ncB] == 'O') {
							break;
						}
						nrB += dr[dir];
						ncB += dc[dir];
					}
					
					// 파란 구슬이 홀에 들어갔으면 제외하기 위해 continue 
					if(nrB == holeR && ncB == holeC) {
						continue;
					}
					
					// 빨강, 파랑 구슬이 같은 위치에 있으면 방향에 따라 위치 변경 
					if(nrR == nrB && ncR == ncB) {
						switch(dir) {
						case 0:	// 위쪽으로 굴린 경우, 처음 위치의 r이 더 큰 수 nr++
							if(redR > blueR) nrR++;
							else nrB++;
							break;
						case 1:	// 아래쪽으로 굴린 경우, 처음 위치 r이 더 작은 수 nr--
							if(redR < blueR) nrR--;
							else nrB--;
							break;
						case 2:	// 왼쪽으로 굴린 경우, 처음 위치 c가 더 큰 수 nc++
							if(redC > blueC) ncR++;
							else ncB++;
							break;
						case 3:	// 오른쪽으로 굴린 경우, 처음 위치 c가 더 작은 수 nc--
							if(redC < blueC) ncR--;
							else ncB--;
							break;
						}
					}
					
					// 이미 방문한 곳이면 continue
					if(visited[nrR][ncR][nrB][ncB]) continue;
					
					redBall.offer(new int[] {nrR, ncR});
					blueBall.offer(new int[] {nrB, ncB});
					visited[nrR][ncR][nrB][ncB] = true;
				}	
			}
			
			// 이번 횟수에서 빨간 구슬을 홀에 넣었으면 break 
			if(flag) break;
			result++;	// 횟수 증가 
		}
		
		// 결과 출력 
		if(flag) System.out.println(result);
		else System.out.println(-1);
	}

}
