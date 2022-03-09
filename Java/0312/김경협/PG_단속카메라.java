import java.util.*;

/*
 * 프로그래머스 단속카메라
 * 
 * 나간시간으로 모두 오름차순 정렬해준다.
 * 
 * 만약 현재 비교하는 차의 진입 시간이 cctv 위치보다 크면 cctv 하나 더 설치해주고
 * 현재 자동차의 진출 시간에 cctv를 설치해 준다.
 */

class PG_단속카메라 {
    static final int CCTV_INIT = -30001;
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (a1,a2) -> Integer.compare(a1[1], a2[1]));
        
        int answer = 0, currCCTV = CCTV_INIT;
        for(int i = 0, len = routes.length; i < len; i++) {
            if(routes[i][0] <= currCCTV) continue;
            
            // CCTV 하나 더 설치해야 함
            answer++;
            currCCTV = routes[i][1];
        }
        
        return answer;
    }
}