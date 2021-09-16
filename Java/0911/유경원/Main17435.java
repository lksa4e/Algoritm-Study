import java.io.*;
import java.util.*;

public class Main17435 {
	/*
	 * 11437 LCA문제에서 부모관계 설정한 방법 사용
	 * x = j, 2^i = n 일때 fn(x)값
	 * arr[j][i] = arr[arr[j][i - 1]][i - 1];
	 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	static int M, Q;
	static final int MAX_LEVEL = 19;
	static int[][] arr = new int[500001][MAX_LEVEL];

	public static void main(String[] args) throws NumberFormatException, IOException {
		M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) 
			arr[i][0] = Integer.parseInt(st.nextToken());
		

		for (int i = 1; i < MAX_LEVEL; i++) 
			for (int j = 1; j <= M; j++) 
				arr[j][i] = arr[arr[j][i - 1]][i - 1];
			
		Q = Integer.parseInt(br.readLine());

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			for (int j = MAX_LEVEL - 1; j >= 0; j--) {
				int cur = (1 << j);
				if (n >= cur) {
					x = arr[x][j];
					n -= cur;
					if (n == 0) break;
				}
			}
			sb.append(x).append("\n");
		}

		System.out.println(sb);
	}

}