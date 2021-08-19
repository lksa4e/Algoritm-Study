package BaekOJ.study.date0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 처음에 완탐인가 생각을 해봤다가 아닌것 같아서 어떻게 풀까 생각을 해보다가
 * 도저히 못참고 알고리즘 분류를 보니 그리디였다.
 * 그리디인 것을 보고 문제가 그리디라면 어떻게 풀면 되는지 바로 아이디어가 떠오름
 * 3*3 변환이니까 0 ~ N-2 / 0 ~ M-2 로 서로 차이가 존재하는 부분은 뒤집고 넘어간다. 
 * 뒤집는 개념은 두번 뒤집으면 다시 원상태라 두번 이상 뒤집는 경우는 의미가 없으므로 다시 돌아올 필요없이 넘어가도 됨
 * N-2, M-2까지 순차탐색을 끝나고 난 다음의 결과를 보고 -1을 리턴할 지 cnt를 리턴할 지 결정하면 됨 
 * 
 * 왜 태희쌤이 알고리즘 분류를 보는 것 만으로도 스포가 된다고 하신 것을 다시 한번 더 깨닫게 됨
 * 
 * 메모리 	시간
 * 14260	140
 */

public class BaekOJ1080_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, M, A[][], B[][], gap[][], cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 행렬  A 입력
		A = new int[N][M];
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) A[i][j] = Character.getNumericValue(input.charAt(j));
		}
		
		// 행렬  B 입력
		B = new int[N][M];
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) B[i][j] = Character.getNumericValue(input.charAt(j));
		}
		
		// 행렬 A와 행렬 B 차이를 구함
		gap = new int[N][M];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++) gap[i][j] = Math.abs(B[i][j]-A[i][j]);
		
		// 서로 차이가 있다면 뒤집고 카운트 
		for(int i = 0; i < N-2; i++) {
			for(int j = 0; j < M-2; j++) {
				if(gap[i][j] == 1) {
					transform(i,j);
					cnt++;
				}
			}
		}
		
		System.out.println(debug());
	}
	
	// 뒤집기
	public static void transform(int si, int sj) {
		for(int i = si; i < si+3; i++) 
			for(int j = sj; j < sj+3; j++) gap[i][j] = (gap[i][j]+1)%2; 
	}
	
	// 결과가 0이 아닌 요소가 있으면 return -1, 다 0이면 retrun cnt 
	public static int debug() {
		for (int i = 0; i < gap.length; i++) {
			for (int j = 0; j < gap[0].length; j++) {
				if(gap[i][j] != 0) return -1;
			}
		}
		return cnt;
	}
}
