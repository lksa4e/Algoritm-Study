package BaekOJ.study.date0904;

import java.io.*;
import java.util.*;

/*
 * 타임머신으로 시간을 되돌아 가는 경우 (음수 가중치)가 존재하는 최단거리 알고리즘 : 벨만 포드
 * 음수 순환이 존재하면 -INF
 * 음수 순환이 존재하지 않으면 최단거리 구할 수 있음
 * 그래서 음수 순환이 존재하는지 체크해야 함
 * 
 * 출발지부터  V-1개의 vertex를 돌면 마지막 vertex는 자동으로 최단거리가 정해지는데
 * 그래서 마지막 vertex에서 최단거리를 다른 vertex로 최단거리를 체크 했을 때 최단거리가 갱신되면 안됨
 * 갱신이 된다면 음수순환이 존재한다는 의미
 
 * 1. 출발노드 설정
 * 2. 최단거리 초기화
 * 3. V-1번 vertex를 돌아가며 연결되지 않은 모든 edge 까지 다 체크
 * 4. 1번 더 3번 과정을 수행해서 최단거리 갱신
 * 5. 4번과정에서 최단거리가 초기화 되면 음수 순환 존재하는 것임
 *  
 * C(V*E)
 * 
 * 시행착오 : 최단시간 배열인 times를 int형으로 선언하였더니 오버플로우로 출력초과가 뜸
 * 질문 게시판을 보니 int -> long으로 변경해주면 된다길래 변경해주니 해결
 * 
 * 출력초과 예시:
 * 500 500
 * 1 2 -10000
 * 2 1 -10000
 * 위 2개의 입력이 500번 반복
 * 
 * 놀랍게도 int 형이면 위 테스트케이스가
 * -2147470000
 * -1
 * -1
 * -1.... 가 출력되고
 * long형은 그냥 -1이 출력됨
 * 
 * 메모리 	시간
 * 19128	272
 */

class Bus{
	int from;
	int to;
	int time;
	
	Bus(int from, int to, int time) {
		this.from = from;
		this.to = to;
		this.time = time;
	}
}

public class BaekOJ11657_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int V, E;
	static long times[];
	static Bus bus[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		setBus();

		// 최단시간 배열
		// int : 출력초과
		times = new long[V+1];
		Arrays.fill(times, Integer.MAX_VALUE);
		
		// 음수 사이클이 존재하면 -1
		if(hasNegative_Cycle(1)) sb.append("-1");
		// 존재하지 않는다면 각 도시까지의 최소시간 출력
		else { 
			for(int i = 2; i <= V; i++) {
				if(times[i] == Integer.MAX_VALUE) sb.append("-1").append("\n");
				else sb.append(times[i]).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	// E개의 버스 from - to, time 입력
	public static void setBus() throws IOException {
		bus = new Bus[E];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			bus[i] = new Bus(from, to, time);
		}
		
	}
	
	// 벨만 포드 알고리즘
	public static boolean hasNegative_Cycle(int start) {
		times[start] = 0; // 시작지점 초기화
		
		for(int v = 0; v < V; v++) { // V번 반복
			for(int e = 0; e < E; e++) { // 모든  edge 탐색
				int from = bus[e].from;
				int to = bus[e].to;
				int time = bus[e].time;
				
				// 현재 도시까지 오는데 걸리는 시간 INF가 아니면서, 현재 도시를 거쳐 목적지로 가는게 더 시간이 적게걸린다면 갱신
				if(times[from] != Integer.MAX_VALUE && times[to] > times[from] + time) {
					times[to] = times[from] + time;
					if(v == V-1) return true; // 만약 V번째 반복에서도 최소시간이 갱신된다면 음수사이클 존재
				}
			}
		}
		
		return false; // 여기까지 코드가 도달했다면 음수사이클 존재 X
	}
}
