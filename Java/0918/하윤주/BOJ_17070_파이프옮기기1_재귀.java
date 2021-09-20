import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0925] BOJ 17070 파이프 옮기기 1
 *  완전탐색, dfs, 2차원 배열
 * 
 * sol)
 * 시작 좌표부터 우, 하, 대각선 방향을 모두 탐색하며 종료 좌표에 도착하면 경우의 수를 증가시킨다.
 * - 기저조건
 * (N, N) 좌표에 도달했을 때 해당 좌표 역시 벽이 아니면 경우의 수 증가
 * - 유도파트
 * 우방향 이동 : 우방향으로 도착한 곳이 벽이 아니고 경계 안이면 이동
 * 하방향 이동 : 하방향으로 도착한 곳이 벽이 아니고 경계 안이면 이동
 * 대각선 이동 : 우방향, 하방향, 대각선으로 도착한 곳이 벽에 아니고 경계 안이면 이동
 * 
 * 시행착오)
 * 도착 좌표 역시 벽인지 체크해주는 부분이 빼먹기 쉬웠다.
 * dfs 매개변수로 파이프의 좌표를 저장하는데 배열로 저장했더니 메모리도 많이 쓰고 시간도 훨씬 오래 걸렸다. 
 * 길이가 고정되었다면 그냥 기본형 변수를 쓰는 것이 베스트인 것 같다.
 * 
 */

public class BOJ_17070_파이프옮기기1_재귀 {
	static int N, cnt;
	static int[][] map;             // 초기 지도
	static int[] dx = {0, 1, 1};    // 우, 하, 대각선
	static int[] dy = {1, 0, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];						// 초기 지도 배열
		for (int[] row : map) Arrays.fill(row, 1);      // 경계 체크를 위한 패딩 처리
		
		// 초기 지도 입력
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 완전 탐색
		dfs(1, 1, 1, 2);
		System.out.println(cnt);
		
	}

	// dfs로 지도를 완전 탐색하며 파이프 설치 가능한 경우의 수 카운트
	private static void dfs(int x1, int y1, int x2, int y2) {
		// 기저조건 : (N, N)좌표에 도착
		if (x2==N && y2==N && map[x2][y2]==0) {     // 최종 도착지가 벽이 아니면 경로 완성
			cnt++;
			return;
		}
		
		// 유도파트 : 우, 하, 대각선 방향으로 지도 탐색
		for (int i=0; i<3; i++) {
			if (y2>y1 && x2==x1 && i==1) continue;  // 가로 배치는 하 방향 이동 제한
			if (x2>x1 && y2==y1 && i==0) continue;  // 세로 배치는 우 방향 이동 제한
			
			// 이동할 좌표
			int nx = x2 + dx[i];
			int ny = y2 + dy[i];
			
			if (map[nx][ny] == 1) continue;        							  // 경계 및 벽 체크
			if (i==2 && (map[nx-1][ny]==1 || map[nx][ny-1]==1)) continue;     // 대각선 방향 이동은 우 방향과 하 방향이 모두 1이 아니어야함
			
			// 위 조건을 모두 만족하면 다음 좌표로 파이프 설치
			dfs(x2, y2, nx, ny);
		}
	}
	
}
