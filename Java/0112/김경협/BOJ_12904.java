import java.io.*;
import java.util.*;

/*
 * BOJ 12904번 A와 B
 * 
 * target 문자열에서 하나씩 빼면서 시작 문자열로 가는 그리디 문제
 * 
 * 	14212KB	128ms
 */

public class BOJ_12904 {
	static boolean START = true, END = false;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str = br.readLine().toCharArray();
		char[] target = br.readLine().toCharArray();
		
		Deque<Character> dq = new ArrayDeque<>();
		for(int i = 0, size = target.length; i < size; i++) {
			dq.offer(target[i]);
		}
		
		System.out.println(isConvertable(str, dq));
	}

	private static int isConvertable(char[] str, Deque<Character> dq) {
		int strLen = str.length;
		
		boolean dir = END;
		while(strLen < dq.size()) {	// target에서 뽑을 때는 뒤에서 뽑아야 함
			char tmp = getFromDqWithDir(dir, dq);
			if(tmp == 'B') dir = !dir;
		}
		
		dir = !dir; // str과 비교해줄 때에는 앞에서 부터 비교해야하기 때문에 dir 방향을 반대로
		for(int i = 0; i < strLen; i++) {
			char tmp = getFromDqWithDir(dir, dq);
			if(tmp != str[i]) return 0;
		}
		return 1;
	}
	
	static char getFromDqWithDir(boolean dir, Deque<Character> dq) {
		if(dir == START)
			return dq.pollFirst();
		else
			return dq.pollLast();
	}

}
