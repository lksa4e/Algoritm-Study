import java.util.*;

/**
 * [0309] PGS 탐욕법(Greedy) 단속카메라
 * 그리디, 정렬
 * 
 * sol)
 * 차량 진입 지점을 기준으로 오름차순 정렬한다(먼저 들어온 차를 먼저 확인)
 * 현재 차량이 이전 진출 시점보다 먼저 진입했으면 카메라를 중복해서 설치할 수 있음
 * 하지만 현재 차량이 이전 진출 시점보다 나중에 진입했으면 카메라를 새롭게 설치해야하므로 카메라 개수를 증가함
 * 
 * 이때 주의해야할 것은 이전 진출 시점을 갱신하는 부분이다.
 * 현재 차량의 진입 시점이 이전 진출 시점보다 나중이어서 카메라를 새롭게 설치하게 된 경우와
 * 현재 차량의 진출 시점이 이전 진출보다 이전이 되면 교집합 부분으로 묶기 위해 이전 진출을 현재 진출로 갱신해줘야한다.
 * 
 */

public class PGS_탐욕법_단속카메라 {
    int size, high, camera=1;
    int[][] sortedRoutes;
    
    public int solution(int[][] routes) {
        size = routes.length;
        sortedRoutes = new int[size][2];
        
        sortRoutes(routes);       // 진입 시점 기준으로 오름차순 정렬
        installCamera();          // 설치하는 카메라 개수 카운트
        
        int answer = camera;
        return answer;
    }
    
    // 진입 시점 기준 오름차순 정렬
    public void sortRoutes(int[][] routes) {
        Arrays.sort(routes, (r1, r2) -> r1[0] - r2[0]);
        sortedRoutes = routes;
    }
    
    // 카메라 개수 카운트
    public void installCamera() {
        high = sortedRoutes[0][1];
        
        for (int i=1; i<size; i++) {
            int curLow = sortedRoutes[i][0];      // 현재 진입
            int curHigh = sortedRoutes[i][1];     // 현재 진출
            
            // 현재 진입이 이전 진출보다 나중이면 카메라를 새롭게 설치
            if (curLow > high) {
                camera++;
                high = curHigh;    // 진출 갱신
            }
            
            // 현재 진출이 이전 진출 이전이어도 진출 갱신
            if (curHigh < high) high = curHigh;
        }
    }
}