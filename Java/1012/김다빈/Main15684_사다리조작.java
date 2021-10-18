import java.io.*;
import java.util.*;

/**
 * 백준 15684번 사다리 조작
 */
public class Main15684_사다리조작 {
	
	static int N, M, H;
	static int[][] map;
	static int answer;
	static boolean finish = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			//오른쪽으로 가는 경우 
			map[x][y] = 1;
			//왼쪽으로 가는 경우
			map[x][y+1] = 2;
		}
		
		//다리를 놓을 수 있는 갯수인 3번 반복하며 조합 수행하기 
		for (int i = 0; i <= 3; i++) {
			answer = i;
			combi(1, 0);
			if(finish) break;
		}
		
		if(finish) System.out.println(answer);
		else System.out.println(-1);
	}
	
	private static void combi(int r, int cnt) {
		if(finish) return;
		
		if(answer == cnt) {
			if(check()) finish = true;
			return;
		}
		
		for (int i = r; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if(map[i][j] == 0 & map[i][j+1] == 0) {	// 사다리를 놓지 않았던 곳이면 시도 
					map[i][j]=1;	// 오른쪽 
					map[i][j+1]=2;	// 왼쪽 
					combi(i,cnt+1);
					map[i][j]=0;
					map[i][j+1]=0;
				}
			}
		}
	}

	// i번째 줄이 i번에 도착하는지 체크하는 함수 
	private static boolean check() {
		for (int i = 1; i <= N; i++) {
			int nx = 1;
			int ny = i;
			
			for (int j = 0; j < H; j++) {
				if(map[nx][ny]==1) ny++;	// 오른쪽으로 이동  
				else if(map[nx][ny]==2) ny--;	// 왼쪽으로 이동 
				nx++;	// 아래로 이동 
			}
			if(ny != i) return false;
		}
		return true;
	}
}