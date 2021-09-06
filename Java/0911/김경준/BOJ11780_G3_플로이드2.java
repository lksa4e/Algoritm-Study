import java.io.*;
import java.util.*;

/**
 * G3 BOJ 11780 플로이드 2 :
 * 메모리 : 52908kb, 시간 : 620ms 
 * 
 * 플로이드 와샬 + 경로까지 기억해줘야 하는 문제
 * path[i][j] => i에서 j로 갈 때 어떤 경로를 통해서 가는지를 저장해 준다.
 * 옛날에 풀었던 경로추적 방식대로 i -> j로 갈 때
 * 
 * int start = i, end = j;
 * while(start != end) {
 * 	st.push(end);
 *  end = path[start][end];
 * }
 * 와 같이 도착점에서 시작하여 도착지를 바꿔가며 출발지까지 탐색한다.
 * 
 */

public class BOJ11780_G3_플로이드2 {
	static int N,M;
	static int[][] map, path;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		path = new int[N+1][N+1];
		
		init();  // 초기화
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fst = Integer.parseInt(st.nextToken());
			int snd = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			if(map[fst][snd] > dist) {
				map[fst][snd] = dist;
				path[fst][snd] = fst;  // i -> j로 가는 경로가 있을 때 j의 이전 경로는 i로 세팅
			}
		}
		solve();
		print_answer();
	}

	// 플로이드 와샬 함수
	// i -> j 보다 i -> k -> j 거쳐가는게 빠르다면 갱신
	// k 거쳐가는게 더 빠른 경우에 j의 이전 경로를 k로 갱신하고, 이전 경로도 마찬가지로 갱신한다.
	static void solve() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i == j) continue;
					if(map[i][k] + map[k][j] < map[i][j]) {
						map[i][j] = map[i][k] + map[k][j];
						// k를 거쳐가는게 더 빠르므로 거리와 함께 path도 갱신한다.
						// k를 거쳐서 왔기 때문에 이전 경로를 k로 갱신한다고 생각할 수 있지만
						// 플로이드 와샬 알고리즘은 단순한 i -> k -> j 의 경로가 아니라 k를 경유한다는 의미를 지니므로
						// i -> XX -> k -> ㅁㅁ -> j 처럼 중간에 다른 경로를 지나갈 수 있다.
						// 따라서 path[i][j] = k 가 아닌 path[k][j](ㅁㅁ)로 갱신한다. 
						// 다른 풀이를 보니 단순하게 k로 지정하고 재귀 방식으로도 푸는 경우가 있는 것 같으나 그건 잘 모르겠음..
						path[i][j] = path[k][j];
					}
				}
			}
		}
	}
	
	// 정답 출력하기
	static void print_answer() {
		StringBuilder sb = new StringBuilder();

		// 1. n*n 행렬 출력
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j] == 999999) sb.append(0 + " ");  // 갈수 없다면 0으로 출력
				else sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
		
		// 2. n*n 줄에  1->1 , 1-> 2, ,,, N->N-1, N->N 도시개수 & 경로 출력
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) sb.append(0);  // i와 j가 같으면 무조건 0
				else if(map[i][j] == 999999) sb.append(0); //갈 수 없어도 0
				else {
					Stack<Integer> st = new Stack<Integer>();  // 역순 경로탐색을 위한 스택
					
					int start = i, end = j;
					while(start != end) { // 도착지에서 시작하며 이전 경로들을 하나하나 탐색함 
						 st.push(end);
						 end = path[start][end];
					}
					
					st.push(start);  // while문 조건에 의해 시작 경로는 빠졌기 때문에 시작 경로도 포함 
					sb.append(st.size() + " ");	// stack의 사이즈 => 전체 경로 개수
					while(!st.empty()) {  
						sb.append(st.pop() + " "); // 전체 경로 출력
					}						
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
	
	// 입력 받기 전 초기화
	static void init() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				map[i][j] = 999999;
			}
		}
	}
}
