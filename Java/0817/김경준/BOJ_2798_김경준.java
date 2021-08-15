import java.io.*;
import java.util.*;
/**
 * BOJ 2798 블랙잭 : https://www.acmicpc.net/problem/2798
 * 메모리 : 14108KB, 시간 : 132ms
 * 
 * N장의 카드 중 3장을 골라 합을 계산했을 때 M을 넘지 않는 경우의 수 찾기
 * 모든 경우를 탐색해야함 + 고른 카드의 순서는 상관없음 => 조합
 * 
 * 최적화 : 중간에 M과 정확히 일치하는 조합을 찾은 경우 더이상의 탐색은 무의미 (어차피 이게 최선)
 * 
 * Time Complexity
 * - O(nC3) == O(n^3)
 * - N의 최대값은 100이므로 pow(100,3) == 1000000(백만) 충분
 */
public class BOJ_2798_김경준 {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int answer = 0;
		// nC3의 조합 생성
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				for(int k = j + 1; k < N; k++) {
					int num = arr[i] + arr[j] + arr[k];
					if(num < M) {
						if(M - num < M - answer) answer = num;  // M에 더 근접한 수 찾기
					}else if(num == M) {                        // M과 정확히 일치하면 return;
						System.out.print(M);
						return;
					}
				}
			}
		}
		System.out.print(answer);
	}
}
