import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 15685번 드래곤 커브
 * 
 * 드래곤 커브를 그리는 문제,
 * 아이디어를 생각해 내는게 까다로웠던 문제이다.
 * 
 * 드래곤 커브 만들기
 * 1. 시작방향으로 긋기, 시작방향 list에 저장. 
 * 2. 그어서 온 부분이 시작지점이 됨
 * 3. List의 끝을 시작점으로 출발해서 90도 돌리고(dir+1) 그 방향으로 선 긋기, 선 그은 방향에서 다시 출발
 * 4. 90도 돌린 방향을 List 뒤에 붙이기
 * 5. List 처음까지 계속 반복
 * 6. 2로 가기
 * 
 * 0세대 = ↑
 * 1세대 = 0세대+0세대*90도 = ↑ + →
 * 2세대 = 1세대+1세대*90도 = ↑ → + → ↓
 * 3세대 = 2세대+2세대*90도 ...이기 때문에 계속 List에다가 90도 돌린걸 뒤에 붙인다.
 * 
 */

public class BOJ_15685 {
	static final int MAP_SIZE = 101;
	static final int[] dr = {0,-1,0,1}; // 우,상,좌,하
	static final int[] dc = {1,0,-1,0};
	static int N, map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수
		map = new int[MAP_SIZE][MAP_SIZE];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// x축은 컬럼방향, y축은 row 방향
			int col = Integer.parseInt(st.nextToken());	// x
			int row = Integer.parseInt(st.nextToken());	// y
			int startDir = Integer.parseInt(st.nextToken()); // 시작 방향
			int dragonGen = Integer.parseInt(st.nextToken());	// 드래곤 커브 세대
			drawDragonCurve(row,col,startDir,dragonGen);
		}
		System.out.println(calcQuad());
	}
		
	static void drawDragonCurve(int row, int col, int startDir, int dragonGen) {
		List<Integer> dirs = new ArrayList<Integer>();
		map[row][col] = 1; // 시작점에서 시작방향으로 그리기: 0세대
		row += dr[startDir];
		col += dc[startDir];
		map[row][col] = 1;
		
		dirs.add(startDir); // 시작 방향 추가
		
		// 1세대 ~ n세대까지
		for (int i = 1; i <= dragonGen; i++) {
			int idx = dirs.size() - 1;
			// 역순으로 하나씩 꺼내면서 90도 돌리고 선 긋기
			for (int j = idx; j >= 0; j--) {
				int dir = (dirs.get(j) + 1) % 4;
				row += dr[dir];
				col += dc[dir];
				map[row][col] = 1;
				dirs.add(dir);	// 90도 돌린 방향 list 끝에 넣어주기
			}
		}
	}
	
	// 네 꼭짓점이 모두 드래곤 커브인 1x1 정사각형 찾기
	static int calcQuad() {
		int cnt = 0;
		for (int i = 0; i < MAP_SIZE - 1; i++) {
			for (int j = 0; j < MAP_SIZE - 1; j++) {
				if(isQuad(i,j)) cnt++;
			}
		}
		return cnt;
	}
	
	static boolean isQuad(int row, int col) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if(map[i+row][j+col] != 1) return false;
			}
		}
		return true;
	}
}
