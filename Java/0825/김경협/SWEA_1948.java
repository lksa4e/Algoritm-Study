import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  SWEA 1948번 날짜 계산기
 *  
 *  월일로 된 날짜 2개가 주어지고 두 날짜 사이에 몇 일이 있는지 구하는 문제.
 *  월 별로 배열에 저장해 놓은 일 수를 통해 카운팅하고
 *  일 별로 단순 뺄셈을 통해 카운트했다.
 *  
 */

public class SWEA_1948 {
	static int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int month1 = Integer.parseInt(st.nextToken());
			int day1 = Integer.parseInt(st.nextToken());
			int month2 = Integer.parseInt(st.nextToken());
			int day2 = Integer.parseInt(st.nextToken());
			
			int cnt = 0;
			for(int i = month1; i < month2; i++) {	// 월별로 카운트
				cnt += days[i];
			}
			
			cnt += day2 - day1 + 1;
			
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
		
	}

}
