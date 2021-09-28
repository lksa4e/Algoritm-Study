import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * BOJ 17472번 다리 만들기 2
 * 
 * 이차원 배열로 맵이 주어지고
 * 섬과 바다가 있고 각 섬에서 다른 섬으로 가는 다리를 놓을 때,
 * 모든 섬들을 연결하는 다리들의 최소 거리를 구하라
 * 
 * 1. 맵을 열 순서로 보면서 새로운 섬이면 dfs로 땅을 타고 가서 마킹해줌
 * 2. 각 땅에서 다른 섬으로 다리를 놓을 수 있는지, 있으면 인접 행렬에 채워줌
 *		이 때, 다리의 길이는 2 이상이어야 하고, 이미 존재하는 다리보다 짧게 갈 수 있으면 대체한다.
 * 3. 만들어진 인접 행렬로 dfs를 돌려서 모든 섬들을 연결 할 수 있는지 확인한다.
 * 4. 모든 섬을 연결할 수 있으면 prim 알고리즘을 돌려서 최단거리를 구한 뒤 출력한다.
 * 
 * 14276KB	124ms
 */

public class BOJ_17472 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, M, islandCnt, map[][];
	static int[][] adjMat;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 2][M + 2];

		// 둘레는 -2로
		for (int i = 0; i < N + 2; i++)
			Arrays.fill(map[i], -2);

		// 땅은 -1로, 바다는 0으로 마크
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++)
				map[i][j] = Integer.parseInt(st.nextToken()) * -1;
		}
		
		// 맵의 섬들에 index 붙이기
		markMap();

		// 다리 정보 인접 행렬 초기화
		adjMat = new int[islandCnt][islandCnt];
		for (int i = 0; i < islandCnt; i++)
			Arrays.fill(adjMat[i], Integer.MAX_VALUE);

		// 간선 정보 저장하기
		getIslandBridgeInfo();
		
		// 모든 섬을 돌 수 있는지 체크하고, 돌 수 있으면 prim으로 결과값을, 아니면 -1을 출력
		System.out.println(dfsCheckAllConnected()? prim() : -1);

	}
	
	// 모든 섬들이 연결되어 있는지 확인하는 메소드
	static boolean dfsCheckAllConnected() {
		// stack으로 dfs
		Stack<Integer> stk = new Stack<>();
		boolean[] visited = new boolean[islandCnt];
		stk.push(0);
		visited[0] = true;
		
		int cnt = 1;
		while(!stk.isEmpty()) {
			int curr = stk.pop();
			for (int i = 0; i < islandCnt; i++) {
				if(!visited[i] && adjMat[curr][i] != Integer.MAX_VALUE) {
					stk.push(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		
		return cnt == islandCnt ? true : false;
	}

	// 다리 인접 행렬로 MST 구하기
	static int prim() {
		int result = 0;
		boolean visited[] = new boolean[islandCnt];
		int distance[] = new int[islandCnt];

		Arrays.fill(distance, Integer.MAX_VALUE);

		// 첫번째 정점을 출발지로 선택
		distance[0] = 0;
		int cnt = 0;

		while (true) {
			int min = Integer.MAX_VALUE;
			int idx = 0;
			for (int i = 0; i < islandCnt; i++) {
				if (!visited[i] && distance[i] < min) {
					idx = i;
					min = distance[i];
				}
			}
			visited[idx] = true;
			result += min;
			cnt++;

			// 모든 정점을 처리 완료
			if (cnt == islandCnt)
				break;

			// 새로 추가한 정점에서 연결된 다른 정점의 간선 정보 업데이트
			for (int i = 0; i < islandCnt; i++)
				if (!visited[i] && adjMat[idx][i] > 0 && distance[i] > adjMat[idx][i])
					distance[i] = adjMat[idx][i];
		}
		return result;
	}

	// 다리를 놓을 수 있는지 각 땅마다 확인
	static void getIslandBridgeInfo() {
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				if (map[i][j] > 0) // 섬이면, 그 섬에서 뻗을 수 있는 다리 정보를 생성
					makeBridge(i, j);
	}

	// 선택된 땅에서 4방향으로 탐색해서 다리 놓을 수 있는지 확인하는 메소드
	static void makeBridge(int row, int col) {
		int startIsland = map[row][col];	// 현재 출발한 섬
		for (int i = 0; i < 4; i++) {
			int bridgeLen = 1;	// 놓는 다리 길이
			int nextR = row + dr[i];
			int nextC = col + dc[i];
			while (map[nextR][nextC] == 0) {	// 바다가 아니면, 다른 섬이나 벽(-2)에 닿으면 멈추기
				nextR += dr[i];
				nextC += dc[i];
				bridgeLen++;
			}
			// 도달한 곳이 -2거나 출발섬이었거나, 다리 길이가 2보다 작았으면 다리를 놓을 수 없다.
			if (map[nextR][nextC] == -2) continue;
			if (map[nextR][nextC] == startIsland) continue;
			if (--bridgeLen < 2) continue; // 다리길이 - 1 해야 출발 섬 ~ 도착 섬의 정확한 다리 길이

			int destIsland = map[nextR][nextC];	// 현재 도착한 섬

			// 원래 저장된 다리 값보다 작으면
			if (bridgeLen < adjMat[startIsland - 1][destIsland - 1]) {
				adjMat[startIsland - 1][destIsland - 1] = bridgeLen;
				adjMat[destIsland - 1][startIsland - 1] = bridgeLen;
			}
		}
	}

	// 각 섬에 이름표 붙이는 메소드
	static void markMap() {
		islandCnt = 1;	// 섬들의 index
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				if (map[i][j] == -1) // 새로운 섬을 찾으면 dfs로 마크해줌
					markDfs(i, j, islandCnt++);

		islandCnt--;	// 섬들이 몇 개 있는지 저장하는 변수로 사용
	}

	// 이어진 땅들은 하나의 섬으로 마킹해주는 메소드
	static void markDfs(int row, int col, int mark) {
		map[row][col] = mark;

		for (int k = 0; k < 4; k++) { // 주변 4방향 탐색
			int nextR = row + dr[k];
			int nextC = col + dc[k];

			if (map[nextR][nextC] == -1) // 원래 있던 섬과 붙어 있던 땅
				markDfs(nextR, nextC, mark);
		}
	}
}
