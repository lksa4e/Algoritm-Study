package P0810;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 11728번 배열 합치기
 * @author 김다빈 
 *
 */
public class Solution11728_김다빈 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// N : A 배열의 크기, M : B 배열의 크기 
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arrA = new int[N];
		st = new StringTokenizer(br.readLine());
		
		// A 배열 입력 받기 
		for(int i=0;i<N;i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		
		// A 배열 인덱스 
		int idx = 0;
		st = new StringTokenizer(br.readLine());
		
		// B 배열 하나씩 입력 받으면서 비교 
		for(int i=0;i<M;i++) {
			int element = Integer.parseInt(st.nextToken());
			
			// A 배열 크기 확인하면서 B 배열 요소보다 작으면 출력 
			while(idx < N && element > arrA[idx]) {
				sb.append(arrA[idx++]+" ");
			}
			// B 배열 요소 출력 
			sb.append(element+" ");
		}
		
		// 남아있는 A 배열 요소 출력 
		while(idx < N) {
			sb.append(arrA[idx++]+" ");
		}
		
		System.out.println(sb);
	}	

}
