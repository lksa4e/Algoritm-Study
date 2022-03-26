
/*
 * ���α׷��ӽ� Ÿ�� �ѹ�
 * 
 * ������ ��Ž ����
 * index ������� dfs ������,
 * �� ���������� ���ϰ� ���� target�� ��ġ�ϴ�ġ üũ
 * 
 */
class PG_Ÿ�ٳѹ� {
    public int answer = 0, targetNum;
    public int solution(int[] numbers, int target) {
        targetNum = target;
        
        dfs(numbers, 0, 0);
        return answer;
    }
    
    void dfs(int[] numbers, int idx, int sum) {
        if(idx == numbers.length) {	// ��������, �̶����� ������ ���� target�� ��ġ�ϴ��� Ȯ��
            if(sum == targetNum)
                answer++;
            return;
        }
        
        // �� index�� ���ڸ� ���ϰų�, ���ų�
        dfs(numbers, idx + 1, sum + numbers[idx]);
        dfs(numbers, idx + 1, sum - numbers[idx]);
    }
}