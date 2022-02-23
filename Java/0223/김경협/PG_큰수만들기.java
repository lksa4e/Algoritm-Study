import java.util.*;

/*
 * 프로그래머스 큰 수 만들기
 * 
 * 스택을 활용했다.
 * 앞부터 스택에 넣으면서, peek를 체크해서 넣으려는 수가 더 크면 스택에서 빼고 또 체크하고 빼고 이런식으로 구현했다.
 * 그렇게 해서 뺀게 K개가 되면 나머지 stack에 푸쉬하고, 그 순서대로 문자열을 만들어서 반환했다.
 */

class PG_큰수만들기 {
    public String solution(String number, int k) {
        
        Stack<Character> stk = new Stack<>();
        
        for(int i = 0, size = number.length(); i < size; i++) {
            char curr = number.charAt(i);
            while(!stk.isEmpty()) {	// 스택이 비었거나, 스택의 제일 위가 더 크거나, K개 만큼 이미 뺏다면 패스,
                if(stk.peek() >= curr) break;
                if(k-- <= 0) break;
                stk.pop();	// 위 조건이 아니라면, 스택에서 빼주기
            }
            
            stk.push(curr);
        }
        
        // 내림차순 정렬 시, K자리만 남겨놓고 break
        while(k-- > 0) {
            stk.pop();
        }
        
        StringBuilder sb = new StringBuilder();
        for(char c : stk) {
            sb.append(c);
        }
        return sb.toString();
    }
}