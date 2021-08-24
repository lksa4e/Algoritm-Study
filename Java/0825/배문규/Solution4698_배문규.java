package SWEA.study0825;

import java.io.*;
import java.util.*;

/*
 * 에라토스테네스의 체를 활용하는 문제는 맞는데
 * SWEA 같은 경우는 프로그램을 1번만 실행하고 테케 for문을 반복적으로 돌리기 때문에
 * for문 밖에서 미리 최대범위의 소수를 만들어 놓는것이 좋음.
 * 그전까진 set으로 보통 소수를 관리했었는데 별로 안좋은것 같아서
 * boolean으로 소수가 아닌 녀석들을 찾아 토글해주는 방식으로 문제에 접근해봄
 * 
 * 메모리		시간
 * 20,876 	218 
 */
public class Solution4698_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, D, A, B, cnt;
	static boolean prime[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		getPrime();
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			sb.append("#").append(tc).append(" ").append(cnt_D(D)).append("\n");
		}
		System.out.println(sb);
	}
	
	// 미리 소수를 구함
	public static void getPrime() {
		prime = new boolean[1000001];
		prime[0] = prime[1] = true;
		
		// 에라토스테네스의 체 : 소수를 찾으려는 범위의 제곱근 까지만 접근하고, 범위 안의 접근한 수의 배수를 제거하여 소수를 찾는 방법
		// i는 제곱근
		for(int i = 2; i <= Math.sqrt(prime.length); i++) {
			if(prime[i]) continue;
			// 배수 제거
			for(int j = i * 2; j < prime.length; j += i) {
				prime[j] = true;
			}
		}
	}
	
	// A부터 B사이에 D가 있는지 찾아서 카운트
	public static int cnt_D(int D) {
		int cnt = 0;
		for(int i = A; i <= B; i++) {
			if(!prime[i]) {
				int temp = i;
				while(temp > 0) {
					if(temp%10 == D) {
						cnt++;
						break;
					}
					temp /= 10;
				}
			}
		}
		return cnt;
	}
}