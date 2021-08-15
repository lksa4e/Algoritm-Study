import java.io.*;
import java.util.*;
/**
 * BOJ 2563 색종이2 : https://www.acmicpc.net/problem/2567
 * 메모리 : 14176KB 시간 : 148ms
 *  
 * 전체 도화지의 사이즈는 100 * 100 이기 때문에, 100 사이즈의 색종이를 붙이는 수는 90 * 90이다.
 * 한번 색종이를 붙이는 데 100의 연산이 필요하기 때문에 90 * 90 * 100을 해도 810,000으로 충분한 수가 나온다.
 * 따라서 검은 영역을 구하기 위해 번거로운 작업을 하는 것보단 단순하게 들어온 모든 색종이에 대해 칠해주는 연산을 수행하는 것이 이득 
 * 
 * 둘레 구하기 100 * 100 도화지를 탐색하면서 검은색 영역인 경우 검사를 시작한다. (검은색이어야 둘레를 잴 수 있음)
 * 해당 지점에서 4방을 탐색하며 흰색이 존재하는 경우의 수를 세준다. (주변이 흰색이라는 것은 가장 바깥쪽이라는 것을 의미 == 둘레 count에 들어가는 영역)
 * 따라서 100 * 100 도화지를 탐색하며 4방을 탐색해주고, 흰색인 경우에 둘레 + 1을 해준다.
 * 
 * Time Complexity
 * - 색종이 붙이기 연산 : O(N*N*N) == O(N^3)
 * - 둘레 구하기 연산 : O(N*N*4) == O(N^2)
 * ==> O(N^3) <N의 최대값 100>
 */
public class BOJ_2567_김경준 {
	static int N, fst, snd, answer = 0;
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	static boolean[][] arr = new boolean[102][102];
	
	static void fill_arr() {
		for(int i = fst; i < fst + 10; i++) {
			for(int j = snd; j < snd + 10; j++) {
				arr[i][j] = true;
			}
		}
	}
	static void count() {
		for(int i = 1; i <= 100; i++) {
			for(int j = 1; j <= 100; j++) {
				if(arr[i][j] == true) {
					for(int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(arr[nx][ny] == false) answer++;
					}					
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			fst = Integer.parseInt(st.nextToken());
			snd = Integer.parseInt(st.nextToken());
			fill_arr();
		}
		count();
		System.out.println(answer);
	}
}