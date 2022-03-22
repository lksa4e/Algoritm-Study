import java.util.*;

/**
 * 
 * [0312] PGS 동적 계획법(Dynamic Programming) 정수 삼각형
 * DP, bottom-up
 * 
 * sol)
 * 가장 위 레벨부터 정답을 구해야 하는 아래 레벨까지 순차적으로 내려가며 누적합을 구한다.
 * 각 칸은 자신의 부모 레벨의 왼쪽 오른쪽 중 더 큰 값을 누적합한다. 양 끝단은 부모 레벨 중 각각 오른쪽과 왼쪽만 존재하므로 하나의 부모 값으로 누적합한다.
 * 점화식은 다음과 같다.
 * 
 *    DP[i][0] += DP[i-1][0]
 *    DP[i][n] += DP[i-1][n-1]
 *    DP[i][j] += max(DP[i-1][j-1], DP[i-1][j])
 * 
 */

public class PGS_동적계획법_정수삼각형 {
    int size;

    public int solution(int[][] triangle) {
        size = triangle.length;

        int answer = fillTriangleWithSum(triangle);
        return answer;
    }

    // DP 테이블 채우기
    public int fillTriangleWithSum(int[][] triangle) {
        int parentMax=0, totalMax=0;

        for (int i=1; i<size; i++) {
            for (int j=0; j<i+1; j++) {
            	// 양 끝은 무조건 부모 레벨의 양 끝으로 설정
                if (j==0) parentMax = triangle[i-1][0];
                else if (j==i) parentMax = triangle[i-1][j-1];
                // 중간은 부모 레벨의 2가지 선택지 중 더 큰 값으로 설정 
                else parentMax = Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                
                triangle[i][j] += parentMax;
                // 마지막 레벨에서는 누적합 최댓값을 구함
                if (i == size-1) totalMax = Math.max(totalMax, triangle[i][j]);
            }
        }
        return totalMax;
    }
}
