import java.io.*;
import java.util.*;

/**
 * [0129] PGS 정렬 H-Index
 * 정렬
 * 
 * sol)
 * 인용 횟수 배열을 오름차순으로 정렬한다
 * 인용 횟수와 해당 논문보다 많이 인용된 논문의 개수 중 더 작은 값을 h-index로 설정한다.
 * 인용 횟수를 오름차순부터 접근한다면 이전 논문들은 무조건 H-index 이하로 인용된 것이므로 두번째 조건은 고려할 필요가 없다.
 * 이러한 h-index가 최대가 되는 경우가 정답
 *
 */

public class PGS_정렬_HIndex {
    int n;
    int[] sortedCitations;
    
    public int solution(int[] citations) {
        n = citations.length;
        sortedCitations = citations;
        // 인용 횟수를 오름차순으로 정렬
        sortCitations();
        // 최대 h-index 찾기
        int answer = calcMaxHIndex();
        return answer;
    }
    
    // 인용 횟수 오름차순 정렬
    public void sortCitations() {
        Arrays.sort(sortedCitations);
    }
    
    // 최대 h-index 찾기
    public int calcMaxHIndex() {
        int hIndex = 0;
        int curHIndex = 0;
        // 인용 횟수가 적은 것부터 차례대로 확인하며
        for (int i=0; i<n; i++) {
        	// 현재 논문의 인용 횟수와 해당 횟수보다 많이 인용된 논문의 개수 중 더 작은 값 선택
            curHIndex = Math.min(sortedCitations[i], n-i);     // i번째 논문은 상위 n-i번째 논문임
            hIndex = Math.max(hIndex, curHIndex);              // 이렇게 구한 h-index를 최댓값으로 갱신
        }
        return hIndex;
    }
}