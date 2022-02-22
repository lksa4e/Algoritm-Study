import java.util.*;

/**
 * [0223] PGS 탐욕법(Greedy) 섬 연결하기
 * 그리디, 크루스칼, MST, Union-Find
 * 
 * sol)
 * 정말 오랜만에 알고리즘으로 푼 문제! 최소신장트리(MST) 알고리즘 중 간선의 가중치를 기준으로 하는 크루스칼 알고리즘 풀이
 * 
 * 간선 가중치를 기준으로 간선 정보를 오름차순 정렬한다. 이 풀이에서는 pq를 이용한다.
 * pq.poll()로 가중치가 가장 작은 간선을 뽑아 두 정점이 같은 집합에 포함됐는지(신장 트리에 포함됐는지, 연결 됐는지) 확인한 뒤
 * 서로 다른 집합에 있다면 union()하여 연결한다. 이때 가중치를 최종 가중치 출력값에 합산한다.
 *
 */

public class PGS_탐욕법_섬연결하기 {
    public final int FROM = 0;
    public final int TO = 1;
    public final int COST = 2;
    
    public int nodeCnt, edgeCnt;
    public int[] roots;
    // 가중치를 기준으로 간선 정보를 저장
    public PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr1[COST] - arr2[COST]);
    
    // Union-Find 알고리즘의 집합 생성 메서드
    public void makeSet() {
        roots = new int[nodeCnt];
        // 루트에 자기 자신을 저장해둠
        for (int i=0; i<nodeCnt; i++) roots[i] = i;
    }
    
    // Union-Find 알고리즘의 각 집합 루트를 찾는 메서드
    public int findRoot(int node) {
        if (roots[node] == node) return node;
        return roots[node] = findRoot(roots[node]);
    }
    
    // Union-Find 알고리즘의 두 원소를 같은 집합으로 만드는 메서드
    public boolean union(int node1, int node2) {
        int rootNode1 = findRoot(node1);
        int rootNode2 = findRoot(node2);
        // 부모가 같으면 이미 같은 집합
        if (rootNode1 == rootNode2) return false;
        // 부모가 다르면 node2의 루트를 node1의 루트로 설정
        roots[rootNode2] = rootNode1;
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        nodeCnt = n;
        edgeCnt = costs.length;
        // pq를 이용해 가중치를 기준으로 간선 정렬
        sortDistances(costs);
        // 가중치 합이 최소가 되는 총 가중치 합 구하기
        int answer = getTotalCost();
        return answer;
    }
    
    // pq에 간선의 정보를 저장
    public void sortDistances(int[][] costs) {
        for (int i=0; i<edgeCnt; i++) pq.offer(costs[i]);
    }
    
    // 총 가중치 합 최소가 되는 경우 찾기
    public int getTotalCost() {
        int connected = 1;
        int totalCost = 0;
        // 집합 생성한 뒤
        makeSet();
        // 모든 정점 연결될 때까지 union
        while(connected < nodeCnt) {
            int[] costInfo = pq.poll();
            // 출발 정점과 도착 정점이 아직 연결 안됐으면 연결하고 가중치 합산
            if (union(costInfo[FROM], costInfo[TO])) {
                totalCost += costInfo[COST];
                connected++;
            }
        }
        
        return totalCost;
    }
}