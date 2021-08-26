import java.io.*;
import java.util.*;
/**
 * SWEA 6485 삼성시의 버스 노선 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWczm7QaACgDFAWn
 * 
 * 완전탐색 풀이
 * 1. 모든 버스정류장을 커버할 수 있는 5000 범위의 버스정류장 배열을 선언하고,
 *    범위각 버스 노선의 정보가 주어지면, 해당 버스 노선 범위의 모든 버스정류장의 count를 증가시킨다.
 *    
 * 2. 버스노선 정보 (버스정류장 시작, 도착) 정보를 배열에 저장해둔 후
 *    버스 정류장 번호가 들어오면 모든 버스노선 정보를 탐색하며 포함되는경우 count++
 *    -> 버스노선 정보를 시작 정류장 기준으로 sorting
 *       -> 시작 정류장을 비교함
 *          Case 1. 현재 버스노선의 시작 정류장이 지금 비교중인 정류장보다 크다 
 *                  -> 뒤쪽 버스노선들은 무조건 겹치지 않음
 *          Case 2. 현재 버스노선의 시작 정류장이 나보다 작다
 *                  -> 마지막 정류장이 나보다 크다 -> 현재 정류장이 포함됨
 */

public class SWEA_6485_삼성시의버스노선_김경준 {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T, N, P, fst, snd, arr[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr, (a1, a2) -> a1[0] - a2[0]);  // 시작 정류장 기준으로 sort
			
			P = Integer.parseInt(br.readLine());
			sb.append("#").append(tc).append(" ");
			for(int i = 0; i < P; i++) {
				int num = Integer.parseInt(br.readLine());
				solve(num);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}	
	static void solve(int num) {
		int answer = 0;
		for(int i = 0 ; i < N; i++) {
			if(num < arr[i][0]) break;       // 만약 시작 정류장이 나보다 크다면 -> 뒤쪽은 볼 필요 없음
			if(num <= arr[i][1]) answer++;  // 시작 정류장이 나보다 작고, 종료 정류장이 나보다 크다 -> 내가 버스노선 범위에 포함
		}
		sb.append(answer).append(" ");
	}
}
