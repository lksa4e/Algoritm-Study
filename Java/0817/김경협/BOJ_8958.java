import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_8958 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < TC; tc++) {
			char[] str = br.readLine().toCharArray();
			int len = str.length;
			int sum = 0;
			int Osum = 0;
			
			for (int i = 0; i < len; i++) {
				if(str[i] == 'O')
					sum += ++Osum;
				else if (str[i] == 'X')
					Osum = 0;
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
