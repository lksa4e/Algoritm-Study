package BaekOJ.study.date1012;

import java.io.*;
import java.util.*;

/*
 * 백준 15684 : 사다리 주작
 * 
 * 넥퍼로 사다리를 세울 수 있는 조합을 만들고 완탐했음
 * 주요 조건이 추가할 수 있는 가로선 개수가 3개가 넘으면 -1을 출력하기 때문에
 * 0, 1, 2, 3까지만 탐색해주면 된다고 생각함.
 * 그리고 조합에서 이미 가로선이 1개라도 이미 그어진 조합은 패스했는데도 불구하고 메모리 300메가에 2초가 넘었음...
 * 이 풀이에서 최적화를 어떻게 더 하면 좋을까 생각해보다가 
 * check배열 안쓰고 subset배열을 활용해보면 좀 개선이 될 것 같은데 그냥 통과했음에 의의를 두기로 했음
 * 
 * 메모리 	시간
 * 298728	2432
 */

public class BaekOJ15684_배문규 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int LIMIT = 3;
	static int N, M, H, result = -1;
	static boolean check[], copyCheck[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		check = new boolean[H*(N-1)];
		
		// 가로선이 위치할 수 있는 배열 [N-1][H]로 하려다가 조합을 만들어야 하니 1차원으로 만들어 줌
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			check[(h-1)*(N-1)+v-1] = true;
		}		
		
		if(M > 1) {
			// 0, 1, 2, 3개의 원소를 가진 부분집합 배열
			int subset[][] = new int[4][H*(N-1)];
			for(int i = 1; i <= 3; i++) {
				int idx = H*(N-1);
				while(idx-- >= H*(N-1)-i+1) subset[i][idx] = 9;
			}
			
			// 원소의 개수가 0, 1, 2, 3개인 부분 집합 순으로  조합을 구성함 
			for(int i = 0; i <= 3; i++) {
				int idx[] = new int[i]; // 
				do {
					Arrays.fill(idx, 0);
					int n = 0;
					for(int j = 0; j < subset[i].length; j++) 
						if(subset[i][j] == 9) idx[n++] = j; // 가로선 조합의 인덱스를 담음
					manipulation(idx, i);
				}while(np(subset[i]));
			}
			System.out.println(-1);
		}
		// M이 0이면 무조건 0이고, 1이면 무조건 1임
		else if(M == 0) System.out.println(0);
		else if(M == 1) System.out.println(1);
	}
	
	public static void manipulation(int[] arr, int cnt) {
		// 체크배열 복사하고 
		copyCheck = check.clone();
		if(arr.length > 0) {
			for(int idx : arr){
				if(copyCheck[idx]) return;
				copyCheck[idx] = true;
			}
		}
			
		for(int x = 0; x < N; x++) {
			int y = x;
			for(int line = 0; line < H; line++) {
				// 사다리 타기
				// 양 끝은 안쪽 한 곳만 탐색
				if(y == 0) {
					if(copyCheck[(N-1)*line+y]) y += 1;
				}
				else if(y == N-1) {
					if(copyCheck[(N-1)*line+y-1]) y -= 1; 
				}
				// 중간 부분은 양 쪽 탐색
				else {
					if(copyCheck[(N-1)*line+y]) y += 1;
					else if(copyCheck[(N-1)*line+y-1]) y -= 1;
				}
			}
			// 사다리 다 타고 내려왔는데 값이 다르면 리턴
			if(y != x) return;
		}
		
		// 모든 라인이 값이 같다면 가로선 개수를 출력하고 프로그램 종료
		System.out.println(cnt);
		System.exit(0);
	}
	
	public static boolean np(int[] arr) {
		int i = H*(N-1)-1;
		while(i>0 && arr[i-1] >= arr[i]) i--;
		if(i == 0) return false;

		int j = H*(N-1)-1;
		while(arr[i-1] >= arr[j]) j--;
		
		swap(arr, i-1, j);

		int k = H*(N-1)-1;
		while(i<k) swap(arr, i++, k--);

		return true;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
