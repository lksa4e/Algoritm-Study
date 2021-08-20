package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * M을 3, L을 2라고 하드코딩 해놓고 맞는데 왜 틀리지 이러고 있다;;
 * 정신줄 붙잡고 문제풀자
 *
 * 홀수번 받은 상태면 시계방향 : (ball+L)%N;
 * 짝수번 받은 상태면 반시계방향으로 공을 던짐 : ball = (ball-L+N)%N;
 * 공의 인덱스 컨트롤을 잘해야 하고 받은 횟수가 M인 사람이 생길 때 까지 while문을 돌다가 게임이 종료되면 그 때까지 던진 총 횟수를 구함 
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
