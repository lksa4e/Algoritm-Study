package P0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 2805번 나무 자르기 
 * 풀이	: "이분 탐색"을 이용하여 최소 길이 구하기 
 * 
 */

public class Solution2805_김다빈 {

	static int N, M;
	static Integer[] tree;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new Integer[N];
		
		st = new StringTokenizer(br.readLine());
		int max = 0;
		for(int i=0;i<N;i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			if(tree[i] > max) max = tree[i];
		}
		
		// 이분 탐색을 이용하여 길이 구하기 
		System.out.println(binarySearch(max, 0));
	}

	private static int binarySearch(int max, int min) {
		int up = max, down = min;
		
		while(up >= down) {
			int mid = (up + down)/2;
			
			// N : 최대 1,000,000 (100만) 
			// H : 최대 1,000,000,000 (10억) 
			// N*H : 최대 1,000,000,000,000,000 까지도 담을 수 있어야 함
			
			// int에 저장할 수 있는 최대 값 : 2,147,483,647 => 택도 없다!! 
			// long에 저장할 수 있는 최대 값 : 9,223,372,036,854,775,807 
			// 따라서 long에 저장해주어야 한다..
			long treeSum = calculateHeight(mid);
			
			if(treeSum >= M) {
				down = mid + 1;
			} else {
				up = mid - 1;
			}
		}
		
		return up;
	}

	// 설정한 높이에 따라 가져갈 수 있는 나무 길이 계산 
	public static long calculateHeight(int h) {
		long sum = 0;
		
		for(int i=0;i<N;i++) {
			if(tree[i]-h > 0)	sum += tree[i]-h;
		}
		return sum;
	}

}
