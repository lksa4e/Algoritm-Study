import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 19237번 어른 상어
 * 
 * 시뮬레이션
 * 
 * 상어 마다 현재 방향에 따른 우선순위가 있고, 이 우선순위의 인덱스 설정해 주는게 어려웠다.
 */

public class BOJ_19237 {
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	
	static int N, M, k, map[][][], sharkOrder[][][], sharkLoc[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[N][N][3];	// 0: 맵 1: 어떤 상어의 흔적 2: 흔적 남은 기간
		sharkLoc = new int[M+1][3]; // 상어의 row, col, dir
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int area = Integer.parseInt(st.nextToken());
				map[i][j][0] = area;
				if(area > 0) {
					sharkLoc[area][0] = i;
					sharkLoc[area][1] = j;
				}
			}
		}
		sharkLoc[0][0] = -1; // 0번째 인덱스는 사용하지 않음
		
		// 상어의 초기 방향 설정
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharkLoc[i][2] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		sharkOrder = new int[M][4][4];	// 상어의 idx, 상어의 방향, 방향에 따른 우선순위
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					sharkOrder[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		
		System.out.println(moveShark());
	}
	
	// 상어가 1마리가 될 때까지 반복 혹은 시간이 1000초가 넘기면 out
	static int moveShark() {
		int time = 0, sharkCnt = M;
		
		for (time = 1; time <= 1000; time++) {
			// 냄새 뿌리기
			markMap();
			
			// 상어 이동
			// 상어의 현재 방향에 따라 우선순위 방향으로 이동한다.
			for (int i = 1; i <= M; i++) {
				int row = sharkLoc[i][0];
				if(row == -1) continue; // 쫓겨난 상어
				int col = sharkLoc[i][1];
				
				boolean isMove = false;
				int dir = sharkLoc[i][2];	// 현재 방향
				for (int j = 0; j < 4; j++) { // 우선 순위 따라 이동
					int nd = sharkOrder[i-1][dir][j]; // 우선 순위에 따른 방향
					
					int nextR = row + dr[nd];
					int nextC = col + dc[nd];
					if(isOutOfMap(nextR, nextC)) continue;
					if(map[nextR][nextC][1] != 0) continue; // 상어 흔적이 있는 경우
					
					if(map[nextR][nextC][0] != 0) {	// 이동 하려고 했는데 거기에 다른 상어가 있을 때, 현재 상어 삭제(작은 순서대로 상어가 움직이고 있으므로)
						isMove = true;
						map[row][col][0] = 0;
						sharkLoc[i][0] = -1;
						sharkCnt--;
						break;
					}
					
					// 상어 이동
					isMove = true;
					map[row][col][0] = 0;
					map[nextR][nextC][0] = i;
					sharkLoc[i][0] = nextR;
					sharkLoc[i][1] = nextC;
					sharkLoc[i][2] = nd;	// 방향도 갱신
					break;
				}
				
				if(!isMove) {	// 이동하지 못했기 때문에 자기 흔적 찾아서 이동
					for (int j = 0; j < 4; j++) { // 우선 순위 따라 이동
						int nd = sharkOrder[i-1][dir][j]; // 우선 순위에 따른 방향
						int nextR = row + dr[nd];
						int nextC = col + dc[nd];
						if(isOutOfMap(nextR, nextC)) continue;
						if(map[nextR][nextC][1] == i) {	// 자기 흔적 따라서 이동
							// 상어 이동
							isMove = true;
							map[row][col][0] = 0;
							map[nextR][nextC][0] = i;
							sharkLoc[i][0] = nextR;
							sharkLoc[i][1] = nextC;
							sharkLoc[i][2] = nd;	// 방향도 갱신
							break;
						}
					}
				}
				
				if(sharkCnt == 1) return time;
			}
			
			// 흔적 1씩 줄여주기
			reduceMark();
		}
		return -1;	// 시간 제한 넘기면 -1 리턴
	}
		
	static void markMap() {
		for (int i = 1; i <= M; i++) {
			int row = sharkLoc[i][0];
			if(row == -1) continue; // 쫓겨난 상어
			int col = sharkLoc[i][1];
			map[row][col][1] = i;	// 맵에 i라는 상어가 k만큼의 지속시간을 가진 마크를 남김
			map[row][col][2] = k;
		}
	}
	
	static void reduceMark() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j][2] > 0) {
					map[i][j][2]--;
					if(map[i][j][2] == 0)	// 흔적이 사라지면, 어떤 상어가 남긴 흔적인지도 삭제
						map[i][j][1] = 0;
				}
			}
		}
	}
	
	static boolean isOutOfMap(int r, int c) {
		return r < 0 || c < 0 || r >= N || c >= N;
	}

}
