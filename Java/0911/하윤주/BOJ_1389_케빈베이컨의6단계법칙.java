import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0907] 백준 1389 케빈 베이컨의 6단계 법칙
 * 그래프, 플로이드 워샬
 * 
 * sol)
 * 그래프에 존재하는 모든 정점 사이의 최단 경로를 한번에 찾는 알고리즘
 * 0번 정점에서 k-1번 정점까지 최단경로를 구했다고 가정한다면,
 * i~j사이의 최단경로는 i~k~j와 i-k 중 비용이 더 작은 쪽이 됨
 * 
 * tc)
 * 1) 값 업데이트, 구간합 구하기
 * 		log(1,000,000) * 10,000 = 약 200,000
 *	
 */

public class BOJ_1389_케빈베이컨의6단계법칙 {
	static int N, M, winner, minRelation = 101;     // 각 가중치는 1씩이므로 비용 최대는 사람수를 넘지 않음
	static int[][] adjMatrix;                       // 정점 간 가중치 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 가중치 최댓값으로 초기화
		adjMatrix = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (i!=j) adjMatrix[i][j] = 101;     // 자기 자신 정점은 0으로 둠
			}
		}
		
		// 인접 행렬에 가중치 저장
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			
			// 단방향 그래프
			adjMatrix[A][B] = 1;
			adjMatrix[B][A] = 1;
		}
		
		floydWarshall();
		totalRelation();
		
		System.out.println(winner);
	}

	// 최단 경로 구하기 - 플로이드 워샬
	private static void floydWarshall() {
		for (int k=0; k<N; k++) {
			for (int i=0; i<N; i++) {
				if (k == i) continue;        // 자기 자신이면 pass
				for (int j=0; j<N; j++) {
					if (k == j || i == j) continue;      // 자기 자신이면 pass
					
					int via = adjMatrix[i][k] + adjMatrix[k][j];            // k를 경유한 비용과
					int straight = adjMatrix[i][j];                         // 직선 비용 중
					
					adjMatrix[i][j] = via < straight ? via : straight;      // 최소값으로 비용갱신
				}
			}
		}
	}

	// 가장 많이 결합된 사람 찾기
	private static void totalRelation() {
		for (int i=0; i<N; i++) {
			
			int sum = 0;
			for (int j=0; j<N; j++) sum += adjMatrix[i][j];
			
			if (sum < minRelation) {
				minRelation = sum;
				winner = i+1;
			}
		}
	}

}
