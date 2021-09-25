import java.io.*;
import java.util.*;

/**
 * G3 BOJ 11049 행렬 곱셈 순서 :
 * 메모리 : 17728kb 시간 : 280ms
 * 
 * 저번주의 파일 합치기와 굉장히 유사한 dp 문제
 * dp[i][j] -> i ~ j 까지 행렬 곱 했을 때의 연산의 최소량 으로 정한다.
 * 
 * dp[i][j] = min<i<=k<=j> (dp[i][k] + dp[k+1][j]) + cost[i][j] 의 점화식을 만족.
 * 
 * 이 때 i -> k - > j 의 cost[i][j]는 (i row) * (k col) * (j col)이다.
 * why?
 * i ~ k 의 행렬 곱으로 N * M 행렬이 만들어지고, k+1 ~ j 행렬의 곱으로 M * K가 만들어졌을 때
 * 두 행렬의 곱의 연산값은 N * M * K가 되기 때문
 * 
 */

public class BOJ11049_G3_행렬곱셈순서 {
	static int N, answer = Integer.MAX_VALUE, arr[][], dp[][];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine()); 
		arr = new int[N+1][2];
		dp = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 간격 설정
		for(int k = 1; k < N; k++) { 
			// 시작점 지정
			for(int i = 1; i + k <= N; i++) {
				// 종료지점 == 시작지점 + 간격
				int end = i + k;
				dp[i][end] = Integer.MAX_VALUE;
				// 모든 중간값에 대해 dp값 갱신 -> start ~ start, start ~ start + 1 , start ~ start + 2,,, 채우기
				for(int j = i; j < end; j++) {  
					dp[i][end] = Math.min(dp[i][end], dp[i][j] + dp[j+1][end] + (arr[i][0] * arr[j][1] * arr[end][1]));
				}
			}
		}
		System.out.println(dp[1][N]);
	}
}
