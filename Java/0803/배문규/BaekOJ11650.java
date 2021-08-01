package study.date0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BaekOJ11650 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] list = new int[N][];
		for(int i = 0; i < N; i++) {
			int[] data = new int[2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 2; j++) 
				data[j] = Integer.parseInt(st.nextToken());
			list[i] = data;
		}
		
		//Lambda Sort
		Arrays.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] t1, int[] t2) {
				// 첫 번째 인덱스의 값이 같으면
				if(t1[0] == t2[0])
					// 두 번째 인덱스의 값을 비교
					return t1[1] - t2[1];
				else
					return t1[0] - t2[0];
			}
		});
		
		for(int[] i : list) System.out.println(i[0]+" "+i[1]);
	}

}