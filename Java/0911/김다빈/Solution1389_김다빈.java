import java.util.*;
import java.io.*;

/**
 * 백준 1389번 케빈 베이컨의 6단계 법칙 
 *
 * 풀이 : 플로이드 와샬 알고리즘
 * 
 * >> 다익스트라 알고리즘 
 * = "하나의 정점"에서 출발했을 때 다른 "모든 정점"으로의 최단 경로를 구하는 알고리즘 
 * 
 * >> 플로이드 와샬 알고리즘
 * = "모든 정점"에서 "모든 정점"으로의 최단 경로를 구하는 알고리즘 
 * => "거쳐가는 정점"을 기준으로 최단 거리 구하기 
 * 
 * 모든 유저에 대해 케빈 베이컨의 수를 계산해야 하므로 플로이드 와샬 알고리즘 사용 
 * 
 * >> 시행착오	
 * 	INF를 단순하게 Integer.MAX_VALUE로 설정하고
 *  비교 연산자로 직접 비교하지 않고 Math.min()으로 비교해주면
 * 	friends[a][cur] + friends[cur][b] = INF + V로 음수가 나오는 불상사 발생..
 * 	
 * 	따라서 최대 유저 수가 100이라고 주어졌으므로 최대 단계 수인 99+1 = 100으로 설정해주었다!
 * 
 * 
 * 14192KB	136ms
 */
public class Solution1389_김다빈 {
	
	static int N, M;
	static int INF = 100;	// 최대 유저의 수가 100이므로 최대 단계 수는 99
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// friends 배열 초기화 
		int[][] friends = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(friends[i], INF);
			friends[i][i] = 0;	// 자기 자신으로 가는 정점은 0으로 초기화 
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			// A와 B가 서로 친구 (1단계로 설정)
			friends[A][B] = 1;
			friends[B][A] = 1;
		}
		
		floydWarshall(friends);
	}

	private static void floydWarshall(int[][] friends) {
		for (int cur = 1; cur <= N; cur++) {	// cur 노드를 거쳐가는 경우 
			for (int a = 1; a <= N; a++) {		// 출발 노드 a
				for (int b = 1; b <= N; b++) {	// 도착 노드 b
					// a->b 보다 a->cur->b가 최소인 경우 갱신
					friends[a][b] = Math.min(friends[a][b], friends[a][cur] + friends[cur][b]);
				}
			}
		}
		
		int minIndex = 1, result = INF;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			
			for (int j = 1; j <= N; j++) {
				sum += friends[i][j];			
			}
			
			// 케빈 베이컨의 수가 가장 작은 사람이 여러명이면 번호가 가장 작은 사람 출력 (>로 비교)
			if(result > sum) {
				minIndex = i;
				result = sum;
			}
		}
		
		System.out.println(minIndex);
	}

}
