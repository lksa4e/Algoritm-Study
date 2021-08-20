/**
	문제 링크 : https://www.acmicpc.net/problem/14888
	모든 연산자 경우의 수를 파악해야 하므로 순열 or 조합 풀이
	동일 연산자는 순서가 바뀌는 것이 의미 없으므로 조합으로 풀어야 함
	수업시간에 배운 next_permutation을 이용한 풀이
	숫자를 이용한 next_permutation 활용을 위해 + : 0, - : 1, * : 2, / : 3 으로 매팽하여 사용
	ex) [0,0,1,1,2,2,3] 의 경우  + + - - * * / 순서의 연산자
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
