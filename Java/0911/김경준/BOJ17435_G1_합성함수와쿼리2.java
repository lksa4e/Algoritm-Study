import java.io.*;
import java.util.*;

/**
 * G3 BOJ 17435 합성함수와 쿼리 :
 * 메모리 : 108116, 시간 : 1156ms
 * 
 * LCA의 log(N) 최적화 버전과 유사한 풀이
 * 1. f의 합성함수 배열을 1,2,4,8,,, 식으로 2^n으로 저장함
 * 2. 쿼리 x를 찾을 때 x를 2진수 표현한 후  2^n 형태로 잘라서 1단계에서 구한 dp값을 이용함 
 * 
 * 너무어렵네요....
 * 심지어 LCA logN 풀이를 먼저 공부했음에도 여기에 적용할 생각을 못했습니다...
 */

public class BOJ17435_G1_합성함수와쿼리2 {
	static int N,M;
	static int arr[][];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		int tree_h = (int)Math.ceil(Math.log(200000) / Math.log(2)) + 1;
		arr = new int[N+1][tree_h];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) 
			arr[i][0] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i < tree_h; i++) // 깊이 1 ~ tree_height까지
			for(int cur = 1; cur <= N; cur++)  // 모든 f에 대하여 수행
				arr[cur][i] = arr[arr[cur][i-1]][i-1];
		
		// LCA와 동일한 원리
		// f의 4합성은 f의 2합성의 2합성, f의 8합성은 f의 4합성의 4합성....
		
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			for(int j = 0; n != 0; j++) {
				if(n % 2 == 1)       // 2진수로 나타내었을때 1이라면 shift
					x = arr[x][j];
				n >>= 1;
			}
			sb.append(x + "\n");
		}
		System.out.print(sb);
	}
}
