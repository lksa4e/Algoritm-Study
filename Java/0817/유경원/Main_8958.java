import java.io.*;

public class Main_8958 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		int ans = 0;
		for(int i=0; i<T; i++) {
			char[] str = br.readLine().toCharArray();
			ans = 0;
			int combo = 0;
			for(char c: str) {
				if(c == 'O') {
					ans += 1+combo++;
				}else {
					combo = 0;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}