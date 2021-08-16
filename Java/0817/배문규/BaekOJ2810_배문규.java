package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 좌에서 우로 커플석일 때, 일반석일 때를 구분하여 컵홀더 체크를 하였음.
 * 커플석일 때, 첫번 째 자리인지 두번 째 자리인지를 체크하였고 첫번째 자리인데 좌측에 컵홀더를 사용할 수 있으면 사용한다.
 * 그리고 두번째 좌석이면 무조건 우측 컵홀더를 사용한다.
 * 일반석일 때, 좌측에 컵홀더가 비면 컵홀더를 사용한다.
 * 그리고 좌측에 사용중이면 우측 컵홀더를 사용한다.
 * 가장 단순한 방법인 문제를 보면서 머리속에서 어떻게 답을 찾아가는지 생각한 그대로 구현하였다.
 * 
 * 메모리 	시간
 * 14200	148
 */

public class BaekOJ2810_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		int N = Integer.parseInt(br.readLine());
		char[] seat = br.readLine().toCharArray();
		boolean[] cupHolder = new boolean[N+1];
		
		int coupleCnt = 0;
		for (int i = 0; i < N; i++) {
			if (seat[i] == 'L') {
				coupleCnt++;
				if(coupleCnt%2 == 1 && !cupHolder[i]) cupHolder[i] = true;
				else if(coupleCnt%2 == 0) cupHolder[i+1] = true;
			}
			else {
				if(!cupHolder[i]) cupHolder[i] = true;
				else cupHolder[i+1] = true;
			}
		}
		
		int result = 0;
		for(boolean hasHolder : cupHolder) {
			if(hasHolder) result++;
		}
		
		System.out.println(result);
	}

}
