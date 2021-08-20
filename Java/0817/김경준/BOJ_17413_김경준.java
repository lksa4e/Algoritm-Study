import java.io.*;
import java.util.*;
/**
 * BOJ 17413 단어 뒤집기 2 : https://www.acmicpc.net/problem/17413
 * 메모리 : 15804KB, 시간 : 176ms
 * 
 * flag2 : <> 안의 문자를 건드리지 않기 위한 설정값
 *        1. < 나오면 flag2 = 1로 만든다. flag2 가 1인 상태에서 입력받는 모든 값은 뒤집는 대상에서 제외
 *        2. > 나오면 flag2 = 0 으로 만든다.
 *          
 * flag1 : 뒤집어줄 문자가 있는 경우 뒤집을 문자의 시작지점을 저장 && -1이면 뒤집을거 없다는 flag
 *         1. 문자가 나오면 flag1 = i(문자의 해당 위치) 로 만든다.
 *         2. 공백 or < 나오면 flag1 부터 지금까지의 문자를 뒤집는다. 뒤집는 flag를 -1로 초기화
 *   
 * 주의사항 : 마지막까지 탐색 후 공백이 없어 뒤집어지지 않은 경우 flag1을 통해 뒤집을 문자가 있는지 확인 후 뒤집기
 * 
 * Time Complexity
 * - O(N)
 *
 */
public class BOJ_17413_김경준 {
	static int N;
	static char[] origin;
	static int flag1 = -1, flag2 = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		origin = br.readLine().toCharArray();
		for(int i = 0; i < origin.length; i++) {
			if(origin[i] == '<') {
				flag2 = 1;
				if(flag1 != -1) swap(flag1, i-1);
			}
			else if(origin[i] == '>') flag2 = 0;
			else if(origin[i] == ' ') {
				if(flag2 == 0 && flag1 != -1) swap(flag1, i-1);
			}
			else if(flag2 == 0 && flag1 == -1) flag1 = i;
		}
		if(flag1 != -1) swap(flag1, origin.length - 1);
		System.out.println(origin);
	}
	
	// 뒤집는 함수
	// 시작점과 끝점을 주고 중간까지 탐색하며(start ~ 중간 ~ end)
	// 맨 앞과 맨뒤를 바꿔준다.
	static void swap(int start, int end) {
		for(int i = 0; i < (end - start + 1)/2 ; i++) {
			char temp = origin[start + i];
			origin[start + i] = origin[end - i];
			origin[end - i] = temp;
		}
		flag1 = -1;
	}
}
