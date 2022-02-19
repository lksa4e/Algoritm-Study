/*
 * 프로그래머스 프린터
 * 
 * 큐에 하나씩 넣고 뺄 때 중요도를 체크해서
 * 현재 큐에 지금 꺼내는 것 보다 중요도가 높은게 있으면 큐의 뒤쪽에 다시 넣어주는 식으로 구현
 */
import java.util.*;

class PG_프린터 {
    class Pair {    // 문서의 순서와 중요도를 저장하는 클래스
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
            
            for(Pair tmp : q) { // 큐에 담긴 내용물들을 모두 꺼내서 확인
                if(p.prio < tmp.prio)   // 중요도가 더 높은게 있으면,
                    flag = true;
            }
            
            if(flag) {  // 다시 목록 마지막에 넣어주기
                q.offer(p);
            }
            else {
                done++; // 작업 처리시, 끝낸 일 ++
                if(p.loc == location) {
                    return done;
                }
            }
        }
        
        return done;
    }
}