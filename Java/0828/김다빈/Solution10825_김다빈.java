package P0821;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 백준 10825번 국영수
 * 
 * 풀이: Comparable 인터페이스를 상속한 클래스 이용
 * 1. 이름, 국영수 점수를 저장하기 위한 Score 클래스 정의
 * 2. Comparable 인터페이스를 상속하여 compareTo 함수를 문제 조건에 맞게 재정의
 * 3. 이름을 사전순으로 정의하기 위해 String의 compareTo 함수 이용
 * 4. Arrays.sort(score)로 정렬 후 이름 출력 
 *
 * 114204KB	1452ms
 */
public class Solution10825_김다빈 {

	public static class Score implements Comparable<Score>{
		String name;
		int korean;
		int english;
		int math;
		
		public Score(String name, int korean, int english, int math) {
			super();
			this.name = name;
			this.korean = korean;
			this.english = english;
			this.math = math;
		}

		@Override
		public int compareTo(Score o) {
			// 국어 점수가 감소하는 순서로 
			if(korean == o.korean) {
				// 국어 점수가 같으면 영어 점수가 증가하는 순서로 
				if(english == o.english) {
					// 국어, 영어 점수가 같으면 수학점수가 감소하는 순서로 
					if(math == o.math) {
						// 이름이 사전 순으로 증가하는 순서로 
						return name.compareTo(o.name);	// 오름차순 
					} else return -(math - o.math);	// 내림차순 
				} else return english- o.english;	// 오름차순 
			} else return -(korean - o.korean);	// 내림차순 
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		// Score 클래스 배열에 저장 
		Score[] score = new Score[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			score[i] = new Score(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(score);
		
		for(int i=0;i<N;i++) {
			sb.append(score[i].name+"\n");
		}
		System.out.println(sb);
	}

}
