import java.util.*;
import java.io.*;

/*
 * BOJ 5430번 AC
 * 
 * command에 따라 주어진 배열을 뒤집거나 제거하는 문제
 * 
 * Deque를 사용하여 R이 주어질 경우 poll 하는 방향을 바꿨다.
 * 
 * 	70168KB	652ms	
 */

public class BOJ_5430 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < TC; i++) {
			Deque<String> dq = new ArrayDeque<>();			// deque 생성
			char[] commands = br.readLine().toCharArray();	// command 받기
			int size = Integer.parseInt(br.readLine());		// 배열의 개수
			String str = br.readLine();						// 주어진 배열
			
			strToDeque(dq, str.substring(1,str.length()-1), size);	// 주어진 배열을 Deque에 넣기
			sb.append(solve(commands, dq)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static String solve(char[] commands, Deque<String> dq) {
		boolean listOrder = true;	// 꺼낼 방향
		for(int i = 0, size = commands.length; i < size; i++) {
			if(commands[i] == 'R') 	// 꺼낼 방향 reverse
				listOrder = !listOrder;
			else if(commands[i] == 'D') {
				if(dq.isEmpty()) return "error";	// 없는데 꺼내려하면 error
				
				if(listOrder)
					dq.poll();
				else 
					dq.pollLast();
			}
		}
		
		return dequeToStr(dq, listOrder);
	}

	static void strToDeque(Deque<String> dq, String str, int size) {
		StringTokenizer st = new StringTokenizer(str, ",");
		while(size-- > 0) {
			dq.offer(st.nextToken());
		}
	}
	
	static String dequeToStr(Deque<String> dq, boolean listOrder) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(!dq.isEmpty()) {	// deque 요소 개수가 1이상일 경우만 맨 끝 , 제거
			while(!dq.isEmpty()) {
				if(listOrder) sb.append(dq.poll()).append(",");
				else sb.append(dq.pollLast()).append(",");
			}
			sb.setLength(sb.length()-1);	// 맨 끝 , 제거
		}
		sb.append("]");
		return sb.toString();
	}

}
