import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P16948 {
	static int N;
	static int start_x, start_y, end_x, end_y;
	static Queue<Knight> q = new LinkedList<>();
	static int[] dx = {-2,-2,0,0,2,2};
	static int[] dy = {-1,1,-2,2,-1,1};
	static boolean visit[][];
	static boolean oob(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >=N;
	}
	static int solve() {
		q.add(new Knight(start_x,start_y));
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int cur_x = q.peek().x;
				int cur_y = q.peek().y;
				q.poll();
				if(cur_x == end_x && cur_y == end_y) return cnt;				
				for(int i = 0; i < 6; i++) {
					int nx = cur_x + dx[i];
					int ny = cur_y + dy[i];
					if(!oob(nx,ny)) {
						if(!visit[nx][ny]) {
							visit[nx][ny] = true;
							q.add(new Knight(nx,ny));
						}
					}
				}
			}
			cnt++;
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		visit = new boolean[N+1][N+1];
		st = new StringTokenizer(br.readLine());
		start_x = Integer.parseInt(st.nextToken());
		start_y = Integer.parseInt(st.nextToken());
		end_x = Integer.parseInt(st.nextToken());
		end_y = Integer.parseInt(st.nextToken());		
		System.out.println(solve());
	}
}

class Knight {
	public int x;
	public int y;
	public Knight(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
