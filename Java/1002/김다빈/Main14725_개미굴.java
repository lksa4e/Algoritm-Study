import java.io.*;
import java.util.*;

/**
 * 백준 14725번 개미굴
 * 
 * 풀이 : 트라이 + DFS 
 * 
 * 사전순으로 출력하기 위해 TrieNode를 TreeMap으로 구현 
 * 
 * 먹이 정보들을 삽입한 후, DFS로 먹이 정보를 재귀적으로 출력 (printChild 함수)
 * => 현재 노드의 자식 노드들을 타고가면서 출력해주는 방식
 * => 깊이를 나타내는 -- 을 몇 번 출력할지 나타내주는 depth를 매개변수로 전달 
 * 
 * 15744KB	176ms
 */
public class Main14725_개미굴 {

	static class Trie {
		TrieNode root = new TrieNode();	// root 노드 

		void insert(String[] words) {
			TrieNode curNode = root;
			
			for(String key : words) {
				// 해당 key 값이 이미 자식으로 존재한다면, 해당 노드 return
				// 존재하지 않는다면, 새로운 TrieNode 생성 후 return
				curNode = curNode.childNode.computeIfAbsent(key, node -> new TrieNode());
			}
		}
	}
	
	static class TrieNode {
		// key = String, value = 자식 노드들
		// 키 값을 기준으로 정렬하여 출력해야하기 때문에 TreeMap 사용 
		Map<String, TrieNode> childNode = new TreeMap<String, TrieNode>();
	}
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		
		Trie trie = new Trie();	// root 노드 생성 
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			String[] input = new String[K];	// 삽입할 먹이 정보 
			
			for (int j = 0; j < K; j++) {
				input[j] = st.nextToken();
			}
			
			trie.insert(input);	// 먹이 정보 삽입 
		}
		
		TrieNode rootNode = trie.root;
		printChild(rootNode, 0);	// rootNode의 자식 노드들을 차례대로 출력 
		System.out.println(sb);
	}

	// curNode : 현재 출력할 자식 노드들의 부모 노드  
	// depth : 출력할 노드들의 깊이 
	// curNode의 자식 노드들을 출력하는 함수 
	private static void printChild(TrieNode curNode, int depth) {
		for (String child : curNode.childNode.keySet()) {	// 현재 노드의 자식 노드들 차례대로 탐색 
			// 노드의 깊이만큼 -- 출력 
			for (int i = 0; i < depth; i++) {
				sb.append("--");
			}
			
			// 자식 노드 출력
			sb.append(child + "\n");
			// 자식 노드의 자식 노드들 출력을 위해 재귀적으로 호출 
			printChild(curNode.childNode.get(child), depth + 1);
		}
	}

}
