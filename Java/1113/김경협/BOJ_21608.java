import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * BOJ 21608번 상어 초등학교
 * 
 * Cell 클래스를 구현하여, 비어있는 칸 개수에 따른 비교와
 * row와 col에 따른 비교를 Comparable로 구현하여
 * Priority Queue를 통해서 바로바로 뽑을 수 있도록 하였다.
 * 
 */

public class BOJ_21608 {
	static class Cell implements Comparable<Cell>{
		int row;
		int col;
		int adjEmpty;
		
		public Cell(int row, int col, int adjEmpty) {
			super();
			this.row = row;
			this.col = col;
			this.adjEmpty = adjEmpty;
		}

		@Override
		public int compareTo(Cell o) {
			return this.adjEmpty != o.adjEmpty ? o.adjEmpty-this.adjEmpty : (this.row != o.row ? this.row - o.row : this.col - o.col);
		}
	}

	/*
	 * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
	 * 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
	 * 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
	 */
	static final int[] dr = {-1,1,0,0};
	static final int[] dc = {0,0,-1,1};
	static int N, map[][],favAdj[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		favAdj = new int[N*N+1][4];	// 나중에 score 계산을 하기 위한 각 학생들의 좋아하는 학생 리스트 
		
		int num = N*N;
		for (int i = 0; i < num; i++) {
			int maxFav = 0;
			HashMap<Integer, Boolean> hash = new HashMap<Integer, Boolean>();	// 주변 학생들 중에 좋아하는 사람이 있는지를 탐색하기 위한 해쉬맵
			st = new StringTokenizer(br.readLine());
			int input = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < 4; j++) {
				int n = Integer.parseInt(st.nextToken());
				hash.put(n, true);
				favAdj[input][j] = n;
			}
			
			PriorityQueue<Cell> pq = new PriorityQueue<>();
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(map[r][c] != 0) continue;	// 비어있는 칸에만 들어갈 수 있음
					
					int numOfEmpty = 0, numOfFav = 0;	// 그 칸의 주변에 비어있는 칸 수와, 주변에 좋아하는 학생의 수
					for (int dir = 0; dir < 4; dir++) {
						int nextR = r + dr[dir];
						int nextC = c + dc[dir];
						if(isOutOfMap(nextR, nextC)) continue;
						if(map[nextR][nextC] == 0) numOfEmpty++;	// 비어있는
						if(hash.get(map[nextR][nextC]) != null) numOfFav++;	// 좋아하는 학생이 있는
					}
					
					if(numOfFav > maxFav) {		// 1번 조건, 좋아하는 학생의 수가 maxFav 보다 많아졌을 때, pq를 비우고 다시 시작
						pq.clear();
						maxFav = numOfFav;
						pq.add(new Cell(r, c, numOfEmpty));
					} else if(numOfFav == maxFav) {	// 2번 조건, 좋아하는 학생의 수가 동일할 때,
						pq.add(new Cell(r, c, numOfEmpty));	// PQ를 사용해 2번,3번 조건을 동시에 구함
					}
					
				}
			}

			Cell mark = pq.poll();		// 자동적으로 조건에 가장 맞는 Cell이 나옴
			map[mark.row][mark.col] = input;
		}
		
		System.out.println(getScore());
	}
	
	// 스코어 구하기 메소드
	static int getScore() {
		int score = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int num = map[r][c];
				int numOfFavorite = 0;	// 주변 좋아하는 학생 수 탐색
				for (int dir = 0; dir < 4; dir++) {
					int nextR = r + dr[dir];
					int nextC = c + dc[dir];
					if(isOutOfMap(nextR, nextC)) continue;
					
					int[] favList = favAdj[num];
					int nextN = map[nextR][nextC];
					for (int i = 0; i < 4; i++) {	
						if(nextN == favList[i]) {
							numOfFavorite++;
							break;
						}
					}
				}
				
				if(numOfFavorite == 1)
					score += 1;
				else if(numOfFavorite == 2)
					score += 10;
				else if(numOfFavorite == 3)
					score += 100;
				else if(numOfFavorite == 4)
					score += 1000;
			}
		}
		
		return score;
	}
	
	static boolean isOutOfMap(int r, int c) {
		return r < 0 || c < 0 || r >= N || c >= N;
	}

}
