package P0821;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 백준 1080번 행렬
 * 
 * 풀이: 그리디 알고리즘 
 * 1. 3*3 행렬이므로 반전시킬 수 있는 시작 index 범위는 (0~N-3),(0~M-3)
 * 2. 2중 for문을 돌면서 요소가 동일한지 판단 (행우선 탐색으로 이미 지나간 요소는 반전되지 않는다.)
 * 3. 요소가 다르다면, 해당 요소를 시작점으로 3*3 행렬 반전
 * 4. 범위를 모두 판단한 후, 모든 행렬이 동일한지 판단 
 * 
 * 14252KB	132ms
 */
public class Solution1080_김다빈 {

	static int[][] A, B;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		A = new int[N][M];
		B = new int[N][M];
		
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				A[i][j] = s.charAt(j) - '0';
			}
		}
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				B[i][j] = s.charAt(j) - '0';
			}
		}
		
		// 0~N-3, 0~M-3까지 순차적으로 요소가 동일한지 판단 
		int cnt = 0;
		for(int i=0;i<=N-3;i++) {
			for(int j=0;j<=M-3;j++) {
				if(A[i][j] != B[i][j]) {	// 다르면 행렬 반전 
					reverseMatrix(i,j);
					cnt++;
				}
			}
		}
		
		// 같은 행렬이 됐는지 검사 
		boolean sameMatrix = true;
LOOP:	for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(A[i][j] != B[i][j]) {
					sameMatrix = false;
					break LOOP;
				}
			}
		}
		
		if(sameMatrix) System.out.println(cnt);
		else System.out.println(-1);
	}

	// 행렬 반전 시켜주는 함수 
	private static void reverseMatrix(int r, int c) {
		for(int i=r;i<r+3;i++) {
			for(int j=c;j<c+3;j++) {
				A[i][j] = (A[i][j] == 1) ? 0 : 1;
			}
		}
	}
	
}
