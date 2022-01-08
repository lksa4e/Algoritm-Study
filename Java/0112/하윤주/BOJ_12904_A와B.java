import java.io.*;

/**
 * [0108] BOJ 12904 A와 B
 * 문자열
 * 
 * sol)
 * T가 S와 길이가 같아질 때까지 맨 뒤 문자를 하나씩 제거하고, T와 S의 길이가 같아지면 같은 문자열인지 비교한다.
 * 이때 맨 뒤 문자가 B일때는 문자열을 뒤집어준 상태로 비교한다.
 *
 */

public class BOJ_12904_A와B {
	static String S, T;
	static int sizeS, sizeT;
	static StringBuilder tempT;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();
		sizeS = S.length();
		sizeT = T.length();
		tempT = new StringBuilder(T);
		
		System.out.println(restoreString());
	}

	private static int restoreString() {
		char lastChar;    // 현재 T의 마지막 문자
		
		for (int i=sizeT-1; i>0; i--) {
			lastChar = tempT.charAt(i);
			
			// T의 길이를 문자 하나씩 줄인다
			tempT.setLength(i);
			// 만약 마지막 문자가 B이면 T문자열을 뒤집는다
			if (lastChar=='B') tempT = tempT.reverse();
			// S와 T의 길이가 같아지면 두 문자열이 일치하는지 비교한다
			if (i==sizeS) {
				if (S.equals(tempT.substring(0, i))) return 1;
				else return 0;
			}
		}
		return 0;
	}

}
