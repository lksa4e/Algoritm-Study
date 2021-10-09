import java.io.*;
import java.util.*;

/**
 * 백준 19237번 어른 상어
 * 
 * 풀이 : DFS + 구현 
 * 
 * 1. 상어들 이동 (번호가 작은 순서부터 이동)
 * 		1) 우선순위 순으로 무취인 곳 탐색 -> 있으면 이동 
 * 		2) 무취인 곳이 없으면 우선순위 순으로 자기 냄새가 있는 곳으로 이동 
 * 		3) 만약 이미 번호가 작은 상어가 위치해있으면 해당 상어 삭제 
 * 2. 맵에 이미 뿌려져있던 냄새 카운트 1개 감소
 * 3. 이동한 상어들 냄새 뿌리기 
 * 
 * 시행착오)	문제가 길어서 로직 순서를 파악하기 어려웠다..
 * 
 * 20844KB	232ms
 */
public class Main19237_어른상어 {

	static int N, M, k;
	static Smell[][] map;
	static List<Shark> sharkList = new ArrayList<Shark>();
	static int[][][] sharkDir;
	
	static int[] dr = {-1,1,0,0};	// 상하좌우 
	static int[] dc = {0,0,-1,1};
	
	static class Shark implements Comparable<Shark> {
		int r, c;	// 상어 위치 
		int dir;	// 상어 방향 
		int num;	// 상어 번호 
		
		public Shark(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}

		@Override
		public int compareTo(Shark o) {
			return this.num - o.num;
		}
	}
	
	static class Smell {
		int num;	// 몇번째 상어의 냄새인지 
		int cnt;	// 몇번 이동 후 사라지는지 
		
		public Smell(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        map = new Smell[N][N];
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(num != 0) {	// 상어가 있으면 냄새 뿌리기 
					sharkList.add(new Shark(i, j, num));
					map[i][j] = new Smell(num, k);	
				} else {
					map[i][j] = new Smell(0, 0);
				}
			}
		}
        
        // 상어 번호별로 오름차순 정렬 
        Collections.sort(sharkList);
        
        // 상어들 초기 방향 입력 
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
        	sharkList.get(i).dir = Integer.parseInt(st.nextToken()) - 1;
		}
        
        // 상어들의 방향별 우선순위 방향 입력 
        sharkDir = new int[M+1][4][4];
        for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {	// 상하좌우별 우선순위 입력
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < 4; j2++) {
					sharkDir[i][j][j2] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
        
        dfs(0);
    }

	private static void dfs(int time) {
		if(time > 1000) {	// 1000초를 넘으면 -1 출력하고 종료 
			System.out.println(-1);
			return;
		}
		
		if(sharkList.size() == 1) {	// 상어가 1번 한 마리만 남아있는 경우 시간 출력
			System.out.println(time);
			return;
		}
		
		// 1. 상어들 이동하기 (번호가 작은 상어부터 이동)
		int index = 0;
		int size = sharkList.size();
		int[][] temp = new int[N][N];	// 상어들이 이동할 위치 저장 
		
		for (int i = 0; i < size; i++) {
			boolean isMovable = false;
			int r = sharkList.get(index).r;
			int c = sharkList.get(index).c;
			int dir = sharkList.get(index).dir;
			int num = sharkList.get(index).num;
			
			// 무취인 곳 우선순위에 맞춰 사방탐색 
			int nr = r, nc = c, ndir = dir;
			for (int j = 0; j < 4; j++) {
				ndir = sharkDir[num][dir][j];
				nr = r + dr[ndir];
				nc = c + dc[ndir];
				
				// 경계 안에 있고 무취이면 이동 
				if(!isOOB(nr,nc) && map[nr][nc].num == 0) {
					isMovable = true;
					break;
				}
			}
			
			if(!isMovable) {	// 무취인 곳이 없으면 자신의 냄새가 나는 곳으로 이동 
				for (int j = 0; j < 4; j++) {
					ndir = sharkDir[num][dir][j];
					nr = r + dr[ndir];
					nc = c + dc[ndir];
					
					if(!isOOB(nr,nc) && map[nr][nc].num == num) {
						break;
					}
				}
			}
			
			if(temp[nr][nc] != 0) {	// 이미 번호가 낮은 상어가 위치해있는 경우 상어 삭제 
				sharkList.remove(index);
			} else {	// 아무도 없으면 이동
				sharkList.get(index).r = nr;
				sharkList.get(index).c = nc;
				sharkList.get(index).dir = ndir;
				
				temp[nr][nc] = num;
				index++;	// 다음 상어로 변경 
			}			
		}
		
		// 2. 냄새 한칸 지우기 
		decreaseSmell();
		
		// 3. 이동한 상어들 표시하고 냄새 뿌리기 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(temp[i][j] != 0) {
					map[i][j].num = temp[i][j];
					map[i][j].cnt = k;
				}
			}
		}
		
		dfs(time+1);	// 1초 후 다음 로직 수행 
	}

	// 1초 경과 후 냄새 한칸 줄이는 함수 
	private static void decreaseSmell() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j].num != 0) {
					map[i][j].cnt -= 1;
					
					if(map[i][j].cnt == 0) {	// 냄새가 사라졌으면 상어 번호 지우기 
						map[i][j].num = 0;
					}
				}
			}
		}
	}
    
	// 경계값 체크해주는 함수 
	private static boolean isOOB(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}