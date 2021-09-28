import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 13460번 구슬 탈출2
 * 
 * 가장 최단 거리를 구하는 문제이기에 BFS로 풀었다.
 * 기울일 때 빨간 공과 파란 공이 상대 공의 위치에 따라서 움직임이 다른게 까다로웠다.
 * 빨간색 한 칸, 파란색 한 칸씩 움직이면서, 어느 공이던지 움직임이 있으면 계속 반복해서 움직이도록 했다.
 * 
 * 14248KB	136ms
 */

public class BOJ_13460 {
	static int row, col;
	static char[][] map;
	static boolean[][][][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,1,-1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		map = new char[row][col];
		// 빨간공과 파란공의 위치를 visited배열로 사용
		visited = new boolean[row][col][row][col];
		int[] data = new int[5];
		data[0] = 1;
		
		// 빨간 공과 파란 공은 맵에서 지우고, 위치 정보만 변수로 관리한다.
		for (int i = 0; i < row; i++) {
			char[] tmp = br.readLine().trim().toCharArray();
			for (int j = 0; j < col; j++) {
				map[i][j] = tmp[j];
				if (map[i][j] == 'R') {
					data[1] = i;
					data[2] = j;
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					data[3] = i;
					data[4] = j;
					map[i][j] = '.';
				}
			}
		}

		System.out.println(bfs(data));
	}

	// bfs로 기울이면서, 빨간공과 파란공의 이동을 추적
	static int bfs(int[] data) {
		// 큐에다가 cnt랑 redR, redC, blueR, blueC 전부 int[]로 넣어주기
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(data);
		visited[data[1]][data[2]][data[3]][data[4]] = true;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int cnt = tmp[0];
			int r1 = tmp[1];
			int c1 = tmp[2];
			int r2 = tmp[3];
			int c2 = tmp[4];
			
			if(cnt > 10) return -1;		// 최대 시도가 10을 넘으면 안 됨
			
			for (int i = 0; i < 4; i++) {
				// 레드 이동
				int redR = r1;
				int redC = c1;
				// 블루 이동
				int blueR = r2;
				int blueC = c2;
				
				// 빨간 공과 파란 공을 while문 안에서 한 칸씩 움직이면서 반복
				// 빨간 공과 파란 공 둘 중에 하나라도 움직이는 중이라면 계속 루프문이 돌아야함
				boolean rMoved = true, bMoved = true;
				// 빨간 공과 파란 공이 홀에 들어 갔을 때를 저장하는 변수
				boolean redGoal = false, blueGoal = false;
				while(rMoved || bMoved) {	// 현재 어떤 공이 움직이고 있는 중이면 true
					rMoved = false;
					bMoved = false;
					// 넥스트가 평지이고, blue가 아니면
					int nRedR = redR + dr[i]; 
					int nRedC = redC + dc[i]; 
					if(map[nRedR][nRedC] != '#' &&
							(blueGoal || !(nRedR == blueR && nRedC == blueC))) {	// 공은 다른 공의 위치를 침범할 수 없다. 단, 다른 공이 홀에 들어간 경우라면 가능
						redR += dr[i];
						redC += dc[i];
						rMoved = true;	// 빨간 공은 움직이는 중
						if(map[redR][redC] == 'O') {
							redGoal = true;
						}
					}
					
					int nBlueR = blueR + dr[i]; 
					int nBlueC = blueC + dc[i]; 
					if(map[nBlueR][nBlueC] != '#' &&
							(redGoal || !(nBlueR == redR && nBlueC == redC))) {
						blueR += dr[i];
						blueC += dc[i];
						bMoved = true;	// 파란 공은 움직이는 중
						if(map[blueR][blueC] == 'O') {
							blueGoal = true;
						}
					}
				}
				
				if(blueGoal) continue;
				
				// 공이 들어갔을 때, 빨간공만 들어가고 파란공은 안들어가야함
				// 성공한 후에는 그 다음 시도들은 다 현재 cnt보다 크므로 return
				if(redGoal) {
					return cnt;
				}
				
				// 빨간공이나 파란 공이 이미 방문한 위치면 visited
				if(!visited[redR][redC][blueR][blueC]) {
					int[] next = {cnt + 1, redR, redC, blueR, blueC};
					visited[redR][redC][blueR][blueC] = true;
					q.offer(next);
				}
			}
		}
		return -1;
	}
}


