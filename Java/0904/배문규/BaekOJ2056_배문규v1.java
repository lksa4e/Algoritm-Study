package BaekOJ.study.date0904;

import java.io.*;
import java.util.*;

/*
 * 해당 vertex로 들어오는 vertex들이 모두 dp값이 확정이라고 생각하였는데 그렇지 않은 케이스가 존재했음
 * 5
 * 1 0
 * 2 2 1 3
 * 3 1 4
 * 4 0
 * 5 1 2
 * 가 바로 그 반례
 * 아직 dp값이 확정되기 전인데 queue를 모두 poll 해주어서 문제가 발생한 것 이었다.....
 * 
 * 그래서  in, out 모두  queue에서 list로 변경해주고 foreach문으로 dp값을 갱신
 * 그리고 해당 vertex 만 remove 해주고 in 사이즈를 체크해서 enqueue하니까 통과..
 * 다만, in out 두개의 리스트 필드를 관리하니 메모리나 시간에서 오버헤드가 심해서 vertex자체를 link하는 방법으로 새로 풀었음.
 * 
 * 메모리 	시간
 * 109260	1028
 */

class Work{
	int num;
	int time;
	List<Integer> out = new ArrayList<Integer>(); // 해당 vertex가 진출하는 vertex
	List<Integer> in = new ArrayList<Integer>(); // 해당 vertex로 진입하는 vertex
	
	Work(int num){
		this.num = num;
	}
}

public class BaekOJ2056_배문규v1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, _max, dp[];
	static Work[] works;
	static Queue<Work> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {

		N = Integer.parseInt(br.readLine());
		works = new Work[N+1];
		for(int i = 1; i <= N; i++) works[i] = new Work(i);
		
		for(int in = 1; in <= N; in++) {
			st = new StringTokenizer(br.readLine());
			works[in].time = Integer.parseInt(st.nextToken());
			
			int num = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num; j++) {
				int out = Integer.parseInt(st.nextToken());
				works[out].out.add(in); // 진출 => 진입 edge
				works[in].in.add(out); // 진입 <= 진출 edge
			}
		}
		
		dp = new int[N+1]; // 진입 edge가 없는 vertex들의 시간을 확정 시킬 dp
		queue = new ArrayDeque<Work>();
		for(int i = 1; i <= N; i++) {
			// 진입 edge가 없는 vertex들은 dp값을 자기 자신의 시간으로 확정짓고 queue에 enqueue
			if(works[i].in.isEmpty()) { 
				dp[works[i].num] += works[i].time;
				queue.offer(works[i]);
			}
		}
		
		while(!queue.isEmpty()) {
			Work work = queue.poll(); 
			for(int in : work.out) {
				dp[in] = dp[in] < works[in].time + dp[work.num] ? works[in].time + dp[work.num] : dp[in];
				works[in].in.remove(works[in].in.indexOf(work.num));
				if(works[in].in.isEmpty()) queue.offer(works[in]); 
			}
		}
		
		// 작업 시간 구하기
		_max = Integer.MIN_VALUE;
		for(int i = 1; i <= N; i++) {
			if(_max <= dp[i]) _max = dp[i];
		}
		
		System.out.println(_max);
	}
	
}
