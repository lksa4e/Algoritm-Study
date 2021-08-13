package P0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 14888번 연산자 끼워넣기
 * 풀이 : 순열로 가능한 연산자 조합을 구한 후, 계산 
 * 
 */
public class Solution14888_김다빈 {

	static int N, max, min;
	static int[] numbers;
	static int[] operator;
	static int[] order;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		operator = new int[N-1];
		order = new int[N-1];
		isSelected = new boolean[N-1];
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		// 연산자 개수만큼 배열에 저장
		// 0:+, 1:-, 2:*, 3:/
		st = new StringTokenizer(br.readLine());
		int cnt = 0;
		for(int i=0;i<4;i++) {
			int iter = Integer.parseInt(st.nextToken());
			
			for(int j=0;j<iter;j++) {
				operator[cnt++] = i;
			}
		}
		
		permutation(0);
		
		System.out.println(max);
		System.out.println(min);
	}

	// 순열을 이용해 모든 경우의 수 계산 
	private static void permutation(int cnt) {
		if(cnt == N-1) {
			calculate();
			return;
		}
		
		for(int i=0;i<N-1;i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			order[cnt] = operator[i];
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}

	// 구한 연산자 조합을 이용해 계산 
	private static void calculate() {
		int result = numbers[0];
		for(int i=0;i<N-1;i++) {
			switch(order[i]) {
			case 0:	// +
				result = result + numbers[i+1];
				break;
			case 1:	// -
				result = result - numbers[i+1];
				break;
			case 2:	// * 
				result = result * numbers[i+1];
				break;
			case 3:	// Java는 C++과 같은 나눗셈 방식 
				result = result / numbers[i+1];
			}
		}
		
		if(result > max) max = result;
		if(result < min) min = result;
	}

}
