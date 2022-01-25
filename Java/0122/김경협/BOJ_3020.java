import java.io.*;
import java.util.*;

/*
 * BOJ 3020번 개똥벌레
 * 
 * 누적 합을 구하는 방법.
 * 
 * 첫 번째 아이디어. H짜리 배열을 준비해 놓고 길이가 3인 석순이 있으면
 * 밑에서 3칸까지 +1씩 해주고 5인 종유석이 있으면 위에서 5칸을 모두 +1 해준다.
 * --> N(500000 * 200000)
 * 
 * 두번째 아이디어,
 * 석순 배열과 종유석 배열을 준비, 3짜리 석순이 있으면 석순 배열의 3번째 위치를 +1한다.
 * 5짜리 종유석이 있으면 5번째 위치를 +1한다.
 * 맨 마지막에 제일 위에서부터 내려오면서 아래층에 자기 값을 더해준다.
 * 석순 배열과 종유석 배열을 합치고 최솟값을 찾는다.
 * --> N(500000 * 4)
 * 
 * 30688KB	360ms
 */

public class BOJ_3020 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[] toTop = new int[H], toBottom = new int[H];	// 석순, 종유석 배열
		
		for(int i = 0; i < N; i++) {
			if(i % 2 == 0) {	// 번갈아가면서 각 높이에 맞춰 저장
				toTop[Integer.parseInt(br.readLine()) - 1]++;
			} else
				toBottom[Integer.parseInt(br.readLine()) - 1]++;
		}
		
		getSum(toTop);
		getSum(toBottom);	// 각각의 누적합 계산
		
		getSum(toTop, toBottom);	// 석순 배열, 종유석 배열 합치기
		
		int min = Integer.MAX_VALUE, cnt = 0;
		
		for(int i = 0; i < H; i++) {	// 최솟값 찾기
			if(toTop[i] < min) {
				cnt = 1;
				min = toTop[i];
			} else if(toTop[i] == min) {
				cnt++;
			}
		}
		
		sb.append(min).append(" ").append(cnt);
		System.out.println(sb);
		
	}
	
	static void getSum(int[] arr) {
		for(int i = arr.length - 1; i > 0; i--) {	// 위에서부터, 아래층으로 자기값을 누적해서 넘겨줌
			arr[i - 1] += arr[i];
		}
	}
	
	// 석순 종유석 합치기
	static void getSum(int[] toTop, int[] toBottom) {
		for(int i = 0, size = toTop.length; i < size; i++) {
			toTop[i] += toBottom[size - i - 1];
		}
	}

}
