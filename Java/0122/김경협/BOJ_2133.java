import java.io.*;

/*
 * BOJ 2133번 타일 채우기
 * 
 * memoization
 * 
 * N이 짝수일 경우에만 성립한다.
 * N = 2일 경우, 3가지 케이스가 있고
 * N이 4, 6, 8부터는 2가지 케이스씩 있다.
 * 
 * 이를 재귀적으로 N에 대해서 
 * 2 과 (N-2)로 나눠질 때의 케이스
 * 4 와 (N-4)로 나눠질 때의 케이스
 * ...
 * 를 계산한다.
 * 각 N-2, N-4 ... 등은 다시 재귀적으로 들어가서 위의 상황을 반복한다.
 * 그리고 memoization을 사용해 중간중간 값을 저장하여 재사용 한다. 
 * 
 * 	14320KB	128ms 
 */

public class BOJ_2133 {
	static int dp[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		dp = new int[N + 1];
		System.out.println(divideTile(N));
		
	}
	
	static int divideTile(int N) {
		if(N == 2) {	// 기저 조건, N이 2일 때와 0일 때,
			return 3;
		} else if(N == 0) { // 0일 때는 경우의 수가 없기 때문에 1을 리턴해서 곱했을 때를 생각한다.
			return 1;
		}
		
		if(dp[N] != 0) {	// 저장된 값이 있으면 가져간다.
			return dp[N];
		}
		
		int cases = 0;
		
		for(int i = 2; i <= N; i+=2) {
			if(i == 2)	// N = 2일 경우, 3가지 케이스가 있음
				cases += 3 * divideTile(N - i);
			else	// N != 2일 경우 2가지 케이스가 있음
				cases += 2 * divideTile(N - i); 
		}
		
		dp[N] = cases;	// 다음 연산을 위해 값 저장
		return cases;
	}

}
