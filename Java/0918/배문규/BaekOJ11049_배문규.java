package BaekOJ.study.date0921;

import java.io.*;
import java.util.*;

/*
 * 자세한 설명은 아래에
 * 
 * 메모리 	시간
 * 17292	280
 */
public class BaekOJ11049_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, matrix[][], dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N][N];
		for(int x = 1; x < N; x++) { // 행렬 곱하는 범위
			for(int i = 0; i < N-x; i++) { // 시작점
				int j = i+x; // 끝점
				dp[i][j] = Integer.MAX_VALUE;
				for(int div = i; div < j; div++) { // 소구간 분할
					// 구간 i와 j를 div라는 기준으로 행렬을 2개로 나눠서 
					// 나눠진 두 행렬의 곱과, 미리 구해놓은 소구간의 최솟값을 더해 i부터 j까지의 행렬 곱셈 연산 횟수의 최솟값을 구한다. 
					// mat[div+1][0] 과 mat[div][1]는 서로 동일하니 아무거나 사용하면 됨.
					// 
					//  ABC는 너무 간단하니, D(6 4)를 추가해서  ABCD의 행렬 곱셈 연산 횟수의 최솟값을 구해보자.
					//
					//  A   B   C   D
					// 5 3*3 2*2 6*6 4에서
					// 먼저 x가 1일 때를 생각해보자 (x = 0일 땐 A,B,C,D 단일 행렬이므로 부분해 값이 0)
					// x = 1: AB, BC, CD
					//        AB = 5 3*3 2 = 30 ; 5 2
					//        BC = 3 2*2 6 = 36 ; 3 6
					//        CD = 2 6*6 4 = 48 ; 2 4
					// x = 2 : ABC, BCD
					//       *ABC
					//        A/BC = 5 3*3 6 = 90 ; 5 6
					//        AB/C = 5 2*2 6 = 60 ; 5 6
					//
					//        여기서 A는 부분해 값이 없으니 0, BC는 위에서 미리 36이라고 구해놨음
					//        A/BC = 90 + 0(A) + 36(BC) = 126
					//
					//        마찬가지로 C도 부분해 값이 없으니, AB는 위에서 미리 30이라고 구해놨음
					//        AB/C = 60 + 30(AB) + 0(C) = 90
					//        따라서 ABC의 최소연산 횟수는 90이 됨 
					// 
					//       *BCD
					//        B/CD = 3 2*2 4 = 24 ; 3 4
					//        BC/D = 3 6*6 4 = 72 ; 3 4
					//
					//        여기서 B는 부분해 값이 없으니 0, CD는 위에서 미리 48이라고 구해놨음
					//        B/CD = 48 + 0(B) + 24(CD) = 72
					//
					//        마찬가지로 D도 부분해 값이 없으니, BC는 위에서 미리 36이라고 구해놨음
					//        BC/D = 72 + 36(BC) + 0(C) = 108
					//        따라서 BCD의 최소연산 횟수는 72이 됨 
					//        
					// x = 3 : ABCD
					//        ABCD는 A/BCD, AB/CD, ABC/D로 구분할 수 있고, 위에서 미리 구분된 소구간의 최솟값까지 구해놨다.
					//        A/BCD = 0 + 72 + 5*3*4(60) = 132
					//        AB/CD = 30 + 48 + 5*2*4(40) = 118
					//        ABC/D = 90 + 0 + 5*6*4(120) = 210
					//        행렬 A*B*C*D 곱의 최소 연산 횟수는 118이 된다.
					//        
					//        아래 코드의 matrix[i][0]*matrix[div][1]*matrix[j][1]은 
					//        최솟값을 구하려는 구간을 2개의 소구간으로 행렬을 A/BCD, AB/CD, ABC/D 로 나눠서 두행렬을 곱하는 것이고,
					//       
					//        dp[i][div]+dp[div+1][j]는 소구간의 행렬 곱셈 연산 횟수의 최솟값이다.
					//        나눈 두 행렬의 곱셈 연산수와, 나눠진 두 행렬이 만들어지기까지의 곱셈 연산 횟수의 최솟값을 모두 해줘서 dp[i][j]를 구할 수 있다.
					// 
					// 
					//       행렬 곱셈은 weight[i][j]인, matrix[i][0]*matrix[div][1]*matrix[j][1]가 아래의 크누스 최적화 조건 3번(사각부등식)을 만족시키지 못하기 때문에 적용시킬 수 없다.
					//       3) a <= b <= c <= d일 때,
			        //          weight[a][c] + weight[b][d] <= weight[a][d] + weight[b][c];
					//       
					//         
					dp[i][j] = Math.min(dp[i][j], (matrix[i][0]*matrix[div][1]*matrix[j][1]) + (dp[i][div]+dp[div+1][j]));
				}
			}
		}
		
		System.out.println(dp[0][N-1]);
	}
}