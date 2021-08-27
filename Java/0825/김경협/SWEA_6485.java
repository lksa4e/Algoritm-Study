import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  SWEA 6485번 삼성시의 버스 노선
 * 
 *  최대 5000개의 버스 정류장이 있기 때문에 그만큼의 배열을 만들어 주고
 *  start에서 end까지 운행하는 버스 노선이 있으면 이에 해당하는 배열들을 1씩 늘려줬다.
 *  그리고 index로 접근하여 원하는 버스 정류장의 버스 노선 개수를 출력했다.
 */

public class SWEA_6485 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] busStations = new int[5001]; // 버스 정류장 5000개
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				for(int j = start; j <= end; j++)	// 이 버스노선은 start~end 정류장을 방문한다. 따라서 start~end 정류장에 버스 수 1씩 올려줌
					busStations[j]++;
			}
			
			sb.append("#").append(tc).append(" ");
			int P = Integer.parseInt(br.readLine());
			for (int i = 0; i < P; i++) {
				int bs = Integer.parseInt(br.readLine());
				sb.append(busStations[bs]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		
	}

}
