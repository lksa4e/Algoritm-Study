import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0907] 백준 2042 구간 합 구하기
 * 트리, 세그먼트 트리
 * 
 * sol)
 * 원소 개수가 1,000,000이고 M과 K가 10,000이므로 선형 탐색 시 10,000,000,000 이라는 말도 안되는 시간이 걸림
 * 따라서 log n만에 구간합을 구하고 값을 변경할 수 있는 세그먼트 트리 알고리즘을 적용해야함
 * 
 * 세그먼트 트리를 이용해 구간합 구하기
 * 1. 트리 생성
 * - 이진 트리 탐색을 시도하며 리프 노드를 찾음. 리프 노드 자리에는 배열 원소를 저장
 * - 각 노드는 자신의 왼쪽 자식 노드와 오른쪽 자식 노드에 저장된 구간 합을 더해 더 큰 범위의 구간합을 저장함
 * 
 * 2. 구간합 구하기
 * - 쿼리 부분이 세그먼트 범위를 벗어나면 0을 반환
 * - 쿼리 부분이 세그먼트 범위에 일부라도 포함되면 노드에 저장된 값 반환
 * - 쿼리 부분이 세그먼트 범위를 완전히 덮어쓰면 자식 노드들을 탐색해서 구간합 재귀적 연산
 * 
 * 3. 값 갱신
 * - 값을 변경하려는 원소 인덱스가 세그먼트 범위를 벗어나면 반환
 * - 배열의 원소뿐 아니라 원소가 영향을 미치는 부모 노드에 저장된 값까지 모두 변경
 * - 만약 배열 원소가 저장된 리프 노드에 도달하여 배열 원소 값을 변경했다면 반환
 * - 리프 노드를 찾아갈 때까지 재귀적으로 반복(자식 노드 탐색)
 * 
 * 
 * 시행착오)
 * 진짜 너무 지독한데요.. 제가 정답률 하향에 큰 기여를 했습니다...
 * 1. 구간합 구하는 함수에서 범위 벗어나는 부분은 0을 반환해야하는데 최댓값을 반환함 -> 구간 최소일 때 반환할 값을 반환해버려서 오버플로우 발생
 * 1. 구간합 구하는 함수에서 노드 값의 합을 출력하지 않고 노드 값에 합을 덮어씀 -> 구간합을 구하는 것뿐인데 노드 값이 변경되어버림
 * 2. update부분에서 변경하려는 원소와 관련된 모든 부노 노드 값을 변경해야하는 것을 간과하고 (start==end) 일때만 변경함 -> 정말 배열의 값만 변경될 뿐 트리 노드에 저장된 구간 합은 갱신되지 않음
 * 3. update할 때 변경되어진 원소 값을 기준으로 하지 않고 초기 배열 원소 값을 기준으로 변경할 값과의 갭을 구함 -> 엉뚱한 값으로 update 되어버림
 * 4. long... 변수 선언은 long으로 해놓고 parseInt 해버림 -> 하...
 * 
 * 저는 세그먼트 트리 구현 자체가 어려운 것 같아요ㅜㅜ
 * 
 * tc)
 * 1) 값 업데이트, 구간합 구하기
 * 		log(1,000,000) * 10,000 = 약 200,000
 *	
 */

public class BOJ_2042_구간합구하기 {
	static int N, M, K;
	static long[] sTree, arr;
		
	// 트리 초기화
	public static long initNode(int start, int end, int node) {
		// 리프 노드를 찾았다면 배열 값 저장
		if(start == end) return sTree[node] = arr[start];	
		
		// 자식 노드들에 대해 재귀적으로 초기화를 진행하며 구한 구간합을 현재 노드에 저장
		int mid = (start + end)/2;
		return sTree[node] = initNode(start, mid, node*2)
				    	   + initNode(mid+1, end, node*2+1);
	}
	
	// 구간 합 구하기
	public static long sum(int tStart, int tEnd, int qStart, long qEnd, int node) {
		// 쿼리 범위 벗어나면 무효
		if (qEnd<tStart || qStart>tEnd) return 0;
		
		// 쿼리 범위가 조금이라도 포함되면 노드 값 반환
		if (qStart<=tStart && qEnd>=tEnd) return sTree[node];
		
		// 자식 노드들에 대해 재귀적으로 구간합을 구하여 합함
		int mid = (tStart+tEnd)/2;
		return sum(tStart, mid, qStart, qEnd, 2*node) 
			 + sum(mid+1, tEnd, qStart, qEnd, 2*node+1);
	}
	
	// 트리 갱신
	public static void update(int start, int end, int node, int idx, long value) {
		// 쿼리 범위 벗어나면 무효
		if (idx<start || idx>end) return;
		
		// 리프 노드와 리프 노드가 영향을 미치는 모든 부모 노드 값 갱신
		sTree[node] += value;
		if (start==end) return;       // 만약 리프노드 도달했다면 반환
		
		// 자식 노드들에 대해 재귀적으로 값 갱신 시도
		int mid = (start+end)/2;
		update(start, mid, 2*node, idx, value);
		update(mid+1, end, 2*node+1, idx, value);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N];
		for (int i=0; i<N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		sTree = new long[N*4];
		initNode(0, N-1, 1);
		
		for (int i=0, size=M+K; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if (a == 1) {
				update(0, N-1, 1, b-1, (c-arr[b-1]));
				arr[b-1] = c;     // 원래 배열을 기준으로 삼기위해 원래 배열도 값 갱신
			}
			else System.out.println(sum(0, N-1, b-1, c-1, 1));
			
		}
	}

}
