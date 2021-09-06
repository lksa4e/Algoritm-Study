import java.io.*;
import java.util.*;

public class Main11780 {
	/*
	 * 시행착오
	 * 1. 같은 경로를 입력받을 수 있는지 몰랐음
	 * ex) 1 4 1
	 *     1 4 2
	 *   => 입력받을때 이런 경우 고려해야함..
	 * 2. 갈 수 없는 경우 INF값 그대로 출력 => 0으로 출력해야됨
	 */

	static int N, M;
	static final int INF = (int)1e9;
	static int[][] graph, path;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static Stack<Integer> s;
	static StringBuilder ans = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new int[N+1][N+1];
		path = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				graph[i][j] = INF; // 초기값 셋팅
				path[i][j] = -1;
			}
		}
		
		for(int i=1; i<=N; i++) graph[i][i] = 0; // 자기자신은 0
		
		int a, b, c;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(graph[a][b] > c) graph[a][b] = c;
			path[a][b] = a;
		}
		
		// 플로이드 와샬
		for(int k=1; k<=N; k++) { // 거쳐가는 정점
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					// i에서 j로 가는경우와 k를 거쳐가는 경우 비교
					if(graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
						path[i][j] = path[k][j]; // k 거쳐갈 경우 경로 갱신
					}
				}
			}
		}
		
		// 출력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(graph[i][j]==INF) graph[i][j] = 0;
				ans.append(graph[i][j]).append(" ");
			}
			ans.append("\n");
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(path[i][j] == -1) { // 못가는 길이면  0 출력
					ans.append(0).append("\n");
					continue;
				}
				
				s = new Stack<Integer>(); // 스택으로 경로 뒤집기
				int before = j;
				s.push(j);
				
				while(i != path[i][before]) { // 스택에 경로 push
					before = path[i][before];
					s.push(before);
				}
				
				ans.append(s.size()+1).append(" ").append(i).append(" ");
				while(!s.isEmpty()) { // 스택 pop 하면서 ans에 append
					ans.append(s.pop()).append(" ");
				}
				ans.append("\n");
			}
		}
		System.out.println(ans);
		
	}
}