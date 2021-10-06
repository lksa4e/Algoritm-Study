package BaekOJ.study.date1005;

import java.io.*;
import java.util.*;

/*
 * 청소기의 인덱스와 방향이 주어지면 dfs로 청소를함
 * 
 * 1. 현재 방향에서 왼쪽 회전
 * 2. 청소할 수 있으면 청소하고 해당 방향으로 전진
 * 3. 벽이거나, 이미 청소를 했다면  계속 왼쪽으로 회전하며 청소할 수 있는지 확인
 * 4. 한바퀴 돌 때 까지 청소를 하지 않았다면 후진 시도
 * 5. 후진하려고 했는데 만약 뒤에가 벽이면 종료, 벽이 아니면 후진하고 다시 전체 반복
 * 
 * 문제 표현이 뭔가 애매모호한 것 같아서 이해하는데 좀 걸림
 * 뇌정지와서 직접 테이블을 그리기 전까지 왼쪽 회전하는 인덱스를 못구함..
 * 
 * 메모리 	시간
 * 14440	148
 */

public class BaekOJ14503_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int DIRTY = 0;
	static final int CLEAN = 2;

	static int N, M, result, map[][];
	static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int di = Integer.parseInt(st.nextToken());
		int dj = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dfs(di, dj, d);
		
		System.out.println(result);
	}
	
	public static void dfs(int i, int j, int d) {
		// 청소할 수 있으면 청소하고 카운트
		if(isDirty(i, j)) {
			map[i][j] = CLEAN;
			result++;
		}
		/*
		 * x   l   d   r   u
		 * 0   3   2   1   0
		 * 1   0   3   2   1
		 * 2   1   0   3   2
		 * 3   2   1   0   3
		 * 위 테이블을 그려보고 left 회전된 인덱스를 찾음
		 */
		for(int x = 3; x >= 0; x--) {
			int left = (x+d)%4;
			int di = i + delta[left][0];
			int dj = j + delta[left][1];
			if(isDirty(di, dj)) {
				dfs(di, dj, left);
				return; // dfs 빠져나오면 그냥 끝
			}
		}

		// 4방향 모두 청소할 곳이 없는데 뒤가 벽이아니라면 후진하고 다시 dfs
		int back = (d+2)%4;
		if(map[i + delta[back][0]][j + delta[back][1]] == CLEAN) {
			dfs(i + delta[back][0], j + delta[back][1], d);
		}
	}
	
	public static boolean isDirty(int i, int j) {
		return map[i][j] == DIRTY;
	}
}
