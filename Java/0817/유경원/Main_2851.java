import java.io.*;
import java.util.*;

public class Main_2851 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	static StringBuilder sb = new StringBuilder();
//	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		int score = 0, mush = 0;
		for(int i=0; i<10; i++) {
			mush = Integer.parseInt(br.readLine());
			score += mush;
			if(score == 100) break;
			if(score > 100) {
				int a = 100 - (score - mush); // 차이
				int b = score - 100;
				if (a < b) score -= mush;
				
				break;
			}
		}
		System.out.println(score);
		
	}
}