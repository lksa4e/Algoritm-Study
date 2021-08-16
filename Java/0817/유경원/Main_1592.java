import java.io.*;
import java.util.*;

public class Main_1592 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		arr[0] = 1;
		int cnt = 1, idx = 0;
		while(true) {
			if(arr[idx]%2==0) 
				idx = (idx - L + N) % N;
			else 
				idx = (idx + L) % N;
			
			if(++arr[idx] == M) break;
			
			cnt++;
		}
		System.out.println(cnt);
	}
}