import java.util.*;
import java.io.*;

/**
 * 백준 17143번 낚시왕
 * 
 * 풀이 : 구현
 *
 * 상어가 이동할 위치를 구할 때 속력만큼 반복문을 돌리지 않고
 * 
 * if(dir < 2) iter %= (2 * (R-1));	// 상하 
 * else iter %= (2 * (C-1));	// 좌우
 * 
 * 상하로 이동하면 (R-1)*2, 좌우로 이동하면 (C-1)*2의 나머지만큼만 이동 수행
 * 
 * 127760KB	896ms
 */
public class Main17143_낚시왕_adv {

	static int R, C, sharkNum;
	static int[][] map;
	static Map<Integer, Shark> sharks = new HashMap<Integer, Shark>();
	
	static int[] dr = {-1,1,0,0};	// 상하우좌 
	static int[] dc = {0,0,1,-1};
	
	static class Shark {
		int r, c;	// 상어 위치 
		int speed, dir, size;	// 상어 속력, 방향, 크기 

		public Shark(int r, int c, int speed, int dir, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sharkNum = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int i = 1; i <= sharkNum; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int size = Integer.parseInt(st.nextToken());
			
			// i번째 상어 설정 
			map[r][c] = i;
			sharks.put(i, new Shark(r, c, speed, dir, size));
		}
		
		int result = 0;
		for(int i = 0; i < C; i++) {	// 1. 낚시왕이 오른쪽으로 한 칸 이동 
			// 2. i열에 있는 상어 중 가장 가까운 상어 잡기 
			result += fishShark(i);
			
			// 3. 상어들 이동 
			moveSharks();
		}
		
		System.out.println(result);
	}

	private static int fishShark(int c) {
		int sharkSize = 0;
		for(int r = 0; r < R; r++) {
			if(map[r][c] != 0) {
				// 낚시할 상어 크기 구하고 상어 제거
				int num = map[r][c];
				sharkSize = sharks.get(num).size;
				map[r][c] = 0;
				sharks.remove(num);
				break;
			}
		}
		return sharkSize;
	}
	
	private static void moveSharks() {
		int[][] tempMap = new int[R][C];
		
		ArrayList<Integer> sharkList = new ArrayList<Integer>();
		for (int num : sharks.keySet()) sharkList.add(num);
		
		for (int num : sharkList) {
			Shark curShark = sharks.get(num);
			int r = curShark.r;
			int c = curShark.c;
			
			int dir = curShark.dir;
			int ndr = dr[dir], ndc = dc[dir];
			
			// 상어가 이동할 위치 계산 
			int iter = curShark.speed;
			if(dir < 2) iter %= (2 * (R-1));
			else iter %= (2 * (C-1));
			
			for (int i = 0; i < iter; i++) {
				// 경계를 만나면 방향 전환
				int nr = r + ndr;
				int nc = c + ndc;
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
					ndr *= -1;
					ndc *= -1;
				}
				
				r += ndr;
				c += ndc;
			}
			
			// 기존 방향과 달라졌다면 변경 
			if(dr[dir] != ndr || dc[dir] != ndc) {
				sharks.get(num).dir = changeDir(dir);
			}
			
			if(tempMap[r][c] != 0 && sharks.get(tempMap[r][c]).size > curShark.size) {	// 이동할 위치에 상어가 있고 크기가 더 크면 이동 불가 
				sharks.remove(num);
			} else {	// 이동 && 기존에 상어가 있으면 제거
				if(tempMap[r][c] != 0) sharks.remove(tempMap[r][c]);
				sharks.get(num).r = r;
				sharks.get(num).c = c;
				tempMap[r][c] = num;
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = tempMap[i][j];
			}
		}
	}

	private static int changeDir(int dir) {
		if(dir == 0 || dir == 2) return dir+1;
		else return dir-1;
	}
	
}
