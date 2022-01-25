import java.util.*;
import java.io.*;

/*
 * BOJ 13913번 숨바꼭질 4
 * 
 * 최단 거리 찾기 문제, 경로 추적
 * 
 * BFS를 사용해 최단거리를 찾고, 이전 루트를 저장하는 배열을 만들어서
 * 경로를 기억하게 했다.
 * 
 * 경로 추적할 때, 시작점을 -1 같은 걸로 지정해놔야 나중에 경로 추적을 사용할 때
 * 오류가 나지 않는다.
 * 
 * 27956KB	292ms
 */

public class BOJ_13913 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(N, K));

	}
	
	static String bfs(int N, int K) {
		boolean visited[] = new boolean[100001];
		int route[] = new int[100001];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);	// 시작점, 이전 경로는 없으므로 -1로 세팅해준다.
		route[N] = -1;
		visited[N] = true;
		
		while(!q.isEmpty()) {	// 평범한 bfs
			int curr = q.poll();
			
			if(curr == K) {
				break;
			}
			
			for(int i = 0; i < 3; i++) {
				int next = curr;
				
				if(i == 0) next++;
				else if(i == 1) next--;
				else next *= 2;
				
				if(next < 0 || next > 100000) continue;
				if(visited[next]) continue;
				
				q.offer(next);
				route[next] = curr;	// 이전 경로만 세팅
				visited[next] = true;
			}
		}
		
		List<Integer> list = new ArrayList<>();
		int findRoute = K, cnt = 0;
		while(findRoute != -1) {	// 시작점인 -1이 나올 때까지 계속 경로를 추적해 나감
			cnt++;
			list.add(findRoute);
			findRoute = route[findRoute];
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt - 1).append("\n");
		
		for(int i = 0, size = list.size(); i < size; i++) {
			sb.append(list.get(size - i - 1)).append(" ");
		}
		
		return sb.toString();
	}

}
