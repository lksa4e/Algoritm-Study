import java.io.*;
import java.util.*;
/**
 * [D3]SWEA 1234 [S/W 문제해결 기본] 10일차 - 비밀번호 : 
 * 메모리 : 18420kb 시간 : 99ms
 * 
 * 1. 스택의 성질을 활용하여 넣으려고 할때 top과 같으면 넣지 않고 pop
 * 2. 스택을 사용할경우 뒤집는 것이 귀찮으므로 deque 사용
 * 
 */
 
public class SWEA_1234_비밀번호_김경준 {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static String str;
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        for(int tc = 1; tc <= 10; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	str = st.nextToken();
        	sb.append("#").append(tc).append(" ");
        	solve();
        	sb.append("\n");
        }
        System.out.print(sb);
    }
    static void solve() {
    	Deque<Character> dq = new ArrayDeque<Character>();
    	for(int i = 0; i < str.length(); i++) {
    		if(!dq.isEmpty() && dq.peekLast() == str.charAt(i)) dq.pollLast(); // 연속숫자면 pop
    		else dq.addLast(str.charAt(i));  // 연속 아니면 붙이기
    	}
    	while(!dq.isEmpty()) {
    		sb.append(dq.pollFirst()); // 앞에서부터 뽑아서 sb에 넣기
    	}
    }
}
