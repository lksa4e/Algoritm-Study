import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *   BOJ 2110번 공유기 설치
 *  
 *   N개의 집의 좌표가 주어지고, C개의 와이파이를 설치해야한다.
 *   이 때, C개를 모두 설치하면서 가장 가까운 와이파이의 거리가
 *   최대가 되도록하는 거리를 구하는 문제.
 *   
 *   Parametric Search를 사용해서 와이파이의 거리를 특정하는 문제이다.
 *   어떻게 와이파이를 설치하는지만 해결하면 이분탐색과 똑같아서 괜찮았던 문제.
 *   
 */

public class BOJ_2110 {
	static int N, C, home[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		home = new int[N];
		for (int i = 0; i < N; i++) {
			int homeAddr = Integer.parseInt(br.readLine());
			home[i] = homeAddr;
		}
		Arrays.sort(home);
		
		// 여기 high 넘겨주는 값의 최적값이 있는데 못 찾겠음
		System.out.println(parametric_search(0, home[N - 1]));
	}
	
	static int parametric_search(int low, int high) {
		int mid = 0, result = 0;
		while(low <= high) {
			mid = (low + high) / 2;
			if(isInstallable(mid)) {
				result = mid;
				low = mid + 1;
			} else
				high = mid - 1;
		}
		return result;
	}
	
	static boolean isInstallable(int distance) {
		int len = home.length, cnt = 1, addr, i = 0;
		while(i<len) {	// index가 home의 사이즈 보다 커질때까지 반복
			addr = home[i++];// 현재 index의 home 주소 받고, i 증가
			
			// i가 범위 밖에 가지 않는지 && home 주소 + 우리가 최소로 정한 distance 값이
			// 다음 받아올 home의 주소를 넘겨버리면, 최소로 정한 distance 보다는 더 긴 distance로
			// 와이파이를 설치해야하기 때문에 처음 받아온 home + distance 보다 최소한 더 큰
			// home 주소가 나올 때 까지 i를 증가시킨다.
			while (i < len && addr + distance > home[i]) i++;
			if(i>=len) break;
			cnt++;			// 와이파이 설치
			
			if(cnt == C)	// 와이파이가 C만큼 설치됐다면, 성공한 것
				return true;
		}
		// i가 home을 다 들리고도, 현재 distance로는 와이파이를 전부 설치 못한 경우, false
		return false;
	}
	
}
