import java.io.*;
import java.util.*;

public class Main17471 {
	/*
	 * 조합으로 두 선거구 나누고
	 * 각 선거구가 가능한 선거구인지 확인 (가능한 선거구 = 선거구 내 구역끼리 연결되있음) 
	 * 둘 다 가능한 선거구라면 인구 차이 최솟값 갱신
	 * 모든 경우의 수에서 두 선거구로 나눌 수 없다면 -1
	 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	static int N, people[], area[], area2[], matrix[][], e, totPeople, minDiff = 1001;
	static boolean visited[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		// 각 구역 인구수 저장
		people = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			totPeople += people[i]; // 전체 인구
		}
		
		// 인접행렬 만들기
		matrix = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j=0; j<size; j++) {
				int k = Integer.parseInt(st.nextToken());
				matrix[i][k] = matrix[k][i] = 1;
			}
		}
		
		// 1~N/2 조합 구하기
		for(int i=1; i<=N/2; i++) {
			area = new int[i];
			comb(0,i,1);
		}
		
		// 최솟값 갱신 안됐으면 -1
		System.out.println((minDiff==1001)?-1:minDiff);
		
	}
	
	private static void comb(int cnt, int k, int start) {
		if(cnt == k) { // k개 조합 완성되면
			e=0; // 탐색 수 초기화
			visited = new boolean[N+1]; // 방문배열 초기화
			dfs(0, area); // dfs로 선거구 연결여부 탐색
			
			if(e==k) { // k번 모두 탐색했다면 가능한 선거구
				// 나머지 선거구 생성
				visited = new boolean[N+1];
				area2 = new int[N-k];
				for(int i=0; i<k; i++) {
					visited[area[i]] = true;
				}
				for(int i=1, j=0; i<=N; i++) {
					if(!visited[i]) {
						area2[j++] = i;
					}
				}
				
				// 두번째 선거구도 가능한지 탐색
				e=0;
				visited = new boolean[N+1];
				dfs(0, area2);
				
				if(e==N-k) { // 가능한 선거구라면 인구차이 최솟값 갱신
					int sum = 0;
					for(int i=0; i<k; i++) {
						sum += people[area[i]];
					}
					int diff = Math.abs(totPeople - sum - sum);
					
					if(minDiff > diff) minDiff = diff;
				}
			}
			return;
		}
		
		for(int i=start; i<=N; i++) {
			area[cnt] = i;
			comb(cnt+1, k, i+1);
		}
	}
	
	private static void dfs(int v, int[] arr) {
		visited[arr[v]] = true;
		e++;
		for(int i=1; i<arr.length; i++) {
			if(matrix[arr[v]][arr[i]] == 1 && !visited[arr[i]]) {
				dfs(i, arr);
			}
		}
	}
	
}
