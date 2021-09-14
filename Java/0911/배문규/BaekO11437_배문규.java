package BaekOJ.study.date0911;

import java.io.*;
import java.util.*;

/*
 * 풀이 개념을 알면 구현은 어렵지 않은데, 제로베이스에서 풀이 개념을 떠올리기는 쉽지 않은 문제라고 생각
 * 트리를 무향 그래프처럼 입력받고, 루트가 미리 정해져 있으므로 루트에서부터 트리를 구성함
 * 트리를 dfs로 구성하는 과정에서 각 노드의 부모와 뎁스를 기록해둔다.
 * 
 * 이 후 가장 가까운 공통 조상을 알고싶은 두 노드들을 입력받으면, 
 * 1. 두 노드의 가장 먼저 뎁스를 통일시킴 (부모를 호출하며 뎁스를 역행)
 * 2. 뎁스가 같아지면, 같이 뎁스를 역행하며 동일한 조상이 나타날 때 까지 반복
 * 
 * 메모리 	시간
 * 43284	1348
 */
public class BaekO11437_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, M, depth[], parent[];
	static List<Integer> tree[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		tree = new List[N+1];
		for(int i = 1; i <= N; i++) tree[i] = new ArrayList<Integer>();
		
		// 무향 그래프 생성
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			tree[n1].add(n2);
			tree[n2].add(n1);
		}
		
		// 트리를 구성하며 뎁스, 부모를 기록
		depth = new int[N+1];
		parent = new int[N+1];
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

	// dfs로 루트에서부터 시작해서 트리를 구성하고, 뎁스와 부모를 저장
	public static void setDepth(int p, int d) {
		depth[p] = d; // 뎁스 저장
		for(int c : tree[p]) {
			if(depth[c] != 0) continue; // 방문한 곳이면 패스
			parent[c] = p; // 부모 저장 
			setDepth(c, d+1);
		}
	}
	
	public static int LCA(int n1, int n2) {
		// 두 노드의 공통조상이 나타날 때 까지 반복
		while(n1 != n2) {
			// 깊이가 다르면 깊이 통일
			if(depth[n1] > depth[n2]) n1 = parent[n1]; 
			else if(depth[n1] < depth[n2]) n2 = parent[n2];
			// 깊이가 같으면 공통 조상 찾기
			else {
				n1 = parent[n1];
				n2 = parent[n2];
			}
		}
		return n1;
	}
}
