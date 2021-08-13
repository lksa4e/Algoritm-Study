import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [0814] 백준 9019 DSLR
 * 문자열(자리수가 중요한 정수) 핸들링, BFS
 * 
 * sol)
 * 연산자로 순열을 만든 뒤 모든 경우의 수에 대해 연산을 시도
 * 재귀를 통한 순열 구현
 * 
 * 시행착오)
 * 1. 시간 초과 -> visited 배열을 만들어 이전에 방문한 적 있으면 저장된 값 사용
 * 2. 메모리 초과 : 최초에는 큐 2개를 만들어 현재 수와 명령어를 각각 핸들링했는데요, 큐 2개는 무리였나 봅니다..
 * 				 -> visited 배열의 인덱스 값을 현재 수로 표현하고 배열의 값을 명령어로 저장
 * 3. 줄바꿈 안함
 * 4. 명령어 문자열 저장에러 : 최초에는 문자열을 이어붙이는 연산을 줄여보겠다고 DSLR을 각각 1234 숫자로 표현했는데, 
 * 						 이게 int 표현 범위 수를 넘어가면서 엉뚱한 값이 저장되어 에러가 났습니다.
 * 						 -> 그냥 문자 DSLR을 바로 이어붙여 해결
 * 
 * time_complex)
 * 어후 시간초과가 너무 많이 떠서 시간복잡도 계산을 어떻게 해야할지 모르겠습니다..
 * 시간 복잡도가 어떻게 될까요?
 */

public class BOJ9019 {
	static StringBuilder sb;
	static int A, B;
	static Queue<Integer> q;        // BFS를 위한 큐
	static String[] visited;        // 해당 수 방문 여부 배열(값으로 해당 수에 도달하기 위한 명령어를 저장)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			// 수 탐색 시작
			visited = new String[10000];            // visited 배열 null로 초기화 상태
			bfs();
			System.out.println(sb.append("\n"));
		}
	}
	
	// 수 탐색 메서드(BFS)
	public static void bfs() {
		q = new LinkedList<>();
		q.offer(A);
		visited[A] = "";         // 최초 수의 명령어는 ""로 설정(null로 초기화 된 것을 덮어쓰기위해)
		
		while (!q.isEmpty()) {
			int curNum = q.poll();
			
			// 기저 조건 : 목표 수에 도달하면 명령어 출력
			if (curNum == B) {
				sb = new StringBuilder();
				sb.append(visited[curNum]);
			}
			
			// 목표 수 탐색을 위한 명령어(DSLR 모두 탐색해보기)
			int resultD = (curNum<<1) % 10000;                 // D
			int resultS = curNum >= 1 ? curNum-1 : 9999;       // S
			int resultL = curNum % 1000 * 10 + curNum/1000;    // L
			int resultR = curNum % 10 * 1000 + curNum/10;      // R

			// D로 탐색
			if (visited[resultD] == null) {                    // 최초 방문이면
				visited[resultD] = visited[curNum] + "D";      // 명령어에 D 붙여주기
				q.offer(resultD);
			}    // 최초 방문 아니면 원래 저장된 명령어 그대로 쓰기
			
			// S로 탐색
			if (visited[resultS] == null) {
				visited[resultS] = visited[curNum] + "S";
				q.offer(resultS);
			}
			
			// L로 탐색
			if (visited[resultL] == null) {
				visited[resultL] = visited[curNum] + "L";
				q.offer(resultL);
			}
			
			// R로 탐색
			if (visited[resultR] == null) {
				visited[resultR] = visited[curNum] + "R";
				q.offer(resultR);
			}
			
		}
		
	}
}
