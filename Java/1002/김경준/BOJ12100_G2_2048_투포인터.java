import java.io.*;
import java.util.*;

/**
 * G2 BOJ 12100 2048(EASY) - 투포인터 방식의 풀이
 * 메모리 : 19784kb 시간 : 180ms
 * 
 * 한칸씩 미는 풀이와 주석 제거 기준 코드길이 1000B, 시간 50ms 차이가 났다
 * 한칸씩 미는 경우 매번 해당 좌표가 합칠 수 있는지, 범위 밖으로 나가는지 등 조건 처리가 많고 중복 연산이 일어나기 때문인거같음  
 * 
 * 기본풀이)
 * 상 하 좌 우 미는 연산을 DFS 깊이 5가 될때까지 수행한 후 5회 연산을 마쳤을 때 최대값을 구한다. 
 * 
 * 투포인터 풀이)
 * 놓는 위치 + 합쳐야 하는지를 판단하는 flag 를 제일 처음 칸으로 잡고 시작
 * Case 1. 그냥 놓을 수 있는 경우 (합쳐지지 않았기 때문에 flag 이동 X) 
 * Case 2. 합칠 수 있는 경우 합치고 flag를 이동시킴 (이동시킨 위치에 놓거나 합치기 위해)
 * Case 3. 합칠 수 없는 경우 한 칸 뒤로 옮기고 flag를 이동시킴   
 * 
 */
public class BOJ12100_G2_2048_2 {
static int N, map[][], answer = 0;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine()); 
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, map);
		System.out.println(answer);
	}
	// dfs 함수
	static void dfs(int cnt, int[][] map) {
		if(cnt == 5) {
			int result = get_max(map);
			answer = Math.max(answer, result);
			return;
		}
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
		for(int i = 0; i < N; i++) {
			// 0번 열부터 해당 자리에 놓을 수 있는지 체크한다.
			int flag = 0;
			for(int j = 1; j < N; j++) {
				// 숫자인 경우만 탐색
				if(map[i][j] != 0) {
					// 만약 합칠 수 있는 숫자라면 -> 합친다
					// 합치기 연산시 flag를 변화시켜주기 때문에 동일 자리에서 두번 합쳐지는 경우는 없다.
					// 그래서 한칸씩 미는 풀이와 다르게 visit 등의 boolean 배열을 사용할 필요가 없다.
					if(map[i][flag] == map[i][j]) {
						map[i][flag] *= 2;
						map[i][j] = 0;
						flag++; // flag를 옮겨줌으로써 다음에 합쳐지거나 놓아질 자리를 한칸 뒤로 이동시킨다.
					}
					// 탐색하는 후보 자리가 0인 경우에는 그냥 놓는다.
					else if(map[i][flag] == 0) {
						map[i][flag] = map[i][j];
						map[i][j] = 0;
					}
					// 탐색하는 후보 자리에 다른 숫자가 있는 경우
					else {
						// 시작하자마자 바로 다른 숫자를 만나는 경우
						// 기존 연산과 같이 옮길 자리에 옮기고 map[i][j] = 0을 해버리면 숫자가 사라짐
						if(flag + 1 != j) {
							map[i][flag + 1] = map[i][j];
							map[i][j] = 0;
						}
						flag++;
					}
				}
			}
		}
	}
	static void move_R(int[][] map) {
		for(int i = 0; i < N; i++) {
			int flag = N-1;
			for(int j = N-2; j >= 0; j--) {
				if(map[i][j] != 0) {
					if(map[i][flag] == map[i][j]) {
						map[i][flag] *= 2;
						map[i][j] = 0;
						flag--;
					}else if(map[i][flag] == 0) {
						map[i][flag] = map[i][j];
						map[i][j] = 0;
					}else {
						if(flag - 1 != j) {
							map[i][flag - 1] = map[i][j];
							map[i][j] = 0;
						}
						flag--;
					}
				}
			}
		}
	}
	static void move_U(int[][] map) {
		for(int j = 0; j < N; j++) {
			int flag = 0;
			for(int i = 1; i < N; i++) {
				if(map[i][j] != 0) {
					if(map[flag][j] == map[i][j]) {
						map[flag][j] *= 2;
						map[i][j] = 0;
						flag++;
					}else if(map[flag][j] == 0) {
						map[flag][j] = map[i][j];
						map[i][j] = 0;
					}else {
						if(flag + 1 != i) {
							map[flag+1][j] = map[i][j];
							map[i][j] = 0;
						}
						flag++;
					}
				}
			}
		}
	}
	static void move_D(int[][] map) {
		for(int j = 0; j < N; j++) {
			int flag = N-1;
			for(int i = N-2; i >= 0; i--) {
				if(map[i][j] != 0) {
					if(map[flag][j] == map[i][j]) {
						map[flag][j] *= 2;
						map[i][j] = 0;
						flag--;
					}else if(map[flag][j] == 0) {
						map[flag][j] = map[i][j];
						map[i][j] = 0;
					}else {
						if(flag - 1 != i) {
							map[flag - 1][j] = map[i][j];
							map[i][j] = 0;
						}
						flag--;
					}
				}
			}
		}
	}
	static int get_max(int[][] map) {
		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				answer = Math.max(answer, map[i][j]);
			}
		}
		return answer;
	}
	static int[][] map_copy(int[][] origin){
		int temp[][] = new int[N][N];
		for(int i = 0; i < N; i++)
			temp[i] = origin[i].clone();
		return temp;
	}
}
