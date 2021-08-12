/**
문제 링크 : https://www.acmicpc.net/problem/11728
뻘짓리스트 
              풀이방법                                                                                                                        | 메모리      | 시간
N+M size Array, N+M 사이즈 한번에 sort                                 | 313988 | 1832
N+M size Array, While문 Merge sort, for문 나머지처리(한쪽 다 담고 남은거 몰아서) | 314992 | 1836
String Array, While문 Merge sort, for문 나머지 처리(st.nextToken제거)     | 417372 | 1636 
Priority Queue 사용, 전부 뽑아서 출력                                                                                | 346256 | 2460
N+M size Array, for문을 통한 merge sort (조건문 개수 줄이기)                | 302380 | 1568

*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P14888 {
	static int N, num, max_answer = Integer.MIN_VALUE, min_answer = Integer.MAX_VALUE;
	static int[] arr, op;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int [N];
		op = new int[N-1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			num = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num; j++) op[cnt++] = i;
		}
		
		do {
			solve();
		}while(next_permutation(op));
		System.out.print(max_answer + "\n" + min_answer);
	}
	static void solve() {
		int sum = arr[0];
		for(int i = 0; i < N - 1; i++) {
			if(op[i] == 0) sum += arr[i+1];
			else if(op[i] == 1) sum -= arr[i+1];
			else if(op[i] == 2) sum *= arr[i+1];
			else sum /= arr[i+1];
		}
		max_answer = Math.max(sum, max_answer);
		min_answer = Math.min(sum, min_answer);
	}
	static boolean next_permutation(int[] arr) {
		int N = arr.length;
		int i = N-1;
		// 뒤쪽 출발, 내림차 아닐 때
		while(i > 0 && arr[i-1] >= arr[i]) --i;
		if(i == 0) return false;
		
		int j = N-1;
	    // 뒤쪽 중 가장 작은숫자
		while(arr[i-1] >= arr[j]) --j;
		swap(arr, i-1,j);
		// sort
		int k = N-1;
		while(i < k) swap(arr, i++, k--);
		return true;
	}
	static void swap(int[] arr, int fst, int snd) {
		int temp = arr[fst];
		arr[fst] = arr[snd];
		arr[snd] = temp;
	}

}
