import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0810] 백준 11728 배열 합치기
 * 
 * sol)
 * 각 배열의 맨 앞 원소를 비교하며 더 작은 원소를 선택
 * 각 배열의 마지막에 최대값 삽입하여 인덱스 비교
 * 배열 출력시에는 마지막 값 제거하고 출력
 * 
 * time_complex)
 * O(n)
 */

public class BOJ11728 {
	static int N, M;
	static int[] A;
	static int[] B;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N+1];
		B = new int[M+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		A[N] = Integer.MAX_VALUE;    // 비교를 위해 배열의 마지막에 최대값 삽입
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		B[M] = Integer.MAX_VALUE;
		
		sortArrays();
		
		// 인덱싱을 위해 삽입한 마지막 원소 제거하여 출력
		sb.setLength(sb.length()-12);
		System.out.println(sb);
	}

	// 두 개의 배열의 앞부터 접근하여 크기가 더 작은 원소 선택
	private static void sortArrays() {
		int a = 0;
		int b = 0;
		
		while(a<=N && b<=M) {
			if (A[a] <= B[b]) sb.append(A[a++]).append(" ");
			else sb.append(B[b++]).append(" ");
		}
	}

}
