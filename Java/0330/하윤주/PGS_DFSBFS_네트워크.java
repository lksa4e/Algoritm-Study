import java.util.*;

/**
 * 
 * [0325] PGS 깊이/너비 우선 탐색(DFS/BFS) 네트워크
 * DFS, 완탐
 * 
 * sol)
 * 각 컴퓨터를 순서대로 확인하며 아직 아무 네트워크에 연결되어있지 않다면 연결여부를 따지러 dfs 탐색을 시도한다.
 * dfs 시작 시 현재 컴퓨터가 현재 네트워크에 연결되어있음을 표시한다.
 * 이후 해당 컴퓨터와 모든 컴퓨터를 비교하면서 연결되어있고 아직 다른 컴퓨터에 의해 연결 여부가 업데이터되어있지 않다면 업데이트하러 재귀를 타고 들어간다.
 * 모든 컴퓨터에 대해 위 과정을 반복한다.
 *
 */

public class PGS_DFSBFS_네트워크 {
    int totalNetwork;     // 총 네트워크 개수
    int[] networks;       // 각 컴퓨터별 어떤 네트워크에 연결되었는지 숫자로 구분하여 기억

    public int solution(int n, int[][] computers) {
        networks = new int[n];
        // 컴퓨터 중 아직 연결 여부를 확인하지 않은 컴퓨터에 대해 재귀적으로 연결 여부 조사
        for (int i=0; i<n; i++) {
            if (networks[i]==0) {
                totalNetwork++;           // 새로운 네트워크에 연결
                dfs(n, i, computers);
            }
        }
        int answer = totalNetwork;
        return answer;
    }

    // 재귀적으로 연결된 네트워크 끝까지 타고들어가 연결되었다고 표시
    public void dfs(int n, int cur, int[][] computers) {
    	// 현재 네트워크에 연결되었음을 표시
        networks[cur] = totalNetwork;

        // 현재 컴퓨터를 모든 컴퓨터에 대해 연결 여부 조사
        for (int i=0; i<n; i++) {
            int next = computers[cur][i];

            if (i == cur || next == 0) continue;    // 비교 대상이 자기 자신이거나 연결되지 않았다면 pass
            
            if (networks[i] == 0) {                 // 연결되어 있고 해당 컴퓨터를 네트워크 연결 여부 체크 안해줬다면 체크해주러 재귀 탐
                dfs(n, i, computers);
            }
        }
    }
}
