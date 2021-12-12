import java.io.*;
import java.util.*;

public class Main11656 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		
		String[] arr = new String[S.length()];
		for(int i=0; i<S.length(); i++) {
			arr[i] = S.substring(i);
		}
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		for(String s: arr) {
			sb.append(s).append("\n");
		}
		System.out.println(sb);
		
	}

}