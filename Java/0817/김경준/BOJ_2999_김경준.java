import java.io.*;
import java.util.*;
/**
 * BOJ 2999 비밀 이메일 : https://www.acmicpc.net/problem/2999
 * 메모리 : 14136KB, 시간 : 124ms
 * 
 * 1. R과 C를 찾는 방법
 * 메세지의 최대 길이는 100이다. 이 경우에서 R <= C, R * C = N을 만족하는 최대의 R은 10이다. (10 * 10 == 100)
 * 따라서 10부터 시작하여 1까지 가면서 R을 먼저 고르고, R <= C, R * C == N 을 검사하며 찾는다.
 * 
 * 2. 암호 해독하기 
 * 정인이가 암호를 만드는 방법은 
 *   1) 왼쪽 위부터 오른쪽 아래까지 Z 형태로 채운다.
 *   2) 왼쪽 위부터 오른쪽 아래까지 N 형태로 읽는다. 
 * 따라서 해독하기 위해서는 주어진 암호를 2 뒤집기, -> 1 뒤집기 순서로 해야 한다.
 *   2뒤집기) 오른쪽 아래부터 왼쪽 위까지 И 형태로 채운다.
 *   1뒤집기) 오른쪽 아래부터 왼쪽 위까지 Z 형태로 읽는다. 
 *  
 * Time Complexity
 * 알고리즘을 사용할 수 없는 worst case문제, 무조건 암호 해독을 위해서 배열을 채우고 읽어야 함
 */
public class BOJ_2999_김경준 {
	static int N, R, C;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		char[] chars = br.readLine().toCharArray();
		N = chars.length;
		// R, C 찾기
		findRC();
		char[][] code = new char[R][C];
		int cnt = 0;
		// И 형태로 오른쪽 아래부터 채우기
		for(int i = C-1; i >= 0; i--) {
			for(int j = R-1; j >= 0; j--) {
				code[j][i] = chars[cnt++];
			}
		}
		// Z 형태로 오른쪽 아래부터 읽기
		for(int i = R-1; i >= 0; i--) {
			for(int j = C-1; j >= 0; j--) {
				sb.append(code[i][j]);
			}
		}
		System.out.print(sb);
	}
	
	
	static void findRC(){
		for(int i = 10; i >= 1; i--) {
			if(N % i == 0) {
				int tempc = N / i;
				if(tempc >= i) {
					C = tempc;
					R = i;
					break;
				}
			}
		}
	}
}
