import java.io.*;
import java.util.*;

/**
 * 
 * [1009] BOJ 14890 경사로
 *  시뮬레이션, 구현, 투포인터
 * 
 *  sol)
 * 1. 모든 열과 모든 행을 확인. 먼저 행 기준으로 확인한 다음에 지도를 뒤집어서 열 기준으로 확인
 * 2. 현재 상태는 높아진 상태로 가정. 각 열을 하나씩 이동하면서 높이가 1 이상으로 변화하면 pass
 * 3. 높이가 1칸 증가하면
 * 		- 높아진 곳에 위치해있었다면 평평한 구간이 X 이상 지속되었는지 확인한다.
 * 		- 낮아진 곳에 위치해있었다면 평평한 구간이 2*X 이상 지속되었는지 확인한다.(높아졌다가 낮아진 상태에서 다시 높아진 것이므로 활주로가 2개 들어가야함)
 * 4. 높이가 1칸 감소하면 평평한 구간이 X 이상 지속되었는지 확인한다.
 * 5. 현재 행의 각 열을 다 확인했다면 마지막으로 평평했던 구간이 X 이상 지속되었는지 확인한다.
 * 6. 위의 상황에서 예외 없다면 활주로 건설
 *
 */

public class BOJ_14890_경사로 {
	static int N, X, totalCnt;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// 지도 입력
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 활주로 건설
		totalCnt = 0;
		install();		// 행기준 활주로 건설
		rotateMap();	// 지도를 돌려서
		install();		// 열기준 활주로 건설
		
		System.out.println(totalCnt);
		
	}
	
	// 각 행별로 활주로 건설 시도
	private static void install() {
		OUTER:for (int i=0; i<N; i++) {     // 행기준
			boolean isDown = false;         // 높은쪽에 있는지 여부
			int start = 0;                  // 경사로 설치 시작점
			
			for (int j=1; j<N; j++) {       // 각 열에 대해 확인
				int gap = map[i][j-1]-map[i][j];
				
				if (Math.abs(gap)>1) continue OUTER;     // 높이가 1 이상으로 변화하면 pass
				
				// 한칸 갑자기 높아지는 경우
				if (gap == -1) {
					if (!isDown && (j-start)<X) continue OUTER;     // 높아진 곳에 위치했다면 평평한 구간의 길이가 X 미만이면 pass
					if (isDown && (j-start)<2*X) continue OUTER;    // 낮아진 곳에 위치했다면 평평한 구간의 길이가 X*2 미만이면 pass
					start = j;                                      // 출발점을 현위치로 조정하고
					isDown = false;                                 // 높아진 곳에 위치함으로 표기
				// 한칸 갑자기 낮아지는 경우
				} else if (gap == 1) {
					if (isDown && (j-start)<X) continue OUTER;      // 낮아진 곳에 위치했다면 구간의 길이가 X 미만이면 pass
					start = j;                                      // 출발점을 현위치로 조정하고
					isDown = true;                                  // 낮아진 곳에 위치함으로 표기
				}
			}
			if (isDown && (N-start)<X) continue;                    // 마지막에 낮아진 곳에 위치했다면 구간의 길이가 X 미만이면 pass
			totalCnt++;                                             // 예외 없었으면 활주로 건설
		}
	}
	
	// 지도 회전
	private static void rotateMap() {
		int[][] rotated = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) rotated[j][i] = map[i][j];
		}
		map = rotated;
	}
}
