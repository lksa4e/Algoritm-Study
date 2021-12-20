import java.io.*;

/**
 * [1221] BOJ 10026 적록색약
 * 그래프 탐색, dfs, bfs
 * 
 * sol)
 * 착한 dfs, bfs문제
 * 적록색약을 위한 visited 배열과 적록색약이 아닌 사람을 위한 visited 배열을 따로 만들어
 * 각 좌표 별 dfs 탐색을 수행한 뒤 수행한 횟수를 출력한다.
 * 이때 적록색약 dfs 탐색은 'R'과 'G'를 동일한 문자로 취급한다.
 *
 */

public class BOJ_10026_적록색약 {
	static int N, sectionCnt, redGreenSectionCnt;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 지도 입력
		map = new char[N][N];
		for (int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 각 좌표 별 dfs 탐색하며 구역 나누기
		findSections();
		// 출력
		System.out.println(sectionCnt + " " + redGreenSectionCnt);
	}

	// 각 좌표 별 dfs 탐색 시도하며 구역 나누기
	private static void findSections() {
		boolean[][] visited = new boolean[N][N];             // 적록색약 아닌 경우 visit 체크
		boolean[][] redGreenVisited = new boolean[N][N];     // 적록색약인 경우 visit 체크
		
		// 각 좌표에 대해
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				char color = map[i][j];
				
				// 적록색약 아닌 경우 아직 방문 안했으면 방문하고 구역 개수 증가
				if (!visited[i][j]) {
					visited = dfs(i, j, color, visited);
					sectionCnt++;
				}
				// 적록색약인 경우 아직 방문 안했으면 방문하고 구역 개수 증가
				if (!redGreenVisited[i][j]) {
					// 'R', 'G' 색상 좌표의 dfs 탐색은 따로 실시
					if (color == 'R' || color == 'G') redGreenVisited = redGreenDfs(i, j, redGreenVisited);
					else redGreenVisited = dfs(i, j, color, redGreenVisited);
					redGreenSectionCnt++;
				}
			}
		}
	}
	
	// 모든 좌표 dfs 탐색
	private static boolean[][] dfs(int x, int y, char color, boolean[][] visited) {
		visited[x][y] = true;
		
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (isInside(nx, ny) && !visited[nx][ny] && map[nx][ny]==color) dfs(nx, ny, color, visited);
		}
		
		return visited;
	}

	// 빨강, 초록 좌표 dfs 탐색
	private static boolean[][]  redGreenDfs(int x, int y, boolean[][] redGreenVisited) {
		redGreenVisited[x][y] = true;
		
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 'R'과 'G'는 동일한 색상으로 취급
			if (isInside(nx, ny) && !redGreenVisited[nx][ny] && (map[nx][ny]=='R' || map[nx][ny]=='G')) redGreenDfs(nx, ny, redGreenVisited);
		}
		
		return redGreenVisited;
	}
	
	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}

}
