import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P11650 {
	static int N, M;
	static int[] arr;
	
	static boolean cmp(Pair p1, Pair p2) {
		if(p1.x == p2.x) return p1.y < p2.y;
		else return p1.x < p2.x;
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		List<Pair> list = new ArrayList<Pair>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int fst = Integer.parseInt(st.nextToken());
			int snd = Integer.parseInt(st.nextToken());
			list.add(new Pair(fst,snd));
		}
		Collections.sort(list);
		for(Pair p : list) {
			System.out.println(p.x + " " + p.y);
		}
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
		if(x == p2.x) return Integer.compare(y, p2.y);
		else return Integer.compare(x, p2.x);
	}
}
