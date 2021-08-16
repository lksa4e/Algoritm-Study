package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 처음에 
 * for(int i = 1; i < 10; i++) {
 *		if(Math.abs(total-100 + mushroom[i]) <= Math.abs(total-100)) total += mushroom[i];
 * }
 *  이렇게 조건을 짰더니 틀렸습니다라고 나왔다.
 *  다음 버섯을 포함한 값이 현재보다 100보다 가깝거나 같으면 다음버섯을 먹는다는 조건인데,
 *  절대값이 같으면 버섯을 먹는다는 조건까지도 충족한다.
 *  테스트케이스도 다 맞고 얼핏보면 뭐가 잘못된지 몰랐는데
 *  한번이라도 안먹으면 더이상 먹지 않기 때문에  else break;가 빠진것 이었다.
 *  
 *  메모리	시간
 *  14212	132
 */

public class BaekOJ2851_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, M, L;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		int[] mushroom = new int[10];
		for(int i = 0; i < 10; i++) mushroom[i] = Integer.parseInt(br.readLine());
		
		// 100이해 정수니까 무조건 첫번째는 포함
		int total = mushroom[0];
		// 2번째부터 10번째까지
		for(int i = 1; i < 10; i++) {
			// 현재까지보다 다음 버섯을 포함한 값이 100보다 더 멀다면 break
			if(Math.abs(total-100) < Math.abs(total+mushroom[i]-100)) break;
			// 버섯 먹음
			total += mushroom[i];
		}
		
		System.out.println(total);
	}

}
