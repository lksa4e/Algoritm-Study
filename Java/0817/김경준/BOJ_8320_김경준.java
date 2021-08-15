import java.io.*;
import java.util.*;
/**
 * BOJ 8320 직사각형을 만드는 방법 : https://www.acmicpc.net/problem/8320
 * 메모리 : 14180KB 시간 : 176ms
 * 
 * 높이를 기준으로 삼는다.
 * 높이가 1인 경우 만들 수 있는 직사각형  1 x 1, 1 x 2, 1 x 3,,,, 1 x N
 * 높이가 2인 경우 만들 수 있는 직사각형  2 x 2, 2 x 3, 2 x 4,,,, while(2 x ㅁ <= N)
 * 
 * 높이가 a 인 경우 이전과 중복되지 않는 직사각형을 만드려면 뒤에 곱하는 숫자는 a보다 작으면 안된다.
 * 
 *  Time Complexity
 *  - O(N^2)
 */
public class BOJ_8320_김경준 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = i; j <=N; j++) {
				if(i * j <= N) cnt++;
			}
		}
		System.out.print(cnt);
	}
}
