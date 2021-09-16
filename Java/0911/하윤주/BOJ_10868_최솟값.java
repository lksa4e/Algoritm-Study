import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0913] 백준 10868 최솟값
 * 트리, 세그먼트 트리
 * 
 * sol)
 * 각 배열의 원소들의 구간 최솟값을 저장한 세그먼트 트리를 생성한 뒤,
 * 트리의 노드가 포함하는 구간과 탐색을 원하는 구간을 비교하여 최솟값 찾기
 * 
 * 트리의 각 노드에는 구간 최솟값이 저장되어있으므로 매우 빠르게 탐색 가능
 * 
 * tc)
 * O(logN) * M = O(log100,000) * 100,000
 *	
 */

public class BOJ_10868_최솟값 {
	static int N, M;
	static int[] input, tree;
	
	// 세그먼트 트리 생성
	static int initNode(int start, int end, int node) {
		// 리프 노드에 배열의 원소 저장
		if(start == end) return tree[node] = input[start];
		
		// 각 부모 노드에는 구간 최솟값을 저장
		int mid = (start+end)/2;
		return tree[node] = Math.min(initNode(start, mid, 2*node),
									 initNode(mid+1, end, 2*node+1));
	}
	
	// 세그먼트 트리를 이용해 구간 최솟값 구하기
	static int segmentMin(int tStart, int tEnd, int qStart, int qEnd, int node) {
		// 현재 트리 구간이 탐색 구간범위 밖이면 pass
		if (qEnd < tStart || qStart > tEnd) return Integer.MAX_VALUE;
		
		// 현재 트리 구간이 쿼리 구간 범위에 포함되면 구간 최솟값 반환
		if (qStart <= tStart && qEnd >= tEnd) return tree[node];
		
		// 가능한 트리의 구간 중 가장 작은 값인 최솟값을 찾기 위해 재귀적으로 구간 탐색
		int mid = (tStart+tEnd)/2;
		return Math.min(segmentMin(tStart, mid, qStart, qEnd, 2*node),
						segmentMin(mid+1, tEnd, qStart, qEnd, 2*node+1));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		input = new int[N+1];      // 입력받은 원소
		tree = new int[N*4];       // 입력받은 원소의 구간 최솟값을 저장하는 세그먼트 트리
		
		// 배열 초기화
		for (int i=1; i<=N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		// 트리 초기화
		initNode(1, N, 1);
		
		M = Integer.parseInt(st.nextToken());
		// 쿼리 구간 최솟값 도출
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int qStart = Integer.parseInt(st.nextToken());
			int qEnd = Integer.parseInt(st.nextToken());
			sb.append(segmentMin(1, N, qStart, qEnd, 1)).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.print(sb);

	}

}
