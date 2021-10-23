import java.io.*;
import java.util.*;

/**
 * G5 BOJ 16234 인구이동
 * 메모리 : 297228kb 시간 : 772ms
 * 
 * 1. 2차원 배열을 탐색하며 BFS 수행
 *   -> BFS 수행하며 연합이 된 경우 연합을 number로 표시
 *   -> BFS 수행 도중 거쳐간 인구수 합을 list의 number 인덱스에 저장 
 * 
 */

public class BOJ16234_G5_인구이동 {
	static int N,L,R; // 인구수, 인구이동 범위
	static boolean visit[][];
	static int map[][], check_map[][];
	static Map<Integer, Integer> area_avg = new HashMap<Integer, Integer>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		visit = new boolean[N][N];
		check_map = new int[N][N];
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		while(true) {
			
			// visit 배열과 map 초기화
			clear();
			
			// 만약 연합 구성할 수 있으면(인구이동 가능하면) cnt 증가, 없으면 break 
			if(make_union()) cnt++;
			else break;
		}
		
		System.out.println(cnt);
	}
	// 2차원 배열을 탐색하면서 BFS 수행해보기
	static boolean make_union(){
		int num = 1;
		boolean result = false;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visit[i][j]) {
					// i,j 위치 기준으로 bfs 수행하고, 만들어진 연합의 번호를 num으로 붙임
					// 인구 이동이 발생하면 result를 true로 만들어줌
					if(bfs(i,j, map[i][j], num++)) result = true;					
				}
			}
		}
		
		// BFS 수행 후 연합의 땅의 인구를 통일시킴
		make_one_area();
		return result;
	}
	static boolean bfs(int x, int y, int area, int num) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {x,y, area});
		visit[x][y] = true;
		check_map[x][y] = num;
		
		int area_sum = 0; // 인구수의 합
		int cnt = 0;  // 연합한 나라의 수
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cur_x = cur[0], cur_y = cur[1], cur_area = cur[2];
			
			area_sum += cur_area;
			cnt++;
			
			for(int i = 0; i < 4; i++) {
				int nx = cur_x + ("2110".charAt(i) -'1');
				int ny = cur_y + ("1201".charAt(i) -'1');
				if(oob(nx,ny)) continue;
				if(visit[nx][ny]) continue;
				int gap = Math.abs(map[nx][ny] - map[cur_x][cur_y]);
				if(L <= gap && gap <= R) {
					check_map[nx][ny] = num;
					visit[nx][ny] = true;
					q.offer(new int[] {nx,ny,map[nx][ny]});
				}
			}
		}
		
		// 연합 인구의 num 인덱스에 평균 인구수를 저장해둠 
		int avg_num = area_sum / cnt;
		area_avg.put(num, avg_num);
		
		// 연합나라가 1개면 인구이동 X -> return false
		if(cnt == 1) return false;
		else return true;
	}
	
	// 인구이동 일어난 이후 map에 인구 맞춰주기
	static void make_one_area() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = area_avg.get(check_map[i][j]);
			}
		}
	}
	
	static void clear() {
		for(int i = 0; i < N; i++) {
			Arrays.fill(check_map[i], 0);
			Arrays.fill(visit[i], false);
		}
	}
	static boolean oob(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
