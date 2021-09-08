import java.io.*;
import java.util.*;

/**
 * G1 BOJ 2042 구간 합 구하기:
 * 메모리 : 113060kb 시간 : 728ms
 * 
 * 세그먼트 트리 문제
 * 새로운 자료구조에 대한 공부 -> 주석 풀이는 생략. 대신 아래 링크를 첨부
 * 
 * https://yabmoons.tistory.com/431
 * 메인언어를 C++로 사용하기는 하지만 풀이 자체가 너무 자세하고 이해하기 쉽게 설명해줘서 자바 기준으로 봐도 문제없음. 
 * 알고리즘풀다가 새로운 이론 공부할때나 헷갈리면 자주 참고하는편
 * 
 */

public class BOJ2042_G1_구간합구하기 {
	static int N, M, K;
	static long[] segmentTree, arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		segmentTree = new long[4*N];
		arr = new long[N+1];
		
		for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());
		make_SegmentTree(1, 1, N);
		
		for(int i = 1; i <= M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if(op == 1) {
				int src = Integer.parseInt(st.nextToken());
				long target = Long.parseLong(st.nextToken());
				update_SegmentTree(1, 1, N, src, target - arr[src]);
				
				// 사람들 질문보고 간신히 찾아냄
				// 값 업데이트 시 세그먼트트리 뿐만 아니라 원본 배열의 숫자도 바꿔주어야함...
				arr[src] = target;
				
			}else {
				int sum_left = Integer.parseInt(st.nextToken());
				int sum_right = Integer.parseInt(st.nextToken());
				System.out.println(sum(1, 1, N, sum_left, sum_right));
			}
		}
	}
	static long make_SegmentTree(int node, int start, int end) {
		// start와 end가 같다는 것은 리프 노드를 의미한다. 리프 노드는 값을 가짐
		if(start == end) return segmentTree[node] = arr[start];
		
		// 리프 노드가 아닌 경우 -> 왼쪽, 오른쪽 서브트리로부터 구간합을 받아서 저장해야 함
		int mid = (start + end) / 2;
	    long left_result = make_SegmentTree(node * 2, start, mid);
	    long right_result = make_SegmentTree(node * 2 + 1, mid + 1, end);
	    segmentTree[node] = left_result + right_result;
	    
	    return segmentTree[node];
	}
	
	static long sum(int node, int start, int end, int left, int right) {
		// 지금 탐색하고 있는 범위가 원하는 구간합의 범위를 완전 벗어난다면 탐색중지
		if (left > end || right < start) return 0;
		
		// 지금 탐색하고 있는 범위가 원하는 구간합의 범위와 완전히 일치한다면 바로 결과값 return
	    if (left <= start && end <= right) return segmentTree[node];
	 
	    // 지금 탐색하고 있는 범위가 원하는 구간합의 범위와 일부 걸친 경우 -> 더 세분화해서 탐색해야함
	    int Mid = (start + end) / 2;
	    long left_result = sum(node * 2, start, Mid, left, right);
	    long right_result = sum(node * 2 + 1, Mid + 1, end, left, right);
	    return left_result + right_result;
	}
	
	static void update_SegmentTree(int node, int start, int end, int index, long diff) {
		// 지금 탐색하는 범위가 업데이트하려는 범위와 겹치지 않는다면 -> 신경쓸필요 없이 탐색 종료
		if (index < start || index > end) return;
		
		// 위쪽의 if문을 통과해서 왔다는 것은 나의 subtree 중 업데이트 할 자식들이 있다는 것이고
		// subtree가 업데이트되면 구간합을 가지고 있는 세그먼트 트리의 특성으로 인해 부모의 구간합도 변경이 일어남
	    segmentTree[node] += diff;
	 
	    // start 와 end가 같지 않다면 탐색을 더 진행
	    if (start != end)
	    {
	        int mid = (start + end) / 2;
	        update_SegmentTree(node * 2, start, mid, index, diff);
	        update_SegmentTree(node * 2 + 1, mid + 1, end, index, diff);
	    }
	}

}
