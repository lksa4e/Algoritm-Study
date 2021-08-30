import java.io.*;
import java.util.*;

public class Main2056 {
	/*
	 * 선행단계가 2개 이상일때 각각의 작업시간을 고려해야 한다
	 * 다음 작업 끝나는 시간 = 현재 작업 끝나는 시간 + 다음 작업 소요시간 
	 * 선행 작업이 2개이상이면 최댓값으로 작업 끝나는 시간 갱신해줘야 함
	 * 
	 * 시행착오 : 
	 * 1. 2개 이상의 선행단계가 있는데 하나만 고려했음
	 * 2. 맨 마지막 작업이 시간이 제일 많이 소요됐을 거란 착각..
	 * 
	 * 반례
5
6 0
3 0
3 2 1 2
1 1 1
1 1 4

9

5
6 0
3 0
3 1 2
1 2 1 2
1 2 3 4

8

5
6 0
3 0
3 1 2
1 2 1 2
1 1 3

7

5
6 0
3 0
3 2 1 2
1 1 1
1 2 3 4

10

     * 
	 */
	
	static int N, M;
	static List<Integer>[] graph;
	static int[] inDegree, endTime, workTime;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		inDegree = new int[N+1];
		endTime = new int[N+1]; // 작업 끝나는 시간
		workTime = new int[N+1]; // 작업 소요 시간
		graph = new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) graph[i] = new ArrayList<Integer>();
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			workTime[i] = endTime[i] = Integer.parseInt(st.nextToken());
			int relation = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<relation; j++) {
				int num = Integer.parseInt(st.nextToken());
				graph[num].add(i);
			}
			
			inDegree[i] = relation;
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		for(int i=1; i<=N; i++) if(inDegree[i] == 0) q.offer(i);
		
		for(int i=1; i<=N; i++) {
			int x = q.poll();
			for(int j : graph[x]) {
				endTime[j] = Math.max(endTime[j], endTime[x]+workTime[j]); // 다음 작업 끝나는 시간 = 현재 작업 끝나는 시간 + 다음 작업시간 
				if(--inDegree[j] == 0) q.offer(j);
			}
		}
		
		Arrays.sort(endTime); // 최댓값(작업완료 시간) 출력 
		System.out.println(endTime[N]);
	}
}