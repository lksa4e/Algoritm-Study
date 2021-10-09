import java.io.*;
import java.util.*;

/**
 * G3 BOJ 19237 어른상어 
 * 메모리 : 15676kb 시간 : 180ms
 * 
 * 청소년보단 어른이 훨씬 나은듯
 */
public class BOJ19237_G3_어른상어 {
	static int N,M,K, map[][];
	static Shark[] shark;
	static Board[][] board;
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		shark = new Shark[M+1];
		map = new int[N][N];
		board = new Board[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = new Board();
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) shark[map[i][j]] = new Shark(i,j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= M; i++) {
			shark[i].dir = Integer.parseInt(st.nextToken()) - 1;
		}
		
		for(int i = 1; i <= M; i++) {
			for(int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < 4; k++) {
					shark[i].prio[j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		System.out.println(process());
	}
	static int process() {
		int time = 1;
		while(time <= 1000) {
			// 냄새뿌리기
			setSmell(time);
			
			boolean tempcheck[] = new boolean[M+1];
			// 아무 냄새가 없는 칸으로 이동
			noSmell(tempcheck, time);
			
			// 자신의 냄새가 있는 칸으로 방향잡기
			yesSmell(tempcheck, time);
			
			// 자리 이동 후 냄새뿌리기
			setSmell(time + 1);
			
			// 1번 상어만 남았는지?
			if(onlyFirstShark()) return time;
			time++;
		}
		return -1;
	}
	
	// 2~M번 상어 중 살아있는게 하나라도 있으면 return false;
	static boolean onlyFirstShark() {
		for(int i = 2; i <= M; i++) {
			if(shark[i].live) return false;
		}
		return true;
	}
	
	// 내가 뿌린 냄새로 이동하는 함수
	static boolean yesSmell(boolean[] tempcheck, int time) {
		for(int i = 1; i <= M; i++) {
			if(!shark[i].live) continue; // 죽은건 pass
			if(tempcheck[i]) continue; // 냄새없는곳으로 이미 이동했으면 pass
			
			int dir = shark[i].dir;
			for(int j = 0; j < 4; j++) {
				int nx = shark[i].x + dx[shark[i].prio[dir][j]];
				int ny = shark[i].y + dy[shark[i].prio[dir][j]];
				
				if(oob(nx,ny)) continue;
				
				// 만약 내가 뿌린 냄새고, 냄새 뿌린지 K초가 지나지 않았다면 -> 이동 가능
				if(board[nx][ny].shark_num == i && time - K <= board[nx][ny].smell) {
					// 원래 있던자리 치우기
					map[shark[i].x][shark[i].y] = 0;
					// 빈자리 or 기존에 있던 상어보다 내가 쎄면
					if(map[nx][ny] == 0 || map[nx][ny] > i) {
						// 자리 차지하기
						shark[i].x = nx;
						shark[i].y = ny;
						shark[i].dir = shark[i].prio[dir][j];
						map[nx][ny] = i;
					}else {
						// 나보다 쎈 상어 있으면 주금
						shark[i].live = false;
					}
					break;
				}
			}
		}
		return false;
	}
	
	// 인접한 냄새 없는곳으로 이동하는 함수
	static void noSmell(boolean[] tempcheck, int time) {
		for(int i = 1; i <= M; i++) {
			if(!shark[i].live) continue;
			int dir = shark[i].dir;
			for(int j = 0; j < 4; j++) {
				int nx = shark[i].x + dx[shark[i].prio[dir][j]];
				int ny = shark[i].y + dy[shark[i].prio[dir][j]];
				if(oob(nx,ny)) continue;
				
				// 기존 냄새가 있는데 뿌린지 K초가 안지나서 남아있는 상태면 continue
				if(board[nx][ny].smell != -1 && time - K < board[nx][ny].smell) continue;
				
				// 일단 이동시킴
				map[shark[i].x][shark[i].y] = 0;
				
				// 내가 이미 있는 상어보다 더 쌘경우
				if(map[nx][ny] == 0 || map[nx][ny] > i) {
					// 해당 자리를 차지하기 
					shark[i].x = nx;
					shark[i].y = ny;
					shark[i].dir = shark[i].prio[dir][j];
					map[nx][ny] = i;
				}else {
					// 내가 경쟁에서 밀렸다면 주금
					shark[i].live = false;
				}
				// 해당 상어는 이동을 완료했다고 체크
				tempcheck[i] = true;
				break;
			}
		}
	}
	// 냄새를 뿌리는 함수
	static void setSmell(int cnt) {
		for(int i = 1; i <= M; i++) {
			if(!shark[i].live) continue;
			int x = shark[i].x;
			int y = shark[i].y;
			// board 배열을 갱신해줌
			board[x][y].shark_num = i;
			board[x][y].smell = cnt;
		}
	}
	
	static class Board{
		int shark_num = -1, smell = -1;
	}
	static class Shark{
		int x, y, dir;
		boolean live = true;
		int prio[][] = new int[4][4];
		public Shark(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static boolean oob(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}

}
