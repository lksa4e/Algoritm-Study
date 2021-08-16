import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2567 {
	static final int MAP_SIZE = 100;
	static final int PAPER_SIZE = 10;
	static boolean[][] map = new boolean[MAP_SIZE + 2][MAP_SIZE + 2];
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void pastePaper(int row, int col) {
		for (int r = row; r < row + 10; r++)
			for (int c = col; c < col + 10; c++)
				map[r][c] = true;
	}

	public static int printArea() {
		int cnt = 0;
		for (int r = 1; r <= MAP_SIZE; r++)
			for (int c = 1; c <= MAP_SIZE; c++)
				if (map[r][c])
					for (int i = 0; i < 4; i++)
						if (!map[r + dr[i]][c + dc[i]])
							cnt++;
		return cnt;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(tk.nextToken());
			int col = Integer.parseInt(tk.nextToken());
			pastePaper(row, col);
		}

		System.out.println(printArea());

	}

}
