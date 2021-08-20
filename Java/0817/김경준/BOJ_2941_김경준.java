import java.io.*;
import java.util.*;
/**
 * BOJ 2941 크로아티아 알파벳 : https://www.acmicpc.net/problem/2941
 * 메모리 : 14244KB, 시간 : 144ms
 * 
 * if else 를 통해 적절히 비교하면 된다.
 * 
 * 풀이방법 1. 앞  문자 기준으로 switch case -> c, d, i ,n ,s , z 6가지 케이스
 * 풀이방법 2. 뒤 문자 기준으로 switch case -> =, -, j 3가지 케이스 
 * 
 * 이번 문제에서는 뒤 문자 기준으로 switch case 하는것이 코드가 훨씬 간결해진다. (solve2)
 */
public class BOJ_2941_김경준 {
	static int N, size, answer;
	static char[] chars;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		chars = br.readLine().toCharArray();
		size = chars.length;
		answer = 0;
		solve2();
		System.out.println(answer);
	}
	static void solve2() {
		for(int i = 0; i < size; i++) {
			if(i == 0) {
				answer++;
				continue;
			}
			switch(chars[i]) {
			case '=':
				switch(chars[i-1]) {
				case 'c': case 's': break;
				case 'z':
					if(i - 2 >= 0 && chars[i-2] == 'd') answer--;
					break;
				default : answer++;
				}
				break;
			case '-':
				switch(chars[i-1]) {
				case 'c': case 'd': break;
				default: answer++;
				}
				break;
			case 'j':
				switch(chars[i-1]) {
				case 'l': case 'n': break;
				default: answer++;
				}
				break;
			default : answer++;
			}
		}
	}
	static void solve() {
		for(int i = 0; i < size; i++) {
			switch(chars[i]) {
			case 'c':
				if(i < size - 1) {
					if(chars[i+1] == '=' || chars[i+1] == '-') {
						answer++;
						i++;
					}else answer++;
				}else answer++;
				break;
			case 'd':
				if(i < size -1) {
					if(chars[i+1] == '-') {
						answer++;
						i++;
					}else if(chars[i+1] == 'z') {
						if(i < size - 2 && chars[i+2] == '=') {
							answer++;
							i+=2;
						}else answer++;
					}else answer++;
				}else answer++;
				break;
			case 'l':
				if(i < size - 1) {
					if(chars[i+1] == 'j')i++;
					answer++;
				}else answer++;
				break;
			case 'n':
				if(i < size - 1) {
					if(chars[i+1] == 'j') i++;
					answer++;
				}else answer++;
				break;
			case 's':
				if(i < size - 1) {
					if(chars[i+1] == '=') i++;
					answer++;
				}else answer++;
				break;
			case 'z':
				if(i < size - 1) {
					if(chars[i+1] == '=')i++;
					answer++;
				}else answer++;
				break;
			default:
				answer++;
				break;
			}
		}
	}
}
