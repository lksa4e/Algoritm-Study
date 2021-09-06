package BaekOJ.study.date0911;

import java.io.*;
import java.util.*;

/*
 * 가장 기초적인 플로이드 워셜 문제
 * 이 문제에서 A와 B가 친구라는 조건은, 가중치 없는 무뱡향 그래프라고 볼 수 있다.
 * 
 * 그래서 만약 A와 B가 연결되어 있지 않으면, INF로 초기화하고
 * 연결되어 있으면, map[A][B] = 1, map[B][A] = 1로 인접 그래프를 만들어 주면 된다.
 * 
 * 그다음 플로이드 워셜 알고리즘을 수행한 뒤,
 * 행 별로 누적합을 구해서 최솟값을 찾아서 출력하면 된다.
 * 
 * 메모리 	시간
 * 14080	136
 */

public class BaekOJ1389_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int V, E, INF = Integer.MAX_VALUE;
	static long map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// 맵 입력
		setMap();
		
		// 플로이드 워셜 알고리즘
		for(int k = 0; k < V; k++) {
			for(int i = 0; i < V; i++) {
				for(int j = 0; j < V; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		System.out.println(getResult(map));
	}
	
	public static void setMap() throws IOException {
		map = new long[V][V];
		for(int i = 0; i < V; i++) {
			for(int j = 0; j < V; j++) {
				if(i == j) continue;
				map[i][j] = INF;
			}
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			// 무방향 무가중치 그래프
			map[A][B] = 1;
			map[B][A] = 1;
		}
	}
	
	public static int getResult(long[][] map) {
		int resultIdx = 0;
		int result = INF;
		
		for(int i = 0; i < map.length; i++) {
			int temp = 0;
			for(int j = 0; j < map[0].length; j++) temp += map[i][j]; // 행 누적합
			
			// 작아야만 갱신
			// 같은 경우는 인덱스 작은수가 유지
			if(temp < result) {
				result = temp;
				resultIdx = i+1;
			}
		}
		
		return resultIdx;
	}
}