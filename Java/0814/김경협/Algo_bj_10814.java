package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Member{
	int age;
	String name;
	
	Member(int age, String name) {
		this.age = age;
		this.name = name;
	}
}

public class Algo_bj_10814 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Member[] list = new Member[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer tk = new StringTokenizer(br.readLine(), " ");
			list[i] = new Member(Integer.parseInt(tk.nextToken()), tk.nextToken());
		}
		
		// 들어온 순서대로는 정렬되어 있으니 나이 순대로만 정렬
		Arrays.sort(list, (a,b) -> {return a.age - b.age;});
		
		StringBuilder sb= new StringBuilder();
		for(Member m : list)
			sb.append(m.age).append(" ").append(m.name).append("\n");
		
		System.out.println(sb);
		
	}

}
