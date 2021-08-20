package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 케익의 최대 길이가 1000으로 짧아서 최대 길이의 배열을 선언해도 되겠다고 생각함.
 * 가장 많이 받길 희망 하는 사람은 입력값으로 바로 희망 길이의 최대값을 구하고 이전까지의 최대값과 비교하여 인덱스를 구함
 * 실제 가장 많이 받는 사람은 앞순위 사람부터 선점적으로 케익 배열에 희망 조각을 마킹하고 카운트를 하여 최대값을 갱신하는 방법으로 문제를 품
 * 
 * 메모리		시간
 * 16092	160
 */

public class BaekOJ3985_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int L, N;
	static int _maxR = Integer.MIN_VALUE;
	static int _maxH = Integer.MIN_VALUE;
	static int real;
	static int hope;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		L = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		// 케익의 최대 길이가 1000인데, 희망 위치가 1000까지 입력들어오니까 1001로 선언
		int[] cake = new int[1001];

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			// 희망 위치 범위 from ~ to
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 희망 범위의 최대값을 찾고 인덱스 저장
			if(to-from > _maxH) {
				hope = i;
				_maxH = to-from;
			}
			
			// 케익 배열에 앞에 사람부터 선점적으로 마킹하고 마킹한 수를 셈
			int cnt = 0;
			for(int j = from; j <= to; j++) {
				if(cake[j] == 0) {
					cake[j] = i;
					cnt += 1;
				}
			}
			
			// 마킹 수가 제일 많다면 실제로 케익을 가장 많이 받는 사람이므로 
			// 비교용 최대 마킹수와 인덱스 저장
			if(cnt > _maxR) {
				real = i;
				_maxR = cnt;
			}
		}
		
		System.out.println(hope+"\n"+real);
	}

}