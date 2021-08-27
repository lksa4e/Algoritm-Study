import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *  SWEA 1966번 숫자를 정렬하자
 *  
 *  숫자를 정렬하는 문제
 *  
 */

public class SWEA_1966 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arr);
			
			sb.append("#").append(tc).append(" ");
			for(int a : arr)
				sb.append(a).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
