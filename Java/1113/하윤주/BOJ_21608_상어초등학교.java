import java.io.*;
import java.util.*;

/**
 * [1109] BOJ 21608 상어 초등학교
 * 구현
 * 
 * sol) 모든 입력 학생에 대해 (0, 0) ~ (N, N) 자리에 적합한지 확인하여 빈 자리에 앉힌다. 이후 만족도를 계산한다.
 * 시행착오) 인접한 최애가 없을 경우를 고려하지 못해 학생들이 모두 앉지 못한 상황이 발생함. 최애가 없는 경우를 고려하기 위해 변수를 추가함.
 */

public class BOJ_21608_상어초등학교 {
	static int N, N2, favCnt, emptyCnt, selectedX, selectedY, totalSatisfy;
	static int[][] map;
	static boolean[][] favorite;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		N2 = N*N;        // 전체 학생 수
		
		map = new int[N][N];                     // 앉은 상태
		favorite = new boolean[N2+1][N2+1];      // 각 학생 별 최애
		
		// 학생들 순서대로 앉힘
		for (int i=0; i<N2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int std = Integer.parseInt(st.nextToken());
			for (int j=0; j<4; j++) favorite[std][Integer.parseInt(st.nextToken())] = true;
			findSeat(std);
		}
		// 만족도 조사
		calcSatisfy();
		System.out.println(totalSatisfy);
	}

	// 각 학생 별 앉을 자리 찾기
	private static void findSeat(int std) {
		int favX, favY;      // 최애 없을 경우 가장 왼쪽 상단 좌표부터 앉히기 위해 변수 추가
		selectedX = selectedY = favX = favY = -1;
		favCnt = 0;
		emptyCnt = 0;
		// (0, 0) ~ (N, N) 자리 확인
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]!=0) continue;
				if (favX==-1) {      // 빈 자리 중 가장 왼쪽 상단을 우선 기억해두고
					favX = i;
					favY = j;
				}
				findNearFavorites(i, j, std);      // 인접한 자리 조건에 따라 앉을지 결정
			}
		}
		// 인접한 최애 없었다면 가장 왼쪽 상단 빈자리에 앉힘
		if (selectedX == -1) {
			selectedX = favX;
			selectedY = favY;
		}
		map[selectedX][selectedY] = std;
	}

	// 인접한 최애 찾기
	private static void findNearFavorites(int x, int y, int std) {
		int curFav = 0;
		int curEmpty = 0;
		
		// 인접한 4방 탐색
		for (int i=0; i<4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if (!isInside(nx, ny)) continue;     // 경계 체크
			
			// 인접한 학생이 없으면 빈자리로 체크
			int num = map[nx][ny];
			if (num == 0) {
				curEmpty++;
				continue;
			}
			// 인접한 학생이 최애면 최애 증가
			if (favorite[std][num]) curFav++;
		}
		
		// 인접한 최애가 가장 많거나, 인접한 최애 수가 똑같은데 빈자리가 더 많으면 이 자리로 갱신
		if (curFav > favCnt || (curFav == favCnt && curEmpty > emptyCnt)) {
			selectedX = x;
			selectedY = y;
			favCnt = curFav;
			emptyCnt = curEmpty;
		}
	}
	
	// 만족도 조사
	private static void calcSatisfy() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				int std = map[i][j];
				int curFav = 0;
				
				// 인접한 4방 탐색으로 최애 수 카운트
				for (int d=0; d<4; d++) {
					int nx = dx[d] + i;
					int ny = dy[d] + j;
					if (!isInside(nx, ny)) continue;
					if (favorite[std][map[nx][ny]]) curFav++;     // 최애 찾음
				}
				// 만족도 계산
				calcWeight(curFav);
			}
		}
	}
	
	// 최애 수에 따른 만족도 계산
	private static void calcWeight(int fav) {
		switch (fav) {
		case 0: break;
		case 1: totalSatisfy++; break;
		case 2: totalSatisfy+=10; break;
		case 3: totalSatisfy+=100; break;
		case 4: totalSatisfy+=1000; break;
		}
	}
	
	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}

}
