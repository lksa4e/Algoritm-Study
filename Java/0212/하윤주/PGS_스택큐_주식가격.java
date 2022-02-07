import java.io.*;
import java.util.*;

/**
 * [0209] PGS 스택/큐 주식가격
 * 스택
 * 
 * sol)
 * 가격이 오름차순으로 정렬되어있다고 가정한다면 각 가격의 주식 가격 증가 기간은 (전체 가격 개수 - 자신이 앞에서부터 몇번째인지)가 된다.
 * 이를 기본값이라고 생각하고 예외가 발생하면(오름차순 정렬이 아니라 중간에 자신보다 작은 가격이 등장하면) 
 * 최초에 예상한 증가 기간에서 해당 예외가 발생한 지점이 뒤에서부터 몇번째인지 계산하여 빼준다.
 * -> 자신부터 예외까지는 증가한 기간이며 이 기간이 계산됨
 * 
 * 가격을 하나씩 스택에 넣되, 스택의 top 가격이 자신보다 크면 자신보다 작거나 같은 가격아 나올때까지 pop 한다.
 * 이때 pop하게된 가격은 최초 예상했던(전부 오름차순으로 가정한 상황) 주식 가격 증가 기간에서 예외 발생 지점을 빼주어 갱신해줘야한다.
 *
 */

public class PGS_스택큐_주식가격 {
    int size;

    public int[] solution(int[] prices) {
        size = prices.length;
        int[] answer = stackPrices(prices);
        return answer;
    }

    // 주식 가격을 오름차순이 되도록 스택에 쌓음
    public int[] stackPrices(int[] prices) {
        int[] increasingPeriod = new int[size];
        Stack<Integer> stack = new Stack<>();
        increasingPeriod[0] = size-1;           // 최초 가격이 예상한 주식 가격 증가 기간
        stack.push(0);                          // 최초 가격 스택에 삽입해둠

        for (int i=1; i<size; i++) {
            increasingPeriod[i] = size-i-1;     // 각 가격의 예상 주식 가격 증가 기간

            // 하지만 예상과 달리 가격이 떨어지면 이전 동일 가격 지점을 만날때까지 스택에서 pop
            while(!stack.isEmpty() && prices[stack.peek()]>prices[i]) {
                int poppedIdx = stack.pop();
                increasingPeriod[poppedIdx] -= increasingPeriod[i];    // pop 된 가격들은 최초 예상 가격 증가 기간에서 예외가 발생한 지점 빼줌 
            }

            // 이전 동일 가격 지점을 만나게 되면(혹은 스택이 비면) 스택에 자신을 삽입하여 자신 이후의 가격은 자신을 기준으로 비교하도록 함
            stack.push(i);
        }
        return increasingPeriod;
    }
}