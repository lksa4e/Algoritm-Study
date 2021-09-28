import java.io.*;
import java.util.*;

/**
 * G5 BOJ 17417 게리맨더링 :
 * 메모리 : 14844kb 시간 : 152ms
 * 
 * 조합을 이용해서 마을을 분리한 뒤 BFS를 통해 분리된 마을끼리 연결되어있는지 확인
 * N의 최대값은 10 -> nC(2/n) 을 수행하여도 100000정도밖에 되지 않음
 * 이후에 BFS를 수행해도 충분
*/
public class BOJ17471_G5_게리맨더링 {
	static int N, answer = Integer.MAX_VALUE, population[];
	static List<Integer> graph[];
	static boolean select[];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine()); 
		population = new int[N+1];
		graph = new ArrayList[N+1];
		select = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) 
			graph[i] = new ArrayList<Integer>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) 
			population[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			for(int j = 0; j < K; j++) {
				int to = Integer.parseInt(st.nextToken());
				graph[i].add(to);
				graph[to].add(i);
			}
		}
		// nC1, nC2, nC3 ,,, nC(n/2) 까지의 조합을 선택
		for(int i = 1; i <= N/2; i++) {
			Arrays.fill(select, false);
			combi(0,1,i);
		}
		// 만약 정답 갱신이 안되었다면 
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
	
	// 조합 만드는 함수
	// cnt : 선택한 마을 개수
	// num : 이전에 선택한 마을
	// target : 목표 마을 개수
	static void combi(int cnt, int num, int target) {
		if(cnt == target) {
			// 조합을 완성했다면, BFS 수행
			int result = traverse(target);
			if(result != -1) answer = Math.min(answer, result);
			return;
		}
		for(int i = num; i <= N; i++) {
			select[i] = true;
			combi(cnt + 1, i + 1, target);
			select[i] = false;
		}
	}
	static int traverse(int num) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean visited[] = new boolean[N+1];
		int visit_idx = 0, no_visit_idx = 0;
		
		// visit_idx => 조합에서 선택된 마을 중 아무거나
		// no_visit_idx => 나머지 마을 그룹 중 아무거나
		for(int i = 1; i <= N; i++) {
			if(select[i]) visit_idx = i;
			else no_visit_idx = i;
		}
		
		// 선택 그룹에 대한 BFS 수행
		q.offer(visit_idx);
		visited[visit_idx] = true;
		int cnt1 = 0;
		int sum1 = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			cnt1++;
			sum1 += population[cur];
			for(int next : graph[cur]) {
				// 선택되지 않은 마을로는 진입 불가
				if(select[next] && !visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
		// graph가 연결되어있지 않아서 목표 개수만큼 채우지 못했다면 -1 리턴
		if(cnt1 < num) return -1;
		
		Arrays.fill(visited, false);
		q.offer(no_visit_idx);
		visited[no_visit_idx] = true;
		int sum2 = 0;
		int cnt2 = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			cnt2++;
			sum2 += population[cur];
			for(int next : graph[cur]) {
				if(!select[next] && !visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
		if(cnt2 < N - num) return -1;
		
		return Math.abs(sum1 - sum2);
	}
}
