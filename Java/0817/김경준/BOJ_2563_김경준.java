import java.io.*;
import java.util.*;
/**
 * BOJ 2563 색종이 : https://www.acmicpc.net/problem/2563
 * 메모리 : 14232KB 시간 : 136ms
 *  
 * 전체 도화지의 사이즈는 100 * 100 이기 때문에, 100 사이즈의 색종이를 붙이는 수는 90 * 90이다.
 * 한번 색종이를 붙이는 데 100의 연산이 필요하기 때문에 90 * 90 * 100을 해도 810,000으로 충분한 수가 나온다.
 * 따라서 검은 영역을 구하기 위해 번거로운 작업을 하는 것보단 단순하게 들어온 모든 색종이에 대해 칠해주는 연산을 수행하는 것이 이득 
 * 
 * Time Complexity
 * - 색종이 붙이기 연산 : O(N*N*N) == O(N^3)
 * - 넓이 구하기 : O(N*N) == O(N^2)
 * ==> O(N^3) <N의 최대값 100>
 */
public class BOJ_2563_김경준 {
	static int N, fst, snd, answer;
	static boolean[][] arr = new boolean[101][101];
	
	static void fill_arr() {
		for(int i = fst; i < fst + 10; i++) {
			for(int j = snd; j < snd + 10; j++) {
				arr[i][j] = true;
			}
		}
	}
	static void count() {
		answer = 0;
		for(int i = 1; i <= 100; i++) {
			for(int j = 1; j <= 100; j++) if(arr[i][j]) answer++; 
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			fst = Integer.parseInt(st.nextToken());
			snd = Integer.parseInt(st.nextToken());
			fill_arr();
		}
		count();
		System.out.println(answer);
	}
}