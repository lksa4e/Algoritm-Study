
/*
 * 프로그래머스 타겟 넘버
 * 
 * 간단한 완탐 문제
 * index 순서대로 dfs 돌리고,
 * 맨 마지막까지 구하고 나면 target과 일치하는치 체크
 * 
 */
class PG_타겟넘버 {
    public int answer = 0, targetNum;
    public int solution(int[] numbers, int target) {
        targetNum = target;
        
        dfs(numbers, 0, 0);
        return answer;
    }
    
    void dfs(int[] numbers, int idx, int sum) {
        if(idx == numbers.length) {	// 기저조건, 이때까지 구해진 합이 target과 일치하는지 확인
            if(sum == targetNum)
                answer++;
            return;
        }
        
        // 각 index의 숫자를 더하거나, 빼거나
        dfs(numbers, idx + 1, sum + numbers[idx]);
        dfs(numbers, idx + 1, sum - numbers[idx]);
    }
}