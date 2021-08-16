package P0816;

import java.util.Scanner;

/** 
 * 백준 2941번 크로아티아 알파벳 
 * 
 */
public class Solution2941_김다빈 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] input = sc.nextLine().toCharArray();
		
		int result = 0;
		for(int i=0;i<input.length;i++) {
			if(i == input.length-1) {
				result++;
				break;
			}
			switch(input[i]) {
			case 'c':
				if(input[i+1] == '=' || input[i+1] == '-') i++;
				break;
			case 'd':
				if(input[i+1]=='-') i++;
				else if(input[i+1]=='z'&&((i+2)<input.length && input[i+2]=='=')) i+=2;
				break;
			case 'l':
			case 'n':
				if(input[i+1] == 'j') i++;
				break;
			case 's':
			case 'z':
				if(input[i+1] == '=') i++;
				break;
			}
			result++;
		}
		System.out.println(result);
	}

}
