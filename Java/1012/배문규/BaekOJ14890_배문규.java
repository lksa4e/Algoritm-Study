package BaekOJ.study.date1012;

import java.io.*;
import java.util.*;

public class BaekOJ14890_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, X, cnt, map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 행 탐색
		getResult();
		// 회전
		map = rotate(map);
		// 열 탐색
		getResult();
	 	
 		System.out.println(cnt);
	}
	
	public static void getResult() {
	loop : for(int i = 0; i < N; i++) {
			int up = 1, down = X, h = map[i][0];
			for(int j = 1; j < N; j++) {
				// 같은 경사일 때 먼저 내리막 경사 공간을 확보해야 하는 경우에 내리막 경사를 확보하고, 아니면 오르막 경사 공간을 확보함
				if(h == map[i][j]) {
					if(down < X) down++; // 내리막 길이를 확보해야하는 경우
					else up++; // 이미 내리막 길이가 확보되어 있으면 그 때 부터 오르막 길이 확보
				}
				// 이전보다 높이가 1만큼 더 높아졌는데, 오르막 경사를 세울 공간이 확보됐다면
				else if(map[i][j]== h + 1 && up >= X) {
					h = map[i][j];
					up = 1; // 오르막 여유길이 초기화
				}
				// 이전보다 높이가 1만큼 더 낮아졌는데, 내리막 경사를 확인할 준비가 됐다면
				else if(map[i][j]== h - 1 && down == X) {
					h = map[i][j];
					up = 0; // 지금까지의 오르막 길이는 의미가 없고  down 자리가 확보될 때 까지 up은 계속 0으로 유지
					down = 1; // 내리막 여유길이 초기화
				}
				// 위 조건들을 만족하지 않거나, 높이차가 1보다 더 클 때
				else continue loop;
			}
			
			// 내리막 경사를 세울 공간을 확보하지 못하고 끝났다면
			if(down != X) continue loop;
			cnt++;
 		}
	}
	
	// 열 탐색을 행 탐색으로 바꿔주기 위해 배열을 시계방향으로 회전시키는 메소드
	public static int[][] rotate(int map[][]){
		int[][] copy = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) copy[j][N-i-1] = map[i][j];
		}
		return copy;
	}
}
