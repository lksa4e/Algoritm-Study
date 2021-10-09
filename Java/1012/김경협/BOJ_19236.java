import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 19236번 청소년 상어
 * 
 * 시뮬레이션 문제,
 * DFS를 이용해 상어가 갈 수 있는 선택지들을 선택했다.
 * DFS를 돌면서 선택지마다 맵과 물고기들의 방향을 복사해서 분기하도록 해줬다.
 * 
 * 이중 배열을 통해 맵을 저장하고, 물고기들의 번호에 따라서 방향을 저장하는 배열을 추가로 사용했다.
 */

public class BOJ_19236 {
	static final int MAP = 4;
	static final int[] dr = {-1,-1,0,1,1,1,0,-1};
	static final int[] dc = {0,-1,-1,-1,0,1,1,1};
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int [][] map = new int[MAP][MAP];
		int[] fish = new int[MAP*MAP + 1];
		
		for (int i = 0; i < MAP; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < MAP; j++) {
				int fishNum = Integer.parseInt(st.nextToken());
				map[i][j] = fishNum;
				fish[fishNum] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		
		sharkDFS(0,0,0,map, fish);
		System.out.println(max);
	}
	
	static void sharkDFS(int row, int col, int sum, int map[][], int[] fish) {
		// 상어가 row,col에 있는 먹이를 먹으러
		int fishNum = map[row][col];
		int sharkDir = fish[fishNum];
		map[row][col] = 0;	// 물고기 사망
		sum += fishNum;
		max = Math.max(sum, max);
		
		// 물고기 이동
		moveFish(map,fish, row, col);
		
		// 상어 이동
		int nextR = row;
		int nextC = col;
		while(true) {
			nextR += dr[sharkDir];
			nextC += dc[sharkDir];
			if(isOutOfMap(nextR, nextC)) break;
			if(map[nextR][nextC] != 0) {
				// 먹이가 있음
				int[][] cpMap = new int[MAP][MAP];
				int[] cpFish = fish.clone();
				copyMap(map, cpMap);
				sharkDFS(nextR, nextC, sum, cpMap, cpFish);
			}
		}
	}
	
	static void moveFish(int[][] map, int[] fish, int sharkRow, int sharkCol) {
		for (int i = 1; i <= MAP*MAP; i++) {
			int row = 0, col = 0;
			boolean isExist = false;
			FISH_LOC: for (row = 0; row < MAP; row++) {
				for (col = 0; col < MAP; col++) {
					if(map[row][col] == i) {
						isExist = true;
						break FISH_LOC;
					}
				}
			}
			if(!isExist) continue;
			int nextR = 0, nextC = 0;
			// i 번째 물고기 이동
			for (int j = 0; j < 8; j++) {
				int fishDir = (fish[i] + j) % 8;
				nextR = row + dr[fishDir];
				nextC = col + dc[fishDir];
				
				if(nextR == sharkRow && nextC == sharkCol) continue;
				if(isOutOfMap(nextR, nextC)) continue;
				
				// swap
				int tmp = map[row][col];
				map[row][col] = map[nextR][nextC];
				map[nextR][nextC] = tmp;
				fish[i] = fishDir;
				break;
			}
		}
	}
	
	static void copyMap(int[][] from, int[][] to) {
		for (int i = 0; i < MAP; i++) {
			for (int j = 0; j < MAP; j++) {
				to[i][j] = from[i][j];
			}
		}
	}
	
	static boolean isOutOfMap(int row, int col) {
		return row < 0 || col < 0 || row >= MAP || col >= MAP;
	}
}
