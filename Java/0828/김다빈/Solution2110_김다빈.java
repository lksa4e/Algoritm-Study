import java.util.*;
import java.io.*;

/**
 * 백준 2110번 공유기 설치 
 * 
 * 풀이 : 이진탐색 (Parametric Search) 
 * => 공유기 개수를 만족하는 최대 인접거리 찾기
 * 
 * 최소 = 1, 최대 = 양쪽 끝 집 사이 거리
 * 중간값 = 현재 인접거리
 * 
 * 1. 집과 집 사이 거리가 중간값보다 크거나 같으면 공유기 설치 
 * 2. 설치한 공유기 개수가 목표치를 만족하면, 결과값 갱신하고 중간값 늘려보기
 *    만족 못하면 중간값 줄이기
 * 
 * 23008KB	316ms
 */
public class Solution2110_김다빈 {
	
	static int N, C, min = 0;
	static int[] home;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		home = new int[N];
		for (int i = 0; i < N; i++) {
			home[i] = Integer.parseInt(br.readLine());
		}
		
		// 이진 탐색을 위해 오름차순으로 정렬 
		Arrays.sort(home);
		
		int minDistance = 1;	// 공유기 사이 최소 거리 
		int maxDistance = home[N-1] - home[0];	// 공유기 사이 최대 거리 
		
		binarySearch(minDistance, maxDistance);
		
		System.out.println(min);
	}

	private static void binarySearch(int left, int right) {
		while(left <= right) {
			int mid = (left+right)/2;	// 공유기 간 인접거리 설정 
			int start = home[0];
			int cnt = 1;	// 제일 첫번째 집에 공유기 설치
			
			for(int i=0;i<N;i++) {
				int diff = home[i]-start;
				
				if(diff >= mid) {	// 공유기 간 인접거리 최소보다 크면 현 위치에 설치하고, 설치한 위치부터 다시 공유기 설치 진행  
					cnt++;
					start = home[i];
				}
			}
			
			if(cnt >= C) {	// 목표 공유기 수를 만족하면 업데이트 & 인접거리 늘려서 다시 시도  
				min = mid;
				left = mid+1;
			} else {	// 인접거리 줄여서 다시 시도 
				right = mid-1;
			}
		}
	}

}
