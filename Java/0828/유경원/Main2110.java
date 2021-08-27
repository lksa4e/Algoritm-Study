import java.io.*;
import java.util.*;


public class Main2110 {
	/*
	 * 공유기 개수가 적으면 => 거리를 좁힌다
	 * 많으면 거리 늘린다
	 */
	static int N, C;
	static int[] home;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		home = new int[N];
		
		for(int i=0; i<N; i++) 
			home[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(home); // 이진탐색을 위해 정렬
		
		System.out.println(search());
		
	}
	
	private static int search() {
		int left = 1, right = home[N-1] - home[0], mid = 0, ans = 0;
		
		while(left<=right) {
			mid = (left+right)/2;
			
			int wifi = 1, j=0; // 공유기 개수, 설치한 집 좌표
			for(int i=1; i<N; i++) {
				if(mid <= home[i] - home[j]) {
					j = i; // 설치한 집 좌표 갱신
					if(++wifi>C) break; // C개 보다 많아지면 종료
				}
			}
			
			if(wifi >= C) { // 공유기 개수가 많으면
				left = mid + 1; // 거리 늘린다
				if(ans<mid) ans = mid; // 최대거리 찾기
			}else {
				right = mid - 1;
			}
		}
		
		return ans;
	}
	
}