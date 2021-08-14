import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0814] 백준 2805 나무 자르기
 * 이진 탐색(Binary Search)
 * 
 * sol)
 * 나무의 높이와 나무의 수 크기가 상당하므로 이진 탐색 사용해야함
 * 이진 탐색을 위해 높이는 무조건 오름차순으로 정렬되어있어야함(최소와 최대를 알아야 함)
 * 또한 목표 나무 높이를 구하되 그 값의 최대치를 구해야하므로 end 반환
 * 나무 개별 높이는 int 범위를 넘지 않으나, 합산 도중 넘어가는 케이스가 존재하므로 long 형으로 설정해야함
 * 
 * time_complex)
 * O(logN)
 */

public class BOJ2805 {
	static int N, M, maxHeight;
	static int[] heights;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		heights = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
			maxHeight = Math.max(maxHeight, heights[i]);
		}
		
		// 나무를 자르지 않는 경우(최소)부터 최대로 자르는 경우까지 검사
		System.out.println(binarySearch(0, maxHeight));
	}

	// 이진탐색
	private static int binarySearch(int start, int end) {
		long answer = 0;   
		int mid = 0;
		
		while(start <= end) {          // 같거나 작게로 설정했으므로 end = mid - 1
			mid = (start + end) / 2;
			
			long leftLength = 0;       // mid값 기준으로 나무 베기
			for (int i=0; i<N; i++) leftLength += heights[i] > mid ? heights[i] - mid : 0;
			
			if (leftLength < M) end = mid-1;     // 목표치를 달성하지 못했다면 기준값을 mid 아래 최댓값으로
			else start = mid+1;        // 목표치를 달성했다면 기준값을 mid 위로 최소만큼 증가
		}
		return end;
	}
}
