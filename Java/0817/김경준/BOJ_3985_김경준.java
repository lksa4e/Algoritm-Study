import java.io.*;
import java.util.*;
/**
 * BOJ 3985 롤 케이크 : https://www.acmicpc.net/problem/3985
 * 메모리 : 16120KB, 시간 : 160ms
 *  
 * 기대 케이크의 개수 : end - start + 1
 * 실제 케이크의 개수 구하기
 *   입력을 받은 후 start -> end까지 탐색하며 기존에 누가 안가져갔다면 케잌을 집는다
 *   집으면서 케잌개수 ++ 한다
 * 
 * Time Complexity
 * worst case : 1000명이 1000개 조각원할때 
 * O(1000 x 1000) == 100000(십만)
 */
public class BOJ_3985_김경준 {
	static int L, N, hope = 0, hope_idx, fact = 0, fact_idx; 
	static StringTokenizer st;
	static int[][]arr;
	static int[] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		L = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		check = new int[L+1];
		arr = new int[N+1][2]; // 희망, 실제
		
		int flag = 0;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			arr[i][0] = end - start + 1;  // 희망 개수
			for(int j = start; j <= end; j++) {
				if(check[j] == 0) {
					check[j] = 1;
					arr[i][1]++; // 실제 개수
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(hope < arr[i][0]) {
				hope = arr[i][0];
				hope_idx = i;
			}
			if(fact < arr[i][1]) {
				fact = arr[i][1];
				fact_idx = i;
			}
		}
		System.out.print(hope_idx + "\n" + fact_idx);
	}
}
