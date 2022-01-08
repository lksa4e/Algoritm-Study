import java.io.*;
import java.util.*;

/**
 * [0108] BOJ 1937 욕심쟁이 판다
 * DP, DFS, 재귀, Top-Down
 * 
 * sol)
 * nxn 모든 좌표에 대해 DP 값이 갱신되지 않은 상태이면 탐색을 시도한다.
 * 상, 하, 좌, 우 중 자신보다 대나무 개수가 적은 좌표는 탐색할 수 있다.
 * 인접 좌표 중 탐색 가능한 좌표일 때 함수를 재귀적으로 호출하여 해당 인접 좌표에 저장된 dp값을 구하고,
 * (인접 좌표 dp값 + 1) 한 값이 자신의 dp 값이 된다.(해당 인접좌표까지 깊이에 자기 자신을 더함)
 * 재귀적으로 더이상 타고 들어갈 수 없는 경우(기저조건)는 자기 자신이 인접한 4방과 비교했을 때 대나무 개수가 가장 적을 때이고, 이때는 dp 값을 1로 설정한다.
 *
 */

public class BOJ_1937_욕심쟁이판다 {
	static int n, maxBamboo;
	static int[][] map;
	static int[][] dp;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		// 초기 지도 설정 및 DP 테이블 초기화
		map = new int[n][n];
		dp = new int[n][n];
		for (int i=0; i<n; i++) {
			Arrays.fill(dp[i], -1);     // 방문여부 체크를 위해 -1로 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		// DP 테이블 만들며 최댓값 찾고 출력
		makeDPTable();
		System.out.println(maxBamboo);
	}

	// 모든 좌표에 대해 DP 테이블 구성
	private static void makeDPTable() {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				// 아직 방문하지 않아 DP 테이블이 빈 경우만 DP 테이블을 채움
				if (dp[i][j]==-1) {
					countBamboo(i, j);
					maxBamboo = Math.max(maxBamboo, dp[i][j]);    // 최댓값 갱신
				}
			}
		}
	}

	// 현재 좌표의 DP 값 구하기
	private static int countBamboo(int i, int j) {
		// 이미 방문한 적이 있어 DP 테이블이 채워져있으면 해당 값 + 1을 반환
		if (dp[i][j]!=-1) return dp[i][j] + 1;
		
		// 방문한 적 없으면 4방 탐색으로 DP 테이블 채움
		dp[i][j] = 0;
		for (int d=0; d<4; d++) {
			int nx = i + dx[d];
			int ny = j + dy[d];
			// 경계 내부이고 자신보다 적은 개수의 대나무가 있는 경우만 탐색
			if (isInside(nx, ny) && map[nx][ny]<map[i][j]) dp[i][j] = Math.max(dp[i][j], countBamboo(nx, ny));
		}
		// 4방 탐색 이후에도 DP 테이블이 갱신되지 않은 상태, 즉 4방과 비교했을 때 자기 자신의 대나무가 가장 적으면 1칸(자기자신)으로 설정
		if (dp[i][j]==0) dp[i][j] = 1;
		
		return dp[i][j] + 1;    // 인접한 좌표까지의 DP 값 + 1(자기자신) 반환
	}
	
	// 경계체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<n && y>=0 && y<n);
	}

}
