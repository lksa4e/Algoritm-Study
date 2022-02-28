import java.util.*;

/**
 * [0223] PGS 탐욕법(Greedy) 큰 수 만들기
 * 스택, 그리디
 * 
 * sol)
 * 수의 각 자리 숫자를 뒤에서부터 차곡차곡 스택에 쌓는다.
 * 스택이 비지 않을 동안 하나씩 Pop()하여 스택의 top보다 작으면 버린다.
 * 총 지워야하는 정도만큼 반복한다.
 *
 */

public class PGS_탐욕법_큰수만들기 {
    public int leftK;
    public Stack<Integer> stack = new Stack<>();        // 빼야하는 전체 숫자가 저장된 스택
    public Stack<Integer> subStack = new Stack<>();     // 뺀 숫자를 임시로 저장하는 스택

    public String solution(String number, int k) {
        leftK = k;
        
        makeStack(number);     // 수의 각 자리 숫자를 스택에 넣고
        removeNumber();        // 꼭대기 수를 비교하여 하나씩 뺌

        String answer = getMaxNumber();
        return answer;
    }

    // 수를 스택에 쌓기
    public void makeStack(String number) {
    	// 맨 뒤의 숫자부터 쌓는다
        for (int size=number.length(), i=size-1; i>=0; i--) {
            stack.push(Integer.parseInt(number.substring(i, i+1)));
        }
    }

    // 꼭대기의 숫자 하나씩 지우기
    public void removeNumber() {
        while(leftK-->0) {
        	// 현재 자리 숫자가 다음 자리 숫자보다 작으면 더 앞에 있는 숫자가 작은 경우이므로 지워야지 최대가 된다
            int cur = stack.pop();

            while(!stack.isEmpty() && cur >= stack.peek()) {
                subStack.push(cur);
                cur = stack.pop();       // while문 마지막으로 나온 cur는 스택의 꼭대기보다 작은 숫자이므로 pop되어 지워짐
            }
            // 지운 숫자의 다음 자리 숫자와 직전 자리 숫자를 비교하기 위해 임시 스택에 저장했던 수 중 하나만 다시 스택에 쌓음
            if(!subStack.isEmpty()) stack.push(subStack.pop());
        }
        // 지워야하는 숫자 개수만큼 지웠으면 임시 스택에 저장했던 수들을 다시 스택에 쌓아 최종 수 완성
        while(!subStack.isEmpty()) stack.push(subStack.pop());
    }

    // 스택에 저장된 최종 수를 출력
    public String getMaxNumber() {
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

}
