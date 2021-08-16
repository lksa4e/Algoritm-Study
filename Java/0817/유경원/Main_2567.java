import java.io.*;
import java.util.*;

public class Main_2567 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		boolean[][] paper = new boolean[101][101];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int j=x; j<x+10; j++) {
				for(int k=y; k<y+10; k++) {
					if(!paper[j][k]) paper[j][k] = true;
				}
			}
		}
		
		int[] dx = {-1,1,0,0}; 
		int[] dy = {0,0,-1,1};
		int nx, ny, len=0;
		for(int x=0; x<101; x++) {
			for(int y=0; y<101; y++) {
				if(paper[x][y]) {
					for(int k=0; k<4; k++) {
						nx = x + dx[k];
						ny = y + dy[k];
						
						if(!paper[nx][ny]|| nx<0||nx>100||ny<0|ny>100) len++;
					}
				}
			}
			
		}
		System.out.println(len);
	}
}