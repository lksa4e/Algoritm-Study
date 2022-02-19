import java.util.*;

/*
 * 프로그래머스 - 더 맵게
 * 
 * K가 10억까지이므로 Long 타입을 사용해서 계산을 진행했다.
 * 그 외에는 PQ를 써서 스코빌 지수가 작은 음식들을 합쳤다.
 */

class PG_더맵게 {
    public int solution(int[] scoville, int K) {
    	// min_heap으로 scovile지수 저장
        PriorityQueue<Long> pq = new PriorityQueue<>();
        
        for(int sc : scoville) {
            pq.offer((long)sc);
        }
    
        return mixFoods(pq, K);
    }
    
    int mixFoods(PriorityQueue<Long> pq, int K) {
        int cnt = 0;
        
        // 현재 최솟값이 K보다 작고, pq에 2개 이상 있어서 합칠 수 있다면,
        while(pq.peek() < K && pq.size() > 1) {
            long first = pq.poll();
            long second = pq.poll();
            long mixed = first + (second * 2);
            
            pq.offer(mixed);
            cnt++;
        }
        
        if(pq.size() < 2) {	// 남은 갯수가 1개 이하일 때,
            if(pq.peek() >= K) return cnt;
            return -1;
        }
        return cnt;
    }
}