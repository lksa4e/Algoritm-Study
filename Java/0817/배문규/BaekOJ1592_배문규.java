package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제를 풀 때 M을 3, L을 2라고 하드코딩 해놓고 맞는데 왜 틀리지 이러고 있다;;
 * 정신줄 붙잡고 문제풀자
 * 
 * 메모리		시간
 * 14212	136
 */

public class BaekOJ1592_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N, M, L;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		int[] friend = new int[N];
		// 첫번째 친구부터 시작
		int ball = 0;
		// 던진 횟수
		int total = 0;
		// 첫번째 친구는 일단 받고 시작
		friend[ball]++;
		
		// 공을 M번 받은 친구가 나타날 때 까지 반복
		while(friend[ball] < M) {
			// 짝수 번 받았으면 L만큼 반시계로 던짐
			if(friend[ball] % 2 == 0) ball = (ball-L+N)%N;
			// 홀수 번 받았으면 L만큼 시계방향으로 던짐
			else ball = (ball+L)%N;
			
			// 던진 횟수 ++
			total++;
			// 받은 횟수 ++
			friend[ball]++;
		}
		
		System.out.println(total);
	}

}
