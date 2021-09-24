import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 11049번 행렬 곱셈 순서
 * 
 * DP를 사용하여 행렬의 곱연산을 구했다.
 * i부터 j까지의 최솟값을 구한다고 치면, 그 사이의 
 * i~i+1매트릭스 * i+2~j매트릭스
 * i~i+2매트릭스 * i+3~j매트릭스
 * ...
 * 를 구하고 그 중에서 가장 작은 값을 저장하는 형태이다.
 * 
 * 재귀함수를 사용해서 부분적인 행렬의 최솟값을 구하는 식으로
 * 파고들도록 구현했다.
 *  
 */

public class BOJ_11049 {
	static int N, mat[][], dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		mat = new int[N][2];	// 곱연산을 계산하기 위한 각 매트릭스들의 행렬저장
		dp  = new int[N][N];	// 곱연산들의 최솟값을 저장하기 위한 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			mat[i][0] = Integer.parseInt(st.nextToken());
			mat[i][1] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[i], Integer.MAX_VALUE);	// 이따가 나온 cost와 최솟값으로 비교하기 위해 max로 채워줌
		}
		
		System.out.println(getMinValueOfMatrix(0, N-1));
	}
	
	// 재귀함수로 주어진 구간의 매트릭스 곱의 최솟값을 구하기
	static int getMinValueOfMatrix(int start, int end) {
		if(start == end) return 0;
		if(dp[start][end] != Integer.MAX_VALUE) return dp[start][end];	// 이미 최솟값이 저장되어 있으면 저장된거 사용
		// start 부터 end까지 i로 쪼개기
		// start부터 i까지의 최솟값 + (i+1)부터 end까지의 최솟값 + start~i매트릭스와 i+1~end매트릭스를 곱하는 값
		for (int i = start; i < end; i++) {
			int value = getMinValueOfMatrix(start, i) +
						getMinValueOfMatrix(i+1, end) +
							mat[start][0]*mat[i][1]*mat[end][1];
			dp[start][end] = Math.min(dp[start][end], value);
		}
		return dp[start][end];
	}

}
