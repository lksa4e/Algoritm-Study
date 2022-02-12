import java.util.*;

/*
 * ���α׷��ӽ� �ֽ� ����
 * 
 * index 0������ ���ÿ� �����鼭 ����,
 * �����ϸ鼭 ���� ������ �������� �ʴ´ٸ� ��� ���ÿ� �ִ´�.
 * ������ �������ٸ� ������ �������� �ʴ� ������ �� ������ ���ÿ��� ����.
 * ���ÿ��� ������ index���� ������ �������� �ʾҴ� �ð����� ����ؼ� �������ش�.
 * �迭�� ������ ���� ��, ���� �ִ� �͵��� ������ ������ �������� �ʾҴ� index�̱� ������
 * �̸� �����ؼ� ������� �������ش�.
 */

class PG_�ֽİ��� {
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