/*
 * ���α׷��ӽ� ���
 * 
 * ��θ� ���ϴ� DP����,
 * ���� �ϴ����� �̵��ϸ鼭 �̵��� �� ���� ���ʰ� ������ ����� ���� �ڽ����� �����ش�.
 * �׷��� �� ���� �����ϴ����� �̵��ϸ� ��� ����� ���� ���� �� �ִ�.
 * 
 * �������� ��ⷯ ������ �̿��ߴ�.
 */

class PG_��� {
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