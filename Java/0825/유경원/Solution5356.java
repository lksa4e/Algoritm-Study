package swea;

import java.io.*;
import java.util.*;

class Solution5356 {
	/*
	 * 각 테케는 5섯줄이고 1~15 길이의 문자열이 주어진다고 했으므로
	 * 5*15 크기의 char배열을 만들어서 입력받는다
	 * 열반복, 행반복을 돌면서 공백이 아니라면 stringBuilder에 append한다.
	 * 전부 공백인 열이라면 종료
	 */
	static int T;
	static char[][] word;
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("C:\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			word = new char[5][15];
			for(int i=0; i<5; i++) {
				String s = br.readLine();
				for(int j=0; j<s.length(); j++) {
					word[i][j] = s.charAt(j);
				}
			}
			
			sb.append("#").append(testCase).append(" ");
			
			for(int i=0; i<15; i++) {
				boolean flag = true;
				for(int j=0; j<5; j++) {
					if(word[j][i] != '\u0000') { // 공백이 아니면 append
						sb.append(word[j][i]);
						flag = false;
					}
				}
				if(flag) break; // 전부 공백인 열이면 종료
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
}
