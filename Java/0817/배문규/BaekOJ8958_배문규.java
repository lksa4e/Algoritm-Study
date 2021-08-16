package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	total = 0, score = 1로 초기화
 * 	O가 연속되지 않으면 다시 score = 1부터 시작
 * 	O가 연속되면 total += score++
 * 	return total
 * 	위 로직 그대로 메소드를 구현
 * 
 * 	메모리	시간
 * 	14248	136
 */
public class BaekOJ8958_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer sb = new StringBuffer();
    
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) System.out.println(OX(br.readLine()));

	}
	
	public static int OX(String str) {
		int total = 0;
		int score = 1;
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == 'X') score = 1;
			else total += score++;
		}
		
		return total;
	}

}
