package BaekOJ.study.date1113;

import java.io.*;
import java.util.*;

/*
 * 백준 20055 컨베이어 벨트 위의 로봇
 * 
 * 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
 * 2.1 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
 * 2.2 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
 * 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다. (조건 충족하면 계속 올림) 
 * 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
 * 
 * 도대체 문제의 요구사항이 무슨말인지 알아먹기가 너무 힘들었음
 * 상태 : 로봇이 처음에 하나도 안올려져있는 상태. 빈상태로 1,2번 조건 시행 후 3번에서 첫 로봇 받음
 * 이동 : 1번에서 먼저 컨베이어벨트로 이동 + 2번에서 로봇들이 조건 보고 자체 이동
 * 내구도 : 로봇이 자체 이동 + 올리는 곳에서 감소.
 * 
 * 메모리 	시간
 * 17636	300
 * 
 */
public class BaekOJ20055_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, K, cnt, belt[];
	static boolean robotIdx[]; // i번째 컨베이어 벨트 위에 로봇이 있으면 있으면 해당 인덱스 true 

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 내구도
		
		belt = new int[2*N];
		robotIdx = new boolean[2*N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2*N; i++) belt[i] = Integer.parseInt(st.nextToken());
		
		while(true) {
			cnt++;
			step();
		}
	}
	
	public static void step() {
		// 1번 조건 : 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
		int temp = belt[2*N-1];
		for(int i = 2*N-2; i >= 0; i--) {
			belt[i+1] = belt[i];
			robotIdx[i+1] = robotIdx[i];
			if(i+1 == N-1 && robotIdx[i+1]) robotIdx[i+1] = false; // 로봇 내림
		}
		belt[0] = temp;
		robotIdx[0] = false;
		
		// 2번 조건 : 가장 먼저 벨트에 올라간 로봇부터 이동
		for(int i = N-2; i >= 0; i--) {
			// 만약 로봇이 있는데 앞에 칸이 내구도가 0보다 크다면
			if(robotIdx[i] && belt[i+1] > 0) {
				// 내리는 위치로 이동한다면
				if(i+1 == N-1) move(i); 
				// 앞에 로봇이 없다면
				else if(!robotIdx[i+1]){ 
					robotIdx[i+1] = robotIdx[i];
					move(i);
				}
			}
		}
		
		// 3번 조건 : 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올림
		if(belt[0] > 0) {
			robotIdx[0] = true;
			belt[0]--;
			if(belt[0] == 0) K--;
			endCheck();
		}
	}
	
	public static void move(int i) {
		robotIdx[i] = false;
		belt[i+1]--; // 내구도 -1
		if(belt[i+1] == 0) K--; // 만약 내구도가 0이 되면 카운트
		endCheck();
	}

	// 4번 조건 : 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
	public static void endCheck() {
		if(K == 0) {
			System.out.println(cnt);
			System.exit(0);
		}
	}
}
