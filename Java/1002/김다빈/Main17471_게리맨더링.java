import java.io.*;
import java.util.*;

/**
 * 백준 17471번 게리맨더링
 * 
 * 풀이 : 조합 + BFS
 * 
 * 1. 조합으로 선거구 1에 포함되는 정점을 선택한다! (선택되지 않은 정점은 자동으로 선거구 2에 포함)
 * 2. 선택된 선거구들의 정점들이 서로 연결되어 있는지 BFS로 탐색
 * 3. 두 선거구 모두 잘 연결되어 있다면 인구차의 최솟값 업데이트 
 * 
 * 15380KB	156ms
 */
public class Main17471_게리맨더링 {
	
	static int N, result = Integer.MAX_VALUE;
	static boolean[] visited;	// 조합을 체크하는 배열 (선거구 1에 해당하는 정점 체크) 
	static int[] people;		// 정점별 인구수를 저장하는 배열 
	static ArrayList<int[]> edgeList;	// 각 정점에 연결된 간선리스트 저장 
	static ArrayList<Integer> gu1 = new ArrayList<Integer>();	// 나눈 선거구별로 정점을 저장하는 리스트 
	static ArrayList<Integer> gu2 = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		people = new int[N];
		
		// 정점별 인구수 저장 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정점별 간선 리스트 저장 
		edgeList = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int[] temp = new int[size];
			
			for (int j = 0; j < size; j++) {
				temp[j] = Integer.parseInt(st.nextToken())-1;
			}
			edgeList.add(temp);
		}

		// 두 선거구로 나누기 위한 조합 수행 
		visited = new boolean[N];	// 선거구 1에 들어가는 정점을 체크하는 배열 
		combination(0);
		
		// 인구차가 갱신되지 않았다 = 두 선거구로 나눌 수 없다 => -1 출력 
		if(result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
	}

	private static void combination(int index) {
		if(index == N) {	// 모든 조합을 수행했다면
			int people1 = 0, people2 = 0;	// 두 선거구별 인구 총합을 저장하는 변수 
			gu1.clear();
			gu2.clear();
			
			for (int i = 0; i < N; i++) {
				if(visited[i]) {	// 선거구 1에 해당하는 정점 추가, 인구수 더하기 
					gu1.add(i);
					people1 += people[i];
				} else {			// 선거구 2인 경우 
					gu2.add(i);
					people2 += people[i];
				}
			}
			
			// 두 선거구 중 정점이 아예 없는 곳이 존재하면 패스 
			if(gu1.size() == 0 || gu2.size() == 0) return;
			
			// 두 선거구 모두 인접한 정점으로 이루어져 있으면 인구차 최솟값 갱신 
			if(bfs(gu1) && bfs(gu2)) {
				result = Math.min(result, Math.abs(people1 - people2));
			}
			
			return;
		}
		
		visited[index] = true;	// 선거구 1에 포함되는 경우 
		combination(index+1);
		
		visited[index] = false;	// 선거구 1에 포함되지 않는 경우 
		combination(index+1);
	}

	private static boolean bfs(ArrayList<Integer> gu) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visit = new boolean[N];
		
		// 임의의 선거구 정점을 시작점으로 설정 
		int start = gu.get(0);
		queue.offer(start);
		visit[start] = true;
		
		int cnt = 1;	// 연결된 정점의 개수 (시작점 1개 포함하는 것으로 초기값 설정) 
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int edge : edgeList.get(cur)) {			// cur 정점과 연결된 간선 확인 
				if(!visit[edge] && gu.contains(edge)) {	// 연결하지 않았고, 조합으로 설정한 선거구에 포함되면 연결 
					queue.offer(edge);
					visit[edge] = true;
					cnt++;
				}
			}
		}
		
		// 선거구로 설정한 구가 모두 연결되어 있다면 true 
		if(cnt == gu.size()) return true;
		
		return false;
	}

}
