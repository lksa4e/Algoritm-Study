import java.io.*;
import java.util.*;

/**
 * [1211] BOJ_1799_비숍
 * 완전탐색, 백트래킹, 재귀, 2차원 배열(대각선)
 * 
 * sol)
 * 백트래킹을 연습하기에 좋은 문제, 원리를 이해하기 매우 어려웠던 문제.
 * 
 * 체스판을 흑, 백으로 나누어 각각 2번을 체크해준다.
 * 흑, 백 각 경우에 대해서는 현재 좌표가 속한 대각선이 점유됐으면(좌, 우 중 하나라도) 카운트 변수를 증가하지 않고 재귀타고, 점유되지 않았으면 카운트 변수를 증가하여 재귀를 태운다.
 * 재귀를 탈 때는 열이 경계를 넘어갈 경우 행을 증가시킨다.
 * 기저 조건인 행 경계에 도달하면 흑, 백 여부에 따라 카운트 변수를 최댓값으로 갱신한다.
 * 
 * 대각선 점유 여부는 좌, 우 대각선의 기준점을 key로 하고, 점유 여부를 value로 하는 맵으로 관리.
 * 대각선 기준점은 현재 좌표와 대각선 좌표 값의 차이가 (N-1)과 (N+1)임에서 착안함.
 * 좌로 향하는 대각선(\) 기준점은 현재 좌표에서 (N+1)만큼을 최대로 차감한 점인데, 행좌표 * (N+1)만큼을 뺀 점으로 설정함.
 * 마찬가지로 우로 향하는 대각선(/) 기준점은 현재 좌표에서 (N-1)만큼을 최대로 차감한 점인데, 행좌표 * (N-1)만큼을 뺀 점으로 설정함.
 * 
 * 시행착오)
 * 1. 처음엔 그리디처럼 모든 좌표에 대해 대각선에 위치한 비숍에 영향을 주는 개수를 세 그 수가 적은 좌표를 우선적으로 포함하는 방식으로 풀어 틀림.
 *    그런데 왜 그리디로 풀면 안되는지는 잘 모르겠다.. 아마 그리디로 풀었을 때 미처 포함하지 못하는 좌표가 있을 것 같다.
 * 2. 모든 가능한 좌표에 대해 비숍처리 했다, 안했다 하는 방식으로 풀었으나 2^(NxN)의 시간 복잡도로 인해 시간초과 뜸.
 * 3. 현재 좌표를 포함하는 대각선이 점유되었는지 여부로 풀었는데도 시간초과.. 대각선 + 흑백위치로 해결함.
 *    이 풀이에서도 몇번의 시행착오가 있었는데...
 *    - 현재 좌표에 대해 비숍 처리를 한 뒤 재귀 타고 다음으로 넘어갈 때 다시 (0,0) 위치부터 탐색하다보니 여전히 시간초과가 떴다.
 *      (0,0) 부터가 아니라 현재 좌표 이후부터 탐색했어야 했다.
 *      백트래킹이라는 것이 현재 도달하기까지는 문제가 없었다는 의미이고, 재귀를 타고 넘어갔을 때 문제가 생기면 더 확인할 필요가 없어 되돌아가는 것이기때문에
 *      처음부터 확인하면 안되는 것이었는데 아직까지 백트래킹 구현에 익숙치 않아 틀린 것 같다...
 *    - 백트래킹 부분에서 열 경계체크 먼저하고 행 경계체크를 해야 기저조건에 도달할 수 있는데(열 경계체크에서 열이 경계를 넘어가면 행++ 해주므로)
 *      반대로 했다가 기저조건에 도달하지 않아 계속 답이 0이 됐었음.
 *      
 */

public class BOJ_1799_비숍 {
	static int N, blackScore, whiteScore;
	static int[][] map;
	static Map<Integer, Boolean> left = new HashMap<Integer, Boolean>();       // 좌로 향하는 대각선 점유 여부
	static Map<Integer, Boolean> right = new HashMap<Integer, Boolean>();      // 우로 향하는 대각선 점유 여부
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 입력
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 좌, 우 대각선 점유 여부 초기화
		for (int i=(-1)*(N-1); i<N; i++) left.put(i, false);              // 좌로 향하는 대각선
		for (int range=N+(N-1), i=0; i<range; i++) right.put(i, false);   // 우로 향하는 대각선
		
		playBishop(0, 0, true, 0);     // 흑
		playBishop(0, 1, false, 0);    // 백
		System.out.println(blackScore + whiteScore);
	}

	// 흑, 백 체스판 각각의 비숍 완탐
	private static void playBishop(int x, int y, boolean isBlack, int cnt) {
		// 열경계 체크
		if (y >= N) {
			x++;                      // 열 경계 넘어가면 행을 증가
			y = y % 2 == 0 ? 1 : 0;   // 이전 열 인덱스가 짝수이면 홀수로, 홀수이면 짝수로
		}
		
		// 기저조건 : 행경계 체크
		if (x >= N) {
			// 카운트 최댓값으로 갱신
			if (isBlack) blackScore = Math.max(blackScore, cnt);
			else whiteScore = Math.max(whiteScore, cnt);
			return;
		}
		
		// 유도파트 : 현재 좌표에 대해 비숍을 처리했다, 안했다 하며 카운트 세기
		int curPoint = x*N+y;
		int l = curPoint - x * (N+1);     // 현재 좌표가 포함된 대각선 기준점(좌)
		int r = curPoint - x * (N-1);     // 현재 좌표가 포함된 대각선 기준점(우)
		
		// 가능한 좌표이며 좌, 우 대각선이 점유되지 않았으면
		if (map[x][y]==1 && !left.get(l) && !right.get(r)) {
			left.put(l, true);                     // 대각선 점유 표시하고 
			right.put(r, true);
			playBishop(x, y+2, isBlack, cnt+1);    // 카운트 증가하여 재귀탐
			left.put(l, false);                    // 대각선 점유 해제하고
			right.put(r, false);
		}
		playBishop(x, y+2, isBlack, cnt);          // 카운트 증가하지 않은 채 재귀탐
	}
}
