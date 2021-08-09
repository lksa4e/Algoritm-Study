package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algo_bj_11728 {
	static int[] N;
	static int[] M;

	// merge sort의 merge 부분
	static String merge() {
		StringBuilder sb = new StringBuilder();
		int nIndex = 0, mIndex = 0;

		while (nIndex < N.length && mIndex < M.length) {
			if (N[nIndex] > M[mIndex])
				sb.append(M[mIndex++] + " ");
			else
				sb.append(N[nIndex++] + " ");
		}

		while (nIndex < N.length)
			sb.append(N[nIndex++] + " ");
		
		while (mIndex < M.length)
			sb.append(M[mIndex++] + " ");

		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");

		int sizeN = Integer.parseInt(tk.nextToken());
		int sizeM = Integer.parseInt(tk.nextToken());
		N = new int[sizeN];
		M = new int[sizeM];

		tk = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < sizeN; i++)
			N[i] = Integer.parseInt(tk.nextToken());

		tk = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < sizeM; i++)
			M[i] = Integer.parseInt(tk.nextToken());

		System.out.println(merge());
	}

}
