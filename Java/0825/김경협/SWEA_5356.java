import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 *  SWEA 5356번 의석이의 세로로 말해요
 *  
 *  주어진 5개의 서로다른 길이 단어들을 세로로 읽는 문제였다.
 *  char 이중 배열을 만들어 단어를 받을 때 세로로 넣어줬고
 *  가로로 읽으면서 '\u0000'을 체크하면서 출력했다.
 *  
 */

public class SWEA_5356 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		
		for (int tc = 1; tc <= TC; tc++) {
			char[][] arr = new char[15][5]; // '\u0000'로 초기화 되어 있다.
			
			for (int i = 0; i < 5; i++) {
				char[] cr = br.readLine().toCharArray();
				for (int j = 0; j < cr.length; j++) {
					// 받을 때 세로로 넣어주기
					arr[j][i] = cr[j];
				}
			}
			
			sb.append("#").append(tc).append(" ");
			
			for (int i = 0; i < 15; i++)
				for (int j = 0; j < 5; j++)
					if(arr[i][j] != '\u0000')	// 공백을 제외하고, 가로로 꺼내기
						sb.append(arr[i][j]);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
