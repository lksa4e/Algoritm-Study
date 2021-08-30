import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  BOJ 14889번 스타트와 링크
 *  
 *  넥퍼로 2팀으로 나누고, 나눈 팀 간의 차이를 구하는 문제
 *  
 */

public class BOJ_14889 {
	static int N, map[][], minDiff = Integer.MAX_VALUE;
	static int[] selected;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br .readLine());
		StringTokenizer st = null;
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		selected = new int[N];
		for (int i = N - 1; i >= N/2; i--) {
			selected[i] = 1;
		}
		
		do {
			getDiff();
			if(minDiff == 0) break;
		}while(np(selected));
		
		System.out.println(minDiff);
	}
	
	static boolean np(int[] arr) {
		int i = N - 1;
		while(i > 0 && arr[i - 1] >= arr[i]) i--;
		
		if(i == 0) return false;
		
		int j = N-1;
		while(arr[i - 1] >= arr[j]) j--;
		swap(arr, i-1, j);
		
		int k = N-1;
		while(i<k) swap(arr, i++,k--);
		
		return true;
	}
	
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	static void getDiff() {
		int sumStart = 0, sumLink = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if(selected[i] == 0 && selected[j] == 0)	// 0이면 start 팀에 능력치 합
					sumStart += map[i][j] + map[j][i];
				else if(selected[i] == 1 && selected[j] == 1)
					sumLink += map[i][j] + map[j][i];
			}
		}
		minDiff = Math.min(minDiff, Math.abs(sumStart - sumLink));
	}
}
