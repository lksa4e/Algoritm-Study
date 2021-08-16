import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_17413 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stk = new Stack<>();

		
		char[] arr = br.readLine().toCharArray();
		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == '<') {
				flag = true;
				if(!stk.isEmpty()) {
					while(!stk.isEmpty()) sb.append(stk.pop());
				}
			} else if(arr[i] == '>')
				flag = false;
			
			
			if(flag)
				sb.append(arr[i]);
			else {
				if(arr[i] == ' ' || arr[i] == '>') {
					while(!stk.isEmpty()) sb.append(stk.pop());
					sb.append(arr[i]);
				} else
					stk.push(arr[i]);
			}
		}
		
		if(!stk.isEmpty())
			while(!stk.isEmpty()) sb.append(stk.pop());
		
		
		System.out.println(sb);
	}
}
