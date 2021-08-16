package P0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * 백준 2567번 색종이 - 2
 * 풀이 : 먼저 색종이 영역을 모두 1로 채우기 
 * 1. 꼭짓점이면, 둘레 2 (상하좌우 중 0인 곳 2개)
 * 2. 변이면, 둘레 1 (상하좌우 중 0인 곳 1개)
 * 따라서 1이면 상하좌우 확인하면서 0인 곳의 개수 체크 = 색종이의 둘레 
 * 
 * 14264KB	152ms
 */
public class Solution2567_김다빈 {

	static int[][] paper = new int[101][101];
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우 
	static int[] dc = {0, 0, -1, 1}; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		for(int num=0;num<N;num++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for(int i=x;i<x+10;i++) {
				for(int j=y;j<y+10;j++) {
					paper[i][j] = 1;
				}
			}
		}
		
		int result = 0;
		for(int i=0;i<101;i++) {
			for(int j=0;j<101;j++) {
				if(paper[i][j]==1) {	// 1이라면 상하좌우 확인 
					for(int dir=0;dir<4;dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];
						
						if(paper[nr][nc]==0) result++; 	// 0이면 둘레길이 + 1
					}
				}
			}
		}
		System.out.println(result);
	}

}
