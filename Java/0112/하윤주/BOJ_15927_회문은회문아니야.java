import java.io.*;

/**
 * [0112] BOJ 15927 회문은 회문아니야!!
 * 문자열
 * 
 * sol)
 * 맨 앞 문자와 맨 뒤 문자를 비교하면서 전체 문자열이 팰린드롬인지 확인한다.
 * 전체 문자열이 팰린드롬이 아니면 문자열 길이가 최대 부분문자열이 된다.
 * 한편 전체 문자열이 팰린드롬이면 모든 문자가 동일할 경우엔 -1을 반환하고, 
 * 아니면 문자열 길이-1 이 최대 부분문자열이 된다.
 * 
 * 시행착오)
 * 복잡하게 생각하고 맨 앞 인덱스를 하나 옮길때마다 이전 모든 문자와 비교하여 모든 가능한 팰린드롬을 확인했는데 시간이 터졌다.
 * 마찬가지로 DP로 해결하기위해 s부터 e까지 팰린드롬인지 확인하는 점화식을 세워(dp[s][e] = dp[s+1][e-1] && str[s]==str[e]) 풀어봈는데 메모리가 터졌다..
 *
 */

public class BOJ_15927_회문은회문아니야 {
	static String str;
	static char[] chars;
	static int len;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		chars = str.toCharArray();
		len = chars.length;
		
		// 전체 문자열이 팰린드롬이 아니면 전체 문자열 길이를 출력
		if (!isPalindrome()) System.out.println(len);
		else {
			// 전체 문자열이 팰린드롬이면 문자열이 모두 동일한 문자로 구성됐다면 -1을,
			if (!flag) System.out.println(-1);
			// 서로 다른 문자들로 구성되어 대칭이 된다면 전체 길이-1 을 출력
			else System.out.println(len-1);
		}
	}
	
	// 전체 문자열이 팰린드롬인지 확인
	private static boolean isPalindrome() {
		int s = 0;
		int e = len-1;
		char before = chars[0];
		
		while(s<=e) {
			// 다른 문자가 한번이라도 등장하는지 체크
			if (before!=chars[e]) flag = true;
			// 대칭이 되는 문자들이 동일한지 체크
			if (chars[s++]!=chars[e--]) return false;
		}
		return true;
	}

}
