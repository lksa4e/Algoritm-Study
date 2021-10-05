import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 14500번 테트로미노
 * 
 * 정사각형 4개를 이어 붙인 폴리오미노 모양의 필터를 이용해
 * 맵에서 4개 값 합의 최댓값을 구하는 문제
 * 
 * dfs로 cnt가 4일때까지 돌리면 T모양을 제외하고 모든 모양이 만들어진다.
 * 다만 중복이 존재해서 속도가 느리다.
 * T 모양은 블록이 2개 만들어져서 작은 작대기 모양일 때 T모양이 될 수 있는 모든 경우의 수를 시행했다.
 * 
 * 시행착오 1:
 * T 모양을 생각 못해서 그냥 dfs로 했다가 테케에서 발견함
 * 
 * 시행착오 2:
 * 중복되는 경우의 수를 없애려고 54번째 줄 시작점 탐색하고 나서 그대로 true로 두고 다음 탐색을 진행했더니 
 *     1 1
 *   1 1
 * 이런 모양을 탐색 못해서 실패, 그래서 그냥 중복되더라도 visited 키고 끄는 코드를 사용했다.
 * 
 * 시간복잡도:
 * 500*500*4*3*3 = 250000 * 36 = 9,000,000
 * 
 * 38,696KB	868ms	
 */

public class BOJ_14500 {
	
	static int N,M,map[][], max;
	static boolean[][] visited;
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	static final int[][] T = {{0,1},{1,2},{2,3},{0,2},{1,3}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {	// 맵의 모든 칸이 시작점이 될 수 있도록 탐색
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(1,i,j,map[i][j]);
				visited[i][j] = false;
			}
		}
		System.out.println(max);
	}
	
	// dfs인데 cnt가 4가 되면 종료
	// 매개 변수로 누적합 같이 들고 가기 ㅜ 모양 빼고 다 체크 가능
	static void dfs(int cnt, int row, int col, int sum) {
		if(cnt == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		if(cnt == 2)
			Tshape(row, col, cnt, sum);
		
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			
			if(isOutOfMap(nr, nc)) continue;
			if(visited[nr][nc]) continue;
			visited[nr][nc] = true;
			dfs(cnt+1,nr,nc,sum+map[nr][nc]);
			visited[nr][nc] = false;
		}
	}
	
	static void Tshape(int row, int col, int cnt, int sum) {
		// T모양 처리: 2개짜리 작은 막대까지 만들어 졌을 때, T모양을 만드는 모든 경우의 수 탐색
		for(int[] dir: T) {
			int nr1 = row + dr[dir[0]];
			int nc1 = col + dc[dir[0]];
			if(isOutOfMap(nr1, nc1)) continue;
			if(visited[nr1][nc1]) continue;
			
			int nr2 = row + dr[dir[1]];
			int nc2 = col + dc[dir[1]];
			if(isOutOfMap(nr2, nc2)) continue;
			if(visited[nr2][nc2]) continue;
			
			// T를 만드는 칸 2개를 한 번에 더해서, 총 4개가 되어 바로 결과 값 계산하도록 --> row랑 col은 그냥 아무거나 해도 상관없음
			dfs(cnt+2,row,col, sum + map[nr1][nc1] + map[nr2][nc2]);	
		}
	}
	
	static boolean isOutOfMap(int row, int col) {
		return row < 0 || col < 0 || row >= N || col >= M;
	}

}
