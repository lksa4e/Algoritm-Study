import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  SWEA 7964번 부먹왕국의 차원 관문
 *  
 *  일렬로 늘어진 맵에서 0부터 N+1까지 가는 문제였다.
 *  
 *  이중 for문을 사용해 바깥 for는 현재 도시의 인덱스를, 안쪽 for는 portal의 이동제한거리를 관리했다.
 *  
 */

public class SWEA_7964 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			int[] map = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= N; i++)
				map[i] = Integer.parseInt(st.nextToken());
			
			
			int cnt = 0;
			OUT: for (int currCity = 0; currCity <= N;) {	// N에 도착할 때 까지 이동
				for(int portalDistance = 1; portalDistance <= D; portalDistance++) {	// 포탈의 사정거리 내에 다른 포탈 있는지 탐색
					if(currCity + portalDistance > N) break OUT;	// N 넘게 이동했기 때문에, break
					if(map[currCity + portalDistance] == 1) {	// 포탈의 사정거리 내에 다른 포탈이 있으면 계속 사정거리 연장하면서 이동가능
						currCity += portalDistance;
						portalDistance = 0;
					}
				}
				cnt++;
				currCity += D;
			}
			
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
			
			
		}
		System.out.println(sb);
	}

}
