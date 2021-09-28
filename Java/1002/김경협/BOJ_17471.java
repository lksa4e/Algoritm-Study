import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 17471번 게리맨더링
 * 
 * 1. 0과 1로 이루어진 플래그 배열로 팀을 구성 후 넥퍼를 돌린다.
 * 2. 만들어진 팀으로 각 팀의 시작점에서 dfs를 돌린다. 이 때, 인접하거나 같은 팀인 정점으로만 dfs가 갈 수 있다.
 * 3. visited 배열 중에 체크 안된게 있으면, 어떤 팀은 중간에 끊어져 있다는 뜻이기 때문에 continue한다.
 * 4. visited가 모두 체크 되었으면 두 팀의 인구수 차이를 구한 뒤, 1을 반복한다.
 * 
 * 15192KB	136ms
 */

public class BOJ_17471 {
	static int N;
	static ArrayList<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		int[] population = new int[N];
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int start = 1; start <= N; start++) {
			st = new StringTokenizer(br.readLine().trim());
			int adjCnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < adjCnt; j++) {
				int end = Integer.parseInt(st.nextToken());
				adj[start].add(end);
			}
		}
		
		int minDiff = Integer.MAX_VALUE;
		
		// 넥퍼로 나누고, dfs로 이어져있는지 확인하고, 최솟값 찾기
		int[] teamFlag = new int[N];
		for (int i = 1; i < N; i++) { // 최소 두 팀이 있어야 하기 때문에 0과 마지막은 제외
			Arrays.fill(teamFlag, 0);
			for (int j = 0; j < i; j++) {
				teamFlag[N-1-j] = 1;
			}
			
			OUT : do {
				// dfs로 이어져있는지 확인
				boolean[] visited = new boolean[N+1];
				// 최초로 0인 index + 1이 출발 노드
				for (int j = 0; j < N; j++) {
					if(teamFlag[j] == 0) {
						dfs(j + 1, visited, teamFlag);
						break;
					}
				}
				// 최초로 1인 index + 1이 출발 노드
				for (int j = 0; j < N; j++) {
					if(teamFlag[j] == 1) {
						dfs(j + 1, visited, teamFlag);
						break;
					}
				}
				
				// 두 선거구 dfs돌고 나서 check 안 된 것 있으면 안 됨
				for (int j = 1; j < N+1; j++)
					if(!visited[j]) continue OUT;
				
				// 이어지면 둘 사이의 차이 최솟값 찾기
				int diff = 0;
				for (int j = 0; j < N; j++) {
					if(teamFlag[j] == 0)
						diff += population[j];
					else
						diff -= population[j];
				}
				diff = Math.abs(diff);
				minDiff = Math.min(diff, minDiff);
			} while(np(teamFlag));
		}
		System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
	}
	
	static void dfs(int curr, boolean[] visited, int[] teamFlag) {
		visited[curr] = true;
		
		for(int next : adj[curr]) {
			// 같은 팀이 아닐 경우 continue;
			if(teamFlag[curr - 1] != teamFlag[next - 1]) continue;
			if(visited[next]) continue;
			dfs(next, visited, teamFlag);
		}
	}
	
	static boolean np(int[] arr) {
		int i = N - 1;
		while(i > 0 && arr[i-1] >= arr[i]) i--;
		
		if(i == 0) return false;
		
		int j = N - 1;
		while(arr[i-1] >= arr[j]) j--;
		
		swap(arr, i-1, j);
		
		int k = N - 1;
		while(i < k) swap(arr,i++, k--);
		return true;
	}
	
	static void swap(int[] arr, int i , int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
