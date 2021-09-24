import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 17136번 색종이 붙이기
 * 
 * 처음에 그리디로 큰 색종이 순서대로 붙여봤는데
 * 반례가 있어서 실패,
 * 
 * 그 뒤에 완탐으로 탐색하면서 색종이 붙였다 뗐다하는 방법으로 풀었음.
 * 
 * 24936KB	324ms
 */

public class BOJ_17136 {
	static final int MAP_SIZE = 10;
	static int[] colorPaper = {5,5,5,5,5};
	static char[][] map;
	static int minCnt = Integer.MAX_VALUE;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[MAP_SIZE][MAP_SIZE];
		
		for (int i = 0; i < MAP_SIZE; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < MAP_SIZE; j++)
				map[i][j] = line[j * 2];
		}
		
		dfs(0,0,0);
		
		System.out.println(minCnt == Integer.MAX_VALUE ? -1 : minCnt);
		
	}
	
	static void dfs(int row, int col, int usedCnt) {
		// 기저조건, 왼쪽 아래 맨 끝에 도착했을 경우
		// 9,9가 아니라 9,10에 도착했을 때가 모두 탐색된 경우이다.
		if(row == MAP_SIZE - 1 && col == MAP_SIZE) {
			minCnt = Math.min(minCnt, usedCnt);
			return;
		}
		
		// 현재 최솟값보다 사용한 색종이가 많아질 경우 return;
		if(usedCnt >= minCnt) return;
		
		// col이 맨 끝점을 넘어설 경우 줄 바꿔주기
		if(col == MAP_SIZE) {
			dfs(row + 1, 0, usedCnt);
			return;
		}
		
		// 1이면 색종이를 가장 큰 것부터 순서대로 붙여줌
		if(map[row][col] == '1') {
			for (int pSize = 5; pSize >= 1; pSize--) {
				// 붙일 색종이가 남아 있어야하고, 사이즈가 맞아야 한다.
				if(colorPaper[pSize-1] != 0
						&& isFit(map, row, col, pSize)) {
					fill(map, row, col, pSize, '0');
					colorPaper[pSize-1]--;
					
					// 색종이 붙이고 옆으로 움직여서 다시 탐색
					dfs(row, col + 1, usedCnt + 1);
					
					// 붙이 색종이 다시 떼기
					fill(map, row, col, pSize, '1');
					colorPaper[pSize-1]++;
				}
			}
		} else {	// 0이면 그냥 다음칸 탐색
			dfs(row, col + 1, usedCnt);
		}
		
	}
	
	static void fill(char[][] map, int row, int col, int size, char fillWith) {
		for (int i = row; i < row + size; i++)
			for (int j = col; j < col + size; j++)
				map[i][j] = fillWith;
	}
	
	static boolean isFit(char[][] map, int row, int col, int size) {
		for (int i = row; i < row + size; i++)
			for (int j = col; j < col + size; j++)
				if(i < 0 || i >= MAP_SIZE ||
						j < 0 || j >= MAP_SIZE ||
						map[i][j] != '1')
					return false;
		return true;
	}

}
