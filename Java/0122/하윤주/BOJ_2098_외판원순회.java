import java.io.*;

/**
 * [0119] BOJ 2098 외판원 순회
 * dp, 비트마스킹
 * 
 * sol)
 * 0번 정점에서 시작해 모든 정점을 방문한다.
 * 현재 정점을 기준으로 모든 정점을 방문했다면 다시 0번 정점까지의 거리를 반환한다.
 * 이전에 방문한 정점들을 거쳐 현재 정점에 도달했지만 이 상태로 이미 방문한 적이 있다면 기록해둔 최단거리를 반환한다.
 * 아니라면 아직 방문하지 못한 정점들을 하나씩 방문할때 최단거리와 현재까지의 최단거리를 비교해 최단거리를 갱신한다.
 * 
 * 시행착오)
 * 다익스트라로 푸는건가? 라는 생각이 들었지만 알고리즘 분류가 dp 라고 되어있어서 포기했다.
 * 사실 비트마스킹도, dp도 너무 어려워서 다른 사람들 풀이를 보고 풀었지만 아직도 완벽하게 이해하지 못했다..
 * 
 * 방문할 수 없는 정점이거나 자기 자신의 경우 Integer.MAX_VALUE로 설정하면 오버플로우가 발생해서 틀린다!
 */

import java.util.*;

public class BOJ_2098_외판원순회 {
	static final int INF = 16 * 1_000_000;    // Integer.MAX_VALUE : 오버플로우 발생
	static int N;
	static int[][] map, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		// 비용 2차원 배열
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 현재 정점에서 모든 정점을 방문하는 최단 비용을 저장한 2차원 배열
		dp = new int[N][(1<<N)-1];    // 1차원에는 현재 위치한 정점을, 2차원에는 현재 정점을 제외한 나머지 모든 정점의 방문여부를 저장
		for (int i=0; i<N; i++) Arrays.fill(dp[i], INF);
		
		System.out.println(travel(1, 0));
	}

	// 재귀적으로 순회(현재 정점에서 아직 방문하지 않은 정점까지의 최단거리 계산)
	private static int travel(int visited, int current) {
		// 만약 현재 정점을 기준으로 나머지 정점을 모두 방문했으면 시작 정점으로 돌아감
		if (visited == (1<<N)-1) {
			int toStartDist = map[current][0];
			if (toStartDist == 0) return INF;    // 현재 정점에서 시작 정점까지 도달할 수 없으면 예외
			else return toStartDist;
		}
		
		// 현재 정점에서 나머지 정점까지의 최단거리를 계산했었으면 계산한 값을 반환
		int dist = dp[current][visited];
		if (dist != INF) return dist;
		
		// 현재 정점에서 아직 방문하지 않은 정점들을 방문
		for (int i=0; i<N; i++) {
			int curDist = map[current][i];
			// 현재 정점에서 특정 정점까지 도달할 수 없거나, 이미 방문한 정점이면 pass
			if (curDist==0 || (visited & (1<<i))!=0) continue;
			// 방문하지 않았고 방문할 수 있으면 현재까지의 최단거리와 해당 정점을 거친 최단거리를 비교해 갱신
			dp[current][visited] = Math.min(dp[current][visited], travel((visited | (1<<i)), i)+curDist);
		}
		
		return dp[current][visited];
	}

}
