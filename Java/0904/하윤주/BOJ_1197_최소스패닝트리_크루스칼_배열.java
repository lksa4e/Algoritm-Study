import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0904] 백준 1197 최소 스패닝 트리
 * 트리, MST, 크루스칼
 * 
 * sol)
 * 간선을 중심으로 그리디하게 최소 가중치의 합을 구하는 크루스칼 알고리즘
 * 
 * 가중치가 오름차순으로 정렬된 간선 리스트를 만들기 위해 배열, 우선순위 큐 모두 사용해봤는데요,
 * (Arrays.sort()랑 우선순위 큐 원소 추가 시 정렬이랑 뭐가 더 나은지 궁금해서요..!)
 * 예상을 뒤엎고 우선순위 큐가 시간, 공간 모두 성능이 근소하게 좋았습니다.!
 * 		- 배열      : 50124KB  	792ms
 * 		- 우선순위 큐 : 47116KB	504ms
 * 
 * Arrays.sort()에 기본형 배열을 적용한다면 듀얼 피봇 퀵소트가, 참조형 배열을 적용한다면 팀소트(머지소트 + 삽입정렬)가 적용된다는데
 * 이 문제에선 기본형이 아닌 참조형을 정렬했고, 초기 상태가 거의 정렬이 안된 상태일 수 있어서 우선순위 큐가 나은걸까요? 라고 하기엔 너무 근소하긴 하네요...
 * 
 * 추가적으로 배열이 더 빠를 것 같긴 했지만 ArrayList도 궁금해서 해봤는데요,
 * 		- 리스트     : 51176KB  	632ms
 * 리스트 sort()함수 부르면 내부적으로 리스트 원소를 배열로 복사하고 해당 배열을 정렬한 다음에 다시 리스트 원소로 대치시키는 것이라고 하는데 왜 배열보다 나을까요?
 * 역시 테스트 바이 테스트인걸까요 ㅋㅋㅋ??
 * 
 * tc)
 * 간선 리스트 생성 : O(E)
 * 정점 서로소 집합 생성 : O(V)
 * 간선 정렬 : O(E log E)
 * 정점 결합 : O(1)
 *	
 */

public class BOJ_1197_최소스패닝트리_크루스칼_배열 {
	static int V, E;            // 정점, 간선 개수
	static long weights;        // 최종 가중치 합
	static Edge[] edgeList;     // 간선 리스트
	
	// 간선 리스트를 위한 간선 정보 타입
	static class Edge implements Comparable<Edge>{
		int from, to;       // 진출, 진입 정점 인덱스
		long weight;        // 가중치
		
		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		// 정렬을 위한 비교
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	// union-find
	static int[] parents;           // 집합 대표자 정렬
	
	// 서로소 집합 생성
	static void make() {
		parents = new int[V+1];
		for (int i=0; i<V+1; i++) parents[i] = i;
	}
	
	// 집합의 대표자 찾기
	static int find(int p) {
		if (p == parents[p]) return p;
		return parents[p] = find(parents[p]);
	}
	
	// 서로소 집합 결합
	static boolean union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		
		if (rootP == rootQ) return false;
		
		parents[rootQ] = rootP;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeList = new Edge[E];
		
		// 간선 리스트 생성
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long w = Long.parseLong(st.nextToken());
			
			edgeList[i] = new Edge(from, to, w);
		}
		
		// 가중치가 작은 간선을 우선적으로 방문하기 위해 오름차순 정렬
		Arrays.sort(edgeList);
		
		// 크루스칼 알고리즘
		kruskal();
		
		System.out.println(weights);
	}

	// 크루스칼 알고리즘
	private static void kruskal() {
		// 각 정점 서로소 집합 생성
		make();
		
		int edgeCnt = 0;        // 최소 간선 개수만큼만 가중치 합 구하면 되므로 포함된 간선 개수 카운트
		
		for (Edge edge : edgeList) {              // 가중치 작은 간선부터 방문
			if (union(edge.from, edge.to)) {      // 서로 다른 집합이라면 간선 연결 가능
				weights += edge.weight;           // 새롭게 간선이 연결됐다면 해당 간선 가중치 합 구함
				if (++edgeCnt == V-1) break;      // 모든 정점이 연결됐다면 탈출
			}
		}
	}

}

