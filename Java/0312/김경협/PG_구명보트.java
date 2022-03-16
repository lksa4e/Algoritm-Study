import java.util.*;

/*
 * ���α׷��ӽ� ����Ʈ
 * 
 * �ϴ� ������������ �����ϰ�
 * 1. ���� �����԰� ���� ����̶� ū ����̶� ���ؼ� limit�� �Ѵ��� Ȯ���Ѵ�.
 * 2. �Ѵ´ٸ� ���� ū ����̶� ���ؼ� 1�� �ݺ��Ѵ�. 
 * 3. �� �Ѵ´ٸ� ���� ��Ī�� ����� üũ�ϰ� ����Ʈ �ϳ��� ī��Ʈ�Ѵ�. �׸��� �� ���� ���� �����
 * �� ���� ū����̶� ���ؼ� 1�� �ݺ��Ѵ�.
 */

class PG_����Ʈ {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int minIdx = 0, maxIdx = people.length - 1, answer = 0;
        while(minIdx < maxIdx) {
        	// ���� ���� ����� ū ��� ��Ī�ؼ� limit �ʰ��ϴ��� ��.
            if(people[minIdx] + people[maxIdx] > limit) {
                maxIdx--;
            } else {	// limit�� �ȳѴ´ٸ� üũ�ϰ�, ����Ʈ ī��Ʈ
                people[minIdx++] = -1;
                people[maxIdx--] = -1;
                answer++;
            }
        }
        // ���� �� ź ��� �� �� �¿��
        for(int i = 0, len = people.length; i < len; i++) {
            if(people[i] != -1)
                answer++;
        }
        
        return answer;
    }
}