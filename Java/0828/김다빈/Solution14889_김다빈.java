import java.util.*;
import java.io.*;

/**
 * 백준 14889번 스타트와 링크 
 * 
 * 풀이 : 조합 + 재귀 
 * 
 * 스타트 팀 멤버가 되는 경우의 수를 조합으로 계산 (nCn/2)
 * n/2번 재귀를 돌면 모든 멤버를 선택한 것이므로 스타트와 링크의 시너지 차 계산, 최솟값 갱신 
 * 
 * 만약 최솟값이 0이면 더이상 계산할 필요가 없으므로 return
 * 
 * 18636KB	320ms
 */
public class Solution14889_김다빈 {

	static int N, min=Integer.MAX_VALUE;
	static int[][] synergy;
	static boolean[] startTeam;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		synergy = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				synergy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// NC(N/2)로 스타트 멤버 선택, 나머지는 링크 팀 
		startTeam = new boolean[N];
		combination(0,0);
		
		System.out.println(min);
	}

	private static void combination(int s, int r) {
		// 더이상 최솟값을 갱신할 필요가 없으니 return 
		if(min == 0) return;
		
		if(r==N/2) {
			calcSynergy();	// 각 팀의 시너지 계산 
			return;
		}
		
		for (int i = s; i < N; i++) {
			if(!startTeam[i]) {
				startTeam[i] = true;
				combination(i+1, r+1);
				startTeam[i] = false;
			}
		}
	}

	private static void calcSynergy() {
		int start = 0, link = 0;	// 각 팀의 시너지의 합 
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(startTeam[i] && startTeam[j]) {	// 스타트팀인 경우 
					start += synergy[i][j];
				} else if(!startTeam[i] && !startTeam[j]) {	// 링크팀인 경우 
					link += synergy[i][j];
				}
			}
		}
		
		// 최솟값 갱신 
		min = Math.min(min, Math.abs(start-link));
	}
	
}
