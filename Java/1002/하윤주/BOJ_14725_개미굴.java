import java.io.*;
import java.util.*;

/**
 * [1002] BOJ 14725 개미굴
 * 그래프, 트라이, 재귀, 문자열
 * 
 * sol)
 *  트라이 자료구조를 구현한 뒤, 재귀적으로 높이가 같은 문자열을 출력
 *  트라이 자료구조는 key-value형태로 저장해야 하므로 노드 객체를 만든 뒤 자식 노드들을 저장할 노드 객체 배열을 생성한다.
 *  또한 마지막 자식 노드에서는 자신이 마지막이고 모든 문자열 탐색이 끝났다는 의미로 isLeaf 체크를 해야한다.
 *  
 * 시행착오)
 * 	원래 트라이의 경우 이 문제처럼 문자열들을 저장하는 형태보다 자식 노드 배열에 문자열의 각 문자(ex, 알파벳)를 저장하여 접두사가 동일한 문자열을 찾는데 많이 활용하는 것 같다.
 * 	문자 배열을 저장할 경우 배열 인덱싱이 쉬운 반면(아스키 코드로 변환하면 인덱싱이 가능하므로), 문자열 배열을 저장할 경우 문자열을 인덱스로 사용하기가 어려우므로 배열 대신 맵으로 구현할 수 있을 것 같다.
 * 	이번에는 리스트로 구현해봤다.
 *
 */

public class BOJ_14725_개미굴 {
	static int N;
	static Node root;		// 트라이의 루트 노드
	static StringBuilder sb = new StringBuilder();
	
	// 트라이 구현을 위한 노드 객체
	static class Node implements Comparable<Node> {
		String str;												// 문자열을 저장한 노드를 연결하는 형태
		ArrayList<Node> children = new ArrayList<Node>();		// 자식 노드들을 연결할 포인터 리스트
		boolean isLeaf = false;									// 리프 노드 확인용 변수
		
		// 루트 노드 생성을 위한 생성자(루트 노드는 저장할 문자열이 없으므로 디폴트 생성자 필요)
		public Node() {
		}

		// 문자열을 저장한 노드 생성
		public Node(String str) {
			this.str = str;
		}

		// 정렬을 위해 Comparabe의 compareTo 구현
		@Override
		public int compareTo(Node o) {
			return this.str.compareTo(o.str);
		}
		
	}
	
	// 트라이에 각 노드를 삽입하는 메서드
	public static void insert(String[] key) {
		// 현재 노드에 자식 노드들을 추가하는 형태로 삽입
		Node curString = root;								// 단, 최초에는 루트 노드를 현재 노드로 초기화하여 노드 연결
		
		for (int level=0; level<key.length; level++) {		// 입력으로 들어온 문자열 배열(루트에서 리프까지 문자열 저장)
			String child = key[level];
			
			boolean flag = false;
			for (Node node : curString.children) {			// 현재 노드의 자식 중 입력으로 들어온 문자열이 있으면
				if (node.str.equals(child)) {
					curString = node;						// 해당 자식 노드를 다음 탐색을 위해 포인터 타고 들어감
					flag = true;							// 자식 노드가 존재함을 기억
				}
			}
			
			if (!flag) {									// 일치하는 문자열을 가진 자식노드가 없으면
				curString.isLeaf = false;					// 새롭게 자식 노드를 만들어서 연결시켜줘야하므로 현재 노드가 리프가 될 수 없다고 표시하고
				
				Node nextString = new Node(child);			// 자식 노드 새롭게 생성하여
				curString.children.add(nextString);			// 현재 노드가 가진 자식 노드로 기록하고
				curString = nextString;						// 현재 노드에 연결시켜줌
			}
		}
		
		curString.isLeaf = true;							// for문 끝나면 마지막 문자열까지 모두 저장했으므로 리프노드라고 체크
		
		Collections.sort(curString.children);				// 정렬
	}
	
	// 트라이 재귀적으로 출력(dfs)
	private static void print(Node cur, int depth) {
		Collections.sort(cur.children);							// 정렬
		
		// 기저 조건 : 리프노드로 마지막 문자열이면 리턴
		if (cur.isLeaf) return;
		
		// 유도 파트 : 현재 노드의 모든 자식 노드를 재귀적으로 출력
		for (Node next : cur.children) {
			for (int i=0; i<depth; i++) sb.append("--");
			sb.append(next.str).append("\n");
			print(next, depth+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 트라이 루트 노드 생성(루트는 문자열 없이 생성)
		root = new Node();
		
		// 문자열 입력
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			
			String[] tmp = new String[K];
			for (int j=0; j<K; j++) {
				tmp[j] = st.nextToken().toString();
			}
			
			insert(tmp);		// 트라이 구조 생성
		}
		
		// 출력
		print(root, 0);
		System.out.print(sb);
	}

}
