import java.util.*;

/*
 * 프로그래머스 해시 - 전화번호 목록
 * 
 * 해시로 풀라는데 어디에 해시가 들어갈지 모르겠습니다..
 * 그래서 익숙한 PQ로 정렬하고
 * 인접한 두 개를 비교해서 앞의 값이 뒤의 값의 접두사가 되는지 확인하는 방식으로 구현했습니다.
 */

class PG_전화번호_목록 {
    public boolean solution(String[] phone_book) {
        
        PriorityQueue<String> pq = new PriorityQueue<>();
        
        int size = phone_book.length;
        for(int i = 0; i < size; i++) {
            pq.offer(phone_book[i]);	// PQ로 String 오름차순
        }
        
        String curr = pq.poll();
        for(int i = 0; i < size - 1; i++) {	// curr 값이 next 값의 접두사가 되는지 검사
            String next = pq.poll();
            if(next.startsWith(curr))
                return false;
            curr = next;
        }
        
        return true;
    }
}