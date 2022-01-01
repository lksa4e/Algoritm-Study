import java.io.*;

/*
 * BOJ 5582번 공통 부분 문자열
 * 
 * DP
 * 
 * 2차원 배열을 사용해 공통으로 같은 문자가 있다면 기록한다.
 * 기록할 때, 이전에 기록한 걸 가져와서 더해준다.
 * 
 * 처음에 KMP처럼 i,j 인덱스로 해보려했으나 알고리즘 분류를 봐버려서
 * DP로 방향을 바꾸었음
 * 
 * 	77880KB	228ms
 */

public class BOJ_5582 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();

		int len1 = str1.length, len2 = str2.length;

		int[][] dp = new int[len1 + 1][len2 + 1];
		// 첫번째 행열 처리를 편하게 하기 위해서 바운더리를 추가한다.

		int max = 0;
		for (int i = 1; i <= len1; i++) {	// 첫번재 행,열은 바운더리. 1번째 index부터 시작
			for (int j = 1; j <= len2; j++) {
				if (str1[i - 1] == str2[j - 1]) {	// 서로 문자가 같으면
					dp[i][j] = dp[i - 1][j - 1] + 1;	// 이전 기록 + 1
					if(max < dp[i][j])
						max = dp[i][j];
				}
			}
		}
		
		System.out.println(max);

	}

}
