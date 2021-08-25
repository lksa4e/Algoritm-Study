package SWEA.study0825;

import java.io.*;
import java.util.*;

/*
 * 가로로 입력받고 세로로 읽기인데 짧은애는 금방 끝나고 긴애는 계속 읽어가는 문제임
 * 입력을 받으면서 입력 문자열 중 최대 길이를 구함
 * 그다음 최대길이만큼 * 5만큼 반복하면서 해당 문자열이 끝나면 continue하는 방식으로 해결함
 * 메모리		시간
 * 18,408 	125 
 */
public class Solution5356_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T;

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			String[] input = new String[5];
			// 최대 길이
			int maxlen = -1;
			for(int i = 0; i < 5; i++) {
				input[i] = br.readLine();
				if(maxlen < input[i].length()) maxlen = input[i].length(); // 최대 길이 구함
			}
			
			sb.append("#").append(tc).append(" ");
			for(int i = 0; i < maxlen; i++) { // 가로 - 최대길이 만큼 반복
				for(int j = 0; j < 5; j++) { // 세로  - 5
					if(input[j].length() <= i) continue; // 해당 문자열이 끝나면 continue
					sb.append(input[j].charAt(i));
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
