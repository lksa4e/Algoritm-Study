import java.io.*;
import java.util.*;

/**
 * [1106] BOJ 20061 모노미노도미노2
 * 시뮬레이션, 2차원 배열, 구현
 * 
 * sol)
 * 파란색 보드와 초록색 보드 로직은 같지만 행과 열이 다르므로 각각 행렬로 관리
 *
 */

public class BOJ_20061_모노미노도미노2 {
	static boolean[][] RED = new boolean[4][4];          // 빨강색 보드
	static boolean[][] BLUE = new boolean[4][7];         // 파란색 보드
	static boolean[][] GREEN = new boolean[7][4];        // 초록색 보드
	
	static boolean[] full = new boolean[6];
	static int score, tile;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 파랑, 초록 보드는 블록을 새롭게 놓을 때 이전에 채워진 칸 옆이나 위로 쌓아야하므로 
		// 맨 마지막 열이나 행은 블록이 채워진 것같은 효과를 내기 위해 true로 초기화
		for (int i=0; i<4; i++) {
			BLUE[i][6] = true;
			GREEN[6][i] = true;
		}
		
		int N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 블록 놓는 명령어 한줄씩 처리
			switch (t) {
			case 1:                      // (1x1) 블록
				moveBlue(x, 0, 1);
				moveGreen(y, 1, 0);
				break;
			case 2:                      // (1x2) 블록
				moveBlue(x, 0, 2);
				moveGreen(y, 1, 1);
				break;
			case 3:                      // (2x1) 블록
				moveBlue(x, 1, 1);
				moveGreen(y, 2, 0);
				break;
			}
			
			// 행 또는 열이 타일로 가득차면 점수내고
			removeFullCol();
			removeFullRow();
			// 연한 부분에 블록이 차면 블록을 아래로 밀어내기
			moveBrightBlue();
			moveBrightGreen();
		}
		
		// 파란 보드, 초록 보드에 있는 타일 개수 세기
		countBlueTile();
		countGreenTile();
		System.out.println(score);
		System.out.println(tile);
	}

	// 파란 보드판 블록 이동
	private static void moveBlue(int r, int h, int w) {		//h : -1로 들어와야함, w : 그대로 들어와야함
		for (int c=0; c<=6; c++) {
			if (BLUE[r][c] || BLUE[r+h][c]) {       // (2x1) 블록인 경우까지 고려하여 블록이 차있는 열 찾기
				BLUE[r+h][c-1] = true;              // 찾았으면 그 옆으로 쌓음
				BLUE[r][c-1] = true;
				BLUE[r][c-w] = true;
				break;
			}
		}
	}
	
	private static void moveGreen(int c, int h, int w) {	// h : 그대로 들어와야함, w : -1로 들어와야함
		for (int r=0; r<=6; r++) {
			if (GREEN[r][c] || GREEN[r][c+w]) {       // (1x2) 블록인 경우까지 고려하여 블록이 차있는 행 찾기
				GREEN[r-1][c+w] = true;               // 찾았으면 그 위로 쌓음
				GREEN[r-1][c] = true;
				GREEN[r-h][c] = true;
				break;
			}
		}
	}
	
	// 가득 차있는 열 제거
	private static void removeFullCol() {
		Arrays.fill(full, false);
		int start = -1;
		
		for (int c=0; c<6; c++) {
			boolean flag = true;
			for (int r=0; r<4; r++) {
				if (!BLUE[r][c]) flag = false;      // 하나라도 비어있으면 false
			}
			if (flag) {                             // 가득 차있는 열 찾으면 점수내기
				score++;
				full[c] = true;
				start = c;                          // 최종 업데이트된 start에는 가득찬 열 중 가장 오른쪽 열 인덱스가 저장됨
			}
		}
		if (start==-1) return;
		
		for (int r=0; r<4; r++) {
			BLUE[r][start] = false;                  // 가장 오른쪽 열부터 비우고
			int fullCnt = 1;
			for (int c=start-1; c>=0; c--) {
				if (full[c]) {
					fullCnt++;                       // 가득 차있던 열 왼쪽 한 열씩 확인하면서 가득차있지 않은 열의 개수를 세어
					continue;
				}
				BLUE[r][c+fullCnt] = BLUE[r][c];     // 그만큼 이동(블럭을 오른쪽으로 미는 효과)
				BLUE[r][c] = false;
			}
		}
	}

	// 가득 차있는 행 제거
	private static void removeFullRow() {
		Arrays.fill(full, false);
		int start = -1;
		
		for (int r=0; r<6; r++) {
			boolean flag = true;
			for (int c=0; c<4; c++) {
				if (!GREEN[r][c]) flag = false;
			}
			if (flag) {
				score++;
				full[r] = true;
				start = r;                           // 최종 업데이트된 start에는 가득찬 행 중 가장 바닥쪽 행 인덱스가 저장됨
			}
		}
		if (start==-1) return;
		
		for (int c=0; c<4; c++) {
			GREEN[start][c] = false;                 // 가장 바닥쪽 행부터 비우고
			int fullCnt = 1;
			for (int r=start-1; r>=0; r--) {
				if (full[r]) {
					fullCnt++;                       // 가득 차있던 행 위쪽 한 행씩 확인하면서 가득차있지 않은 행 개수를 세어
					continue;
				}
				GREEN[r+fullCnt][c] = GREEN[r][c];   // 그만큼 이동(블럭을 아래로 미는 효과)
				GREEN[r][c] = false;
			}
		}
	}

	// 파란 보드의 연한 부분 블럭 제거
	private static void moveBrightBlue() {
		int cnt = 0;
		
		for (int c=0; c<=1; c++){
			for (int r=0; r<4; r++) {
				if (BLUE[r][c]) {
					cnt++;              // 연한쪽에 위치한 블럭 위치 찾기
					break;
				}
			}
		}
		
		if (cnt==0) return;
		
		for (int r=0; r<4; r++) {
			int remove = cnt;
			for (int c=5; c>=0; c--){      // 오른쪽 열부터 확인하면서
				if (remove-->0) {          // 연한쪽에 위치한 블럭 칸 수 만큼 밀기
					BLUE[r][c] = false;
					continue;
				}
				BLUE[r][c+cnt] = BLUE[r][c];
				BLUE[r][c] = false;
			}
		}
	}

	// 초록 보드의 연한 부분 블럭 제거
	private static void moveBrightGreen() {
		int cnt = 0;
		
		for (int r=0; r<=1; r++){
			for (int c=0; c<4; c++) { 
				if (GREEN[r][c]) {
					cnt++;
					break;
				}
			}
		}
		
		if (cnt==0) return;
		
		for (int c=0; c<4; c++) {
			int remove = cnt;
			for (int r=5; r>=0; r--){      // 바닥쪽 행부터 확인하면서
				if (remove-->0) {          // 연한쪽에 위치한 블럭 칸 수 만큼 밀기
					GREEN[r][c] = false;
					continue;
				}
				GREEN[r+cnt][c] = GREEN[r][c];
				GREEN[r][c] = false;
			}
		}
	}
	
	// 파란 보드 타일 수 세기
	private static void countBlueTile() {
		for (int r=0; r<4; r++) {
			for (int c=0; c<6; c++) if (BLUE[r][c]) tile++;
		}
	}
	
	// 초록 보드 타일 수 세기
	private static void countGreenTile() {
		for (int r=0; r<6; r++) {
			for (int c=0; c<4; c++) if (GREEN[r][c]) tile++;
		}
	}

}
