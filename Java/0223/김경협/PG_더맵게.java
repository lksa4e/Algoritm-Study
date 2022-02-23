import java.util.*;

/*
 * ���α׷��ӽ� - �� �ʰ�
 * 
 * K�� 10������̹Ƿ� Long Ÿ���� ����ؼ� ����� �����ߴ�.
 * �� �ܿ��� PQ�� �Ἥ ���ں� ������ ���� ���ĵ��� ���ƴ�.
 */

class PG_���ʰ� {
    public int solution(int[] scoville, int K) {
    	// min_heap���� scovile���� ����
        PriorityQueue<Long> pq = new PriorityQueue<>();
        
        for(int sc : scoville) {
            pq.offer((long)sc);
        }
    
        return mixFoods(pq, K);
    }
    
    int mixFoods(PriorityQueue<Long> pq, int K) {
        int cnt = 0;
        
        // ���� �ּڰ��� K���� �۰�, pq�� 2�� �̻� �־ ��ĥ �� �ִٸ�,
        while(pq.peek() < K && pq.size() > 1) {
            long first = pq.poll();
            long second = pq.poll();
            long mixed = first + (second * 2);
            
            pq.offer(mixed);
            cnt++;
        }
        
        if(pq.size() < 2) {	// ���� ������ 1�� ������ ��,
            if(pq.peek() >= K) return cnt;
            return -1;
        }
        return cnt;
    }
}