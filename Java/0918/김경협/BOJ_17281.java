import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 17281번 ⚾
 * 
 * 이닝마다 선수들의 결과가 정해져 있고
 * 가장 최대의 점수를 낼 수 있는 타순을 정하는 문제
 * 
 * 넥퍼로 4번 타자를 제외한 나머지를 돌려 타순을 정한다.
 * 나온 타순으로 시뮬레이션을 돌린다.
 * 
 * 65324KB	580ms
 */

public class BOJ_17281 {
	static int N, resultPerInning[][], maxScore = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1번선수(0 index)는 4번 타자
		
		// 가장 많은 득점을 하는 타순을 찾고, 그 때의 득점을 구하기
		
		// 1~8번 선수들의 순서를 정해야 함
		// 순서가 상관 있으므로 순열 Permutation
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		resultPerInning = new int[N][9];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				resultPerInning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 0번 선수 빼고 np 돌릴 배열
		int[] orderWithoutFourth = new int [8];
		for (int i = 1; i < 9; i++) orderWithoutFourth[i-1] = i;
		
		int[] order = new int[9];
		do {
			// np 돌린 배열과 4번 타자에 0번 선수를 넣은 order배열
			for (int i = 0; i < 9; i++) {
				if(i == 3) continue;
				order[i] = i < 3 ? orderWithoutFourth[i] : orderWithoutFourth[i-1];
			}
			// 현재 타순으로 점수 구하기
			getScore(order);
		} while(np(orderWithoutFourth));
		
		System.out.println(maxScore);
	}
	
	static void getScore(int[] order) {
		int playerIndex = 0, score = 0;
		
		for (int i = 0; i < N; i++) {
			boolean[] base = new boolean[4];	// 홈, 1루, 2루, 3루에 주자가 있는지 저장, 경기마다 루는 초기화 된다.
			int outCnt = 0;
			while(outCnt < 3) {	// out이 3번 나올때까지 계속 경기
				// playerIndex: 타순 인덱스
				// currPlayer: 주어진 타순에 따라 타자로 나올 선수
				int currPlayer = order[playerIndex];
				// index 올려주기
				playerIndex = (playerIndex + 1) % 9;
				
				// 현재 타순으로 선 선수의 결과
				int hit = resultPerInning[i][currPlayer];
				
				// 아웃일 때
				if(hit == 0) {
					outCnt++;
					continue;
				} else {
					// 아웃 아닐 때
					base[0] = true;	// 현재 타자
					for (int j = 3; j >= 0; j--) {
						// 만약 j루에 주자가 있으면 현재 루와 가야할 루를 더해서 홈에 도착하면 점수를 올리기 
						if(base[j]) {	// 주자가 있으면
							base[j] = false;	// 현재 루 비워주고
							if(j+hit < 4)		// 다음 루로 가거나, 3루 이상으로 가면 점수 올리기
								base[j+hit] = true;
							else
								score++;
						}
					} 
				}
				
			}
		}
		maxScore = Math.max(maxScore, score);
	}
	
	static boolean np(int[] arr) {
		int playerNum = 7;
		int i = playerNum;
		while(i > 0 && arr[i-1] >= arr[i]) i--;
		
		if(i == 0) return false;
		
		int j = playerNum;
		while(arr[i-1] >= arr[j]) j--;
		
		swap(arr, i-1, j);
		
		int k = playerNum;
		while(i < k) swap(arr, i++, k--);
		return true;
	}
	
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
