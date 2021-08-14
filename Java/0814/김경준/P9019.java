import java.io.*;
import java.util.*;
/**
	단순하게 BFS를 탐색하면서 숫자와 명령어를 같이 넣어준다.
 */

public class P9019 {
	static int N;
	static int origin, target;
	static boolean visit[] = new boolean[10000];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			origin = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());
			Arrays.fill(visit, false);
			solve();
		}
	}
	static void solve() {
		Queue<String> q = new ArrayDeque<String>();
		q.add(origin + " " + ".");
		while(!q.isEmpty()) {
			String current[] = q.poll().split(" ");
			int cur = Integer.parseInt(current[0]);
			if(cur == target) {
				System.out.println(current[1].substring(1));
				return;
			}
			
			int dnum = (cur*2) % 10000;
			if(!visit[dnum]) {
				visit[dnum] = true;
				q.add(dnum +" "+ current[1]+"D");
			}
			
			int snum = cur - 1>= 0 ? cur -1 : 9999;
			if(!visit[snum]) {
				visit[snum] = true;
				q.add(snum +" "+ current[1]+"S");				
			}
			
			int lnum = (cur % 1000) * 10 + (cur / 1000);
			if(!visit[lnum]) {
				visit[lnum] = true;
				q.add(lnum +" "+ current[1]+"L");
			}
			
			int rnum = (cur % 10) * 1000 + (cur / 10);
			if(!visit[rnum]) {
				visit[rnum] = true;
				q.add(rnum +" "+ current[1]+"R");
			}
		}
		
	}
}
