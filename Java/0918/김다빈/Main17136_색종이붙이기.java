import java.io.*;
import java.util.*;

/**
 * 백준 17136번 색종이 붙이기
 * 
 * 풀이 : 완전탐색 + 백트래킹
 * 
 * (0,0)부터 (9,9)까지 순차적으로 탐색하면서 크기가 큰 색종이부터 붙여보는 방식!
 * 
 * 시행착오 1.
 * 무조건 크기가 큰 색종이를 붙이는게 최적이 아니기 때문에 다시 초기화해주는 작업이 필요하다는걸 깨닫는게 힘들었다..
 * 
 * 시행착오 2. 
 * 최솟값으로 갱신이 불가능한 경우 가지치기를 해줘야한다!
 * 분명 로직은 맞는 것 같은데 결과가 안 나와서 헤맸던ㅜㅜ
 * 
 * 23444KB	392ms
 */
public class Main17136_색종이붙이기 {
	
	static int result = Integer.MAX_VALUE;
	static int[][] map;
	static int[] remain = {0,5,5,5,5,5};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		map = new int[10][10];
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		attackPaper(0, 0, 0);
		
		if(result == Integer.MAX_VALUE) {	// 모든 1을 덮을 수 없는 경우 -1 출력 
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

	private static void attackPaper(int r, int c, int cnt) {
		if(c == 10) {	// 한 행의 탐색을 끝낸 경우 
			if(r == 9) {		// 모든 구역 탐색을 완료했으면 최솟값 갱신 
				result = Math.min(result, cnt);
				return;
			}
			
			attackPaper(r+1, 0, cnt);	// 다음 행의 첫 열 인덱스로 이동 
			return;
		}
		
		// 이미 갱신할 필요가 없이 크면 가지치기
		if(cnt > result) {
			return;
		}
		
		// 붙일 영역이 없으면 다음으로 넘어가기 
		if(map[r][c] == 0) {
			attackPaper(r, c+1, cnt);
			return;
		}
		
		for(int i = 5; i >= 1; i--) {
			if(remain[i] > 0 && isPossible(r, c, i)) {	// 색종이가 남아 있고 i 크기의 색종이를 붙일 수 있으면 
				drawPaper(r, c, i, 0);	// 색종이를 붙인 영역 표시 
				remain[i]--;
				
				attackPaper(r, c+1, cnt+1);
				
				drawPaper(r, c, i, 1);	// 색종이 다시 제거 
				remain[i]++;
			}
		}
	}
	
	private static boolean isPossible(int r, int c, int size) {
		for (int i = r; i < r+size; i++) {
			for (int j = c; j < c+size; j++) {
				// 영역을 벗어나거나, 1이 아니면 색종이를 붙일 수 없다고 리턴  
				if(i >= 10 || j >= 10 || map[i][j] != 1) return false;
			}
		}
		return true;
	}

	private static void drawPaper(int r, int c, int size, int color) {
		for (int i = r; i < r+size; i++) {
			for (int j = c; j < c+size; j++) {
				map[i][j] = color;
			}
		}
	}
	
}
