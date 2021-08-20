package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 돌을 1개 or 3개를 가져가기 때문에 
 * 1	- SK, 	2 	- CY,	3 	- SK 이 돌을 가져갈 수 있어서 승리한다.
 * 
 * 그리고 1 ~ 3개 각 개수에서 돌 3개가 추가될 때 마다 상대 턴에서 돌을 가져갈 수 있어서 승리가  바뀜
 * 1+3	- CY, 	2+3 - SK,	3+3 - CY
 * 1+3+3- SK...
 * 
 * 이 승패 패턴 과정이 반복되다 보면
 * 결국엔   N = 홀수 -> SK 승리, 짝수 -> CY 승리
 * 
 * 메모리		시간
 * 14324	140
 */

public class BaekOJ9655_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println(play(Integer.parseInt(br.readLine())));
	}
	
	public static String play(int N) {
		if(N%2 == 0) return "CY";
		else return "SK";
	}

}