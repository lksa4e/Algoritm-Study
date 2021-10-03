import java.io.*;
import java.util.*;

/**
 * [1002] BOJ 3190 뱀
 * 시뮬레이션, 구현, 덱
 * 
 * sol)
 * 뱀의 머리와 꼬리 좌표를 덱으로 관리하는 것이 핵심!
 * 
 * 1. 덱에 뱀의 머리부터 꼬리까지 좌표를 모두 저장해두는데, 덱의 첫번째 인덱스를 꼬리, 마지막 인덱스를 머리로 설정함.
 * 2. 덱에서 꺼낸 머리가 게임 오버 조건에 충족하면 현재 이동 시간을 정답으로 출력.
 * 3. 덱에서 꺼낸 머리가 문제 없으면 머리 좌표에 사과가 있는지 확인하여 꼬리를 유지하거나 잘라냄. 머리 좌표는 무조건 방문해야함.
 * 4. 다음 이동을 위한 새로운 머리 좌표 구함. 방향 회전이 필요하면 방향을 회전하여 덱에 삽입.
 * 5. dfs 타고가서 다음 이동 츄라이
 *  
 * 시행착오)
 *  아이디어나 구현은 어렵지 않았는데 과정 간 순서를 세밀하게 맞춰주는게 까다로웠다. 
 *  특히 현재 시간의 이동을 수행한 뒤, 방향 회전을 체크해야 하는 부분에서 시간을 까먹음.
 *
 */

public class BOJ_3190_뱀 {
	static int N, time=0;				// 이동 시간(재귀 타고 들어간 횟수)
	static int[][] map;
	static String[] changeDir;
	static int[] dx = {-1, 0, 1, 0};	// 상 우 하 좌
	static int[] dy = {0, 1, 0, -1};
	static ArrayDeque<int[]> dq = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		// 초기 지도 입력
		map = new int[N+2][N+2];		// 테투리 닿으면 끝나게 하려고 패딩 추가
		for (int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = -1;		// 사과는 -1로 체크
		}
		
		// 방향 변환 정보 입력
		int L = Integer.parseInt(br.readLine());
		changeDir = new String[10002];
		
		for (int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			String dir = st.nextToken().toString();
			changeDir[sec] = dir;
		}
		
		dq.offer(new int[] {1, 1});		// 초기 꼬리
		dq.offer(new int[] {1, 2});		// 초기 머리
		map[1][1]=1;					// 꼬리 방문 체크
		dfs(1);							// dfs 탐색으로 게임 진행
		
		System.out.println(time);
	}
	
	// dfs 탐색으로 게임 진행
	private static void dfs(int dir) {
		time++;										// 이동 시간(횟수)
		int[] head = dq.peekLast();					// 머리는 무조건 덱의 마지막 인덱스에 저장
		
		// 게임오버 체크
		if (isGameOver(head[0], head[1])) return;
		
		// 사과 존재 여부에 따라 꼬리 조정
		if (map[head[0]][head[1]]==0) {				// 사과 없으면
			int[] tail = dq.pollFirst();			// 꼬리 제거(꼬리는 무조건 덱의 첫번째 인덱스에 저장)
			map[tail[0]][tail[1]] = 0;				// 지도에서도 꼬리 제거
		}
		
		// 헤드 방문 처리
		map[head[0]][head[1]] = 1;
		
		// 방향 회전 여부에 따라 방향 설정
		String change = changeDir[time];
		if (change!=null) dir = changeDirection(dir, change);
		
		// 방향을 고려한 다음의 이동 좌표
		int[] newHead = new int[2];
		newHead[0] = head[0] + dx[dir];
		newHead[1] = head[1] + dy[dir];
		
		// 다음 이동 좌표를 다시 큐에 삽입하여 새로운 머리 설정
		dq.offerLast(newHead);
		// 위 과정을 게임 끝날때까지 반복
		dfs(dir);
	}

	// 방향 회전 함수(방향 델타값 반환)
	private static int changeDirection(int dir, String change) {
		if (change.equals("L")) {		// 왼쪽으로 회전이면
			if (--dir<0) dir = 3;		// -1
		} else {
			if (++dir>3) dir = 0;		// 오른쪽 회전이면 +1
		}
		
		return dir;
	}

	// 게임 오버 확인 함수
	private static boolean isGameOver(int x, int y) {
		if (map[x][y] == 1) return true;			// 뱀을 만나면 종료
		return (x==0 || x>N || y==0 || y>N);		// 혹은 경계 벗어나면 종료
	}

}
