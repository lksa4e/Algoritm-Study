import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0807] 백준 11650 단어수학
 * 2차원 배열 정렬
 */

public class Baekjoon11650 {
	static int N;
	static int[][] points;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		points = new int[N][2];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// Comparator의 compare() 메서드 람다식 이용
		Arrays.sort(points, (x, y) -> {
			if (x[0] == y[0]) {
				return x[1] - y[1];
			} else {
				return x[0] - y[0];
			}
		});

		StringBuilder sb = new StringBuilder();
		for (int[] point : points) {
			sb.append(point[0]).append(" ").append(point[1]).append("\n");
		}
		System.out.println(sb);
	}

}
