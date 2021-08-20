import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [0817] BOJ 2798 블랙잭
 *
 * sol) 조합을 이용한 완전탐색
 * tc) 100C3
 */

public class BOJ2798 {
	static int N, M, closest, closestAbs = Integer.MAX_VALUE;
	static int[] cards;
	static int[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cards = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		// next permutation 조합 시 선택된 카드 인덱스
		selected = new int[N];
		selected[N-3] = selected[N-2] = selected[N-1] = 1;    // 오름차순 정렬
		//combination(0, 0, 0);
		
		do {
			int sum = 0;
			for (int i=0; i<N; i++) {
				if (selected[i] == 1) sum += cards[i];    // 조합으로 고른 카드 합
			}
			findClosest(sum);
		} while (nextPermutation());
		
		System.out.println(closest);
		
	}
	
	// 넥퍼로 조합 구현
	private static boolean nextPermutation() {
		int top = N-1;
		while(top>0 && selected[top-1] >= selected[top]) top--;
		
		if (top == 0) return false;
		
		int target = N-1;
		while(selected[top-1] >= selected[target]) target--;
		
		swap(top-1, target);
		
		int bottom = N-1;
		while(bottom > top) swap(top++, bottom--);
		
		return true;
		
	}
	
	// 넥퍼를 위한 조합
	private static void swap(int i, int j) {
		int tmp = selected[i];
		selected[i] = selected[j];
		selected[j] = tmp;
	}

	// 조합
	private static void combination(int depth, int start, int sum) {
		if (depth == 3) {
			findClosest(sum);
			return;
		}
		
		// 매개변수로 카드 조합 숫자를 더해가며 합을 구함
		for (int i=start; i<N; i++) combination(depth+1, i+1, sum+cards[i]);
		
	}
	
	// M 이내이면서 M과 가장 가까운 값 구하기
	private static void findClosest(int sum) {
		if (sum <= M && closestAbs > Math.abs(M-sum)) {
			closestAbs = Math.abs(M-sum);
			closest = sum;
		}
	}

}
