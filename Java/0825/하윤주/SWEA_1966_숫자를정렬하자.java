import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0825] SWEA 1966 숫자를 정렬하자
 * 정렬 | 10min
 * 
 * sol)
 * 입력 숫자를 int형 배열에 저장한 뒤, 내장함수를 이용해 정렬
 * 
 * time_complex)
 * 내장 정렬(듀얼 피봇 퀵소트) : O(n log(n))
 * 
 */

public class SWEA_1966_숫자를정렬하자 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1966input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] nums = new int[N];
			
			// 입력을 배열에 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) nums[j] = Integer.parseInt(st.nextToken());
			
			// 정렬
			Arrays.sort(nums);
			
			// 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			for (int j=0; j<N; j++) sb.append(nums[j]).append(" ");
			sb.setLength(sb.length()-1);
			System.out.println(sb);
			
		}
	}

}
