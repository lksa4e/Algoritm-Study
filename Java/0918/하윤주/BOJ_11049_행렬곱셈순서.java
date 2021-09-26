import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0925] BOJ 11049 행렬 곱셈 순서
 *  DP
 * 
 * sol)
 * 11066 파일 합치기 문제처럼 인접한 두 행렬에 대한 곱을 구하는 문제
 * 따라서 두 문제의 점화식은 같다.
 * 
 * D[i][j] : i~j 행렬 곱의 최솟값
 * {
 * 		min(D[i][j], D[i][k] + D[k+1][j] + i~k 행렬에 k~j 행렬을 곱한 결과)  (i<=k<=j)
 * }
 * 
 * 시행착오)
 * 지난주 dp문제가 빛을 발해서 점화식 떠올리는 것까지는 어렵지 않았는데 행렬곱을 위해 기저조건 곱을 계산하는 것이 어려웠다.
 * 
 */
public class BOJ_11049_행렬곱셈순서 {
	static int N;
	static int[][] matrix, dp;		// 입력 행렬, dp를 위한 동적 테이블
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 입력 행렬 초기화
		matrix = new int[N+1][2];
		for(int i=1; i<=N; i++) {		// 인덱스 1부터 시작
			StringTokenizer st = new StringTokenizer(br.readLine());
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// dp 배열 저장
		dp = new int[N+1][N+1];
		for (int range=1; range<N; range++) {		// 행렬의 범위
			for (int s=1; s+range<=N; s++) {		// 시작 행렬
				int e = s+range;					// 종료 행렬
				
				dp[s][e] = Integer.MAX_VALUE;		// 최솟값 계산을 위한 초기화
				
				// 행렬을 다시 구간으로 나누어 dp 계산
				for (int m=s; m<e; m++) {
					int sectionMultiply = matrix[s][0] * matrix[m][1] * matrix[e][1];			// i~m 행렬에 m~j 행렬을 곱한 결
					dp[s][e] = Math.min(dp[s][e], dp[s][m] + dp[m+1][e] + sectionMultiply);		// i~j 행렬을 m 구간을 중심으로 나눠 행렬 곱을 구함
				}
			}
		}
		
		System.out.println(dp[1][N]);
		
	}

}
