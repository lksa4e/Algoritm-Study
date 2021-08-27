import java.io.*;
import java.util.*;


public class Main14889 {
	/*
	 * 0으로 시작하는 조합까지만 탐색 (0이 있는 조합이면 스타트팀, 없으면 링크팀)
	 * 01 02 03 / 23 13 12
	 */
	static int N, ans;
	static int[][] matrix;
	static boolean[] arr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		arr = new boolean[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		combination(0, 0);
		
		System.out.println(ans);
	}
	
	private static int play(int[] A, int[] B) {
		int scoreA = 0, scoreB = 0;
		for(int i=0; i<A.length-1; i++) {
			for(int j=i+1; j<A.length; j++) {
				scoreA += matrix[A[i]][A[j]] + matrix[A[j]][A[i]];
				scoreB += matrix[B[i]][B[j]] + matrix[B[j]][B[i]];
			}
		}
		return Math.abs(scoreA - scoreB);
	}
	
	private static void combination(int cnt, int start) {
		if(cnt == N/2) {
			int[] A = new int[N/2];
			int[] B = new int[N/2];
			for(int i=0, a=0, b=0; i<N; i++) {
				if(arr[i]) A[a++] = i; // true면 A팀
				else B[b++] = i; // false면 B팀
			}
			ans = Math.min(ans, play(A,B)); // 최솟값 찾기
			return;
		}
		
		for (int i = start; i < N; i++) {
			
			arr[i] = true;
			combination(cnt+1,i+1);
			arr[i] = false;
			if(cnt==0) return; // 가지치기 : 조합의 맨 앞자리 바뀌면 종료 (0으로 시작하는 조합만 구하면 되므로) 
			
		}
	}
}