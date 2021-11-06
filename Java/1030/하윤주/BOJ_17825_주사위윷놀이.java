import java.io.*;
import java.util.*;

/**
 * [1026] BOJ 17825 주사위 윷놀이
 * 시뮬레이션, 죽음의 시뮬
 *
 */

public class BOJ_17825_주사위윷놀이 {
	static final int COLOR = 0;      // 0~3번 말이 타고있는 화살표 색상
	static final int INDEX = 1;      // 0~3번 말이 타고있는 화살표 배열에서의 인덱스
	
	static final int RED = 0;        // 화살표 색상이 빨강일때
	static final int BLUEA = 1;      // 화살표 색상이 파랑이고 숫자가 10일때
	static final int BLUEB = 2;      // 화살표 색상이 파랑이고 숫자가 20일때
	static final int BLUEC = 3;      // 화살표 색상이 파랑이고 숫자가 30일때
	
	// 파랑 화살표 배열(빨강 화살표는 (인덱스*2)한 곳이 도착점이므로 따로 배열 만들지 않음)
	static final int[][] BLUEMAP = {
		{},                                    // 빨강 화살표(RED)
		{10, 13, 16, 19, 25, 30, 35, 40},      // 파랑 화살표-10(BLUEA)
		{20, 22, 24, 25, 30, 35, 40},          // 파랑 화살표-20(BLUEB)
		{30, 28, 27, 26, 25, 30, 35, 40}       // 파랑 화살표-30(BLUEC)
	};
	
	static int score;                          // 최고점
	static int[] dices = new int[10];          // 입력으로 받는 주사위
	static int[] combi = new int[10];          // 0~3번 말 인덱스 순열
	static int[][] horses;                     // 0~3번 말의 색상, 인덱스를 저장한 배열
	static boolean[] arrived;                  // 0~3번 말의 도착여부
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		for (int i=0; i<10; i++) dices[i] = Integer.parseInt(st.nextToken());
		
		// 중복 순열 경우의 수 확인
		perm(0);
		System.out.println(score);
	}

	private static void perm(int depth) {
		// 기저조건 : 10번 주사위 던짐
		if (depth==10) {
			horses = new int[4][2];
			arrived = new boolean[4];
			score = Math.max(score, play());
			return;
		}
		
		// 유도파트 : 0~3번 말을 10개 모을때까지 중복순열
		for (int i=0; i<4; i++) {
			combi[depth] = i;
			perm(depth+1);
		}
	}

	private static int play() {
		int total = 0;
		int arrivalCnt = 0;
		
		for (int i=0; i<10; i++) {
			// 모든 말이 도착했으면 숫자판 합 반환
			if (arrivalCnt==4) return total;
			
			int curHorse = combi[i];
			
			// 현재 말이 이미 도착한 상태면 실패
			if (arrived[curHorse]) return 0;
			
			int curColor = horses[curHorse][COLOR];
			int nextIdx = horses[curHorse][INDEX] += dices[i];
			int nextNum = 0;
			
			// 주사위 던진 곳이 도착점이면 다음 주사위로
			if (isArrival(curColor, nextIdx)) {
				arrived[curHorse] = true;
				arrivalCnt++;      // 도착한 말 카운트 증가
				continue;
			}
			
			if (curColor==RED) {
				nextNum = nextIdx * 2;
				// 빨강 화살표 타고왔지만 도착한 곳이 파랑일 경우
				if (nextNum<40 && nextNum%10==0) {
					curColor = horses[curHorse][COLOR] = nextNum/10;
					nextIdx = horses[curHorse][INDEX] = 0;
				}
			} else {
				nextNum = BLUEMAP[curColor][nextIdx];
			}
			
			// 도착한 숫자에 다른 말 있으면 실패
			if(isWithOtherHorse(curHorse, curColor, nextIdx, nextNum)) return 0;
			
			total += nextNum;
		}
		return total;
	}
	
	private static boolean isArrival(int curColor, int nextIdx) {
		switch (curColor) {
		case RED: return (nextIdx>20);                   // 빨강 경로면 20번 인덱스 넘으면 도착
		case BLUEA: case BLUEC: return (nextIdx>7);      // 파랑 경로이고 A, C이면 7번 인덱스 넘으면 도착
		case BLUEB: return (nextIdx>6);                  // 파랑 경로이고 B이면 6번 인덱스 넘으면 도착
		}
		return false;
	}
	
	private static boolean isWithOtherHorse(int curHorse, int curColor, int curIdx, int curNum) {
		for (int otherHorse=0; otherHorse<4; otherHorse++) {
			if (curHorse==otherHorse) continue;                            // 자기 자신은 pass
			if (arrived[otherHorse]) continue;                             // 이미 도착한 말도 pass
			
			int otherColor = horses[otherHorse][COLOR];
			int otherIdx = horses[otherHorse][INDEX];
			
			if (curColor==otherColor && curIdx==otherIdx) return true;     // 자신과 다른 말이 정확히 같은 위치에 있으면 pass
			
			int otherNum = 0;
			if (otherColor==RED) otherNum = otherIdx * 2;
			else otherNum = BLUEMAP[otherColor][otherIdx];
			
			if (curNum==40 && otherNum==40) return true;                   // 색상이 달라도 동시에 40번 숫자판에 있으면 pass
			
			if (curColor!=RED && otherColor!=RED) {
				if(curIdx>=3 && otherIdx>=3 && curNum==otherNum) return true;    // 둘 다 파란색이면 25 이상부터만 확인하여 숫자 같으면 pass
			}
		}
		return false;
	}

}
