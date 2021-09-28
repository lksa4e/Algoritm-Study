import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [0928] BOJ 17471 게리맨더링
 * 그래프, 완전탐색, 부분집합, dfs
 * 
 * sol)
 * 2개의 선거구로 나누기 위해 부분집합을 구한 뒤, dfs를 통해 각 부분집합에 포함된 원소가 서로 연결되었는지 확인하며 두 선거구 인구수 최소를 찾아감
 * 
 * 1. 재귀 함수를 통해 모든 부분집합 구하기
 * 2. 현재 부분집합 경우에 포함된 경우와 포함되지 않은 경우를 2개의 선거구로 나눔
 * 3. 각 선거구에 포함된 원소를 BFS 탐색하여 같은 부분집합에 포함된 모든 원소가 탐색 가능하다면 두 선거구 인구수 갭을 구하여 최솟값 갱신
 *
 * 시행착오)
 * - 부분집합을 구하고 부분집합에 포함된 원소와 포함되지 않은 원소 2개의 선거구를 모두 확인해야 함.
 *	 어차피 모든 부분집합의 경우의 수를 구하므로 모든 선거구 경우르 확인할 수 있다고 생각했는데,
 *	 2개의 선거구를 동시에 확인하여 2개의 선거구 모두 각 원소들이 연결되어야 하므로 동시에 체크해줘야 한다.
 * 
 */

public class BOJ_17471_게리맨더링 {
	static int N, total, min = Integer.MAX_VALUE;
	static int[] sections;					// 각 구역 별 인구수
	static boolean[][] adjMatrix;			// 구역 인접 여부
	static boolean[] subset, visited;		// 부분집합 경우, 연결되었는지 여부
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sections = new int[N];
		
		// 구역 별 인구수 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			sections[i] = Integer.parseInt(st.nextToken());
			total += sections[i];		// 총 인구수 저장
		}
		
		// 인접한 구역 정보 저장
		adjMatrix = new boolean[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while(st.hasMoreTokens()) {
				int n = Integer.parseInt(st.nextToken())-1;
				adjMatrix[i][n] = true;
			}
		}
		
		// 모든 부분집합 생성하여 2개의 선거구로 구분
		subset = new boolean[N];
		generateSubset(0, 0);
		
		// 두 선거구로 나눌 수 있는지 여부에 따라 출력
		if (min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}

	// 재귀적으로 부분집합 생성하여 2개의 선거구로 구분
	private static void generateSubset(int idx, int sum) {
		// 기저조건 : 모든 구역을 부분집합 고려하면
		if(idx == N) {
			// 부분집합 원소개수 0개인 경우와 N인 경우 제외
			if (sum == total || sum == 0) return;
			// 2개의 선거구 내부에서 각 구역들이 연결되지 않은 경우 제외
			if (!divide()) return;
			// 2개의 선거구로 나누기 성공했다면 인구수 차이를 최솟값으로 갱신
			int gap = (total - sum) - sum;
			min = Math.min(min, Math.abs(gap));
			return;
		}
		
		// 유도파트 : 각 구역을 부분집합에 넣거나 넣지 않음
		subset[idx] = true;
		generateSubset(idx+1, sum+sections[idx]);
		
		subset[idx] = false;
		generateSubset(idx+1, sum);
	}

	// 부분집합에 포함된 경우와 포함되지 않은 경우를 각각의 선거구로 하여 구역 연결 여부를 확인
	private static boolean divide() {
		visited = new boolean[N];
		visitSection(0);		// 2개의 선거구 중 1번 구역(인덱스 0)이 포함된 선거구와
		int other = 0;
		for (int i=0; i<N; i++) if (subset[i] != subset[0]) other = i;
		visitSection(other);	// 1번 구역(인덱스 0)이 포함되지 않은 선거구
		
		// 2개의 선거구에 포함된 구역 중 연결되지 않은 구역이 1개라도 존재하면 false
		for (boolean tf : visited) if (tf == false) return false;
		return true;
	}

	// 각 선거구 내부 구역 연결 여부를 탐색
	private static void visitSection(int n) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(n);
		
		while(!q.isEmpty()) {
			int i = q.poll();			// 현재 구역에서
			visited[i] = true;
			for (int j=0; j<N; j++) {	// 모든 구역을 둘러보며
				// 방문 안했고, 현재 선거구에 포함된 구역이며, 인접하다면 연결됨
				if (!visited[j] && subset[i]==subset[j] && adjMatrix[i][j]) q.offer(j);
			}
		}
	}

}
