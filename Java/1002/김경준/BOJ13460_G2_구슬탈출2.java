import java.io.*;
import java.util.*;

/**
 * G2 BOJ 13460 구슬탈출2 :
 * 메모리 : 16524kb 시간 : 360ms
 * 
 * 최대 동작의 횟수 : 10회
 * 한번 동작에서 가능한 경우의 수 : 4회
 * ==> 4^10 == 1048576
 * 
 * 가능한 모든 경우를 수행하여도 시간적으로 문제가 없다.
 * DFS를 통해 구현
 * 
*/
public class BOJ13460_G2_구슬탈출2 {
	static int N,M, red_x, red_y, blue_x, blue_y, answer = 11;
	static int dx[] = {1,0,0,-1};
	static int dy[] = {0,1,-1,0};
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'R') {
					red_x = i;
					red_y = j;
				}else if(map[i][j] == 'B') {
					blue_x = i;
					blue_y = j;
				}
			}
		}
		
		dfs(red_x,red_y,blue_x,blue_y, 0);
		
		// 만약 목표지점에 넣을 수 없다면 -1 출력
		if(answer == 11) System.out.println(-1);
		else System.out.println(answer);
	}
	static void dfs(int rx, int ry, int bx, int by, int cnt) {
		// 10회 이상은 수행 불가
		if(cnt > 10) return;
		
		// 빨간 구슬을 넣고, 동시에 파란 구슬을 넣지 않으면 성공
		if(map[rx][ry] == 'O' && map[bx][by] != 'O') {
			answer = Math.min(answer, cnt);
			return;
		}
		//상 하 좌 우 4방 탐색
		for(int dir=0; dir < 4; dir++){
			int next_rx = rx;
            int next_ry = ry;
            int next_bx = bx;
            int next_by = by;
            
            // 빨간 구슬 전진
            while (true) {
            	// 벽이나 구멍에 도달할때까지 계속해서 전진
                if (map[next_rx][next_ry] != '#' && map[next_rx][next_ry] != 'O') {
                	next_rx += dx[dir];
                    next_ry += dy[dir];
                }
                else {
                	// 만약 멈춘 지점이 벽이라면 한 칸 후퇴
                    if (map[next_rx][next_ry] == '#') {
                    	next_rx -= dx[dir];
                        next_ry -= dy[dir];
                    }
                    break;
                }
            }
            // 파란 구슬 전진
            while (true) {
                if (map[next_bx][next_by] != '#' && map[next_bx][next_by] != 'O') {
                	next_bx += dx[dir];
                    next_by += dy[dir]; 
                }
                else {
                    if (map[next_bx][next_by] == '#') {
                    	next_bx -= dx[dir];
                        next_by -= dy[dir];
                    }
                    break;
                }
            }
            
            // 만약 구슬을 이동하고 보니 둘이 같은 지점에 있다면? 도착지 순서를 지정해 줘야 함
            if(next_rx == next_bx && next_ry == next_by){
                if(map[next_rx][next_ry]!='O'){ 
                	// 순서상으로 어떤 것이 도착지점에 가까운지 구하기 위한 dist 계산
                    int red_dist = Math.abs(next_rx - rx)+ Math.abs(next_ry- ry);
                    int blue_dist = Math.abs(next_bx - bx)+ Math.abs(next_by - by);
                    // 만약 도착지까지의 거리가 빨간 구슬이 더 멀다? => 순서가 파란구슬 -> 빨간구슬
                    if(red_dist > blue_dist){
                    	// 빨간구슬을 파란구슬 뒤쪽에 배치
                    	next_rx -= dx[dir];
                    	next_ry -= dy[dir];
                    }else{
                    	next_bx -= dx[dir];
                    	next_by -= dy[dir];
                    }
                    // 다음 dfs 진행
                    dfs(next_rx, next_ry, next_bx, next_by, cnt + 1);                    
                }
            }else {
            	dfs(next_rx, next_ry, next_bx, next_by, cnt + 1);        	
            }
        }
	}
}
