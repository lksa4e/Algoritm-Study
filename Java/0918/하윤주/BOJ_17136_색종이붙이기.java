import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0918] BOJ 17136 색종이붙이기
 *  완전탐색, 재귀
 * 
 * sol)
 * 재귀를 이용해 색종이를 붙일 수 있는 영역마다 1~5 사이즈의 색종이 조합을 모두 붙여보고(완전탐색) 사용한 색종이의 최솟값을 출력
 * -- 유도파트
 * 1. (0, 0)에서 출발하여 '해당 좌표에 색종이 붙일 수 있는지(1)' 확인하고 없다면 다음 좌표 탐색
 * 2. 붙일 수 있는 영역이라면 1~5사이즈의 색종이 별로 '해당 사이즈 색종이가 남아있는지'와 '영역에 색종이 사이즈가 맞는지'를 확인
 * 3. 위의 조건이 충족한다면 해당 사이즈 색종이 사용함을 표시하고 2차원 배열에도 색종이 붙인 상태를 저장하고 다음 색종이 탐색
 * -- 기저조건
 * 위의 과정을 (9, 9)까지 반복하고 마지막 좌표에 도달해서는 총 몇 장의 색종이가 붙었는지 확인한 뒤 최솟값 갱신
 * 
 */

public class BOJ_17136_색종이붙이기 {
	static int cnt, min = Integer.MAX_VALUE;
	static int[][] paper;
	static int[][] copied;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		paper = new int[10][10];
		
		// 초기 상태 입력
		for (int i=0; i<10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<10; j++) paper[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// dfs로 색종이 영역 탐색
		findColorPaper(0, 0, new int[5]);
		// 최솟값
		min = min == Integer.MAX_VALUE ? -1 : min;
		System.out.println(min);
	}

	// dfs로 색종이 영역 완전탐색
	private static void findColorPaper(int xy, int colorPaperCnt, int[] limit) {
		// 기저조건 : 2차원 행렬 탐색 끝나면(xy : 2차원 배열 좌표를 1차원 배열 인덱스로 표현한 값)
		if (xy == 100) {
			min = Math.min(min, colorPaperCnt);     // 최솟값 갱신
			return;
		}
		
		// 1차원 배열 인덱스 형태를 좌표 형태로
		int x = xy / 10;
		int y = xy % 10;
		
		// 유도파트 : 좌표에 해당하는 영역이 색종이를 붙일 수 없는 곳이면 붙인 색종이 수 증가하지 않고 다음 좌표 탐색
		if (paper[x][y] != 1) findColorPaper(xy+1, colorPaperCnt, limit);
		else {        // 색종이를 붙일 수 있는 좌표면
			
			// 1~5사이즈의 색종이를 모두 시도
			for (int i=4; i>=0; i--) {
				if (limit[i] >= 5) continue;                       // 현재 사이즈 종이를 다 소진한 경우 pass
				if (!isPossibleColorPaper(x, y, i)) continue;      // 영역에 현재 사이즈 종이가 안맞아도 pass
				
				// 현재 사이즈 종이를 영역에 붙일 수 있는 경우에는
				limit[i]++;                                        // 현재 사이즈 종이를 사용했다고 체크하고
				attachColorPaper(x, y, i, 2);					   // 영역에는 색종이 붙인 상태를 저장
				
				findColorPaper(xy+1, colorPaperCnt+1, limit);       // 붙인 색종이 수 증가하여 다음 좌표 탐색
				
				limit[i]--;						                   // 재귀가 끝나 현재 좌표로 다시 돌아왔다면 현재 사이즈 종이를 다시 사용하도록 사용 체크 해제
				attachColorPaper(x, y, i, 1);					   // 영역도 색종이를 붙이지 않은 상태로 원상복구
			}
		}
	}

	// 영역에 색종이 사이즈가 맞는지 확인
	private static boolean isPossibleColorPaper(int x, int y, int range) {
		for (int i=x; i<=x+range; i++) {
			for (int j=y; j<=y+range; j++) {
				// 사이즈의 종이가 경계를 벗어나거나 색종이를 붙일 수 없는 영역이면 false
				if (!isInside(i, j) || paper[i][j] != 1) return false;
			}
		}
		return true;
	}
	
	// 영역에 색종이 붙인 상태를 저장
	private static void attachColorPaper(int x, int y, int range, int cover) {
		for (int i=x; i<=x+range; i++) {
			for (int j=y; j<=y+range; j++) {
				paper[i][j] = cover;
			}
		}
	}
	
	// 경계 체크
	private static boolean isInside(int x, int y) {
		if (x>=0 && x<10 && y>=0 && y<10) return true;
		return false;
	}
	
}
