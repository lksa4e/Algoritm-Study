import java.io.*;
import java.util.*;
/**
 * SWEA 7964 부먹왕국의 차원 관문 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWuSgKpqmooDFASy
 * 
 * 모든 도시에서 차원 관문을 통해 이동이 가능하도록 하려면 
 * 특정 차원 관문에서 다음 차원 관문 사이의 거리가 limit 보다 크면 안된다.
 * 
 * 특정 차원 관문과 다음 차원 관문의 위치 차이가 dist이고, 제한 거리가 n 인 경우 
 * 두 차원 관문 사이에는 최소 dist / n 개의 차원 관문이 존재해야만 막힘없이 이동할 수 있다.
 * 
 * 초기에 주어진 차원 관문의 위치를 List에 저장해두고
 * 차원관문을 하나씩 꺼내면서 차원관문의 거리차가 limit보다 큰 경우 차원관문 사이에 추가로 차원관문을 배치하고, 현재 위치를 변경한다.
 * 모든 차원관문을 탐색하며 같은 행동을 반복한다.
 * 
 */

public class SWEA_7964_부먹왕국의차원관문_김경준 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, N, limit, answer = 0;
	static List<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) list.add(i);
			}
			list.add(N+1);
			
			int cur = 0;  //초기 차원관문 위치는 0
			int answer = 0;  // 추가로 배치한 차원관문 개수
			for(int num : list) {  // 저장된 차원관문 하나씩 비교
				if( num - cur - 1 > limit) // 만약 두 차원관문 사이의 거리가 limit(이동거리제한) 보다 크다면 
					answer += (num - cur - 1) / limit;  // 두 차원관문 사이에 차원관문 배치
				cur = num;   // 마지막 차원관문 위치 저장
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}	
}
