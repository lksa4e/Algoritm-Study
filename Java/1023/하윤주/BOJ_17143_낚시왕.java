import java.io.*;
import java.util.*;

/**
 * [1019] BOJ 17143 낚시왕
 * 구현, 시뮬레이션
 * 
 * sol)
 * 상어의 이동이 행 기준으로 이동하거나 열 기준으로 이동하기때문에 1차원 배열로 생각하여 모듈러 연산하면 될 것 같았는데
 * 계산이 너무 안되길래 아주 비효율적인 한칸씩 이동 방식으로 구현했습니다 ㅎ
 * 
 */

public class BOJ_17143_낚시왕 {
	static final int SPEED = 0;
	static final int DIR = 1;
	static final int SIZE = 2;
	static int R, C, M, total;
	static int[][][] map, copiedMap;
	static int[] dx = {-1, 1, 0, 0};          // 상, 하, 우, 좌
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 지도 상에 상어를 기록하되, 속력, 방향, 크기를 배열로 저장한다
		map = new int[R][C][3];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c][SPEED] = Integer.parseInt(st.nextToken());
			map[r][c][DIR] = Integer.parseInt(st.nextToken()) - 1;
			map[r][c][SIZE] = Integer.parseInt(st.nextToken());
		}
		
		// 낚시 시작
		fishing();
		System.out.println(total);
	}

	// 낚시
	private static void fishing() {
		// 1. 낚시왕 1초마다 이동
		for (int c=0; c<C; c++) {
			// 2. 가장 가까운 상어 낚시
			fishShark(c);
			// 3. 상어 이동
			moveShark();
		}
		
	}

	// 가장 가까운 상어 낚시
	private static void fishShark(int c) {
		for (int r=0; r<R; r++) {
			if (map[r][c][SIZE] != 0) {         // 열 기준으로 최초로 상어 등장하는 시점 찾아 낚시
				total += map[r][c][SIZE];
				map[r][c][SIZE] = 0;
				return;
			}
		}
	}

	// 상어 이동
	private static void moveShark() {
		copiedMap = new int[R][C][3];                   // 상어가 동시에 이동하기 위해 지도 복사
		
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (map[i][j][SIZE] == 0) continue;
				findLocation(i, j);                     // 상어가 이동해서 도착할 위치 찾기
			}
		}
		map = copyMap(copiedMap, map);
	}

	// 상어의 이동 위치 찾기
	private static void findLocation(int x, int y) {
		int s = map[x][y][SPEED];
		int d = map[x][y][DIR];
		int z = map[x][y][SIZE];
		
		// 속력만큼 이동
		for (int i=0; i<s; i++) {
			x += dx[d];
			y += dy[d];
			if (!isInside(x, y)) {             // 한칸씩 이동하다가 벽을 만나면
				if (d%2!=0) d--;               // 하, 좌 -> 상, 우
				else d++;                      // 상, 우 -> 하, 좌
				x += 2 * dx[d];                // 방향을 바꿨으면 가장 바깥 행렬 다음 칸으로 이동
				y += 2 * dy[d];
			}
		}
		if (z>copiedMap[x][y][SIZE]) {         // 크기가 큰 상어가 점령
			copiedMap[x][y][SPEED] = s;
			copiedMap[x][y][DIR] = d;
			copiedMap[x][y][SIZE] = z;
		}
	}
	
	// 지도 복사
	private static int[][][] copyMap(int[][][] origin, int[][][] copied) {
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				for (int k=0; k<3; k++)
				copied[i][j][k] = origin[i][j][k];
			}
		}
		return copied;
	}

	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<R && y>=0 && y<C);
	}

}
