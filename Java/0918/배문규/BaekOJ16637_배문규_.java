package BaekOJ.study.date0921;

import java.io.*;
import java.util.*;

/*
 * 코드는 제일 간단한데 알듯 말듯 접근법을 떠올리기가 제일 어려웠음
 * 요즘에 잠을 못자서 그런가 머리가 더 안돌아가는 느낌...
 * 이리 저리 접근해보는데만 기본 1~2시간 걸린듯 
 * 
 * 현재 위치에서 바로 뒤에와 괄호 연산만 하면 되는게 아니라,
 * 현재 위치에서 뒤에 2자리 이상 여유가 있으면 뒤 2자리를 먼저 괄호를 치고
 * 자신과 연산을 진행해야지 모든 경우의 수를 찾을 수 있음. 
 * 
 * 1 + 2 * 3 => (1 + 2) * 3
 * 1 + 2 * 3 => 1 + (2 * 3)
 * 
 * 메모리 	시간
 * 14204  128
 */
public class BaekOJ16637_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, num[], result = Integer.MIN_VALUE;
	static char op[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		num = new int[N/2+1];
		op = new char[N/2];
		
		String input = br.readLine();
		for(int i = 0; i < N; i++) {
			if(i%2 == 0) num[i/2] = Character.getNumericValue(input.charAt(i));
			else op[i/2] = input.charAt(i);
		}
		
		dfs(0, num[0]);
		System.out.println(result);
	}
	
	
	public static void dfs(int idx, int total) {
		// 식의 끝까지 도달하면 최대값 찾아서 리턴 
		if(idx == op.length) {
			result = result < total ? total : result;
			return;
		}
		
		// 바로 뒤에 놈이랑 괄호
		int next = calc(total, op[idx], num[idx+1]);
		dfs(idx+1, next);
		
		// 먼저 뒤에랑 뒤에뒤에 놈을 괄호치고 그다음 자신과 연산 
		if(idx + 1 < op.length) {
			int nextnext = calc(num[idx+1], op[idx+1], num[idx+2]);
			nextnext = calc(total, op[idx], nextnext);
			dfs(idx+2, nextnext);
		}
	}
	
	// 부호 연산 
	public static int calc(int left, char operator, int right) {
		switch (operator) {
		case '+': return left+right;
		case '-': return left-right;
		case '*': return left*right;
		default : return 0;
		}
	}
}
