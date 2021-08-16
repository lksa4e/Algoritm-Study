import java.util.Scanner;

/**
 * [0817] BOJ 9655 돌 게임
 *
 * sol) DP 문제인데 짝홀에 따라 답이 달라지므로 짝홀로 풀었습니다.
 * tc) O(1)
 */

public class BOJ9655 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		if (N%2 == 0) System.out.println("CY");
		else System.out.println("SK");

	}

}
