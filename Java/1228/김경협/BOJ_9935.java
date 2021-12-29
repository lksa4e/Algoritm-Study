import java.io.*;
import java.util.*;

/*
 * BOJ 9935번 문자열 폭발
 * 
 * Stack 2개를 사용해서 아직 읽지 않은 문자열, 읽은 문자열을 저장했다.
 * 읽지 않은 문자열에서 하나씩 꺼내오면서 폭발 문자열과의 일치 여부를
 * coIdx를 통해 관리했다.
 * 그리고 문자열이 폭발하면 읽은 문자열에서 적당히 빼서 다시 읽지 않은 문자열에 넣어
 * 폭발 후 만들어진 문자열이 다시 폭발할 수 있도록 했다.
 * 
 * 74748KB	664ms
 */

public class BOJ_9935 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();		// 바꿀 문자열
		char[] bomb = br.readLine().toCharArray();		// 폭발 문자열
		
		System.out.println(solve(arr, bomb));
	}
	
	static String solve(char[] arr, char[] bomb) {
		int bombLen = bomb.length, coIdx = 0;
		Stack<Character> unreadStk = new Stack<>();		// 아직 읽지 않은 문자열
		Stack<Character> readedStk = new Stack<>();		// 읽은 문자열
		
		for (int i = arr.length - 1; i >= 0; i--) {		// 읽지 않은 문자열 스택에 넣어주기
			unreadStk.push(arr[i]);
		}
		
		while(!unreadStk.isEmpty()) {
			char curr = unreadStk.pop();
			
			if(curr == bomb[coIdx]) {		// 폭발 문자열 coIdx 관리, 일치시 coIdx++
				coIdx++;
			} else if(curr == bomb[0]) {	// 불일치 --> 폭발 문자열  첫자리와 동일
				coIdx = 1;
			} else							// 불일치 --> coIdx 초기화
				coIdx = 0;
			
			if(coIdx == bombLen) {			// 폭발 문자열 만들어진 경우
				coIdx = 0;	// coIdx 초기화
				for (int i = 0; i < bombLen-1; i++) {	// readedStk에서 폭발 문자열의 문자수-1만큼 pop
					readedStk.pop();
				}
				
				for(int i = 0; i < bombLen-1; i++) {	// 폭발 후, 새로 만들어진 문자열에서 폭발문자열이 만들어질 수 있기 때문에
					if(readedStk.isEmpty()) break;		// unreadStk으로 폭발 문자열의 문자수-1만큼 다시 옮겨주기
					unreadStk.push(readedStk.pop());
				}
				continue;
			}
			
			readedStk.push(curr);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(!readedStk.isEmpty()) {	// readed 스택에 뭔가 남아 있는 경우
			while(!readedStk.isEmpty())
				sb.append(readedStk.pop());	// 꺼내고
			return sb.reverse().toString();	// 뒤집어서 반환
		} else return "FRULA";	// readed 스택이 비어 있을 경우, FRULA 반환
	}

}
