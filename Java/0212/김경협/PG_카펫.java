/*
 * ���α׷��ӽ� ī��
 * 
 * ������ ��ü �׵θ� �����̱� ������ �̸� �̿���
 * ���� + ���α��� = 2 + ���� / 2
 * �̰ɷ� ���� + ���α��̸� ���Ѵ�.
 * �׸��� ���� ���̸� ���� �� ���� 1�� �÷����鼭
 * ������� ��ġ�ϴ� ���μ��� ���̸� ã�´�.
 */

class PG_ī�� {
    public int[] solution(int brown, int yellow) {
        int total = brown / 2 + 2;  // ���� ���� + ���� ���� = 2 + ���� / 2
        int width = 0, height = 0;
        for(height = 3; height <= total / 2; height++) {    // ���� ���̰� ������ �ּұ��� 3���� ��+�� / 2 ���� �ݺ�
            width = total - height;
            if((width - 2) * (height - 2) == yellow)    // ���� - 2 * ���� - 2�� ������� ������ break;
                break;
        }
        int[] answer = {width, height};
        return answer;
    }
}