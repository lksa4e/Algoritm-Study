import java.io.*;
import java.util.*;

/**
 * G5 BOJ 17142 연구소3
 * 메모리 : 31220kb 시간 : 284ms
 * 
 *  
 * 1. X개의 바이러스 중 M개를 활성시키는 xCm의 모든 조합을 구함
 * 2. 각 활성 바이러스 조합으로 바이러스 퍼트리는 BFS를 수행
 * 3. 각 BFS 수행값 중 최소값 구하기
 */

public class BOJ17142_G4_연구소3 {
	static int N,M, virus_cnt, empty_cnt, answer = Integer.MAX_VALUE;
	static int map[][];
	static List<int[]> virus_list = new ArrayList<int[]>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+2][N+2];
		for(int i = 0; i < N+2; i++) Arrays.fill(map[i], 1);
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus_cnt++;
					virus_list.add(new int[] {i,j});
				}else if(map[i][j] == 0) empty_cnt++;
			}
		}
		
		virus_spread();
		
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}
	
	// xCm 구하기 -> 비트마스킹을 이용한 풀이
	// ㅇㅇㅇㅇㅇㅇ 1 => 1 번 바이러스 활성화
	// ㅇㅇㅇㅇㅇ 1 ㅇ => 2 번 바이러스 활성화
	// ㅇㅇㅇㅇㅇ 1 1 => 1,2번 바이러스 활성화
	static void virus_spread() {
		// 2^virus 개수만큼의 조합
		for(int i = 0; i < (1 << virus_cnt); i++) {
			
			// 활성 바이러스 개수 카운트
			int cnt = 0;
			for(int j = 0; j < virus_cnt; j++)
				if((i & (1 << j)) != 0) cnt++;
			
			// 만들어진 조합의 활성 바이러스 개수가 M개가 아니면 continue
			if(cnt != M) continue;
			
			// 모든 바이러스를 퍼트리는데 걸리는 시간 => result
			int result = bfs(i);
			answer = Math.min(answer, result);
		}
	}
	
	// 바이러스 퍼트리는 bfs 함수
	static int bfs(int num) {
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		boolean visit[][] = new boolean[N+2][N+2];
		
		// 선택된 virus M개를 전부 queue에 넣기
		for(int i = 0; i < virus_cnt; i++) {
			if((num & (1 << i)) != 0) {
				q.offer(virus_list.get(i));
				visit[virus_list.get(i)[0]][virus_list.get(i)[1]] = true;
			}
		}
		
		int cnt = 0;
		int emptyCount = empty_cnt;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-->0) {
				int[] cur = q.poll();
				if(map[cur[0]][cur[1]] == 0) emptyCount--; // 빈칸인 경우 빈칸개수 감소
				
				for(int i = 0; i < 4; i++) {
					int nx = cur[0] + ("2110".charAt(i)-'1');
					int ny = cur[1] + ("1201".charAt(i)-'1');
					
					if(visit[nx][ny]) continue;
					if(map[nx][ny] == 1 || map[nx][ny] == -1) continue;
					
					visit[nx][ny] = true;
					q.offer(new int[] {nx,ny});
				}
			}
			// 만약 모든 빈칸에 바이러스를 퍼뜨렸다면 break
			if(emptyCount == 0) break;
			cnt++;
		}
		
		// 모두 퍼뜨렸으면 cnt 리턴, 모두 퍼뜨릴 수 없으면 max_value 리턴
		if(emptyCount != 0) return Integer.MAX_VALUE;
		else return cnt;
	}
}
