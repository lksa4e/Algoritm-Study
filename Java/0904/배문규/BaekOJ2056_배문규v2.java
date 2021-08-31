package BaekOJ.study.date0904;

import java.io.*;
import java.util.*;

/*
 * 진입, 진출을 따로 관리하지 않고 link를 두어서 진출 => 진입 방향으로 링크를 해주었음
 * 해당 vertex에 link는 진출에 대한 정보라 따로 enterCnt라는 해당  vertex로 진입하는 vertex의 수를 따로 관리함
 * 
 * 메모리 		시간
 * 72128	752
 */

class Work{
	int num;
	int time;
	int enterCnt; // 해당 vertex로 진입하는 vertex 수
	Queue<Work> link = new ArrayDeque<Work>(); // 해당 vertex가 진출하는 vertex
	
	Work(int num){
		this.num = num;
	}
}

public class BaekOJ2056_배문규v2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, dp[], _max = Integer.MIN_VALUE;;
	static Work[] works;
	static Queue<Work> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {

		N = Integer.parseInt(br.readLine());
		works = new Work[N+1];
		for(int i = 1; i <= N; i++) works[i] = new Work(i);
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 해당 vertex의 웨이트와 진입차수 저장 
			works[i].time = Integer.parseInt(st.nextToken());
			works[i].enterCnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j < works[i].enterCnt; j++) {
				int link = Integer.parseInt(st.nextToken());
				works[link].link.offer(works[i]);
				// 진출.link => 진입으로 저장
			}
		}
		
		dp = new int[N+1];
		queue = new ArrayDeque<Work>();
		for(int i = 1; i <= N; i++) {
			if(works[i].enterCnt == 0) { // 진입  vertex가 없으면 dp값 확정 후 enqueue
				dp[works[i].num] += works[i].time;
				queue.offer(works[i]);
			}
		}
		
		while(!queue.isEmpty()) {
			Work work = queue.poll();
			// 해당 vertex가 진출하는 vertex를 뽑아서 dp값 비교 후 갱신
			while(!work.link.isEmpty()){
				Work next = work.link.poll();
				dp[next.num] = dp[next.num] < works[next.num].time + dp[work.num] ? works[next.num].time + dp[work.num] : dp[next.num];
				if(--next.enterCnt == 0) queue.offer(next); // 더 이상 진입하는 vertex가 없으면 enqueue
			}
		}
		
		// 작업시간 구하기
		for(int i = 1; i <= N; i++) if(_max <= dp[i]) _max = dp[i];
		
		System.out.println(_max);
	}
	
}
