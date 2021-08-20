import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0817] BOJ 2567 색종이 - 2
 *
 * sol) 2차원 배열 연습
 * tc) O(N)
 */

public class BOJ2567 {
	static int border = 0;
	static int[][] matrix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
		matrix = new int[102][102];    // 패딩 주기
		
		while(N-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())+1;
			int c = Integer.parseInt(st.nextToken())+1;
			
			// 색종이를 도화지에 겹쳐지게 붙임
			makeMatrix(r, c);
		}
		
		// 둘레 구하기
		calcWidthBorder();    // 가로 둘레
		calcHeightBorder();   // 세로 둘레
		
		System.out.println(border);
		
	}

	// 색종이 2차원 배열로 표현
	private static void makeMatrix(int r, int c) {
		for (int i=r; i<r+10; i++) {
			for (int j=c; j<c+10; j++) matrix[i][j] = 1;
		}
	}
	
	// 가로 둘레 구하기
	private static void calcWidthBorder() {
		for (int i=0; i<101; i++) {       // 2개의 행을 비교하며
			for (int j=0; j<102; j++) {
				if (matrix[i][j] == 0) {        // 빈 공간 아래에 색종이가 최초로 등장하는 경우를 둘레로 보거나
					if (matrix[i+1][j] == 1) border++;
				}
				else if (matrix[i][j] == 1) {   // 색종이 아래에 빈 공간이 최초로 등장하는 경우를 둘레로 봄
					if (matrix[i+1][j] == 0) border++;
				}
			}
		}
	}
	
	// 세로 둘레 구하기
	private static void calcHeightBorder() {
		for (int i=0; i<101; i++) {       // 2개의 열을 비교하며
			for (int j=0; j<102; j++) {
				if (matrix[j][i] == 0) {        // 빈 공간 오른쪽에 색종이가 최초로 등장하는 경우를 둘레로 보거나
					if (matrix[j][i+1] == 1) border++;
				}
				else if (matrix[j][i] == 1) {   // 색종이 오른쪽에 빈 공간이 최초로 등장하는 경우를 둘레로 봄
					if (matrix[j][i+1] == 0) border++;
				}
			}
		}
	}
	
	
}
