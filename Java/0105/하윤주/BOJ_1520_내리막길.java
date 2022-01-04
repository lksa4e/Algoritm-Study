import java.io.*;
import java.util.*;

/**
 * [0105] BOJ 1520 내리막길
 * DP, 재귀, Top-Down
 * 
 * sol)
 * 도착 좌표인(M-1, N-1)부터 시작 좌표인(0, 0)까지 거슬러 올라간다.
 * 
 * DP 점화식을 생각해보면,
 * 현재 좌표로 도달할 수 있는 방법은 상, 하, 좌, 우 각각으로 도달할 수 있는 경우의 수를 모두 더한 것
 *    DP[i][j] = dp[i-1][j] + dp[i][j-1] + dp[i+1][j] + dp[i][j+1]
 *    
 * DP를 Top-Down방식인 재귀 함수로 구현한다면 현재 좌표를 기준으로 상, 하, 좌, 우 각각으로 도달하는 경우의 수 역시 재귀함수를 타고 가서 구할 수 있음
 * 재귀함수를 끝까지 타고올라가다가 시작 좌표(0, 0)을 만나면 시작점에서의 경우의 수는 1가지이므로 1을 반환하여 재귀 호출을 종료한다.
 * 
 * 시행착오)
 * 시작 좌표에서 종료 좌표까지 반복문으로 도달하는 Bottom-Up 방식을 도전해보려고 했으나,
 * 4방의 인접한 좌표 중 아직 방문한 적이 없어 경우의 수를 알 수 없는 좌표를 다음에 다시 방문하도록 구현하는 데 실패해서 포기했습니다...
 * 
 */

public class BOJ_1520_내리막길 {
	static int M, N;
	static int[][] map, dp;             // 원본 지도(경사 높이), 경우의 수를 저장한 DP 테이블
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 원본 지도 초기화
		map = new int[M][N];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// DP 초기화 - 방문 체크를 위해 -1로 초기화
		dp = new int[M][N];
		for (int i=0; i<M; i++) Arrays.fill(dp[i], -1);
		
		// 종료 좌표부터 재귀함수를 호출하여 시작 좌표까지 거슬러 올라가 경우의 수 구함
		System.out.println(findRoad(M-1, N-1));
	}

	// 재귀함수
	private static int findRoad(int x, int y) {
		// 기저 조건 : 시작 좌표인 (0, 0)을 만나면 경우의 수 1개를 반환
		if (x==0 && y==0) return 1;
		
		// 유도 파트 : 종료 좌표부터 시작 좌표까지 인접한 좌표 경우의 수를 더하여 현재 경우의 수 구함
		// 만약 거슬러 올라가는 과정에서의 좌표가 이미 방문한 적이 있어 경우의 수를 알고 있다면 이를 반환
		if (dp[x][y] != -1) return dp[x][y];
		else dp[x][y] = 0;           // 최초로 방문하는 경우라면 경우의 수를 0으로 초기화하고 인접한 4방 탐색 시도
		
		// 인접한 4방 탐색 시도
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 경계 밖이거나 인접한 좌표가 더 낮으면 pass
			if (!isInside(nx, ny) || map[nx][ny]<=map[x][y]) continue;
			// 가능한 4방 인접 좌표의 경우의 수를 모두 더한 것이 현재 좌표 경우의 수
			dp[x][y] += findRoad(nx, ny);
		}
		return dp[x][y];
	}
	
	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<M && y>=0 && y<N);
	}

}
