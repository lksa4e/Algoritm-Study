import java.io.*;
import java.util.*;

/**
 * G5 BOJ 14503 로봇 청소기
 * 메모리 : 14364kb 시간 : 140ms
 * 
 * 문제에서 주어진 대로 그대로 구현하다 보니 while문 형태로 구현을 했는데
 * 구현을 끝내고 보니까 완전 DFS 문제였음..
 * 
 * 1. 현재 위치를 청소 
 * 2. 현재 위치 기준 4방향 탐색 (순서는 현재 바라보는 방향에서 왼쪽부터)
 *    2.1 4방향 탐색 중 전진이 가능하면? 전진하고 1번으로
 *    2.2 4방향 모두 전진이 불가능하면?
 * 		2.2.1 뒤로 후진
 * 		2.2.2 뒤로 후진도 불가능하면 종료
 * 
 */
public class BOJ14503_로봇청소기{
	static int N,M, map[][], robotX, robotY, robotDir, answer = 0;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	static boolean visit[][];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];

		st = new StringTokenizer(br.readLine());
		robotX = Integer.parseInt(st.nextToken());
		robotY = Integer.parseInt(st.nextToken());
		robotDir = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 청소를 수행할지 말지 결정하는 flag 변수. 문제에서 1번으로 갈지 2번으로 갈지를 판단하는 요소
		boolean flag = true;
		visit[robotX][robotY] = true;
		
		while(true) {
			// 해당 위치를 청소할 수 있으면 청소하고 구역 1칸 증가
			if(flag == true) {
				answer++;
			}
			
			flag = false;
			// 4방향 탐색
			for(int i = 0; i < 4; i++) {
				dirChange();
				int nx = robotX + dx[robotDir];
				int ny = robotY + dy[robotDir];
				
				if(visit[nx][ny]) continue;
				if(map[nx][ny] == 1) continue;
				
				// 여기까지 올 수 있으면 청소가 가능하다는 뜻
				visit[nx][ny] = true;
				robotX = nx;
				robotY = ny;
				flag = true;
				break;
			}
			
			// 만약 4방향 모두 청소할 수 없을 때
			if(!flag) {
				// 후진이 가능한 경우라면?
				if(map[robotX - dx[robotDir]][robotY - dy[robotDir]] != 1) {
					robotX -= dx[robotDir];
					robotY -= dy[robotDir];
				}
				// 4방향 탐색도 불가능하고 후진도 불가능하면 청소 종료
				else break;
			}
		}
		System.out.println(answer);
	}
	// 방향 돌리기 함수
	static void dirChange() {
		if(robotDir == 0) robotDir = 3;
		else robotDir--;
	}
}
