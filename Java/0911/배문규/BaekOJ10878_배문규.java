package BaekOJ.study.date0911;

import java.io.*;
import java.util.*;

/*
 * 지난번 구간합 문제와 거의 동일한 베이직한 세그먼트 트리문제
 * 부모노드에 자식노드의 합 => 부모노드에 자식노드의 최솟값으로 변경된 것 말고는 동일하다.
 * 
 * 그리고 트리 생성시 0으로 초기화 되기때문에, max값으로 다시 초기화 시킴
 * 
 * 메모리 	시간
 * 58656	768
 */
public class BaekOJ10878_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, M, x, max_size, sTree[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		initTree();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(minimum(1, max_size/2, a, b, 1)).append("\n");
		}
		
		System.out.println(sb);
	}

	// 세그먼트 트리 생성
	public static void initTree() throws NumberFormatException, IOException {
		x = (int)(Math.ceil(Math.log(N) / Math.log(2)));
		max_size = 2 * (int) Math.pow(2, x);
		sTree = new int[max_size];
		Arrays.fill(sTree, 1000000000); // 최대값 세팅
		
		for(int i = max_size/2; i < max_size/2+N; i++) // 리프 노드 입력
			sTree[i] =Integer.parseInt(br.readLine());
		for(int i = max_size/2-1; i > 0; i--) // 루트 까지 부모노드에 자식노드값의 최솟값
			sTree[i] = Math.min(sTree[i*2], sTree[i*2+1]);
	}
	
	// 구간 최솟값 찾기
	public static int minimum(int treeStart, int treeEnd, int queryStart, int queryEnd, int root) {
		if(treeStart > queryEnd || treeEnd < queryStart) return 1000000000; // 찾으려는 구간 완전 밖
		if(queryStart <= treeStart && queryEnd >= treeEnd) return sTree[root]; // 찾으려는 구간 완전 포함
		
		int mid = (treeStart+treeEnd)/2; // 트리 중앙 분할
		return Math.min(minimum(treeStart, mid, queryStart, queryEnd, 2*root), minimum(mid+1, treeEnd, queryStart, queryEnd, 2*root+1));
	}
}