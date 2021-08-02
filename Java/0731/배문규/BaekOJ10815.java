package study.date0730;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekOJ10815 {
	
	static int[] Arr = new int[20000001];
    
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();

		int N = Integer.parseInt(br.readLine());		
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < N; i++){
            int tmp= Integer.parseInt(st.nextToken()) + 10000000;
            Arr[tmp] = 1;
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i < M; i++){
            int tmp= Integer.parseInt(st.nextToken()) + 10000000;
            if(Arr[tmp] == 1) sb.append("1 ");
            else sb.append("0 ");
        }
		
        System.out.println(sb);
	}
}
