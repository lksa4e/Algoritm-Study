import java.util.*;

/*
 * 프로그래머스 N으로 표현
 * 
 * 방법을 못 찾아서 헤매다가 질문하기에서 힌트를 얻어 풀었다.
 * 상향식으로 채워나가며 문제를 풀면 된다.
 * 예를 들어 (5)을 N을 5개 써서 만들 수 있는 모든 경우의 수라고 두고
 * <>를 사칙 연산이라고 두고, +를 경우의 수를 합친다는 의미로 두면
 * 
 * (2) = (1) <> (1)
 * (3) = (1) <> (2) + (2) <> (1)
 * (4) = (1) <> (3) + (2) <> (2) + (3) <> (1)
 * ...
 * 
 * 이런식으로 아래부터 채워가며 문제를 풀 수 있다.
 */

class PG_N으로표현 {
    public int solution(int N, int number) {
        
        List<Integer> nCases[] = new ArrayList[9];
        nCases[1] = new ArrayList<Integer>();
        nCases[1].add(N);
        if(N == number)
            return 1;
        
        int answer = -1;
        
        for(int i = 2; i < 9; i++) {
            nCases[i] = new ArrayList<Integer>();
            
            countAllCase(nCases, i, N);
            if(nCases[i].contains(number)) {
                answer = i;
                break;
            }
        }
        return answer;
    }
    
    void countAllCase(List<Integer> nCases[], int num, int N) {
        HashSet<Integer> hash = new HashSet<>();
        // NN의 경우, ex) 22, 222, 2222
        int x = N;
        for(int i = 1; i < num; i++) {
            x *= 10;
            x += N;
        }
        nCases[num].add(x);
        hash.add(x);
        

        for(int i = 1; i < num; i++) {
            // 모든 경우의 수 합치기 (1의 경우, num - 1의 경우), (2의 경우, num - 2 경우) ,,,
            List<Integer> first = nCases[i];
            List<Integer> second = nCases[num - i];
            
            // 사칙 연산
            for(int a = 0, aSize = first.size(), bSize = second.size(); a < aSize; a++) {
                int num1 = first.get(a);
                for(int b = 0; b < bSize; b++) {
                    int num2 = second.get(b);
                    
                    // 더하기
                    int result = num1 + num2;
                    if(!hash.contains(result)) {
                        hash.add(result);
                        nCases[num].add(result);
                    }
                    
                    // 빼기
                    result = num1 - num2;
                    if(!hash.contains(result)) {
                        hash.add(result);
                        nCases[num].add(result);
                    }
                    
                    // 곱하기
                    result = num1 * num2;
                    if(!hash.contains(result)) {
                        hash.add(result);
                        nCases[num].add(result);
                    }
                    
                    // 나누기 ( / 0 제외)
                    if(num2 != 0) {
                        result = num1 / num2;
                        if(!hash.contains(result)) {
                            hash.add(result);
                            nCases[num].add(result);
                        }
                    }
                    
                }
            }
        }
    }
    
    
}