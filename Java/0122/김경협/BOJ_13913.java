import java.util.*;
import java.io.*;

/*
 * BOJ 13913�� ���ٲ��� 4
 * 
 * �ִ� �Ÿ� ã�� ����, ��� ����
 * 
 * BFS�� ����� �ִܰŸ��� ã��, ���� ��Ʈ�� �����ϴ� �迭�� ����
 * ��θ� ����ϰ� �ߴ�.
 * 
 * ��� ������ ��, �������� -1 ���� �ɷ� �����س��� ���߿� ��� ������ ����� ��
 * ������ ���� �ʴ´�.
 * 
 * 27956KB	292ms
 */

public class BOJ_13913 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(N, K));

	}
	
	static String bfs(int N, int K) {
		boolean visited[] = new boolean[100001];
		int route[] = new int[100001];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);	// ������, ���� ��δ� �����Ƿ� -1�� �������ش�.
		route[N] = -1;
		visited[N] = true;
		
		while(!q.isEmpty()) {	// ����� bfs
			int curr = q.poll();
			
			if(curr == K) {
				break;
			}
			
			for(int i = 0; i < 3; i++) {
				int next = curr;
				
				if(i == 0) next++;
				else if(i == 1) next--;
				else next *= 2;
				
				if(next < 0 || next > 100000) continue;
				if(visited[next]) continue;
				
				q.offer(next);
				route[next] = curr;	// ���� ��θ� ����
				visited[next] = true;
			}
		}
		
		List<Integer> list = new ArrayList<>();
		int findRoute = K, cnt = 0;
		while(findRoute != -1) {	// �������� -1�� ���� ������ ��� ��θ� ������ ����
			cnt++;
			list.add(findRoute);
			findRoute = route[findRoute];
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt - 1).append("\n");
		
		for(int i = 0, size = list.size(); i < size; i++) {
			sb.append(list.get(size - i - 1)).append(" ");
		}
		
		return sb.toString();
	}

}
