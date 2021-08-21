import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 14502번 연구소
 * 
 * 이차원 배열의 맵이 주어진다.
 * 맵에는 빈 칸 0, 벽 1, 바이러스 2가 주어진다.
 * 바이러스는 빈 칸을 타고 상하좌우로 계속해서 퍼질 수 있다.
 * 빈 칸에 3개의 벽을 세워서 바이러스를 최소한으로 퍼지게 한다.
 * 
 * 해결 방법:
 * 벽 3개를 어떻게 하면 그리디하게 놓을지 고민했지만 잘 모르겠음
 * 조합 문제로 보고 64칸 중에 3칸을 놓는 문제로 봄
 * 벽 3개를 놓고 그 상태에서 BFS로 virus를 퍼뜨림
 * BFS로 퍼질 때, 바이러스를 카운트함
 * 빈 칸의 갯수에서 최소로 퍼진 바이러스만큼을 빼서 답을 구함
 * 
 * bfs에서 만약 세고 있는 바이러스의 수가 minVirus보다 커지면 가지치기 가능할듯
 * 백트래킹 전 : 21624KB  288ms
 * 백트래킹 후 : 21780KB  284ms
 * ..?
 */

public class BOJ_14502 {
	// 안전한 공간이 아니라 바이러스가 퍼진 공간의 min을 구해서 빼준다.
	static int R, C, minVirus = Integer.MAX_VALUE;
	static int[][] map, cpMap;
	// 바이러스의 좌표 저장
	static List<int[]> virus = new ArrayList<>();
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());

		int safeSpaceCnt = 0;
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());

		map = new int[R][C];
		cpMap = new int[R][C];

		for (int r = 0; r < R; r++) {
			tk = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(tk.nextToken());
				if (map[r][c] == 2) {
					int[] tmp = { r, c };
					virus.add(tmp);
				} else if (map[r][c] == 0)
					safeSpaceCnt++;
			}
		}

		solve(0, 0);
		// 안전 공간에서 벽 3개 빼주고, 최소 퍼진바이러스 칸 수 만큼 빼주기
		safeSpaceCnt -= 3;
		System.out.println(safeSpaceCnt - minVirus);
	}

	static void solve(int start, int wall) {
		if (wall == 3) {
			// 벽 세운 뒤에 바이러스 퍼뜨리기
			// 바이러스 퍼뜨릴 맵 다시 세팅하기
			resetMap();

			// BFS로 바이러스 퍼뜨리기
			// 퍼뜨리면서 2의 갯수 가져오기
			int virusCnt = 0;
			for (int[] v : virus) {
				virusCnt += virusBfs(v[0], v[1]);
				// 백트래킹
				if(virusCnt >= minVirus)
					break;
			}
			
			minVirus = Math.min(minVirus, virusCnt);
			return;
		}

		// 2차원 배열을 1열로 늘여놓고 Column단위로 끊을 수 있다.
		// 1열로 늘여진 index를 Column단위로 나누면, 몫은 row의 위치가, 나머지는 col의 위치가 된다.
		for (int i = start; i < R * C; i++) {
			int row = i / C;
			int col = i % C;

			if (map[row][col] == 0) {
				// 벽 세우기
				map[row][col] = 1;
				solve(i + 1, wall + 1);
				// 세운 벽 다시 허물기
				map[row][col] = 0;
			}
		}
	}
	
	static int virusBfs(int row, int col) {
		// 몇 칸 감염 시켰는지 카운트
		int cnt = 0;
		// 사방 탐색으로 바이러스가 뻗을 곳 찾기
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			
			// 맵 범위 체크 + 빈 칸인지 확인
			if (nr >= 0 && nr < R && nc >= 0 && nc < C && cpMap[nr][nc] == 0) {
				cnt++;
				cpMap[nr][nc] = 2;
				// 다음 노드가 감염시킨 갯수 받아오기
				cnt += virusBfs(nr, nc);
			}
			
			// cnt가 minVirus보다 커질 경우 백트래킹
			if(cnt >= minVirus)
				return cnt;
		}
		return cnt;
	}
	
	static void resetMap() {
		for (int r = 0; r < R; r++)
			for (int c = 0; c < C; c++)
				cpMap[r][c] = map[r][c];
	}

}
