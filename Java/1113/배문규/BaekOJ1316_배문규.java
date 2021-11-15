package BaekOJ.study.date1113;

import java.io.*;
import java.util.*;

/*
 * 백준 1316 그룹 단어 체커
 * 
 * 쉬운 문자열 문제를 풀어볼수록 코테에서 풀이 과정중 일부분으로 자주 보였던 느낌임
 * 푸는 것자체는 쉽게 풀 수있으나 자바언어가 상대적으로 문자열처리에서 별로기 때문에 
 * 어떻게 하면 더 효율적으로 풀 수있는지는 은근 고민됨
 * 
 * set으로 단어를 다 더하고 연달아 나오지 않는 수를 카운트해서 그룹단어 체크해봄
 * 
 * 메모리 	시간
 * 14252	124
 */

public class BaekOJ1316_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, cnt;
	static Set<Character> set = new HashSet<Character>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) countGroup(br.readLine());

		System.out.println(cnt);
	}
	
	public static void countGroup(String str) {
		int count = 0;
		set.clear();
		
		for(int i = 0; i < str.length(); i++) {
			if(i > 0 && str.charAt(i) != str.charAt(i-1)) count++; // 이전 알파벳과 다른 알파벳이 나타나면 카운트
			set.add(str.charAt(i)); // Set에 다 더함
		}
		
		// length()-1번 비교했으니  count+1하고 set이랑 사이즈 비교 
		if(count+1 == set.size()) cnt++; 
	}
}
