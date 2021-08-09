package study.date0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekOJ10814 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
		
	public static void main(String[] args) throws NumberFormatException, IOException {
			
		N = Integer.parseInt(br.readLine());
		// 2차원 스트링배열로 각 행을 split으로 나눈 스트링배열로 입력받음
		String[][] list = new String[N][];
		for(int i = 0; i < N; i++) list[i] = br.readLine().split(" ");
		
		// 람다식으로 list배열의 첫번째 요소들로 값을 기준으로 정렬 -> 값이 같을땐 자동으로 앞의 인덱스가 앞에 위치
		Arrays.sort(list, (l1, l2) -> {return Integer.parseInt(l1[0]) - Integer.parseInt(l2[0]);});
		
		// StringBuilder로 나이순으로 정렬된 객체들을 담아서 출력
		for(int i = 0; i < N; i++) sb.append(list[i][0]).append(" ").append(list[i][1]).append("\n");
		
		System.out.println(sb.toString());
	}

}