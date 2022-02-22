import java.util.*;

/**
 * [0219] PGS 힙(Heap) 더맵게
 * 힙, 구현, 우선순위큐
 * 
 * sol)
 * 우선순위큐를 이용해 최소힙을 구현한 뒤, 각 음식의 스코빌 지수를 저장하여 poll() 시 최솟값이 나오도록 만든다.
 * 최솟값이 K 이하일 동안 음식을 섞은 뒤 다시 최소힙에 삽입하는 것을 반복한다.
 *
 */

public class PGS_힙_더맵게 {
    PriorityQueue<Integer> pq = new PriorityQueue<>();     // 최소힙
    
    public int solution(int[] scoville, int K) {
    	// 최소힙에 음식의 스코빌 삽입
        for (int i=0, size=scoville.length; i<size; i++) pq.offer(scoville[i]);
        int answer = mix(scoville, K);
        return answer;
    }
    
    // 음식 섞기
    public int mix(int[] scoville, int K) {
        int mixCnt = 0;
        int minScoville1, minScoville2;
        
        while(!pq.isEmpty()) {
        	// 최솟값
            minScoville1 = pq.poll();
            // 최솟값이 K 이상이면 섞는 행위를 멈추고 횟수를 반환
            if (minScoville1 >= K) return mixCnt;
            if (pq.isEmpty()) return -1;
            // 최솟값이 K 미만이면 섞은 뒤 다시 최소힙에 삽입
            minScoville2 = pq.poll();
            pq.offer(minScoville1 + minScoville2 * 2);
            mixCnt++;
        }
        return mixCnt;
    }
}