package BaekOJ.study.date1002;

import java.io.*;
import java.util.*;

/*
 * 트라이를 처음으로 다뤄봐서 익숙하지 않았지만, 기초적인 문제라서 그런지 나름 할만했음
 * 루트노드부터 시작해서 자식노드를 찾아서 이동하는데, 
 * 만약 자식노드가 있으면 바로 들어가고 
 * 없으면 자식노드를 생성하고 child에 추가시킨 뒤 이동함
 * 
 * 트라이를 완성하면 직접적으로 연결된 자식노드들을 사전순으로 정렬하고 dfs 탐색을 반복함.
 * dfs의 기저조건은 해당 노드가 리프노드인지 체크하는 것이므로, 트라이를 구성할 때 리프노드를 잘 체크해줘야 함 
 * 
 * 시행착오 :
 * 처음에 미리 사이즈 1000의 배열을 미리 선언하고 HashMap<String, Integer>으로 
 * 인덱스를 매핑해주는 방식으로 접근했더니 출력할 때 사전순 정렬이 잘 안돼서 List로 다시 풀었음.
 * 
 * 메모리 	시간
 * 15316	172
 */

class Node implements Comparable<Node>{
	String str;
	List<Node> children = new ArrayList<Node>();
	boolean isLeaf; // 리프노드 체크 필드

	Node(String str) {
		this.str = str;
		isLeaf = false;
	}
    
	// 사전순 정렬
	@Override
	public int compareTo(Node o) {
		return this.str.compareTo(o.str);
	}
}

public class BaekOJ14725_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, K, size;
	static boolean check;
	static String str;
	static Node root, temp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		root = new Node(null);
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			// 항상 루트부터 시작
			temp = root;
			for(int j = 0; j < K; j++) {
				str = st.nextToken();
				size = temp.children.size();
				check = false;
				// 자식 노드에 str이 있으면 바로 진입
				if(size > 0) {
					for(int n = 0; n < size; n++) {
						if(temp.children.get(n).str.equals(str)) {
							temp = temp.children.get(n);
							check = true;
							break;
						}
					}
				}
				// 2가지 경우에 자식노드를 새로 생성해서 해당 노드로 들어감
				// 1. 사이즈가 0일 때
				// 2. 자식노드에 str이 없을 때
				if(!check){ 
					temp.children.add(new Node(str));
					temp = temp.children.get(size);
				}
			}
			temp.isLeaf = true; // 리프 노드 표시
		}
		dfs(root, 0);
		System.out.println(sb);
	}
	
	public static void dfs(Node parent, int depth) {
		if(parent.isLeaf) return; // 기저조건 : 리프노드(개미굴 끝)
		Collections.sort(parent.children); // 사전순 정렬
		
		// 스트링빌더에 출력문자 추가
		for(Node node : parent.children) {
			for(int i = 0; i < depth; i++) sb.append("--");
			sb.append(node.str).append("\n");
			dfs(node, depth+1);
		}
	}
}
