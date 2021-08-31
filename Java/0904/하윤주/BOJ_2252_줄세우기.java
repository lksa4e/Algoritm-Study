import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [0831] 백준 2252 줄 세우기
 * 그래프, 위상정렬
 * 
 * sol)
 * 기본적인 위상 정렬 문제
 * 
 * 노드 타입을 생성하기 귀찮아 배열이랑 맵으로 관리했는데 시간도 오래걸리고 더 헷갈립니다.
 * 이 문제 풀고 바로 노드 타입 만들어서 썼습니다..
 * 
 * 시행착오)
 * 특정 노드로부터 출발해 도착한 노드가 2개 이상일 것을 생각하지 못해서 리스트로 관리하지 않았다가 중간에 깨달음
 * 
 * tc)
 * O(V+E)
 *	
 */

public class BOJ_2252_줄세우기 {
	static int N, M;
	static Map<Integer, ArrayList<Integer>> students;      // key : 출발 노드 인덱스, value : 도착 노드들 인덱스
	static int[] behind;             // 각 노드 별 진입 차수 개수
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		students = new HashMap<>();
		behind = new int[N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			
		    // 진출 노드와 진입 노드를 저장(맵의 key : 출발 노드 인덱스, value : 도착 노드 인덱스 리스트)
			if (students.containsKey(s1)) students.get(s1).add(s2);
			else students.put(s1, new ArrayList<Integer>(Arrays.asList(s2)));
			
			// 각 노드의 진입 차수 개수 카운트
			if (behind[s2] == 0) behind[s2] = 50000;     // 최초 진입 차수가 0인 노드들과 중간에 0이되는 노드들을 구분하기 위해 인덱싱 조정
			behind[s2]++;
		}
		
		sb = new StringBuilder();
		
		searchEnter();      // 위상정렬
		
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}

	// 위상 정렬
	private static void searchEnter() {
		Queue<Integer> q = new ArrayDeque<>();        // 다음으로 방문할 노드를 저장한 큐
		
		for (int i=1; i<=N; i++) {
			if (behind[i]==0) q.offer(i);             // 진입차수가 0인 노드는 정렬
			
			while(!q.isEmpty()) {
				int curStd = q.poll();
				sb.append(curStd).append(" ");
				
				if (!students.containsKey(curStd)) continue;       // 특정 노드로 진출하지 않는 노드는 pass
				for (int b : students.get(curStd)) {               // 위에서 방금 정렬한 진입 차수가 0인 노드와 연결된 노드들 중
					if (--behind[b] == 50000) q.offer(b);          // 간선을 제거해 진입 차수가 0이된 새로운 노드가 있으면 정렬 시도
				}
			}
		}
    }
	
}

