package BaekOJ.study.date0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 예전에 dfs로 조합을 짜서 전력의 차를 구했더니 시간이 너무 오래걸렸던 적이 있었다.
 * 수업시간에 풀었던 요리사 문제와 완전히 동일한 문제인데, 그 때 좀 더 다른 방법으로 풀어봄
 * map에서 각 열과 행의 합을 저장한다. 그리고 팀이 2개라서 전체에서 선택한 사람의 인덱스의 행의 합과 열의 합을 빼주는 재귀 조합을 짜서 문제를 풀었음. 
 * 만약 {0,1,2,3} 중에서 start팀이 0,1 로 구성이 된다고 하자.
 * 여기서 map에서 R[0]과C[0],R[1]과C[1]을 빼주면 start팀인 map[0][1]과 map[1][0]은 음수가 되고, link팀인 map[2][3]과 map[3][2]는 양수가 된다.
 * 그리고 나머지는 다 0이기 때문에 결국 전체 -(start팀 인덱스 행, 열의 합) = start팀과 link팀의 전력의 차가 된다.
 * 해당 조합의 능력의 합을 구하는 추가 반복연산이 필요하지 않음
 * 
 * 메모리 	시간
 * 14456	152
 */

public class BaekOJ14889_배문규 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, N, map[][], R[], C[], total, _min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_min = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		R = new int[N];
		C = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int ability = Integer.parseInt(st.nextToken());
				total += ability;
				R[i] += ability; // 행 값 저장
				C[j] += ability; // 열 값 저장
			}
		}
		
		makeTeam(1, 1, total-(R[0]+C[0]));
		System.out.println(_min);
	}
	
	// idx = start팀 선택 인덱스, cnt = N/2까지 도달하면 팀 구성, gap = cnt가 N/2가 되면 양팀의 전력의 차
	// gap에서 n/2번 R과 C의 idx 값들을 빼면 start팀(음수) + link팀(양수) 전력의 차가 됨
	// 루트를 제외하고 depth가 n/2인 dfs로 조합 구성
	public static void makeTeam(int idx, int cnt, int gap) {
		// 팀이 만들어 지면 전력의 최솟값 찾고 해당 조합 종료
		if(cnt == N/2) {
			_min = Math.min(_min, Math.abs(gap)); // 전력차가 음수가 될 수 있으므로 절댓값
			return;
		}
		if(idx == N) return; // 인덱스가 N-1번째까지니까 N에서 return 해줘야 함 
		// 해당 인덱스 선택 O
		makeTeam(idx+1, cnt+1, gap-(R[idx]+C[idx]));
		// 해당 인덱스 선택 X
		makeTeam(idx+1, cnt, gap);
	}
}
