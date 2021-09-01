package BaekOJ.study.date0904;

import java.io.*;
import java.util.*;

/*
 * 어... 맞는데 자꾸 틀렸다고 하네요...
 * 지금 방식을 저는 이해가 잘돼서 사용하는데, 틀려도 왜 틀렸는지 디버깅을 못하겠습니다.
 * 그래서 위상 정렬 문제에 접근하는 방식을 Vertex간 link 방식으로 바꿔야 할 듯 합니다.
 * 일단 이상태로 그냥 PR합니다 ㅠㅠ
 */

class Work{
	int num;
	int time;
	Queue<Integer> out = new ArrayDeque<Integer>(); // 해당 vertex가 진출하는 vertex
	Queue<Integer> in = new ArrayDeque<Integer>(); // 해당 vertex로 진입하는 vertex
	
	Work(int num){
		this.num = num;
	}
}

public class BaekOJ2056_배문규 {
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
				works[out].out.offer(in); // 진출 => 진입 edge
				works[in].in.offer(out); // 진입 <= 진출 edge
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
			Work work = queue.poll(); // 진입 edge가 없는 vertex들, dp값 확정
			
			while(!work.out.isEmpty()) { 
				int out = work.out.poll(); // 해당 vertex가 진출하여 도착한 vertex들
				while(!works[out].in.isEmpty()) { 
					int in =  dp[works[out].in.poll()]; // 도착한 vertex로 진출하는 vertex들의 확정 dp값
					// 자신으로 오는 vertex들 중 시간이 가장 오래 걸리는 vertex를 선택하여 자신의 시간을 더해 자신의 dp값 갱신
					dp[out] = dp[out] < works[out].time + in ? dp[out] = works[out].time + in : dp[out];
				}
				queue.offer(works[out]); // 자신에게 진입하는 edge를 다 끊고 dp값을 확정지은 뒤 queue에 enqueue
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
