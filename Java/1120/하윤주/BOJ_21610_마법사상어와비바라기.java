import java.io.*;
import java.util.*;

/**
 * [1116] BOJ 21610 마법사 상어와 비바라기
 * 구현, 시뮬레이션, 2차원 배열, 배열돌리기
 * 
 * sol)
 * 문제 그대로 구현하되, 배열 돌리기? 인덱싱? 을 신경써야하는 문제
 * 
 */

public class BOJ_21610_마법사상어와비바라기 {
	static int N, M, total;
	static int[][] map;
	static ArrayList<Integer> cloud = new ArrayList<Integer>();        // 구름인 칸 기억할 큐
	static ArrayList<Integer> nextCloud = new ArrayList<Integer>();    // 새롭게 구름이 된 칸 기억할 큐
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] diagonal = new int[] {1, 3, 5, 7};                    // 대각선 이동을 위한 델타
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 초기 물의 양 입력
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 초기 구름 칸 초기화
		cloud.add((N-1)*N+0);
		cloud.add((N-1)*N+1);
		cloud.add((N-2)*N+0);
		cloud.add((N-2)*N+1);
		
		// M번동안 비바라기 
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			step12MoveAndRain(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
			step4Magic();
			step5Cloud();
		}
		// 최종 물의 양 합산
		sumWaters();
		System.out.println(total);
	}

	// step 1~2: 구름 이동한 뒤 비 내리기
	private static void step12MoveAndRain(int d, int s) {
		for (int size=cloud.size(), i=0; i<size; i++) {
			int xy = cloud.get(i);    // 구름인 칸 꺼내서
			int x = xy/N;
			int y = xy%N;
			
			int nx = indexingDirection(x, dx[d], s);     // 이동하고
			int ny = indexingDirection(y, dy[d], s);
			
			map[nx][ny]++;              // 비내린 뒤
			cloud.set(i, nx*N+ny);      // 이동한 구름 위치를 다시 구름 큐에 저장
		}
	}
	
	// d방향으로 s칸 이동하여 도착하는 위치 구하기
	private static int indexingDirection(int start, int d, int s) {
		if (d<0) return (start+N + d*(s%N)) % N;       // 위로 혹은 왼쪽으로 이동할 때
		else return (start + d*s) % N;                 // 아래로 혹은 오른쪽으로 이동할 때
	}

	// step 3~4: 마법을 시전해 대각선 기반으로 물 증가
	private static void step4Magic() {
		for (int size=cloud.size(), i=0; i<size; i++) {
			int xy = cloud.get(i);         // 구름인 칸 꺼내서
			int x = xy/N;
			int y = xy%N;
			
			int cnt = 0;
			for (int j=0; j<4; j++) {      // 대각선 칸 물의 양 확인하고
				int dr = diagonal[j];
				int nx = x + dx[dr];
				int ny = y + dy[dr];
				if (isInside(nx, ny) && map[nx][ny]>0) cnt++;
			}
			map[x][y] += cnt;              // 구름이었던 칸 물의 양 증가
		}
	}

	// step 5: 물의 양 2 이상인 모든 칸 구름 생성
	private static void step5Cloud() {
		nextCloud.clear();             // 새롭게 생성된 구름 위치가 저장될 큐
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]>=2 && !cloud.contains(i*N+j)) {
					map[i][j] -= 2;
					nextCloud.add(i*N+j);      // 새로운 구름으로 기억
				}
			}
		}
		cloud.clear();
		for (int xy : nextCloud) cloud.add(xy);      // 새로운 구름 큐를 기존 구름 큐에 복사(다음 단계 처리를 위해)
	}
	
	// 물의 양 합산
	private static void sumWaters() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) total += map[i][j];
		}
	}
	
	// 경계 체크
	private static boolean isInside(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}

}
