import java.io.*;
import java.util.*;

public class Main2042 {
	
	/*
	 * 초기 tree를 만드는 init()
	 * 배열에서 특정 index 값이 변경 됐을 때 tree에서 상위 노드들의 값을 바꿔주는 update()
	 * 배열에서 left ~ right 까지의 합을 구하는 sum() 
	 * 이 3개의 메소드가 세그먼트 트리의 핵심 로직
	 */

	static long[] arr, tree;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		arr = new long[N+1];
		tree = new long[4*N];
		
		//0. 배열에 숫자값들 입력 받기
		for(int i=1; i<=N ;i++) arr[i] = Long.parseLong(br.readLine());
		
		
		//1. 초기값 세팅 - init()
		init(1, N, 1);
		
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			//2. update() b번째 숫자를 c로 바꾼다
			if(a == 1) {
				long dif = c-arr[b];
				arr[b] = c;
				update(1, N, b, 1, dif);
			
			//3. sum()
			}else if(a == 2) {
				sb.append(sum(1, N, 1, b, (int)c)+"\n");
			}
		}
		
		System.out.println(sb);

	}
	
	static long sum(int start, int end, int node, int left, int right) {
		
		// 배열 범위 밖
		if(left>end || right <start) return 0;
		
		// 범위 안
		if(left<=start && end<=right) return tree[node];
		
		// 두 구간으로 나누어 합 구하기
		int mid = (start+end) / 2;
		return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
		
	}

	static void update(int start, int end, int idx, int node, long diff) {
		
		if(start>idx || end<idx ) return;
		
		//1번(모든 합이 다 담겨있는 노드)부터 내려가며 갱신
		tree[node] += diff;
		
		if(start != end) {
			int mid = (start+end)/2;
			update(start, mid, idx, node*2, diff);
			update(mid+1, end, idx, node*2+1, diff);
		}
	}

	static long init(int start, int end, int node) {

		if(start==end) return tree[node] = arr[start];

		int mid = (start+end)/2;
		
		return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2 + 1);
	}
}