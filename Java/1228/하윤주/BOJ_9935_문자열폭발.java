import java.io.*;
import java.util.*;

/**
 * [1229] BOJ 9935 문자열 폭발
 * 문자열, 스택
 * 
 * sol)
 * 문자열을 뒤에서부터 문자 하나씩 파싱하여 스택에 차곡 차곡 쌓는다.
 * 맨 꼭대기가 폭탄의 시작 문자와 같고, 스택 길이가 폭탄의 길이와 같으면 폭탄만큼의 스택 상단 서브 문자열이 폭탄과 같은지 비교
 * 
 * 시행착오)
 * - 메모리 초과 : 스택 자료구조 대신 StringBuilder를 이용해서 스택과 같은 원리로 풀었는데 메모리 초과 발생.
 *   스트링 빌더 크기가 1,000,000을 다 담지 못하는 것 같다. 이부분은 다시 테스트 해봐야겠다.
 *   
 * - 시간 초과 : StringBuilder를 Stack으로 바꾼 뒤 스택을 pop할 때마다 출력하도록 했는데 시간 초과 발생.
 *   다시 StringBuilder에 담아 출력하여 해결
 *
 */

public class BOJ_9935_문자열폭발 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] strArray = br.readLine().toCharArray();
		char[] bomb = br.readLine().toCharArray();
		int bombSize = bomb.length;
		int stackSize = 0;
		char bombStart = bomb[0];
		
		Stack<Character> stack = new Stack<Character>();
		
		OUTER:for (int strSize=strArray.length, i=strSize-1; i>=0; i--) {
			stack.push(strArray[i]);      // 문자열 뒤의 문자부터 스택에 쌓음
			stackSize = stack.size();
			
			if (stack.peek()==bombStart && stackSize>=bombSize) {     // 스택 꼭대기와 폭발 문자열 시작이 같고, 스택 길이도 폭발 문자열보다 크면
				for (int idx=0, j=stackSize-1; j>=stackSize-bombSize; j--) {
					if (stack.get(j) != bomb[idx++]) continue OUTER;      // 문자를 하나씩 비교해 다를 경우에는 pass
				}
				for (int j=0; j<bombSize; j++) {       // 부분 문자열과 폭발 문자열이 일치하면 스택에서 부분 문자열 삭제
					stack.pop();
				}
			}
		}
		
		if (stack.size()==0) System.out.println("FRULA");
		else {
			StringBuilder sb = new StringBuilder();
			while(!stack.isEmpty()) { 
				sb.append(stack.pop());        // 출력은 StringBuilder에 담아 출력
			}
			System.out.println(sb);
		}
	}
	
}
