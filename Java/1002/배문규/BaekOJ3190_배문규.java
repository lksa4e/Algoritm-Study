package BaekOJ.study.date1002;

import java.io.*;
import java.util.*;

/*
 * 다른건 모르겠고 꼬리처리와 방향전환이 주요했던 문제
 * 꼬리처리는 계속 큐에 head를 Enqueue하고, 뱀이 사과를 먹지 않으면 Dequeue 해줘서 0으로 처리해주었다
 *
 * 방향전환은 최대 X 값 사이즈 만큼 배열을 선언해두고 X를 인덱스로 두고 C가 L이면 1로 저장, D면 3으로 저장하였다.
 * 나중에 해당 값만큼 delta 인덱스를 더해주고 %4 해줘서 방향을 컨트롤 해주었음
 * 
 * 메모리 	시간
 * 14352	144
 */

public class BaekOJ3190_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, map[][], K, appleI, appleJ, L, snake[];
	static int delta[][] = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	static Queue<Integer> tail = new ArrayDeque<Integer>(); // 꼬리처리용 큐
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		// 패딩(뱀, 벽 모두 2)
		map = new int[N+2][N+2];
		for(int i = 0; i < N+2; i++) {
			for(int j = 0; j < N+2; j++) {
				if(i == 0 || j == 0 || i == N+1 || j == N+1) map[i][j] = 2;
			}
		}
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			appleI = Integer.parseInt(st.nextToken());
			appleJ = Integer.parseInt(st.nextToken());
			map[appleI][appleJ] = 1; // 사과는 1
		}
		
		snake = new int[10001]; // time의 최대 입력값인 10000만큼 미리 배열 선언
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			
			if(st.nextToken().equals("L")) snake[time] = 1; // L이면 1, D면 3 저장
			else snake[time] = 3;
		}
		
		System.out.println(play());
	}
	
	public static int play() {
		int time = 0, state = 0;
		
		int headI = 1, headJ = 1;
		map[headI][headJ] = 2;
		tail.offer(headI);
		tail.offer(headJ);
		
		while(true){
			headI += delta[state][0];
			headJ += delta[state][1];
			time++;
			
			if(map[headI][headJ] == 2) break; // 벽이거나, 몸통이면 종료
			
			if(map[headI][headJ] != 1) map[tail.poll()][tail.poll()] = 0; // 사과를 먹지 않았다면 꼬리 이동
			
			// 뱀이 이동하고 머리부분을 큐에 Enqueue
			map[headI][headJ] = 2;
			tail.offer(headI);
			tail.offer(headJ);
			
			// 시간에 따른 방향 전환
			state = (state + snake[time])%4;
		}
		return time;
	}
}
