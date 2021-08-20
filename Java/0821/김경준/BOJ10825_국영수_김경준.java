import java.io.*;
import java.util.*;
/**
 * BOJ 10825 국영수 : https://www.acmicpc.net/problem/10825
 * Comparable or Comparator 문제.. 해설 불필요해보임
 * 
 */

public class BOJ10825_국영수_김경준 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, answer = 0;
	static Kys[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new Kys[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Kys(st.nextToken(), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(arr);
		for(Kys k : arr) {
			sb.append(k.name + "\n");
		}
		System.out.print(sb);
		
	}
}
class Kys implements Comparable<Kys>{
	String name;
	int k,y,s;
	public Kys(String name,int k,int y, int s) {
		this.name=name;
		this.k = k;
		this.y = y;
		this.s = s;
	}
	@Override
	public int compareTo(Kys o) {
		if(k == o.k) {
			if(y == o.y) {
				if(s == o.s) 
					return name.compareTo(o.name);
				else return o.s - s;
			}else return y - o.y;
		}else return o.k - k;
	}
	
}
