import java.io.*;

/*
 * BOJ 1013번 Contact
 * 
 * String의 matches를 사용해 정규표현식을 넣고 t/f를 반환받아 풀었다.
 * 
 * 16608KB	208ms
 */

public class BOJ_1013 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < TC; i++) {
			if(br.readLine().matches("(100+1+|01)+"))
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}
		System.out.println(sb);
	}

}
