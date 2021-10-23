import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 17142번 연구소3
 *
 * 조합, BFS
 *
 * 비활성 바이러스의 위치를 list로 저장 후, 조합으로 활성화 시킬 바이러스를 고른다.
 * 고른 바이러스부터 BFS로 바이러스를 퍼뜨리며 연구소에 바이러스가 꽉 찰 때 까지
 * 얼마나 걸리는지 측정한다.
 * 조합 별로 다 해보고 가장 최솟값을 출력한다.
 *
 * 20104KB	300ms
 */

public class BOJ_17142 {
	static final int dr[] = {-1,1,0,0};
	static final int dc[] = {0,0,-1,1};

	static int N, M, map[][], selected[], virusCnt, min = Integer.MAX_VALUE, cntEmpty;
	static List<int[]> virusLoc;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		virusLoc = new ArrayList<int[]>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virusLoc.add(new int[]{i,j});
				} else if(map[i][j] == 0) {
					cntEmpty++;
				}
			}
		}

		virusCnt = virusLoc.size();

		selected = new int[M];
		combination(0,0);
		if(min == Integer.MAX_VALUE) min = -1;

		System.out.println(min);
	}

	static void combination(int cnt, int start) {
		if(cnt == M) {
			// simulation
			if(BFS() == 3) {
				BFS();
			}
			min = Math.min(min, BFS());
			return;
		}

		for (int i = start; i < virusCnt; i++) {
			selected[cnt] = i;
			combination(cnt+1, i+1);
		}
	}

	static void printMap(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int BFS() {
		int cnt = 0, cntSpread = 0;
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[][] visited = new boolean[N][N];

		// select된 부분 큐에 넣기
		for (int i = 0; i < M; i++) {
			int[] loc = virusLoc.get(selected[i]);
			q.add(loc[0]);
			q.add(loc[1]);
			visited[loc[0]][loc[1]] = true;
		}

		if(cntSpread == cntEmpty) return 0;

		while(!q.isEmpty()) {
			int size = q.size() / 2;

			for (int i = 0; i < size; i++) {
				int r = q.poll();
				int c = q.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nextR = r + dr[dir];
					int nextC = c + dc[dir];
					if(isOutOfMap(nextR, nextC)) continue;
					if(visited[nextR][nextC]) continue;
					if(map[nextR][nextC] == 1) continue;
					if(map[nextR][nextC] == 0) cntSpread++;
					visited[nextR][nextC] = true;
					q.offer(nextR);
					q.offer(nextC);
				}
			}

			cnt++;
			if(cntSpread == cntEmpty) break;
		}

		return cntSpread == cntEmpty ? cnt : Integer.MAX_VALUE;
	}

	static boolean isOutOfMap(int r, int c) {
		return r < 0 || c < 0 || r >= N || c >= N;
	}

}
