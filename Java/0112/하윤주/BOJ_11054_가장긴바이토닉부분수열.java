import java.io.*;
import java.util.*;

/**
 * [0112] BOJ 11054 가장 긴 바이토닉 부분 수열
 * DP
 * 
 * sol)
 * 수열의 인덱스의 오름차순과 내림차순에 대해 각각 가장 긴 증가하는 부분수열 배열을 만든다.
 * 두 배열의 같은 인덱스의 값을 더한 것이 최대가 될 때 답이 됨
 * 
 * 가장 긴 증가하는 부분수열은 현재 인덱스 위치의 수를 기준으로 해당 수보다 작은 수들의 가장 긴 증가하는 부분수열 길이 중 최대 값보다 1 크도록 설정한다
 *
 */

public class BOJ_11054_가장긴바이토닉부분수열 {
	static int N, limitNum, maxLen;
	static int[] A, cntSmaller, ascending, descending;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		// 수열 입력
		limitNum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			limitNum = Math.max(limitNum, A[i]);       // 수열의 수 중 가장 큰 수를 찾아 해당 길이만큼의 부분 수열 길이를 저장할 배열 생성한다
		}
		
		ascending = new int[N];      // 인덱스 오름차순
		descending = new int[N];     // 인덱스 내림차순
		// 오름차순과 내림차순 기준으로 탐색하며 가장 긴 증가하는 부분수열 길이를 구하고
		getLongestAscending();
		getLongestDescending();
		// 두 값을 더한 것이 최대가 될 때를 찾음
		getLongestBitonicSubsequence();
		System.out.println(maxLen-1);      // 자기 자신은 오름차순과 내림차순에서 중복적으로 카운트되므로 -1
		
	}

	// 오름차순 기준 가장 긴 증가하는 부분수열 찾기
	private static void getLongestAscending() {
		// 수열 최댓값 기준으로 배열 생성(현재 수까지 증가하는 부분 수열 개수를 저장)
		cntSmaller = new int[limitNum+1];
		
		for (int i=0; i<N; i++) {
			int curNum = A[i];
			int beforeMax = 0;
			// 수열 현재 수보다 작은 수의 등장 횟수를 세어 최댓값인 경우가 자신까지 증가하는 부분 수열 개수가 됨
			for (int j=curNum-1; j>=1; j--) {
				beforeMax = Math.max(beforeMax, cntSmaller[j]);
			}
			// 자신까지를 반영하여 배열로 완성
			ascending[i] = ++beforeMax;
			cntSmaller[curNum] = beforeMax;
		}
	}
	
	// 내림차순 기준 가장 긴 증가하는 부분수열 찾기
	private static void getLongestDescending() {
		cntSmaller = new int[limitNum+1];
		for (int i=N-1; i>=0; i--) {
			int curNum = A[i];
			int beforeMax = 0;
			for (int j=curNum-1; j>=1; j--) {
				beforeMax = Math.max(beforeMax, cntSmaller[j]);
			}
			descending[i] = ++beforeMax;
			cntSmaller[curNum] = beforeMax;
		}
	}
	
	// 오름차순과 내림차순 기준을 배열을 더해 최대가 되는 값을 찾음
	private static void getLongestBitonicSubsequence() {
		for (int i=0; i<N; i++) maxLen = Math.max(maxLen, ascending[i]+descending[i]);
	}

}
