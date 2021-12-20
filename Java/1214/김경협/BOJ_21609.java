import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 21609번 상어 중학교
 * 
 * 노가다 시뮬레이션 문제.
 * 
 * 그룹 블록을 찾을 때에는 dfs를 사용해서 group을 찾았다. 
 * 내부 class를 통해 그룹 블록을 저장하면서 그 그룹 블록에
 * 무지개 블록이 몇 개 있는지 같이 저장했다.
 * 
 * 24980KB	192ms
 */

public class BOJ_21609 {
	static class GroupBlock {
		List<int[]> groupBlock = new ArrayList<int[]>();;
		int numOfRainbowBlock;
	}

	static int map[][], totalScore, N, M;
	static int dr[] = {-1,1,0,0}, dc[] = {0,0,-1,1};
	static boolean[][] checkedGroup, visited;
	static GroupBlock bigBlock;
	static final int EMPTY = -2, BLACK = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		checkedGroup = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		play();
		System.out.println(totalScore);
	}
	
	static void play() {
		while(true) {
			reset();		// 초기화 메소드
			if(!findGroup()) return;	// 더 이상 그룹이 없다면 종료
			deleteGroup();				
			setGravity();
			rotate();
			setGravity();
		}
	}
	
	static void deleteGroup() {
		List<int[]> group = bigBlock.groupBlock;
		
		int size = group.size();
		totalScore += size * size;	// 현재 groupBlock size^2만큼 점수 획득
		
		for (int i = 0; i < size; i++) {	// map에서 groupBlock 없애고 EMPTY 채워주기
			int row = group.get(i)[0];
			int col = group.get(i)[1];
			map[row][col] = EMPTY;
		}
	}
	
	static void setGravity() {
		for (int c = 0; c < N; c++) {	// 맵 왼쪽 아래에서 행 1순위, 열 2순위로 
			for (int r = N-1; r >= 0; r--) {
				if(map[r][c] != EMPTY && map[r][c] != BLACK) {	// 현재 칸이 블록이면(검정제외) 아래를 보고, 가장 아래 칸에 떨어뜨리기
					
					int targetRow = r;
					do {
						if(++targetRow >= N) break;	// 맵 밖으로 나가는 경우,
					} while(map[targetRow][c] == EMPTY);
					
					if(targetRow - 1 == r) continue;	// 아래 칸이 비어 있지 않은 경우
					map[targetRow-1][c] = map[r][c];
					map[r][c] = EMPTY;
				}
			}
		}
	}
	
	static void rotate() {
		int[][] newMap = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				newMap[N-1-c][r] = map[r][c];	// 반시계 방향 90도 회전
			}
		}
		
		map = newMap;
	}
	
	static boolean findGroup() {
		for (int i = 0; i < N; i++) {	// checkedGroup은 그룹 블록의 기준 좌표를 찾을 때, 이미 탐색된 그룹 블록을 체크하기 위해 사용
			for (int j = 0; j < N; j++) {	// visited는 dfs할 때 중복체크하기 위해 사용
				if(checkedGroup[i][j]) continue;
				int curr = map[i][j];
				if(curr == 0) continue; 
				if(curr == BLACK) continue; 
				if(curr == EMPTY) continue; 
				
				GroupBlock tmpBlock = new GroupBlock();	// dfs로 그룹이 되는 블록을 묶으면서 탐색
				visited = new boolean[N][N];
				tmpBlock.groupBlock.add(new int[] {i,j});
				checkedGroup[i][j] = true;	
				visited[i][j] = true;
				dfs(tmpBlock, i, j, curr);
				
				if(tmpBlock.groupBlock.size() > bigBlock.groupBlock.size()) {
					bigBlock = tmpBlock;	// 사이즈가 더 큰게 big
				} else if (tmpBlock.groupBlock.size() == bigBlock.groupBlock.size()) {
					if(tmpBlock.numOfRainbowBlock >= bigBlock.numOfRainbowBlock) {
						bigBlock = tmpBlock;	// 사이즈가 같으면, 무지개 블록이 더 많을수록 big
					} 
				}
			}
		}
		
		if(bigBlock.groupBlock.size() >= 2)
			return true;
		else	// 그룹 사이즈가 2 미만이라면 시뮬레이션 종료
			return false;
	}
	
	static void dfs(GroupBlock gb, int row, int col, int color) {
		for (int i = 0; i < 4; i++) {
			int nextRow = row + dr[i];
			int nextCol = col + dc[i];
			if(isOutOfMap(nextRow, nextCol)) continue;	// 맵 밖 제외
			if(visited[nextRow][nextCol]) continue;
			int next = map[nextRow][nextCol];
			if(next == BLACK) continue; // 검은 블록 제외
			if(next == EMPTY) continue; // 비어있는 칸 제외
			if(next != color && next != 0) continue; // 다른 색깔 제외(무지개 블록 제외)
			
			if(next == 0) 
				gb.numOfRainbowBlock++;	// 무지개 블록,
			else
				checkedGroup[nextRow][nextCol] = true;	// 같은 블록이면 체크해주기.
			
			gb.groupBlock.add(new int[] {nextRow, nextCol});
			visited[nextRow][nextCol] = true;	// dfs용 visited 체크,
			dfs(gb, nextRow, nextCol, color);
		}
	}
	
	static boolean isOutOfMap(int row, int col) {	// 맵 밖이면 true
		return row < 0 || col < 0 || row >= N || col >= N;
	}
	
	static void reset() {	// 초기화
		bigBlock = new GroupBlock();
		for (int i = 0; i < N; i++) {
			Arrays.fill(checkedGroup[i], false);
		}
	}
}
