import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0825] SWEA 4789 성공적인 공연 기획
 * 구현 | 20min
 * 
 * sol)
 * 박수 사람수 제한을 0명부터 하나씩 늘려가며, 현재까지 박수치고 있는 사람으로는 몇 명이 모자란지 세어 고용될 사람을 증가시킴
 * 
 * 시행착오)
 * 새로 고용될 사람이 아닌 이미 고용된 사람을 새롭게 박수치게 된 사람에게 중복적으로 누적하면서 오답파티
 * 또 char 형으로 관리하면서 '0'을 안빼줘서 엉뚱한 숫자가 들어가고 있었음
 * 
 * time_complex)
 * O(N)
 * 
 */

public class SWEA_4789_성공적인공연기획 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			// 입력 문자열을 캐릭터형 배열에 저장
			char[] people = br.readLine().toCharArray();
			
			int clap = 0;         // 현재까지 박수치고 있는 사람
			int employee = 0;     // 현재까지 고용된 사람
			
			// 문자열을 하나씩 확인하며 박수치고 있는 사람과 고용될 사람 세기
			for (int i=0; i<people.length; i++) {
				// 박수 사람수 제한이 없는 경우(0인 경우) 에는 박수칠 사람도, 고용될 사람도 추가되지 않으므로 넘어감
				if (people[i] - '0' == 0) continue;
				
				// 만약 현재 박수 사람수 제한이 있는 사람이 있고, 현재까지 박수치고 있는 사람이 사람수 제한에 못미치면 그만큼 추가로 고용
				int curEmployee = i-clap > 0 ? i-clap : 0;      // 현재 단계에서 추가로 고용될 사람
				employee += curEmployee;                        // 지금까지 고용된 사람에 새로 고용된 사람 추가
				
				// 새로 고용된 사람과 현재 박수칠 수 있어진 사람을 더해 현재까지 박수치고 있는 사람을 증가시킴
				clap += (curEmployee + people[i] - '0');
			}
			
			// 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(employee);
			System.out.println(sb);
		}
	}

}
