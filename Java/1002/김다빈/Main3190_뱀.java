import java.io.*;
import java.util.*;

/**
 * 백준 3190번 뱀
 * 
 * 풀이 : 구현 + Deque
 * 
 * 뱀의 위치를 머리와 꼬리 부분, 앞 뒤로 봐야하기 때문에 Deque로 관리
 * 
 * while문을 돌리면서 1초동안 뱀 이동
 * -> 벽이나 자기자신의 몸에 부딪히면 종료 
 * 
 * - 시행착오 : X초가 끝난 후에 방향을 전환해야한다는 조건을 잘못 읽어서 답이 안 나와서 헤맸던..
 * 
 * 14636KB	148ms
 */
public class Main3190_뱀 {

	static int N;
	static int[][] map;
	static Map<Integer, Integer> rotate = new HashMap<Integer, Integer>();	// 방향 전환 정보 (시간, 방향)
	
	static int[] dr = {0,1,0,-1};	// 우하좌상 
	static int[] dc = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int appleNum = Integer.parseInt(br.readLine());
		for (int i = 0; i < appleNum; i++) {
			st = new StringTokenizer(br.readLine());
			// 사과가 있는 곳을 1로 표시 
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		
		int moveCnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < moveCnt; i++) {
			st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken());
			int dir = 1;	// 오른쪽으로 회전 (1)
			if(st.nextToken().equals("L")) dir = -1;	// 왼쪽으로 회전 (-1)
			
			rotate.put(time, dir);	// time초가 끝난 후에 dir으로 회전
		}
		
		moveSnake();
	}

	// 종료될 때까지 뱀을 이동시키는 함수 
	private static void moveSnake() {
		Deque<int[]> snake = new ArrayDeque<int[]>();	// 뱀의 위치 좌표를 나타내는 Deque
		int time = 1, dir = 0, r, c;
		
		// 뱀 초기 위치 (0, 0) 설정 
		map[0][0] = -1;
		snake.addLast(new int[] {0, 0});
		
		while(true) {
			// 현재 뱀의 머리 위치 
			int[] head = snake.peekFirst();
			r = head[0];
			c = head[1];
			
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			// 경계값 체크 + 자기자신의 몸에 부딪힌 경우 break  
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == -1) break;			

			if(map[nr][nc] != 1) {	// 사과를 먹지 않았다면 꼬리칸 비워주기 
				int[] tail = snake.pollLast();
				map[tail[0]][tail[1]] = 0;
			}
			
			map[nr][nc] = -1;	// 뱀 머리 이동 
			snake.offerFirst(new int[] {nr, nc});
			
			if(rotate.containsKey(time)) {	// time초가 끝난 뒤 뱀 방향 변환 (-1 : 왼쪽, 1 : 오른쪽)
				dir += rotate.get(time);
				
				// 범위를 넘어간 경우 방향 조정 
				if(dir < 0) dir = 3;
				else if(dir > 3) dir = 0;
			}
			
			time++;
		}
		
		System.out.println(time);
	}

}
