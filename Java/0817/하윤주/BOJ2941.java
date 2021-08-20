import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * [0817] BOJ 2941 크로아티아 알파벳
 *
 * sol) 문자열을 조지는 문제
 * tc) ??
 */

public class BOJ2941 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Deque<Character> stack = new ArrayDeque<Character>();    // stack을 사용하려 했으나 exception을 막기 위해 deque으로 구현했습니다
		char[] str = br.readLine().toCharArray();
		int cnt = 0;
		
		for (char c : str) {
			if (c == '=') {
				String peek = stack.pollLast().toString();     // 이전 문자
				if (stack.isEmpty()) cnt++;            // = 문자
				else {
					if ("cs".contains(peek)) cnt++;    // c=, s= 문자
					else if (peek.equals("z")) {
						cnt++;                         // dz=, z= 문자
						if (stack.peekLast() == 'd') stack.pollLast();
					} else cnt += 2;                   // =와 크로아티아가 아닌 다른 알파벳 문자들
				}
				
				cnt += stack.size();   // 스택에는 크로아티아 문자가 들어있지 않으므로 일반 문자 세고 스택 비우기
				stack.clear();
				
			} else if (c == '-') {
				if (stack.isEmpty()) cnt++;   // - 문자
				else {
					if ("cd".contains(stack.pollLast().toString())) cnt++;    // c-, d- 문자
					else cnt += 2;                     // =와 크로아티아가 아닌 다른 알파벳 문자들
				}
				
				cnt += stack.size();
				stack.clear();
				
			} else if  (c == 'j') {
				if (stack.isEmpty()) cnt++;   // j 문자
				else {
					if ("ln".contains(stack.pollLast().toString())) cnt++;    // lj, nj 문자
					else cnt += 2;                     // =와 크로아티아가 아닌 다른 알파벳 문자들
				}
				
				cnt += stack.size();
				stack.clear();
				
			} else stack.add(c);     // 크로아티아 문자로 끝나는 경우가 아니라면 검사 대상이므로 스택에 삽입
		}
		
		cnt += stack.size();         // 스택에 남은 일반 문자들 개수까지 카운트
		System.out.println(cnt);
		
	}

}
