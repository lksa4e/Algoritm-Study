package BaekOJ.study.date1228;

import java.io.*;
import java.util.*;

/*
 * 백준 10026 적록색약
 * 
 * 처음에 입력받을 때 map배열을 일반용, 적록색약용 2개를 만들어서 각각  bfs를 해줌
 * 
 * 메모리 	시간
 * 14520	144
 */

public class BaekOJ10026_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, delta[][] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static Queue<Integer> queue = new ArrayDeque<Integer>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		char[][] mapRG = new char[N][N];
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = mapRG[i][j] = input.charAt(j);
				if(mapRG[i][j] == 'G') mapRG[i][j] = 'R';	// 적록색약 map 만들기
			}
		}
		// 일반 map bfs + 적록색약 map bfs
		sb.append(bfs(map)).append(" ").append(bfs(mapRG));
		System.out.println(sb);
	}
	
	public static int bfs(char[][] map) {
		int cnt = 0;
		boolean[][] check = new boolean[N][N];
		for(int idx = 0; idx < N*N; idx++) {	// N*N을 선형탐색
			int i = idx/N;
			int j = idx%N;
			if(!check[i][j]) {
				char color = map[i][j];
				queue.offer(idx);
				while(!queue.isEmpty()) {
					int index = queue.poll();
					i = index/N;
					j = index%N;
					for(int[] d : delta) {
						int di = i+d[0];
						int dj = j+d[1];
						if(!isOOB(di, dj) && !check[di][dj] && map[di][dj] == color) {
							check[di][dj] = true;
							queue.offer(di*N+dj);
						}
					}
				}
				cnt++;
			}
		}
		return cnt;
	}
	
	public static boolean isOOB(int i, int j) {
		return i > N - 1 || i < 0 || j > N - 1 || j < 0;
	}
}