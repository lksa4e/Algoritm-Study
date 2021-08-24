import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 *  SWEA 2005번 파스칼의 삼각형
 *  
 *  주어진 규칙에 맞춰 출력하는 구현 문제.
 *  각 층의 인덱스 생각하는게 어려웠다.
 *  
 */

public class SWEA_2005 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] tri = new int[55];
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			int size = Integer.parseInt(br.readLine());
			
			
			sb.append("#").append(tc).append("\n");
			// cnt 는 각 층을 구별한다.
			for(int cnt = 1; cnt <= size; cnt++) {
				
				for (int i = 0; i < cnt; i++) {	// 인덱스는 (이전 층까지의 모든 요소들의 개수 + 현재 층에서의 순서) 이다.
					// 각 층의 첫번째와 마지막은 1이 됨
					if(i == 0 || i == cnt - 1)
						tri[getIndex(cnt) + i] = 1;
					else	// 이전 층의 i-1번째와 i번째의 합
						tri[getIndex(cnt) + i] = tri[getIndex(cnt-1) + i - 1]
								+ tri[getIndex(cnt-1) + i];
					
					sb.append(tri[getIndex(cnt) + i]).append(" ");
				}
				sb.append("\n");
				
			}
			
			
			
		}
		System.out.println(sb);
		
	}
	
	static int getIndex(int i) {
		return i * (i - 1) / 2;
	}

}
