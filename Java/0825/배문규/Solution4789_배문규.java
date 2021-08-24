package SWEA.study0825;

import java.io.*;
import java.util.*;

/*
 *  처음에는 문제를 읽고 이해를 하는데 시간이 좀 걸림
 *  i번째에서 기립박수를 받으려면 최소 i만큼의 사람을 확보하여야 하고
 *  확보된 사람이 i보다 적으면 최소한 필요한 만큼 사람을 보충한다.
 *  보충할 사람의 수를 need라는 변수로 관리했고,
 *  현재 확보된 사람의 수는 cnt로 관리하였다.
 *  
 *  i번째 수가 0이 아니면서 확보된 사람이 i보다 적으면  
 *  need에 필요한 사람의 수인 i-cnt를 더하고
 *  cnt에 총 확보된 사람의 수, 
 *  1. 조건이 충족되면 박수를 칠 사람
 *  2. 조건이 충족되기 까지 필요한 사람 
 *  3. 이전까지 확보된사람
 *  1,2,3 모두 더해준다.
 *  
 *  만약 i번째에서 확보된 사람이 i보다 많으면 
 *  cnt에 조건이 충족되면 박수를 칠 사람을 더해준다.
 *  
 *  메모리	시간
 *  21,168	130 
 */
public class Solution4789_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T;

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			String people = br.readLine();
			
			int cnt = people.charAt(0)-'0'; // 현재까지 확보된 사람 0번째는 무조건 박수를 쳐주시는 좋으신 분들
			int need = 0; // 조건을 충족시키기 위해 고용해야할 사람
			
			for(int i = 1; i < people.length(); i++) { 
				/*i번째 수가 0이 아니면서 확보된 사람이 i보다 적으면  
				 *  need에 필요한 사람의 수인 i-cnt를 더하고
				 *  cnt에 총 확보된 사람의 수, 
				 *  1. 조건이 충족되면 박수를 칠 사람
				 *  2. 조건이 충족되기 까지 필요한 사람 
				 *  3. 이전까지 확보된사람
				 *  1,2,3 모두 더해준다.
				 */
				if(people.charAt(i)-'0' != 0 && cnt < i) {
					need += i-cnt;
					cnt += people.charAt(i)-'0' + i-cnt;
				}
				// i번째에서 확보된 사람이 i보다 많으면 
				// cnt에 조건이 충족되면 박수를 칠 사람을 더해준다.
				else cnt += people.charAt(i)-'0';
			}
			sb.append("#").append(tc).append(" ").append(need).append("\n");
		}
		System.out.println(sb);
	}
}
