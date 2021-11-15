import java.io.*;
import java.util.*;
/**
 * 백준 21610 골드 5 마법사 상어와 비바라기
 * 메모리 : 17216kb, 시간 : 208ms
 * 
 *  시키는 대로 구현하는 그나마 단순한 문제
 *  구름 변화를 깔끔하게 하기 위해 매번 임시 배열을 만들어서 원본 구름배열에 덮어씌움
 *
 */

public class BOJ21610_G5_마법사상어와비바라기 {
	static int N,M, map[][], cloud[][];
	static int dx[] = {-5, 0,-1,-1,-1,0,1,1,1};
	static int dy[] = {-5, -1,-1,0,1,1,1,0,-1};
	static int cross_dx[] = {-1,-1,1,1};
	static int cross_dy[] = {-1,1,1,-1};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		cloud = new int[N][N];
		cloud[N-1][0] = cloud[N-1][1] = cloud[N-2][0] = cloud[N-2][1] = 1;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			cloudMove(dir,dist);
			rainDrop();
			waterCopy();
			makeNewCloud();
		}
		
		int answer = calcWaterAmount();
		System.out.print(answer);
	}
	
	// 모든 구름이 dir 방향으로 dist만큼 이동
	static void cloudMove(int dir, int dist) {
		int [][] tempcloud = new int[N][N];
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(cloud[i][j] != 0) { 	// 구름인 경우에 이동
					int nx = (i + dx[dir] * dist + 50 * N) % N; //50 * N -> dist 곱 연산으로 %N 연산이 불가능한 것 방지
					int ny = (j + dy[dir] * dist + 50 * N) % N;
					tempcloud[nx][ny] = 1; 	// 임시배열에 옮겨진 구름 저장
				}
			}
		}
		cloud = tempcloud; // 원본 구름배열에 다시 옮기기
	}
	
	// 구름 위치에 비내리기 + 구름사라지기
	static void rainDrop() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(cloud[i][j] == 1) {
					map[i][j]++;
					cloud[i][j] = -1;
				}
			}
		}
	}
	// 물복사 버그
	static void waterCopy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(cloud[i][j] != -1) continue;  // 물이 증가한 칸에만 물복사 버그 시전
				for(int k = 0; k < 4; k++) {
					int nx = i + cross_dx[k];    // 대각선 x, y 좌표
					int ny = j + cross_dy[k];
					if(oob(nx,ny)) continue;
					if(map[nx][ny] != 0) {       // 인접 대각선에 물 있으면 증가
						map[i][j]++;
					}
				}
			}
		}
	}
	
	// 새로운 구름 만들기
	static void makeNewCloud() {
		int[][] tempCloud = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] >= 2 && cloud[i][j] == 0) {  // 물의 양이 2 이상이고 3에서 구름이 사라진 칸이 아닌경우
					tempCloud[i][j] = 1;
					map[i][j] -= 2;
				}
			}
		}
		cloud = tempCloud;
	}
	// 물 총합 계산
	static int calcWaterAmount() {
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	static boolean oob(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
