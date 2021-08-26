import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [0825] SWEA 5603 건초더미
 * 수학...? | 10min
 * 
 * sol)
 * 건초의 개수를 모두 동일하게 만들기 위해서는 모든 건초의 개수를 평균으로 만들어줘야함
 * 건초는 하나씩 옮길 수 있으므로 건초가 더 많은 것에서 적은 것으로 옮겨준다고 생각하면,
 * 건초가 많은 것들은 평균보다 얼마나 많은지 계산하여 더할 경우 옮겨야 하는 횟수를 구할 수 있음
 * 
 * time_complex)
 * O(N)
 * 
 */

public class SWEA_5603_건초더미 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] hay = new int[N];
			int answer = 0, avg = 0;
			
			// 건초더미 입력받아 배열에 저장
			for (int i=0; i<N; i++) {
				hay[i] = Integer.parseInt(br.readLine());
				avg += hay[i];      // 평균값 계산을 위해 합을 구하는 과정
			}
			
			// N개 건초더미의 평균을 계산
			avg /= N;
			
			// 건초더미를 하나씩 확인하며 평균보다 많은 것은 얼마나 많은지 개수를 셈
			for (int i=0; i<N; i++) if (hay[i] > avg) answer += (hay[i] - avg);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(answer);
			System.out.println(sb);
		}
	}

}
