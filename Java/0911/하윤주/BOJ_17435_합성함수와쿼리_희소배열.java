import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0913] 백준 17435 합성함수와 쿼리
 * 그래프, 희소배열, DP
 * 
 * sol)
 * 문제의 함수를 재귀적으로 중첩한 결과 같은 흐름의 결과값이 반복됨.
 * -> 유향 그래프에서 사이클을 찾는 문제로 접근
 * 
 * 2가지 방법으로 풀어봄
 * 1) dfs로 사이클을 찾은 뒤 중첩된 횟수(n)만큼 사이클 원소를 타고가 답을 찾음
 * 2) 희소배열을 이용해 한번에 1~logn까지 타고가 만나는 진출노드를 기억해 답을 찾음(희소배열 생성 과정에서 DP활용)
 * 
 * 1번으로 풀 경우 구현이 쉽지만 사이클 내 원소 개수만큼씩 타고가야하며, 어차피 중복되는 사이클을 탐색하는 것이므로 비효율적임 -> 시간 낭비
 * 만약 1번으로 풀되, (중첩된 횟수/사이클 원소 개수)로 탐색의 중복을 피하더라도 함수마다 동일한 사이클을 여러개 저장하고 있는 비효율이 발생 -> 공간 낭비
 * 
 * 2번으로 푼다면 1, 2, 4, 8 ... 처럼 한번에 많은 단계를 이동해 탐색 가능하므로 시간이 단축되며,
 * 1~logn까지 이동 단계를 저장해두는 배열이 필요하므로 nlogn만큼의 공간이 사용되어 공간을 아낄 수 있다.
 * 
 * 시행착오)
 * 1번으로 풀다가 메모리초과 받고 이렇게 푸는게 아니구나..했음
 * 
 * tc)
 * 희소 배열 초기화 : m * O(log m)
 * 중첩 결과 쿼리 : O(log m)
 *	
 */

public class BOJ_17435_합성함수와쿼리_희소배열 {
	static int m, size;
	static int[] fx;               // 초기 함수값(f(x))
	static int[][] sparseTable;    // 1~logn까지 함수를 중첩시킨 결과를 저장한 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		m = Integer.parseInt(br.readLine());
		size = (int)(Math.log(500000)/Math.log(2))+1;      // 1~logn까지 중첩된 함수의 결과를 저장하기 위한 배열의 사이즈 계산(logn계산)
		fx = new int[m+1];
		
		// 최초의 함수값
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=m; i++) fx[i] = Integer.parseInt(st.nextToken());
		
		// 희소 배열에 1~logn까지 함수를 중첩시킨 결과를 저장
		sparseTable = new int[size][m+1];
		makeSparseTable();
		
		// f(x)함수를 n번 중첩한 결과를 구하기
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		for (int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(query(Integer.parseInt(st.nextToken()),       // n
							Integer.parseInt(st.nextToken())));     // x
			sb.append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}
	
	// 희소 배열 초기화
	private static void makeSparseTable() {
		// 함수를 1번 중첩시킴(f(x) 결과 그 자체)
		for (int i=1; i<=m; i++) {
			sparseTable[0][i] = fx[i];
		}
		
		// 함수를 logn번까지 중첩시킴
		for (int k=1; k<size; k++) {
			for (int i=1; i<=m; i++) {
				int next = sparseTable[k-1][i];                 // 현재 f(x) 결과값을 next라고 하면
				sparseTable[k][i] = sparseTable[k-1][next];     // 함수를 중첩시킨 f(f(x))는 f(next)가 되므로, 이렇게 구한 값을 중첩결과에 저장
			}
		}
	}
	
	// f(x)를 n번 중첩한 결과 구하기
	private static int query(int n, int x) {
		for (int bit=0; bit<size; bit++) {     // 비트 연산을 이용해 1~logn 중첩까지 중 n번 중첩 결과값을 쉽게 탐색
			if ((n & (1<<bit)) != 0) {
				x = sparseTable[bit][x];
			}
		}
		return x;
	}
	
}
