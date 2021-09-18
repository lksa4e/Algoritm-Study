import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0918] BOJ 11066 파일합치기
 *  DP
 * 
 * sol)
 * 파일을 합칠 때는 무조건 인접한 앞부분 파일과 뒷부분 파일 2개를 합쳐 합치는 비용이 최소가 되도록 해야함
 * 크게 보면 i~j까지 범위의 파일을 i~k까지 파일과 (k+1)~j까지 파일로 나눈 뒤 두 개를 더하는 비용이 최소가 되어야함
 * 따라서 dp 점화식은 다음과 같아진다.
 * 
 * D[i][j] : i~j까지 파일을 합치는 최소 비용
 * {
 * 		min(D[i][j], D[i][k] + D[k+1][j] + i~j를 합치는 비용)  (i<=k<=j)
 * }
 * 
 * 시행착오)
 * -
 * 아예 어떻게 풀어야할지 감도 안오던 문제..
 * 해설을 찾아봤는데 DP랑 재귀 2가지 방식으로 풀 수 있다는데 둘 다 이해하기까지 시간이 오래걸렸고, 그나마 재귀가 조금 더 이해가 가는 듯 하지만, 둘다 어려웠다..
 * 반성의 의미로 2가지 버전 풀이 모두 따라 풀어보았습니다. 휴
 * 
 * -
 * 최소에 꽂혀 오름차순 정렬하고 PQ에 삽입한 것처럼 가장 작은 묶음끼리씩 더하도록 계산해봤는데 자꾸 결과보다 작게 나와서 잘못됐다는 것을 깨달음
 * 파일의 순서를 섞으면 안됨!!
 * 주어진 순서 그대로일 때 두 부분으로 나눈 것을 더한 것이 최소가 되도록 해야함
 * 
 */

public class BOJ_11066_파일합치기_DP {
	static int K;
	static int[] files, sum;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=0; tc<T; tc++) {
			K = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			files = new int[K+1];          // 초기 파일 크기
			sum = new int[K+1];            // 파일 합치는 비용 구간 합
			
			// 초기 파일 크기 배열과 구간합 배열 초기화
			for (int i=1; i<=K; i++) {             // 구간합 배열때문에 배열 인덱스 1부터 시작
				files[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1]+files[i];
			}
			
			// 최소 비용을 저장해둘 배열
			dp = new int[K+1][K+1];
			
			for (int range=1; range<K; range++) {           // 1~K 길이의 구간 씩 합치기 시도
				for (int s=1; s+range<=K; s++) {            // 시작점(1~구간의 길이)
					int e = s+range;                        // 종료점(시작점+구간의 길이)
					int sectionSum = sum[e] - sum[s-1];     // 구간의 합
					
					dp[s][e] = Integer.MAX_VALUE;           // dp 배열에는 최소 비용이 저장되어야하므로 최댓값으로 초기화
					
					// 각 구간(s~e) 안에서 k를 옮겨가며 어떤 부분 구간의 합이 최소가 되는지 확인하여 dp배열에 반영
					for (int m=s; m<e; m++) {
						dp[s][e] = Math.min(dp[s][e], dp[s][m] + dp[m+1][e] + sectionSum);    // s~m구간 + (m+1)~e구간 + s~e 구간의 합
					}
				}
			}
			System.out.println(dp[1][K]);
		}
	}
}
