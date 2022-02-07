import java.io.*;
import java.util.*;

/**
 * [0209] PGS 스택/큐 프린터
 * 큐, 배열의 인덱스 관리
 * 
 * sol)
 * 큐를 사용하지 않고 배열 인덱스를 이용하여 해결
 * 처음부터 끝까지 순회하며 우선순위 최댓값인 인덱스를 찾고 순위를 반영
 * 다음 순회부터는 (이전에 찾은 인덱스 ~ 마지막 인덱스) / (0번 인덱스 ~ 이전에 찾은 인덱스) 순서로 순회하며 우선순위 최댓값을 찾는 과정 반복
 * 
 * 시간복잡도)
 * N^2
 * 
 */

public class PGS_스택큐_프린터 {
    int size, start, rank;
    int[] ranks;

    public int solution(int[] priorities, int location) {
        size = priorities.length;
        ranks = new int[size];
        start = 0;       // 검사 시작지점
        rank = 1;        // 현재 인덱스의 순위
        
        // 모든 인덱스의 순위를 구할 때까지 반복
        while(rank <= size) {
            getRanks(priorities);
        }
        
        int answer = ranks[location];
        return answer;
    }

    // 모든 인덱스 순위 구하기
    public void getRanks(int[] priorities) {
        int max = 0;
        int maxIdx = -1;

        // 이전 순회에서 찾은 최우선 인덱스 이후부터 최우선 인덱스 직전까지를 다시 순회
        for (int i=start-1, j=0; j<size; j++) {
        	// (최우선 인덱스 ~ 마지막 인덱스)까지 조회했다면 (0번 인덱스 ~ 최우선 인덱스)를 순회
            if (++i==size) i=0;
            
            int priority = priorities[i];
            if (max < priority) {
                max = priority;
                maxIdx = i;
                start = i+1;
            }
        }
        // 새롭게 찾은 최우선 인덱스는 방문처리하고 몇번째 순위인지 기록
        priorities[maxIdx] = 0;
        ranks[maxIdx] = rank++;
    }
}
