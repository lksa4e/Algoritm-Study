
/*
 * ���α׷��ӽ� ���� �ﰢ��
 * 
 * ���� �ٺ���, �ڽ� �������� ���ʰ� ������ ���� ����
 * �� �߿��� �� ū ���� �ڽ����� �����ش�. �̸� ������ �� ���� �ݺ��Ѵ�.
 */
class PG_�����ﰢ�� {
    public int solution(int[][] triangle) {
        
        int triLen = triangle.length;
        for(int i = 0; i < triLen; i++) {
            
            for(int j = 0; j < triangle[i].length; j++) {
                // ���� ���� ������ ���� ���ؼ� �� ū ���� ����
                int left = 0, right = 0;
                if(j != 0)
                    left = triangle[i - 1][j - 1];
                
                if(j != triangle[i].length - 1)
                    right = triangle[i - 1][j];
                
                triangle[i][j] += Math.max(left, right);	// ���ʰ� ������ �� �� ū ���� �����ֱ�
            }
        }
        
        int answer = 0;
        
        for(int i = 0; i < triangle[triLen - 1].length; i++) {
            answer = Math.max(answer, triangle[triLen - 1][i]);
        }
        return answer;
    }
}