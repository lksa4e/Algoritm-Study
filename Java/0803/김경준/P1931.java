import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1931 {
	static int N, start_time, end_time;
	static List<Pair> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			start_time = Integer.parseInt(st.nextToken());
			end_time = Integer.parseInt(st.nextToken());
			list.add(new Pair(start_time, end_time));
		}
		Collections.sort(list);
		int cur_time = 0, cnt = 0;
		for(Pair meeting : list) {
			if(cur_time <= meeting.x) {
				cur_time = meeting.y;
				cnt++;
			}
		}
		System.out.print(cnt);
	}
}

class Pair implements Comparable<Pair>{
	public int x;
	public int y;
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Pair p2) {
		if(y == p2.y) return Integer.compare(x, p2.x);
		else return Integer.compare(y, p2.y);
	}
}