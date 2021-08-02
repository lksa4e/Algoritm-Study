package study.date0730;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeakOJ1790 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());        
        
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        if(getLength(N) < k) System.out.println(-1);
        else {
        	int left = 1;
            int right = N;
            int result = 0;
            while(left <= right) {
                int mid = (left + right)/2;
                int len = getLength(mid);
                if(len < k) {
                    left = mid + 1;
                } else {
                	result = mid;
                    right = mid - 1;
                }
            }
            int length = getLength(result);
            String resultS = String.valueOf(result);
            System.out.println(resultS.charAt(resultS.length()-length + k-1));
        }
        
	}
	
	public static int getLength (int n) {
        int length = 0;
        for (int i = 1, digit = 1; i <= n; i *= 10, digit++) {
            int digitlen = i*10-1;
            if(n < digitlen) {
            	digitlen = n;
            }
            length += (digitlen-i+1)*digit;
        }
        return length;
    }
}
