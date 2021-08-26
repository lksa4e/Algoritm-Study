import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0825] SWEA 6485 삼성시의 버스 노선
 * 문장 독해 능력2 | 20min
 * 
 * sol)
 * 총 5001 길이의 일차원 배열을 생성한 뒤, 입력으로 받은 범위를 배열의 인덱스로 하여 배열에 접근해 값을 증가시킴
 * 
 * 시행착오)
 * 처음에 map을 이용해서 노선을 증가시켜줬는데 노선에 없는 정류장이 입력으로 들어올 것을 생각 못함
 * 정류장 번호는 1~5000 사이의 어떤 값도 입력될 수 있으므로 노선에 없는 정류장은 0을 출력해야하지만 null을 출력함
 * 맵을 잘 생각하고 쓰자!
 * 
 * time_complex)
 * O(N)
 * 
 */

public class SWEA_6485_삼성시의버스노선 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			
			int N = Integer.parseInt(br.readLine());

			// 노선을 기록할 정류장 배열
			int[] busCourse = new int[5001];
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				// 버스 별 노선 범위를 인덱스로 하여 배열에 접근하여 노선 개수를 증가시킴
				for (int j=from; j<=to; j++) busCourse[j]++;
			}
			
			int P = Integer.parseInt(br.readLine());
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			
			// 입력되는 정류장에 노선이 존재하면 개수를 출력
			for (int i=0; i<P; i++) {
				int busStop = Integer.parseInt(br.readLine());
				sb.append(busCourse[busStop]).append(" ");
			}
			
			sb.setLength(sb.length()-1);
			System.out.println(sb);
			
		}
	}

}
