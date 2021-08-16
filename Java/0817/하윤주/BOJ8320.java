
import java.util.Scanner;

/**
 * [0817] BOJ 8320 직사각형을 만드는 방법
 *
 * sol) 약수 구하기
 * tc) O(N^2)
 */

public class BOJ8320 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] nums = new int[10001];
		nums[1] = 1;
		
		int N = sc.nextInt();
		
		for (int j=1; j<=N; j++) {
			int answer = 0;
			for (int i=1; i<=j; i++) {
				if (j%i == 0) {   // 약수구해서
					answer++;
				}
			}
			answer = (answer%2) == 0 ? answer/2 : answer/2+1;    // 약수 홀수개와 짝수개 각각 다르게 처리
			nums[j] = nums[j-1] + answer;     // N까지의 약수 더해나가기
		}
		
		System.out.println(nums[N]);

	}

}
