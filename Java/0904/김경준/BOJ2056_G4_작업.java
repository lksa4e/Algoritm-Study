import java.io.*;
import java.util.*;
/**
 * BOJ 2056 G4 작업: 
 * 메모리 : 82376kb  시간 : 892ms 
 * 
 * 처리해야 할 일의 순서가 있는 위상정렬 문제
 * 주의할점 : 다른 문제들과 다르게 작업을 처리하는데 걸리는 소요시간이 존재한다.
 *         이 경우에 다음 작업을 처리하기 위해 queue에 넣는 과정에서 단순하게 BFS 혹은 다익스트라처럼 그리디한 방식으로
 *         "현재(queue에서 뽑은 개체)까지의 걸린 시간 + 다음 작업 시간" 으로 처리를 한다면 문제가 발생할 수 있다.
 *         
 *         작업 3을 처리하기 위해서 1,2가 선행되어야 하고 작업1과 작업2는 선행작업이 없다는 상황을 가정해보자.
 *         (이때 작업 1을 처리하기 위해서 필요한 시간은 work_time[1] = 100; 작업 2를 처리하기 위해서 필요한 시간은 work_time[2] = 10;)
 *         
 *         위상정렬의 구현 방식에 따라 차이가 있을 수 있지만, 이번 경우에는 index 순서로 처리한다는 상황 하에 1이 먼저 처리되고, 2가 처리되는 상황을 가정한다.
 *         작업 1이 처리되는 순간에 작업 3은 아직 선행되어야 할 작업이 남아 있으므로 작업처리 queue에 들어가지 못한다.
 *         이후에 작업2가 처리되는 순간이 되어서야 작업 3은 모든 선행작업이 종료되어 작업처리 queue에 진입할 수 있다.
 *         
 *         이때 작업 2의 종료시간은 10이다. 하지만 시간 10에 작업 3을 바로 처리할 수 없다.
 *         작업 3을 수행하기 위해서는 작업 1,2가 모두 종료되어야 하므로 작업 2의 종료시간인 10이 아닌 작업 1의 종료시간인 100이 되어서야 작업 3을 시작할 수 있다.
 *         이것은 특정 작업을 시작하기 위해서 선행되어야 할 작업이 N개인 경우 N개의 작업 종료시간 중 가장 긴 것을 고려해야 한다는 것을 의미한다.
 *         
 *         따라서 이러한 작업시간이 주어지는 문제 유형을 해결하기 위하여 특정 작업에 대한 degree 처리(선행작업 제거)를 해줄 때 선행작업의 종료시간 중
 *         가장 긴 작업이 무엇인지를 기억하는게 필요하다.
 *         본인은 아래와 같이 별도의 배열을 선언해서 매번 최대값을 갱신하는 방법을 사용함 
 *         finish_time[next] = Math.max(finish_time[next], cur.y + work_time[next]);
 * 
 */

public class BOJ2056_G4_작업{
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, degree[], work_time[], finish_time[], answer = 0;
	static List<Integer> list[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		init();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			work_time[i] = Integer.parseInt(st.nextToken());
			int pre_cnt = Integer.parseInt(st.nextToken());
			
			degree[i] += pre_cnt;
			
			for(int j = 0; j < pre_cnt; j++) {
				int pre_num = Integer.parseInt(st.nextToken());
				list[pre_num].add(i);
			}
		}
		
		solve();
		System.out.print(answer);
	}
	
	static void solve() {
		Queue<Pair> q = new ArrayDeque<Pair>();
		
		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) q.offer(new Pair(i, work_time[i]));   // 처음부터 처리할 작업들 queue에 바로 추가
		}
		
		int count = 0;
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			answer = Math.max(answer, cur.y);
			
			if(++count == N) return;
			
			for(int i = 0; i < list[cur.x].size(); i++) {
				int next = list[cur.x].get(i);
				finish_time[next] = Math.max(finish_time[next], cur.y + work_time[next]);  // 해당 작업의 완료에 걸리는 가장 긴 시간 측정
				if(--degree[next] == 0) q.offer(new Pair(next, finish_time[next])); // 모든 선행작업 종료 시 작업처리 queue에 추가
			}
		}
	}
	
	// 배열이 많아서 초기화 함수 분리
	static void init() {
		degree = new int[N+1];
		finish_time = new int[N+1];
		list = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) list[i] = new ArrayList<Integer>();
		work_time = new int[N+1];
	}
	
	//  index , 걸리는 시간
	static class Pair{
		int x,y;  
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
