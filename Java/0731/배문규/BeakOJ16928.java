package study.date0730;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BeakOJ16928 {

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                
        String[] str = br.readLine().split(" ");
        int L = Integer.parseInt(str[0]);
        int S = Integer.parseInt(str[1]);
        
        int[] LorS = new int[101];
        
        for(int i = 0; i < L+S; i++) {
        	str = br.readLine().split(" ");
        	int s = Integer.parseInt(str[0]);
            int e = Integer.parseInt(str[1]);
            LorS[s] = e;
        }
        
        int[] map = new int[101];
		Arrays.fill(map, -1);
		Deque<Integer> dq = new LinkedList<Integer>();
		
		map[1] = 0;
		dq.add(1);
		
		while(!dq.isEmpty()) {
			int front = dq.pollFirst();
			
			for(int i = 1; i <= 6; i++) {
				int go = front + i;
				
				if(go > 100) continue;
				
				if(LorS[go] != 0) go = LorS[go];
				
				if(map[go] == -1) {
					map[go] = map[front] + 1;
					dq.add(go);
				}
			}
		}
		
		System.out.println(map[100]);
	}
}
