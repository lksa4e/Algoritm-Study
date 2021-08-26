import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0825] SWEA 1948 날짜 계산기
 * 날짜 계산 | 10min
 * 
 * sol)
 * 먼저 월별 일수를 1차원 배열로 저장하여 기억함
 * 이후 날짜를 입력받아 두번째 날짜와 첫번째 날짜 사이에 존재하는 월별 일수를 모두 합산하고,
 * 두번째 날짜와 첫번째 날짜의 일의 차이를 계산하여 월별 일수에 더해줌
 * 
 * time_complex)
 * O(N)
 * 
 */

public class SWEA_1948_날짜계산기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 월별 일수를 저장한 배열
		int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 입력
			int[] inputDays = new int[4];
			for (int i=0; i<4; i++) inputDays[i] = Integer.parseInt(st.nextToken());
			
			// 두 날짜 사이 월의 일수 합산
			int answer = 0;
			for (int m=inputDays[0]; m<inputDays[2]; m++) answer += days[m-1];
			
			// 두 날짜의 일수만을 빼 추가적인 일수 계산
			answer += (inputDays[3] - inputDays[1] + 1);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(answer);
			System.out.println(sb);
		}

	}

}
