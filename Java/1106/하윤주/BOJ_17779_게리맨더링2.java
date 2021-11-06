import java.io.*;
import java.util.*;

/**
 * [1106] BOJ 17779 게리맨더링2
 * 완전탐색, 시뮬레이션, 2차원 배열, 구현
 * 
 * sol)
 * 왼쪽 상단부터 오른쪽 하단까지 기준점 (x, y)을 바꿔가며 모든 가능한 구역을 나누고 각 구역 별 인구수 모두 계산해보기
 * 미쳐버린 인덱스 관리... 머리가 팽글 팽글 돕니다ㅜ
 *
 */

public class BOJ_17779_게리맨더링2 {
	static int N, size, maxPopulation, minPopulation, answer=Integer.MAX_VALUE;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 초기 인구수
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// d의 최소 길이를 위해 N-2를 행렬의 최대 인덱스로 설정
		size = N-2;
		gerrymandering();
		System.out.println(answer);
	}

	// 왼쪽 상단부터 오른쪽 하단까지 기준점 옮겨가며 게리맨더링
	private static void gerrymandering() {
		for (int i=0; i<size; i++) {
			for (int j=1; j<=size; j++) {
				makeDistrict(i, j);
			}
		}
	}
	
	// 각 기준점을 바탕으로 구획 나누기
	private static void makeDistrict(int r, int c) {
		for (int d1=1; d1<size; d1++) {
			for (int d2=1; d2<size; d2++) {
				maxPopulation = 0;
				minPopulation = Integer.MAX_VALUE;
				
				if(!count5(r, c, d1, d2)) continue;      // 5번 선거구(경계 벗어나면 1, 2, 3, 4는 볼 것도 없으므로 pass)
				count1(r, c, d1, d2);                    // 1번 선거구
				count2(r, c, d1, d2);                    // 1번 선거구
				count3(r, c, d1, d2);                    // 1번 선거구
				count4(r, c, d1, d2);                    // 1번 선거구
				
				// 인구수 차이 최소 구하기
				answer = Math.min(answer, maxPopulation-minPopulation);
			}
		}
	}

	// 1번 선거구
	private static void count1(int x, int y, int d1, int d2) {
		int total = 0;
		int cSize = y;
		// (0, 0) ~ (x+d1, y) 안의 인구
		for (int rSize=x+d1, r=0; r<rSize; r++) {
			if (r>=x) cSize--;
			for (int c=0; c<=cSize; c++) {
				total += map[r][c];
			}
		}
		maxPopulation = Math.max(maxPopulation, total);
		minPopulation = Math.min(minPopulation, total);
	}

	// 2번 선거구
	private static void count2(int x, int y, int d1, int d2) {
		int total = 0;
		int cSize = y+1;
		// (0, y+1) ~ (x+d2, N-1) 안의 인구
		for (int rSize=x+d2, r=0; r<=rSize; r++) {
			for (int c=cSize; c<=N-1; c++) {
				total += map[r][c];
			}
			if (r>=x) cSize++;
		}
		maxPopulation = Math.max(maxPopulation, total);
		minPopulation = Math.min(minPopulation, total);
	}

	// 3번 선거구
	private static void count3(int x, int y, int d1, int d2) {
		int total = 0;
		int rSize = x+d1+d2;
		int cSize = y-d1;
		// (x+d1, 0) ~ (N, y-d1) 안의 인구
		for (int r=x+d1; r<N; r++) {
			for (int c=0; c<cSize; c++) {
				total += map[r][c];
			}
			if (r<rSize) cSize++;
		}
		maxPopulation = Math.max(maxPopulation, total);
		minPopulation = Math.min(minPopulation, total);
	}

	// 4번 선거구
	private static void count4(int x, int y, int d1, int d2) {
		int total = 0;
		int rSize = x+d1+d2;
		int cSize = y+d2;
		// (x+d2+1, y+d2) ~ (N, N) 안의 인구
		for (int r=x+d2+1; r<N; r++) {
			for (int c=cSize; c<N; c++) {
				total += map[r][c];
			}
			if (r<=rSize) cSize--;
		}
		maxPopulation = Math.max(maxPopulation, total);
		minPopulation = Math.min(minPopulation, total);
	}

	// 5번 선거구
	private static boolean count5(int x, int y, int d1, int d2) {
		// 5번 선거구는 기준점의 행을 기준으로 아래로 한 행씩 내려오면서 각 열 안에 포함된 좌표의 인구수 셈
		int total = map[x][y];
		int left = y;            // 각 열의 가장 왼쪽 포인트
		int right = y;           // 각 열의 가장 오른쪽 포인트
		
		LOOP : while(true) {
			if (d1-->0) --left;      // 경계 안쪽인 동안에는 왼쪽 포인트는 더 왼쪽으로
			else ++left;             // 경계에 부딪히면 왼쪽 포인트는 오른쪽으로
			if (d2-->0) ++right;     // 경계 안쪽인 동안에는 오른쪽 포인트는 더 오른쪽으로
			else --right;            // 경계에 부딪히면 오른쪽 포인트는 왼쪽으로
			
			// 기준점 행부터 한 행씩 아래로 내려옴
			++x;
			for (int c=left; c<=right; c++) {
				if (!isInside(x, c)) {           // 경계 체크
					total = -1;
					break LOOP;
				}
				total += map[x][c];              // 인구 증가
			}
			if (left==right) break;              // 경계 만날 때까지는 왼쪽 포인트와 오른쪽 포인트가 퍼지고 경계 만나고는 다시 수렴하므로 두 포인트가 만나면 5번 선거구 형성 종료
		}
		if (total<0) return false;
		maxPopulation = Math.max(maxPopulation, total);
		minPopulation = Math.min(minPopulation, total);
		return true;
	}

	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}

}
