import java.io.*;
import java.util.*;

/**
 * [1211] BOJ 21609 상어 중학교
 * 구현, 2차원 배열, 완전탐색, dfs
 * 
 * sol)
 * 1~5 로직 순서대로 구현한다. 대신 다음을 주의한다.
 * 1)
 * N*N 개의 좌표를 모두 확인하면서 일반 블록이고 아직 방문하지 않은 경우 dfs 탐색을 통해 인접한 블록 그룹을 탐색한다.(0 또는 자기 자신인 블록)
 * 이때 무지개 블록(0)은 방문 여부를 해제하여 다른 블록 그룹에도 포함될 수 있도록 해야한다.
 * 3)
 * 중력 구현은 각 열마다 큐를 생성하여 빈칸 좌표를 차곡차곡 enqueue하도록 한다.
 * 단, 검은 블록을 만나면 큐를 초기화해야한다.
 * 
 * 2차원 배열 좌표는 1차원 배열 좌표로 변환하여 구현했다.
 * 
 * 시행착오)
 * - 크기가 가장 큰 블록 그룹 구하면서 우선순위 구현을 잘못하여 테케 2번이 계속 틀렸다.
 * - 무지개 블록 방문 해제도 게시판 테케보고 뒤늦게 알아챘다..
 * 
 */

public class BOJ_21609_상어중학교 {
	static final int VACANT = -2;       // 블록 제거 이후 빈 칸
	static final int BLACK = -1;        // 검은 블록
	static final int RAINBOW = 0;       // 무지개 블록
	
	static final int BLOCK_GROUP_SIZE = 0;       // maxBlockGroup 배열의 0번 인덱스(그룹 내 블록 개수)
	static final int RAINBOW_SIZE = 1;           // maxBlockGroup 배열의 1번 인덱스(무지개 블록 개수)
	
	static int N, M, score, maxStandardBlock;    // 최대 블록 그룹의 기준 블록 좌표
	static int[] maxBlockGroup = new int[2];     // 블록 그룹 우선순위를 비교하기 위한 배열(0: BLOCK_GROUP_SIZE, 1: RAINBOW_SIZE)
	static int[][] map, rotatedMap;
	static boolean[][] visited;
	static Map<Integer, List<Integer>> blockGroupMap = new HashMap<>();       // 각 블록 그룹에 포함된 블록 좌표를 저장하기 위한 맵(key: 기준 블럭 좌표, value: 블록 그룹에 포함된 블록 좌표 리스트)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Integer> rainbowPoints = new ArrayDeque<Integer>();          // 무지개 블록 방문 해제하기 위해 각 dfs 탐색 시 방문한 무지개 블록 좌표 저장
	static Queue<Integer> vacantPoints = new ArrayDeque<Integer>();           // 중력 작용을 위해 열 별 빈 좌표 저장하는 큐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][N];
		map = new int[N][N];
		rotatedMap = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 오토 플레이 시작
		while(findBlockGroups()) {      // 블록 그룹 존재할 때까지 오토 플레이 반복
			removeAndGetScore();        // 블록 그룹 존재하면 찾은 그룹을 맵에서 삭제하고 점수 획득
			actGravity();               // 중력 작용
			rotateMap();                // 90도 반시계 회전
			actGravity();               // 중력 작용
		}
		
		// 출력
		System.out.println(score);
	}

	// 블록 그룹 존재 여부 파악
	private static boolean findBlockGroups() {
		// 초기화
		for (boolean[] v : visited) Arrays.fill(v, false);    // 방문 초기화
		blockGroupMap.clear();                                // 각 블록 그룹 별 블록들 좌표 저장하는 맵 초기화
		Arrays.fill(maxBlockGroup, 0);                        // 블록 그룹 별 크기, 무지개 블록 개수 배열 초기화
		maxStandardBlock = -1;                                // 가장 큰 블록 그룹의 기준 블록 초기화
		
		// N*N 모든 가능한 좌표 dfs 탐색
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j] && map[i][j]>0) {          // 방문 안했고 일반 블록인 경우만 기준 좌표로 삼아 dfs
					rainbowPoints.clear();                    // 무지개 블록은 방문여부 초기화해야하므로 큐에 저장해야함. 이 큐를 초기화
					
					int xy = i*N+j;                           // 기준 좌표
					int[] curblockGroup = findMaxBlockGroup(xy, i, j, map[i][j], new int[2]);      // dfs 탐색(반환 배열 : 기준 좌표 블록 그룹에 포함된 블록 개수, 무지개 블록 개수 저장한 배열)
					if (curblockGroup[BLOCK_GROUP_SIZE]<2) continue;                               // 블록 그룹에 포함된 블록 개수가 2개 미만이면 pass
					
					// 우선순위 비교(행열 좌표값이 작은 경우먼저 비교하므로 만약 값이 같으면 자연스럽게 나중에 비교하는 좌표를 기억하도록 함
					if ((maxBlockGroup[BLOCK_GROUP_SIZE] < curblockGroup[BLOCK_GROUP_SIZE]) ||
						(maxBlockGroup[BLOCK_GROUP_SIZE] == curblockGroup[BLOCK_GROUP_SIZE] && maxBlockGroup[RAINBOW_SIZE] <= curblockGroup[RAINBOW_SIZE])) {
						// 우선순위 높은 블록 그룹으로 갱신
						maxBlockGroup[BLOCK_GROUP_SIZE] = curblockGroup[BLOCK_GROUP_SIZE];
						maxBlockGroup[RAINBOW_SIZE] = curblockGroup[RAINBOW_SIZE];
						maxStandardBlock = xy;
					}
					
					clearRainbowVisit();                      // 무지개 블록 방문여부 초기화
				}
			}
		}
		return (maxStandardBlock!=-1);                        // 블록 그룹이 존재하면 true 반환
	}

	// 기준 좌표 별 블록 그룹 찾기(dfs)
	private static int[] findMaxBlockGroup(int xy, int x, int y, int color, int[] size) {
		visited[x][y] = true;       // 방문 저장
		blockGroupMap.computeIfAbsent(xy, v -> new ArrayList<>()).add(x*N+y);    // 현재 기준 좌표의 블록 그룹 리스트에 추가
		size[BLOCK_GROUP_SIZE]++;   // 블록 그룹 크기 증가
		
		// 4방 인접 탐색
		for (int i=0; i<4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			
			// 경계 안이고, 방문 안했고, 자기 자신과 같은 색상 혹은 무지개 블록이면 블록 그룹에 포함
			if (isInside(nx, ny) && !visited[nx][ny] && (map[nx][ny]==color || map[nx][ny]==RAINBOW)) {
				if (map[nx][ny]==RAINBOW) {      // 이때 무재개 블록이면 무지개 블록 개수를 증가하고 나중에 방문 해제해주기 위해 큐에도 저장
					size[RAINBOW_SIZE]++;
					rainbowPoints.offer(nx);
					rainbowPoints.offer(ny);
				}
				findMaxBlockGroup(xy, nx, ny, color, size);      // 재귀 태워 보냄(dfs)
			}
		}
		return size;      // 블록 그룹 내 블록 개수, 무지개 블록 개수를 저장한 배열 반환
	}
	
	// 무지개 블록 방문 해제
	private static void clearRainbowVisit() {
		while(!rainbowPoints.isEmpty()) {
			int x = rainbowPoints.poll();
			int y = rainbowPoints.poll();
			visited[x][y] = false;
		}
	}

	// 맵에서 블록 그룹을 제거하고 점수 획득
	private static void removeAndGetScore() {
		for (int block : blockGroupMap.get(maxStandardBlock)) map[block/N][block%N] = VACANT;
		score += Math.pow(maxBlockGroup[BLOCK_GROUP_SIZE], 2);
	}
	
	// 중력 작용
	private static void actGravity() {
		for (int c=0; c<N; c++) {
			vacantPoints.clear();          // 각 열 별 빈 좌표 저장할 큐를 초기화
			
			for (int r=N-1; r>=0; r--) {   // 아래 행부터 위의 행으로 올라가며
				int p = map[r][c];
				
				if (p==VACANT) {               // 빈 좌표면 큐에 저장
					vacantPoints.offer(r);
					vacantPoints.offer(c);
				} else if (p==BLACK) {         // 단, 검은 블록을 만나면 큐를 초기화
					vacantPoints.clear();
				} else if (p>BLACK && !vacantPoints.isEmpty()) {    // 검은색이 아닌 블록을 만났고 아래에 빈 좌표가 있으면
					int x = vacantPoints.poll();                    // 큐에서 빈 좌표 꺼내서 해당 좌표에 블록 저장하고
					int y = vacantPoints.poll();
					map[x][y] = p;
					map[r][c] = VACANT;
					vacantPoints.offer(r);                          // 블록이 있던 자리는 다시 빈칸으로 만들어 큐에 저장
					vacantPoints.offer(c);
				}
			}
		}
	}

	// 맵을 90도 반시계 회전
	private static void rotateMap() {
		for (int[] m : rotatedMap) Arrays.fill(m, 0);
		
		// 90도 반시계 회전하여 임시 배열에 저장하고
		for (int max=N-1, c=max; c>=0; c--) {
			for (int r=0; r<N; r++) rotatedMap[max-c][r] = map[r][c];
		}
		
		// 임시 배열을 원본 배열에 복사한다.
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) map[i][j] = rotatedMap[i][j];
		}
	}
	
	// 경계 체크
	public static boolean isInside(int nx, int ny) {
		return (nx>=0 && nx<N && ny>=0 && ny<N);
	}

}
