import java.io.*;
import java.util.*;

public class Main1181 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String[] arr = new String[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = br.readLine();
		}
		
		Arrays.sort(arr, 
				(e1, e2) -> 
					(e1.length() == e2.length())? e1.compareTo(e2) :e1.length() - e2.length()
				);
		
		StringBuilder sb = new StringBuilder();
		String before = "";
		for(String s: arr) {
			if(before.equals(s)) continue;
			before = s;
			sb.append(s).append("\n");
		}
		System.out.println(sb);
		
	}
	

}