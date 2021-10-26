package BaekOJ.study.date1012;

import java.io.*;
import java.util.*;

/*
 * 백준 16236 : 아기 상어
 * 
 * 로직은 생각보다 단순해보이는데 실제 구현까지 머리가 너무 복잡했던 문제...
 *  1. bfs로 최단거리로 먹을 수 있는 생선 일단 다먹음
 *  2. bfs로 거리는 모두 최단거리니까 위-왼쪽 순으로 정렬해서 첫번째 생선을 선택하고 토탈 시간 추가
 *  3. 선택을 하면 선택한 위치에서 다시 1번부터 반복. 먹을 수 있는 생선이 없을 때 까지
 *  
 * 시행착오 1.
 * bfs 특성을 생각해서 bfs 탐색 순서를  위쪽-왼쪽-오른쪽-아래쪽 순서대로 다음번 위치를 큐에 넣는 방법으로 접근을 했었다. 
 * 하지만 만약 현재 위치 C에서 2초 거리인 위치들이 큐에 들어가는 순서는 다음과 같다.
 * ? ? 1 ? ?
 * ? 2 x 3 ?
 * 4 x C x 6
 * ? 5 x 7 ?
 * ? ? 8 ? ?
 * 위 방문 순서를 보면 6이 우선순위가 되어야 하지만, 더 아래에 있는 5가 먼저 탐색되기 때문에 델타 우선 탐색방식만으로는 문제를 풀 수가 없다. 
 * 
 * 메모리 	시간
 * 14464	140
 */

public class BaekOJ16236_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N, map[][], sharkI, sharkJ, size = 2, eat, min, total;
	static int delta[][] = {{-1,0}, {0,-1}, {0,1}, {1,0}};
	static boolean check[][], flag;
	static Queue<int[]> queue = new ArrayDeque<int[]>();
	static List<int[]> fishList = new ArrayList<int[]>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		check = new boolean[N][N];
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 상어 위치를 저장하고 0으로 변경
				if(map[i][j] == 9) {
					sharkI = i;
					sharkJ = j;
					map[sharkI][sharkJ] = 0;
				}
			}
		}
		
		while(true) {
			// 새롭게 최단거리 생선을 찾아야 하므로 큐, 리스트, 체크배열 초기화
			queue.clear();
			fishList.clear();
			for(int x = 0; x < N; x++) Arrays.fill(check[x], false);
			
			// 최단거리 초기화하고 큐에 시작 위치 추가 + 방문 체크
			min = Integer.MAX_VALUE;
			queue.offer(new int[] {sharkI, sharkJ, 0});
			check[sharkI][sharkJ] = true;
			
			while(!queue.isEmpty()) {
				int[] now = queue.poll();
				int i = now[0];
				int j = now[1];
				int dist = now[2];
				
				// 새로 탐색을 시작 할 거리가 현재 생선을 발견한 최단거리와 같거나 멀면 중단 
				if(dist >= min) break;
					
				for(int[] d : delta) {
					int di = i + d[0];
					int dj = j + d[1];
					
					// 맵범위 안이면서, 방문하지 않았고, 지나갈 수 있다면
					if(!isOOB(di, dj) && !check[di][dj] && map[di][dj] <= size) {
						// 방문 체크하고 큐에 추가
						check[di][dj] = true;
						queue.offer(new int[] {di, dj, dist+1});
						
						// 먹을 수 있다면 먹을 수 있는 리스트에 추가하고 먹을 수 있는 생선의 최단거리 갱신
			 			if(map[di][dj] != 0 && map[di][dj] < size) {
			 				fishList.add(new int[] {di, dj, dist+1});
							min = dist+1;
						}
					}
				}
			}
			
			// 먹을 수 있는 생선이 없다면 중단
			if(fishList.size() == 0) break; 
			
			// 1. 가장 위에 있는 물고기 -> 2. 가장 왼쪽에 있는 물고기 순으로 정렬
			fishList.sort((a, b)-> {return a[0] != b[0] ? a[0]-b[0] : a[1]-b[1];}); 
			int[] fish = fishList.get(0);
			// 상어 위치, 거리(==시간) 갱신
			sharkI = fish[0]; 
			sharkJ = fish[1];
			map[sharkI][sharkJ] = 0;
			total += fish[2];
			// 해당 사이즈에서 먹은 생선수가 사이즈와 같다면 사이즈 추가하고, 먹은 생선 초기화
			if(++eat == size) {
				size++;
				eat = 0;
			}
		}
		
		System.out.println(total);
	}
	
	public static boolean isOOB(int i, int j) {
		return i>N-1 || i<0 || j>N-1 || j<0;
	}
}
