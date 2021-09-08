import java.util.*;
import java.io.*;

/**
 * 백준 2042 구간 합 구하기 
 * 
 * 풀이 : 세그먼트 트리 
 * 
 * 함정이 많았던 문제.. 역시 정답률이 낮은 이유가 있었다!
 * 1. 주어지는 모든 수가 -2^63 ~ 2^63-1 이라는 큰 값이라 long형으로 선언해주어야 한다는 점 
 * 2. 특정 원소의 값을 update해줄 때 b - input[a]값을 넘겨주어야 한다는 점 
 * 
 * 110628KB	600ms
 */
public class Solution2042_김다빈 {

	static long[] input, tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		input = new long[N+1];
		tree = new long[4 * N];	// 4를 곱하면 모든 범위 커버할 수 있다. (갯수에 대해서 2의 제곱 형태의 길이를 가져서) 
		
		for (int i = 1; i <= N; i++) {
			input[i] = Long.parseLong(br.readLine());
		}
		
		// 세그먼트 트리 구성하기 
		init(1, N, 1);
		
		for (int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int operator = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			if(operator == 1) {	// a -> b 변경 = a번째 노드를 포함하는 구간의 합에 b-input[a]!!! 더해주기  
				long dif = b-input[a];
				input[a] = b;
				update(1, N, 1, a, dif);
			} else {	// a ~ b 합 
				sb.append(sum(1, N, 1, a, (int)b) + "\n");
			}
		}
		
		System.out.println(sb);
	}

	/**
	 * 세그먼트 트리 생성 함수 
	 * @param start 시작 인덱스
	 * @param end 끝 인덱스 
	 * @param node 현재 노드의 인덱스 
	 */
	private static long init(int start, int end, int node) {
		// leaf node가 되면 값 그대로 저장 
		if(start == end) return tree[node] = input[start];
		
		int mid = (start + end) / 2;
		// 재귀적으로 왼쪽, 오른쪽 두 부분으로 나눈 뒤 그 합을 tree에 저장 
		return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
	}
	
	/**
	 * 특정 원소의 값을 수정하는 함수 
	 * @param index 구간 합을 수정하고자 하는 노드
	 * @param dif 수정할 값 
	 */
	private static void update(int start, int end, int node, int index, long dif) {
		// 범위 밖에 있는 경우 
		if(index < start || index > end) return;
		
		// 범위 안에 있으면 타고 내려가면서 다른 원소들도 갱신 
		tree[node] += dif;
		if(start == end) return;
		
		int mid = (start + end) / 2;
		update(start, mid, node*2, index, dif);		// 왼쪽 노드 업데이트 
		update(mid+1, end, node*2+1, index, dif);	// 오른쪽 노드 업데이트 
	}
	
	/**
	 * 구간 합을 구하는 함수 
	 * @param left, right 구간 합을 구하고자 하는 범위  
	 */
	private static long sum(int start, int end, int node, int left, int right) {
		// [left, right] 와 [start, end]가 전혀 겹치지 않는 경우
		if(left > end || right < start) return 0;
		
		// [start, end] 가 [left, right]에 속해 있는 경우
		if(left <= start && right >= end) return tree[node];
		
		// 그렇지 않다면 왼쪽, 오른쪽 두 부분으로 나누어 합 구하기 
		int mid = (start + end) / 2;
		return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
	}
}
