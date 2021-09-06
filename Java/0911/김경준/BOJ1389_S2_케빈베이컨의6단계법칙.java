import java.io.*;
import java.util.*;

/**
 * S1 BOJ 1389 케빈 베이컨의 6단계 법칙:
 * 메모리 : 14112kb 시간 : 136ms
 * 
 * 1 -> 2
 *      2 -> 3 이면 1 -> 3이다를 이용하는 플로이드-와샬 문제 
 * 
 * 초기화를 integer.max_value로 잡으면  Math.min(map[i][j], map[i][k] + map[k][j]);
 * 여기서 오버플로우가 발생할 수 있음 -> 99999로 잡음
 * 
 * 
 */

public class BOJ1389_S2_케빈베이컨의6단계법칙 {
	static int N,M;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		
		init();  // 999999, 0 으로 초기화
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fst = Integer.parseInt(st.nextToken());
			int snd = Integer.parseInt(st.nextToken());
			map[fst][snd] = 1; 
			map[snd][fst] = 1;  // A와 B가 친구면 B와 A도 친구
		}
		
		
		solve();
		System.out.print(get_answer());
	}
	// 입력 받기 전 999999로 초기화 + 자기 자신과의 베이컨은 0 
	static void init() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++)
				map[i][j] = (i==j) ? 0 : 999999;
		}
	}

	// 플로이드 와샬 함수
	// i -> j 보다 i -> k -> j 거쳐가는게 빠르다면 갱신
	// 이 문제에서는 케빈 베이컨 게임수가 짧아지면 갱신
	static void solve() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) 
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
			}
		}
	}
	
	// 케빈 베이컨 수가 가장 작은 사람 구하기
	static int get_answer() {
		int answer = Integer.MAX_VALUE;
		int answer_idx = 0;
		for(int i = 1; i <= N; i++) {
			int sum = 0;
			for(int j = 1; j <= N; j++)
				sum += map[i][j];
			
			if(answer > sum) {
				answer = sum;
				answer_idx = i;
			}
		}
		return answer_idx;
	}
}
