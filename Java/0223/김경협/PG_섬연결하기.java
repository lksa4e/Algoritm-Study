import java.util.*;

/*
 * ���α׷��ӽ� �� �����ϱ�
 * 
 * ���ͽ�Ʈ�� �˰���.
 * 
 * �̸� ���� �׷����� List �迭�� ������ش�.
 * ������ �� ������ �����ؼ� �� ���� ����� ��� edge�� PriorityQueue�� �־��ش�. �� �� PQ�� �ڽ�Ʈ�� �������� ���ĵǾ� �ִ�.
 * PQ���� �ּ� �ڽ�Ʈ�� ��θ� �ϳ� ���� �̹� �湮�ߴ��� üũ ��, �� ������ �ݺ��Ѵ�.
 * 
 */

class PG_�������ϱ� {
    public int solution(int n, int[][] costs) {
        
        int answer = getShortestPath(n, costs);
        return answer;
    }
    
    public static int getShortestPath(int N, int[][] costs) {
    	// �ڽ�Ʈ�� �������� ���ĵ� PQ
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
        // �湮 üũ�� �迭
        boolean[] visited = new boolean[N];
        
        // ���� ��� GRAPH �����
        List<int[]> graph[] = new ArrayList[N];
        for(int i = 0 ; i< N; i++) {	// �׷��� �ʱ�ȭ
            graph[i] = new ArrayList<int[]>();
        }
        
        for(int[] cost : costs) {
            int start = cost[0];
            int end = cost[1];
            int co = cost[2];
            graph[start].add(new int[] {end, co});	// ���� ��忡 index�� �������, �迭�� �������� �ڽ�Ʈ�� �����Ѵ�.
            graph[end].add(new int[] {start, co});
        }
        
        visited[0] = true;	// 0�� ���� �������� ��Ҵ�.
        
        for(int i = 0; i < graph[0].size(); i++) {	// 0�� ���� ������ ��� ��θ� pq�� ����
            pq.offer(graph[0].get(i));
        }
        
        int sum = 0;
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int target = curr[0];
            if(visited[target]) continue;	// �湮�� ���̸� �н�
            
            sum += curr[1];	// �湮�� ���̱⿡ sum�� ����
            visited[target] = true;	// �湮 üũ
            for(int i = 0; i < graph[target].size(); i++) {	// ������ ��� ��� �־��ֱ�
                pq.offer(graph[target].get(i));
            }
        }
        
        return sum;
    }
}