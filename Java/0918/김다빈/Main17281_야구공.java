import java.io.*;
import java.util.*;

/**
 * 백준 17281번 야구공(이모티콘)
 * 
 * 풀이 : 순열 + 구현
 * 
 * '순열'로 미리 내정된 4번 타자를 제외한 타순을 결정한다.
 * 결정된 타순으로 모든 이닝의 점수를 합산한 후 최댓값 업데이트! 
 * 
 * 78892KB	536ms
 */
public class Main17281_야구공 {

	static int N, max = Integer.MIN_VALUE;
	static int[][] input;
	static boolean[] visited = new boolean[10];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		input = new int[N][10]; 
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] order = new int[10];
		order[4] = 1;	// 4번 타자는 1번 선수로 내정
		perm(1, order);	// 1번 타자부터 타순 결정 (순열)
		
		System.out.println(max);
	}

	// 타순을 정하는 순열 함수 
	private static void perm(int r, int[] order) {
		if(r == 10) {	// 모든 타순이 정해졌다면 게임 점수 계산 
			calcScore(order);
			return;
		}
		
		if(r == 4) {	// 4번 타자는 이미 정해졌기 때문에 패스 
			perm(r+1, order);
			return;
		}
		
		for(int i = 2; i <= 9; i++) {	// 1번 타자를 제외한 나머지 타자들 타순 결정
			if(visited[i]) continue;
			visited[i] = true;
			order[r] = i;
			perm(r+1, order);
			visited[i] = false;
		}
	}

	// 정해진 타순으로 모든 이닝의 점수를 계산하는 함수 
	private static void calcScore(int[] order) {	
		int curHitter = 1;	// 1번 타자부터 시작 
		int result = 0;
		
		for (int inning = 0; inning < N; inning++) {
			int out = 0;
			int[] curPos = new int[4];
			
			while(out < 3) {	// 삼진아웃 되기 전까지 이닝 진행  
				int curHit = input[inning][order[curHitter]];
				
				if(curHit != 0) {	// 안타: 1, 2루타: 2, 3루타: 3, 홈런: 4
					// 진루했던 타자들 이동 
					for (int i = 3; i > 0; i--) {	// 역순으로 체크 (3루, 2루, 1루) 
						if(curPos[i] != 0) {
							curPos[i] = 0;		// 기존 자리 비우기 
							
							if(i+curHit > 3) {	// 홈을 밟을 수 있으면 점수 획득 
								result++;
							} else {			// 타자 이동 
								curPos[i+curHit]++;
							}
						}	
					}
					
					// 현재 타자 이동 
					if(curHit > 3) {	// 홈런이면 점수 획득 
						result++;
					} else {			// 타자 이동 
						curPos[curHit]++;
					}
				} else {	// 아웃인 경우 
					out++;
				}
				
				// 다음 타자로 변경 
				curHitter++;
				if(curHitter >= 10) {	// 9번 타자가 친 후, 1번 타자로 타순 돌아옴 
					curHitter = 1;
				}
			}
		}
		
		// 점수 최댓값 업데이트 
		max = Math.max(max, result);
	}

}
