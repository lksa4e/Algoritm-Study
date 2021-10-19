import java.io.*;
import java.util.*;

/**
 * [1012] BOJ 15684 사다리 조작
 * 완전탐색, 조합, 재귀, dfs, 백트래킹
 * 
 * sol)
 * 재귀적으로 좌표 조합을 1개, 2개, 3개씩 각각 뽑은 뒤 모든 경우의 수에 대해 직방으로 연결되는지 확인한다.
 * 
 * 시행착오)
 * 처음에는 최대 사다리 3개까지 설치해야하므로 재귀적으로 좌표 순열을 구현하여 1~3개 설치할 때마다 직방으로 갈 수 있는지 확인했는데
 * 순열이다보니까 중복이 발생해서 시간이 터졌다..
 * 
 * 이후에 조합으로 구현하기 위해 현재 뽑은 좌표 이후에 대해서만 좌표 조합을 뽑았는데 뭔가 어디서 잘못됐는지 모든 경우의 수를 보지 못해서 틀렸고,
 * 또 재귀에서 돌아왔을 때 depth 원상복구가 잘 안되어서 완전 꼬였다. 
 * 순열, 조합 문제를 재귀적으로 풀면 디버깅이 너무 어렵다.. 완탐 문제는 틀리면 억울하니까 이런 문제를 많이 연습해야할 것 같은데 맞왜틀을 너무 많이해서 괴로웠다..ㅎㅎ
 * 2차원 배열 문제를 풀다보면 좌표 관리를 더 연습해야겠다고 다짐하는데 완탐 문제를 풀면 재귀 연습도 열심히해야겠다고 생각하고.. 부족한게 너무 많은 것 같다 ㅋㅋㅋㅋㅋㅋ 하
 *
 */

public class BOJ_15684_사다리조작 {
	static int N, M, H, buildCnt = -1;     // 입력, 지어진 사다리 개수
	static boolean flag;                   // 직방으로 도착했는지 여부
	static int[][] map;
	static boolean[][] visited;            // 각 사다리 경우에 대해 직방으로 도착했는지 확인하기 위해 방문 처리하는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H+1][N+1];
		visited = new boolean[H+1][N+1];
		
		// 초기 사다리 입력
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = map[r][c+1] = c;                // 가로로 연결하기 위해 (r,c), (r,c+1)로 연결
		}
		
		if (checkLadderPair()) System.out.println(0);   // 사다리 설치하지 않아도 되는 경우
		else {
			for (int limit=1; limit<=3; limit++) {      // 사다리를 1개~3개 설치
				combi(limit, 1, 0);
				if (flag) break;                        // 사다리 설치 시도 도중 직방으로 연결됐으면 탈출
			}
			System.out.println(buildCnt);
		}
		
	}
	
	// 사다리를 조합으로 설치
	private static void combi(int limit, int x, int depth) {
		// 기저파트 : limit(1~3)개 설치했으면 직방 연결 여부 확인
		if (depth==limit) {
			if (checkLadderPair()) {          // 직방 연결됐으면 재귀 탈출
				flag = true;
				buildCnt = limit;
			}
			return;
		}
		
		// 유도파트 : 사다리 limit개까지 우선 무조건 설치
		for (int i=x; i<=H; i++) {
			for (int j=1; j<N; j++) {
				if (map[i][j]!=0 || map[i][j+1]!=0) continue;     // 이미 설치했으면 pass
				map[i][j] = map[i][j+1] = j;                      // 같은 사다리끼리는 열의 좌표인 j로 표기
				combi(limit, i, depth+1);                         // 재귀타고 한개씩 추가로 설치
				map[i][j] = map[i][j+1] = 0;                      // 방문 해제
				if (flag) return;           
			}
		}
	}
	
	// 1~N열의 출발점에 대해 직방 연결 확인
	private static boolean checkLadderPair() {
		for (int i=1; i<=N; i++) if (!moveDown(i)) return false;    // 하나의 열이라도 직방 연결 실패하면 false
		return true;
	}

	// 한 열에 대해 출발점과 도착점이 직방으로 연결됐는지 확인
	private static boolean moveDown(int col) {
		for (int i=1; i<=H; i++) Arrays.fill(visited[i], false);      // 방문 초기화
		
		int r = 1;
		int c = col;
		
		while (r<=H) {
			visited[r][c] = true;
			if (isInisde(r, c-1) && map[r][c-1]==c-1 && !visited[r][c-1]) c-=1;       // 왼쪽 사다리 확인
			else if (isInisde(r, c+1) && map[r][c+1]==c && !visited[r][c+1]) c+=1;    // 오른쪽 사다리 확인
			else r+=1;                                                                // 왼쪽과 오른쪽에 사다리 없으면 무조건 하향
		}
		return (c==col);          // 출발점과 도착점의 인덱스가 같으면 직방으로 도착
	}
	
	// 경계체크
	private static boolean isInisde(int x, int y) {
		return (x>=1 && x<=H && y>=1 && y<=N);
	}

}
