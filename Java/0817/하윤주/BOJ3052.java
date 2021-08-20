import java.util.Arrays;
import java.util.Scanner;

/**
 * [0817] BOJ 3052 나머지
 *
 * sol) 나머지 구하기
 * tc) O(N)
 */

public class BOJ3052 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] leftover = new int[42];    // 나머지를 인덱스로 갖는 배열 생성
		Arrays.fill(leftover, -1);
		
		int answer = 0;
		int tc=10;
		
		while(tc-->0) {
			int n = sc.nextInt() % 42;
			leftover[n]++;     // 나머지가 등장하는 횟수 카운트 업
		}
		
		for (int i=0; i<42; i++) {
			if (leftover[i] != -1) answer++;    // 등장했던 적이 있는 나머지면 카운트
		}
		
		System.out.println(answer);

	}

}
