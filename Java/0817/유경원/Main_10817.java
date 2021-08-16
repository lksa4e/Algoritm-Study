import java.io.*;
import java.util.*;

public class Main_10817 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int mid = 0;
		if(A>=B) {
			if(B>=C) {
				mid = B;
			}else {
				if(A>=C){
					mid = C;
				}else {
					mid = A;
				}
			}
		}else {//A<B
			if(A>=C) {
				mid = A;
			}else {
				if(B>=C){
					mid = C;
				}else {
					mid = B;
				}
			}
		}
		System.out.println(mid);
	}
}