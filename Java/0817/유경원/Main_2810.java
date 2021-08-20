import java.io.*;

public class Main_2810 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		s = s.replace("LL", "S");
		
		System.out.println((N <= s.length()+1) ? N : s.length()+1);
		
	}
}