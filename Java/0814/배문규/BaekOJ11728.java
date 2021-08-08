package study.date0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekOJ11728 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
			
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// A와 B모두 이미 소팅이 되어있음 -> merge
		// MAX값을 넣어줄 마지막 인덱스자리 추가
		int[] A = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
		
		int[] B = new int[M+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());
		
		// 값의 범위가 최대 10억이니까 A와 B 마지막인덱스에 Integer.MAX_VALUE 입력
		// 둘 중 한쪽이 마지막 인덱스에 도달해도 나머지 배열의 어떤 값보다 크기 때문에 if문을 통과하지 않아 런타임 에러가 발생하지 않음
		A[N] = Integer.MAX_VALUE;
		B[M] = Integer.MAX_VALUE;
		int aIdx = 0, bIdx = 0;
		// A와 B모두 마지막 인덱스만 남았을 때, break
		while(aIdx+bIdx != N+M) {
			// A가 B보다 작거나 같으면  A 추가
			if(A[aIdx] <= B[bIdx]) sb.append(A[aIdx++]).append(" ");
			else sb.append(B[bIdx++]).append(" ");
		}
		
		System.out.println(sb.toString());
	}

}
