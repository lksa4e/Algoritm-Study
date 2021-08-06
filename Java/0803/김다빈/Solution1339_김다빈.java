package Problem0807;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution1339_김다빈 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		
		Integer[] alpha = new Integer[26]; // A-Z
		Arrays.fill(alpha, 0);
		
		for(int i=0;i<N;i++) {
			char[] ch = sc.nextLine().toCharArray();
			
			for(int j=ch.length-1; j>=0; j--) {	// 10^(자리수)
				alpha[ch[j]-'A'] += (int)Math.pow(10,ch.length-j-1);
			}
		}
		
		Arrays.sort(alpha, Collections.reverseOrder());
		int result = 0;
		for(int i=0;i<10;i++) {	// 최대 단어 개수 10 
			if(alpha[i]==0) break;
			result += alpha[i]*(9-i);
		}
		System.out.println(result);
	}
}
