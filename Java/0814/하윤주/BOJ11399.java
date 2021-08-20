import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [0810] 백준 11399 ATM
 * 대기 시간을 최소화하는 방법
 * 
 * sol)
 * 전체 대기 시간을 최소화하기 위해 빨리 끝나는 작업을 우선적으로 수행
 * SJF(Shortest Job First)
 * 작업시간이 짧은 순서대로 배열을 정렬한 뒤, 대기하는 사람 수만큼 곱함
 * 
 * time_complex)
 * 내장 정렬함수(퀵소트) - O(nlogn) ~ O(n^2)
 */

public class BOJ11399 {
	static int N, minTime;
	static int[] time;      // 전체 작업 시간 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		time = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		lineUp();
		System.out.println(minTime);
		
	}
	
	// 작업시간이 짧은 순서대로 대기 시간 구하기
	private static void lineUp() {
		Arrays.sort(time);
		
		for (int i=0; i<N; i++) {
			int count = N - i;
			minTime += time[i] * count;    // 총 대기시간 = 작업 시간 * 대기자 수
		}
		
	}

}
