import java.util.*;

/*
 * 프로그래머스 섬 연결하기
 * 
 * 다익스트라 알고리즘.
 * 
 * 미리 인접 그래프를 List 배열로 만들어준다.
 * 임의의 한 점에서 시작해서 그 점과 연결된 모든 edge를 PriorityQueue에 넣어준다. 이 떄 PQ는 코스트로 오름차순 정렬되어 있다.
 * PQ에서 최소 코스트인 경로를 하나 빼고 이미 방문했는지 체크 후, 위 과정을 반복한다.
 * 
 */

class PG_섬연결하기 {
    public int solution(int n, int[][] costs) {
        
        int answer = getShortestPath(n, costs);
        return answer;
    }
    
    public static int getShortestPath(int N, int[][] costs) {
    	// 코스트로 오름차순 정렬된 PQ
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
        // 방문 체크용 배열
        boolean[] visited = new boolean[N];
        
        // 인접 노드 GRAPH 만들기
        List<int[]> graph[] = new ArrayList[N];
        for(int i = 0 ; i< N; i++) {	// 그래프 초기화
            graph[i] = new ArrayList<int[]>();
        }
        
        for(int[] cost : costs) {
            int start = cost[0];
            int end = cost[1];
            int co = cost[2];
            graph[start].add(new int[] {end, co});	// 인접 노드에 index는 출발지로, 배열에 도착지와 코스트를 저장한다.
            graph[end].add(new int[] {start, co});
        }
        
        visited[0] = true;	// 0번 점을 시작지로 잡았다.
        
        for(int i = 0; i < graph[0].size(); i++) {	// 0번 점과 인접한 모든 경로를 pq에 저장
            pq.offer(graph[0].get(i));
        }
        
        int sum = 0;
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int target = curr[0];
            if(visited[target]) continue;	// 방문한 곳이면 패스
            
            sum += curr[1];	// 방문할 것이기에 sum에 누적
            visited[target] = true;	// 방문 체크
            for(int i = 0; i < graph[target].size(); i++) {	// 인접한 경로 모두 넣어주기
                pq.offer(graph[target].get(i));
            }
        }
        
        return sum;
    }
}