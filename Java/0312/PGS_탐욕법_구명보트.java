import java.util.*;

/**
 * [0309] PGS 탐욕법(Greedy) 구명보트
 * 그리디
 * 
 * sol)
 * 무게가 무거운 사람부터 태운 뒤 남은 공간에 다른 사람을 태울 수 있는지 확인한다. 모든 사람을 태울 때까지 반복
 *
 */

public class PGS_탐욕법_구명보트 {
    int size;
    int[] peopleWeights;            // 인덱스를 무게로 생각하고 해당 무게의 사람이 몇 명 남아있는지 저장
    
    public int solution(int[] people, int limit) {
        size = people.length;
        peopleWeights = new int[241];        // 사람의 무게는 40~240kg
        
        for (int i=0; i<size; i++) {
            int curPeople = people[i];
            peopleWeights[curPeople]++;      // 모든 사람의 무게를 인덱스로 하여 몇 명인지 저장
        }
        
        Arrays.sort(people);                 // 무게로 정렬하여 가장 무거운 사람부터 접근
        
        int answer = savePeople(people, limit);
        return answer;
    }
    
    // 무거운 사람부터 태우고 남은 공간에 다른 사람 태움
    public int savePeople(int[] people, int limit) {
        int leftLimit = limit;
        int totalBoat = 0;
        
        for (int i=size-1; i>=0; i--) {
            leftLimit = limit;
            int curWeight = people[i];
            if (peopleWeights[curWeight]-- <= 0) continue;      // 현재 무게의 사람이 이미 다른 사람과 함께 탄 적이 있으면 다음 사람으로
            
            leftLimit -= curWeight;
            saveTwo(leftLimit+1);           // 무거운 사람이 타고 남은 공간에 상대적으로 가벼운 사람이 탈 수 있는지 확인
            totalBoat++;
        }
        
        return totalBoat;
    }
    
    // 무거운 사람이 타고 남은 공간에 상대적으로 가벼운 사람 타기
    public void saveTwo(int left) {
        while (--left >= 40) {
            if (peopleWeights[left] >= 1) {     // 상대적으로 가벼운 사람의 무게에 사람이 남아있으면 태움
                peopleWeights[left]--;
                return;
            }
        }
    }
}