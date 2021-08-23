import java.io.*;
import java.util.*;

/**
 * SW Expert 7964번 부먹왕국의 차원 관문
 *
 * 풀이 : 구현
 * 1. 0 ~ N까지 (제일 끝 도시는 체크 안함) 현재 위치에서 사정거리 안에 있는 가장 가까운 차원 관문 인덱스로 이동 
 * 2. 만약 못 찾으면, 최소로 건설해야하므로 가장 먼 사정거리에 있는 도시에 건설한 후 이동 
 * 
 * 78,572 kb	231 ms
 */
public class Solution7964_김다빈 {
	
	static int N, D;	// 도시 개수, 제한 거리 
	static int[] city;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			city = new int[N+2];
			city[0] = 1;	// 양 끝 도시는 차원관문이 있다는 것 표시 
			city[N+1] = 1;
			
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) {
				city[i] = Integer.parseInt(st.nextToken());
			}
			
			int result = 0;	// 추가 건설해야할 최소 차원관문의 개수 
			
			for(int i=0;i<N+1;i++) {
				// 현재 위치에서 가장 가까운 사정거리에 있는 도시 인덱스 구하기
				int index = calcDim(i);
				
				if(index != 0) {	// 가장 가까운 차원관문으로 이동 
					i = index-1; 
				} else {	// 없으면 가장 먼 사정거리에 차원관문 세우기 
					i += (D-1);
					result++;
				}
			}
			sb.append("#"+test_case+" "+result+"\n");
		}
		
		System.out.println(sb);
	}

	// 사정거리 내에 차원관문이 있으면 해당 인덱스 반환, 없으면 0 
	private static int calcDim(int cur) {
		for(int i=cur+1;i<=cur+D;i++) {
			if(city[i]==1) return i;
		}
		return 0;
	}

}
