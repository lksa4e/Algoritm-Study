import java.io.*;
import java.util.*;

/**
 * G5 BOJ 17281 ⚾ : 
 * 메모리 : 19420kb 시간 : 508ms
 * 
 * 최적의 경우의 수를 찾는 문제 -> 모든 경우의 수를 전부 고려하여 최대값을 구한다.
 * 야구선수를 배치하는 경우의 수 -> 1번 타자를 제외한 나머지 8명 -> 8!
 * 
 * 순열을 통해 야구선수 배치를 끝냈다면 이후엔 단순한 구현 문제(문제 이해가 제일 어려웠음)
 */

public class BOJ17281_G4_야구공 {
	static int N, answer = 0, game[][], player[] = new int[9], onboard[] = new int[3];
	static boolean visit[] = new boolean[9];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine()); // 이닝 수
		game = new int[N][9];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				game[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		player[3] = 0;  // 4번 타자는 1번 선수로 고정
		visit[0] = true;  // 1번 선수는 순열 고려대상에서 제외
		set_player(0);
		System.out.println(answer);
	}
	static void set_player(int cnt) {
		// 4번 타자는 이미 1번 선수로 정해졌으므로 skip
		if(cnt == 3) {           
			set_player(cnt + 1);
			return;
		}
		// 9명의 야구선수 순서를 정했다면 게임 진행
		if(cnt == 9) {   
			play();
			return;
		}
		for(int i = 0; i < 9; i++) {
			if(!visit[i]) {
				visit[i] = true;
				player[cnt] = i;
				set_player(cnt + 1);
				visit[i] = false;
			}
		}
	}
	static void play() {
		// num - 현재 타석에 들어선 타자 번호
		// sum - 점수 합계
		int num = 0, sum = 0;
		for(int i = 0; i < N; i++) {
			int out = 0;
			// 1,2,3 루수 초기화시킴
			Arrays.fill(onboard, 0);
			while(out < 3) {
				// 현재 타자가 어떤걸 쳤는지? (안타 || 2루타 || ....)
				switch(game[i][player[num]]) { 
				case 0: out++; break;
				case 1: sum += swift(1); break;
				case 2: sum += swift(2); break;
				case 3: sum += swift(3); break;
				case 4: sum += (swift(4) + 1); Arrays.fill(onboard, 0); break;
				}
				num++;
				if(num == 9) num = 0;
			}
		}
		answer = Math.max(answer, sum);
	}
	// n루타인 경우 n번만큼 진루하는 함수, 획득 점수를 return 함
	static int swift(int point) {
		int sum = 0;
		for(int i = 0; i < point; i++) {
			if(onboard[2] == 1) sum++;
			onboard[2] = onboard[1];
			onboard[1] = onboard[0];
			onboard[0] = 0;
		}
		if(point != 4) onboard[point - 1] = 1; // n루타인 경우에 n루 자리에 선수 배치 (홈런은 제외)
		return sum;
	}
}
