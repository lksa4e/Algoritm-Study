import java.io.*;
import java.util.*;

public class Main17136 {
	/*
	 * 처음엔 5x5 다 붙이고 4x4 다붙이는 식으로 했다가 아래 반례에서 걸림
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0

5
	 * 결국 dfs로 1을 만날때마다 큰 크기부터 모든 색종이 사이즈를 붙였다 뗏다 해야함
	 *
	 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int[][] map = new int[10][10];
	static int[] paper = new int[] {0, 5,5,5,5,5};
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0,0);
		System.out.println((ans==Integer.MAX_VALUE)?-1:ans);
	}
	
	private static void dfs(int x, int y, int cnt) {
        if (x >= 9 && y >= 10) {
        	if(ans>cnt) ans = cnt;
            return;
        }
 
        if (ans <= cnt) return; // 최솟값 넘기면 가지치기
        
        if (y > 9) { // 끝 열 도달시 행 증가
            dfs(x + 1, 0, cnt);
            return;
        }
 
        if (map[x][y] == 1) { // 1이면 큰 사이즈부터 모든 사이즈 붙여보기
            for (int i = 5; i >= 1; i--) {
                if (paper[i] > 0 && check(x, y, i)) { // 해당사이즈 남아있고 붙일수 있으면
                    cover(x, y, i, 0); // 붙이고
                    paper[i]--; // 해당사이즈 1개 감소
                    dfs(x, y + 1, cnt + 1);
                    cover(x, y, i, 1);
                    paper[i]++;
                }
            }
        } else {
            dfs(x, y + 1, cnt); // 1 아니면 열 증가
        }
		
	}
	
	// 색종이 붙이기 or 복구 paper 0 or 1
	private static void cover(int x, int y, int n, int paper) {
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				map[i][j] = paper;
			}
		}
	}
	
	// 붙일 수 있다면 true 리턴
	private static boolean check(int x, int y, int n) {
		if(x+n >= 11 || y+n >= 11) return false;
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(map[i][j]!=1) return false;
			}
		}
		return true;
	}
}