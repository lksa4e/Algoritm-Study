package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 그냥 소트하지 않고 풀어봄
 * A <= B 일때 A <= C가 아니면 A가 중간 수, A <= C일 때 B <= C면 B가 중간수 아니면 C가 중간수
 * A <= B 가 아닐 때, B <= C 가 아니면 B가 중간수, B <= C일 때 A <= C면 A가 중간수 아니면 C가 중간수
 * 이렇게 삼항연산자를 쓰니 가독성은 최악이라 생각
 * 
 * 메모리 	시간
 * 14204	132  
 */

public class BaekOJ10817_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		System.out.println(A <= B ? A <= C ? B <= C ? B : C : A :  B <= C ? A <= C ? A : C : B);
	}

}
