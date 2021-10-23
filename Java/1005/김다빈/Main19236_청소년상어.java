import java.io.*;
import java.util.*;

/**
 * 백준 19236번 청소년 상어
 * 
 * 풀이 : DFS + 구현
 * 
 * 1. 작은 번호의 물고기부터 순차적으로 이동 (반시계 45도 회전하면서 모든 방향 시도)
 * 		1) 빈칸 -> 이동
 * 		2) 다른 물고기 -> 서로 위치 바꾸기
 * 		3) 상어 or 경계 -> 이동 X
 * 2. 상어가 이동할 수 있는 모든 곳의 경우의 수 수행
 * 
 * 시행착오)
 *  Fish[] 배열을 복사하는데 너무너무 많은 삽질을 하고..
 *  결국, 값만 복사해서 새로운 배열을 만들어줬다.. 
 * 
 * 14528KB	124ms
 */
public class Main19236_청소년상어 {

	static int maxValue = Integer.MIN_VALUE;
	
	static int[] dr = {-1,-1,0,1,1,1,0,-1};	//  ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dc = {0,-1,-1,-1,0,1,1,1};
	
	static class Shark {
		int r, c, dir, value;
		
		public Shark(int r, int c, int dir, int value) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.value = value;
		}
	}
	
	static class Fish {
		int r, c, dir;
		boolean isAlive = true;	// 물고기의 생존여부 
		
		public Fish(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
    	int[][] map = new int[6][6];	// 경계값 체크를 위해 -1로 패딩
    	for (int i = 0; i < 6; i++) Arrays.fill(map[i], -1);
    	
    	Fish[] fish = new Fish[17];
        for (int i = 1; i <= 4; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 4; j++) {
				int index = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				fish[index] = new Fish(i, j, dir);
				map[i][j] = index;
			}
		}
        
        int start = map[1][1];	// 시작점 (1,1)의 물고기 번호 
        fish[start].isAlive = false;	// start 물고기가 죽었다고 표시..
        map[1][1] = -2;	// (1,1)에 상어 -2로 표시 
        
        Shark shark = new Shark(1, 1, fish[start].dir, start);
        dfs(shark, map, fish);
        
        System.out.println(maxValue);
    }
    
    private static void dfs(Shark shark, int[][] map, Fish[] fish) {
    	maxValue = Math.max(maxValue, shark.value);
    	
		moveFish(map, fish);	// 물고기 이동 
		
		int r = shark.r;
		int c = shark.c;
		int dir = shark.dir;
		int curValue = shark.value;
		
		int nr = r, nc = c;
		for (int i = 0; i < 3; i++) {	// 상어가 최대 3번 이동할 수 있으므로 3번만 반복 
			nr += dr[dir];
			nc += dc[dir];
			
			if(map[nr][nc] == -1) break;	// 경계를 벗어나면 탐색 종료 
			else if(map[nr][nc] == 0) continue;	// 빈칸이면 이동할 수 없으니 continue 
			
			int[][] temp = new int[6][6];	// 임시 배열 설정 
			Fish[] tempFish = new Fish[17];
			for (int j = 0; j < 6; j++) temp[j] = map[j].clone();
			for (int j = 1; j < 17; j++) {
				tempFish[j] = new Fish(fish[j].r, fish[j].c, fish[j].dir);
				tempFish[j].isAlive = fish[j].isAlive;
			}
			
			int index = temp[nr][nc];	// 이동하려는 칸의 물고기 번호 
			int ndir = tempFish[index].dir;	// 이동한 위치의 물고기 방향으로 상어 방향 변경 
			
			temp[r][c] = 0;	// 상어가 있던 곳 빈칸으로 변경 
			temp[nr][nc] = -2;	// 현재 상어가 이동한 곳 -2로 변경 
			
			tempFish[index].isAlive = false;	// index 물고기 죽었다..
			Shark newShark = new Shark(nr, nc, ndir, curValue + index);
			
			dfs(newShark, temp, tempFish);
		}
	}
    
    private static void moveFish(int[][] map, Fish[] fish) {
		for (int i = 1; i <= 16; i++) {
			if(fish[i].isAlive) {	// i번째 물고기가 살아있으면 이동 
				int r = fish[i].r;
				int c = fish[i].c;
				int dir = fish[i].dir;
				
				int nr, nc, ndir;
				for (int j = 0; j < 8; j++) {	// 모든 방향으로 이동 시도 
					ndir = (dir + j) % 8;
					nr = r + dr[ndir];
					nc = c + dc[ndir];
					
					int next = map[nr][nc];	// 이동할 곳에 해당하는 값 
					
					if(next >= 0) {		// 이동할 수 있는 경우 
						// i번째 물고기 이동 
						map[nr][nc] = i;
						fish[i].r = nr;
						fish[i].c = nc;
						fish[i].dir = ndir;
						
						// 이동한 곳이 빈칸이 아니라 다른 물고기가 있었다면, 해당 물고기 위치 변경 
						if(next != 0) {
							fish[next].r = r;
							fish[next].c = c;
						}
						
						map[r][c] = next;
						break;
					}
				}
			}
		}
	}
 
}