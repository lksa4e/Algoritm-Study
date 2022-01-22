import java.io.*;
import java.util.*;

/**
 * [0122] BOJ 13913 숨바꼭질
 * bfs
 * 
 * sol)
 * 최단으로 가는 경로를 찾아야하므로 완탐 중 bfs로 풀어야한다.
 * 흔히 접하는 2차원 배열 4방탐색을 1차원 3방 탐색으로 바꿔주면 된다.
 *
 */

public class BOJ_13913_숨바꼭질 {
	static int N, K;
	static int[] visited;          // 어디서 도달했는지 정보 저장
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 어디에서 출발하여 현재 인덱스 좌표에 왔는지를 저장하기 위한 배열
		visited = new int[100001];
		Arrays.fill(visited, -1);      // 좌표는 0~100000까지 가능하므로 0이 아닌 다른 값으로 초기화해야함
		
		// 탐색
		bfs();
	}

	// 탐색
	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		visited[N] = N;     // 출발점 설정
		q.offer(N);
		
		int time = 0;       // 소요 시간
		
		while(!q.isEmpty()) {
			int qSize = q.size();          // 큐 사이즈만큼씩 끊어서 bfs 탐색하면 소요 시간(너비)를 알 수 있음
			
			while(qSize-->0) {
				int cur = q.poll();
				
				if (cur==K) {              // 목적지에 도달하면 현재까지 경로를 출력
					printSteps(time);
					return;
				}
				
				// 3방 탐색
				int walkFront = cur+1;
				int walkBack = cur-1;
				int jump = 2*cur;
				
				// 경계 체크, 방문 여부 체크
				if ((walkFront>=0 && walkFront<=100000) && (visited[walkFront]==-1)) {
					visited[walkFront] = cur;
					q.offer(walkFront);
				}
				if ((walkBack>=0 && walkBack<=100000) && (visited[walkBack]==-1)) {
					visited[walkBack] = cur;
					q.offer(walkBack);
				}
				if ((jump>=0 && jump<=100000) && (visited[jump]==-1)) {
					visited[jump] = cur;
					q.offer(jump);
				}
			}
			time++;       // 1초가 지남
		}
	}

	// 경로 출력
	private static void printSteps(int time) {
		System.out.println(time);
		if (time == 0) {              // 출발지와 도착지가 같으면 출력은 1개의 좌표만
			System.out.println(N);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		int to = K;
		sb.append(to);
		
		while(true) {
			int from = visited[to];                 // 이전 좌표를 StringBuilder 앞으로 차곡차곡 쌓음
			sb.insert(0, " ").insert(0, from);
			if (from == N) {
				System.out.println(sb);
				break;
			}
			to = from;
		}
		
	}

}
