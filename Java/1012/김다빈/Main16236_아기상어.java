import java.io.*;
import java.util.*;

/**
 * 백준 16236번 아기 상어
 */

public class Main16236_아기상어 {
	
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    
    static int[] shark = null;
    static int N;
    static int size = 2;
    static int eat = 0; // 먹은 물고기 수
    static int move = 0; // 움직인 총 거리

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                
                if (map[i][j] == 9) {	// 아기 상어 위치 저장 
                    shark = new int[]{i, j};
                    map[i][j] = 0;
                }
            }
        }

        while (bfs()) {
        	if (size == eat) { // 사이즈와 먹이를 먹은 수가 동일하다면 상어의 크기 증가
    		    size++;
    		    eat = 0;
    		}
        }

        System.out.println(move);
    }

    public static boolean bfs() {
    	PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2] != o2[2]) {	// 이동한 거리가 다르면 이동한 거리순으로 정렬 
					return Integer.compare(o1[2], o2[2]);
				} else {				// 이동한 거리가 같으면 
					if(o1[0] != o2[0]) {	// r을 기준으로 정렬 
						return Integer.compare(o1[0], o2[0]);
					} else {				// r이 같으면 c 기준으로 정렬 
						return Integer.compare(o1[1], o2[1]);
					}
				}
			}
		});
		boolean[][] visit = new boolean[N][N];
		
		queue.add(new int[]{shark[0], shark[1], 0}); // r, c, 이동한 거리
		visit[shark[0]][shark[1]] = true;
		
		boolean check = false; // 상어가 먹이를 먹었는지 체크할 변수
		
		while (!queue.isEmpty()) {
		    shark = queue.poll();
		
		    if (map[shark[0]][shark[1]] != 0 && map[shark[0]][shark[1]] < size) { // 먹이가 있으면서 상어의 사이즈보다 작다면?
		        map[shark[0]][shark[1]] = 0; // 물고기 제거
		        eat++; 
		        move += shark[2]; // 움직인 거리를 총 움직인 거리에 추가
		        check = true; // 먹이 먹었다고 체크
		        break;
		    }
		
		    for (int k = 0; k < 4; k++) {
		        int nr = shark[0] + dr[k];
		        int nc = shark[1] + dc[k];
		
		        if (nr < 0 || nc < 0 || nc >= N || nr >= N || visit[nr][nc] || map[nr][nc] > size)
		            continue;
		
		        queue.add(new int[]{nr, nc, shark[2] + 1});
		        visit[nr][nc] = true;
		    }
		}
		
		return check;	// 먹이를 먹은 적이 없다면, 더 이상 먹은 물고기가 없으므로 탈출
    }
}