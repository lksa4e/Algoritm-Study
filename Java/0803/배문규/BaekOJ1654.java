package study.date0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekOJ1654 {

	static int K, N;
	static long[] lan;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		lan = new long[K];
		for(int i = 0; i < K; i++) lan[i] = Long.parseLong(br.readLine());
		
		long sum = 0;
		for(long l : lan) sum += l;
		
		System.out.println(parametric_search(1, sum/N));
	}
	
	public static long parametric_search(long left, long right) {
		
		long pivot = (left + right)/2;
		
		if(left > right) return right;
		
		long remain = 0;
		for(long l : lan) remain += l/pivot;

		if(remain >= N) return parametric_search(pivot+1, right);
		else return parametric_search(left, pivot-1);
	}

}