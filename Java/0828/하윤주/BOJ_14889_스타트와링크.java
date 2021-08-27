import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * [0828] 백준 14889 스타트와 링크
 * 조합, 2차원 배열
 * 
 * sol)
 * N개 중 N/2개짜리 조합을 만들고 각 조합 별 능력치의 합을 구한 뒤 최솟값 도출
 * 재귀를 통한 조합 구현
 * 2개의 팀을 만들기 위해 원소가 N/2개짜리 조합 경우의 수를 덱에 담고,
 * 덱의 맨 앞과 맨 뒤를 비교하면 맨 앞과 맨 뒤가 각각 스타트팀과 링크팀으로 짝을 이룸
 * ex) {0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {2, 3}
 * 	4명의 사람으로 2명이 인원인 팀을 짜면 위와 같은 경우의 수가 존재하고, 이를 절반으로 잘라 대칭으로 비교하면 2팀으로 쪼개는 경우가 됨
 * 
 * tc)
 * 조합 : NCN/2
 * 능력치 합 계산 : 각 조합에 대해 N번 연산
 * 최솟값 비교 : N 
 * 
 *	20C10 * N + N
 *
 * N이 커지면 조합이 어려워질 것 같음
 *	
 */

public class BOJ_14889_스타트와링크 {	
	static int N, minGap = Integer.MAX_VALUE;
	static int[][] stat;      // 능력치 배열
	static int[] team;        // 한가지 팀 조합
	static Deque<Integer> statCombi = new ArrayDeque<Integer>();    // 가능한 모든 팀 조합 경우를 담은 덱
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 능력치 배열 생성
		stat = new int[N][N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) stat[i][j] = Integer.parseInt(st.nextToken());
		}
		
		team = new int[N/2];    // 재귀를 통한 조합 생성시 각 조합 경우가 담길 배열
		
		// 조합으로 팀짜기
		combination(0, 0);
		// 최소한의 능력치 차이 팀을 도출
		calcMinGap();
		
		System.out.println(minGap);
		
	}

	// 재귀를 통해 팀 조합을 짜는 메서드
	private static void combination(int depth, int start) {
		// 기저조건 : 팀 인원이 N/2로 가득 차면
		if (depth == N/2) {
			calcStatSum();    // 해당 팀의 능력치를 계산
			return;
		}
		
		// 유도파트 : 팀 짜기
		for(int i=start; i<N; i++) {
			team[depth] = i;
			
			combination(depth+1, i+1);
		}
		
	}

	// 각 팀 조합의 능력치 합을 계산하는 메서드
	private static void calcStatSum() {
		int total = 0;
		
		for (int i=0; i<team.length; i++) {
			for (int j=0; j<team.length; j++) {
				if (i != j) total += stat[team[i]][team[j]];   // 능력치 배열에서 팀 조합의 능력치를 산출하여 합산
			}
		}
		
		statCombi.offerLast(total);     // 각 팀조합의 능력치 합은 덱의 맨 뒷단에 차곡차곡 저장
	}

	// 두 팀의 능력치 차가 최소인 경우 찾는 메서드
	private static void calcMinGap() {
		// 덱의 맨 앞 원소와 맨 뒤 원소를 빼내어 나온 두 팀은 N명을 N/2명으로 쪼개어 팀을 짜는 한 경우가 됨
		while(!statCombi.isEmpty()) {
		
			int curGap = Math.abs(statCombi.pollFirst() - statCombi.pollLast());   // 두 팀의 능력치 차이 계산
			minGap = Math.min(minGap, curGap);     // 최솟값 갱신
		}
		
	}
	
}
