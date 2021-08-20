/**
문제 링크 : https://www.acmicpc.net/problem/10814
단순한 정렬 문제
*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P10814 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Pair[] pList = new Pair[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			pList[i] = new Pair(age,name);
		}
		Arrays.sort(pList);
		for(int i = 0; i < N; i++) {
			sb.append(pList[i].x).append(" ").append(pList[i].y).append("\n");
		}
		System.out.println(sb.toString());
	}
}
class Pair implements Comparable<Pair>{
	public int x;
	public String y;
	public Pair(int x, String y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Pair p2) {
		return x - p2.x;
	}
}