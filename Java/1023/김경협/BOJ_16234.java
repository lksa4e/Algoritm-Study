import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 16234번 인구 이동
 * 
 * union-find로 L이상 R이하의 차이는 묶어줬다.
 * union-find할 때, parent[N][2]로 했는데 0번째에는 음수가 있으면 루트, 양수면 루트를 가르키는 배열을,
 * 1번째에는 지금까지 합친 국가들의 인구 수 누적을 저장했다.
 * 
 * visited와 백트래킹을 사용해서 시간을 줄였다.
 * 17688KB	564ms
 */

public class BOJ_16234 {

	static final int dr[] = {-1,1,0,0};
	static final int dc[] = {0,0,-1,1};
	
	static int N, L, R, pop[][], parent[][];
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		parent = new int[N*N][2];	// union-find를 위한 배열
		pop = new int[N][N];		// 인구 수 저장
		visited = new boolean[N*N];	// 속도 향상을 위한 visited 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				pop[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int day = 0;
		for (; day < 2000; day++) {	// 최대 2000일 까지 주어진다.
			Arrays.fill(visited, false);	// visited는 한 번 인구이동시마다 초기화
			if(!migration()) break;	// 인구이동, 더 이상 인구 이동할 수 없을 때 break
		}
		System.out.println(day);
	}
		
	static boolean migration() {
		boolean isMigrate = false;	// 인구이동 할 수 있는지 검사하는 flag
		init();						// union 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int currPop = pop[i][j];	// 현재 가르키는 국가의 인구수와 1차원 배열로 늘어뜨린 idx
				int currIdx = j + i * N;
				for (int dir = 0; dir < 4; dir++) {	// 4방향
					int nextR = i + dr[dir];
					int nextC = j + dc[dir];
					
					if(isOutOfMap(nextR, nextC)) continue;	// 범위 밖일 때,

					int diff = Math.abs(currPop - pop[nextR][nextC]);
					if(diff >= L && diff <= R) {
						union(currIdx, nextC + nextR * N);	// 인구수가 L과 R 사이일 때 그룹 합치기
						isMigrate = true;	// 인구 이동 가능,
					}
				}
			}
		}
		
		// 만들어진 각 union-set의 평균값으로 맵 갱신해주기
		for (int i = 0; i < N * N; i++) {	// parent가 음수인 국가 찾기
			if(visited[i]) continue;
			if(parent[i][0] < 0) {
				mark(i);				// parent가 음수면 현재 i를 parent로 하고 있는 모든 국가는 같은 그룹
			}
		}
		
		return isMigrate;
	}
	
	static void mark(int i) {
		int cnt = parent[i][0] * -1;	// 현재 그룹에 속한 국가의 수
		int avg = parent[i][1] / (cnt);
		
		// 루트 부분은 미리 처리해주기
		visited[i] = true;
		pop[i / N][i % N] = avg;
		if(--cnt == 0)
			return;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int idx = c + r * N;
				if(visited[idx]) continue;	// 이미 방문한 국가는 더 이상 방문 x
				if(find(idx) == i) {
					visited[idx] = true;
					pop[r][c] = avg;	// 맵 갱신
					if(--cnt == 0) {	// 백트래킹, 바꿀 수 있을만큼 바꿨으면 반환하기
						return;
					}
				}
			}
		}
	}
	
	static boolean isOutOfMap(int r, int c) {
		return r < 0 || c < 0 || r >= N || c >= N;
	}
	
	// 음수를 이용한 union-find 알고리즘, 음수로 카운팅, 배열로 인구 총합 구하기
	static void init() {
		for (int i = 0; i < N*N; i++) {
			parent[i][0] = -1;
			parent[i][1] = pop[i / N][i % N];	// 인구 수
		}
	}
	
	static int find(int x) {
		if(parent[x][0] < 0)
			return x;
		else
			return parent[x][0] = find(parent[x][0]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y)
			return;
		
		if(parent[x][0] > parent[y][0]) {
			parent[y][0] += parent[x][0];
			parent[y][1] += parent[x][1];	// 합칠 때, 인구 수도 같이 합치기
			parent[x][0] = y;
		} else {
			parent[x][0] += parent[y][0];
			parent[x][1] += parent[y][1];
			parent[y][0] = x;
		}
	}

}
