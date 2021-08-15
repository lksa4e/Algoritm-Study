import java.io.*;
import java.util.*;
/**
 * BOJ 8958 OX 퀴즈 : https://www.acmicpc.net/problem/8958
 * 메모리 : 14252KB, 시간 : 140ms
 * 누적 횟수를 나타내는 flag 변수를 별도로 두어 O가 연속되면 flag가 증가되도록 함
 * 
 * Time Complexity
 * - O(N)
 */
public class BOJ_8958_김경준 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			char[] chars = br.readLine().toCharArray();
			int flag = 0;
			int answer = 0;
			for(int j = 0; j < chars.length; j++) {
				if(chars[j] == 'O') answer += flag++ + 1;
				else flag = 0;
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
