package BaekOJ.study.date0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 파라메트릭 서치로 공유기 설치 위치를 정한다.
 * 파라메트릭 서치는 바이너리탐색과 구분이 거의 동일하지만 구체적인 목표 값을 찾는게 아니라
 * 주어진 범위를 수렴시켜 조건의 최적의 값을 찾아내는 알고리즘임.
 * 
 * 첫번째 집에서 부터 최소 d의 거리만큼 떨어진 집에 공유기를 설치 했을 때, 몇개를 설치할 수 있는지를 구한다.
 * k만큼 설치할 수있다고 할 때, C와 값을 비교하여 k가 C보다 크거나 작다면 d의 거리를 늘리고  k가 C작다면 d의 거리를 줄이는 방식으로
 * 가장 인접한 두 공유기 사이의 최대 거리를 수렴시켜 구한다.
 * 
 * 메모리	시간
 * 23292	280
 * 
 */

public class BaekOJ2110_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, C, router[];

	public static void main(String[] args) throws NumberFormatException, IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		router = new int[N];
		for(int i = 0; i < N; i++) router[i] = Integer.parseInt(br.readLine());
		
		// 일단 집 좌표부터 정렬
		Arrays.sort(router);
		
		// 집의 거리 범위를 넘겨 줌. 그래서 start = 1, end = 첫번째 집과 마지막 집의 거리인 router[N-1]-router[0] 을 넘겨줌
		System.out.println(p_search(1, router[N-1]-router[0]));
	}
	
	public static int p_search(int start, int end) {
		if(start > end) return end; // 최대 거리로 수렴하면 종료
	
		int pivot = (start+end)/2;
		
		// pivot으로 문제가 해결 가능한지. yes -> 거리를 더 늘려봄. no -> 거리를 더 줄여봄 
		if(isPossible(pivot)) return p_search(pivot+1, end);
		else return p_search(start, pivot-1); 
	}
	
	public static boolean isPossible(int distance) {
		
		int cnt = 1; // 공유기 설치 개수
		int curr = router[0]; // 공유기 설치 지점 = 시작점으로 초기화
		
		for(int i = 1; i < N; i++) {
			if(curr+distance <= router[i]) { // 현재 공유기 설치 지점좌표  + 거리 <= i번 째 공유기 좌표 
				// 공유기 설치 후 현재 공유기 설치 지점 갱신
				cnt += 1;
				curr = router[i];
			}
		}
		
		// C개 이상 공유기를 설치가 가능한지
		if(cnt >= C) return true;
		else return false;
	}
}
