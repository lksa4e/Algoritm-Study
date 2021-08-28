import java.io.*;
import java.util.*;
/**
 * BOJ 14889 스타트와 링크 : 
 * 메모리 : 18216KB 시간 : 480ms
 * 
 * SWEA에서 풀었던 요리 문제와 사실상 같은 문제
 * nCn/2 을 구하고, 만들 수 있는 모든 시너지 만들어서 더해준 뒤
 * 시너지의 차이를 구하기
 * 
 * 조합 -> next_permutation
 * 모든 시너지의 합 -> for문을 이용한 순열
 */

public class BOJ14889_S3_스타트와링크 {
	static StringBuilder sb;
	static StringTokenizer st;
	static int N, M, map[][], idn[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		idn = new int[N];
		// 입력부분
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = N-1; i >= N/2; i--) idn[i] = 1;
		
		int result = Integer.MAX_VALUE;
		do {
			int start_point = 0;
			int link_point = 0;
			for(int i = 0; i < N; i++) {     // 모든 2개 쌍 순열에 대해 시너지의 합 구하기
				for(int j = 0; j < N; j++) {
					if(idn[i] == 1 && idn[j] == 1) start_point += map[i][j];   
					if(idn[i] == 0 && idn[j] == 0) link_point += map[i][j];   
				}
			}
			result = Math.min(result, Math.abs(start_point - link_point));
		}while(next_permutation(idn));
		
		System.out.print(result);
	}
	static boolean next_permutation(int[] arr) {
		int N = arr.length;
		int i = N - 1;
		// 뒤쪽 출발, 내림차 아닐 때
		while (i > 0 && arr[i - 1] >= arr[i])
			--i;
		if (i == 0)
			return false;

		int j = N - 1;
		// 뒤쪽 중 가장 작은숫자
		while (arr[i - 1] >= arr[j])
			--j;
		swap(arr, i - 1, j);
		// sort
		int k = N - 1;
		while (i < k)
			swap(arr, i++, k--);
		return true;
	}

	static void swap(int[] arr, int fst, int snd) {
		int temp = arr[fst];
		arr[fst] = arr[snd];
		arr[snd] = temp;
	}
}
