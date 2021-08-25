import java.io.*;
import java.util.*;

/**
 * SW Expert 6485번 삼성시의 버스 노선
 *
 * 풀이 : 구현?
 * 1. 버스 정류장의 개수가 최대 5000개이므로 int[5001] 배열에 지나간 버스의 개수 저장
 * 2. 버스 노선을 입력받으면 그 사이의 버스 정류장의 값을 증가시킨다.
 * 3. 이후 입력받은 버스 정류장 인덱스로 배열에 접근하여 값 출력 
 * 
 * 21,940 kb	129 ms
 */
public class Solution6485_김다빈 {
	
	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input_6485.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] allStop = new int[5001];	// 모든 버스정류장을 지나는 버스의 개수 저장 (최대 5000) 
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				for(int j=start;j<=end;j++) {	// 버스 노선 사이에 있는 버스정류장++ 
					allStop[j]++;
				}
			}
			
			int M = Integer.parseInt(br.readLine());
			
			sb.append("#"+test_case+" ");
			for(int i=0;i<M;i++) {
				int index = Integer.parseInt(br.readLine());
				sb.append(allStop[index]+" ");	// 해당 버스 정류장을 지나는 버스의 개수 출력 
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
