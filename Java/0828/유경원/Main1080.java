import java.io.*;
import java.util.*;

public class Main1080 {
	/*
	 * 처음부터 끝까지 탐색하며 행렬 A와 B가 다른 부분이 있다면 변환 연산을 해준다
	 * 다 돌았는데 같다면 변환 연산 횟수, 다르다면 -1 출력
	 * 
	 * -시행착오
	 * 문제 입력 조건에서 N,M이 3이상이라는 조건이 없는데 별 생각없이 짯다가 arrayIndex에러~
	 * => for문 범위 조정으로 해결
	 */
	static int N, M;
	static char[][] mapA, mapB;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		mapA = new char[N][];
		mapB = new char[N][];

		for (int i = 0; i < N; i++) 
			mapA[i] = br.readLine().toCharArray();
		
		for (int i = 0; i < N; i++) 
			mapB[i] = br.readLine().toCharArray();

		int cnt = 0;
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 2; j++) {
				if (mapA[i][j] != mapB[i][j]) {
					swap(i, j);
					++cnt;
				}
			}
		}

		System.out.println((equalMap()) ? cnt : -1);
	}

	static void swap(int x, int y) {
		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				mapA[i][j] = (mapA[i][j] == '0') ? '1' : '0';
			}
		}
	}

	static boolean equalMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (mapA[i][j] != mapB[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

}
