import java.io.*;
import java.util.*;

/*
 * BOJ 12904�� A�� B
 * 
 * target ���ڿ����� �ϳ��� ���鼭 ���� ���ڿ��� ���� �׸��� ����
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
		while(strLen < dq.size()) {	// target���� ���� ���� �ڿ��� �̾ƾ� ��
			char tmp = getFromDqWithDir(dir, dq);
			if(tmp == 'B') dir = !dir;
		}
		
		dir = !dir; // str�� ������ ������ �տ��� ���� ���ؾ��ϱ� ������ dir ������ �ݴ��
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
