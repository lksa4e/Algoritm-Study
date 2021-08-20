package BaekOJ.study.date0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 100*100 색종이 1장에 다가 10*10 색종이가 100장이라서 100*100 == 10,000 이라면
 * 그냥 브루탈 포스로 풀어도 전혀 시간 문제가 없을거라 생각함
 * 검은 색종이의 좌하단 좌표가 주어지므로 2차원 배열을 생성하지 않고 처음으로 Point 클래스를 생성해서 Point배열로 문제를 풀어봄
 * 좌하단 좌표x, y 에서 x+10, y+10 까지 boolean 타입 배열에 검은색이라고 마킹을 함
 * 만약 종이가 몇개 겹쳐졌냐는 문제라면 boolean -> int 타입 배열로 바꾸고 +1을 해주면 될거라 생각함
 * 
 * 최종적으로 100*100을 순회하며 마킹이 되어있는 구역을 찾아서 넓이에 +1을 해주면 검은색종이 넓이를 구할 수 있음
 * 
 * 메모리		시간
 * 14228	124
 */

// 쉬운 문제라서 이때 한번 사용해보자 하고 처음으로 사용해봄
class Point{
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class BaekOJ2563_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		Point[] pts = new Point[N];
		// 마킹하고 넓이를 구할 배열
		boolean[][] isblack = new boolean[100][100];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// 좌하단 좌표 입력
			pts[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getArea(pts, isblack));
	}
	
	public static int getArea(Point[] pts, boolean[][] isblack) {
		// 좌하단을 기준으로 우상당까지 10*10 마킹
		for(Point pt : pts) {
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					isblack[pt.x+i][pt.y+j] = true;
				}
			}
		}
		
		// 마킹된 넓이를 구함
		int area = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(isblack[i][j]) area++; 
			}
		}
		return area;
	}

}
