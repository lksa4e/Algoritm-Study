import java.io.*;
import java.util.*;

/**
 * 백준 10868번 최소값
 * 
 * 풀이 : 세그먼트 트리 이용 
 * 
 * 세그먼트 트리를 이용하지만 이전 구간 합 구하기 문제와 다른 점은
 * 노드에 구간의 합을 저장하는 것이 아닌 구간 중 '최소값'을 저장해준다.
 * 
 * 따라서 세그먼트 트리를 생성할 때 똑같이 leaf node에는 입력값 자체를 넣어주지만
 * 그 값을 이용하여 부모 노드에 값을 저장할 때 자식 노드 중 최소값을 저장! (= 구간의 최소값)
 * 
 * 구간의 최소값을 구할 때는 해당 구간의 이미 저장되어있는 최소값을 리턴해주면 된다.
 * 
 * 57948KB	720ms
 */

public class Solution10868_김다빈 {

	// 각각의 정수들은 1이상 1,000,000,000(10억)이하의 값을 가지지만 
	// 최소값을 노드로 가지고 있기 때문에 int로 저장
	static int[] inputs, trees;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		inputs = new int[N+1];
		trees = new int[4*N];
		
		for (int i = 1; i <= N; i++) {
			inputs[i] = Integer.parseInt(br.readLine());
		}
		
		// 세그먼트 트리 생성 
		init(1, N, 1);
		
		// 구간에서 최소값 구하기 
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			// 구간 최소값 구하기
			sb.append(searchMin(1, N, 1, left, right)+"\n");
		}
		
		System.out.println(sb);
	}

	private static int init(int start, int end, int node) {
		// leaf node인 경우 
		if(start == end) return trees[node] = inputs[start];
		
		int mid = (start + end)/2;
		int left = init(start, mid, node * 2);
		int right = init(mid+1, end, node * 2 + 1);
		
		// 자식 노드 중 최소값을 부모 노드에 저장해줌
		return trees[node] = (left < right) ? left : right;
	}

	private static int searchMin(int start, int end, int node, int left, int right) {
		// [left, right], [start, end] 구간이 전혀 겹치지 않는 경우 -> 최대값으로 리턴 
		if(left > end || right < start) return Integer.MAX_VALUE;
		
		// [start, end] 가 [left, right]에 속해 있는 경우 -> 해당 노드 값을 리턴 
		if(left <= start && right >= end) return trees[node];
		
		int mid = (start + end)/2;
		// 그렇지 않다면 왼쪽, 오른쪽 두 부분으로 나누어 비교한 후 최소값 리턴 
		int left_min = searchMin(start, mid, node*2, left, right);
		int right_min = searchMin(mid+1, end, node*2+1, left, right);
		
		return (left_min < right_min) ? left_min : right_min;
	}
}
