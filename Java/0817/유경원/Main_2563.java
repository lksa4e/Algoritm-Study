import java.io.*;
import java.util.*;

public class Main_2563 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		boolean[][] paper = new boolean[101][101];
		int area = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int j=x; j<x+10; j++) {
				for(int k=y; k<y+10; k++) {
					if(!paper[j][k]) {
						paper[j][k] = true;
						area++;
					}
				}
			}
		}
		System.out.println(area);
	}
}