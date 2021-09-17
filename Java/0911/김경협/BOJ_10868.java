import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 10868번 최솟값
 * 
 * 저번에 했던 BOJ 2042번 구간 합 구하기 문제의 최솟값 구하기 버전
 * 세그먼트 트리에서 a~b 구간의 합을 구하는 대신 a~b 구간의 최솟값을 구해줌으로써
 * 최솟값 세그먼트 트리를 완성할 수 있다.
 * 오히려 업데이트 하는 부분이 없어져서 더 쉽게 풀 수 있었다.
 * input이 10억 이하이지만, 최솟값을 구하는 문제이기에 long 범위를  넘지 않는다.
 * 
 * 54484KB	684ms
 */

public class BOJ_10868 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// input을 받을 arr 배열과 tree를 만들 배열
		int[] arr = new int[N + 1];
		int[] tree = new int[N * 4];
		
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		init(1, N, 1, tree, arr);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// start, end, index, left, right
			sb.append(getMin(1,N,1,a,b, tree,arr)).append("\n");
		}
		System.out.println(sb);
	}

	// 세그먼트 트리 초기화
	static int init(int start, int end, int index, int[] tree, int[] arr) {
		if (start == end) // 리프 노드까지 도달해서 요소들의 값을 꺼내줄 때.
			return tree[index] = arr[start];

		int mid = (start + end) / 2;
		// binary search처럼 mid를 중심으로 둘로 나눠서 재귀로 호출 하고, 나온 값으로 둘 중에 최솟 값을 고른다.
		return tree[index] = Math.min(init(start, mid, index * 2, tree, arr),
				init(mid + 1, end, index * 2 + 1, tree, arr));
	}

	// left, right가 구간 최솟값을 구하려는 범위. start end는 tree의 index.
	static int getMin(int start, int end, int index, int left, int right, int[] tree, int[] arr) {
		 /*
		 * 1.[left,right]와 [start,end]가 겹치지 않는 경우
		 * 2.[left,right]가 [start,end]를 완전히 포함하는 경우
		 * 3.[start,end]가 [left,right]를 완전히 포함하는 경우
		 * 4.[left,right]와 [start,end]가 겹쳐져 있는 경우 (1, 2, 3 제외한 나머지 경우)
		 */
		// 1번 경우
		if (left > end || right < start) {
			return Integer.MAX_VALUE;	// 겹치지 않는 경우, 아예 최솟값에 포함되지 않도록 max_value 던져주기
		} else if (left <= start && end <= right) { // 2번 경우
			return tree[index];
		} else {
			// 3번과 4번 경우
			int mid = (start + end) / 2;
			return Math.min(getMin(start, mid, index * 2, left, right, tree, arr),
					getMin(mid + 1, end, index * 2 + 1, left, right, tree, arr));
		}
	}
}
