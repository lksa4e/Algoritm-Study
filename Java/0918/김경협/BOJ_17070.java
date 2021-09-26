
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 17070번 파이프 옮기기1
 * 
 * 파이프의 방향이 있고, 방향에 따라서 갈 수 있는 방법이 달라지는 문제
 * DFS를 돌 때 방향을 저장해서, 다음 DFS에서 갈 수 있는 방향을 한정해야한다.
 * 
 */
public class BOJ_17070 {
	static int N, clearCnt, map[][];
	static final int HORIZONTAL = 0;
	static final int VERTICAL = 1;
	static final int DIAGONAL = 2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+2][N+2];
		
		// 둘레를 1로 채워주고 input 받기
		for (int r = 0; r < N + 2; r++) {
			if(r != 0 && r != N + 1)
				st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N + 2; c++) {
				if(r == 0 || c == 0 || r == N + 1 || c == N + 1)
					map[r][c] = 1;
				else
					map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(1,2,HORIZONTAL);
		System.out.println(clearCnt);
	}
	
	static void dfs(int row, int col, int direction) {
		if(row == N && col == N) {
			// 도착
			clearCnt++;
			return;
		}
		// 가로 0 세로 1 대각선 2
		// 대각선은 어차피 가로, 세로 모두 가야하니 포함시키기
		if(direction != VERTICAL) 
			if(map[row][col+1] != 1)
				dfs(row,col+1,HORIZONTAL);
		if(direction != HORIZONTAL) 
			if(map[row+1][col] != 1) 
				dfs(row+1,col,VERTICAL);
		if(diagonalCheck(row, col)) dfs(row+1,col+1,DIAGONAL);		
	}
	
	// 대각선 갈 수 있는지 체크
	static boolean diagonalCheck(int row,int col) {
		if(map[row][col+1] == 1) return false;
		if(map[row+1][col] == 1) return false;
		if(map[row+1][col+1] == 1) return false;
		return true;
	}
	
}
