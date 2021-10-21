import java.io.*;
import java.util.*;

/**
 * G2 BOJ 19236 청소년 상어
 * 메모리 : 16512kb 시간 : 180ms
 * 
 * 개 악질문제
 * 악질포인트 + 삽질포인트가 너무 많았음
 * 
 * DFS 수행하면서 map을 복사하는건 생각을 했었지만, 물고기 배열까지 복사하는건 생각을 못했다. 여기서 시간을 너무 많이씀
 * 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다. -> 이거때문에 물고기없을때 break했다가 삽질 한참함
 * 그리고 class에서 생성자 선언해줄때 초기값 세팅한다고 
 * public Fish(int x, int y, int dir, boolean live) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.live = true;
		}
 * 이런식으로 boolean에 하드코딩 박지말아야함.. fish 복사하는 과정에서 무조건 true로 세팅되는 문제때문에 개고생함
 */
public class BOJ19236_G2_청소년상어 {
	static Fish fish[] = new Fish[17];
	static int map[][] = new int[4][4];
	static int answer = 0;
	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken()); // 물고기 번호 
				int dir = Integer.parseInt(st.nextToken())-1; // 물고기 방향
				fish[num] = new Fish(i, j, dir, true); 
				map[i][j] = num; //map에 물고기 번호 저장 
			}
		}

		int start_sum = map[0][0];
		int start_dir = fish[map[0][0]].dir;
		fish[map[0][0]].live = false;
		map[0][0] = -1;

		dfs(0, 0, start_dir, start_sum);

		System.out.println(answer);
	}

	static void dfs(int x, int y, int dir, int sum) {
		answer = Math.max(answer, sum);
		
		// map, fish 배열 temp에다가 잠시 옮겨두기
		int[][] tempMap = new int[map.length][map.length];
		for (int i = 0; i < map.length; i++) {
			System.arraycopy(map[i], 0, tempMap[i], 0, map.length);
		}

		Fish[] tempFish = new Fish[fish.length];
		for (int i = 1; i <= 16; i++)
			tempFish[i] = new Fish(fish[i].x, fish[i].y, fish[i].dir, fish[i].live);

		// 물고기를 이동시키는 함수
		fishMove();
		

		// 상어의 이동
		int nx = x;
		int ny = y;
		while(true) {
			// 해당 방향으로 직진
			nx += dx[dir];
			ny += dy[dir];
			
			// 범위 나가면 멈춤
			if(oob(nx,ny)) break;
			// 먹을 수 있는 물고기가 없으면 그냥 건너뜀
			if(map[nx][ny] == 0) continue;
			
			int eatfish = map[nx][ny];
			int new_sum = sum + map[nx][ny];
			
			// 상어 이동 좌표 갱신
			map[x][y] = 0;
			map[nx][ny] = -1;
			
			// 해당 자리에 있는 물고기 주금
			fish[eatfish].live = false;
			
			// 다음 먹이를 찾아 방향을 바꾸고 이동
			dfs(nx, ny, fish[eatfish].dir, new_sum);
			
			// dfs 돌아왔다면 좌표 다시 해제
			map[x][y] = -1;
			map[nx][ny] = eatfish;
			fish[eatfish].live = true;
		}


		// 맵 상태, 물고기 정보 되돌리기
		for (int j = 0; j < map.length; j++)
			System.arraycopy(tempMap[j], 0, map[j], 0, map.length);

		for (int i = 1; i <= 16; i++)
			fish[i] = new Fish(tempFish[i].x, tempFish[i].y, tempFish[i].dir, tempFish[i].live);
	}
	
	// 물고기 이동하기
	static void fishMove() {

		for (int i = 1; i <= 16; i++) {
			if (!fish[i].live) continue; // 죽은 물고기는 pass

			int dir = fish[i].dir;

			for (int j = 0; j < 8; j++) {
				int nx = fish[i].x + dx[(dir + j) % 8];
				int ny = fish[i].y + dy[(dir + j) % 8];
				
				// 물고기 방향전환
				fish[i].dir = (dir + j) % 8;

				if (oob(nx, ny)) continue;
				if (map[nx][ny] == -1) continue; // 상어 자리는 못감

				// 교체할 물고기가 없는 경우 -> 그냥 이동
				if (map[nx][ny] == 0) {
					// 원래 자리를 0으로
					map[fish[i].x][fish[i].y] = 0;
				}
				// 교체할 물고기가 있는 경우 -> swap
				else {
					// swap해서 자리 바꾸기
					int change_fish = map[nx][ny];
					fish[change_fish].x = fish[i].x;
					fish[change_fish].y = fish[i].y;
					map[fish[change_fish].x][fish[change_fish].y] = change_fish;
				}
				
				fish[i].x = nx;
				fish[i].y = ny;
				map[nx][ny] = i;
				break;
			}

		}
	}

	static boolean oob(int x, int y) {
		return x < 0 || x >= 4 || y < 0 || y >= 4;
	}

	static class Fish {
		int x, y;
		int dir;
		boolean live;

		public Fish(int x, int y, int dir, boolean live) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.live = live;
		}
	}
}
