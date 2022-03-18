import java.util.*;

/**
 * 
 * [0312] PGS 동적 계획법(Dynamic Programming) 등굣길
 * DP, bottom-up
 * 
 * sol)
 * 현재 경로로 도달할 수 있는 2가지 방법(위에서 아래로 내려감 or 왼쪽에서 오른쪽으로 이동함) 중
 * 더 짧은 경로로 도달한다. 출발점에서 도착점까지 이 방식대로 이동하여 최종 최단 경로를 구한다.
 * 물에 잠긴 지역은 경로 거리를 MAX로 설정하여 도달할 수 없는 좌표로 설정하고 최단 경로 구할 때도 건너뛴다.
 * 
 * 주의할 점은 최종적으로 출력할 값은 최단 경로의 개수이지만 최단 경로인지 판별하기 위해 각 좌표별 최단 경로 개수와 최단 거리 2가지를 기억하고 있어야 한다.
 * 점화식은 다음과 같다
 *     dist[x][y] = min(dist[x-1][y], dist[x][y-1])
 *     count[x][y] = 
 *     - count[x-1][y] + count[x][y-1] (dist[x-1][y] = dist[x][y-1]) or
 *     - count[x-1][y] (dist[x-1][y] < dist[x][y-1]) or
 *     - count[x][y-1] (dist[x-1][y] > dist[x][y-1])
 */

public class PGS_동적계획법_등굣길 {
    final int MOD = 1000000007;
    final int MAX = 10001;
    int r, c;
    int[][] road;            // 최단 경로 개수 DP 테이블
    int[][] distance;        // 최단 거리 DP 테이블

    public int solution(int m, int n, int[][] puddles) {
        r = n;
        c = m;
        road = new int[n][m];
        distance = new int[n][m];
        road[0][0] = 1;           // 출발점 초기화
        distance[0][0] = 1;

        avoidPuddles(puddles);    // 잠긴 지역 표시
        move();                   // 최단 경로 개수 구하기

        int answer = road[r-1][c-1] % MOD;
        return answer;
    }

    // 잠긴 지역 표시
    public void avoidPuddles(int[][] puddles) {
        int len = puddles.length;

        for (int i=0; i<len; i++) {
            int y = puddles[i][0]-1;
            int x = puddles[i][1]-1;
            distance[x][y] = -1;        // 잠긴 지역은 거리를 -1로 채움
        }
    }

    // 우하향 하는 방향으로 최단 경로 개수 카운트
    public void move() {
        int upRoad, leftRoad, upDist, leftDist;

        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
            	// 잠긴 지역은 pass
                if (distance[i][j] != 0) continue;

                // 위쪽 지역이 경계 내부이고 잠기지 않았으면 위쪽 지역의 최단 거리와 최단 경로 개수를 기억
                if (isInside(i-1, j) && isDry(i-1, j)) {
                    upRoad = road[i-1][j];
                    upDist = distance[i-1][j];
                } else {
                    upRoad = 0;           // 위쪽 지역이 잠겼으면 위쪽에서 도달한 경우가 최단이 될 수 없도록 설정
                    upDist = MAX;
                }

                // 왼쪽 지역이 경계 내부이고 잠기지 않았으면 왼쪽 지역의 최단 거리와 최단 경로 개수를 기억
                if (isInside(i, j-1) && isDry(i, j-1)) {
                    leftRoad = road[i][j-1];
                    leftDist = distance[i][j-1];
                } else {
                    leftRoad = 0;           // 왼쪽 지역이 잠겼으면 왼쪽에서 도달한 경우가 최단이 될 수 없도록 설정
                    leftDist = MAX;
                }

                // 위쪽, 왼쪽 경로 중 최단 경로로 최단 거리 설정
                distance[i][j] = Math.min(upDist, leftDist) + 1;

                // 위쪽, 왼쪽 경로 중 최단 경로로 최단 경로 개수 설정(위쪽, 왼쪽 거리가 같다면 두 방향에서 온 경로 모두 합)
                if (upDist == leftDist) road[i][j] = (upRoad + leftRoad) % MOD;
                else if (upDist < leftDist) road[i][j] = upRoad % MOD;
                else road[i][j] = leftRoad % MOD;
            }
        }
    }

    // 경계 체크
    public boolean isInside(int x, int y) {
        return x>=0 && x<r && y>=0 && y<c;
    }

    // 잠겼는지 여부 체크
    public boolean isDry(int x, int y) {
        return distance[x][y] != -1;
    }
}