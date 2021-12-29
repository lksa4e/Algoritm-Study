import java.io.*;
import java.util.*;

/**
 * [1229] BOJ 5430 AC
 * 문자열, 덱
 * 
 * sol)
 * 입력 문자 배열을 덱에 담아 명령어 R이 나올 때마다 방향 플래그를 토글 변경한다.
 * 명령어 D가 나오면 방향 플래그에 따라 pollFirst() 또는 pollLast()를 수행한다.
 * 
 */

public class BOJ_5430_AC {
	static String p, arr;
	static int n, commandSize;
	static boolean isR;          // 방향 플래그
	static ArrayDeque<Integer> dq = new ArrayDeque<Integer>();    // 문자열을 저장할 덱
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) {
			p = br.readLine();                     // 명령어
			n = Integer.parseInt(br.readLine());   // 문자열 수
			
			arr = br.readLine();
			arr = arr.substring(1, arr.length()-1);                 // 시작, 종료 괄호 파싱
			StringTokenizer st = new StringTokenizer(arr, ",");     // 쉼표 파싱
			
			while(st.hasMoreTokens()) {
				dq.offer(Integer.parseInt(st.nextToken()));         // 문자열을 덱에 int형 원소로 저장
			}
			
			if (executeCommand()) {                // error가 아닐 때는 문자열 배열 출력
				StringBuilder sb = new StringBuilder("[");
				if (dq.size()!=0) {
					if (isR) {                     // 뒤집힌 상태면 pollLast()
						while(!dq.isEmpty()) {
							sb.append(dq.pollLast()).append(",");
						} 
					} else {                      // 올바른 상태면 pollFirst()
						while(!dq.isEmpty()) {
							sb.append(dq.pollFirst()).append(",");
						}
					}
					sb.setLength(sb.length()-1);
				}
				sb.append("]");
				System.out.println(sb);
			} else System.out.println("error");   // error 처리
		}
		
	}

	// 명령어 처리 후 에러 반환하는지 확인하는 메서드
	private static boolean executeCommand() {
		commandSize = p.length();
		isR = false;
		
		for (int i=0; i<commandSize; i++) {         // 명령어 개수 만큼
			char command = p.charAt(i);
			
			if (command == 'D') {
				if (dq.isEmpty()) return false;     // D 명령어를 수행해야할 때 덱이 비어있으면 error
	
				if (isR) dq.pollLast();             // 뒤집힌 상태면 pollLast()
				else dq.pollFirst();                // 올바른 상태면 pollFirst()
			} else {
				if (isR) isR = false;               // R 명령어를 수행해야할 때는 방향 상태를 토글 방식으로 변경
				else isR = true;
			}
		}
		return true;
	}

}
