/*
 * ���α׷��ӽ� ������
 * 
 * ť�� �ϳ��� �ְ� �� �� �߿䵵�� üũ�ؼ�
 * ���� ť�� ���� ������ �� ���� �߿䵵�� ������ ������ ť�� ���ʿ� �ٽ� �־��ִ� ������ ����
 */
import java.util.*;

class PG_������ {
    class Pair {    // ������ ������ �߿䵵�� �����ϴ� Ŭ����
        int loc;
        int prio;
        Pair(int loc, int prio) {
            this.loc = loc;
            this.prio = prio;
        }
    }
    
    public int solution(int[] priorities, int location) {
        Queue<Pair> q = new LinkedList<>();
        
        for(int i = 0; i < priorities.length; i++) {
            q.offer(new Pair(i, priorities[i]));
        }
        
        int done = 0;
        while(!q.isEmpty()) {
            boolean flag = false;
            Pair p = q.poll();
            
            for(Pair tmp : q) { // ť�� ��� ���빰���� ��� ������ Ȯ��
                if(p.prio < tmp.prio)   // �߿䵵�� �� ������ ������,
                    flag = true;
            }
            
            if(flag) {  // �ٽ� ��� �������� �־��ֱ�
                q.offer(p);
            }
            else {
                done++; // �۾� ó����, ���� �� ++
                if(p.loc == location) {
                    return done;
                }
            }
        }
        
        return done;
    }
}