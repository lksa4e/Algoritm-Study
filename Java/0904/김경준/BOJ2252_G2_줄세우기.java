import java.io.*;
import java.util.*;
/**
 * BOJ 2252 줄세우기: 
 * 메모리 : 43716kb  시간 : 544ms 
 * 
 * 처리해야 할 일의 순서가 있는 위상정렬 문제
 * 문제는 키 순서로 나열하는것이지만, 특정 사람 기준으로 나보다 키가 큰 사람들을 먼저 처리해야 할 작업으로 간주하여
 * 작업의 우선순위를 두는 위상정렬 문제로 해결할 수 있다.
 *  
 * 별다른 제한사항이 없음...
 * 
 */

public class BOJ2252_G2_줄세우기{
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, degree[];
	static List<Integer> list[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		degree = new int[N+1];
		list = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) list[i] = new ArrayList<Integer>();  // Arraylist 초기화

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fst = Integer.parseInt(st.nextToken());
			int snd = Integer.parseInt(st.nextToken());
			list[fst].add(snd);   // fst 보다 키가 작은 사람들을 list에 추가
			degree[snd]++;        // snd 보다 키가 큰 사람들의 수
		}
		
		solve();
		System.out.print(sb);
	}
	
	static void solve() {
		Queue<Integer> q = new ArrayDeque<Integer>();  // 제한사항이 없으므로 그냥 queue 선언
		
		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) q.offer(i);          // 나보다 키가 큰 사람이 없는 경우 -> 바로 줄세우기 가능
		}
		
		int count = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur + " ");
			
			if(++count == N) return;              // N명을 모두 줄세웠다면 바로 return
			
			for(int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i);
				if(--degree[next] == 0) q.offer(next);   // 나보다 키큰 사람들을 모두 처리했다면 -> 다음은 내가 줄설 차례
			}
		}
	}
}
