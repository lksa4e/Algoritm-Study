import java.io.*;
import java.util.*;

public class Main1316 {

	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int cnt = 0;
		for(int i=0; i<N; i++) {
			char[] word = br.readLine().toCharArray();
			boolean[] check = new boolean[26];
			check[word[0]-'a'] = true;
			boolean flag = true;
			
			for(int j=1; j<word.length; j++) {
				if(word[j-1] == word[j]) continue;
				if(check[word[j]-'a']) {
					flag = false;
					break;
				}
				check[word[j]-'a'] = true;
			}
			if(flag) cnt++;
		}
		
		System.out.println(cnt);
	}
}