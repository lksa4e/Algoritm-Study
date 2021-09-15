import java.io.*;
import java.util.*;

/**
 * G3 BOJ 10868 최솟값 :
 * 메모리 : 57752, 시간 : 652ms
 * 
 * 세그먼트 트리의 변형문제
 * 부모에 구간합이 아닌 자식 중 최솟값을 찾아서 저장한다.
 * 
 * 그나마 풀만했네요...
 */

public class BOJ10868_G1_최솟값 {
	static int N,M;
	static int arr[];
	static int segmentTree[];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		segmentTree = new int[N*4];
		arr = new int[N+1];

		for(int i = 1; i <= N; i++) 
			arr[i] = Integer.parseInt(br.readLine());
		
		make_Segment_Tree(1,1,N);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(find_min(1,1,N,start,end) + "\n");
		}
		System.out.print(sb);
	}
	static int find_min(int node, int tree_start, int tree_end, int start, int end) {
		// 찾는 구간이  탐색중인 tree 구간을 완전히 벗어난 경우 -> 쓸모없는값 리턴 (최소이므로 max_value)
		if(tree_start > end || tree_end < start) return Integer.MAX_VALUE;
		// 찾는 구간이 탐색중인 tree 구간 안쪽에 완전히 들어왔을때 -> segmentTree 값 리턴
		if(tree_start >= start && tree_end <= end) return segmentTree[node];
		
		// 위쪽에서 리턴 안된경우 -> 원하는 구역과 찾는 구역이 일부 겹치는 경우
		int mid = (tree_start + tree_end) / 2;
		int left_min = find_min(node * 2, tree_start, mid, start, end);
		int right_min = find_min(node * 2 + 1, mid + 1, tree_end, start, end);
		
		return Math.min(left_min, right_min);
	}
	
	static int make_Segment_Tree(int node, int start, int end) {
		// 리프 노드인 경우
		if(start == end) return segmentTree[node] = arr[start];
		
		int mid = (start + end) / 2;
		// 왼쪽자식, 오른쪽 자식 재귀
	    int left = make_Segment_Tree(node * 2, start, mid);
	    int right = make_Segment_Tree(node * 2 + 1, mid + 1, end);
	    
	    return segmentTree[node] = Math.min(left, right);
	}
}
