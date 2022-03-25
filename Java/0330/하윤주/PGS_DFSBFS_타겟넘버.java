/**
 * 
 * [0325] PGS 깊이/너비 우선 탐색(DFS/BFS) 타겟 넘버
 * DFS, 완탐
 * 
 * sol)
 * 재귀적으로 배열의 모든 원소를 확인할 때까지 각 원소를 더하고 빼본다.
 * 배열의 모든 원소를 확인한 경우 연산 결과가 타겟 넘버와 같으면 경우의 수를 1 증가하여 개수를 센다.
 *
 */

public class PGS_DFSBFS_타겟넘버 {
    int size, caseCnt;

    public int solution(int[] numbers, int target) {
        size = numbers.length;
        dfs(0, 0, numbers, target);
        int answer = caseCnt;
        return answer;
    }

    // 재귀적으로 모든 경우의 수 연산 결과를 계산
    public void dfs(int depth, int total, int[] numbers, int target) {
    	// 기저조건 : 배열의 모든 원소를 확인하면
        if (depth == size) {
        	// 지금까지의 연산 결과가 타겟 넘버와 같은지 확인
            if (total == target) caseCnt++;
            return;
        }

        int nextNum = numbers[depth];
        dfs(depth+1, total+nextNum, numbers, target);    // 플러스 연산
        dfs(depth+1, total-nextNum, numbers, target);    // 마이너스 연산
    }
}