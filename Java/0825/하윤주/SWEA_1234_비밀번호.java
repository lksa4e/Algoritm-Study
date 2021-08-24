import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [0825] SWEA 1234 비밀번호
 * 문자열, 스택 | 10min
 * 
 * sol)
 * 문자열의 문자를 저장할 스택을 생성
 * 스택에 문자열 첫번째 문자를 넣어두고 두번째 문자부터 스택 상단과 비교하여 동일할 경우 pop(), 다르면 push()
 * 최종 스택에 담긴 것이 비밀번호. 단 정답 출력 시 스택 하단부터 순서대로 출력해야함
 * 
 * time_complex)
 * O(N)
 * 
 */

public class SWEA_1234_비밀번호 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1234input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc=1; tc<=10; tc++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String nums = st.nextToken();
			
			// 문자열의 문자를 저장하고 비교할 스택 생성
			Stack<Character> pw = new Stack<>();
			pw.push(nums.charAt(0));      // 문자열의 첫번째 문자는 삽입한 상태로 초기화
			
			// 문자열의 두번째 문자부터 시작하여 스택 상단과 비교
			for (int i=1; i<N; i++) {
				if (pw.isEmpty() || nums.charAt(i) != pw.peek()) pw.push(nums.charAt(i));       // 스택이 비어있거나 상단과 현재 문자가 동일하지 않으면 push()
				else pw.pop();          // 동일하다면 Pop()
			}
			
			// 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			for (Character s : pw) sb.append(s);
			
			System.out.println(sb);
			
		}
	}

}
