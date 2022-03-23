/*
 * 프로그래머스 도둑질
 * 
 * DP 문제인데 케이스가 두 가지로 나눠진다.
 * 집이 원형으로 세워져 있어서 
 * 만약 첫 번째 집을 훔치면 마지막 집은 훔치면 안되고
 * 마지막 집을 훔치려면 첫 번째 집을 훔치면 안된다.
 * 이 두가지 경우의 수로 나눠서 DP로 풀면 된다.
 */
class PG_도둑질 {
    public int solution(int[] money) {
        int answer = 0;
        
        // 0에는 0번 집을 담았을 경우, 1에는 0번 집은 담지 않았을 경우,
        int[][] dp = new int[2][money.length];
        
        // 0번집 담았을 경우,
        dp[0][0] = money[0];
        dp[0][1] = Math.max(money[0], money[1]);
        
        // 0번집 담지 않았을 경우,
        dp[1][0] = 0;
        dp[1][1] = money[1];
        
        for(int i = 2, len = money.length; i < len; i++) {
            for(int j = 0; j < 2; j++) {
                if(i == len - 1 && j == 0)  // 0번 집을 담았을 경우, 마지막 집은 담을 수 없다.
                    continue;
                dp[j][i] = Math.max(dp[j][i-1], dp[j][i-2] + money[i]);
            }
        }
        
        // 0번 집을 담았을 경우, 끝에서 두번째 집이 최대의 값을 가지고 있고,
        // 0번 집을 패스했을 경우, 끝 집이 최대의 값을 가지고 있다.
        answer = Math.max(dp[0][money.length - 2], dp[1][money.length - 1]);
        return answer;
    }
}