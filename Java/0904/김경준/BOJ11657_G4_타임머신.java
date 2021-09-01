import java.io.*;
import java.util.*;

/**
 * G4 BOJ 11657 타임 머신:
 * 메모리 : 27260kb 시간 : 332ms
 * 
 * 가중치가 음수가 나오는 그래프이므로 벨만-포드 알고리즘을 사용한다.
 벨만 포드 알고리즘 프로세스
 1. 시작 정점을 결정한다.
 2. 시작 정점부터 다른 정점까지 거리 값을 모두 무한대로 초기화한다. (시작 정점은 0으로 초기화)
 3. 현재 정점의 모든 인접 정점들을 탐생하며, 기존에 기록된 인접 정점까지의 거리보다 현재 정점을 거쳐 인접 정점에 도달하는 
        거리가 더 짧다면 인접 정점까지의 거리를 갱신한다.
 4. 3번 과정을 V−1번 반복한다. ★
    (최단 경로는 사이클을 포함할 수 없다. 따라서 최대 V - 1개의 간선만 사용할 수 있다. 
          따라서 거리를 갱신하는 과정을 V-1번 수행하게 된다면 최단거리를 얻어낼 수 있다.)
     V-1 번의 루프로 최단거리를 구할 수 있는 이유
     -> 특정 정점으로의 최단경로는 특정 정점 이전의 정점까지의 최단경로를 알아낸다면 1의 추가 연산으로 구할 수 있다. 
        (이전 정점이 최단 경로라는 것을 이미 가정했기 때문에 최단경로가 확정된 정점에서 다음 정점까지의 최단거리는 1에 구할 수 있음)
     -> 특정 정점을 N이라고 하고 이전 정점을 N-1이라고 하자
        N까지의 최단경로를 알기 위해 수행되는 연산 수는 N-1까지의 최단 경로를 알아내기 위해 필요한 연산 수 + 1 이다.
     i == 1(시작점)
     -> 시작점에서 인접한 임의의 정점까지의 최단경로를 구하기 위해 필요한 연산수는 1이다.
     i == 2
     -> 마찬가지로 출발점 -> 인접 정점 -> 그다음 인접 정점까지의 최단 경로를 구하기 위해 필요한 연산 수는 1+1 = 2가 된다.
     i == N
     -> 귀납적으로 i에서 N번째 정점까지의 최단 경로를 구하기 위해서 필요한 연산 수는  N-1이 된다.
 5.위 과정을 모두 마친 후에도 거리가 갱신되는 경우가 있다면 그래프에 음수 사이클이 존재한다는 것을 알 수 있다.
 
 * 
 * 평소처럼 안풀고 input()으로 입력부분 분리한다음에  static 없애고 인자로 전해주는 방식 사용하려했더니
 * nullPointerException때문에 엄청 고생... 알고리즘은 기왕이면 그냥 static 풀이로....
 * 
 */

public class BOJ11657_G4_타임머신 {
	static int N, M;
	static List<int[]>[] list;
	static long[] dist;  // 거리의 누적값이 int 범위를 벗어날 수 있음

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		init();
		// bellman_ford 함수 - 사이클 발생 시 true, 아니면 false
		if (bellman_ford()) System.out.print(-1);
		else {
			// 사이클 발생하지 않았다면 거리 출력
			for (int i = 2; i <= N; i++) {
				// 거리 갱신이 안되었다면 -> 도달할 수 없는 도시
				if(dist[i] == Long.MAX_VALUE) System.out.println(-1);
				else System.out.println(dist[i]);
			}
		}
	}

	// 크루스칼
	static boolean bellman_ford() {
		boolean cycle = false;
		dist[1] = 0;
		for (int k = 0; k < N; k++) {  // (V-1)번만큼의 루프 + 마지막 cycle 체크
			for(int i = 1; i <= N; i++){  // 모든 도시에 대해
				for(int[] data : list[i]){  // 다른 도시로 갈 수 있는 경우 다른 도시들 탐색
					int next_node = data[0];
					int next_weight = data[1];
					// dist[i] == Long.MAX_VALUE -> 1에서 출발하여 도달할 수 없는 도시 -> 제외
					// 만약 다음 도시로의 거리가 더 짧아질 수 있다면 갱신
					if (dist[i] != Long.MAX_VALUE && dist[next_node] > next_weight + dist[i]) {
						// N-1번만큼 탐색을 반복을 돈 후에도 거리가 갱신될 수 있다면? -> 사이클 발생
						if (k == N - 1) cycle = true;
						dist[next_node] = next_weight + dist[i];
					}
				}
			}
		}
		return cycle;
	}

	// 입력받아주기
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 버스 노선의 개수
		list = new ArrayList[N + 1];
		dist = new long[N + 1];
		for (int i = 1; i <= N; i++) list[i] = new ArrayList<int[]>();
		Arrays.fill(dist, Long.MAX_VALUE);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fst = Integer.parseInt(st.nextToken());
			int snd = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			list[fst].add(new int[] { snd, distance });
		}
	}
}
