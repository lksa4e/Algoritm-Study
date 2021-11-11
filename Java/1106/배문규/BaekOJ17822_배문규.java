package BaekOJ.study.date1030;

import java.io.*;
import java.util.*;

/*
 * 백준 17882 원판 돌리기
 * 
 * 1. n개의 원판이있고 반지름은 1~n이며, 원판은 동심원을 이루고 있다.
 * 2. i번째 원판의 반지름은 i이다.
 * 3. 각각의 원판에는 M개의 정수가 적혀있는데, i번째 원판에 적힌 j번째 수의 위치는 (i,j)라고 표현한다.
 * - (i, 1)은 (i, 2), (i, M)과 인접하며 (i, M)은 (i, M-1), (i, 1)과 인접하다.
 * - (i, j)는 (i, j-1), (i, j+1)과 인접하다. (2 ≤ j ≤ M-1)
 * - (1, j)는 (2, j)와 인접하며 (N, j)는 (N-1, j)와 인접하다.
 * - (i, j)는 (i-1, j)와 (i+1, j)에 인접한다. (2 ≤ i ≤ N-1)
 * 4. 회전은 독립적으로 이루어진다.
 * 5. 번호가 x의 배수인 원판을 d방향으로 k칸 회전시킨다.
 * - d == 0 : 시계
 * - d == 1 : 반시계
 * 6. 원판에 수가 남아있으면 인접하면서 수가 같은 것을 모두 찾는다. 
 * - 있으면, 지운다.
 * - 없으면, 원판에 적힌 수의 평균을 구하고 평균보다 크다면 1을 빼고, 작다면 1을 더한다.
 * 7. T번 회전 후 원판에 적힌 수의 합을 구하여라.
 * 
 * 시행착오
 * 인접한 수가 없을 때, update메소드를 실행하는데 이 때 평균을 구할 때 0이 아닌 수의 수를 나누다 보니
 * 회전하는 중간에 이미 모두 0이 되는 순간을 체크하지 않으면  런타임 에러 (/ by zero) 발생함
 * 
 * 메모리 	시간
 * 14716	184
 * 
 */

public class BaekOJ17822_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, M, T, total, cnt, board[][];
	static boolean isAdjacent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		// 12341234 이렇게 한번 더 똑같은 배열을 패딩으로 추가
		board = new int[N+1][M+M];
		Arrays.fill(board[0], 1001);
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				board[i][j+M] = board[i][j];
			}
		}
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			turn(x, d, k); // 회전
			fillPadding(); // 패딩 갱신
			check(); // 인접하는 수가 있는지 체크
			
			if(cnt == 0) break; // 중간에 모두 0이 되면 break -> 아니면 런타임 에러 (/ by zero) 발생
			
			if(isAdjacent) delete(); // 인접한 수가 있으면 제거
			else update(); // 없으면 업데이트
		}
		
		System.out.println(getResult());
	}
	
	public static void turn(int x, int d, int k) {
		for(int i = 1; i <= N; i++) {
			// x의 배수만 회전
			if(i % x != 0) continue;
			// 시계
			if(d == 0) {
				for(int j = 0; j < M; j++) board[i][j] = board[i][M-k+j];
			}
			// 반시계
			else {
				for(int j = 0; j < M; j++) board[i][j] = board[i][k+j];
			}
		}
	}
	
	public static void check() {
		total = 0;
		cnt = 0;
		isAdjacent = false;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				// 일단 0이 아니면 모두 더하고 카운트
				if(board[i][j] != 0) {
					total += board[i][j];
					cnt++;
					// 같은 원판내 인접
					if(Math.abs(board[i][j]) == board[i][j+1] || Math.abs(board[i][j]) == -board[i][j+1]) {
						int num = -Math.abs(board[i][j]);
						board[i][j] = num;
						board[i][j+1] = num;
						if(j == M-1) board[i][0] = num;
						isAdjacent = true; // 인접하는 수 있다고 표시
					}
					// 다른 원판과 인접
					if(Math.abs(board[i][j]) == board[i-1][j] || Math.abs(board[i][j]) == -board[i-1][j]) {
						int num = -Math.abs(board[i][j]);
						board[i][j] = num;
						board[i-1][j] = num;
						isAdjacent = true;
					}
				}
			}
		}
	}
	
	// 음수로 체크된 인접한 수를 제거
	public static void delete() {
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] < 0) {
					board[i][j] = 0;
					board[i][j+M] = 0;
				}
			}
		}
	}
	
	// 평균을 구해서 업데이트
	public static void update( ) {
		int mean = total/cnt;
		boolean hasDecimalPoint = total%cnt != 0 ? true : false; // 소수점이 있는지 없는지 판별
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				// 평균보다 작거나 같으면
				if(board[i][j] != 0 && board[i][j] <= mean) {
					if(board[i][j] == mean && !hasDecimalPoint) continue; // 평균과 같으면 컨티뉴
					board[i][j] += 1;
					board[i][j+M] += 1;
					
				}
				// 평균보다 크면
				else if(board[i][j] > mean) {
					board[i][j] -= 1;
					board[i][j+M] -= 1;
				}
			}
		}
	}
	
	// 결과값 구하기
	public static int getResult() {
		int result = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				result += board[i][j];
			}
		}
		return result;
	}
	
	// 패딩채우기
	public static void fillPadding() {
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) board[i][j+M] = board[i][j];
		}
	}
}



