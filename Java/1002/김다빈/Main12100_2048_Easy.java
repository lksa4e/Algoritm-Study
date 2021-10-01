import java.io.*;
import java.util.*;

/**
 * 백준 12100번 2048(Easy)
 * 
 * 풀이 : 구현
 * 
 * 문제 제목만 Easy고 하나도 안 Easy했던..
 * 
 * 블록을 하나씩 보면서 합칠 수 있으면 합치고
 * 합칠 수 없으면 저장했다가 다시 다음 블록이랑 비교하는 부분에서 많이 헤맸다..
 * 
 * 20216KB	172ms
 */
public class Main12100_2048_Easy {
	
	static int N, result = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(map, 1);
		
		System.out.println(result);
	}

	private static void dfs(int[][] map, int cnt) {
		if(cnt == 6) {	// 최대 5번 이동 후 최댓값 갱신 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					result = Math.max(result, map[i][j]);
				}
			}
			return;
		}
		
		// 위로 이동 -> 행 기준 위쪽에서 아래쪽으로 탐색하면서 위쪽부터 채우기 
		int[][] temp = new int[N][N];
		for (int c = 0; c < N; c++) {
			int idx = 0;	// 값이 입력될 위치 
			int save = 0;	// 아직 합쳐지지 않은 블록의 숫자 
			for (int r = 0; r < N; r++) {
				if(map[r][c] == 0) continue;
				
				if(save == map[r][c]) {	// 합칠 수 있으면 갱신 
					temp[idx - 1][c] = save * 2;
					save = 0;
				} else {	// 합칠 수 없으면 우선 블록 입력 + save에 저장 
					save = map[r][c];
					temp[idx][c] = save;
					idx++;
				}
			}
		}
		dfs(temp, cnt+1);	// 횟수 증가해서 재귀 호출 
		
		// 아래로 이동 -> 행 기준 아래쪽에서 위쪽으로 탐색하면서 아래쪽부터 채우기 
		temp = new int[N][N];
		for (int c = 0; c < N; c++) {
			int idx = N-1; 
			int save = 0;
			for (int r = N-1; r >= 0; r--) {
				if(map[r][c] == 0) continue;
				
				if(save == map[r][c]) {
					temp[idx + 1][c] = save * 2;
					save = 0;
				} else {
					save = map[r][c];
					temp[idx][c] = save;
					idx--;
				}
			}
		}
		dfs(temp, cnt+1);
		
		// 왼쪽으로 이동 -> 열 기준 왼쪽부터 오른쪽으로 탐색하면서 왼쪽부터 채우기 
		temp = new int[N][N];
		for (int r = 0; r < N; r++) {
			int idx = 0;
			int save = 0;
			for (int c = 0; c < N; c++) {
				if(map[r][c] == 0) continue;
				
				if(save == map[r][c]) {
					temp[r][idx - 1] = save * 2;
					save = 0;
				} else {
					save = map[r][c];
					temp[r][idx] = save;
					idx++;
				}
			}
		}
		dfs(temp, cnt+1);
		
		// 오른쪽으로 이동 -> 열 기준 오른쪽부터 왼쪽으로 탐색하면서 오른쪽부터 채우기 
		temp = new int[N][N];
		for (int r = 0; r < N; r++) {
			int idx = N-1; 
			int save = 0;
			for (int c = N-1; c >= 0; c--) {
				if(map[r][c] == 0) continue;
				
				if(save == map[r][c]) {
					temp[r][idx + 1] = save * 2;
					save = 0;
				} else {
					save = map[r][c];
					temp[r][idx] = save;
					idx--;
				}
			}
		}
		dfs(temp, cnt+1);
	}

}
