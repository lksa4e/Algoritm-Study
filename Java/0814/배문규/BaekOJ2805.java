package study.date0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*	이 문제는 0807때 문제였던 1654번 랜선자르기와 동일한 문제라고 생각 (https://www.acmicpc.net/problem/1654) 
 * 	마찬가지로 파라메트릭 서치를 통해 최적값으로 수렴하는 방식을 사용함.
 * 
 * 	입력을 받기 위해 선언하는 타입에 따른 메모리와 시간 차이
 * 	int[]			메모리 119140	시간 544
 *	Integer[]		메모리 134956	시간 716
 *	List<Integer>	메모리 145320	시간 824
 *  당연하겠지만 primitive type이 성능이 좋다. (https://siyoon210.tistory.com/139)
 */

public class BaekOJ2805 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static int[] tree;
	static int _max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
	    st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());

	    tree = new int[N];
	    st = new StringTokenizer(br.readLine());
	    for(int i = 0; i < N; i++) {
	    	tree[i] = Integer.parseInt(st.nextToken());
	    	// 입력을 받으면서 동시에 서치에 넘겨줄 최대값 저장
	    	if(tree[i] > _max) _max = tree[i]; 
	    }
	    
	    System.out.println(p_search(0, _max));
	}
	
//	 파라메트릭 서치로 최적값을 찾아감
	public static int p_search(int left, int right) {
//		 최적값 수렴
		if(left > right) return right;
		
//		remain이 int형일 때 오버플로우 주의!!
//		오버플로우 테케
//		5 2000000000
//		900000000 900000000 900000000 900000000 900000000
		int pivot = (left + right)/2;
		long remain = 0;
		for(int i : tree) {
			if(i - pivot >= 0) remain += i-pivot;
		}
		
		if(remain >= M) return p_search(pivot+1, right);
		else return p_search(left, pivot-1);
	}
}