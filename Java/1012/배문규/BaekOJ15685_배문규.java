package BaekOJ.study.date1012;

import java.io.*;
import java.util.*;

/*
 * 백준 15685 : 드래곤 커브
 * 
 * 끝 점을 기준으로  그래프를  시계방향으로 회전시키며 그래프를 점점 키워나가는 문제.
 * 드래곤 커브 생성을 모두 끝마치고 나면 1X1 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부로 포함된 개수를 구한다.
 * 
 * 시행착오 1. 원점을 제 1사분면 처럼 좌하단으로 잡으면 안되고 좌상단으로 잡아야 함. 
 * 		괜히 1: y좌표가 감소하는 방향 (↑) 화살표를 준게 아님
 * 시행착오 2. 다음 드래곤 커브의 끝 점을 리스트 마지막점이라고 생각했는데, 
 * 		항상 첫번째 점이 새로운 끝 점을 기준으로 시계 방향으로 움직이고 난 뒤의 점이 다음 드래곤 커브의 끝 점이 됨
 * 
 * 메모리 	시간
 * 14572	132
 * 
 */

public class BaekOJ15685_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int MAX_SIZE = 101;
	static int N, dCurve[][], delta[][] = {{0,1}, {-1,0}, {0,-1}, {1,0}};
	static boolean map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {

		N = Integer.parseInt(br.readLine());
		dCurve = new int[N][4];
		map = new boolean[MAX_SIZE][MAX_SIZE];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			// 회전 시킬 끝점(기준점)
			int ox = x + delta[d][1];
			int oy = y + delta[d][0];
			
			// 배열에 0세대 커브 추가 + 체크
			List<int[]> coordinate = new ArrayList<int[]>();
			coordinate.add(new int[] {y, x});
			coordinate.add(new int[] {oy, ox});
			map[y][x] = true;
			map[oy][ox] = true;
			
			makeDragonCurve(oy, ox, g, coordinate);
		}
		
		System.out.println(getResult());
	}
	
	public static void makeDragonCurve(int oy, int ox, int g, List<int[]> coordinate) {
		// 기저조건 
		if(g == 0) return;

		// 지난 세대까지 생성된 커브 탐색
		int size = coordinate.size();
		for(int i = 0; i < size; i++) {
			 if(coordinate.get(i)[0] == oy && coordinate.get(i)[1] == ox) continue; // 회전하지 않은 끝 점은 패스
			 rotate(coordinate, oy, ox, coordinate.get(i)[0], coordinate.get(i)[1]); // 끝 점을 기준으로 반시계 방향 90도 회전
		}
		
		// 끝 점을 바꿔주고 세대 -1
		makeDragonCurve(coordinate.get(size)[0], coordinate.get(size)[1], g-1, coordinate);
	}
	
	// 끝 점을 기준으로 해서 시계 방향으로 90 도 회전하고 배열에 추가 + 체크
	public static void rotate(List<int[]> coordinate, int oy, int ox, int y, int x) {
		int rotY = oy + (x-ox);
		int rotX = ox - (y-oy);
		coordinate.add(new int[] {rotY, rotX});
		map[rotY][rotX] = true;
	}
	
	// 결과값 계산
	public static int getResult() {
		int result = 0;
		for(int i = 0; i < MAX_SIZE-1; i++) {
			for(int j = 0; j < MAX_SIZE-1; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) result++;
			}
		}
		return result;
	}
}
