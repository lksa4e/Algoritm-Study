import java.io.*;
import java.util.*;

/**
 * [1005] BOJ 14500 테트로미노
 *	완전탐색, dfs
 *
 * sol)
 *  결국 4방 4칸 완전탐색을 통해 방문한 좌표들의 합이 최대가 되는 경우를 구하는 문제
 *  단, 법규모양(ㅗ)의 경우 4칸까지 모두 탐색해서 방문할 수 없고,
 *  3칸탐색 후 2번째 방문한 좌표에서 다시 4방탐색을 해야 방문할 수 있는 모양이므로 이부분 따로 구현해줘야 한다.
 *  
 * 시행착오)
 *  모든 좌표에 대해 dfs 탐색 해야하는데 모든 좌표에 대해 방문 배열을 new boolean[][] 으로 생성해주니 시간초과 폭탄이었다.
 *  객체 new 하는게 얼마나 시간을 많이 잡아먹는지 뼈저리게 느낀 문제...
 *  방문 배열 전역으로 만든 뒤, 방문 끝나고 돌아와서 false 해줌으로써 매개변수 효과를 내 해결함!
 *
 */

public class BOJ_14500_테트로미노 {
	static int N, M, max;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};		// 북, 동, 남, 서
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 초기 지도 입력
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 모든 좌표에 대해 dfs 테트로미노 탐색
		visited = new boolean[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				visited[i][j] = true;				// 방문 체크한 뒤 방문하고
				play(i, j, 0, 0, 1, map[i][j]);
				visited[i][j] = false;				// 돌아와서는 방문 해제 해야함
			}
		}
		
		System.out.println(max);
	}

	// 테트로미노 탐색하기위한 dfs 메서드
	private static void play(int x, int y, int mx, int my, int depth, int sum) {
		// 기저조건 : 4칸 방문 끝나면 최댓값 갱신
		if (depth==4) {
			max = Math.max(max, sum);
			return;
		}
		
		// 법규 모양을 위해 2번째 방문 좌표를 기억
		if (depth==2) {
			mx = x;
			my = y;
		}
		
		// 4칸 방문 직전인 3칸 방문때 법규 모양 탐색
		if (depth==3) {
			checkMiddle(mx, my, sum);
		}
		
		// 4방 탐색
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!isInside(nx, ny) || visited[nx][ny]) continue;		// 방문했으면 pass
			
			visited[nx][ny] = true;
			play(nx, ny, mx, my, depth+1, sum+map[nx][ny]);			// 다음으로 방문하기 전 후에 방문 체크 관리 해줘야함
			visited[nx][ny] = false;
		}
	}

	// 3칸 방문 끝났을 때 2번째 방문 좌표를 바탕으로 법규 모양 확인
	private static void checkMiddle(int mx, int my, int sum) {
		for (int i=0; i<4; i++) {
			int nx = mx + dx[i];
			int ny = my + dy[i];
			
			if (!isInside(nx, ny) || visited[nx][ny]) continue;
			
			max = Math.max(max, sum+map[nx][ny]);		// 법규 모양으로 최댓값 갱신 시도
		}
		
	}

	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}

}
