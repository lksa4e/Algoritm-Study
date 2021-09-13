import java.io.*;
import java.util.*;

/**
 * 백준 17435번 합성함수와 쿼리 
 * 
 * 풀이 : 희소 배열 & 비트마스킹 
 * 
 * 쿼리 결과를 미리 배열에 저장하는 희소 배열을 이용하여
 * O(NQ)의 시간복잡도를 O(QlogN)으로 줄이기!
 * 
 * n을 비트마스킹으로 2진법으로 변환한 후 한 자리씩 확인 -> 1이면 이동
 * 
 * 되게 어려웠던..
 * 
 * 103560KB	940ms
 */
public class Solution17435_김다빈 {
	
	// 최대 500,000까지 쿼리가 실행될 수 있으므로 2^d > 500,000 인 d 구하기 => 19
	static int MAX_VALUE = 200001;
	static int MAX_DEGREE = 19;
	static int[][] array = new int[MAX_DEGREE][MAX_VALUE];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		// 기저배열 저장
		for (int i = 1; i <= M; i++) {
			array[0][i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < MAX_DEGREE; i++) {
			for (int j = 1; j <= M; j++) {
				array[i][j] = array[i-1][array[i-1][j]];
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			// n을 2진법으로 변환한 값을 한 자리씩 확인하면서 1이면 이동
			for (int j = 0; j < MAX_DEGREE; j++) {
                if ((n & (1 << j)) > 0) {
                    x = array[j][x];
                }
            }
			
			sb.append(x + "\n");
		}
		System.out.println(sb);
	}

}
