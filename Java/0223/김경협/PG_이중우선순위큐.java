import java.util.*;

/*
 * ���α׷��ӽ� - ���߿켱����ť
 * 
 * �ִ񰪰� �ּڰ��� �����ϴ� ���,
 * �� ���� PQ�� �ϳ��� �ּ� ����, �ϳ��� �ִ� ���� ������ش�.
 * �ִ��� ������ ������ �ִ� ������ �ϳ� ����, ���� ���� �ּ� �������� �����ش�.
 * 
 */

class PG_���߿켱����ť {
    public int[] solution(String[] operations) {
        StringTokenizer st = null;
        // �ִ���, �ּ���
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((o1,o2)->Integer.compare(o2,o1));
        
        for(String oper : operations) {	// operations ����
            st = new StringTokenizer(oper);
            if("I".equals(st.nextToken())) {	// ���� ������ ��,
                int input = Integer.parseInt(st.nextToken());
                minPq.offer(input);	// �� �� PQ�� ��� ����
                maxPq.offer(input);
            } else {
                if(minPq.size() == 0) continue;	// PQ�� ������� ��� Ŀ�ǵ� ����,
                if("1".equals(st.nextToken())) {	// �ִ��� ���
                    int removed = maxPq.poll();	// �ִ뿡�� poll���ְ�
                    minPq.remove(removed);		// �ش� ���� �ٸ� PQ���� �������ش�.
                } else {
                    int removed = minPq.poll();
                    maxPq.remove(removed);
                }
            }
        }
        
        int[] answer = new int[2];
        
        if(minPq.size() == 0)
            return answer;
        
        answer[0] = maxPq.poll();
        answer[1] = minPq.poll();
        return answer;
    }
}