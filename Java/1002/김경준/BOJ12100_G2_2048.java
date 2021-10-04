import java.io.*;
import java.util.*;

/**
 * G2 BOJ 12100 2048(EASY) - 한칸씩 미는 풀이
 * 메모리 : 22152kb 시간 : 236ms
 * 
 * 기본풀이)
 * 상 하 좌 우 미는 연산을 DFS 깊이 5가 될때까지 수행한 후 5회 연산을 마쳤을 때 최대값을 구한다. 
 * 
 * 풀이 1)
 * 단순하게 한칸씩 밀면서 조건체크 하는 방법으로 구현
 * 
 */
public class BOJ12100_G2_2048 {
	static int N, map[][], answer = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, map);
		System.out.println(answer);
	}

	// dfs 함수
	static void dfs(int cnt, int[][] map) {
		if (cnt == 5) {
			int result = get_max(map);
			answer = Math.max(answer, result);
			return;
		}
		// 밀때마다 map의 변화가 생기기 때문에 map을 복사한 뒤 미는 연산 수행
		int[][] copy_map = map_copy(map);
		move_U(copy_map);
		dfs(cnt + 1, copy_map);

		copy_map = map_copy(map);
		move_D(copy_map);
		dfs(cnt + 1, copy_map);

		copy_map = map_copy(map);
		move_L(copy_map);
		dfs(cnt + 1, copy_map);

		copy_map = map_copy(map);
		move_R(copy_map);
		dfs(cnt + 1, copy_map);
	}

	// 왼쪽으로 밀기
	static void move_L(int[][] map) {
		// 한번 합치기 연산이 일어난 경우 해당 자리에서 다시 합치기 연산 못하게 체크하는 배열
		// 왼쪽으로 합치기 때문에 y 좌표를 기준으로 check 배열 만듬
		boolean[] visit = new boolean[N];
		// 모든 행에 대해서 왼쪽으로 미는 연산 수행
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit, false);
			// 1 열에서 시작해서 왼쪽으로 한칸씩 밀어봄
			for (int j = 1; j < N; j++) {
				if(map[i][j] == 0) continue;
				int ny = j - 1;
				while (ny >= 0) {
					// 밀다가 0이 아닌 순자를 만났을 때
					if (map[i][ny] != 0) {
						// 합칠 수 있는 숫자인 경우
						if (map[i][ny] == map[i][j]) {
							// 만약 해당 숫자가 합칠 수 있으면 합치기
							if (!visit[ny]) {
								visit[ny] = true;
								map[i][ny] *= 2;
								map[i][j] = 0;
							}
							// 이미 합치기 연산이 수행되었다면 -> 이전칸에 숫자를 배치
							else {
								map[i][ny + 1] = map[i][j];
								map[i][j] = 0;
							}
						}
						// 숫자를 만났지만 합칠 수 없는 경우
						else {
							// 시작하자마자 바로 다른 숫자를 만나는 경우
							// 기존 연산과 같이 옮길 자리에 옮기고 map[i][j] = 0을 해버리면 숫자가 사라짐
							if (ny + 1 == j)
								break;
							map[i][ny + 1] = map[i][j];
							map[i][j] = 0;
						}
						break;
					}
					// 0인 경우엔 계속 직진함
					ny--;
				}
				// 만약 while문을 탈출했는데 끝까지 가서 탈출한 것이라면 -> 첫번째 칸에 숫자 배치
				if (ny == -1) {
					map[i][0] = map[i][j];
					map[i][j] = 0;
				}
			}
		}
	}

	static void move_R(int[][] map) {
		boolean[] visit = new boolean[N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit, false);
			for (int j = N - 2; j >= 0; j--) {
				if(map[i][j] == 0) continue;
				int ny = j + 1;
				while (ny < N) {
					if (map[i][ny] != 0) {
						if (map[i][ny] == map[i][j]) {
							if (!visit[ny]) {
								visit[ny] = true;
								map[i][ny] *= 2;
								map[i][j] = 0;
							} else {
								map[i][ny - 1] = map[i][j];
								map[i][j] = 0;
							}
						} else {
							if (ny - 1 == j)
								break;
							map[i][ny - 1] = map[i][j];
							map[i][j] = 0;
						}
						break;
					}
					ny++;
				}
				if (ny == N) {
					map[i][N - 1] = map[i][j];
					map[i][j] = 0;
				}
			}
		}
	}

	static void move_U(int[][] map) {
		boolean[] visit = new boolean[N];
		for (int j = 0; j < N; j++) {
			Arrays.fill(visit, false);
			for (int i = 1; i < N; i++) {
				if(map[i][j] == 0) continue;
				int nx = i - 1;
				while (nx >= 0) {
					if (map[nx][j] != 0) {
						if (map[nx][j] == map[i][j]) {
							if (!visit[nx]) {
								visit[nx] = true;
								map[nx][j] *= 2;
								map[i][j] = 0;
							} else {
								map[nx + 1][j] = map[i][j];
								map[i][j] = 0;
							}
						} else {
							if (nx + 1 == i)
								break;
							map[nx + 1][j] = map[i][j];
							map[i][j] = 0;
						}
						break;
					}
					nx--;
				}
				if (nx == -1) {
					map[0][j] = map[i][j];
					map[i][j] = 0;
				}
			}
		}
	}

	static void move_D(int[][] map) {
		boolean[] visit = new boolean[N];
		for (int j = 0; j < N; j++) {
			Arrays.fill(visit, false);
			for (int i = N - 2; i >= 0; i--) {
				if(map[i][j] == 0) continue;
				int nx = i + 1;
				while (nx < N) {
					if (map[nx][j] != 0) {
						if (map[nx][j] == map[i][j]) {
							if (!visit[nx]) {
								visit[nx] = true;
								map[nx][j] *= 2;
								map[i][j] = 0;
							} else {
								map[nx - 1][j] = map[i][j];
								map[i][j] = 0;
							}
						} else {
							if (nx - 1 == i)
								break;
							map[nx - 1][j] = map[i][j];
							map[i][j] = 0;
						}
						break;
					}
					nx++;
				}
				if (nx == N) {
					map[N - 1][j] = map[i][j];
					map[i][j] = 0;
				}
			}
		}
	}

	// 2차원 배열을 탐색하며 최대값 찾기
	static int get_max(int[][] map) {
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer = Math.max(answer, map[i][j]);
			}
		}
		return answer;
	}

	// 2차원 배열 복사하기
	static int[][] map_copy(int[][] origin) {
		int temp[][] = new int[N][N];
		for (int i = 0; i < N; i++)
			temp[i] = origin[i].clone();
		return temp;
	}
}
