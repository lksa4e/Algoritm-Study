import java.io.*;

public class Main_8320 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		int sqrt = (int)Math.sqrt(n);
		int cnt = n;
		for(int r=2, c=r; r<=sqrt; r++) {
			c=r;
			while(r*c++ <= n) cnt++;
		}
		System.out.println(cnt);
		
	}
}