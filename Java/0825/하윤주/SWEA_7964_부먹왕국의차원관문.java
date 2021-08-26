import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0825] SWEA 7964 부먹왕국의 차원 관문
 * 문장 독해 능력 판단 | 10min
 * 
 * sol)
 * 모든 도시를 방문하고자 하지만 이동 제한 거리 이내의 도시들은 모두 방문 가능하므로 결국 이동 제한 거리 간격만큼 관문을 만들면 됨
 * 관문이 존재하고부터 이후로 몇 개의 도시들이 관문이 없는지 누적합을 구하고,
 * 다시 관문을 만나는 지점에서 지금까지 관문이 몇개 필요한지 계산
 * 지금까지 몇개의 관문이 필요한지는 `누적합 / 이동 거리 제한`으로 계산 가능함
 * 
 * time_complex)
 * O(N)
 * 
 */

public class SWEA_7964_부먹왕국의차원관문 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			int answer = 0, accumZero = 0;    // 정답 변수, 누적된 0의 개수
			
			// 도시를 처음부터 순회하며 차원 관문을 몇개 설치할지 계산
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				if (st.nextToken().equals("0")) accumZero++;      // 차원 관문이 없는 도시가 시작되면 몇 개의 도시가 연속적으로 관문이 없는지 누적합을 계산
				else {                           // 차원 관문이 존재하는 도시를 만나면
					answer += accumZero / D;     // 지금까지 누적된 차원 관문이 없는 도시들의 개수를 구한 뒤, 이동 제한 거리 기준하에서 관문이 몇개 부족한지 계산하고 설치
					accumZero = 0;               // 현재 도시까지는 차원관문을 모두 설치했으므로 다시 누적합을 초기화
				}
			}
			
			if (accumZero > 0) answer += accumZero / D;     // 순회 끝난 뒤 아직까지 차원 관문이 존재하는 도시를 못 만나 관문을 설치하지 못한 도시가 있다면 설치
			
			// 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(answer);
			System.out.println(sb);
		}

	}

}
