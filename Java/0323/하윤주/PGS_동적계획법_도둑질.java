
/**
 * 
 * [0312] PGS 동적 계획법(Dynamic Programming) 도둑질
 * DP, bottom-up
 * 
 * sol)
 * 처음 DP로 접근하기 위해서는 순환형이 아니라 선형이라고 생각해야 한다!!!
 * 선형 배열처럼 생각한다면 다음 집은 우선 생각하지 않고 지금까지 거쳐온 집만 생각한다.
 * 
 * 현재 집을 털기 위해서는 직전 집은 무조건 털지 않아야하고, 이전 집중에서는 어떤 집을 털어도 괜찮다.
 * 즉, 현재 집의 최댓값은 직전 집에 저장된 최댓값이거나 아니면 직직전까지 저장된 최댓값에 자기자신을 더한 것이 됨!
 * 
 * 이를 점화식으로 표현하면 다음과 같다
 *    DP[i] = max(house[i]+DP[i-2], DP[i-1])
 *    
 * 이제 순환형의 경우를 생각해보자. 선형으로 고려해도 괜찮았던 이유는 현재의 최대는 이전까지의 최대가 누적되어있기 때문에 이전을 다시 갱신할 필요 없이 DP의 마지막이 최댓값이 된다.
 * 하지만 이 말인 즉슨, 마지막 집을 고려할 때 첫번째 집(돌고돌아 인접하게 됨)이 포함된 경우일 수 있는 것이다.
 * 따라서 첫번째집을 선택하고 마지막 집은 선택하지 않는 경우와 마지막집을 선택하고 첫번째 집을 선택하지 않는 경우, 2가지로 나누어 DP를 구해야 한다.
 * 
 * 만약 첫번째 집을 포함한다면 마지막 집이 포함되지 않으므로 DP 배열의 마지막 인덱스는 첫번째 집과 인접해도 된다(논리적으로 무조건 마지막 집이 빠지므로)
 * 따라서 DP[1]은 house[0]과 house[1] 중 더 큰 값이 되어야 한다.
 * 
 * 만약 첫번째 집을 포함하지 않는다면 DP[0]은 0으로 설정하여 논리적으로 무조건 값을 제거하고, DP[1]은 house[1](자기 자신)만 될 수 있다.
 * 
 */

public class PGS_동적계획법_도둑질 {
    int size;
    int[] firstIncludeDP;      // 첫번째 집을 포함하는 경우
    int[] lastIncludeDP;       // 마지막 집을 포함하는 경우

    public int solution(int[] money) {
        size = money.length;
        firstIncludeDP = new int[size];
        lastIncludeDP = new int[size];

        getMaxMoney(money);

        int answer = Math.max(firstIncludeDP[size-2], lastIncludeDP[size-1]);    // 첫번째 집을 포함하는 경우의 최대와 마지막 집을 포함하는 경우의 최대 중 더 큰 것으로
        return answer;
    }

    public void getMaxMoney(int[] money) {
    	// 첫번째 집을 포함
        firstIncludeDP[0] = money[0];
        firstIncludeDP[1] = Math.max(money[0], money[1]);      // 2번째 집은 1번집과 2번집 중 더 큰값
        for (int i=2; i<size-1; i++) {
            firstIncludeDP[i] = Math.max(money[i]+firstIncludeDP[i-2], firstIncludeDP[i-1]);
        }

        // 마지막 집을 포함
        lastIncludeDP[0] = 0;                                  // 1번째 집은 무조건 제거
        lastIncludeDP[1] = money[1];
        for (int i=2; i<size; i++) {
            lastIncludeDP[i] = Math.max(money[i]+lastIncludeDP[i-2], lastIncludeDP[i-1]);
        }
    }
}