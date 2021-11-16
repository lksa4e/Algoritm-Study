import java.io.*;
import java.util.*;

public class Main1213 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] name = br.readLine().toCharArray();
		
		int[] arr = new int[26];
		for(char c: name) {
			arr[c-'A']++;
		}
		
		// 홀수인 문자 개수 체크
		int oddCnt = 0;
		String odd = "";
		
		for(int i=0; i<26; i++) {
			if(arr[i]%2!=0) {
				oddCnt++;
				odd = String.valueOf(((char)(i+65)));
			}
		}
		
		// 홀수인 문자 개수가 2개 이상이면 펠린드롬 불가
		if(oddCnt>=2) {
			System.out.println("I'm Sorry Hansoo");
		}else {
			StringBuilder sb = new StringBuilder();
			// 'A'부터 개수의 절반씩 붙여줌
			for(int i=0; i<26; i++) {
				for(int j=0; j<arr[i]/2; j++) {
					sb.append(String.valueOf( (char)(i+65) ));
				}
			}
			String ans = sb.toString();
			
			// 홀수인 문자 있으면 붙여줌
			if(oddCnt==1) {
				ans += odd;
			}
			
			// 역순 붙여주고 출력
			ans += sb.reverse().toString();
			System.out.println(ans);
		}
	}

}