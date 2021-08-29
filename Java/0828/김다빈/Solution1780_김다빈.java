import java.util.*;
import java.io.*;

/**
 * 백준 1780번 종이의 개수
 * 
 * 풀이 : 분할 정복 + 재귀
 * 
 * 수업 때 쌤이 언급해주신 누적합으로 풀어보려다가 -1, 1로 인해 실패..
 * 결국 첫 인덱스와 같은지 여부로 모두 같은 수인지 판단!
 * 
 * 다르다면, 9개로 분할하여 각각 재귀
 * 
 * 313060KB	1044ms
 */
public class Solution1780_김다빈 {

	static int minus, zero, plus;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divideMap(0,0,N);
		
		sb.append(minus+"\n"+zero+"\n"+plus);
		System.out.println(sb);
	}

	private static void divideMap(int r, int c, int size) {	// (r,c) : 시작 인덱스, size : 분할 사이즈
		// 모두 같은 숫자이면 종료 
		if(checkMap(r,c,size)) {
			return;
		}
		
		int divSize = size/3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				divideMap(r + divSize*i, c + divSize*j, divSize);
			}
		}
	}

	// 모두 같은 수인지 판단하고 해당 값 업데이트 
	private static boolean checkMap(int r, int c, int size) {
		int number = map[r][c];	// 첫 인덱스와 같은지 여부로 판단 
		
		for (int i = r; i < r+size; i++) {
			for (int j = c; j < c+size; j++) {
				if(number != map[i][j]) return false;
			}
		}
		
		switch(number) {
		case -1:	// 모두 -1일 때
			minus++;
			break;
		case 0:	// 모두 0일 때
			zero++;
			break;
		case 1:	// 모두 1일 때
			plus++;
			break;
		}
		return true;
	}

}
