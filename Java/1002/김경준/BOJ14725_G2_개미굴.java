import java.io.*;
import java.util.*;

/**
 * G2 BOJ 14725 개미굴
 * 메모리 : 16248 시간 : 200ms
 * 
 * Trie 문제는 Map으로도 풀 수 있는데 둘이 시간차이가 많이 안난다고 하길래 그냥 Map으로 풀어봄
 * 나중에 제대로 공부하겠습니다.
 * 
 * APPLE
 *	--APPLE
 *	--BANANA
 *	----KIWI
 *
 * 문제에서 위와 같이 부모 -> 자식 -> 자식 형태로 출력을 해야한다. (dfs)
 * 부모 -> 자식 관계가 String String 이지만 여러개의 자식을 가질 수 있음 (Class 형태로 내부에 list or map 가져야함)
 * list를 쓰면 비교연산을 수행해야하므로 그냥 map을 씀
 * 
 * main_map.put(APPLE, new 자식노드)
 * main_map's 자식노드 map.put(APPLE, new 자식노드)
 * main_map's 자식노드 map.put(BANANA, new 자식노드)
 * main_map's 자식노드 map's 자식노드 map.put(KIWI, new 자식노드)
 * 형태로 구현함
 * 
 * 찾아보니 Hashmap 대신 Treemap을 쓰면 알아서 Key 기준으로 정렬해줌
 */
public class BOJ14725_G2_개미굴 {
	static int N,K,L;
	static StringBuilder sb = new StringBuilder();
	static class Node{
		String data;
		Map<String, Node> m = new TreeMap<String,Node>();
		public Node(String data) {
			this.data = data;
		}
	}
	static Map<String, Node> main_map = new TreeMap<String,Node>();
	static List<Node> nodeList = new ArrayList<Node>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			Map<String, Node> cur_map = main_map;
			for(int k = 0; k < K; k++) {
				String str = st.nextToken();
				// 이미 존재하는 head라면
				if(cur_map.get(str) != null) {
					Node node = cur_map.get(str);
					// 자식 노드로 들어감
					cur_map = node.m;
				}
				// 만약 처음 등록되는 head라면
				else {
					// 새로운 자식 노드를 만들고 넣음
					Node node = new Node(str);
					cur_map.put(str, node);
					cur_map = node.m;
				}
			}
		}
		// 출력을 위해 dfs 함수를 수행
		for(String sKey : main_map.keySet()) {
			dfs(main_map.get(sKey), 0);
		}
		System.out.print(sb);
	}
	static void dfs(Node node, int cnt) {
		// 깊이만큼 --를 출력
		for(int i = 1; i <= 2 * cnt; i++) sb.append("-");
		// 현재 data(String)을 출력
		sb.append(node.data + "\n");
		
		// map의 원소들 -> 자식 노드
		for(String sKey : node.m.keySet()) {
			dfs(node.m.get(sKey), cnt + 1);
		}
	}
}
