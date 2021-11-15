package BaekOJ.study.date1120;

/*
 * 백준 1213 펠린드롬 만들기
 * 
 * 펠린드롬을 여러개 만들 수 있다면 사전순으로 앞서는 것을 출력한다.
 * 
 * 펠린드롬이 불가능한 조건 : 홀수개인 알파벳이 2개 이상
 * 
 * 나이브하게 풀기 싫어서 나름 최적화를 시도 해보려고 했으나 깔끔하게 풀기는 쉽지 않았음
 * 
 * 메모리 	시간
 * 14308	124
 */

import java.io.*;
import java.util.*;

public class BaekOJ1213_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sbFront = new StringBuilder();
	static StringBuilder sbBack = new StringBuilder();
	
	static int[] arr = new int[26];
	public static void main(String[] args) throws NumberFormatException, IOException {
		String input = br.readLine();
		// 각 알파벳이 몇 개 나왔는지 카운트
		for(int i = 0; i < input.length(); i++) arr[input.charAt(i)-'A']++;
		
		// 홀수개인 알파벳을 카운트하고, 어떤 알파벳이 홀수 개 나왔는지 기록함
		int cnt = 0;
		int idx = -1;
		for(int i = 0; i < 26; i++) {
			if(arr[i] % 2 == 1) {
				cnt++;
				idx = i;
			}
		}
		
		// 홀수개인 알파벳이 2개 이상이면 쏘리 출력
		if(cnt >= 2) System.out.println("I'm Sorry Hansoo");
		else {
			// A -> Z까지 순차탐색
			for(int i = 0; i < 26; i++) {
				int num = arr[i];
				if(num == 0) continue; // 0개 등장한 알파벳은 패스
				
				char ch = (char)(i+65);
				// 홀수가 등장한 알파벳은 1개 빼고, 대칭을 이룰 스트링빌더에 각각 추가
				if(num % 2 == 1) { 
					for(int j = 0; j < (num-1)/2; j++) {
						sbFront.append(ch);
						sbBack.append(ch);
					}
				}
				// 짝수개가 등장한 알파벳은 바로 대칭을 이룰 스트링빌더에 각각 추가
				else {
					for(int j = 0; j < num/2; j++) {
						sbFront.append(ch);
						sbBack.append(ch);
					}
				}
			}
			
			// 홀수개인 알파벳이 존재하는 문제열이었다면 중간에 해당 알파벳 추가
			if(cnt == 1) sbFront.append((char)(idx+65));
			// 뒤에 문자열을 뒤집어서 앞에 문자열에 붙혀 줌으로써 펠린드롬 완성
			System.out.println(sbFront.append(sbBack.reverse()));
		}
	}
}
