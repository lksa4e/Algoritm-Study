import java.io.*;
import java.util.*;

/**
 * G5 BOJ 17070 괄호 추가하기 :
 * DFS - 메모리 : 15092kb 시간 : 292ms
 * BFS - 메모리 : 378952kb 시간 : 764ms
 * 
 * DFS BFS가 둘 다 가능한 경우 BFS가 빠르다고 하지만
 * Java에서 Pair, Tuple 이상의 자료구조를 사용하는 경우
 * 객체 생성 시간 때문에 BFS가 훨씬 느린 것 같다... 데리고 다녀야 할 인자의 개수가 늘어난다면 DFS를 사용하자
 * 
 */

public class BOJ17070_G5_파이프옮기기_DFS {
	static int N, map[][], answer = 0;
	// 이전 방향 기준의 dx, dy  dir[0][i] - 가로 , dir[1][i] - 세로, dir[2][i] - 대각선
	// 이전 dir이 가로인 경우에 다음 방향에서 세로는 없음 -> 해당 dx를 -1로 처리 -> dx[0][1] == -1
	// 마찬가지로 이전 dir이 세로인 경우에 다음 방향에서 가로는 없음 -> dx[1][0] == -1
	static int dx[][] = {{0,-1,1}, {-1,1,1}, {0,1,1}};
	static int dy[][] = {{1,-1,1}, {-1,0,1}, {1,0,1}};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];
		for(int i = 0; i < N+2; i++) Arrays.fill(map[i], 1);
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(1,2,0);
		System.out.println(answer);
	}
	
	static void dfs(int x, int y, int dir) {
		if(x == N && y == N) {
			answer++;
			return;
		}
		for(int i = 0; i < 3; i++) {
			// 가로 -> 세로  || 세로 -> 가로를 제외시킴
			if(dx[dir][i] == -1) continue;
			
			int nx = x + dx[dir][i];
			int ny = y + dy[dir][i];
			
			// 벽이 있다면 진행 X
			if(map[nx][ny] == 1) continue;
			
			// 만약 대각선인 경우에는 좌, 상까지 모두 비어있는지 체크
			if(i == 2) {
				if(map[nx-1][ny] == 1 || map[nx][ny-1] == 1) continue;
			}
			
			// 여기까지 넘어왔으면 다음 dfs 진행 가능
			dfs(nx, ny, i);
		}
	}
}
