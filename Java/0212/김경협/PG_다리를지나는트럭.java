import java.util.*;

/*
 * ���α׷��ӽ� �ٸ��� ������ Ʈ��
 * 
 * �ٸ��� �ߵ� �� �ִ� ���� weight�� �ٸ��� ����,
 * �׸��� Ʈ������ ���԰� �־�����.
 * Queue�� �̿��� Ʈ���� ������ ��츦 ���, 1�ʸ��� Ʈ���� q�� �־��ְ�,
 * ���� �ʰ��� Ʈ���� �� ���� ��� 0�� ��� �־���. (ť�� ����� �ٸ��� ���̿� �������� ��� ��������ϱ� ������)
 */

class PG_�ٸ���������Ʈ�� {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        int curr_w = 0, idx = 0, size = truck_weights.length;   // ���� ���� �ٸ��� �ִ� Ʈ���� ����, ��������, Ʈ���� ����
        
        Queue<Integer> q = new LinkedList<>();
        
        while(true) {
            if(idx == size) break;  // ������ Ʈ���� �ٸ��� �ö󰡸� ���� Ż��
            
            // �ٸ��� �ִ� Ʈ���� �ٸ� ���� �����ϸ�, �ٸ� �� Ʈ���� ���� ����
            if(q.size() == bridge_length) {
                curr_w -= q.poll();
            
            } else if (curr_w + truck_weights[idx] > weight) {
                // Ʈ���� �� �ø� ���,
                q.offer(0);
                answer++;
            } else {
                q.offer(truck_weights[idx]);
                curr_w += truck_weights[idx];
                answer++;
                idx++;
            }
        }
        
        // ������ Ʈ���� �ٸ��� �ö� ���¿��� break �Ǿ��� ������, �ٸ� ���̸�ŭ �����༭ return
        return answer + bridge_length;
    }
}