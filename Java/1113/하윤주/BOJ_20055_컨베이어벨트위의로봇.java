import java.io.*;
import java.util.*;

/**
 * [1113] BOJ 20055 컨베이어 벨트 위의 로봇
 *	구현, 시뮬레이션, 배열
 *
 *	sol)
 *	컨베이어 벨트 각 칸에 내구성과 로봇 존재 여부 정보를 모두 담아야하므로 객체를 생성하여 객체 타입 배열을 만듦
 *  이때 객체 타입 배열은 2차원 배열로 할 것 없이 1차원 배열로하고, 한 칸씩 이동할 때 마지막 인덱스만 첫번째 인덱스랑 이어주면 됨
 *  
 *  시행착오)
 *  문제 그림에서와 같이 행이 2개인 2차원 배열로 풀었었는데, 1차원 배열로 쭉 펼치는 것이 훨씬 간단했다.
 *  또, 벨트가 움직이거나 로봇이 이동하여 로봇이 내리는 위치에 도착하면 무조건 내려줘야하는데 이 부분을 빼먹음.
 *  문제가 엄청 어렵진 않았지만 실버까지는 아닌 것 같은데,,,
 *  
 */

public class BOJ_20055_컨베이어벨트위의로봇 {
	static int N, K, NN, UP, DOWN, level=1;
	static Block[] belt;
	
	// 컨베이어 벨트 각 칸 객체
	static class Block {
		int durability;        // 내구성
		boolean robot;         // 로봇 존재 여부
		
		public Block(int durability, boolean robot) {
			super();
			this.durability = durability;
			this.robot = robot;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		NN = N*2;        // 총 컨베이어 벨트 칸 수
		UP = 0;          // 올리는 위치
		DOWN = N-1;      // 내리는 위치
		
		// 컨베이어 벨트 배열
		belt = new Block[NN];
		st = new StringTokenizer(br.readLine());
		// 각 컨베이어 벨트 칸 내구성, 로봇 초기화
		for (int i=0; i<NN; i++) belt[i] = new Block(Integer.parseInt(st.nextToken()), false);
		
		// 벨트 움직이기 시작
		startMove();
		System.out.println(level);
	}

	// 벨트 움직임
	private static void startMove() {
		while(true) {
			step1();
			step2();
			step3();
			if(!step4()) break;       // 내구도 0인 칸이 K개 이상이면 break
			level++;                  // 단계 증가
		}
	}

	// 벨트와 로봇 한 칸씩 이동
	private static void step1() {
		Block temp = belt[NN-1];
		for (int i=NN-1; i>0; i--) belt[i] = belt[i-1];
		belt[0] = temp;
		belt[DOWN].robot = false;        // 벨트 한 칸씩 이동하되 내리는 위치는 로봇 무조건 내림
	}

	// 로봇만 한 칸 이동
	private static void step2() {
		// 내리는 위치 직전부터 올리는 위치까지 역추적(최초로 올라온 로봇부터 이동하기 위해)
		for (int cur=N-2; cur>=0; cur--) {
			// 현재 칸에 로봇이 있고, 바로 다음 칸에 로봇이 없으며, 바로 다음칸 내구성 남아있으면 이동
			if (belt[cur].robot && (!belt[cur+1].robot && belt[cur+1].durability>=1)) {
				belt[cur+1].robot = true;
				belt[cur+1].durability--;
				belt[cur].robot = false;
			}
		}
		belt[DOWN].robot = false;        // 로봇 한 칸씩 이동하되 내리는 위치는 로봇 무조건 내림
	}

	// 올리는 위치에 로봇 올리기
	private static void step3() {
		if (belt[UP].durability>=1) {
			belt[UP].robot = true;
			belt[UP].durability--;
		}
	}

	// 내구도가 0인 칸 개수 카운트
	private static boolean step4() {
		int cnt = 0;
		for (int i=0; i<NN; i++) {
			if (belt[i].durability==0) cnt++;
		}
		return (cnt<K);      // K개 이상이면 false, 미만이면 true
	}

}
