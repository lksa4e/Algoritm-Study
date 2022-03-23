
/*
 * 프로그래머스 정수 삼각형
 * 
 * 위쪽 줄부터, 자신 기준으로 왼쪽과 오른쪽 위를 보고
 * 둘 중에서 더 큰 값을 자신한테 더해준다. 이를 마지막 줄 까지 반복한다.
 */
class PG_정수삼각형 {
    public int solution(int[][] triangle) {
        
        int triLen = triangle.length;
        for(int i = 0; i < triLen; i++) {
            
            for(int j = 0; j < triangle[i].length; j++) {
                // 왼쪽 위와 오른쪽 위를 비교해서 더 큰 값을 가짐
                int left = 0, right = 0;
                if(j != 0)
                    left = triangle[i - 1][j - 1];
                
                if(j != triangle[i].length - 1)
                    right = triangle[i - 1][j];
                
                triangle[i][j] += Math.max(left, right);	// 왼쪽과 오른쪽 중 더 큰 값을 더해주기
            }
        }
        
        int answer = 0;
        
        for(int i = 0; i < triangle[triLen - 1].length; i++) {
            answer = Math.max(answer, triangle[triLen - 1][i]);
        }
        return answer;
    }
}