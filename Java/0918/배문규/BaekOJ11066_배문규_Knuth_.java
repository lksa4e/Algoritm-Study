package BaekOJ.study.date0921;

/*
 * 아주 제한적인 상황에서 쓰이는 놈이지만 그래도 배웠던 기억이 있어서 흐린눈으로 모른척 지나치기가 어려웠다..
 * 
 * Knuth Optimization O(N^3) => O(N^2)
 * 크누스 최적화는 DP 점화식이 특정 조건을 만족하는 경우 사용할 수 있는 최적화 기법이다.
 * 아마 보더라도 이해가 잘 되지 않는 이론적인 식들은 구글링하면 사람들이 지들도 뭔말인지 모르면서 다 복붙해서 가져오는 내용들이라
 * 별로 의미가 없다고 생각함.
 * 
 * 1) DP 점화식 형태 (1 <= i <= div <= j <= n)
 * dp[i][j] = Integer.MAX_VALUE;
 * for(int div = i; div < j; div++) {
 *	dp[i][j] = Math.min(dp[i][j], dp[i][div] + dp[div+1][j]) + weight[i][j];;
 * }
 * -> 여기서 weight는 i에서 j까지 데이터의 합인 구간합배열 sum[j+1]-sum[i]이라고 할 수 있다.
 * 
 * i부터 j까지의 최솟값을 div로 구간을 나눠서 각 소구간의 합으로 도출하는 딱 지금과 같은 점화식에서 
 * knuth 최적화가 가능하다. 대표적인 DP유형이다보니 파일 합치기 말고도 스토리만 바꾼 비슷한 문제가 혹시 코테에서 나올 수도 있으니 
 * 계속 알아보도록 하자..
 * 
 * 2) a <= b <= c <= d일 때,
 * weight[c][d] <= weight[a][d]; 
 * a부터 d까지 파일의 합이 당연히 c부터 d까지의 파일의 합보다 크거나 같다.
 * 그래서 적용이 가능함. 이 조건은 지금과 같은 구간합 문제에서 웨이트 값이 음수가 아니라는 의미이다. 
 * 그래서 음수 용량이라는 개념이 없는 파일 용량을 가지고 문제로 냈다 보다.  
 * (행렬 곱셈과 같은 경우는 지금과 같은 무조건적 값상승(단조 증가)가 이루어 지지 않으니 주의!)
 * 
 * 3) a <= b <= c <= d일 때,
 * weight[a][c] + weight[b][d] <= weight[a][d] + weight[b][c]; (사각 부등식이라고는 하는데 증명이 아주 복잡한 식임)
 * 지금과 같은 문제를 예시로 위 식을 바라 봤을 때,
 * a = 1, b = 3 , c = 5, d = 7이라고 해보자.
 * weight[a][c] : 1 2 3 4 5
 * weight[b][d] :     3 4 5 6 7 
 * 
 * weight[a][d] : 1 2 3 4 5 6 7
 * weight[b][c] :     3 4 5
 * 두 식 모두 1~7 + 3~5로 동일하다. 즉 단순 양수 구간합과 같은 케이스에서는 만족이 된다.
 * 만약 크누스 최적화를 적용해보고 싶다면 실제로 값을 대입해서 등호를 만족하는지 꼭 체크하길 바람 
 * (이 조건 또한 행렬 곱셈과 같은 경우에는 만족하지 않음)
 * 
 * 만약 조건 2, 3을 만족한다면 3번째 반복문인 
 * for(int div = i; div < j; div++)을 
 * for(int div = knuth[i][j-1]; div <= knuth[i+1][j]; div++)로 바꿔줄 수 있다.
 * 
 * 이는 종합적으로 봤을 때, '상수'번 반복한다고 봐도 무방하기 때문에 O(N^3)을 O(N^2)로 만들어주는 효과가 있다.
 * 
 * 메모리 	시간
 * 34312	352
 */

import java.io.*;
import java.util.*;

public class BaekOJ11066_배문규_Knuth {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, K, file[], sum[], dp[][], knuth[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			K = Integer.parseInt(br.readLine());
			file = new int[K];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < K; i++) file[i] = Integer.parseInt(st.nextToken());
			
			sum = new int[K+1];
			for(int i = 0; i < K; i++) sum[i+1] = sum[i]+file[i];
			
			// 크누스 최적화 배열 
			knuth = new int[K][K];
			// knuth[i][i]에 i를 저장하면 ,i에서 1칸 뒤 j(i+1)로 가려고 할 때
			// knuth[i][j-1] ~  knuth[i+1][j]가
			// knuth[i][i] ~  knuth[i+1][i+1]와 동일하고
			// 이는 i ~ i+1의 값이다.
			for(int i = 0; i < K; i++) knuth[i][i] = i; 
			
			dp = new int[K][K];
			for(int x = 1; x < K; x++) {
				for(int i = 0; i < K-x; i++) {
					int j = i+x;
					dp[i][j] = Integer.MAX_VALUE;
					/*
					 * 첫 시작인 합치는 구간 크기 x가 1일 땐,
					 * knuth[i][j-1] 가 i,
					 * knuth[i][j-1] 는 j의 인덱스가 저장되어있음.
					 * 즉, 처음엔 0~1, 1~2, 2~3 인덱스가 들어가 있다.
					 * 그리고 knuth 배열에 최소값이 되는 분할지점을 저장함.
					 * 
					 * 그러면 다음번 1 ~ 3의 구간의 최소합을 구하려고 하면,
					 * (1 ~ 2구간의 최소값 분할 지점) ~ (2 ~ 3구간의 최소값 분할 지점)
					 * 마찬가지로 1 ~ 4 구간의 최소합도 같은 매커니즘으로 반복을 줄일 수 있다.
					 * 그런데 이 (1 ~ 3구간의 최소값 분할 지점) ~ (2 ~ 4구간의 최소값 분할 지점) 반복 횟수가
					 * 크기가 아무리 커져도 O(1)번 이루어진다고 하더라 
					 * 만약 1 ~ 3구간의 최소값 분할 지점이 1이고, 2 ~ 4구간의 최소값 분할 지점이 4와 같은 워스트케이스에서 
					 * O(1)이 아닐 것 같지만 첫행이 방금과 같은 워스트케이스가 되려면 다른 행에서는 모두 2번씩만 반복하면 됨.
					 * 완벽한 O(1)라기 보다는 1/N에서만 O(N)이고, 나머지 N-1/N에서는 O(1)이라고 보면 됨.
					 * 그래서 종합적으로 O(N)*1/N + O(1)*N-1/N이면, O(1)로 봐도 무방하니까 순순히 그렇게 알아들으면 될 것 같음
					 * 
					 * 위 메커니즘이 가능한 이유는 맨 위에서 살펴보았던 점화식에 단조증가와 사각부등식으로 
					 * 크누스라는 분께서 엄청나게 복잡하게 증명을 해주셨다...
					 */
					for(int div = knuth[i][j-1]; div <= knuth[i+1][j]; div++) {
						if(div < K-1 && dp[i][j] > (dp[i][div] + dp[div+1][j]) + (sum[j+1]-sum[i])) {
							dp[i][j] = (dp[i][div] + dp[div+1][j]) + (sum[j+1]-sum[i]);
							knuth[i][j] = div; // 최소값 분할 지점 저장 
						}
					}
				}
			}
			sb.append(dp[0][K - 1]).append("\n");
		}
		System.out.println(sb);
	}
}
