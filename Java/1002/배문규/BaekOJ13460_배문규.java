package BaekOJ.study.date1002;

import java.io.*;
import java.util.*;

/*
 * 깡 BFS 시뮬 구현인데 디테일한 조건체크가 귀찮았음...
 * 역시 시뮬은 재밌긴한데 화가 많이 남
 * 
 * 빨간구슬과 파란구슬이 같이 홀에 빠지면 안되고
 * 빨간구슬만 빠져야 함.
 * 
 * 동시에 공을 각각 움직이고 싶어서 여러방법으로 구현을 시도해보았는데 잘안되었음
 * 기울이면 그냥 각각 홀에 빠지거나, 벽에 닿을 때 까지 쭉밀고 움직인 수를 체크해줘서 공의 위치를 조정해주었는게 중요했다
 * 자바에서는 그냥 최대한 객체를 생성안하는 방식으로 접근하려고 노력하는 중..
 * 
 * 시행착오 : '10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.' 를 제대로 못봤음 제발 문제를 좀 야무지게 살펴보자
 * 
 *  메모리 	시간
 * 	14288	140
 */

public class BaekOJ13460_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, M, redI, redJ, blueI, blueJ;
	static char map[][];
	static boolean check[][][][];
	static int delta[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static Queue<int[]> queue = new ArrayDeque<int[]>();
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'R') { redI = i; redJ = j; } // 빨간 공 위치저장
				else if(map[i][j] == 'B') { blueI = i; blueJ = j; } // 파란 공 위치저장
			}
		}
		
		System.out.println(tilt());
	}
	
	public static int tilt() {
		check = new boolean[N][M][N][M];

		int ri, rj, bi, bj, cnt, moveR, moveB, result = 1;
		queue.offer(new int[] {redI, redJ, blueI, blueJ, result});
		
		while(!queue.isEmpty()) {
			int[] balls = queue.poll();
			
			if(balls[4] > 10) return -1; // *중요 조건 : 10번을 초과하면 -1 리턴
			
			for(int[] d : delta) {
				ri = balls[0];
				rj = balls[1];
				bi = balls[2];
				bj = balls[3];
				cnt = balls[4];
				
				moveR = 0; // 서로 겹칠 때 위치 조절 용
				while(map[ri][rj] != 'O' && 
						map[ri+d[0]][rj+d[1]] != '#') { // 홀에 빠지거나, 벽 앞에 올 때 까지
					ri += d[0];
					rj += d[1];
					moveR++;
				}
				
				moveB = 0;
				while(map[bi][bj] != 'O' &&
						map[bi+d[0]][bj+d[1]] != '#') {
					bi += d[0];
					bj += d[1];
					moveB++;
				}
				
				if(map[bi][bj] == 'O') continue; // 파란공이 홀에 빠지면 다시
				if(map[ri][rj] == 'O') return cnt; // 파란공이 홀에 안빠졌는데, 빨간공이 홀에 빠졌으면 움직인 횟수 리턴
					
				if(ri == bi && rj == bj) { // *중요 조건 : 같은 벽앞에서 서로 겹쳐졌다면
					if(moveR > moveB) { // 더 많이 움직인 공이 뒤에 있는 공
						ri -= d[0];
						rj -= d[1]; 
					}
					else {
						bi -= d[0];
						bj -= d[1];
					}
				}
				
				if(!check[ri][rj][bi][bj]) { // 이미 와본 곳이 아니면
					check[ri][rj][bi][bj] = true; // 방문체크
					queue.offer(new int[] {ri, rj, bi, bj, cnt+1}); // BFS
				}
			}
		}
		
		return -1;
	}
}
