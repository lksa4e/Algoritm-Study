import java.util.*;

/*
 * 프로그래머스 구명보트
 * 
 * 일단 오름차순으로 정렬하고
 * 1. 가장 몸무게가 작은 사람이랑 큰 사람이랑 더해서 limit을 넘는지 확인한다.
 * 2. 넘는다면 다음 큰 사람이랑 비교해서 1을 반복한다. 
 * 3. 안 넘는다면 현재 매칭된 사람을 체크하고 구명보트 하나를 카운트한다. 그리고 그 다음 작은 사람과
 * 그 다음 큰사람이랑 비교해서 1을 반복한다.
 */

class PG_구명보트 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int minIdx = 0, maxIdx = people.length - 1, answer = 0;
        while(minIdx < maxIdx) {
        	// 현재 작은 사람과 큰 사람 매칭해서 limit 초과하는지 비교.
            if(people[minIdx] + people[maxIdx] > limit) {
                maxIdx--;
            } else {	// limit을 안넘는다면 체크하고, 구명보트 카운트
                people[minIdx++] = -1;
                people[maxIdx--] = -1;
                answer++;
            }
        }
        // 아직 못 탄 사람 한 명씩 태우기
        for(int i = 0, len = people.length; i < len; i++) {
            if(people[i] != -1)
                answer++;
        }
        
        return answer;
    }
}