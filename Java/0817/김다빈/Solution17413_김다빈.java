package P0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/** 
 * 백준 17413번 단어 뒤집기 2 
 * 풀이 :
 * 1. 문자열을 역순으로 출력하기 위해 String에 저장한 후 한 번에 push
 * 2. 공백을 만나면 지금까지 저장했던 String과 함께 push
 * 3. >을 만나면 지금까지 저장했던 String push한 후, <를 만날 때까지 하나씩 순차적으로 push 
 * 
 * 305672KB	1120ms
 */
public class Solution17413_김다빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] input = br.readLine().toCharArray();
		
		Stack<String> init = new Stack<String>();
		for(int i=0;i<input.length;i++) {
			init.push(String.valueOf(input[i]));
		}
		
		Stack<String> result = new Stack<String>();
		String s = "";
		
		while(!init.isEmpty()) {
			String cur = init.pop();
			
			if(cur.equals(" ")) {	// 공백을 만나면 " "+string push 
				result.push(cur+s);
				s = "";
			} else if(cur.equals(">")) {	// >를 만나면 string push 
				if(!s.equals("")) result.push(s);
				
				result.push(cur);	// 순서대로 출력하기 위해 < 만날 때까지 순차적으로 push 
				while(!init.peek().equals("<")) {
					result.push(init.pop());
				}
				result.push(init.pop());
				s = "";
			} else {	// 역순 출력을 위해 string에 저장 
				s += cur;
			}
		}
		
		sb.append(s);
		while(!result.isEmpty()) {
			sb.append(result.pop());
		}
		System.out.println(sb);
	}

}
