import java.io.*;

/**
 * [0122] BOJ 2133 타일 채우기
 * dp, bottom-up
 * 
 * sol)
 * 타일의 긴 면의 길이가 2이므로 무조건 짝수 너비의 벽만 채울 수 있음
 * 짝수인 경우
 *    - 너비 2 : 3가지
 *    - 너비 4 : 너비 2짜리 벽을 2개 붙이는 방법 + 새로운 방법 2가지
 *    - 너비 6 : 너비 2짜리 벽을 3개 붙이는 방법 + 너비 4짜리 새로운 벽에 너비 2짜리 벽을 붙이는 방법 + 새로운 방법 2가지
 *             = (너비 4짜리 벽을 붙이는 경우의 수)에 너비 2짜리 벽을 붙이는 방법 + 새로운 방법 2가지 
 *    ...
 *    
 * 위의 규칙을 보면 벽 너비가 2씩 증가하면 이는 이전 너비 경우의 수에 너비 2짜리 벽을 붙이는 경우의 수를 곱하고,
 * 추가적으로 새로운 방법 2가지가 추가되는 형태임을 알 수 있다.
 *
 */

public class BOJ_2133_타일채우기 {
	static int N;
	static int[] tiles;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tiles = new int[N+1];
		
		System.out.println(fillTiles());
	}

	// dp 테이블 채우기
	private static int fillTiles() {
		if (N%2!=0) return 0;                 // 홀수면 무조건 못채움
		else {
			tiles[2] = 3;                     // 기저조건 : 너비 2짜리 벽은 무조건 3개의 경우의 수 존재
			// bottom-up 방식으로 채워감
			for (int i=4; i<=N; i++) {
				// 너비 2만큼씩 증가하므로 너비가 2 작은 벽에 너비 2만큼의 타일을 붙임
				tiles[i] += tiles[i-2] * 3;
				// 너비 4 이상인 벽들은 자체적으로 새로운 방법 2가지씩을 지니므로 이들을 중첩하여 곱함
				for (int j=i-4; j>=2; j-=2) tiles[i] += tiles[j] * 2;
				// 각 너비에서 추가되는 새로운 방법 2가지
				tiles[i] += 2;
			}
		}
		return tiles[N];
	}

}
