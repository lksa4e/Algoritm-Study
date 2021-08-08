package study.date0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekOJ11399 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	static StringTokenizer st;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
	    N = Integer.parseInt(br.readLine());
	    int[] time = new int[N];
	    
	    st = new StringTokenizer(br.readLine());
	    for(int i = 0; i < N; i++) time[i] = Integer.parseInt(st.nextToken());
	    
	    // 인출시간이 짧은순으로 사람들을 정렬함
	    Arrays.sort(time);
	    
	    // 가장 짧은 시간이 걸리는 사람들부터 시간을 누적시켜 
	    // 해당 사람이 돈을 뽑기까지 걸리는 전체 시간을 구하고
	    // 모든 시간들을 더함 
	    int total = 0, sum = 0;
	    for(int t : time) {
	    	total += t;
	    	sum += total;
	    }
	    
	    System.out.println(sum);
	}

}