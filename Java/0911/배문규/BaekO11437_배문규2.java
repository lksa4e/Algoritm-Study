package BaekOJ.study.date0911;

import java.io.*;
import java.util.*;

/*
 *	같은 노드의 정보를 저장하는 배열이 여러개라 노드객체 여러 정보들을 관리해봄
 *	메모리는 동일하나 시간이 거의 배로 오래걸림
 *	
 *	메모리	시간
 *	43764	2524
 */

class Node{
	int parent;
	int depth;
	List<Integer> link = new ArrayList<Integer>();
}

public class BaekO11437_배문규2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, M;
	static Node tree[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		tree = new Node[N+1];
		for(int i = 1; i <= N; i++) tree[i] = new Node();
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			tree[n1].link.add(n2);
			tree[n2].link.add(n1);
		}
		
		setDepth(1, 1);
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			sb.append(LCA(n1, n2)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void setDepth(int p, int d) {
		tree[p].depth = d;
		for(int c : tree[p].link) {
			if(tree[c].depth != 0) continue;
			tree[c].parent = p;
			setDepth(c, d+1);
		}
	}
	
	public static int LCA(int n1, int n2) {
		while(n1 != n2) {
			if(tree[n1].depth > tree[n2].depth) n1 = tree[n1].parent;
			else if(tree[n1].depth < tree[n2].depth) n2 = tree[n2].parent;
			else {
				n1 = tree[n1].parent;
				n2 = tree[n2].parent;
			}
		}
		return n1;
	}
}
