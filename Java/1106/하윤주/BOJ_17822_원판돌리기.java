import java.io.*;
import java.util.*;

/**
 * [1106] BOJ 17822 원판돌리기
 * 시뮬레이션, 2차원 배열, 구현
 * 
 * sol)
 * 원판을 2차원 배열로 생각하고, 인접한 수들을 한번에 지우는 것이 포인트
 *
 */

public class BOJ_17822_원판돌리기 {
	static final int IDX = 0;                // x
	static final int DIR = 1;                // d
	static final int RANGE = 2;              // k
	
	static final int CLOCK = 0;              // 시계 방향
	static final int COUNTER_CLOCK = 1;      // 반시계 방향
	
	static int N, M, T;
	static int[][] map;                      // 행 : 원판 번호, 열 : 원판에 적힌 숫자
	static int[][] commands;
	static int[] tempRow;
	static Queue<Integer> nearPoints = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		// 초기 원판 상태
		map = new int[N+1][M];
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 회전 명령
		commands = new int[T][3];
		for (int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) commands[i][j] = Integer.parseInt(st.nextToken());
		}
		
		tempRow = new int[M];
		rotateCircle();
		System.out.println(calcSum());
	}

	// 명령 한줄씩 읽으며 원판 돌림
	private static void rotateCircle() {
		for (int[] command : commands) {
			int row = command[IDX];          // 행 : 원판 번호
			int col = command[RANGE];        // 열 : 원판에 적힌 숫자
			int newStart = 0;
			
			// 원판을 시계방향, 반시계방향에 따라 돌렸을 때 원판 내 0번 인덱스 숫자가 새롭게 위치할 인덱스 찾음
			if (command[DIR]==CLOCK) newStart = command[RANGE] % M;
			else newStart = (M - command[RANGE]) % M;
			
			// x의 배수인 원판들을 새로운 위치를 기준으로 회전
			for (int size=N/row, i=1; i<=size; i++) rotate(newStart, row*i, col);
			
			// 인접하면서 수가 같은 것을 찾은 뒤 지우거나 평균 구하기
			if(!findNearNums()) updateByAverage(calcAverage());
		}
	}
	
	// x의 배수인 원판들 회전
	private static void rotate(int newStart, int row, int k) {
		// 임시 배열에 저장해둠
		// 새로운 시작점부터 끝까지 채우고
		int size = M-newStart;
		for (int i=0; i<size; i++) {
			tempRow[newStart++] = map[row][i];
		}
		// 잘리고 남은 나머지 숫자들은 0번부터 새로운 시작점 직전까지 채움
		newStart = 0;
		for (int i=size; i<M; i++) {
			tempRow[newStart++] = map[row][i];
		}
		// 임시배열에 저장된 값을 원본 배열에 반영
		for (int i=0; i<M; i++) map[row][i] = tempRow[i];
	}
	
	// 인접한 수가 있는지 찾음
	private static boolean findNearNums() {
		nearPoints.clear();
		boolean flag = false;
		if(findByRow()) flag = true;
		if(findByCol()) flag = true;
		if (flag) removeNears();
		return flag;                // 인접한 수가 있어서 원판에서 지웠다면 true, 아니면 false
	}
	
	// 행 기준 인접한 수 찾기
	private static boolean findByRow() {
		boolean flag = false;
		for (int i=0; i<M; i++) {
			for (int j=1; j<N; j++) {
				if (map[j][i]==0) continue;
				if (map[j][i] == map[j+1][i]) {
					nearPoints.offer(j);          // 인접한 수들은 nearPoints 큐에 저장한 뒤 한번에 지움
					nearPoints.offer(i);
					nearPoints.offer(j+1);
					nearPoints.offer(i);
					flag = true;
				}
			}
		}
		return flag;
	}

	// 열 기준 인접한 수 찾기
	private static boolean findByCol() {
		boolean flag = false;
		for (int i=1; i<=N; i++) {
			for (int j=0; j<M-1; j++) {
				if (map[i][j]==0) continue;
				if (map[i][j] == map[i][j+1]) {
					nearPoints.offer(i);          // 인접한 수들은 nearPoints 큐에 저장한 뒤 한번에 지움
					nearPoints.offer(j);
					nearPoints.offer(i);
					nearPoints.offer(j+1);
					flag = true;
				}
			}
			if (map[i][0]==0) continue;
			if (map[i][0] == map[i][M-1]) {
				nearPoints.offer(i);
				nearPoints.offer(0);
				nearPoints.offer(i);
				nearPoints.offer(M-1);
				flag = true;
			}
		}
		return flag;
	}
	
	// 인접한 수 지우기
	private static void removeNears() {
        // nearPoints 큐에 저장된 수들을 원판에서 모두 지움
		for (int size=nearPoints.size()/2, i=0; i<size; i++) {
			int x = nearPoints.poll();
			int y = nearPoints.poll();
			map[x][y] = 0;
		}
	}

	// 평균 구하기
	private static double calcAverage() {
		double avg = 0;
		int cnt = 0;
		for (int i=1; i<=N; i++) {
			for (int j=0; j<M; j++) {
				avg += map[i][j];
				if (map[i][j]!=0) cnt++;
			}
		}
		avg /= cnt;
		return avg;
	}
	
	// 평균을 기준으로 큰지 작은지 구하고 수 업데이트
	private static void updateByAverage(double avg) {
		for (int i=1; i<=N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==0) continue;
				if (map[i][j] > avg) map[i][j]--;
				else if (map[i][j] < avg) map[i][j]++;
			}
		}
	}
	
	// 최종적으로 원판에 적힌 수들의 합 구함
	private static int calcSum() {
		int total = 0;
		for (int i=1; i<=N; i++) {
			for (int j=0; j<M; j++) total += map[i][j];
		}
		return total;
	}

}
