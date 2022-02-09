import java.util.*;

/*
 * ���α׷��ӽ� �ؽ� - ��ȭ��ȣ ���
 * 
 * �ؽ÷� Ǯ��µ� ��� �ؽð� ���� �𸣰ڽ��ϴ�..
 * �׷��� �ͼ��� PQ�� �����ϰ�
 * ������ �� ���� ���ؼ� ���� ���� ���� ���� ���λ簡 �Ǵ��� Ȯ���ϴ� ������� �����߽��ϴ�.
 */

class PG_��ȭ��ȣ_��� {
    public boolean solution(String[] phone_book) {
        
        PriorityQueue<String> pq = new PriorityQueue<>();
        
        int size = phone_book.length;
        for(int i = 0; i < size; i++) {
            pq.offer(phone_book[i]);	// PQ�� String ��������
        }
        
        String curr = pq.poll();
        for(int i = 0; i < size - 1; i++) {	// curr ���� next ���� ���λ簡 �Ǵ��� �˻�
            String next = pq.poll();
            if(next.startsWith(curr))
                return false;
            curr = next;
        }
        
        return true;
    }
}