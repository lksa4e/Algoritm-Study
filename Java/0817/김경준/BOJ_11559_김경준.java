import java.io.*;
import java.util.*;

/**
 * BOJ 11559 Puyo Puyo : https://www.acmicpc.net/problem/11559
 * 메모리 : 14492KB, 시간 : 128ms
 * 
 * 문제 조건 1. 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작된다.
 *          -> 특정 뿌요를 만났을 때 탐색을 하며 4개 이상 연결된 경우 터트려야 함 -> DFS or BFS 사용 탐색
 *          
 * 문제 조건 2. 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
 *          -> 한번 뿌요가 터졌다고 멈추지 말고  맵 끝까지 계속 탐색하여 한번에 처리
 *  
 * 문제 조건 3. 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
 *          -> 배열 돌리기가 아닌 빈 공간을 메꾸면서 아래로 내리는 로직 필요 
 *  
 * 문제풀이 로직 :
 * 		while(true) 
 * 			맵을 탐색하며 뿌요를 터트림
 *             -> if 뿌요펑?
 *                -> 공백 없이 아래로 내려뜨리기 (중력 적용)
 *                -> combo 증가
 *             -> else
 *                -> break;
 * 
 * Time Complexity
 * - 모든 뿌요펑일때까지 탐색
 *    - 뿌요펑 탐색 (BFS) : O(N^2) -> worst case의 경우 N^2 모든 좌표 탐색
 *       - 뿌요펑인 경우 : O(N^2) -> 모든 열에 대해 아래로 당기는 작업 수행
 * 
 * - 단순 계산상으로는 O(N^4) 이상인 것처럼 보이지만, 한번 뿌요펑을 수행한 이후에는 남은 뿌요의 개수가 줄어든다.
 *   따라서 실제로 수행하게 되는 BFS의 탐색 회수는 줄어들고, 중력 당기기 작업의 수행 횟수도 줄어든다.
 *   + 어차피 시뮬레이션 문제여서 실제로 동작을 수행하지 않으면 문제를 풀 수 없어서 시간복잡도는 큰 의미가 없는듯
 */
public class BOJ_11559_김경준 {
	static int N;
	static char[][] map;
	static boolean[][] visit;
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		map = new char[12][];
		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int answer = 0;
		
		Outer: while (true) {                          // 뿌요 터트릴 수 있을때까지 무한반복
			int num = 0;
			for (int i = 11; i >= 0; i--) {            // 전체 맵 탐색하면서
				for (int j = 5; j >= 0; j--) {
					if (map[i][j] != '.') {            // 공백 아니면 -> 뿌요
						num += boom(i, j, map[i][j]);  // 뿌요 터트리기 진행
					}
				}
			}
			if (num != 0) {   // 만약 뿌요펑이면
				down();       // 아래로 내리기
				answer++;     // 콤보 증가
			}else break;
		}
		System.out.println(answer);
	}

	// 중력 적용하여 아래로 내리는 함수
	// 맨 아래행부터 시작하여 위로 올라가며 공백이 아니면 queue에 넣는다.
	// 맨 아래행부터 시작하여 queue의 size만큼 하나씩 queue에서 꺼내 채워준다.
	static void down() {
		Queue<Character> q = new ArrayDeque<Character>();
		for (int j = 5; j >= 0; j--) {
			for (int k = 11; k >= 0; k--) {
				if (map[k][j] != '.') {
					q.offer(map[k][j]);
					map[k][j] = '.';
				}
			}
			int size = q.size();
			for (int k = 11; k > 11 - size; k--) {
				map[k][j] = q.poll();
			}
		}
	}
	// BFS를 통해 탐색하며 뿌요를 터트리는 함수
	// 탐색하면서 임시 queue에 지나온 뿌요들을 담아준다.
	// 탐색한 뿌요가 4개 이상이면 임시 queue에 담은 뿌요들을 터트려준다.
	static int boom(int x, int y, char c) {
		visit = new boolean[12][6];
		Queue<Pair> q = new ArrayDeque<Pair>();
		Queue<Pair> temp = new ArrayDeque<Pair>();
		q.offer(new Pair(x, y));
		temp.offer(new Pair(x, y));
		visit[x][y] = true;
		while (!q.isEmpty()) {            // 4방탐색 bfs
			Pair cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (!oob(nx, ny) && !visit[nx][ny] && map[nx][ny] == c) {  // 경계 안넘었고, 아직 확인안한 뿌요고, 같은 종류 뿌요면
					visit[nx][ny] = true;
					q.offer(new Pair(nx, ny));
					temp.offer(new Pair(nx, ny));
				}
			}
		}
		if (temp.size() >= 4) {            // 뿌요 4개 이상 연결 -> 펑
			while (!temp.isEmpty()) {
				Pair cur = temp.poll();
				map[cur.x][cur.y] = '.';
			}
			return 1;                      // 뿌요펑했다.
		}
		return 0;
	}
	
	static boolean oob(int x, int y) {
		return x < 0 || x >= 12 || y < 0 || y >= 6;
	}

	static void debug() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

class Pair {
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
