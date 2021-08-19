package BaekOJ.study.date0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 바리케이트 3개를 완탐으로 세우고
 * 바이러스를 BFS로 퍼뜨린 뒤
 * 안전지대를 세서 최대 안전지대 수를 구하면 틀리진 않겠다고 생각함
 * 문제는 시간...
 * 
 * N,M <= 8 니까
 * setBarricade(DFS) = (8^2)^3 
 * getSafeZone = 8^2
 * virus(BFS) = 8^2
 * 
 * 8^6 * (8^2 + 8^2) = 2^25 = 33,554,432
 * 더 효율적인 방법이 당연히 있겠지만 딱히 생각안나서 걍 해보니 다행히 시간 안터짐
 * 
 * 메모리	시간
 * 297460	716
 */

public class BaekOJ14502_배문규 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, M, map[][], copy[][], _max, cntVirus;
	static List<int[]> virus;
	static Queue<int[]> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M]; 
		copy = new int[N][M];
		virus = new ArrayList<int[]>(); // 나중에 BFS를 위해 바이러스 인덱스를 저장할 리스트
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.add(new int[] {i,j});
			}
		}
		
		_max = Integer.MIN_VALUE;
		setBarricade(0);
		System.out.println(_max);
	}
	
	public static void setBarricade(int depth) {
		// 바리케이트를 3개 세우면
		if(depth == 3) {
			// 바리케이트 세운 맵 복사
			for(int i = 0; i < N; i++) System.arraycopy(map[i], 0, copy[i], 0, M);
			
			virus(copy); // 바이러스 퍼뜨림
			_max = Math.max(_max, getSafeZone(copy)); // 안전지대 최대 수 구함
			//System.out.println(cntVirus);
			return;
		}
		
		// DFS로 바리케이트 세우기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1; // 세우기
					setBarricade(depth+1); 
					map[i][j] = 0; // 허물기
				}
			}
		}
	}
	
	// 바이러스가 퍼지는 걸 BFS로 구현
	public static void virus(int[][] copy) {
		queue = new ArrayDeque<int[]>();
		for(int[] v : virus) { // 바이러스를 하나씩 꺼냄
			queue.offer(v);
			cntVirus++;
			while(!queue.isEmpty()) {
				int[] idx = queue.poll();
				// 4방으로 퍼뜨릴 수 있으면 퍼뜨리고 BFS
				if(!oob(idx[0]+1, idx[1]) && copy[idx[0]+1][idx[1]] == 0) { copy[idx[0]+1][idx[1]] = 2; queue.offer(new int[]{idx[0]+1, idx[1]}); cntVirus++;}
				if(!oob(idx[0]-1, idx[1]) && copy[idx[0]-1][idx[1]] == 0) { copy[idx[0]-1][idx[1]] = 2; queue.offer(new int[]{idx[0]-1, idx[1]}); cntVirus++;}
				if(!oob(idx[0], idx[1]+1) && copy[idx[0]][idx[1]+1] == 0) { copy[idx[0]][idx[1]+1] = 2; queue.offer(new int[]{idx[0], idx[1]+1}); cntVirus++;}
				if(!oob(idx[0], idx[1]-1) && copy[idx[0]][idx[1]-1] == 0) { copy[idx[0]][idx[1]-1] = 2; queue.offer(new int[]{idx[0], idx[1]-1}); cntVirus++;}
			}
		}
	}
	
	public static boolean oob(int i, int j) {
		if (i>N-1 || i<0 || j>M-1 || j<0) return true;
		else return false;
	}
	
	// 안전지대 카운트
	public static int getSafeZone(int[][] copy) {
		int temp = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copy[i][j] == 0) temp += 1;
			}
		}
		return temp;
	}
}
