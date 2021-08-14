import java.io.*;
import java.util.*;
/**
 * 문제 링크 : https://www.acmicpc.net/problem/9019
 * 경로 추적 방식 풀이
 * start -> !!!! -> @@@@ -> #### -> $$$$ -> target 경로로 가는 경우에
 *           D   ->   D  ->  S   ->  L   ->  R     명령어를 통해 간 경우
 * BFS를 통해 탐색하면서 다음 숫자가 어떤 숫자를 통해 왔는지 저장한다.
 * before[!!!!] = start;
 * before[@@@@] = !!!!;
 * 그리고 각 경로마다 어떤 명령어를 통해 왔는지를 저장해 준다.
 * path[!!!!] = D
 * path[@@@@] = D 
 * 
 * before[target]에는 이전 경로가 저장되어 있다. 따라서 아래의 while문을 통해 target에서 시작하여 start 지점을 중간 경로를 탐색하며 갈 수 있다.
 * while(target != start){
 *    sb.append(path[target])   // 탐색하면서 명령어도 저장하기
 * 	  target = before[target]
 * }
 * 이어붙인 명령어는 뒤집어진 순서이므로, 출력할 때 뒤집어서 원상복구한다.
 */


public class P9019_2 {
	static StringBuilder sb;
	static StringTokenizer st;
	static int N;
	static int origin, target;
	static int before[] = new int[10000];
	static char path[] = new char[10000];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			origin = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());
			Arrays.fill(before, -1);
			bfs();
			solve();
		}
	}
	static void solve() {
		sb = new StringBuilder();
		while(target != origin) {
			sb.append(path[target]);
			target = before[target];
		}
		System.out.println(sb.reverse());
	}
	static void bfs() {
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(origin);
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			int dnum = (cur*2) % 10000;
			if(before[dnum] == -1) {
				before[dnum] = cur;
				path[dnum] = 'D';
				q.offer(dnum);
			}
			
			int snum = cur - 1 >= 0 ? cur -1 : 9999;
			if(before[snum] == -1) {
				before[snum] = cur;
				path[snum] = 'S';
				q.offer(snum);
			}
			
			int lnum = (cur % 1000) * 10 + (cur / 1000);
			if(before[lnum] == -1) {
				before[lnum] = cur;
				path[lnum] = 'L';
				q.offer(lnum);
			}
			
			int rnum = (cur % 10) * 1000 + (cur / 10);
			if(before[rnum] == -1) {
				before[rnum] = cur;
				path[rnum] = 'R';
				q.offer(rnum);
			}
			if(before[target] != -1) return;
		}
		
	}
}
