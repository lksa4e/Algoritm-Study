import java.util.*;

/**
 * [0312] PGS 동적 계획법(Dynamic Programming) N으로 표현
 * DP, bottom-up
 * 
 * sol)
 * 전형적인 bottom-up 방식의 DP
 * 
 * N을 1개~8개 이용하여 만들 수 있는 수 DP 테이블을 모두 생성한다.
 * 각 DP 테이블에는 우선 N을 1~8개 이어붙인 수를 저장해둔다.(N, NN, NNN, ...)
 * 
 * n번째 DP 테이블은 (1번째 DP 테이블 수 ~ (n-1-1), (n-1-1)번째 DP 테이블 수 ~ 1번째 DP 테이블 수)를 사칙연산하여 구할 수 있다.
 * 어차피 사칙연산을 수행한 테이블과 테이블일지라도 곱셈, 나눗셈 연산은 피연산자 순서에 따라 결과가 다르므로 순서를 달리하여 연산해줘야한다.
 * 
 * ex)
 * 4번째 DP 테이블은 (1, 3) (2, 2) (3, 1) 번째끼리 연산해준 결과를 모두 포함하게 됨.
 * 
 * 시행착오)
 * top-down + 재귀 완탐 방식으로 결과 number에 사칙연산을 수행한 결과를 다음 재귀로 넘기면서 풀었는데 테케 1~4번까지만 맞고 나머지는 죽어도 틀림... 
 * 아마 이전 연산 결과에 체이닝하는 경우 말고 괄호를 통한 연산을 수행하는 경우를 포함하지 못하기때문에 틀렸던 것 같다.
 * 무조건 모든 순서를 고려한 연산을 하는 DP 방식으로 풀어야 한다.
 *
 */

public class PGS_동적계획법_N으로표현 {
    public List<Set<Integer>> operations = new ArrayList<>();    // 1~8 번의 연산 경우마다 만들 수 있는 수를 모두 저장

    public int solution(int N, int number) {
        fillWithN(N);      // N으로만 채운 수를 저장
        operate();         // 사칙연산으로 만든 수를 저장
        return findMinOperation(number);      // 최소 연산 횟수 구하기
    }

    // N으로만 채우기
    public void fillWithN(int N) {
        int n = 0;
        for (int i=0; i<8; i++) {
            Set<Integer> result = new HashSet<>();
            n = n * 10 + N;
            result.add(n);
            operations.add(result);
        }
    }

    // 사칙연산 결과로 채우기
    public void operate() {
    	// N을 2개 이상 8개 이하로 사용하는 경우(N을 1개만 사용하는 경우는 연산결과가 N뿐)
        for (int i=1; i<8; i++) {
            Set<Integer> target = operations.get(i);

            // i번째 DP 테이블은 (0부터 i-1까지의 DP 테이블의 조합으로 채움)
            for (int j=0; j<i; j++) {
                // 0 ~ i-1에서 양 끝단의 2개 조합
                Set<Integer> op1 = operations.get(j);
                Set<Integer> op2 = operations.get(i-j-1);

                for (int result1 : op1) {
                    for (int result2 : op2) {
                    	// 사칙연산
                        target.add(result1 + result2);
                        target.add(result1 - result2);
                        target.add(result1 * result2);
                        if (result1 != 0 && result2 != 0) target.add(result1 / result2);
                    }
                }
            }
        }
    }

    // 최소 연산 횟수 구하기
    public int findMinOperation(int number) {
        for (int i=0; i<8; i++) {
            Set<Integer> operationSet = operations.get(i);

            // 연산 횟수 작은 경우부터 보면서 원하는 수인 number를 찾으면 종료
            for (int result : operationSet) {
                if (result == number) return i+1;
            }
        }
        return -1;
    }

}
