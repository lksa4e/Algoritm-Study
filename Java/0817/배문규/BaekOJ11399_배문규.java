package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 가장 빨리 돈을 뽑을 수 있는 사람부터 뽑아내도록 정렬하고 ,
 * 그 순으로 시간의 누적 총합을 구하는 그리디한 방식으로 풀면 될거라 생각함
 * 
 * 메모리 	시간
 * 14536	128
 */

public class BaekOJ11399_배문규 {

	static BufferedReader br;
	static StringTokenizer st;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
	    br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    st = new StringTokenizer(br.readLine());

	    int[] time = new int[N];
	    for(int i = 0; i < N; i++) time[i] = Integer.parseInt(st.nextToken());
	    
	    // 인출시간이 짧은순으로 사람들을 정렬함
	    Arrays.sort(time);
	    
	    // 가장 짧은 시간이 걸리는 사람들부터 기다리는 시간(t)을 누적시켜 
	    // 해당 사람이 돈을 뽑기까지 걸리는 전체 시간(wait)을 구하고
	    // 모든 시간들을 더함(total)
	    int wait = 0, total = 0;
	    for(int t : time) {
	    	wait += t;
	    	total += wait;
	    }
	    
	    System.out.println(total);
	    
	}

}
