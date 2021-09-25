package BaekOJ.study.date0921;

import java.io.*;
import java.util.*;

/*
 * 파이프의 헤드 부분만 이동시키면 됨 그리고 다시 뒤로 돌아 올 수 없는 델타가 주어지고, 같은 장소로 도착하는 여러 경로를 알아야되니
 * 체크배열은 필요없음. 상태에 따라 갈 수 있는 방향조절만 잘해주고 갈 수 있는지 없는지 체크를 해주면 됨
 * 
 * 시행착오 : 
 * 1. 경로에 도달할 때 마다 map[di][dj]--를 하고 System.out.println(map[N-1][N-1]*-1);을 하니까 1% 컷 
 * 도착지에 도착하면 cnt++하고 cnt를 프린트하니까 문제 해결됨 
 * 마지막 도착지가 1이라서 도달을 못하는 경우에 isBlock 메소드에서 걸러질 줄 알았는데 아니었음...
 * 
 * 2. dfs파라미터로 new int[]{i, j}를 넘겨주면
 * 메모리 	시간 
 * 166716	428
 * 그냥 int i, int j를 넘겨주면
 * 메모리 	시간 
 * 15448	296
 * 
 */
public class BaekOJ17070_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, map[][], cnt;
	static int state[][] = {{0, 2}, {1, 2}, {0, 1, 2}};
	static int delta[][] = {{0, 1}, {1, 0}, {1, 1}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 1, 0);
		System.out.println(cnt);
	}
	
	// 파이프의 i, j와 파이프의 현재 상태
	public static void dfs(int i, int j, int nowState) {
		// 마지막에 도착하면 cnt++
		if(i == N-1 && j == N-1) {
			cnt++;
			return;
		}
		
		// 현재 상태에서 갈 수 있는 델타로 이동
		for(int s : state[nowState] ) {
			int di = i + delta[s][0];
			int dj = j + delta[s][1];
			
			if(isBlock(di, dj, s)) continue; // 갈 수 없으면 continue
			dfs(di, dj, s); // 파이프 새로운 위치, 상태
		}
	}
	
	public static boolean isOOB(int i, int j) {
		if (i>N-1 || i<0 || j>N-1 || j<0) return true;
		else return false;
	}
	
	// 갈 수 있는지 없는지 체크
	public static boolean isBlock(int i, int j, int state) {
		if(isOOB(i, j)) return true; // 맵 경계 체크
		if(map[i][j] == 1) return true; // 벽 체크
		if(state == 2 && (map[i-1][j] == 1 || map[i][j-1] == 1)) return true; // 상태가 대각이면 왼, 위 까지 체크
		return false;
	}
}
