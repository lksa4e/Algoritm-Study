import java.io.*;
import java.util.*;
/**
 * BOJ 1592 영식이와 친구들 : https://www.acmicpc.net/problem/1592
 * 메모리 : 14188KB, 시간 : 140ms
 * 
 * N 사이즈의 arr[] 를 원형 테이블로 생각하고 풀이
 * 시계방향으로 돌리는 경우 nidx = (idx + 이동거리) % N
 * 반시계 방향으로 돌리는 경우 nidx = (idx - 이동거리) > 0 ? idx - 이동거리 : idx - 이동거리 + N; 
 *
 * Time Complexity
 * - worst case의 경우 N명이 M-1개씩 모두 받고 마지막으로 M개를 받는 경우
 * - O(N * M) 
 */
public class BOJ_1592_김경준 {
	static int N,M,L;
	static int arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N];
		int cur_idx = 0, cnt = 0;
		while(true) {
			arr[cur_idx]++;
			if(arr[cur_idx] == M) break;

			if(arr[cur_idx] % 2 == 1) { // 받은 횟수 홀수 - 시계방향
				cur_idx = (cur_idx + L) % N;
			}else { // 받은 횟수 짝수 - 반시계 방향
				cur_idx -= L;
				if(cur_idx < 0) cur_idx += N;
			}
			cnt++;
		}
		System.out.println(cnt);
	}
}
