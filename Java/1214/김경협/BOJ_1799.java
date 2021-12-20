import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 1799번 비숍
 * 
 * 시행착오1,
 * powerset + 가지치기로 풀릴 줄 알고 짰지만 시간초과
 * 
 * 참고 블로그:
 * https://j2wooooo.tistory.com/80
 * 
 * 흰 칸과, 검은 칸이 대각선적으로 독립이기 때문에 둘로 나눠서 진행
 * powerset + 가지치기는 동일하지만 기존 N*N 탐색이 진행되던 것에서 ((N/2) * (N/2)) * 2로 바뀌었다.
 * 
 * 또한 처음에 대각선 체크 전용 int 이중배열을 만들어서 비숍을 두면 해당하는 대각선에 1추가하고,
 * 비숍을 빼면 대각선에서 1을 빼는 형식으로 진행했는데, 대각선 자체를 그냥 하나의 boolean으로 보고
 * 판단하는게 시간적인 면에서 더 좋아보여서 그대로 적용
 * 
 * 15332KB	160ms
 */

public class BOJ_1799 {
	static int N, max[];
	static boolean deployable[][], left[], right[];	
	// left -> 왼쪽위에서 오른쪽 아래로 긋는 대각선, right -> 오른쪽 위에서 왼쪽 아래로 긋는 대각선
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		left = new boolean[2*N];
		right = new boolean[2*N];
		deployable = new boolean[N][N];
		max = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE};
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1)
					deployable[i][j] = true;
			}
		}
		
		deployBishop(0, 0, 0, 0);	// 첫 번째 대각선들
		deployBishop(0, 0, 1, 1);	// 두 번째 대각선들
		System.out.println(max[0] + max[1]);
	}
	
	static void deployBishop(int cnt, int row, int col, int color) {
		if(col >= N) {	// col이 범위 밖으로 나갔을 때 다음 row로,
			row++;
			col = (col % 2 == 0) ? 1 : 0;
			/*col = col % N;  처음에 이렇게 했는데, N이 짝수일 때 정상적으로 작동하지 않았다.*/
		}
		if(row >= N) {
			max[color] = Math.max(max[color], cnt);
			return;
		}
		
		if(deployable[row][col] && 	// 비숍 놓을 수 있음
				!left[col - row + N - 1] &&		// 현재 대각 선에 다른 비숍 없음
				!right[row + col]) {
			left[col - row + N - 1] = right[row + col] = true;
			deployBishop(cnt + 1, row, col + 2, color);		// 비숍 놓을 수 있을 경우, 놓은 상태에서 진행
			left[col - row + N - 1] = right[row + col] = false;
		}
		deployBishop(cnt, row, col + 2, color);	// 현 위치에 놓지 않은 상태에서 진행
	}
		
	static boolean isOutOfMap(int row, int col) {
		return row < 0 || col < 0 || row >= N || col >= N;
	}

}
