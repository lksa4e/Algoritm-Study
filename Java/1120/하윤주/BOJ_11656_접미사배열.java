import java.io.*;
import java.util.*;

/**
 * [1116] BOJ 11656 접미사 배열
 * 문자열, 정렬, StringBuilder
 * 
 * sol)
 * 문자열을 char형 배열로 받아 마지막 char부터 하나씩 앞으로 이어붙여 부분 접미사를 만든다.
 * 이후 배열에 넣고 정렬 뒤 출력
 * 
 */

public class BOJ_11656_접미사배열 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();               // 입력 스트링을 char 배열로
		
		int size = S.length;
		String[] suffixes = new String[size];
		
		StringBuilder sb = new StringBuilder();
		for (int i=size-1; i>=0; i--) {
			suffixes[i] = sb.insert(0, S[i]).toString();       // 마지막 char부터 하나씩 앞에 이어붙임
		}
		
		Arrays.sort(suffixes);                                 // 정렬 뒤 출력
		sb.setLength(0);
		for (String suffix : suffixes) {
			sb.append(suffix).append("\n");
		}
		System.out.println(sb);
	}

}
