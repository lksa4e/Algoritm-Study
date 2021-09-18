import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0918] BOJ 11066 파일합치기
 *  재귀, 메모이제이션
 * 
 * sol)
 * 재귀를 통해 두 부분의 파일을 합칠 수 있는 모든 경우 완전 탐색
 * 단, 이전에 계산한 최소 비용을 기억했다가 동일한 결과는 배열에서 바로 읽어오도록 메모해야함
 * 
 * 근데 아직도 긴가 민가 하네요... 오늘 설명 열심히 듣겠습니다..!
 */

public class BOJ_11066_파일합치기_재귀 {
	static int K;
	static int[] files, sum;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=0; tc<T; tc++) {
			K = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 초기 파일 크기 배열 및 구간합 배열 초기화
			files = new int[K+1];
			sum = new int[K+1];
			
			for (int i=1; i<=K; i++) {
				files[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1]+files[i];
			}
			
			// 최소 비용 저장해둘 배열
			dp = new int[K+1][K+1];
			int min = Integer.MAX_VALUE;
			
			// 전체 부분을 1~k길이씩 두 부분으로 나눠 합칠 때 비용이 최소가 되는 경우를 찾음
			for (int k=1; k<K; k++) {
				int left = combineFiles(1, k);       // 왼쪽 부분
				int right = combineFiles(k+1, K);    // 오른쪽 부분
				min = Math.min(min, left+right);     // 합친 비용이 최소가 될 때를 찾기
			}
			
			System.out.println(min);
		}
		
	}

	// 구간 별 최소 합치는 비용 찾기
	private static int combineFiles(int s, int e) {
		// 이전에 계산하여 메모한 값이 있으면 계산하지 않아야 시간초과를 막을 수 있음
		if (dp[s][e] != 0) return dp[s][e];
		
		// 기저조건 : 구간의 길이가 1이면(즉, 단일 파일 경우) 해당 파일 크기 반환
		if (s==e) return files[s];
		
		// 유도 파트 : 각 구간을 두 개의 파트로 나눠 최소 합치는 비용 찾기
		int sectionSum = sum[e] - sum[s-1];     // s~e 구간합
		dp[s][e] = Integer.MAX_VALUE;           // 최댓값으로 초기화
		
		// 각 구간(s~e) 안에서 k를 옮겨가며 어떤 부분 구간의 합이 최소가 되는지 계산
		for (int m=s; m<e; m++) {
			int leftSection = combineFiles(s, m);         // 왼쪽 부분
			int rightSection = combineFiles(m+1, e);      // 오른쪽 부분
			
			dp[s][e] =  Math.min(dp[s][e], leftSection + rightSection + sectionSum);    // s~m구간 + (m+1)~e구간 + s~e 구간의 합
		}
		
		return dp[s][e];    // 최종 최소 합치는 비용을 반환
	}
}
