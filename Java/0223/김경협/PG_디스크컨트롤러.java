import java.util.*;

/*
 * ���α׷��ӽ� - ��ũ ��Ʈ�ѷ�
 * 
 * ���μ��� �����췯�� �ð� �����ؼ� ��� �� ���̱�.
 * 
 * �� ���� PQ�� ����ߴ�.
 * ù ��° PQ���� ���� �ִ� ��� jobs�� �־��ش�. �� ��, ���� �ð��� ���� �������,
 * ���� ������ ���ٸ� �� �۾��ð��� ª�� ������� �����Ѵ�.
 * �� ��° PQ���� �۾� �ð��� ª�� ������θ� �����Ѵ�.
 * 
 * idx�� ����Ͽ� 0�� ���� ���� �� ���� 1�ʾ� �ø��鼭 �����Ѵ�.
 * ù ��° PQ�� peek���� ��û�� �����ϴ� �ð��� idx�� ��������  �ι� ° PQ�� �־��ش�.
 * �� ��° PQ������ ���� �۾� ���� ���� ���ٸ� �ٷ� ������ �۾��� �����ϰ�,
 * �۾� ���̾��ٸ� �۾��� ���� ������ ��ٷȴٰ� �۾��� �����Ѵ�.
 * ù��° PQ�� �ι�° PQ�� ����� ������ ����Ѵ�.
 * 
 */

class PG_��ũ��Ʈ�ѷ� {
    public int solution(int[][] jobs) {
    	// ù ��° PQ, ��� jobs�� �־��ش�. ���� �ð��� ���� �������, ���ٸ� �۾��ð��� ª�� ������� �����Ѵ�.
        PriorityQueue<int[]> jobScheduler = new PriorityQueue<>((o1,o2)->o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));
        // �� ��° PQ, �۾� �ð��� ª�� ������θ� �����Ѵ�.
        PriorityQueue<int[]> tasks = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1],o2[1]));
        
        // todo�� ���� �ؾ� �ϴ� ����, idx�� ���� �ð���, sum�� �� �۾��ð��� �ǹ��Ѵ�.
        int todo = 0, idx = 0, sum = 0;
        
        for(int[] job : jobs) {	// ��� �۾��� ù ��° PQ�� �־��ش�.
            jobScheduler.offer(job);
        }
        
        while(true) {
        	// ù ��° PQ�� ������� �ʰ�, ���� peek�� ���� ���۽ð��� ���� �ð��� ���ٸ� �� ��° PQ�� �Ű��ش�.
            while(!jobScheduler.isEmpty() && jobScheduler.peek()[0] == idx) {
                tasks.offer(jobScheduler.poll());
            }
            if(todo == 0) {	// ���� �۾� �ϴ� ���� ���� ����,
                if(tasks.size() == 0) {
                    if(jobScheduler.size() == 0) break; // ��� PQ�� ��� �����Ƿ� ����
                    idx++;	// ���� ���۽ð��� �ȵǾ �� ��° PQ�� ������ ���� �۾��� �����Ƿ� idx�� �÷��ְ� continue
                    continue;
                }
                int[] nextWork = tasks.poll();	// ���� �۾� �̾ƿ���
                todo = nextWork[1];	// ���� �۾� �Ҵ緮
                sum += todo + idx - nextWork[0];	// �۾��� ��� �ð��� �۾��� + ���� �ð� - ���۽ð�
            }
            todo--;
            idx++;
        }
        int answer = sum / jobs.length;
        return answer;
    }
}