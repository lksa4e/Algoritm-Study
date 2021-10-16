import java.io.*;
import java.util.*;

/**
 * G2 BOJ 15684 사다리 조작
 * 메모리 : 17340kb 시간 : 1304ms
 * 
 * 모든 사다리를 놔보면서 진행해야 하는 완전탐색 문제 (dfs)
 * A 번째 가로선에서 B-B+1 사이의 가로선이 놓아진 경우 visit[A][B] = true로 설정한다.
 * 임의의  x,y 위치(x 가로선, y세로선)에서 사다리를 놓을 수 있는 경우는
 * !visit[a][b-1] && !visit[a][b] && !visit[a][b+1] 이다.
 * 
 */
public class BOJ15684_G4_사다리조작 {
	static int N,M,H, answer = Integer.MAX_VALUE;
	static boolean visit[][];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		visit = new boolean[H+2][N+2];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			visit[a][b] = true;
		}
		
		make_ladder(1,0);
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
	static void make_ladder(int idx, int cnt) {
		// 최대 놓을 수 있는 사다리 개수 3
		if(cnt == 4) return;
		
		// 현재까지 놓은 사다리로 1->1, 2->2 충족하는지 확인 
		if(play()) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		// 맨 위부터 놓을 수 있는 모든 사다리를 고려하면서 진행
		for(int i = idx; i <= H; i++) {
			for(int j = 1; j < N; j++) {
				// 현재 위치에서 사다리를 놓을 수 있는지 check
				if (visit[i][j] == true) continue;
	            if (visit[i][j-1] == true) continue;
	            if (visit[i][j+1] == true) continue;
	            
	            // 사다리를 놓고 다음 dfs 진행
	            visit[i][j] = true;
	            make_ladder(i, cnt + 1);
	            visit[i][j] = false;
			}
		}
	}
	
	// 현재까지 놓아진 사다리로 1->1 , 2->2, 3->3 조건이 충족하는지를 검사
	static boolean play() {
		for(int i = 1; i <= N; i++) {
			int cur_num = i;
			for(int j = 1; j <= H; j++) {
				// j 가로선에서 cur_num -> cur_num + 1이 연결되어있다면 cur_num++;
				if(visit[j][cur_num]) cur_num++;
				// j 가로선에서 cur_num - 1 -> cur_num이 연결되어있다면 cur_num--;
				else if(visit[j][cur_num - 1]) cur_num--;
			}
			// 도착했을 때 시작 index와 도착 index가 다르면 false
			if(cur_num != i) return false;
		}
		return true;
	}
}
