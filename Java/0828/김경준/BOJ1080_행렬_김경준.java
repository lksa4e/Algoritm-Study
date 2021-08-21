import java.io.*;
import java.util.*;
/**
 * BOJ 1080 행렬 : https://www.acmicpc.net/problem/1080
 * 메모리 : 14304KB 시간 : 144ms
 * 
 * 전체 배열을 탐색하여, A B가 다를때 3*3 만큼 바꿔준다.
 * 좌표 상에서, 왼쪽 위부터 탐색하면서 오른쪽 아래 방향으로 바꾸어주므로, 앞쪽을 다시 살필 필요가 없다.
 * 단순하게 N-3, M-3 범위에서 비교하며 다르면 change 시켜준 후 최종 결과값을 비교한다.
 * 
 * Time Complexity)
 * 최악의 경우 N * M 만큼 탐색하면서 모두 뒤집는 경우이다.
 * 이때 뒤집는 연산은 정확히 9만큼 소요된다. O(N*M) = O(NM)  // 1 <= N,M <= 50
 * 
 */

public class BOJ1080_행렬_김경준 {
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, M, answer = 0;
	static int mapA[][], mapB[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mapA = new int[N][M];
		mapB = new int[N][M];
		
		// 입력부분
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				mapA[i][j] = str.charAt(j) - '0';
				if(mapA[i][j] == 0) mapA[i][j] = -1;  // 밑에서 비교연산자 쓰기 귀찮아서 -1로 치환
			}
		}
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				mapB[i][j] = str.charAt(j) - '0';
				if(mapB[i][j] == 0) mapB[i][j] = -1;
			}
		}
		
		solve();
		
		if(check()) System.out.print(answer);
		else System.out.print(-1);
	}
	
	static void solve() {  // 비교하면서 A, B 다르면 3x3 변환 수행
		for(int i = 0; i < N - 2; i++) {
			for(int j = 0; j < M - 2; j++)
				if(mapA[i][j] != mapB[i][j]) change(i,j);
		}
	}
	
	static void change(int x, int y) {
		answer++;
		for(int i = x; i < x + 3; i++) {
			for(int j = y; j < y + 3; j++) {
				mapA[i][j] *= -1; 			 // 1 , -1로 치환해주었으니 그냥 *-1로 처리	
			}
		}
	}
	
	static boolean check() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				if(mapA[i][j] != mapB[i][j]) return false;   // 중간에 다르면 바로 return
		}
		return true;
	}
}
