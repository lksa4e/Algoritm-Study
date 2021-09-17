import java.io.*;
import java.util.*;

/**
 * G1 BOJ 17435 합성함수와 쿼리 :
 * 풀다가 시간초과나서 실패한 방법
 * 
 * cycle이 반복되는 주기를 구하고, cycle의 %만큼만 연산 수행
 * 하지만 최악의 경우 cycle의 주기가 10만 이상으로 갈 수 있다.. 당연하게 시간초과함 
 * 
 */

public class BOJ17435_G1_합성함수와쿼리 {
	static int N,M;
	static int arr[], cycle[];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		cycle = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) get_cycle(i, arr[i], 0);
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			int cycle_num = cycle[x];
			int loop_num = (n-1) % cycle_num;
			
			x = arr[x];
			while(loop_num-- > 0) {
				x = arr[x];
			}
			
			sb.append(x + "\n");
		}
		System.out.print(sb);
	}
	
	static void get_cycle(int index, int num, int depth) {
		if(depth != 0) {
			if(arr[index] == num) {
				cycle[index] = depth;
				return;
			}
		}
		get_cycle(index, arr[num], depth + 1);
	}
}
