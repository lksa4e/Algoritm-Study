import java.io.*;
import java.util.*;

/**
 * [1019] BOJ 16235 나무 재테크
 * 구현, 시뮬레이션
 * 
 * sol)
 * 2차원 행렬로 위치 좌표를 표현하고 각 좌표마다 우선순위 큐를 구현하여 나무의 나이를 저장해두고 진행한다.
 * 
 * 시행착오)
 * 문제에 나온 그대로 구현하면 되는데 처음에는 2차원 행렬 좌표 기준이 아니라 나무를 기준으로 진행함.
 * pq에 나무 위치, 나이를 담고 정렬은 나이 기준으로 하는 방식이었는데 정말 왜때문인지 모르게 테케는 다 맞았는데 시작하자마자 틀려버림..
 * 아직도 왜때문인지 모르겠는데 아마 추가되는 나무가 제대로 안담기거나 양분 감소 처리가 잘못되거나 하지 않았을까...
 *
 */

public class BOJ_16235_나무재테크 {

	static int N, M, K, total;
	static PriorityQueue<Integer>[][] trees;
	static int[][] map, S2D2;
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 지도 상 양분 초기화
		map = new int[N][N];
		for (int i=0; i<N; i++) Arrays.fill(map[i], 5);
		
		// S2D2에 의해 추가될 양분
		S2D2 = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) S2D2[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 나무 위치 좌표를 PQ로 관리하여 나이가 어린 나무부터 poll할 수 있도록 함
		trees = new PriorityQueue[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) trees[i][j] = new PriorityQueue<Integer>();
		}
		
		// 나무 위치 좌표에 나무 나이 저장
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			trees[x][y].offer(age);
		}
		
		// 살아남은 나무 세기
		total = M;
		passYears();
		System.out.println(total);
	}

	// 봄+여름 -> 가을 -> 겨울 순서로 진행
	private static void passYears() {
		for (int k=1; k<=K; k++) {
			springAndSummer();
			autumn();
			winter();
		}
	}

	// 봄+여름
	private static void springAndSummer() {
		for (int i=0; i<N; i++) {                         // 모든 위치 좌표에 대해
			for (int j=0; j<N; j++) {
				int size = trees[i][j].size();
				if (size==0) continue;                    // 나무가 존재하는 경우만 확인
				
				PriorityQueue<Integer> q = new PriorityQueue<>();
				int plusNutrients = 0;
				while(size-->0) {
					int age = trees[i][j].poll();         // 나이가 어린 나무부터 선택
					if (age>map[i][j]) {                  // 위치 좌표에 남은 양분보다 나이 많으면 여름에 할 일로 넘김
						plusNutrients += age/2;
						total--;                          // 나무 즉시 죽임
					} else {                              // 위치 좌표에 남은 양분보다 나이 어리면 양분 감소하고 나이증가
						map[i][j] -= age;
						q.offer(age+1);
					}
				}
				trees[i][j] = q;                          // 위치좌표를 pq로 관리하고 모두 Poll했으니까 다시 offer해줌
				map[i][j] += plusNutrients;               // 여름할 일
			}
		}
	}

	// 가을
	private static void autumn() {
		for (int i=0; i<N; i++) {                         // 모든 위치 좌표에 대해
			for (int j=0; j<N; j++) {
				if (trees[i][j].isEmpty()) continue;      // 나무가 존재하는 경우만 확인
				
				for (int age:trees[i][j]) {
					if (age%5!=0) continue;               // 5의 배우인 경우만 8방에 나무심기
					for (int d=0; d<8; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (!isInside(nx, ny)) continue;
						trees[nx][ny].offer(1);           // 나무 위치 좌표에 나이 1인 나무 심고
						total++;                          // 새로 생긴 나무 카운트
					}
				}
			}
		}
	}

	// 겨울
	private static void winter() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) map[i][j] += S2D2[i][j];    // 겨울에는 모든 좌표에 양분 추가
		}
	}
	
	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}

}
