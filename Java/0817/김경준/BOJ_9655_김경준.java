import java.io.*;
/**
 * BOJ 9655 돌 게임 : https://www.acmicpc.net/problem/2563
 * 메모리 : 14252KB 시간 : 140ms
 * 둘 다 홀수개로 가져가는 상황에서 SK이가 먼저 가져감 
 * 돌이 홀수개면 SK는 홀수인 상황에서 가져가고, CY는 돌이 짝수인 상황에서 가져감 -> 홀수개씩 가져가므로 무조건 SK win (짝수개면 반대) 
 * ==> SK가 이기는 조건은 초기돌이 홀수개, CY가 이기는 조건은 초기돌이 짝수개
 * 
 * Time Complexity
 * - O(1)
 */

public class BOJ_9655_김경준 {
	static int N;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		System.out.print((N % 2 == 1) ? "SK" : "CY");
	}
}
