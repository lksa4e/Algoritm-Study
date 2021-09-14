import java.io.*;
import java.util.*;

public class Main10868 {
	 
	/*
	 * 세그먼트트리 기본틀에서 더했던것을 최솟값구하는 것으로 살짝 바꿔주면 된다
	 * init에서 각 노드에 구간의 최솟값으로 설정
	 * 주어진 구간의 합을 구하던 sum함수를 최솟값을 리턴하도록 수정
	 * 이때 범위 밖일 경우 Integer.MAX_VALUE를 리턴해야 함
	 */

	static int[] arr, tree;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		tree = new int[4*N];
		
		for(int i=1; i<=N ;i++) arr[i] = Integer.parseInt(br.readLine());
		
		init(1, N, 1);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(findMin(1, N, 1, a, b)+"\n");
		}
		
		System.out.println(sb);

	}
	
	private static int findMin(int start, int end, int node, int left, int right) {
		if(left>end || right <start) return Integer.MAX_VALUE;
		if(left<=start && end<=right) return tree[node];
		int mid = (start+end) / 2;
		return Math.min(findMin(start, mid, node*2, left, right), findMin(mid+1, end, node*2+1, left, right));
	}

	private static int init(int start, int end, int node) {

		if(start==end) return tree[node] = arr[start];

		int mid = (start+end)/2;
		
		return tree[node] = Math.min(init(start, mid, node*2), init(mid+1, end, node*2 + 1));
	}
}