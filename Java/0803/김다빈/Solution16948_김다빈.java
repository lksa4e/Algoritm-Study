package Problem0807;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution16948_김다빈 {
	 
	static int[] dr = {-2,-2,0,0,2,2};
	static int[] dc = {-1,1,-2,2,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		int N = sc.nextInt();
		int[][] visited = new int[N][N];
		
		int startR = sc.nextInt();
		int startC = sc.nextInt();
		int destR = sc.nextInt();
		int destC = sc.nextInt();
		
		Queue<String> queue = new LinkedList<String>();
		queue.offer(startR+" "+startC);
		visited[startR][startC] = 0;
		
		int r,c;
		while(!queue.isEmpty()) {
			st = new StringTokenizer(queue.poll(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			for(int i=0;i<6;i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]!=0) continue;
				
				queue.offer(nr+" "+nc);
				visited[nr][nc] = visited[r][c] + 1;
				
			}
		}
		
		if(visited[destR][destC] == 0) {
			System.out.println(-1);
		}else {
			System.out.println(visited[destR][destC]);
		}
	}

}
