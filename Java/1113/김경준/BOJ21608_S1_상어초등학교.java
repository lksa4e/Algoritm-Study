import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리 : 22868KB, 시간 236ms
 * 시키는대로 구현하면 되는 문제였지만 말을 해석하기가 너무 힘들었음...
 */

public class BOJ21608_S1_상어초등학교 {
	static int N, map[][], scoreMap[][], likeNum[][];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];  // 자리 앉기
		likeNum = new int[(N*N)+ 1][4];  // 좋아하는 사람 번호 저장 배열 
		for(int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int studentNum = Integer.parseInt(st.nextToken());
			for(int j = 0; j < 4; j++) {
				likeNum[studentNum][j] = Integer.parseInt(st.nextToken());
			}
			
			// 1번 조건 찾기
			List<Pair> list = new ArrayList<Pair>();
			scoreMap = new int[N][N];
			int maxFindNum = 0;
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(map[r][c] != 0) continue;
					// 주변에 좋아하는 사람이 몇명 앉았는지 count함
					scoreMap[r][c] = findLikeStudentCount(r,c,studentNum);
					// 좋아하는 사람 수 중 최대값을 저장
					maxFindNum = Math.max(scoreMap[r][c], maxFindNum);
				}
			}
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(map[r][c] != 0) continue;
					// 1번 조건을 만족하는 후보들을 list에 넣음
					if(scoreMap[r][c] == maxFindNum) {
						list.add(new Pair(r,c));
					}
				}
			}
			// list 사이즈가 1이면 1번 조건을 만족하는 자리가 유일 -> 자리지정
			if(list.size() == 1) {
				map[list.get(0).x][list.get(0).y] = studentNum;
			}
			// 조건 2
			else {
				// 인접한 칸 중 비어있는 칸이 가장 많은 칸 구하기
				List<Pair> emptyList = new ArrayList<Pair>();
				scoreMap = new int[N][N];
				int maxEmptyNum = 0;
				for(int k = 0; k < list.size(); k++) {
					int x_pos = list.get(k).x;
					int y_pos = list.get(k).y;
					// 인접한 칸 중 비어있는 칸의 개수를 세어서 scoreMap에 저장
					scoreMap[x_pos][y_pos] = findNearEmptyCount(x_pos,y_pos);
					// 최대값 구하기
					maxEmptyNum = Math.max(maxEmptyNum, scoreMap[x_pos][y_pos]);
				}
				
				for(int k = 0; k < list.size(); k++) {
					int x_pos = list.get(k).x;
					int y_pos = list.get(k).y;
					// 2번 조건을 만족하는 후보들을 list에 넣음
					if(scoreMap[x_pos][y_pos] == maxEmptyNum) {
						emptyList.add(new Pair(x_pos, y_pos));
					}
				}
				
				// 2번 조건을 만족하는 후보가 단일 후보면 바로 자리지정
				if(emptyList.size() == 1) {
					map[emptyList.get(0).x][emptyList.get(0).y] = studentNum;
				}
				// 조건 3 -> 정렬하여 가장 왼쪽 위 자리를 찾아서 지정
				else {
					Collections.sort(emptyList);
					map[emptyList.get(0).x][emptyList.get(0).y] = studentNum;
				}
			}
		}
		// 만족도 구하기
		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int cnt = findLikeStudentCount(i, j, map[i][j]);
				int addpoint = (int)Math.pow(10, cnt - 1);
				answer += addpoint;
			}
		}
		System.out.println(answer);
	}
	// 주변의 빈칸 개수 세기
	static int findNearEmptyCount(int x, int y) {
		int count = 0;
		for(int i = 0; i < 4; i++) {
			int nx = x + ("2110".charAt(i)-'1');
			int ny = y + ("1201".charAt(i)-'1');
			if(oob(nx,ny)) continue;
			if(map[nx][ny] == 0) count++;
		}
		return count;
	}
	// 주변의 좋아하는 사람 개수 세기
	static int findLikeStudentCount(int x, int y, int studentNum) {
		int count = 0;
		for(int i = 0; i < 4; i++) {
			int nx = x + ("2110".charAt(i)-'1');
			int ny = y + ("1201".charAt(i)-'1');
			if(oob(nx,ny)) continue;
			for(int j = 0; j < 4; j++) {
				if(map[nx][ny] == likeNum[studentNum][j]) {
					count++;
					break;
				}
			}
		}
		return count;
	}
	static class Pair implements Comparable<Pair>{
		int x,y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.x == o.x) return Integer.compare(this.y, o.y);
			else return Integer.compare(this.x, o.x);
		}
	}
	static boolean oob(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
