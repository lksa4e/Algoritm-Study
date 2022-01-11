import java.util.*;
import java.io.*;

/*
 * BOJ 11054번 가장 긴 바이토닉 부분 수열
 * 
 * 각 숫자별로 오름차순 수와 내림차순 수를 구해준 다음에 둘을 합산했다.
 * 그리고 그 중에서 가장 큰 수가 가장 긴 바이토닉 부분 수열이 된다.
 * 
 * 14900KB	156ms
 */

public class BOJ_11054 {

	static int N, nums[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		nums = new int[N];
		
		for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		System.out.println(solve());
	}

	private static int solve() {
		
		// 오름차순 구하기
		int ascDp[] = new int[N];	// 각 ascDp[i]에는 i번째 숫자가 가지는 오름차순 수가 저장된다.
		for(int i = 0; i < N; i++) {		// i까지의 오름차순에서 i는 업데이트 될 대상
			for(int j = 0; j < i; j++) {	// j는 0~i-1까지 돌면서 알맞은 오름차순 대상을 정한다.
				// 따라서 업데이트하기 위해서는 j번째 숫자가 업데이트 대상이되는 i보다 작아야하고
				// 걔 중에서 가장 큰 오름차순을 구해야하기 때문에 ascDp[j] + 1보다 작으면 계속 업데이트 해서
				// 0~i-1까지 돌면 가질 수 있는 가장 큰 오름차순 수를 얻을 수 있다.
				if(nums[j] < nums[i] && ascDp[i] < ascDp[j] + 1) {
					ascDp[i] = ascDp[j] + 1;
				}
			}
		}
		
		// 내림차순 구하기
		int descDp[] = new int[N];	
		for(int i = N - 1; i >= 0; i--) {		
			for(int j = N - 1; j > i; j--) {	
				
				if(nums[j] < nums[i] && descDp[i] < descDp[j] + 1) {
					descDp[i] = descDp[j] + 1;
				}
			}
		}
		
		// 둘의 합산
		int max = 0;
		for(int i = 0; i < N; i++) {
			max = Math.max(max, ascDp[i] + descDp[i]);
		}
		
		return max + 1;
	}

}
