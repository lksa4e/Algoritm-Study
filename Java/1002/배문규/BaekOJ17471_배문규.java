package BaekOJ.study.date1002;

import java.io.*;
import java.util.*;

/*
 * 문제를 접근한 방식은 아래와 같음
 * 1. 재귀 방식으로 도시들을 섹터1, 섹터2로 구분
 * 2. 큐로 각 섹터들이 서로 연결되어있는지 체크
 * 3. 서로 연결되어 있다면 해당 섹터들의 인구수들을 각각 합하여 차이를 구함
 * 
 * 문제를 읽고 접근하는 방법은 대충 이렇게 하면 되겠다 라고 생각은 금방 들었는데
 * 버그없이 디테일하게 구현하기까지는 은근 까다로웠음
 * 
 * 메모리 	시간
 * 14228	132
 */

public class BaekOJ17471_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, size, gap, population[], link[][], sector[], temp[], result = Integer.MAX_VALUE;
	static Queue<Integer> queue = new ArrayDeque<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		// 각 도시 별 인구 입력
		population = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) population[i] = Integer.parseInt(st.nextToken());
		
		// 각 도시의 인접 도시 입력
		link = new int[N+1][];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			link[i] = new int[size];
			for(int s = 0; s < size; s++) link[i][s] = Integer.parseInt(st.nextToken());
		}
		
		// 도시를 2구역으로 나눌 배열
		sector = new int[N+1];
		Arrays.fill(sector, 2); // 미리 전부 섹터2로 초기화
		temp = new int[N+1]; // 부분집합이 완성되었을 때 섹터별 연결 체크를 확인 할 임시 배열
		
		getResult(1);
		
		// 두 선거구로 나눌 수 없는 경우 -1 출력
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	public static boolean isLinked(int[] city, int division) {
		queue.clear();
		// 도시들 중 해당 division을 찾아서 -처리해서 체크표시하고 큐에 Enqueue
		for(int c = 1; c <= N; c++) {
			if(city[c] == division) {
				city[c] = -division;
				queue.offer(c);
				break;
			}
		}
		
		// 같은 division인 도시들이 서로 연결되어 있다면 -처리하는 방식으로 같은 division 체크
		while(!queue.isEmpty()) {
			int n = queue.poll();
			for(int c : link[n]) {
				if(city[c] == division) {
					city[c] = -division;
					queue.offer(c);
				}
			}
		}
		
		// 만약 해당 디비전에서 -처리가 되어있지 않은 도시가 있다면 연결되어있지 않은 것이므로  false 리턴
		for(int i = 1; i <= N; i++) {
			if(city[i] == division) return false;
		}

		return true;
	}
	
	public static void getResult(int idx) {
		// 섹터1, 섹터2의 인구수를 담을 배열
		int sector1 = 0;
		int sector2 = 0;
		
		// 섹터가 모두 구분이 되면
		if(idx == N) {
			// 하나의 섹터로 모두 몰리면 리턴
			int cnt = 0;
			for(int i = 1; i <= N; i++) if(sector[i] == 1) cnt++;
			if(cnt == N || cnt == 0) return;
			
			// 배열을 복사하고 각 섹터들이 서로 연결되어있는지 확인
			copyArray();
			if(isLinked(temp, 1) && isLinked(temp, 2)) {
				// 1번섹터 인구, 2번섹터 인구수 각각 더하기
				for(int i = 1; i <= N; i++) {
					if(sector[i] == 1) sector1 += population[i];
					else sector2 += population[i];
				}
				// 두 섹터의 최소 인구수 차이 구하기
				result = Math.min(result, Math.abs(sector1 - sector2));
			}
			return;
		}
		
		// 재귀 방식으로 섹터 구분
		sector[idx] = 1;
		getResult(idx+1);
		sector[idx] = 2;
		getResult(idx+1);
	}
	
	// 배열 복사
	public static void copyArray() {
		for(int i = 1; i <= N; i++) temp[i] = sector[i];
	}
}
