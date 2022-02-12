import java.util.*;

/*
 * 프로그래머스 주식 가격
 * 
 * index 0번부터 스택에 넣으면서 시작,
 * 진행하면서 만약 가격이 떨어지지 않는다면 계속 스택에 넣는다.
 * 가격이 떨어진다면 가격이 떨어지지 않는 순서가 될 때까지 스택에서 뺀다.
 * 스택에서 빠지는 index들의 가격이 떨어지지 않았던 시간들을 계산해서 저장해준다.
 * 배열의 끝까지 돌린 뒤, 남아 있는 것들은 끝까지 가격이 떨어지지 않았던 index이기 때문에
 * 이를 산정해서 결과값을 저장해준다.
 */

class PG_주식가격 {
    public int[] solution(int[] prices) {
        int[] store = new int[prices.length];
        
        Stack<Integer> stk = new Stack<>();
        
        for(int i = 0 ; i < prices.length; i++) {
            while(!stk.isEmpty() && prices[i] < prices[stk.peek()]) {
                store[stk.peek()] = i - stk.peek();
                stk.pop();
            }
            stk.push(i);
        }
        
        while(!stk.isEmpty()) {
            store[stk.peek()] = prices.length - stk.peek() - 1;
            stk.pop();
        }
        
        return store;
    }
}