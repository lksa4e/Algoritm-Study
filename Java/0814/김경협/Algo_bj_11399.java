package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algo_bj_11399 {
	static int N;
	static int[] time;
	
	// 가장 짧은 스케쥴링 시간은 오름차순 정렬된 순서
	static int printMinimumTime() {
		Arrays.sort(time);
		int sum = 0, waitTime = 0;
		
		for (int i = 0; i < N; i++) {
			waitTime += time[i];
			sum += waitTime;
		}
		
		return sum;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
		
		time = new int[N];
		for (int i = 0; i < N; i++)
			time[i] = Integer.parseInt(tk.nextToken()); 
		
		System.out.println(printMinimumTime());		
	}

}
