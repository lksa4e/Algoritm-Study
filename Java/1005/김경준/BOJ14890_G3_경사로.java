import java.io.*;
import java.util.*;

/**
 * G3 BOJ 14890 경사로 
 * 메모리 : 15312kb 시간 : 148ms
 * 
 * 경사로는 수업시간에 풀어서 주석을 생략
 */
public class BOJ14890_G3_경사로 {
	static int T, N, X, map[][], map2[][];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		map2 = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map2[j][i] = map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(check(map) + check(map2));
	}

	static int check(int[][] map) {
		// 가로 체크
		int answer = 0;
		boolean visit[][] = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {

				int gap = Math.abs(map[i][j - 1] - map[i][j]);
				if (gap > 1) break;

				// 오르막 -> 왼쪽 탐색
				if (map[i][j - 1] < map[i][j]) {
					int start_num = map[i][j - 1];
					boolean flag = false;

					for (int k = j - 1; k > j - 1 - X; k--) {
						if (oob(i, k)) break;
						if (map[i][k] != start_num) break;
						if (visit[i][k]) break;

						if (k == j - X) flag = true;
					}
					if (flag == false)
						break;
				}

				// 내리막 -> 오른쪽 탐색
				else if (map[i][j - 1] > map[i][j]) {
					int start_num = map[i][j];
					boolean flag = false;
					for (int k = j; k < j + X; k++) {
						if (oob(i, k)) break;
						if (map[i][k] != start_num) break;
						visit[i][k] = true;
						if (k == j + X - 1) flag = true;
					}
					if (flag == false) break;
				}
				if (j == N - 1) answer++;
			}
		}
		return answer;
	}

	static boolean oob(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
