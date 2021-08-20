import java.io.*;

public class Main_2999 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		String code = br.readLine();
		int R = 0;
		int len = code.length();
		int sqrt = (int) Math.sqrt(len);
		for(int i=1; i<=sqrt; i++) 
			if(len%i == 0) R = i;
		
		int C = len/R;
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++) {
				sb.append(code.charAt((i+R*j)));
			}
		
		System.out.println(sb);
	}
}