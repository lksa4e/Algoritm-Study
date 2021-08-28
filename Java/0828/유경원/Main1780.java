import java.io.*;
import java.util.*;


public class Main1780 {
	
	/*
	 * 9분할 하면서 각각의 범위가 같은 수로 채워져있는지 체크한다
	 */
	static int N, M, Z, P;
	static int[][] paper;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(0,0,N);
		
		System.out.println(M);
		System.out.println(Z);
		System.out.println(P);
	}
	
	private static void divide(int r, int c, int len) {
		if(check(r, c, len)) { // 주어진 범위가 모두 같은 수로 채워져 있다면 true
			if(paper[r][c] == -1) M++; // 그 수의 개수++
			else if(paper[r][c] == 0) Z++;
			else P++;
			
			return;
		}
		
		int newLen = len/3;
		
		for(int i=0; i<3; i++) { // 9분할
			for(int j=0; j<3; j++) {
				divide(r+i*newLen, c+j*newLen, newLen);
			}
		}
		
	}
	
	private static boolean check(int r, int c, int len) {
		int num = paper[r][c];
		
		for(int i=r; i<r+len; i++) {
			for(int j=c; j<c+len; j++) {
				if(paper[i][j] != num) return false;
			}
		}
		
		return true;
	}
	
}