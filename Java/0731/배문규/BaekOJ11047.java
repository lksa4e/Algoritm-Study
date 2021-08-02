package study.date0730;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekOJ11047 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int cnt = 0;
		
		int[] money = new int[N];
		for(int i = N-1; i >= 0; i--) {
			money[i] = Integer.parseInt(br.readLine());
		}
				
		for(int m:money) {
			cnt += K/m;
			K %= m;
			if(K == 0) break;
		}
		
		System.out.println(cnt);
	}

}
