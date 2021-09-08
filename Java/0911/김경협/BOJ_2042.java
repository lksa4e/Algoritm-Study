import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 2042번 구간 합 구하기
 * 
 * 세그먼트 트리로 구간합의 트리를 만들어서
 * 빠르게 구간합을 출력하는 문제.
 * 거기다가 트리가 수정되는 상황까지 추가되었다.
 * 
 * 값 범위 체크를 제대로 안해줘서 number format 에러
 * 
 * 세그먼트 트리 링크
 * https://www.acmicpc.net/blog/view/9
 * 
 */


public class BOJ_2042 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// input을 받을 arr 배열과 tree를 만들 배열
		long[] arr = new long[N + 1];
		long[] tree = new long[N * 4];

		// 들어오는 input의 형식이 long 범위이다.
		for (int i = 1; i <= N; i++)
			arr[i] = Long.parseLong(br.readLine());

		init(1, N, 1, tree, arr);
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			// c도 input이므로 long 범위로 설정해 줘야한다.
			
			if(a==1) {
				long diff = c - arr[b];
				arr[b] = c;
				update(1,N,1,b,diff, tree);
			} else if (a == 2)
				sb.append(sum(1,N,1,b,(int)c,tree,arr)).append("\n");
		}
		System.out.println(sb);
	}
	
	// 세그먼트 트리 초기화
	static long init(int start, int end, int index, long[] tree, long[] arr) {
		if (start == end)	// 리프 노드까지 도달해서 합이 아닌, 요소들의 값을 꺼내줄 때.
			return tree[index] = arr[start];

		int mid = (start + end) / 2;
		// binary search처럼 mid를 중심으로 둘로 나눠서 재귀로 합을 저장한다.
		return tree[index] = init(start, mid, index * 2, tree, arr) + init(mid + 1, end, index * 2 + 1, tree, arr);
	}

	// left, right가 구간합을 구하려는 범위. start end는 tree의 index.
	static long sum(int start, int end, int index, int left, int right, long[] tree, long[] arr) {
//		1.[left,right]와 [start,end]가 겹치지 않는 경우
//		2.[left,right]가 [start,end]를 완전히 포함하는 경우
//		3.[start,end]가 [left,right]를 완전히 포함하는 경우
//		4.[left,right]와 [start,end]가 겹쳐져 있는 경우 (1, 2, 3 제외한 나머지 경우)
		
		// 1번 경우
		if (left > end || right < start) {
			return 0;
		} else if (left <= start && end <= right) {	// 2번 경우
			return tree[index];
		} else {
			// 3번과 4번 경우
			int mid = (start + end) / 2;
			return sum(start, mid, index * 2, left, right, tree, arr)
					+ sum(mid + 1, end, index * 2 + 1, left, right, tree, arr);
		}
	}
	
	static void update(int start, int end, int index, int idxToEdit, long diff, long[] tree) {
		// 범위 밖에 있는 경우 바로 리턴,
		if (idxToEdit < start || idxToEdit > end)
			return;

		// 범위 안에 있으면 트리를 내려가며 다른 원소도 갱신
		tree[index] += diff;
		if (start == end)
			return;
		
		int mid = (start + end) / 2;
		update(start, mid, index * 2, idxToEdit, diff, tree);
		update(mid + 1, end, index * 2 + 1, idxToEdit, diff, tree);
	}
}
