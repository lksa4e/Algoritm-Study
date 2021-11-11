package BaekOJ.study.date1113;

import java.io.*;
import java.util.*;

/*
 * 백준 1181 그룹 단어 체커
 * 
 * 		메모리 	시간
 * List = 	27448	1908
 * [] = 	23976	568
 * 
 * 중복처리 편하게 하려고 List로 해봤는데 진짜 시간차이 많이남...
 */
public class BaekOJ1181_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N;
	static String[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		arr = new String[N];
		for(int i = 0; i < N; i++) arr[i] = br.readLine();
		
		Arrays.sort(arr, (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
		
		for(int i = 0; i < N; i++) {
			if(i > 0 && arr[i].equals(arr[i-1])) continue;
			System.out.println(arr[i]);
		}
	}
}
