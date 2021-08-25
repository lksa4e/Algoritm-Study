package swea;

import java.io.*;

class Solution1234 {
	static int N;
	static String password;
	static String[] code = {"00", "11", "22", "33", "44", "55", "66", "77", "88", "99"};
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("C:\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= 10; testCase++) {
			password = br.readLine().split(" ")[1];
			
			for(int i=0; i<10; i++) {
				String s = password.replace(code[i], "");
				if(password.length() != s.length()) i=-1; 
				password = s;
			}
			sb.append("#").append(testCase).append(" ").append(password).append("\n");
		}
		System.out.println(sb);
	}
}
