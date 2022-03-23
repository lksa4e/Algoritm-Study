/*
 * 프로그래머스 등굣길
 * 
 * 경로를 구하는 DP문제,
 * 우측 하단으로 이동하면서 이동할 때 마다 왼쪽과 위쪽의 경로의 수를 자신한테 더해준다.
 * 그렇게 맵 가장 우측하단으로 이동하면 모든 경우의 수를 구할 수 있다.
 * 
 * 나머지는 모듈러 연산을 이용했다.
 */

class PG_등굣길 {
    final int MODIFIER = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
        boolean[][] isPuddle = new boolean[n][m];
        for(int[] puddle : puddles) {
            isPuddle[puddle[1]-1][puddle[0]-1] = true;
        }
        int[][] map = new int[n][m];
        
        map[0][0] = 1;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(isPuddle[r][c]) continue;
                if(r != 0)
                    map[r][c] += map[r-1][c] % MODIFIER;
                if(c != 0)
                    map[r][c] += map[r][c-1] % MODIFIER;
            }
        }
        
        return map[n-1][m-1] % MODIFIER;
    }
}