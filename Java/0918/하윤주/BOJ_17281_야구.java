import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0925] BOJ 17281 야구
 *  완전탐색, 순열, 넥퍼, 구현
 * 
 * sol)
 * 넥퍼를 이용해 모든 가능한 타순을 구하고, 각 타순 별 득점이 가장 많은 경우를 찾음
 * 
 * 이때, 1번 타자는 무조건 4번째 순서가 되어야하므로 넥퍼는 (전체 인원수-1)만큼으로 구하고 1번 타자를 중간에 끼워 넣어야함
 * 
 * 시행착오)
 * 아웃이 되지 않은 채 9명의 선수의 한 턴이 끝나면 다시 1번 선수로 순서가 돌아가야 함. 타자 순서 계산이 까다로웠다.
 * 또한 현재 이닝이 종료되어도 현재 순서였던 선수 다음 선수로 순서가 이어져서 넘어가야 함.
 * 아웃 체크를 각 선수 순서가 끝날 때마다 해줘야한다.
 * 
 */
public class BOJ_17281_야구 {
	static int N, max;
	static int[] players = {1, 2, 3, 4, 5, 6, 7, 8};    // 1번(인덱스 0) 선수를 뺀 선수들
	static int[] base;									// 1~3루 진출 상태 및 홈, 아웃 상태 정보
	static int[][] scores;								// 각 선수별 점수(입력)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 각 선수 별 득점 가능한 점수 입력 초기화
		scores = new int[N][9];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<9; j++) scores[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 넥퍼를 이용해 각 타순 별 득점을 계산
		do {
			int inning = 0;		   // 현재 이닝
			int curPlayer = 0;     // 현재 플레이어 순서
			base = new int[6];     // 0:타석, 1~3:베이스, 4:홈, 5:아웃
			
			LOOP : while(inning<N) {         // 이닝이 종료될 때까지 반복
				while (curPlayer%9<3) {      // 1~3번 순서의 타자
					// 현재 선수의 득점을 계산
					moveBase(scores[inning][players[curPlayer%9]]);
					curPlayer++;             // 다음 선수로
					// 3아웃이면 및 베이스 상태 초기화하여 다음 이닝으로 넘어감
					if (base[5]>=3) {
						inning++;
						base[1] = base[2] = base[3] = base[5] = 0;
						continue LOOP;
					}
				}
				
				if (curPlayer%9==3) {       // 4번 순서의 타자(인덱스 0 타자)
					// 현재 선수의 득점을 계산
					moveBase(scores[inning][0]);
					curPlayer++;            // 다음 선수로
					// 3아웃이면 및 베이스 상태 초기화하여 다음 이닝으로 넘어감
					if (base[5]>=3) {
						inning++;
						base[1] = base[2] = base[3] = base[5] = 0;
						continue LOOP;
					}
				}
				
				while (curPlayer%9>3) {     // 5~9번 순서의 타자
					// 현재 선수의 득점을 계산
					moveBase(scores[inning][players[(curPlayer-1)%9]]);
					curPlayer++;            // 다음 선수로
					// 3아웃이면 및 베이스 상태 초기화하여 다음 이닝으로 넘어감
					if (base[5]>=3) {
						inning++;
						base[1] = base[2] = base[3] = base[5] = 0;
						continue LOOP;
					}
				}
			}
			max = Math.max(max, base[4]);     // 최대 득점 갱신
			
		} while (nextPermutation());
		
		System.out.println(max);
		
	}
	
	// 각 선수 별 베이스 상태 기록 및 득점 계산
	private static void moveBase(int b) {
		switch (b) {
		case 1: case 2: case 3:    	// 안타, 2루타, 3루타
			base[0] = 1;				      // 홈에 주자 진출
			// 안타, 2루타, 3루타에 따라 1~3루에 위치한 선수들을 이동시킴
			for (int i=3; i>=0; i--) {
				if (base[i]==1) {			  // 현재 베이스에 선수 있으면
					base[i] = 0;			  // 다음 베이스로 이동시키거나 득점으로 계산
					if (i+b >=4) base[4]++;   // 3까지가 3루이므로 3을 넘어서서 이동할 경우 득점으로 계산
					else base[i+b] = 1;		  // 3을 넘어서지 않으면 다음 베이스로 이동
				}
			}
			break;
		case 0:						// 아웃
			base[5]++;
			break;
		case 4:						// 홈런
			// 1~3루에 위치한 선수들을 모두 득점으로 계산
			for (int i=1; i<=3; i++) {
				if (base[i]==1) {
					base[4]++;
					base[i] = 0;
				}
			}
			base[4]++;						  // 현재 타자도 득점으로 계산
			break;
		}
	}
	
	// 넥퍼로 모든 타순 계산
	private static boolean nextPermutation() {
		int top = 7;
		while(top>0 && players[top-1]>=players[top]) top--;
		
		if (top==0) return false;
		
		int target = 7;
		while(players[top-1]>=players[target]) target--;
		
		swap(top-1, target);
		
		int bottom = 7;
		while(bottom>top) swap(top++, bottom--);
		
		return true;
	}
	
	// 넥퍼를 위한 스왑
	private static void swap(int a, int b) {
		int tmp = players[a];
		players[a] = players[b];
		players[b] = tmp;
	}

}
