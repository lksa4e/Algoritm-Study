import java.io.*;

public class Main_3052 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		int[] arr = new int[42];
		for(int i=0; i<10; i++) {
			int n = Integer.parseInt(br.readLine());
			if(arr[n%42]==0) arr[n%42]++;
		}
		int ans = 0;
		for(int a: arr) ans += a;
		System.out.println(ans);
	}
}