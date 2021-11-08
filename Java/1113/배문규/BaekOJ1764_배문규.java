package BaekOJ.study.date1113;

import java.io.*;
import java.util.*;

/*
 * 백준 1764 듣보잡
 * 
 * 1줄짜리 문제라서 너무 행복...
 * 
 * 단어가 최대 2번까지만 중복이 되니까 N+M을 모두 하나의 배열에 입력받고 정렬한 다음 
 * 이전 단어와 비교하여 단어가 같으면 StringBuilder에 추가함
 * 
 * 메모리 	시간
 * 24808	316
 */
public class BaekOJ1764_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, M;
	static String[] str;

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		str = new String[N+M];
		for(int i = 0; i < N+M; i++) str[i] = br.readLine();
		
		int cnt = 0;
		Arrays.sort(str);
		for(int i = 1; i < N+M; i++) {
			if(str[i].equals(str[i-1])) {
				cnt++;
				sb.append(str[i]).append("\n");
			}
		}
		
		System.out.println(cnt+"\n"+sb);
	}
}
