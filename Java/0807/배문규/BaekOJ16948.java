package study.date0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BaekOJ16948 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R1 = Integer.parseInt(st.nextToken());
		int C1 = Integer.parseInt(st.nextToken());
		int R2 = Integer.parseInt(st.nextToken());
		int C2 = Integer.parseInt(st.nextToken());
		
		int[][] direct = {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};
		int[][] map = new int[N][N];
		for (int[] row: map) Arrays.fill(row, -1);
		boolean[][] check = new boolean[N][N];
		Deque<int[]> dq = new LinkedList<int[]>();
		 
		map[R1][C1] = 0;
		check[R1][C1] = true;
		dq.add(new int[] {R1, C1});
		
		while(!dq.isEmpty()) {
			int[] front = dq.pollFirst();
			
			for(int i = 0; i < 6; i++) {
				int x = front[0] + direct[i][0];
				int y = front[1] + direct[i][1];
				
				if(!((N > x && x >= 0) && (N > y && y >= 0))) continue;
				if(check[x][y]) continue;
				
				check[x][y] = true;
				map[x][y] = map[front[0]][front[1]] + 1;
				dq.add(new int[] {x, y});
			}
		}
		
		System.out.println(map[R2][C2]);
	}
}
