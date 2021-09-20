import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0925] BOJ 17070 파이프 옮기기 1
 *  DP, 2차원 배열
 * 
 * sol)
 * 동적 테이블에 직전 좌표값을 저장해두고 우, 하, 대각선 방향에 따라 직전 좌표 방향의 경우의 수를 더하여 현재 방향의 경우의 수 계산
 * 점화식은 다음과 같다.
 * 
 * - 우방향
 * dp[i][j][0] : (i, j)좌표에 우방향으로 도착하기 위한 경우의 수
 * {	dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];      단, (i, j-1)이 경계 안이며, (i, j)가 벽이 아닐 경우
 * 		dp[0][1][0] = 1;								  초기 파이프 배치 경우의 수
 * }
 * 
 * - 하방향
 * dp[i][j][2] : (i, j)좌표에 하방향으로 도착하기 위한 경우의 수
 * {	dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];      단, (i-1, j)이 경계 안이며, (i, j)가 벽이 아닐 경우	
 * 		dp[0][1][0] = 1;								  초기 파이프 배치 경우의 수
 * }
 * 
 * - 대각선
 * dp[i][j][1] : (i, j)좌표에 대각선으로 도착하기 위한 경우의 수
 * {	dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];      단, (i-1, j-1)이 경계 안이며, (i, j),(i, j-1),(i-1, j)가 벽이 아닐 경우		
 * 		dp[0][1][0] = 1;														초기 파이프 배치 경우의 수
 * }
 * 
 * 
 * 시행착오)
 * 처음엔 dfs로 풀었는데 알고리즘 분류에 dp로 되어있길래 dfs 풀고 나서 도전해봤습니다.
 * 확실히 dp가 성능이 좋긴 하지만, 저는 dfs가 더 쉬웠던 것 같습니다!
 * 
 */

public class BOJ_17070_파이프옮기기1_DP {
	static int N, cnt;
	static boolean rightFlag, downFlag;       // 우, 하방향 벽 체크
	static int[][] map;                       // 초기 지도 배열
	static int[][][] dp;                      // dp과정을 위한 동적 테이블
	static int[] dx = {0, 1, 1};              // 우, 대각선, 하 
	static int[] dy = {1, 1, 0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		// 초기 지도 입력
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// dp
		fillDPTable();
		for (int n : dp[N-1][N-1]) cnt += n;     // 최종 좌표(N-1, N-1)의 우, 하 대각선 방향 경우의 수를 모두 더한 것이 최종 답
		System.out.println(cnt);
		
	}

	// dp로 경우의 수 찾기
	private static void fillDPTable() {
		dp = new int[N][N][3];
		dp[0][1][0] = 1;                    // 파이프 초기 위치(0, 1)는 (0, 0)에서 우방향으로 온 경우의 수이므로 이를 초기화
		
		for (int i=0; i<N; i++) {
			for (int j=2; j<N; j++) {		// (0, 1)부터 초기화됐으므로 dp는 (0, 2)부터 시작
				rightFlag = false;          // 대각선 이동 시 우방향, 하방향 빈 공간 조건 확인을 위한 플래그
				downFlag = false;
				
				if (!isPossibleMove(i, j)) continue;                  // 도착하려는 곳이 벽이면 무조건 pass
				
				if (isPossibleMove(i, j-1)) {		                  // 우방향 이동을 위한 조건 체크
					rightFlag = true;								  // 우방향 이동 가능하다면 이를 기억
					dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				}
				
				if (isPossibleMove(i-1, j)) {		                  // 하방향 이동을 위한 조건 체크
					downFlag = true;								  // 하방향 이동 가능하다면 이를 기억
					dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
				}
				
				if (rightFlag && downFlag && isPossibleMove(i-1, j-1)) {   // 우방향, 하방향 모두 이동 가능하며 대각선 이동을 위한 조건 체크도 충족하면 대각선 이동
					dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
	}
	
	// 경계 및 벽 체크
	private static boolean isPossibleMove(int x, int y) {
		if (x>=0 && x<N && y>=0 && y<N && map[x][y]==0) return true;
		return false;
	}
}
