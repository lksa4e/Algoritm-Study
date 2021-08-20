import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 1080번 행렬
 * 
 * 주어진 원본 행렬을 주어진 목표 행렬로 바꾸는 문제
 * 행렬은 3x3 크기의 부분 행렬에 있는 모든 원소를 뒤집는 방법으로 바꿀 수 있다.
 * 
 * 해결 방법:
 * 맵의 가장 왼쪽 위는 부분 행렬이 딱 한 번만 처리 할 수 있다. 부분 행렬의
 * 가장 왼쪽 위를 기준으로 삼아서 original map과 target이 같으면 놔두고
 * 다르면 reverse 한다. 그리고 계속 행렬 연산을 하면 각 왼쪽 위를 기점으로 original과
 * target이 동일한 맵을 가지게 된다.
 * 이 때 두 맵이 다르면 -1을 리턴한다.
 * 
 * 시간 복잡도:
 * N-2*N-2*3*3 = O(N^2)인데 N이 48이 최대
 */

public class BOJ_1080 {
	static int R,C;
	static char[][] map, targetMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		targetMap = new char[R][];
		for (int i = 0; i < R; i++)
			map[i] = br.readLine().toCharArray();
		
		for (int i = 0; i < R; i++)
			targetMap[i] = br.readLine().toCharArray();
		
		System.out.println(solve());
		

	}
	
	static int solve() {
		int cnt = 0;
		// 3x3배열이 왼쪽 위를 기준으로 행렬연산을 하려하니 R, C에서 2씩 빼줌
		for (int r = 0; r < R - 2; r++) {
			for (int c = 0; c < C - 2; c++) {
				// 왼쪽 위 기준으로 orignal과 target이 다르면 cnt 올리고 reverse
				if(map[r][c] != targetMap[r][c]) {
					cnt++;
					reverse3x3(r,c);				
				}
			}
		}
		
		return isSame() ? cnt : -1; // 다 바꾸고 나서 기존 맵과 타겟 맵 비교
	}
	
	static void reverse3x3(int r, int c) {
		for (int i = r; i < 3 + r; i++)
			for (int j = c; j < 3 + c; j++)
				map[i][j] = map[i][j] == '0' ? '1' : '0';
	}
	
	static boolean isSame() {
		for (int r = 0; r < R; r++)
			for (int c = 0; c < C; c++)
				if(map[r][c] != targetMap[r][c])
					return false;
		return true;
	}

}
