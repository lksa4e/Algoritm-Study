package BaekOJ.study.date1120;

import java.io.*;
import java.util.*;

/*
 * 백준 21610 마법사 상어와 비바라기
 * 
 * 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다. 
 * 마법사 상어는 연습을 위해 1번 행과 N번 행을 연결했고, 1번 열과 N번 열도 연결했다. 
 * 즉, N번 행의 아래에는 1번 행이, 1번 행의 위에는 N번 행이 있고, 
 * 1번 열의 왼쪽에는 N번 열이, N번 열의 오른쪽에는 1번 열이 있다.
 * 
 * 구름에 이동을 M번 명령하려고 한다. i번째 이동 명령은 방향 di과 거리 si로 이루어져 있다. 
 * 방향은 총 8개의 방향이 있으며, 8개의 정수로 표현한다. 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다.
 * 
 * 1. 구름 이동 : 모든 구름이 di 방향으로 si칸 이동한다.
 * 2. 비 내림 : 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
 * 3. 구름 소멸 : 구름이 모두 사라진다.
 * 4. 물 복사 : 2.에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
 *  	- 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
 *   	- 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
 * 5. 구름 생성 : 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
 * 
 * 주어진 조건대로 구현하면 되는 문제
 * 
 * 메모리 	시간
 * 21276	252
 */

public class BaekOJ21610_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, M, map[][];
	static int deltaMove[][] = {
			{0,0}, {0,-1}, {-1,-1},
			{-1,0}, {-1,1}, {0,1},
			{1,1}, {1,0}, {1,-1}};
	static int deltaCheck[][] = {{-1,-1}, {1,1}, {-1,1}, {1,-1}};
	static boolean check[][];
	static Queue<int[]> cloudQueue = new ArrayDeque<int[]>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		check = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 비바라기로 초기 구름 생성
		cloudQueue.offer(new int[] {N-1,0});
		cloudQueue.offer(new int[] {N-1,1});
		cloudQueue.offer(new int[] {N-2,0});
		cloudQueue.offer(new int[] {N-2,1});
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			turn(dir, size);
		}
		
		System.out.println(getResult());
	}
	
	public static void turn(int dir, int size) {
		initCheck();
		move_Rain_Vanish(dir, size);
		copy();
		generate();
	}
	
	public static void move_Rain_Vanish(int dir, int size) {
		int s = cloudQueue.size();
		for(int i = 0; i < s; i++) {
			int[] cloud = cloudQueue.poll(); // 3. 구름 소멸
			int ci = cloud[0] + deltaMove[dir][0]*size;
			int cj = cloud[1] + deltaMove[dir][1]*size;
			
			// 1. 구름 이동
			if(ci < 0) ci = (ci%N)+N;
			if(cj < 0) cj = (cj%N)+N;
			ci %= N;
			cj %= N;
			
			// 2. 비내림
			map[ci][cj]++;
			
			// 비내린 지역 큐에 추가
			cloudQueue.offer(new int[] {ci, cj});
			
			// 비내린 지역 체크
			check[ci][cj] = true;
		}
	}
	
	public static void copy() {
		// 4. 물 복사
		int size = cloudQueue.size();
		for(int i = 0; i < size; i++) {
			int[] cloud = cloudQueue.poll();
			
			int cnt = 0;
			// 대각선 체크
			for(int[] c : deltaCheck) {
				int ci = cloud[0] + c[0];
				int cj = cloud[1] + c[1];
				if(isOOB(ci, cj)) continue;
				
				if(map[ci][cj] > 0) cnt++;
			}
			
			// 물복사버그
			map[cloud[0]][cloud[1]] += cnt;
		}
	}
	
	public static void generate() {
		// 5. 구름 생성
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 비가 내린 지역이 아니면서 바구니에 저장된 물의 양이 2 이상이면 구름 생김
				if(!check[i][j] && map[i][j] >= 2) {
					cloudQueue.offer(new int[] {i, j});
					map[i][j] -= 2;
				}
			}
		}
	}
	
	public static void initCheck() {
		for(int i = 0; i < N; i++) Arrays.fill(check[i], false);
	}
	
	public static int getResult() {
		int result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) result += map[i][j];
		}
		return result;
	}
	
	public static boolean isOOB(int i, int j) {
		return i > N - 1 || i < 0 || j > N - 1 || j < 0;
	}
}
