import java.util.*;

/*
 * 프로그래머스 - 이중우선순위큐
 * 
 * 최댓값과 최솟값을 삭제하는 방법,
 * 두 개의 PQ로 하나는 최소 힙을, 하나는 최대 힙을 만들어준다.
 * 최댓값을 삭제할 때에는 최대 힙에서 하나 빼고, 빠진 값을 최소 힙에서도 없애준다.
 * 
 */

class PG_이중우선순위큐 {
    public int[] solution(String[] operations) {
        StringTokenizer st = null;
        // 최대힙, 최소힙
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((o1,o2)->Integer.compare(o2,o1));
        
        for(String oper : operations) {	// operations 진행
            st = new StringTokenizer(oper);
            if("I".equals(st.nextToken())) {	// 숫자 삽입할 때,
                int input = Integer.parseInt(st.nextToken());
                minPq.offer(input);	// 양 쪽 PQ에 모두 삽입
                maxPq.offer(input);
            } else {
                if(minPq.size() == 0) continue;	// PQ가 비어있을 경우 커맨드 무시,
                if("1".equals(st.nextToken())) {	// 최대의 경우
                    int removed = maxPq.poll();	// 최대에서 poll해주고
                    minPq.remove(removed);		// 해당 값을 다른 PQ에서 삭제해준다.
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