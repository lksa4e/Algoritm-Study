package SWEA.study0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 버스정류장의 개수가 5000개라서 그냥 사이즈가 5000인 배열을 생성하면 간단하게 풀이가 가능할거라 생각함
 * i번째 버스의 a와 b가 들어오면 a~b까지 반복문으로 버스정류장 배열에 +1을 하면
 * 나중에 어느 정류장에 버스가 몇개가 나오는지 자동으로 산출됨
 * 메모리		시간
 * 19,316	125
 */

public class Solution6485_배문규 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, N, A[], B[], P, C[], bus[];

	public static void main(String[] args) throws NumberFormatException, IOException {

		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			A = new int[N];
			B = new int[N];
			// 5000개의 정류장 배열생성
			bus = new int[5001];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				A[i] = Integer.parseInt(st.nextToken());
				B[i] = Integer.parseInt(st.nextToken());
				// 정류장에 i번 버스가 다닐수 있는 범위만큼 버스 수 추가
				for(int b = A[i]; b <= B[i]; b++) bus[b]++;
			}
			
			// 
			P = Integer.parseInt(br.readLine());
			sb.append("#").append(tc).append(" ");
			// 입력 받은 정류장에 다니는 버스 수 sb에 추가
			for(int i = 0; i < P; i++) sb.append(bus[Integer.parseInt(br.readLine())]).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
