import java.io.*;
import java.util.*;

/**
 * [1012] BOJ 15685 드래곤 커브
 * 2차원 배열, 좌표 관리, 좌표 이동
 * 
 * sol)
 * 먼저 드래곤 커브를 만들어 2차원 배열에 표현한 뒤, 박스 개수를 센다.
 * 드래곤 커브를 구현하기 이전 세대의 드래곤 커브 방향을 스택에 삽입한다.
 * 이후 세대는 스택에서 방향을 pop()하여(거꾸로 접근) 시계방향 돌리고 이전 세대 드래곤 커브 방향 뒤에 덧붙인다.
 *
 */

public class BOJ_15685_드래곤커브 {
	static int N, answer;
	static boolean[][] map;
	static int[] dx = {0, -1, 0, 1};     // 우, 상, 좌, 하
	static int[] dy = {1, 0, -1, 0};
	static ArrayList<Integer> dir = new ArrayList<Integer>();     // 최종 세대의 드래곤 커브 방향
	static Stack<Integer> stack = new Stack<Integer>();           // 각 세대의 드래곤 커브 방향을 구하기 위한 스택
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 입력과 동시에 드래곤 커브 표현
		map = new boolean[101][101];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			makeDragonCurve(Integer.parseInt(st.nextToken()),     // x
					        Integer.parseInt(st.nextToken()),     // y
					        Integer.parseInt(st.nextToken()),     // d(시작방향)
					        Integer.parseInt(st.nextToken()));    // g(세대)
		}
		
		// 박스 개수 카운트
		countBox();
		System.out.println(answer);
	}
	
	// 드래곤 커브 표현
	private static void makeDragonCurve(int y, int x, int d, int g) {
		dir.clear();
		dir.add(d);                // 시작 방향을 삽입하고
		findDir(g);                // 시계 방향으로 돌려가며 드래곤 커브 방향을 구함
		drawDragonCurve(x, y);     // 드래곤 커브 방향이 리스트에 저장되었으므로 2차원 배열에 표현 
	}

	// 드래곤 커브 방향 구하기
	private static void findDir(int g) {
		for (int i=0; i<g; i++) {
			// 먼저 이전 세대 방향을 스택에 복사하고
			copyToStack();
			// 이전 세대 방향을 맨 뒤에서부터 하나씩 빼서 시계방향으로 돌리고 최종 방향에 더함
			while (!stack.isEmpty()) {
				int cur = stack.pop();
				dir.add((cur+1)%4);
			}
		}
	}
	
	// 2차원 배열에 드래곤 커브 그리기
	private static void drawDragonCurve(int x, int y) {
		map[x][y] = true;
		
		for (int d : dir) {
			x += dx[d];
			y += dy[d];
			map[x][y] = true;
		}
	}
	
	// 방향을 스택에 저장하는 메서드
	private static void copyToStack() {
		for (int d : dir) stack.push(d);
	}
	
	// 박스 개수 카운트
	private static void countBox() {
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				// 현재 좌표를 기준으로 우, 하, 우하 방향을 보면 4꼭지점을 모두 확인한 사각형이 완성됨
				if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) answer++;
			}
		}
	}

}
