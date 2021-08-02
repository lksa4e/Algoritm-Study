package study.date0730;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekOJ2751 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int _min = Integer.MAX_VALUE;
		int _max = Integer.MIN_VALUE;
		boolean[] check = new boolean[2000001];
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine()) + 1000000;
			check[num] = true;
			_min = Math.min(num, _min);
			_max = Math.max(num, _max);
		}
		
		for(int i = _min; i <= _max; i++) {
			if(check[i]) sb.append((i-1000000)+"\n");
		}
		System.out.println(sb.toString());
	}

}
