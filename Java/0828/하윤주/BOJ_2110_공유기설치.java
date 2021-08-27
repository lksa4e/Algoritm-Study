import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * [0828] BOJ 2110 공유기 설치
 * 이분 탐색, 파라메트릭 서치
 * 
 * sol)
 * 순차탐색 불가능(200,000C100,000은 택도 없음) -> 이분탐색, 파라메트릭 서치
 * 
 * 이분 탐색 개념에서처럼 mid 값을 변경하면서 첫번째 집에서부터 mid 값만큼씩 간격이 떨어진 집에 와이파이를 설치할 경우 와이파이가 몇 개 설치되는지 확인
 * 만약 목표값보다 많이 설치되면 (from=mid+1), 목표값보다 적게 설치되면 (to=mid-1)을 해보며 최적이며 최대의 간격을 구함
 * 
 * 파라메트릭 서치를 적용 : 최적값(최대 거리)을 구하는 문제를 범위를 바꿔보며 가능한지 확인하는 결정 문제로 바꿀 수 있다는 점에서 적합함
 * 
 * 시행착오)
 * 1. installWifi() 함수에서 현재 집에서부터 mid 간격만큼 떨어진 위치가 다음 집 위치보다 작거나 같으면 cctv를 설치
 * 		- 처음에는 다음 집에서 현재 집까지의 간격이 mid 보다 크면 해당 다음 집에 cctv를 설치하는 식으로 구현함
 * 		- 이렇게 하면 최적이며 최대인 거리를 구할 수 없고 지금 구한 mid 간격이 cctv를 설치하기에 적합한지만 알 수 있음
 * 
 * 2. from을 1로 설정하지 않은 문제
 * 		- mid 값을 구하기 위해 from은 h[0]~h[1]의 간격으로, to는 h[0]~h[N-1]의 간격으로 설정했었음.
 * 		- 간격을 1부터 시작할 경우 탐색을 더 많이 하게되니까 나름 탐색을 줄이겠다는 마음으로 그랬나봄
 * 		- 하지만 h[0]~h[1]의 간격이 집들의 간격 중 최솟값이 아닐 수 있기때문에 적합한 탐색 범위가 설정되지 않음 
 * 		-> 간격의 최솟값이 될 수 있는 확실한 값인 1로 설정해야함
 * 
 * tc)
 * O(log N)
 *
 */

public class BOJ_2110_공유기설치 {
	static int N, C, answer, from, to;
	static int[] home;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 전체 집의 위치
		home = new int[N];
		for (int i=0; i<N; i++) home[i] = Integer.parseInt(br.readLine());
		
		// 이분 탐색을 위한 오름차순 정렬
		Arrays.sort(home);
		
		// 이분 탐색 mid 값 설정을 위한 최소 거리와 최대 거리
		from = 1;
		to = home[N-1] - home[0];
		
		System.out.println(parametricSearch());
	}

	// 파라메트릭 서치를 하며 최적의 간격 찾기
	private static int parametricSearch() {
		
		while(from<=to) {   // 탈출조건
			int mid = (from+to) / 2;
			
			if (installWifi(mid)) {      // 만약 mid 간격만큼 와이파이 설치한 것이 목표 와이파이 개수보다 많으면
				answer = mid;            // 우선 그 중의 최솟값을 기억하고
				from = mid+1;            // 그보다 더 큰 최적값이 있는지 탐색
			} else {                     // 만약 설치한 와이파이가 목표 와이파이 개수보다 적으면
				to = mid-1;              // 간격을 축소시켜 탐색
			}
		}
		
		return answer;       // 최적값 산출
	}
	
	// 전체 집을 순회하며 mid 간격만큼 떨어진 위치에 와이파이 설치 시 몇 개 설치되는지 확인
	private static boolean installWifi(int mid) {
		int cnt = 1;                             // 첫번째 집에는 무조건 와이파이 설치
		int startHome = home[0];
		
		for (int i=1; i<N; i++) {
			int curHome = home[i];
			
			if (startHome+mid <= curHome) {      // 현재 집에서 mid 간격 떨어진 위치가 다음 집 위치보다 가깝거나 같으면
				startHome = curHome;
				cnt++;                           // 와이파이 설치 가능
			} 
		}
		
		if (cnt >= C) return true;
		return false;
	}

}
