import java.io.*;
import java.util.*;
/**
 * BOJ 2110 공유기 설치 : 
 * 메모리 : 23256KB 시간 : 272ms
 * 
 * 직관적으로 생각했을 때 가장 인접한 두 공유기 사이의 거리가 최대가 되도록 하려면 모든 공유기의 간격이 동일하도록 놓는 것이 최선이다.
 * 하지만 문제에서 집의 간격이 듬성듬성 있기 때문에 위와 같이 간격이 동일하게 공유기를 배치하는 것은 불가능하다.
 * 
 * 1.매번 배치 가능한 위치 중 간격이 넓게 되는 위치를 선택해서 공유기를 배치 -> 그 후 다음 공유기 배치
 * 하지만 이런 방법은 공유기를 배치하기 위해 각 지점에서 기존 공유기와의 거리 계산이 필요하기 때문에 최소 N번 이상의 연산이 필요하다. 
 * 그리고 설치할 공유기의 개수는 최대 N이기 때문에 모든 공유기를 설치하기 위해서 필요한 연산은 20만 * 20만으로 한참 시간을 초과하게 된다.
 * 
 * 2.간격을 기준으로 잡고 매 간격마다 공유기를 배치한 후 C개를 설치되었는지 확인
 * A 간격으로 설치하는 경우 가장 인접한 두 공유기 사이의 최대 거리는 A가 될것이다. 그렇다면 우리가 구해야 하는 값은 공유기를 설치하는 간격 A 중
 * 가장 큰 값을 구하는 것이다. 집의 좌표의 범위는 0 ~ 10억이므로 간격의 범위 또한 0 ~ 10억이 될 것이다. A의 최대값을 구하기 위해 
 * 완전탐색을 수행한다면, 시간을 거뜬히 넘어버리게 된다. -> 파라메트릭 서치 활용
 * 
 * 간격 A를 기준으로 공유기를 배치하는 시뮬레이션을 만든다. 이전의 공유기와 현재 공유기의 간격이 A보다 작은 경우 공유기를 배치하지 않고, 
 * A보다 크거나 같은 경우 공유기를 배치한다.
 * 만약 설치한 공유기의 개수가 목표로 하는 C개보다 적은 경우 더 많은 공유기를 설치해야 하므로 간격을 줄인다.
 * 만약 설치한 공유기의 개수가 목표로 하는 C개보다 많거나 같은 경우 공유기를 적게 설치해야 하므로 간격을 늘린다.
 * 
 * 
 */

public class BOJ2110_S1_공유기설치 {
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, C, arr[], answer = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int left = 1;
		int right = arr[arr.length - 1];
		
		
		while(left <= right) {
			int mid = (left + right) / 2;   // 공유기의 설치 간격
			int count = 1, prev = arr[0];
			for(int i = 1; i < N; i++) {
				if(arr[i] - prev >= mid) {
					count++;
					prev = arr[i];
				}
			}
			
			if(count < C) right = mid - 1;
			else{
				if(count == C) answer = mid;
				left = mid + 1;
			}
		}
		System.out.print(answer);
	}
}
