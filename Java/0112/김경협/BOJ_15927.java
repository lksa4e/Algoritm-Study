import java.io.*;

/*
 * BOJ 15927번 회문은 회문아니야!!
 * 
 * 팰린드롬이 아닌 가장 긴 부분 문자열을 찾아야 하는데 총 3가지 경우가 있었다.
 * 1. 단일 문자로 이루어진 팰린드롬인 경우 --> 이 경우 모든 부분문자열은 팰린드롬이기 때문에 -1
 * 2. 문자열 자체가 팰린드롬인 경우(1번제외) --> 전체에서 하나 빼면 팰린드롬이 아니기 때문에 len-1
 * 3. 그냥 팰린드롬 아닌 경우 -> 문자열 길이 출력
 * 
 * 팰린드롬을 체크하면서 위 3가지 조건을 체크했다.
 * 
 * 19484KB	204ms
 */

public class BOJ_15927 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str = br.readLine().toCharArray();
		
		System.out.println(getLongestSubStr(str));
	}

	private static int getLongestSubStr(char[] str) {
		boolean isSingleCharStr = true;
		
		for(int i = 0, size = str.length; i < size / 2; i++) {
			if(str[i] != str[size - 1 - i]) return size;
			if(str[i] != str[i + 1]) isSingleCharStr = false;
		}
		
		return isSingleCharStr ? -1 : str.length - 1;
	}

}
