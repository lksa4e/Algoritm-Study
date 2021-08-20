import java.io.*;
import java.util.*;

public class Main14502 {
	/*
	 * 순열로 벽 3개 세우고, 바이러스 퍼뜨린 후, 안전영역 카운트
	 * 
	 * - 시행착오
	 * 처음에는 벽 3개 세울 때마다 바이러스 리스트에 있는것을 큐에 집어넣고 다시 큐에서 하나씩 빼면서 bfs했었는데
	 * 메모리 300000kb 나옴ㄷㄷ 시간은 976ms
	 * 그래서 큐 뺐더니 23436kb 488ms로 확 줄게 되었다..
	 */
	static int N, M, ans;
	static int[][] map, cmap;
	static List<Virus> virusList;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cmap = new int[N][M];
		virusList = new ArrayList<Virus>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) virusList.add(new Virus(i, j));
			}
		}
		ans = 0;
		makeWall(0);
		System.out.println(ans);

	}

	static void makeWall(int cnt) {
		if (cnt == 3) { // 벽이 3개 됐으면
			// 맵 복사
			copyMap();
			// 바이러스 퍼뜨리기
			for (Virus v : virusList) {
				bfs(v.x, v.y);
			}
			// 안전영역 카운트, 최댓값 구하기
			ans = Math.max(ans, cntSafeArea());
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					makeWall(cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	// 바이러스 퍼뜨리기
	static void bfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx <= -1 || nx >= N || ny <= -1 || ny >= M) continue;

			if (cmap[nx][ny] == 0) {
				cmap[nx][ny] = 2;
				bfs(nx, ny);
			}
		}
	}
	
	// 안전영역 카운트
	static int cntSafeArea() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cmap[i][j] == 0)
					++cnt;
			}
		}
		return cnt;
	}
	
	// 맵 복사
	static void copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cmap[i][j] = map[i][j];
			}
		}
	}
}

class Virus {
	int x, y;

	public Virus(int x, int y) {
		this.x = x;
		this.y = y;
	}
}