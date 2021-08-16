package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//class Point{
//	int x;
//	int y;
//	
//	Point(int x, int y){
//		this.x = x;
//		this.y = y;
//	}
//}

/*
 * 이전 색종이 문제 처럼 마킹을 하고
 * 둘레를 구하는 방법을 생각을 해봤는데, 이전 포인트 white -> 현재 포인트 black이면 해당 부분이 변이라고 생각하고
 * 100*100 반복에서 인덱스를 조절해서 상하좌우 변들을 모두 구하여 둘레를 구함
 * 
 * 메모리		시간
 * 14152	136  
 */

public class BaekOJ2567_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static boolean[][] isBlack;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		isBlack = new boolean[102][102];
		Point[] pts = new Point[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pts[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		mark(pts);
		System.out.println(getPerimeter());
	}
	
	// 마킹하는 메소드
	public static void mark(Point[] pts) {
		for(Point pt : pts) {
			for(int i = 1; i <= 10; i++) {
				for(int j = 1; j <= 10; j++) {
					isBlack[pt.x+i][pt.y+j] = true;
				}
			}
		}
	}
	
	// 둘레를 구하는 함수
	// 이전 white -> 현재 black = 변
	public static int getPerimeter() {
		int perimeter = 0;
		for(int i = 1; i <= 100; i++) {
			for(int j = 1; j <= 100; j++) {
				if(!isBlack[i][j-1] && isBlack[i][j]) 			perimeter += 1; // 좌변 
				if(!isBlack[i][101-j+1] && isBlack[i][101-j]) 	perimeter += 1; // 우변
				if(!isBlack[j-1][i] && isBlack[j][i]) 			perimeter += 1; // 상변
				if(!isBlack[101-j+1][i] && isBlack[101-j][i]) 	perimeter += 1; // 하변
			}
		}
		return perimeter;
	}

}
