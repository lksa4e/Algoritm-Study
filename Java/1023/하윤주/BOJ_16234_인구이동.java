import java.io.*;
import java.util.*;

/**
 * [1023] BOJ 16234 인구이동
 * 시뮬레이션, bfs
 * 
 * sol)
 * 1. 각 좌표에서 bfs로 4방 탐색을 통해 인접(인구수 차이가 L, R사이)한 나라를 찾는다. 이 과정에서 3가지 일을 수행한다.
 *    1) 인접한 나라 좌표를 같은 숫자로 넘버링
 *    2) 몇 개의 나라가 인접한지 카운트
 *    3) 인구수의 누계
 * 2. 만약 1번 과정에서 인접한 나라가 하나도 없었다면 종료
 * 3. 인접한 나라가 있었다면 모든 좌표를 확인하며 1-1)에서 구한 같은 나라로 구분된 곳에 평균 인구수를 저장한다.
 *
 */

public class BOJ_16234_인구이동 {
	
	static int N, L, R, day, countryCnt, peopleSum, openCnt;
	static int[][] map, opened;            // opened : 같은 나라끼리 같은 번호로 넘버링하여 저장
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Integer> q = new ArrayDeque<Integer>();       // bfs를 위한 큐
	static List<Integer> countries = new ArrayList<>();        // 인덱스 : 총 몇개의 나라로 구분되는지, 값 : 같은 나라의 평균 인구
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 문호 개방
		startDay();
		System.out.println(day);
	}

	private static void startDay() {
		for (day=0; day<=2000; day++) {
			if (!openBorder()) return;      // 국경 열기 시도, 만약 조건에 부합하지 않아 국경을 열 수 없으면 종료
			updatePeople();                 // 평균 인구수로 인구 업데이트
			for (int[] m : map) System.out.println(Arrays.toString(m));
			System.out.println();
		}
	}
	
	// 모든 나라에 대해 국경 열 수 있는지 확인
	private static boolean openBorder() {
		boolean flag = false;
		countries.clear();
		opened = new int[N][N];
		openCnt = 1;
		
		for (int i=0; i<N; i++) {                     // 모든 나라에 대해 국경 열기 시도
			for (int j=0; j<N; j++) {
				if (opened[i][j]!=0) continue;        // 이미 국경 열려있는 곳은 pass
				countryCnt = 1;
				peopleSum = map[i][j];
				
				bfs(i, j);                            // 인접한 나라 확인
				countries.add(peopleSum/countryCnt);  // 인접한 나라 개수와 인구 누계를 통해 평균 인구 계산
				openCnt++;                            // countries 리스트에 나라 구분을 인덱스로, 평균 인구수를 값으로 저장
				
				if (countryCnt!=1) flag = true;       // 만약 bfs 탐색으로 인접한 나라 못찾았으면 false, 한번이라도 찾았으면 true
			}
		}
		return flag;
	}
	
	// 인접한 나라(인구수 차이가 L, R사이) 찾기
	private static void bfs(int i, int j) {
		q.clear();
		q.offer(i);
		q.offer(j);
		opened[i][j] = openCnt;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();
			
			for (int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (!isInside(nx, ny) || opened[nx][ny]!=0) continue;
				
				int gap = Math.abs(map[x][y]-map[nx][ny]);
				if (gap>=L && gap<=R) {                         // 인구수 차이가 L, R 사이면 인접
					opened[nx][ny] = openCnt;                   // 1) 같은 번호로 넘버링하고
					q.offer(nx);
					q.offer(ny);
					countryCnt++;                               // 2) 몇 개의 나라가 인접한지 카운트하고
					peopleSum += map[nx][ny];                   // 3) 인구 누계 계산
				}
			}
		}
	}
	
	// 같은 번호로 넘버링된 인접한 나라끼리 평균 인구수로 인구수 업데이트
	private static void updatePeople() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				int newPeople = countries.get(opened[i][j]-1);
				map[i][j] = newPeople;
			}
		}
	}

	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
}
